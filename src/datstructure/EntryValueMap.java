package datstructure;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import constants.EnumValue;


/**
 * Container class which hold the data returned by getValueMap();
 *
 * @author MarcoForlini
 */
public class EntryValueMap {

	/** Map each value to the list of entries which use that value */
	public final Map<Object, List<Entry>> map;
	/** Total number of entries */
	public final int counter;

	/**
	 * Create a new {@link EntryValueMap}
	 *
	 * @param map     Map each value to the list of entries which use that value
	 * @param counter Total number of entries
	 */
	public EntryValueMap(Map<Object, List<Entry>> map, int counter) {
		this.map = map;
		this.counter = counter;
	}


	private static final class KeyValue<K, V> {
		public final K key;
		public final V value;

		public KeyValue(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}


	/**
	 * Scan all entries and group entries by value
	 *
	 * @param entryGroups The list of entry groups
	 * @param indexes     Indexes of the fields to read
	 * @return an EntryValueMap A new EntryValueMap which hold the results
	 */
	public static EntryValueMap getValuesMap(List<EntryGroup> entryGroups, DatStructure datStructure, int fieldIndex) {
		final Entry[] entries = entryGroups.parallelStream().flatMap(entryGroup -> entryGroup.entries.stream()).toArray(Entry[]::new);
		final FieldStruct fieldStruct = datStructure.getFieldStruct(fieldIndex);
		final EnumValue enum0 = fieldStruct.enumValues != null ? fieldStruct.enumValues[0] : null;

		final var valuesMap = Arrays.stream(entries)
				.map(entry -> {
					if (entry.size() <= fieldIndex) {
						return null;
					}
					Object fieldValue = entry.get(fieldIndex);
					if (fieldValue instanceof Link) {
						fieldValue = ((Link) fieldValue).target;
					} else if (fieldValue instanceof Integer) {
						if (enum0 != null) {
							fieldValue = enum0.parseValue((Integer) fieldValue);
							if (fieldValue == null) {
								return null;
							}
						}
					}
					return new KeyValue<>(fieldValue, entry);
				})
				.filter(obj -> obj != null)
				.collect(Collectors.groupingBy(keyValue -> keyValue.key, Collectors.mapping(keyValue -> keyValue.value, Collectors.toList())));
		valuesMap.values().forEach(list -> list.sort(FieldStruct.valueComparator));
		return new EntryValueMap(valuesMap, entries.length);
	}

	public EntryValueMap applyFilter(Predicate<Entry> filter) {
		final HashMap<Object, List<Entry>> newMap = new HashMap<>(map);
		newMap.entrySet().forEach(entrySet -> {
			final var list = new ArrayList<>(entrySet.getValue());
			list.removeIf(Predicate.not(filter));
			entrySet.setValue(list);
		});
		newMap.entrySet().removeIf(entrySet -> entrySet.getValue().isEmpty());
		return new EntryValueMap(newMap, counter);
	}

}
