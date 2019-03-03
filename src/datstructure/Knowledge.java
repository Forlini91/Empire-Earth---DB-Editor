package datstructure;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This enum define our knowledge about a field
 *
 * @author MarcoForlini
 */
public enum Knowledge {

	/** We know the field */
	KNOWN,

	/** We only know the field is always 0 in all entries */
	NEVER_USED,

	/** We only know the field is always the same in all entries */
	NEVER_CHANGE,

	/** We don't know the field */
	UNKNOWN,

	;

	private static final Map<String, Knowledge> map = Arrays.stream(values()).collect(Collectors.toMap(value -> value.toString(), value -> value));

	public static Knowledge parse(String name) {
		return map.get(name);
	}

	public static Knowledge parse(String name, Knowledge defaultValue) {
		return map.getOrDefault(name, defaultValue);
	}

}
