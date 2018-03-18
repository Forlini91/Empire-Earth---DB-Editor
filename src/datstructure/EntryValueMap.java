package datstructure;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

import constants.EnumValue;


/**
 * Container class which hold the data returned by getValueMap();
 *
 * @author MarcoForlini
 */
public class EntryValueMap {

	/** Map each value to the list of entries which use that value */
	public final Map <Object, List <Entry>>	map;
	/** Total number of entries */
	public final int						counter;

	/**
	 * Create a new {@link EntryValueMap}
	 *
	 * @param map Map each value to the list of entries which use that value
	 * @param counter Total number of entries
	 */
	public EntryValueMap (Map <Object, List <Entry>> map, int counter) {
		this.map = map;
		this.counter = counter;
	}


	/**
	 * Scan all entries and group entries by value
	 *
	 * @param entryGroups The list of entry groups
	 * @param indexes Indexes of the fields to read
	 * @return an EntryValueMap A new EntryValueMap which hold the results
	 */
	public static EntryValueMap getValuesMap (List <EntryGroup> entryGroups, int... indexes) {
		Map <Object, List <Entry>> valueEntryMap = new HashMap <> ();

		int counter = 0;
		for (int index : indexes) {
			counter += ScanEntries (entryGroups, index, valueEntryMap);
		}

		return new EntryValueMap (new TreeMap <> (valueEntryMap), counter);
	}


	private static int ScanEntries (List <EntryGroup> entryGroups, int index, Map <Object, List <Entry>> valueEntryMap) {
		DatStructure datStructure = entryGroups.get (0).datStructure;
		FieldStruct fieldStruct = datStructure.getFieldStruct (index);

		int counter = 0;
		List <Entry> entries;
		Object key;
		EnumValue enum0 = fieldStruct.enumValues != null ? fieldStruct.enumValues[0] : null;
		for (EntryGroup entryGroup : entryGroups) {
			for (Entry entry : entryGroup) {
				counter++;
				if (index < entry.size ()) {
					key = entry.get (index);
					if (key instanceof Link) {
						key = ((Link) key).target;
					} else if (key instanceof Integer) {
						if (enum0 != null) {
							int intVal = (Integer) key;
							key = enum0.parseValue (intVal);
							if (key == null) {
								continue;
							}
						}
					}

					if (!valueEntryMap.containsKey (key)) {
						entries = new ArrayList <> ();
						entries.add (entry);
						valueEntryMap.put (key, entries);
					} else {
						valueEntryMap.get (key).add (entry);
					}
				}
			}
		}
		return counter;
	}


	public EntryValueMap applyFilter (Predicate <Entry> filter) {
		HashMap <Object, List <Entry>> newMap = new HashMap <> ();
		for (Map.Entry <Object, List <Entry>> group : map.entrySet ()) {
			Object key = group.getKey ();
			for (Entry entry : group.getValue ()) {
				if (filter.test (entry)) {
					if (!newMap.containsKey (key)) {
						List <Entry> list = new ArrayList <> ();
						list.add (entry);
						newMap.put (key, list);
					} else {
						newMap.get (key).add (entry);
					}
				}
			}
		}
		return new EntryValueMap (newMap, counter);
	}

}
