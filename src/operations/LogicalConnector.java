package operations;


/**
 * Logical connectors for the conditions
 * @author MarcoForlini
 *
 */
public enum LogicalConnector {

	/** Both conditions must be true */
	AND,
	
	/** Either conditions must be true */
	OR,
	
	/** One condition must be true, one condition must be false */
	XOR,

}
