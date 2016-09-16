package datmanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.RoundingMode;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;
import datstructure.Link;
import gui.DialogProgressBar;
import gui.EESplashScreen;
import gui.FrameEditor;
import gui.FrameMain;
import gui.GUI;
import gui.components.JScrollPaneRed;

/**
 * Core class. Contains the method main, the main data loaded by the program and some useful methods
 * @author MarcoForlini
 *
 */
public class Core {
	
	/** The editor version/revision */
	public static final float VERSION = 1.5f;

	/** Max time (milliseconds) it will wait for loading to complete. If time exceed this value, the load is considered failed. */
	private static final int LOAD_MAX_WAIT = 15000;

	private static final String[] loadErrorChoices = {"Close", "Get \"Wofies Multidecompressor\" from Empire Earth Heaven", "Show stack trace"};
	private static final String[] exceptionChoices = {"Close", "Show stack trace"};

	/** If false, disable the link system */
	public static boolean LINK_SYSTEM = true;
	
	/** If true, the editor is in AOC mode */
	public static Boolean AOC = new EESplashScreen().askEditorType();
	static {
		if (AOC == null){
			System.exit(0);
		}
	}

	@SuppressWarnings ("javadoc")
	public static final NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
	static {
		numberFormat.setMaximumFractionDigits(6);
		numberFormat.setGroupingUsed(false);
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
	}
	

	
	@SuppressWarnings ({ "javadoc" })
	public static void main (String[] args) {
		new Thread(Language.LIST::size).start();  //This makes the Language class initialize... SSSHHH!!!
		new Thread(DatStructure::init).start();
		FrameMain.instance.setVisible(true);
	}
	
	

	/**
	 * Load the given file and disable (but not freeze) the calling window until finished.
	 * @param parent	The parent window
	 * @param datFile	The datFile to load
	 * @param onLoaded	The code to run if loading succeed
	 * @param onFail	The code to run if loading fail
	 */
	public static void loadFile(Window parent, DatFile datFile, Consumer<DatFile> onLoaded, Runnable onFail){
		loadFiles(parent, new ArrayList<>(Arrays.asList(datFile)), (data) -> onLoaded.accept(datFile), onFail);
	}
	
