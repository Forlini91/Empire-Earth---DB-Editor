package datstructure;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import datmanager.DatFile;


/**
 * An Entry is an object in the file.
 * It contains a sequence of values which must be read from the file in a given
 * order (the datStructure object)
 * An entry usually define a Name, a Sequence Number and an ID (sometimes it
 * doesn't define one or more of them).
 * Undefined/Unused fields usually have a negative ID and sequence number.
 * Sometimes there's an undefined field used as "null" element by other objects
 * and must not be altered.
 * @author MarcoForlini
 */
public class Entry implements Comparable <Entry>, Iterable <Object> {

	/** Null target entry */
	public static final Entry nullEntry = new Entry (null, true, "Null", -2, -2);
	/** Used by fields without name. */
	public static final String NAME_NONE = "<No name>";
	/** Used by undefined fields. */
	public static final String NAME_UNDEFINED = "<Undefined>";

	/**
	 * Name of the entry. If null, the name is calculated basing on the fields
	 */
	private final String name;
	/** The structure of this entry. */
	public final DatStructure datStructure;
	/**
	 * The values of this entry. They are in the same order as the fiels defined
	 * in the structure.
	 */
	private final List <Object> values;
	/**
	 * The entry's sequence number. This is a redundant value which can be found
	 * in values[indexSequence].
	 */
	private int sequenceNumber;
	/**
	 * The entry's ID. This is a redundant value which can be found in
	 * values[indexID].
	 */
	private int ID;
	/**
	 * If true, this entry is just for Link purposes and must be ignored
	 * anywhere except in Links
	 */
	public final boolean dummyEntry;

	/**
	 * Create a new entry with the given DatStructure, name, sequence number, ID
	 * and values.
	 * @param datStructure The dat file structure
	 * @param dummyEntry If true, this entry is just for Link purposes and must
	 *            be ignored anywhere except in Links
	 * @param name The name
	 * @param sequenceNumber The sequence number
	 * @param ID The ID
	 * @param values The values for this entry
	 */
	public Entry (DatStructure datStructure, boolean dummyEntry, String name, int sequenceNumber, int ID,
			List <Object> values) {
		this.datStructure = datStructure;
		this.dummyEntry = dummyEntry;
		this.values = values;
		if (values.size () > 0) {
			try {
				if (datStructure.indexName < 0) {
					if (name != null) {
						values.set (datStructure.indexName, name);
					} else if (datStructure.indexName >= 0) {
						name = (String) values.get (datStructure.indexName);
					}
				}
				this.name = name;
				this.sequenceNumber = datStructure.indexSequence < 0 ? sequenceNumber
						: get (datStructure.indexSequence);
				this.ID = datStructure.indexID < 0 ? ID : get (datStructure.indexID);
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder (
						"Error with entry: " + sequenceNumber + "/" + ID + " of " + datStructure + '\n');
				values.forEach (x -> sb.append ("\tValue: ").append (x).append ('\n'));
				System.err.println (sb);
				throw e;
			}
		} else {
			this.name = name;
			this.ID = ID;
			this.sequenceNumber = sequenceNumber;
		}
	}

	/**
	 * Create a new Entry with the given DatStructure, name, sequence number and
	 * ID. Assign default values to all other fields.
	 * @param datStructure The dat file structure
	 * @param dummyEntry If true, this entry is just for Link purposes and must
	 *            be ignored anywhere except in Links
	 * @param name The name
	 * @param sequenceNumber The sequence number
	 * @param ID The ID
	 */
	public Entry (DatStructure datStructure, boolean dummyEntry, String name, int sequenceNumber, int ID) {
		this (datStructure, dummyEntry, name, sequenceNumber, ID, getDefaultValues (datStructure, sequenceNumber, ID));
	}

	/**
	 * Duplicate the current entry and assign the given seqNum and ID to the
	 * clone.
	 * @param sequenceNumber The clone's sequence number
	 * @param ID The clone's ID
	 * @return A duplicate of this Entry
	 */
	public Entry duplicate (int sequenceNumber, int ID) {
		List <Object> valuesCopy = new ArrayList<> (values);
		if (datStructure.indexSequence >= 0) {
			valuesCopy.set (datStructure.indexSequence, sequenceNumber);
		}
		if (datStructure.indexID >= 0) {
			valuesCopy.set (datStructure.indexID, ID);
		}
		return new Entry (datStructure, dummyEntry, null, sequenceNumber, ID, valuesCopy);
	}

