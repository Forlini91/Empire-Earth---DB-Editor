package datstructure;

/**
 * Type of the fields.
 * @author MarcoForlini
 *
 */
public enum Type {
	
	/** Either 0 or 1 */
	BOOLEAN,
	/** A restricted set of values */
	ENUM,
	/** A restricted range of integers */
	RANGE,
	/** The language field */
	LANGUAGE,
	/** An ID value, which link to an Entry in another file Dat */
	ID,
	/** An integer value */
	INTEGER,
	/** A float value */
	FLOAT,
	/** A string value */
	STRING,
	;
	
}