	/**
	 * Load the given list of files and disable (but not freeze) the calling window until finished.
	 * @param parent	The parent window
	 * @param files		The files to load
	 * @param onLoaded	The code to run if loading succeed
	 * @param onFail	The code to run if loading fails
	 */
	public static void loadFiles(Component parent, List<DatFile> files, Consumer<Collection<DatFile>> onLoaded, Runnable onFail){
		if (parent != null){
			parent.setEnabled(false);
		}
		AtomicBoolean ab = new AtomicBoolean(true);
		new Thread(() -> {
			Object lockObj = new Object();
			DialogProgressBar progressDialog = new DialogProgressBar("Loading...", files.size(), true);
			Collection <DatFile> dataLoad = new HashSet<>();
			List<DatFile> errors = new ArrayList<>();
			for (int i = 0; i < files.size(); i++){
				int index = i;
				Thread t = new Thread(() -> {
					DatFile datFile = files.get(index);
					try {
						DatFileManager dbManager = new DatFileManager(datFile);
						dbManager.read(progressDialog::updatePercPart, index);
						synchronized(lockObj){
							dataLoad.add(datFile);
							if (dataLoad.size() >= files.size() - errors.size()) {
								lockObj.notifyAll();
							}
						}
					} catch (Throwable e) {
						synchronized(lockObj){
							if (ab.get()){
								ab.set(false);
								progressDialog.dispose();
								while (e.getCause() != null){
									e = e.getCause();
								}
								ERROR: do {
									switch (JOptionPane.showOptionDialog(parent, "An error occurred during the loading of " + datFile + ".\nAre you sure this file has been extracted from " + (AOC ? "Art of Conquest's (and not Vanilla game's)" : "Empire Earth's (and not Art of Conquest's)") + " archive Data.ssa?\nAlso, have you decrypted the files with \"Wofies Multidecompressor\"?\nYou know, the files you extract from Data.ssa must also be decrypted (and EE Studio doesn't correctly decrypt them).\nFirst decrypt the files you've extracted, then retry the loading", "Error", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON, loadErrorChoices, loadErrorChoices[0])) {
										case 1:
											try {
												final URI uri = new URI("http://ee.heavengames.com/downloads/showfile.php?fileid=2543");
												if (Desktop.isDesktopSupported()) {
													Desktop.getDesktop().browse(uri);
												} else {
													JOptionPane.showMessageDialog(parent, "Can't open the link", "Error", JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON);
												}
											} catch (Exception e2) {
												JOptionPane.showMessageDialog(parent, "Can't open the link", "Error", JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON);
											}
											continue;
										case 2:
											printException(parent, e);
											continue;
										default:
											break ERROR;
									}
								} while (true);
								errors.add(datFile);
								lockObj.notifyAll();
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
				printException(parent, e, "The thread has been interrupted during the wait. What rudeness!!!", "Not a real error");
				return;
			}
			if (progressDialog.isDisplayable()){
				progressDialog.dispose();
			}
			files.removeAll(errors);
			if (dataLoad.size() >= files.size()) {
				onLoaded.accept(dataLoad);
				buildLinks();
			} else {
				onFail.run();
			}
			if (parent != null) {
				parent.setEnabled(true);
				parent.requestFocus();
			}
		}).start();
	}


	/**
	 * Build the links to the fields
	 */
	public static void buildLinks(){
		FieldStruct[] fieldStructs;
		FieldStruct fieldStruct;
		DatFile datFile;
		Entry targetEntry;
		int indexExtra, n2;
		Object value;
		Entry sourceEntry;
		
		for (DatFile datFileLoaded : DatFile.LOADED) {
			fieldStructs = datFileLoaded.datStructure.fieldStructs;
			int n = fieldStructs.length;
			
			for (int i = 0; i < n; i++){
				fieldStruct = fieldStructs[i];
				if (fieldStruct.linkToStruct != null && fieldStruct.linkToStruct.datFile != null && Core.LINK_SYSTEM) {
					datFile = fieldStruct.linkToStruct.datFile;
					for (EntryGroup entryGroup : datFileLoaded){
						for (int j = 0; j < entryGroup.entries.size(); j++){
							sourceEntry = entryGroup.entries.get(j);
							if (i < sourceEntry.values.size()){
								value = sourceEntry.values.get(i);
								if (value instanceof Integer){
									targetEntry = datFile.findEntry(value);
									if (targetEntry == null){
										targetEntry = new Entry(datFile.datStructure, true, -2, (int) value);
										targetEntry.name = '(' + value.toString() + ") Null/Invalid entry";
										datFile.dummyEntryGroup.add(0, targetEntry);
										datFile.dummyEntryMap.put((int) value, targetEntry);
										System.out.println("Create dummy entry: " + datFileLoaded.datStructure + " (" + fieldStruct + ") -> " + datFile.datStructure + "  =  " + targetEntry);
									}
									sourceEntry.values.set(i, new Link(sourceEntry, fieldStruct, targetEntry));
								}
							}
						}
					}
				}
			}
			
			fieldStruct = datFileLoaded.datStructure.extraField;
			if (fieldStruct != null && fieldStruct.linkToStruct != null && fieldStruct.linkToStruct.datFile != null && Core.LINK_SYSTEM) {
				indexExtra = datFileLoaded.datStructure.getIndexExtraFields();
				datFile = fieldStruct.linkToStruct.datFile;
				for (EntryGroup entryGroup : datFileLoaded){
					for (int j = 0; j < entryGroup.entries.size(); j++){
						sourceEntry = entryGroup.entries.get(j);
						if (indexExtra < sourceEntry.values.size()){
							n2 = n + (Integer) sourceEntry.values.get(indexExtra);
							for (int i = indexExtra+1; i < n2; i++){
								value = sourceEntry.values.get(i);
								targetEntry = datFile.findEntry(value);
								if (targetEntry == null){
									targetEntry = new Entry(datFile.datStructure, true, -2, (int) value);
									targetEntry.name = '(' + value.toString() + ") Null/Invalid entry";
									datFile.dummyEntryGroup.add(0, targetEntry);
									datFile.dummyEntryMap.put((int) value, targetEntry);
									System.out.println("Create dummy entry: " + datFileLoaded.datStructure + " (" + fieldStruct + ") -> " + datFile.datStructure + "  =  " + targetEntry);
								}
								sourceEntry.values.set(i, new Link(sourceEntry, fieldStruct, targetEntry));
							}
						}
					}
				}
			}
		}
	}



	/**
	 * Save the given list of EntryGroup to the given file. Disable (but not freeze) the calling window until finished.
	 * @param parent	The parent window
	 * @param datFile	The datContent to save
	 */
	public static void saveFile(Window parent, DatFile datFile){
		System.out.println("Save file: " + datFile);
		if (parent != null){
			parent.setEnabled(false);
		}
		new Thread(() -> {
			int count = 0;
			for(EntryGroup entryGroup : datFile.entryGroups){
				count += entryGroup.entries.size();
			}
			DialogProgressBar progressBar = new DialogProgressBar("Saving...", count, false);
			new Thread(() -> {
				try {
					DatFileManager dbManager = new DatFileManager(datFile);
					dbManager.save(progressBar::update);
					datFile.setUnsaved(false);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(parent, "An error occurred during the saving of " + datFile + '\n' + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON);
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
	 * @param parent The calling component
	 * @param datFile The datContent to open
	 * @param newWindow If true, force open in a new window
	 * @return	The editor window associated to the datContent
	 */
	public static FrameEditor openFile(Component parent, DatFile datFile, boolean newWindow){
		try{
			if (datFile != null){
				System.out.println("Open: " + datFile.getName());
				FrameEditor selWindow;
				if (datFile.frameEditors.isEmpty() || newWindow) {
					selWindow = new FrameEditor(datFile);
					datFile.frameEditors.add(selWindow);
				} else {
					selWindow = datFile.frameEditors.get(0);
				}
				selWindow.setVisible(true);
				return selWindow;
			}
			JOptionPane.showMessageDialog(parent, "An error occurred during the loading of the file", "Error", JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON);
			return null;
		} catch (Exception e){
			printException(parent, e, "Error while opening the window for DatFile: " + datFile, "Error");
			throw e;
		}
	}
	
	
	/**
	 * Calculate the bounds of the given component
	 * @param component		The component
	 * @param width			The width in pixels. If &le; 1, it will be read as fraction of the screen size
	 * @param height		The height in pixels. If &le; 1, it will be read as fraction of the screen size
	 * @return	The bounds for the passed component.
	 */
	public static Rectangle getBounds(Component component, double width, double height){
		GraphicsConfiguration gc = component.getGraphicsConfiguration();
		Rectangle rBounds = gc.getBounds();
		Dimension dimension = new Dimension((int) (width <= 1 ? (rBounds.width*width) : width), (int) (height <= 1 ? (rBounds.height*height) : height));
		Point point = new Point((rBounds.width / 2) - (dimension.width / 2), (rBounds.height / 2) - (dimension.height / 2) - 25);
		return new Rectangle(point, dimension);
	}


	/**
	 * Convert a throwable's stack trace to String
	 * @param e		The throwable
	 * @return		Its stack trace
	 */
	public static String buildStackTrace(Throwable e){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		e.printStackTrace(ps);
		return new String(baos.toByteArray(), StandardCharsets.UTF_8);
	}
	
	/**
	 * Show a message about an error and ask the user to see the stack trace of the given exception
	 * @param parent	The parent component
	 * @param e			The exception
	 * @param message	Message to display
	 * @param title		Title of the message
	 */
	public static void printException(Component parent, Throwable e, String message, String title){
		if (JOptionPane.showOptionDialog(parent, message, title, 0, JOptionPane.ERROR_MESSAGE, null, exceptionChoices, exceptionChoices[0]) == 1) {
			printException(parent, e);
		}
	}
	
	/**
	 * Show a message with the stack trace of the given exception
	 * @param parent	The parent component
	 * @param e			The exception
	 */
	public static void printException(Component parent, Throwable e){
		JTextArea area = new JTextArea(buildStackTrace(e));
		area.setForeground(Color.RED);
		JScrollPane scrollPane = new JScrollPaneRed(area);
		scrollPane.setPreferredSize(new Dimension(800, 500));
		JOptionPane.showMessageDialog(null, scrollPane, "Exception: stack trace", JOptionPane.ERROR_MESSAGE);
	}

	
	
	/** No need to instantiate this */
	private Core(){}
	
}
