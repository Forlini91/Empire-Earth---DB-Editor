package dbstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Entry implements Comparable<Entry>, Cloneable, Iterable <Object> {
	

	public final DatStructure datStructure;
	public List<Object> values;
	
	public int sequenceNumber;
	public int ID;

	public Entry(DatStructure datStructure, List<Object> values){
		this.datStructure = datStructure;
		this.values = values;
		if (values.size() > 0){
			sequenceNumber = datStructure.indexSequence < 0 ? -1 : (int) values.get(datStructure.indexSequence);
			ID = datStructure.indexID < 0 ? -1 :(int) values.get(datStructure.indexID);
		}
	}

	public Entry(DatStructure datStructure, int sequenceNumber, int ID){
		this(datStructure, buildDefaultEntry(datStructure, sequenceNumber, ID));
	}
	
	private static List<Object> buildDefaultEntry(DatStructure datStructure, int sequenceNumber, int ID){
		int n = datStructure.entries.length;
		List<Object> values = new ArrayList<Object>(n);
		for (int i = 0; i < n; i++){
			values.add(datStructure.entries[i].defaultValue);
		}
		values.set(datStructure.indexSequence, sequenceNumber);
		values.set(datStructure.indexID, ID);
		return values;
	}
	
	
	
	@Override
	public String toString(){
		if (datStructure.indexName < 0){
			return "<No name>";
		} else {
			String name = ((String) values.get(datStructure.indexName)).trim();
			if (name.length() < 2 || name.charAt(1) == EntryStruct.NOCHAR){
				return "<Undefined>";
			} else {
				return name;
			}
		}
	}

	@Override
	public Entry clone () {
		return new Entry(datStructure, values);
	}

	@Override
	public int compareTo (Entry o) {
		return Integer.compare(sequenceNumber, o.sequenceNumber);
	}
	
	@Override
	public Iterator <Object> iterator () {
		return values.iterator();
	}

}
