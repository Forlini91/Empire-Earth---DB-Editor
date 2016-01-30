package datmanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.swing.JOptionPane;

import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;
import gui.DialogProgressBar;
import gui.FrameEditor;
import gui.FrameMain;

/**
 * Core class. Contains the method main, the main data loaded by the program and some useful methods
 * @author MarcoForlini
 *
 */
public class Core {
	
	/** Background used in all frames, windows and dialogs */
	public static final Color UI_COLOR_BACKGROUND = new Color(249, 241, 224);
	/** Color used in all buttons */
	public static final Color UI_COLOR_ELEMENT = new Color (150, 15, 15);
	/** Color used in all buttons (mouse over)*/
	public static final Color UI_COLOR_ELEMENT2 = UI_COLOR_ELEMENT.brighter();
	/** Color used in all buttons (button pressed)*/
	public static final Color UI_COLOR_ELEMENT3 = UI_COLOR_ELEMENT2.brighter();
	/** Max time (milliseconds) it will wait for loading to complete. If time exceed this value, the load is considered failed. */
	private static final int LOAD_MAX_WAIT = 15000;
	
	public static final Map<DatStructure, DatFile> DAT_FILES = new HashMap<>();
	public static final Map<DatStructure, List<EntryGroup>> DATA = new HashMap<>();
	public static final Map<DatFile, FrameEditor> FRAME_EDITORS = new HashMap<>();
	
	
	
	public static void main (String[] args) {
		System.out.println("Check entries definitions:");
		int count;
		for (DatStructure datStructure : DatStructure.values()){
			count = 0;
			for (FieldStruct fieldStruct : datStructure.entries){
				count += fieldStruct.size;
			}
			System.out.println('\t' + datStructure.fileName + ':' + ' ' + count);
		}
		EventQueue.invokeLater(() -> {
			new FrameMain();
		});
	}
	
	/**
	 * Attempt to find an entry by ID.
	 * @param datStructure	The dat structure which should contains the entry
	 * @param ID	The ID to search
	 * @return	An array {EntryGroup, Entry} if succeed, null if no entry exists with that ID in the given datStructure
	 */
	public static Object[] findEntryByID(DatStructure datStructure, int ID){
		Entry entry;
		for (EntryGroup entryGroup : DATA.get(datStructure)){
			entry = entryGroup.map.get(ID);
			if (entry != null){
				return new Object[]{entryGroup, entry};
			}
		}
		return null;
	}
	
	

	/**
	 * Load the given file and disable (but not freeze) the calling window until finished.
	 * @param parent	The parent window
	 * @param file		The file to load
	 * @param onLoaded	The code to run if loading succeed
	 * @param onFail	The code to run if loading fail
	 */
	public static void loadFile(Window parent, DatFile datFile, BiConsumer<List<EntryGroup>, Map<DatStructure, DatFile>> onLoaded, Runnable onFail){
		loadFiles(parent, Arrays.asList(datFile), (data, datFile2) -> {
			onLoaded.accept(data.get(datFile.datStructure), datFile2);
		}, onFail);
	}
	
