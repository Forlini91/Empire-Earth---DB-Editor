package datstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An Entry is an object in the file.
 * It contains a sequence of values which must be read from the file in a given order (the datStructure object)
 * An entry usually define a Name, a Sequence Number and an ID (sometimes it doesn't define one or more of them).
 * Undefined/Unused fields usually have a negative ID and sequence number.
 * Sometimes there's an undefined field used as "null" element by other objects and must not be altered.
 * @author MarcoForlini
 *
 */
public class Entry implements Comparable<Entry>, Iterable <Object> {
	
	/** Byte used for empty values/string */
	public static final char b00 = 0x00;
	
	/** Byte used for empty strings */
	public static final char bCC = 65484;
	
	/** Sequence of chars used by empty strings (100 chars) */
	public static final String STRING_UNDEFINED = new String(new char[]{
			b00, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
	});
	
	/** Used by fields without name. */
	public static final String NAME_NONE = "<No name>";
	/** Used by undefined fields. */
	public static final String NAME_UNDEFINED = "<Undefined>";

	
	/** The structure of this entry. */
	public final DatStructure datStructure;
	/** The values of this entry. They are in the same order as the fiels defined in the structure. */
	public List<Object> values;
	/** The entry's sequence number. This is a redundant value which can be found in values[indexSequence]. */
	public int sequenceNumber;
	/** The entry's ID. This is a redundant value which can be found in values[indexID]. */
	public int ID;

	public Entry(DatStructure datStructure, List<Object> values){
		this.datStructure = datStructure;
		this.values = values;
		if (values.size() > 0){
			sequenceNumber = datStructure.getIndexSequence() < 0 ? 0 : (int) values.get(datStructure.getIndexSequence());
			ID = datStructure.getIndexID() < 0 ? 0 :(int) values.get(datStructure.getIndexID());
		}
	}

	public Entry(DatStructure datStructure, int sequenceNumber, int ID){
		this(datStructure, getDefaultValues(datStructure, sequenceNumber, ID));
	}
	
	/**
	 * Return a list of default values for all fields. Useful when adding a new entry in the file.
	 * @param datStructure	The new entry's structure
	 * @param sequenceNumber	The new entry's sequence number
	 * @param ID	The new entry's ID
	 * @return	A list with all default values for every field defined in the structure.
	 */
	private static List<Object> getDefaultValues(DatStructure datStructure, int sequenceNumber, int ID){
		int n = datStructure.getEntries().length;
		List<Object> values = new ArrayList<Object>(n);
		for (int i = 0; i < n; i++){
			values.add(datStructure.getDefaultValues()[i]);
		}
		return values;
	}

	/**
	 * Get a printable name of the entry.
	 * @return	A printable name for the entry
	 */
	public String getName(){
		if (datStructure.getIndexName() < 0){
			return NAME_NONE;
		} else if (isDefined()){
			return ((String) values.get(datStructure.getIndexName())).trim();
		} else {
			return NAME_UNDEFINED;
		}
	}
	
	/**
	 * Get the entry's ID.
	 * @return the entry's ID
	 */
	public int getID () {
		return ID;
	}

	/**
	 * Get the entry's sequence number.
	 * @return the entry's sequence number
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Check and return if the entry is defined and usable.
	 * @return	true if defined, false otherwise
	 */
	public boolean isDefined(){
		if (ID >= 0 && sequenceNumber >= 0){
			if (datStructure.getIndexName() >= 0){
				String name = (String) values.get(datStructure.getIndexName());
				return !STRING_UNDEFINED.equals(name);
			} else {
				return true;
			}
		}
		return false;
		
	}
	
	@Override
	public String toString(){
		if (isDefined()){
			return "(" + ID + ") " + getName();
		} else {
			return NAME_UNDEFINED;
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