	/**
	 * Return a list of default values for all fields. Useful when adding a new
	 * entry in the file.
	 * @param datStructure The new entry's structure
	 * @param sequenceNumber The new entry's sequence number
	 * @param ID The new entry's ID
	 * @return A list with all default values for every field defined in the
	 *         structure.
	 */
	private static List <Object> getDefaultValues (DatStructure datStructure, int sequenceNumber, int ID) {
		if (datStructure == null || datStructure.newEntryValues == null) {
			return new ArrayList<> (0);
		}
		List <Object> values = new ArrayList<> (Arrays.asList (datStructure.newEntryValues));
		if (datStructure.indexSequence >= 0) {
			values.set (datStructure.indexSequence, sequenceNumber);
		}
		if (datStructure.indexID >= 0) {
			values.set (datStructure.indexID, ID);
		}
		return values;
	}

	/**
	 * Get a printable name of the entry.
	 * @return A printable name for the entry
	 */
	public String getName () {
		if (name != null) {
			return name;
		} else if (isDefined ()) {
			if (datStructure.nameBuilder != null) {
				return datStructure.nameBuilder.apply (this);
			}
			int indexName = datStructure.indexName;
			if (indexName >= 0) {
				return (String) values.get (indexName);
			}
			return NAME_NONE;
		}
		return NAME_UNDEFINED;
	}

	/**
	 * Get a printable name of the entry.
	 * @return A printable name for the entry
	 */
	public String getTrimmedName () {
		if (name != null) {
			return name;
		} else if (isDefined ()) {
			if (datStructure.nameBuilder != null) {
				return datStructure.nameBuilder.apply (this);
			}
			int indexName = datStructure.indexName;
			if (indexName >= 0) {
				return ((String) values.get (indexName)).trim ();
			}
			return NAME_NONE;
		}
		return NAME_UNDEFINED;
	}

	/**
	 * Check if this entry is a valid target you can jump in the GUI
	 * @return true if you can jump here, false otherwise
	 */
	public boolean isValidLinkTarget () {
		return !dummyEntry && isDefined ();
	}

	/**
	 * Gets the values of this entry
	 * @return the values of this entry
	 */
	public List <Object> getValues () {
		return values;
	}

	/**
	 * Get the entry's ID.
	 * @return the entry's ID
	 */
	public int getID () {
		if (datStructure.indexID >= 0 && datStructure.indexID < values.size ()) {
			return (Integer) values.get (datStructure.indexID);
		}
		return ID;
	}

	/**
	 * Sets the entry's ID
	 * @param ID The new ID
	 */
	public void setID (int ID) {
		if (datStructure.indexID >= 0 && datStructure.indexID < values.size ()) {
			values.set (datStructure.indexID, ID);
		}
	}

	/**
	 * Get the entry's sequence number.
	 * @return the entry's sequence number
	 */
	public int getSequenceNumber () {
		if (datStructure.indexSequence >= 0 && datStructure.indexSequence < values.size ()) {
			return (Integer) values.get (datStructure.indexSequence);
		}
		return sequenceNumber;
	}

	/**
	 * Set the entry's sequence number.
	 * @param sequenceNumber The new sequence number
	 */
	public void setSequenceNumber (int sequenceNumber) {
		if (datStructure.indexSequence >= 0 && datStructure.indexSequence < values.size ()) {
			values.set (datStructure.indexSequence, sequenceNumber);
		}
	}

	/**
	 * Check and return if the entry is defined and usable.
	 * @return true if defined, false otherwise
	 */
	public boolean isDefined () {
		return datStructure != null && getID () >= datStructure.minID && getSequenceNumber () >= datStructure.minSeq;
	}

	@Override
	public String toString () {
		if (name != null) {
			return name;
		} else if (isDefined ()) {
			if (datStructure.nameBuilder != null) {
				return "(" + ID + ") " + datStructure.nameBuilder.apply (this);
			}
			if (datStructure.indexName >= 0 && datStructure.indexName < values.size ()) {
				return "(" + ID + ") " + ((String) values.get (datStructure.indexName)).trim ();
			}
			return NAME_NONE;
		}
		return NAME_UNDEFINED;
	}

