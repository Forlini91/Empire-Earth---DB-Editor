package constants;

/**
 * Interface for all constants/enums
 * @author MarcoForlini
 *
 */
public interface EnumValue {

	/**
	 * Gets the name to be shown in the UI
	 * @return	The UI name
	 */
	String getName ();
	
	/**
	 * Gets the code used in the dat files
	 * @return	The dat code
	 */
	int getCode();
	
	/**
	 * Checks if the given code is valid
	 * @param code	The code
	 * @return		true if the code is valid, false otherwise
	 */
	boolean isValid(int code);

	/**
	 * Build a name for the UI
	 * @return The UI name
	 */
	default String buildUIName(){
		return getCode() + ": " + getName();
	}

	/**
	 * Returns the EnumValue with this code, if any, else null
	 * @param code	The code
	 * @return		The EnumValue with this code.
	 */
	EnumValue parseValue (int code);

}
