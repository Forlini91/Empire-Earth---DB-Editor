package datmanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;
import datstructure.Link;
import gui.DialogProgressBar;
import gui.FrameEditor;
import gui.GUI;
import gui.components.JButtonDat;

/**
 * A DatFile is a File which also hold informations about the structure of the file.
 * @author MarcoForlini
 *
 */
public class DatFile extends File implements Iterable<EntryGroup> {
	
	private static final long serialVersionUID = 4028033199491184179L;
	
	/** All loaded files */
	public static final Collection<DatFile> LOADED = new HashSet<>();

	
	
	/** The structure of the file. */
	public final DatStructure datStructure;

	/** The button of the file */
	public JButtonDat datButton = null;

	/** If true, the file has been loaded */
	private boolean loaded = false;
	
	/** If true, the file must be saved */
	private boolean unsaved = false;

	/** The content of the file */
	public List<EntryGroup> entryGroups = null;
	
	/** List of dummy entries which only exists to maintain all links intact */
	public List<Entry> dummyEntryGroup = new ArrayList<>();
	
	/** Map of dummy entries which only exists to maintain all links intact */
	public Map<Integer, Entry> dummyEntryMap = new HashMap<>();

	/** List of FrameEditor opened for this file */
	public final List<FrameEditor> frameEditors = new ArrayList<>();

	
	
	
	/**
	 * Creates a new <code>DatFile</code> instance by converting the given
	 * pathname string into an abstract pathname.  If the given string is
	 * the empty string, then the result is the empty abstract pathname.
	 *
	 * @param   pathname  A pathname string
	 * @param	datStructure	The structure of the file
	 * @throws  NullPointerException
	 *          If the <code>pathname</code> argument is <code>null</code>
	 */
	public DatFile(String pathname, DatStructure datStructure){
		super(pathname);
		this.datStructure = datStructure;
	}
	
	/**
	 * Create a new DatContent
	 * @param entryGroups	The content of the file
	 */
	public void loadData (List<EntryGroup> entryGroups){
		this.entryGroups = entryGroups;
		datStructure.datFile = this;
		loaded = true;
	}

	/**
	 * Check and return if the file has been loaded
	 * @return true if the file has been loaded
	 */
	public boolean isLoaded(){
		return loaded;
	}


