package datstructure;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Type of the fields.
 *
 * @author MarcoForlini
 */
public enum FieldType {

	/** Either 0 or 1 */
	BOOLEAN,
	/** A restricted set of values */
	ENUM,
	/** A restricted range of integers */
	RANGE,
	/** The language field */
	LANGUAGE,
	/** A link value, which link to an Entry in another file Dat */
	LINK,
	/** An integer value */
	INTEGER,
	/** A float value */
	FLOAT,
	/** A string value with fixed size */
	STRING,
	/** A string value with a dynamic size */
	DYNAMIC_STRING,
	;


	private static final Map<String, FieldType> map = Arrays.stream(values()).collect(Collectors.toMap(value -> value.toString(), value -> value));

	public static FieldType parse(String name) {
		return map.get(name);
	}
}
