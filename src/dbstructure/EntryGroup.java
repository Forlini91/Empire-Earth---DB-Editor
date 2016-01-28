package dbstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntryGroup implements Identity, Iterable <Entry> {

	public DatStructure datStructure;
	public List<Entry> entries;
	public final int ID;
	public String name;
	
	public EntryGroup (DatStructure datStructure, List<Entry> entries, int ID) {
		this.datStructure = datStructure;
		this.entries = entries;
		this.ID = ID;
		if (entries.size() > 0){
			name = entries.get(0).toString();
		}
	}

	public EntryGroup (DatStructure datStructure, String name, int ID){
		this.datStructure = datStructure;
		this.name = name;
		this.ID = ID;
		entries = new ArrayList<>();
	}
	
	@Override
	public Iterator <Entry> iterator () {
		return entries.iterator();
	}
	
	@Override
	public int getID () {
		return ID;
	}

	@Override
	public int getSequenceNumber () {
		return ID;
	}

	@Override
	public String toString(){
		return name;
	}
	
}
