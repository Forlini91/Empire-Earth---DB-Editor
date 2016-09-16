package operations;

/**
 * Operators used in conditions
 * @author MarcoForlini
 */
public enum Operator {

	/** Values are equals */
	EQUAL ("="),

	/** Values are different */
	DIFFERENT ("!="),

	/** First is greater than second */
	GREATER (">"),

	/** First is greater than or equal to second */
	GREATHER_EQUAL (">="),

	/** First is less than second */
	LESS ("<"),

	/** First is less than or equal to second */
	LESS_EQUAL ("<="),
	;



	private String name;
	Operator (String name){
		this.name = name;
	}

	@Override
	public String toString () {
		return name;
	}

}