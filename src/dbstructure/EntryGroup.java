package dbstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntryGroup implements Cloneable, Iterable <Entry> {
	
	public DatStructure datStructure;
	public List<Entry> entries;
	public String name;

	public EntryGroup (DatStructure datStructure, List<Entry> entries) {
		this.datStructure = datStructure;
		this.entries = entries;
		if (entries.size() > 0){
			name = entries.get(0).toString();
		}
	}
	
	public EntryGroup (DatStructure datStructure, String name){
		this.datStructure = datStructure;
		this.name = name;
		entries = new ArrayList<>();
	}

	@Override
	public String toString(){
		return name;
	}

	@Override
	public EntryGroup clone () {
		return new EntryGroup(datStructure, new ArrayList<>());
	}

	@Override
	public Iterator <Entry> iterator () {
		return entries.iterator();
	}

}