	/**
	 * Search for the entry with the given ID in the file
	 * @param ID	The ID of the entry to search
	 * @return		The Entry with the given ID and its EntryGroup or null if no Entry exists with the given ID.
	 */
	public Entry findEntry(Object ID){
		if (isLoaded()){
			Entry result = dummyEntryMap.get(ID);
			if (result != null) {
				return result;
			}
			if (entryGroups.size() > 1){
				for (EntryGroup entryGroup : entryGroups){
					result = entryGroup.map.get(ID);
					if (result != null) {
						return result;
					}
				}
			} else {
				EntryGroup entryGroup = entryGroups.get(0);
				result = entryGroup.map.get(ID);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}
	
	/**
	 * Search for the EntryGroup containing the given Entry
	 * @param entry		The entry
	 * @return			The group which contains the entry
	 */
	public EntryGroup findGroup (Entry entry){
		if (isLoaded()){
			int ID = entry.getID();
			if (entryGroups.size() > 1){
				return entryGroups
						.parallelStream()
						.filter(x -> x.map.containsKey(ID))
						.findAny()
						.orElseGet(() -> EntryGroup.NULL);
			}
			EntryGroup eg0 = entryGroups.get(0);
			if (eg0.map.containsKey(ID)) {
				return eg0;
			}
		}
		return EntryGroup.NULL;
	}
	

	/**
	 * Build and return the list of entries in this file
	 * @param includeDummy	If true, include the dummy entries
	 * @return	The list of entries in this file
	 */
	public List<Entry> getAllEntries(boolean includeDummy){
		if (isLoaded()){
			int size = includeDummy ? dummyEntryGroup.size() : 0;
			if (entryGroups.size() > 1) {
				size += entryGroups
						.parallelStream()
						.mapToInt(x->x.entries.size())
						.sum();
			} else {
				size += entryGroups.get(0).entries.size();
			}
			List<Entry> allEntries = new ArrayList<>(size);
			if (includeDummy) {
				allEntries.addAll(dummyEntryGroup);
			}
			for (EntryGroup entryGroup : entryGroups){
				allEntries.addAll(entryGroup.entries);
			}
			return allEntries;
		}
		return null;
	}

	@Override
	public int compareTo (File o) {
		if (o instanceof DatFile){
			return datStructure.compareTo(((DatFile) o).datStructure);
		}
		return super.compareTo(o);
	}

	@Override
	public Iterator <EntryGroup> iterator () {
		if (isLoaded()){
			return entryGroups.iterator();
		}
		return null;
	}

	@Override
	public String toString(){
		return "DatContent: " + super.toString();
	}
	
	
	
	/**
	 * Check the file unsaved state
	 * @return the unsaved state
	 */
	public boolean isUnsaved () {
		return unsaved;
	}

	/**
	 * Set/Unset the file unsaved state
	 * @param unsaved the new unsaved state
	 */
	public void setUnsaved (boolean unsaved) {
		this.unsaved = unsaved;
		if (datButton != null){
			datButton.setBorder(unsaved ? BorderFactory.createLineBorder(Color.GREEN.darker(), 4) : datButton.defaultBorder);
		}
	}
	
	
	
	/**
	 * Try to open this file in the editor or show an error message to the calling component.
	 * The file must be already loaded.
	 * @param parent The calling component
	 * @param newWindow If true, force open in a new window
	 * @return	The editor window associated to the datContent
	 */
	public FrameEditor openInEditor(Component parent, boolean newWindow){
		try{
			if (Settings.DEBUG) {
				System.out.println("Open: " + getName());
			}
			FrameEditor selWindow;
			if (frameEditors.isEmpty() || newWindow) {
				selWindow = new FrameEditor(this);
				frameEditors.add(selWindow);
			} else {
				selWindow = frameEditors.get(0);
			}
			selWindow.setVisible(true);
			return selWindow;
		} catch (Exception e){
			Core.printException(parent, e, "Error while opening the window for DatFile: " + this, "Error", true);
			throw e;
		}
	}



	/**
	 * Save the file. Disable (but not freeze) the calling window until finished.
	 * @param parent	The parent window
	 */
	public void saveFile(Window parent){
		if (Settings.DEBUG) {
			System.out.println("Save file: " + this);
		}
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
					DatFileManager dbManager = new DatFileManager(this);
					dbManager.save(progressBar::update);
					setUnsaved(false);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(parent, "An error occurred during the saving of " + this + '\n' + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON);
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
	 * Convert the ID fields into Links objects.
	 */
	public void buildLinks(){
		FieldStruct[] fieldStructs = datStructure.fieldStructs;
		int n = fieldStructs.length, n2, indexExtra;
		FieldStruct fieldStruct;
		DatFile datFile;
		Entry sourceEntry, targetEntry;
		Object value;

		for (int i = 0; i < n; i++){
			fieldStruct = fieldStructs[i];
			if (fieldStruct.linkToStruct != null && fieldStruct.linkToStruct.datFile != null) {
				datFile = fieldStruct.linkToStruct.datFile;
				for (EntryGroup entryGroup : entryGroups){
					for (int j = 0; j < entryGroup.entries.size(); j++){
						sourceEntry = entryGroup.entries.get(j);
						if (i < sourceEntry.size()){
							value = sourceEntry.get(i);
							if (value instanceof Integer){
								targetEntry = datFile.findEntry(value);
								if (targetEntry == null){
									targetEntry = new Entry(datFile.datStructure, true, '(' + value.toString() + ") Null/Invalid entry", -2, (int) value);
									datFile.dummyEntryGroup.add(targetEntry);
									datFile.dummyEntryMap.put((Integer) value, targetEntry);
									if (Settings.DEBUG) {
										System.out.println("Create dummy entry: " + datStructure + " (" + fieldStruct + ") -> " + datFile.datStructure + "  =  " + targetEntry);
									}
								}
								sourceEntry.set(i, new Link(sourceEntry, fieldStruct, targetEntry));
							}
						}
					}
				}
			}
		}

		fieldStruct = datStructure.extraField;
		if (fieldStruct != null && fieldStruct.linkToStruct != null && fieldStruct.linkToStruct.datFile != null) {
			indexExtra = datStructure.indexExtraFields();
			datFile = fieldStruct.linkToStruct.datFile;
			for (EntryGroup entryGroup : entryGroups){
				for (int j = 0; j < entryGroup.entries.size(); j++){
					sourceEntry = entryGroup.entries.get(j);
					if (indexExtra < sourceEntry.size()){
						n2 = n + (Integer)sourceEntry.get(indexExtra);
						for (int i = indexExtra+1; i < n2; i++){
							value = sourceEntry.get(i);
							targetEntry = datFile.findEntry(value);
							if (targetEntry == null){
								targetEntry = new Entry(datFile.datStructure, true, '(' + value.toString() + ") Null/Invalid entry", -2, (int) value);
								datFile.dummyEntryGroup.add(targetEntry);
								datFile.dummyEntryMap.put((Integer) value, targetEntry);
								if (Settings.DEBUG) {
									System.out.println("Create dummy entry: " + datStructure + " (" + fieldStruct + ") -> " + datFile.datStructure + "  =  " + targetEntry);
								}
							}
							sourceEntry.set(i, new Link(sourceEntry, fieldStruct, targetEntry));
						}
					}
				}
			}
		}
	}






	/**
	 * This class contains informations about an entry location
	 * @author MarcoForlini
	 */
	public static class EntryLocation {
		
		/** Dummy EntryLocation used for null/invalid links */
		public static final EntryLocation NULL = new EntryLocation (null, null);


		/** Entry group */
		public final EntryGroup entryGroup;
		
		/** Entry */
		public final Entry entry;
		
		/**
		 * Create a new EntryLocation
		 * @param entryGroup	The entry group
		 * @param entry			The entry
		 */
		public EntryLocation (EntryGroup entryGroup, Entry entry) {
			this.entryGroup = entryGroup;
			this.entry = entry;
		}
	}

}