	@Override
	public int compareTo (Entry o) {
		if (o == null || o.datStructure == null) {
			return -1;
		} else if (datStructure == null) {
			return 1;
		}
		switch (datStructure.compareTo (o.datStructure)) {
			case -1:
				return -1;
			case 1:
				return 1;
			default:
				return Integer.compare (ID, o.ID);
		}
	}

	@Override
	public Iterator <Object> iterator () {
		return values.iterator ();
	}

	/**
	 * Find and return all links to this entry
	 * @param ordered If true, order the list
	 * @return All links to this entry
	 */
	public List <Link> getLinksToEntry (boolean ordered) {
		Collection <Link> linksToEntrySet = new HashSet<> ();
		DatFile.LOADED.forEach (datFile -> {
			FieldStruct[] fieldStructs = datFile.datStructure.fieldStructs;
			Link link;
			int n = fieldStructs.length;
			for (int i = 0; i < n; i++) {
				if (fieldStructs[i].linkToStruct == datStructure) {
					for (EntryGroup entryGroup : datFile) {
						for (Entry entry : entryGroup) {
							link = entry.get (i);
							if (link.target == this) {
								linksToEntrySet.add (link);
							}
						}
					}
				}
			}
			FieldStruct extraField = datFile.datStructure.extraField;
			if (extraField != null && extraField.linkToStruct == datStructure) {
				int indexExtra = datFile.datStructure.indexExtraFields ();
				int n2;
				for (EntryGroup entryGroup : datFile) {
					for (Entry entry : entryGroup) {
						n2 = n + (Integer) entry.get (indexExtra);
						for (int i = indexExtra + 1; i < n2; i++) {
							link = entry.get (i);
							if (link.target == this) {
								linksToEntrySet.add (link);
							}
						}
					}
				}
			}
		});
		List <Link> linksToEntry = new ArrayList<> (linksToEntrySet);
		if (ordered) {
			linksToEntry.sort (null);
		}
		return linksToEntry;
	}

	/**
	 * Gets the number of values in this entry
	 * @return the number of values
	 */
	public int size () {
		return values.size ();
	}

	/**
	 * Sets the value at the given index
	 * @param index The index
	 * @param value The value to set
	 */
	public void set (int index, Object value) {
		values.set (index, value);
	}

	/**
	 * Adds the value to the entry, in the given index
	 * @param index The index
	 * @param value The value to add
	 */
	public void add (int index, Object value) {
		values.add (index, value);
	}

	/**
	 * Adds the value to the entry
	 * @param value The value to add
	 */
	public void add (Object value) {
		values.add (value);
	}

	/**
	 * Remove the value to the entry, at the given index
	 * @param index The index
	 */
	public void remove (int index) {
		values.remove (index);
	}

	/**
	 * Gets the value at the given index
	 * @param <T> Type of object returned
	 * @param index The index
	 * @return The value in the index
	 * @throws IndexOutOfBoundsException If there's no value with the given
	 *             index
	 * @throws ClassCastException If the value type is different than expected
	 */
	@SuppressWarnings ("unchecked")
	public <T> T get (int index) throws IndexOutOfBoundsException, ClassCastException {
		return (T) values.get (index);
	}

	/**
	 * Returns a sequential {@code Stream} with this collection as its source.
	 * <p>
	 * This method should be overridden when the {@link #spliterator()}
	 * method cannot return a spliterator that is {@code IMMUTABLE},
	 * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
	 * for details.)
	 * @implSpec
	 * 			The default implementation creates a sequential {@code Stream}
	 *           from the
	 *           collection's {@code Spliterator}.
	 * @return a sequential {@code Stream} over the elements in this collection
	 * @since 1.8
	 */
	public Stream <Object> stream () {
		return values.stream ();
	}

	/**
	 * Returns a possibly parallel {@code Stream} with this collection as its
	 * source. It is allowable for this method to return a sequential stream.
	 * <p>
	 * This method should be overridden when the {@link #spliterator()}
	 * method cannot return a spliterator that is {@code IMMUTABLE},
	 * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
	 * for details.)
	 * @implSpec
	 * 			The default implementation creates a parallel {@code Stream}
	 *           from the
	 *           collection's {@code Spliterator}.
	 * @return a possibly parallel {@code Stream} over the elements in this
	 *         collection
	 * @since 1.8
	 */
	public Stream <Object> parallelStream () {
		return values.parallelStream ();
	}

}
