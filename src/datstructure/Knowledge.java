package datstructure;

/**
 * This enum define our knowledge about a field
 * @author MarcoForlini
 *
 */
public enum Knowledge {
	
	/** We know the field */
	KNOWN,
	
	/** We only know the field is always 0 in all entries */
	NEVER_USED,
	
	/** We only know the field is always the same in all entries*/
	NEVER_CHANGE,
	
	/** We don't know the field */
	UNKNOWN,

}
