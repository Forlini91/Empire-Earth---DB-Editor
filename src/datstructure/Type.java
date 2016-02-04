package datstructure;

/**
 * Type of the fields.
 * @author MarcoForlini
 *
 */
public enum Type {
	/** Either 0 or 1 */
	BOOLEAN,
	/** An integer value */
	INTEGER,
	/** A float value */
	FLOAT,
	/** A string value */
	STRING,
	/** An ID value, which link to an Entry in another file Dat */
	ID,
	/** A restricted set of values */
	ENUM
	;

}