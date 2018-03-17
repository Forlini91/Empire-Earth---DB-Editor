package operations;

import java.util.Arrays;


/**
 * Operators used in conditions
 *
 * @author MarcoForlini
 */
public enum Operator {

	/** Values are equals */
	EQUAL ("==", true, true, true, true),

	/** Values are different */
	DIFFERENT ("!=", false, true, true, true),

	/** First is greater than second */
	GREATER (">", false, true, true, true),

	/** First is greater than or equal to second */
	GREATER_EQUAL (">=", false, true, true, true),

	/** First is less than second */
	LESS ("<", false, true, true, true),

	/** First is less than or equal to second */
	LESS_EQUAL ("<=", false, true, true, true),

	/** Strings are equals (ignore case) */
	EQUAL_NC ("== (ignore case)", false, false, true, false),

	/** String are different (ignore case) */
	DIFFERENT_NC ("!= (ignore case)", false, false, true, false),

	/** String contains text */
	CONTAINS ("contains", false, false, true, false),

	/** Strings doesn't contain text */
	CONTAINS_NOT ("doesn't contain", false, false, true, false),

	/** String starts with */
	STARTS_WITH ("starts with", false, false, true, false),

	/** String doesn't start with */
	STARTS_WITH_NOT ("doesn't start with", false, false, true, false),

	/** String ends with */
	ENDS_WITH ("ends with", false, false, true, false),

	/** String doesn't end with */
	ENDS_WITH_NOT ("doesn't end with", false, false, true, false);



	public static Operator[]	allOperators	= values ();
	public static Operator[]	boolOperators	= Arrays.stream (allOperators).filter (oper -> oper.supportBoolean).toArray (Operator[]::new);
	public static Operator[]	mathOperators	= Arrays.stream (allOperators).filter (oper -> oper.supportNumber).toArray (Operator[]::new);
	public static Operator[]	strOperators	= Arrays.stream (allOperators).filter (oper -> oper.supportString).toArray (Operator[]::new);
	public static Operator[]	enumOperators	= Arrays.stream (allOperators).filter (oper -> oper.supportEnum).toArray (Operator[]::new);


	private String	name;
	public boolean	supportBoolean;
	public boolean	supportNumber;
	public boolean	supportString;
	public boolean	supportEnum;



	Operator (String name, boolean supportBoolean, boolean supportNumber, boolean supportString, boolean supportEnum) {
		this.name = name;
		this.supportBoolean = supportBoolean;
		this.supportNumber = supportNumber;
		this.supportString = supportString;
		this.supportEnum = supportEnum;
	}

	@Override
	public String toString () {
		return name;
	}

}
