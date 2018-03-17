package operations;

import java.util.Arrays;


/**
 * Operators used in conditions
 *
 * @author MarcoForlini
 */
public enum Operator {

	/** Value is equal to */
	EQUAL ("==", true, true, true, true, true),

	/** Value is different than */
	DIFFERENT ("!=", true, true, true, true, true),

	/** Value is greater than */
	GREATER (">", false, true, true, true, true),

	/** Value is greater than or equal to */
	GREATER_EQUAL (">=", false, true, true, true, true),

	/** Value is less than */
	LESS ("<", false, true, true, true, true),

	/** Value is less than or equal to */
	LESS_EQUAL ("<=", false, true, true, true, true),

	/** Value is equal to (ignore case) */
	EQUAL_NC ("== (ignore case)", false, false, true, false, false),

	/** Value is different than (ignore case) */
	DIFFERENT_NC ("!= (ignore case)", false, false, true, false, false),

	/** Value contains text */
	CONTAINS ("contains", false, false, true, false, false),

	/** Value doesn't contain text */
	CONTAINS_NOT ("doesn't contain", false, false, true, false, false),

	/** Value starts with */
	STARTS_WITH ("starts with", false, false, true, false, false),

	/** Value doesn't start with */
	STARTS_WITH_NOT ("doesn't start with", false, false, true, false, false),

	/** Value ends with */
	ENDS_WITH ("ends with", false, false, true, false, false),

	/** Value doesn't end with */
	ENDS_WITH_NOT ("doesn't end with", false, false, true, false, false);



	public static Operator[]	allOperators	= values ();
	public static Operator[]	boolOperators	= Arrays.stream (allOperators).filter (oper -> oper.supportBoolean).toArray (Operator[]::new);
	public static Operator[]	mathOperators	= Arrays.stream (allOperators).filter (oper -> oper.supportNumber).toArray (Operator[]::new);
	public static Operator[]	strOperators	= Arrays.stream (allOperators).filter (oper -> oper.supportString).toArray (Operator[]::new);
	public static Operator[]	enumOperators	= Arrays.stream (allOperators).filter (oper -> oper.supportEnum).toArray (Operator[]::new);
	public static Operator[]	linkOperators	= Arrays.stream (allOperators).filter (oper -> oper.supportLinks).toArray (Operator[]::new);


	private String	name;
	public boolean	supportBoolean;
	public boolean	supportNumber;
	public boolean	supportString;
	public boolean	supportEnum;
	public boolean	supportLinks;



	Operator (String name, boolean supportBoolean, boolean supportNumber, boolean supportString, boolean supportEnum, boolean supportLinks) {
		this.name = name;
		this.supportBoolean = supportBoolean;
		this.supportNumber = supportNumber;
		this.supportString = supportString;
		this.supportEnum = supportEnum;
		this.supportLinks = supportLinks;
	}

	@Override
	public String toString () {
		return name;
	}

}