	/**
	 * Load the given list of files and disable (but not freeze) the calling window until finished.
	 * @param parent	The parent window
	 * @param files		The files to load
	 * @param onLoaded	The code to run if loading succeed
	 * @param onFail	The code to run if loading fails
	 */
	public static void loadFiles(Window parent, List<DatFile> files, BiConsumer<Map<DatStructure, List<EntryGroup>>, Map<DatStructure, DatFile>> onLoaded, Runnable onFail){
		if (parent != null){
			parent.setEnabled(false);
		}
		new Thread(() -> {
			Object lockObj = new Object();
			DialogProgressBar progressDialog = new DialogProgressBar("Loading...", files.size(), true);
			Map <DatStructure, List <EntryGroup>> dataLoad = new HashMap<>();
			Map <DatStructure, DatFile> datFiles = new HashMap <>();
			for (int i = 0; i < files.size(); i++){
				int index = i;
				Thread t = new Thread(() -> {
					DatFile datFile = files.get(index);
					try {
						DatFileManager dbManager = new DatFileManager(datFile);
						List<EntryGroup> entryGroups = dbManager.read(progressDialog::updatePercPart, index);
						dataLoad.put(datFile.datStructure, entryGroups);
						datFiles.put(datFile.datStructure, datFile);
					} catch (Exception e) {
						System.err.println(e.getMessage());
						JOptionPane.showMessageDialog(parent, "An error occurred during the loading of " + datFile, "Error", JOptionPane.ERROR_MESSAGE);
					} finally {
						synchronized(lockObj){
							if (dataLoad.size() >= files.size()) {
								lockObj.notify();
							}
						}
					}
				});
				t.start();
			}
			
			try {
				synchronized(lockObj){
					if (dataLoad.size() < files.size()){
						lockObj.wait(LOAD_MAX_WAIT);
					}
				}
			} catch (InterruptedException e) {
				return;
			}
			progressDialog.dispose();

			if (dataLoad.size() >= files.size()) {
				onLoaded.accept(dataLoad, datFiles);
			} else {
				onFail.run();
			}
			if (parent != null) {
				parent.setEnabled(true);
			}
		}).start();
	}



	/**
	 * Save the given list of EntryGroup to the given file. Disable (but not freeze) the calling window until finished.
	 * @param parent	The parent window
	 * @param datFile	The file to load
	 * @param entryGroups	The list of EntryGroup to save
	 */
	public static void saveFile(Window parent, DatFile datFile, List<EntryGroup> entryGroups){
		System.out.println("Save file: " + datFile);
		if (parent != null){
			parent.setEnabled(false);
		}
		new Thread(() -> {
			int count = 0;
			for(EntryGroup entryGroup : entryGroups){
				count += entryGroup.entries.size();
			}
			DialogProgressBar progressBar = new DialogProgressBar("Saving...", count, false);
			new Thread(() -> {
				try {
					DatFileManager dbManager = new DatFileManager(datFile);
					dbManager.save(entryGroups, progressBar::update);
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					progressBar.dispose();
					if (parent != null) {
						parent.setEnabled(true);
					}
				}
			}).start();
		}).start();
	}



	/**
	 * Try to open the given file or show an error message to the calling component.
	 * The file must be already loaded.
	 * @param parent	The parent component.
	 * @param file		The file to open.
	 */
	public static FrameEditor openFile(Component parent, DatFile file){
		System.out.println("Open: " + file.getName());
		List<EntryGroup> entryGroups = DATA.get(file.datStructure);
		if (entryGroups != null){
			FrameEditor frameEditor = FRAME_EDITORS.get(file);
			if (frameEditor == null){
				frameEditor = new FrameEditor(file, entryGroups);
				FRAME_EDITORS.put(file, frameEditor);
			}
			frameEditor.setVisible(true);
			return frameEditor;
		} else {
			JOptionPane.showMessageDialog(parent, "An error occurred during the loading of the file", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	
	/**
	 * Calculate the bounds of the given component
	 * @param component		The component
	 * @param width	The width in pixels. If <= 1, it will be read as fraction of the screen size
	 * @param height	The height in pixels. If <= 1, it will be read as fraction of the screen size
	 * @return
	 */
	public static Rectangle getBounds(Component component, double width, double height){
		GraphicsConfiguration gc = component.getGraphicsConfiguration();
		Rectangle rBounds = gc.getBounds();
		Dimension dimension = new Dimension((int) (width <= 1 ? (rBounds.width*width) : width), (int) (height <= 1 ? (rBounds.height*height) : height));
		Point point = new Point((rBounds.width / 2) - (dimension.width / 2), (rBounds.height / 2) - (dimension.height / 2) - 25);
		return new Rectangle(point, dimension);
	}


	/** No need to instantiate this */
	private Core(){}
	
}
