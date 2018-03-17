package datstructure;

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
	/** A string value */
	STRING,
	;

}
