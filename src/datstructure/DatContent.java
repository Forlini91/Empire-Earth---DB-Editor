package datstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import datmanager.DatFile;


/**
 * This class contains the data of a file.
 * @author MarcoForlini
 *
 */
public class DatContent implements Comparable<DatContent>, Iterable<EntryGroup> {

	/** The file loaded */
	public final DatFile datFile;
	
	/** The structure of the file */
	public final DatStructure datStructure;
	
	/** The content of the file */
	public final List<EntryGroup> entryGroups;
	

	public DatContent (DatFile datFile, List<EntryGroup> entryGroups){
		this.datFile = datFile;
		datStructure = datFile.datStructure;
		this.entryGroups = entryGroups;
	}
	
	/**
	 * Build and return the list of entries in this file
	 * @return	The list of entries in this file
	 */
	public List<Entry> getAllEntries(){
		List<Entry> allEntries = new ArrayList<>();
		for (EntryGroup entryGroup : entryGroups){
			allEntries.addAll(entryGroup.entries);
		}
		return allEntries;
	}
	
	@Override
	public int compareTo (DatContent o) {
		return datStructure.compareTo(o.datStructure);
	}
	
	@Override
	public Iterator <EntryGroup> iterator () {
		return entryGroups.iterator();
	}
	
}
