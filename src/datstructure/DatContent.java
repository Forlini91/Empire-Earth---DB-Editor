package datstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import datmanager.DatFile;

public class DatContent implements Comparable<DatContent>, Iterable<EntryGroup> {
	
	public final DatFile datFile;
	public final DatStructure datStructure;
	public final List<EntryGroup> entryGroups;

	public DatContent (DatFile datFile, List<EntryGroup> entryGroups){
		this.datFile = datFile;
		datStructure = datFile.datStructure;
		this.entryGroups = entryGroups;
	}

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
