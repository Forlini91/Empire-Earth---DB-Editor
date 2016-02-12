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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import datstructure.DatContent;
import datstructure.DatStructure;
import datstructure.DatStructureAOC;
import datstructure.DatStructureVanilla;
import datstructure.EntryGroup;
import gui.DialogProgressBar;
import gui.FrameEditor;
import gui.FrameMain;

/**
 * Core class. Contains the method main, the main data loaded by the program and some useful methods
 * @author MarcoForlini
 *
 */
public class Core {
	

	public static final float VERSION = 1.43f;


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
	/** The DatStructure which will be used */
	public static DatStructure[] values;

	public static boolean AOC = false;
	
	public static final Map<DatStructure, DatContent> DATA = new HashMap<>();
	public static final Map<DatContent, List<FrameEditor>> FRAME_EDITORS = new HashMap<>();
	public static Map<Integer, LanguageEntry> LANGUAGE = new HashMap<>();
	public static Vector<LanguageEntry> languageVector;
	
	
	public static void main (String[] args) {
		EventQueue.invokeLater(() -> {
			String[] buttons = new String[]{"Vanilla", "Art of Conquest"};
			int choice = JOptionPane.showOptionDialog(null, "Vanilla or AOC?", "Empire Earth - DB Editor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
			switch (choice){
				case 0:
					AOC = false;
					values = DatStructureVanilla.values();
					new FrameMain();
					break;
				case 1:
					AOC = true;
					values = DatStructureAOC.values();
					new FrameMain();
					break;
				default:
					System.exit(0);
			}
			
			new Thread(() -> {
				try {
					InputStream str = Core.class.getResourceAsStream(AOC ? "Language AOC.txt" : "Language Vanilla.txt");
					try(Scanner br = new Scanner(str)){
						LanguageEntry languageEntry;
						while (br.hasNextLine()){
							languageEntry = new LanguageEntry(br.nextLine());
							LANGUAGE.put(languageEntry.code, languageEntry);
						}
					}
					languageVector = new Vector<>(LANGUAGE.values());
					languageVector.sort(null);
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, "An error occurred while reading the language file:\n" + e.getMessage() + '\n' + e.getCause(), "Language file", JOptionPane.WARNING_MESSAGE);
				}
			}).start();
		});
	}
	
	

	/**
	 * Load the given file and disable (but not freeze) the calling window until finished.
	 * @param parent	The parent window
	 * @param file		The file to load
	 * @param onLoaded	The code to run if loading succeed
	 * @param onFail	The code to run if loading fail
	 */
	public static void loadFile(Window parent, DatFile datFile, Consumer<DatContent> onLoaded, Runnable onFail){
		loadFiles(parent, new ArrayList<>(Arrays.asList(datFile)), (data) -> {
			onLoaded.accept(data.get(datFile.datStructure));
		}, onFail);
	}
	
	/**
	 * Load the given list of files and disable (but not freeze) the calling window until finished.
	 * @param parent	The parent window
	 * @param files		The files to load
	 * @param onLoaded	The code to run if loading succeed
	 * @param onFail	The code to run if loading fails
	 */
	public static void loadFiles(Window parent, List<DatFile> files, Consumer<Map<DatStructure, DatContent>> onLoaded, Runnable onFail){
		if (parent != null){
			parent.setEnabled(false);
		}
		new Thread(() -> {
			Object lockObj = new Object();
			DialogProgressBar progressDialog = new DialogProgressBar("Loading...", files.size(), true);
			Map <DatStructure, DatContent> dataLoad = new HashMap<>();
			for (int i = 0; i < files.size(); i++){
				int index = i;
				Thread t = new Thread(() -> {
					DatFile datFile = files.get(index);
					try {
						DatFileManager dbManager = new DatFileManager(datFile, datFile.datStructure);
						DatContent datContent = dbManager.read(progressDialog::updatePercPart, index);
						dataLoad.put(datFile.datStructure, datContent);
					} catch (Exception e) {
						synchronized(lockObj){
							JOptionPane.showMessageDialog(parent, "An error occurred during the loading of " + datFile, "Error", JOptionPane.ERROR_MESSAGE);
							files.remove(datFile);
							e.printStackTrace();
						}
					}
					synchronized(lockObj){
						if (dataLoad.size() >= files.size()) {
							lockObj.notifyAll();
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
				onLoaded.accept(dataLoad);
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
	public static void saveFile(Window parent, DatContent datContent){
		System.out.println("Save file: " + datContent.datFile);
		if (parent != null){
			parent.setEnabled(false);
		}
		new Thread(() -> {
			int count = 0;
			for(EntryGroup entryGroup : datContent.entryGroups){
				count += entryGroup.entries.size();
			}
			DialogProgressBar progressBar = new DialogProgressBar("Saving...", count, false);
			new Thread(() -> {
				try {
					DatFileManager dbManager = new DatFileManager(datContent.datFile, datContent.datStructure);
					dbManager.save(datContent, progressBar::update);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(parent, "An error occurred during the saving of " + datContent.datFile + '\n' + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
	 */
	public static FrameEditor openFile(Component parent, DatContent datContent, boolean newWindow){
		try{
			if (datContent != null){
				System.out.println("Open: " + datContent.datFile.getName());
				List<FrameEditor> allWindows = FRAME_EDITORS.get(datContent);
				if (allWindows == null){
					allWindows = new ArrayList<>();
					FRAME_EDITORS.put(datContent, allWindows);
				}
				FrameEditor selWindow;
				if (allWindows.isEmpty() || newWindow) {
					selWindow = new FrameEditor(datContent);
					allWindows.add(selWindow);
				} else {
					selWindow = allWindows.get(0);
				}
				selWindow.setVisible(true);
				return selWindow;
			} else {
				JOptionPane.showMessageDialog(parent, "An error occurred during the loading of the file", "Error", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(parent, e.getMessage() + '\n' + (e.getCause() != null ? e.getCause() : ""), "ERROR", JOptionPane.ERROR_MESSAGE);
			throw e;
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
