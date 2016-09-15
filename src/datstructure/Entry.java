package datstructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import datmanager.DatFile;

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

	/** A dummy Entry field used for null/invalid links */
	public static final Entry NULL = new Entry(null, true, -1, -1);
	
	/** A dummy Entry field used for null/invalid links */
	public static final Entry NULL0 = new Entry(null, true, 0, 0);

	static {
		NULL.name = "Null (-1)";
		NULL.sequenceNumber = -1;
		NULL.ID = -1;
		NULL0.name = "Null (0)";
	}
	
	/** Used by fields without name. */
	public static final String NAME_NONE = "<No name>";
	/** Used by undefined fields. */
	public static final String NAME_UNDEFINED = "<Undefined>";
	

	/** Name of the entry. If null, the name is calculated basing on the fields */
	public String name = null;
	/** The structure of this entry. */
	public final DatStructure datStructure;
	/** The values of this entry. They are in the same order as the fiels defined in the structure. */
	public List<Object> values;
	/** The entry's sequence number. This is a redundant value which can be found in values[indexSequence]. */
	public int sequenceNumber;
	/** The entry's ID. This is a redundant value which can be found in values[indexID]. */
	public int ID;
	/** If true, this entry is just for Link purposes and must be ignored anywhere except in Links */
	public boolean dummyEntry = false;
	
	
	
	/**
	 * Create a new entry with the given DatStructure, sequence number, ID and values.
	 * @param datStructure		The dat file structure
	 * @param dummyEntry		If true, this entry is just for Link purposes and must be ignored anywhere except in Links
	 * @param sequenceNumber	The sequence number
	 * @param ID				The ID
	 * @param values			The values for this entry
	 */
	public Entry(DatStructure datStructure, boolean dummyEntry, int sequenceNumber, int ID, List<Object> values){
		this.datStructure = datStructure;
		this.dummyEntry = dummyEntry;
		this.values = values;
		if (values.size() > 0){
			try {
				this.sequenceNumber = datStructure.indexSequence < 0 ? sequenceNumber : (int) values.get(datStructure.indexSequence);
				this.ID = datStructure.indexID < 0 ? ID :(int) values.get(datStructure.indexID);
			} catch (Exception e){
				StringBuilder sb = new StringBuilder("Error with entry: " + sequenceNumber + "/" + ID + " of " + datStructure + '\n');
				values.forEach(x -> sb.append("\tValue: ").append(x).append('\n'));
				System.err.println(sb);
				throw e;
			}
		} else {
			this.ID = ID;
			this.sequenceNumber = sequenceNumber;
		}
	}

	/**
	 * Create a new Entry with the given DatStructure, sequence number and ID. Assign default values to all other fields.
	 * @param datStructure		The dat file structure
	 * @param dummyEntry		If true, this entry is just for Link purposes and must be ignored anywhere except in Links
	 * @param sequenceNumber	The sequence number
	 * @param ID				The ID
	 */
	public Entry(DatStructure datStructure, boolean dummyEntry, int sequenceNumber, int ID){
		this(datStructure, dummyEntry, sequenceNumber, ID, getDefaultValues(datStructure, sequenceNumber, ID));
	}

	/**
	 * Duplicate the current entry and assign the given seqNum and ID to the clone.
	 * @param sequenceNumber	The clone's sequence number
	 * @param ID				The clone's ID
	 * @return					A duplicate of this Entry
	 */
	public Entry duplicate(int sequenceNumber, int ID){
		List<Object> valuesCopy = new ArrayList<>(values);
		if (datStructure.indexSequence >= 0){
			valuesCopy.set(datStructure.indexSequence, sequenceNumber);
		}
		if (datStructure.indexID >= 0){
			valuesCopy.set(datStructure.indexID, ID);
		}
		return new Entry(datStructure, dummyEntry, sequenceNumber, ID, valuesCopy);
	}
	
	/**
	 * Return a list of default values for all fields. Useful when adding a new entry in the file.
	 * @param datStructure	The new entry's structure
	 * @param sequenceNumber	The new entry's sequence number
	 * @param ID	The new entry's ID
	 * @return	A list with all default values for every field defined in the structure.
	 */
	private static List<Object> getDefaultValues(DatStructure datStructure, int sequenceNumber, int ID){
		if (datStructure == null || datStructure.newEntryValues == null){
			return new ArrayList<>(0);
		}
		int n = datStructure.fieldStructs.length;
		List<Object> values = new ArrayList<>(n);
		for (int i = 0; i < n; i++){
			values.add(datStructure.newEntryValues[i]);
		}
		if (datStructure.indexSequence >= 0){
			values.set(datStructure.indexSequence, sequenceNumber);
		}
		if (datStructure.indexID >= 0){
			values.set(datStructure.indexID, ID);
		}
		return values;
	}
	
	/**
	 * Get a printable name of the entry.
	 * @return	A printable name for the entry
	 */
	public String getName(){
		if (name != null){
			return name;
		} else if (isDefined()){
			if (datStructure.nameBuilder != null){
				return datStructure.nameBuilder.apply(this);
			}
			int indexName = datStructure.indexName;
			if (indexName >= 0) {
				return (String) values.get(indexName);
			}
			return NAME_NONE;
		}
		return NAME_UNDEFINED;
	}
	
	/**
	 * Get a printable name of the entry.
	 * @return	A printable name for the entry
	 */
	public String getTrimmedName(){
		if (name != null){
			return name;
		} else if (isDefined()){
			if (datStructure.nameBuilder != null){
				return datStructure.nameBuilder.apply(this);
			}
			int indexName = datStructure.indexName;
			if (indexName >= 0) {
				return ((String) values.get(indexName)).trim();
			}
			return NAME_NONE;
		}
		return NAME_UNDEFINED;
	}

	/**
	 * Check if this entry is a valid target you can jump in the GUI
	 * @return	true if you can jump here, false otherwise
	 */
	public boolean isValidLinkTarget(){
		return !dummyEntry && isDefined();
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
		return datStructure != null
				&& ID >= datStructure.minID
				&& sequenceNumber >= datStructure.minSeq;
	}

	@Override
	public String toString(){
		if (name != null){
			return name;
		} else if (isDefined()){
			if (datStructure.nameBuilder != null){
				return "(" + ID + ") " + datStructure.nameBuilder.apply(this);
			}
			int indexName = datStructure.indexName;
			if (indexName >= 0){
				return "(" + ID + ") " + ((String) values.get(indexName)).trim();
			}
			return NAME_NONE;
		}
		return NAME_UNDEFINED;
	}
	
	@Override
	public int compareTo (Entry o) {
		if (o == null || o.datStructure == null){
			return -1;
		} else if (datStructure == null){
			return 1;
		}
		switch(datStructure.compareTo(o.datStructure)){
			case -1: return -1;
			case 1: return 1;
			default: return Integer.compare(ID, o.ID);
		}
	}

	@Override
	public Iterator <Object> iterator () {
		return values.iterator();
	}

	/**
	 * Find and return all links to this entry
	 * @param ordered	If true, order the list
	 * @return	All links to this entry
	 */
	public List<Link> getLinksToEntry(boolean ordered){
		Collection<Link> linksToEntrySet = new HashSet<>();
		DatFile.LOADED.forEach(datFile -> {
			FieldStruct[] fieldStructs = datFile.datStructure.fieldStructs;
			Link link;
			int n = fieldStructs.length;
			for (int i = 0; i < n; i++){
				if (fieldStructs[i].linkToStruct == datStructure) {
					for (EntryGroup entryGroup : datFile) {
						for (Entry entry : entryGroup) {
							link = (Link) entry.values.get(i);
							if (link.target == this){
								linksToEntrySet.add(link);
							}
						}
					}
				}
			}
			FieldStruct extraField = datFile.datStructure.extraField;
			if (extraField != null && extraField.linkToStruct == datStructure) {
				int indexExtra = datFile.datStructure.getIndexExtraFields();
				int n2;
				for (EntryGroup entryGroup : datFile) {
					for (Entry entry : entryGroup) {
						n2 = n + (Integer) entry.values.get(indexExtra);
						for (int i = indexExtra+1; i < n2; i++){
							link = (Link) entry.values.get(i);
							if (link.target == this){
								linksToEntrySet.add(link);
							}
						}
					}
				}
			}
		});
		List<Link> linksToEntry = new ArrayList<>(linksToEntrySet);
		if (ordered){
			linksToEntry.sort(null);
		}
		return linksToEntry;
	}

}
