package datmanager;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;

import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import gui.FrameEditor;
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
			int ID = entry.ID;
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
	
	/**
	 * Build and return the list of entries in this file
	 * @param includeDummy	If true, include the dummy entries
	 * @return	The list of entries in this file
	 */
	public Map<Integer, Entry> getAllEntriesMap(boolean includeDummy){
		if (isLoaded()){
			int size = includeDummy ? dummyEntryMap.size() : 0;
			if (entryGroups.size() > 1) {
				size += entryGroups
						.parallelStream()
						.mapToInt(x->x.map.size())
						.sum();
			} else {
				size += entryGroups.get(0).map.size();
			}
			Map<Integer, Entry> allEntries = new HashMap<>(size);
			if (includeDummy){
				allEntries.putAll(dummyEntryMap);
			}
			for (EntryGroup entryGroup : entryGroups){
				allEntries.putAll(entryGroup.map);
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
			datButton.setBorder(unsaved ? BorderFactory.createLineBorder(Color.GREEN.darker(), 4) : null);
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