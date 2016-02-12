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
	
	/**
	 * Create a new entry with the given DatStructure, sequence number, ID and values.
	 * @param datStructure		The dat file structure
	 * @param sequenceNumber	The sequence number
	 * @param ID				The ID
	 * @param values			The values for this entry
	 */
	public Entry(DatStructure datStructure, int sequenceNumber, int ID, List<Object> values){
		this.datStructure = datStructure;
		this.values = values;
		if (values.size() > 0){
			try {
				this.sequenceNumber = datStructure.getIndexSequence() < 0 ? sequenceNumber : (int) values.get(datStructure.getIndexSequence());
				this.ID = datStructure.getIndexID() < 0 ? ID :(int) values.get(datStructure.getIndexID());
			} catch (Exception e){
				StringBuilder sb = new StringBuilder("Error with entry: " + sequenceNumber + "/" + ID + " of " + datStructure + '\n');
				values.forEach(x -> sb.append("\tValue: ").append(x).append('\n'));
				System.err.println(sb);
				throw e;
			}
		}
	}

	/**
	 * Create a new Entry with the given DatStructure, sequence number and ID. Assign default values to all other fields.
	 * @param datStructure		The dat file structure
	 * @param sequenceNumber	The sequence number
	 * @param ID				The ID
	 */
	public Entry(DatStructure datStructure, int sequenceNumber, int ID){
		this(datStructure, sequenceNumber, ID, getDefaultValues(datStructure, sequenceNumber, ID));
	}

	/**
	 * Duplicate the current entry and assign the given seqNum and ID to the clone.
	 * @param sequenceNumber	The clone's sequence number
	 * @param ID				The clone's ID
	 * @return					A duplicate of this Entry
	 */
	public Entry duplicate(int sequenceNumber, int ID){
		List<Object> valuesCopy = new ArrayList<>(values);
		if (datStructure.getIndexSequence() >= 0){
			valuesCopy.set(datStructure.getIndexSequence(), sequenceNumber);
		}
		if (datStructure.getIndexID() >= 0){
			valuesCopy.set(datStructure.getIndexID(), ID);
		}
		return new Entry(datStructure, sequenceNumber, ID, valuesCopy);
	}
	
	/**
	 * Return a list of default values for all fields. Useful when adding a new entry in the file.
	 * @param datStructure	The new entry's structure
	 * @param sequenceNumber	The new entry's sequence number
	 * @param ID	The new entry's ID
	 * @return	A list with all default values for every field defined in the structure.
	 */
	private static List<Object> getDefaultValues(DatStructure datStructure, int sequenceNumber, int ID){
		int n = datStructure.getFieldStructs().length;
		List<Object> values = new ArrayList<Object>(n);
		for (int i = 0; i < n; i++){
			values.add(datStructure.getNewEntryValues()[i]);
		}
		if (datStructure.getIndexSequence() >= 0){
			values.set(datStructure.getIndexSequence(), sequenceNumber);
		}
		if (datStructure.getIndexID() >= 0){
			values.set(datStructure.getIndexID(), ID);
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
		return ID >= datStructure.getMinID()
				&& sequenceNumber >= datStructure.getMinSeq();
	}

	@Override
	public String toString(){
		if (isDefined()){
			if (datStructure.getNameBuilder() != null){
				return "(" + ID + ") " + datStructure.getNameBuilder().apply(this);
			} else {
				int indexName = datStructure.getIndexName();
				if (indexName >= 0){
					return "(" + ID + ") " + ((String) values.get(indexName)).trim();
				} else {
					return NAME_NONE;
				}
			}
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
