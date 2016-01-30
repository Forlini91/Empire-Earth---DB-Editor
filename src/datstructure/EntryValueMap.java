package datstructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Container class which hold the data returned by getValueMap();
 * @author MarcoForlini
 */
public class EntryValueMap{
	public final Map<Object, List<Entry>> map;
	public final Map<Object, List<Entry>> mapClean;
	public final int counter;
	
	public EntryValueMap (Map <Object, List<Entry>> map, Map <Object, List<Entry>> mapClean, int counter) {
		this.map = map;
		this.mapClean = mapClean;
		this.counter = counter;
	}

	/**
	 * Scan all fields and group entries by value
	 * @param index	index of the field to read
	 * @param filterUndefined	if true, create a second map which only contains fully defined fields
	 * @return an EntryValueMap
	 */
	public static EntryValueMap getValuesMap(List<EntryGroup> entryGroups, int index, boolean filterUndefined){
		Map<Object, List<Entry>> valueEntryMap = new HashMap<>();
		Map<Object, List<Entry>> valueEntryMapClean = new HashMap<>();
		List<Entry> entries;
		Object value;
		int counter = 0;
		for (EntryGroup entryGroup : entryGroups){
			for (Entry entry : entryGroup){
				counter++;
				value = entry.values.get(index);
				if (!valueEntryMap.containsKey(value)){
					entries = new ArrayList<>();
					entries.add(entry);
					valueEntryMap.put(value, entries);
				} else {
					valueEntryMap.get(value).add(entry);
				}
				
				if (filterUndefined && entry.ID >= 0 && entry.sequenceNumber >= 0){
					if (!valueEntryMapClean.containsKey(value)){
						entries = new ArrayList<>();
						entries.add(entry);
						valueEntryMapClean.put(value, entries);
					} else {
						valueEntryMapClean.get(value).add(entry);
					}
				}
			}
		}
		return new EntryValueMap (new TreeMap<>(valueEntryMap), new TreeMap<>(valueEntryMapClean), counter);
	}
	
}