package dbstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Entry implements Identity, Comparable<Entry>, Iterable <Object> {

	
	public final DatStructure datStructure;
	public List<Object> values;

	public int sequenceNumber;
	public int ID;
	
	public Entry(DatStructure datStructure, List<Object> values){
		this.datStructure = datStructure;
		this.values = values;
		if (values.size() > 0){
			sequenceNumber = datStructure.indexSequence < 0 ? 0 : (int) values.get(datStructure.indexSequence);
			ID = datStructure.indexID < 0 ? 0 :(int) values.get(datStructure.indexID);
		}
	}
	
	public Entry(DatStructure datStructure, int sequenceNumber, int ID){
		this(datStructure, buildDefaultEntry(datStructure, sequenceNumber, ID));
	}

	private static List<Object> buildDefaultEntry(DatStructure datStructure, int sequenceNumber, int ID){
		int n = datStructure.entries.length;
		List<Object> values = new ArrayList<Object>(n);
		for (int i = 0; i < n; i++){
			switch(datStructure.entries[i].type){
				case STRING:
					values.add(EntryStruct.STRING_NONAME); break;
				case FLOAT:
					values.add(0f); break;
				default:
					values.add(0);
			}
		}
		if (datStructure.indexSequence >= 0){
			values.set(datStructure.indexSequence, sequenceNumber);
		}
		if (datStructure.indexID >= 0){
			values.set(datStructure.indexID, ID);
		}
		return values;
	}
	
	public String getName(){
		if (datStructure.indexName < 0){
			return "<No name>";
		} else if (isDefined()){
			return ((String) values.get(datStructure.indexName)).trim();
		} else {
			return "<Undefined>";
		}
	}

	@Override
	public int getID () {
		return ID;
	}
	
	@Override
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	public boolean isDefined(){
		return ID >= 0 && sequenceNumber >= 0;
	}

	@Override
	public String toString(){
		if (isDefined()){
			return "(" + ID + ") " + getName();
		} else {
			return "<Undefined>";
		}
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
