package constants;


/**
 * Tech type codes used in a Technology entry.
 * They defines the technology type.
 * @author MarcoForlini
 */
public enum WonderType implements EnumValue {

	/** No power */
	C0_NONE ("None", 0),
	/** Power which allow multiple conversion */
	C1_MULTIPLE_CONVERSIONS ("Multiple conversions", 1),
	/** Power which increases the building health points */
	C2_BUILDING_RESISTANCE ("Building resistance", 2),
	/** Power which reveal a large amount of sea around the building */
	C3_REVEAL_SEA ("Reveal sea", 3),
	/** Power which steal some max population from other players */
	C4_MORE_POPULATION ("More population", 4),
	/** Power which reveal all buildings */
	C5_REVEAL_BUILDINGS ("Reveal buildings", 5),
	/** Power which allow units to regenerate health */
	C6_REGENERATION ("Regeneration", 6),
	;
	
	
	/** Name to be shown in the UI */
	public final String name;

	/** Code used in the dat files */
	public final int code;
	

	WonderType(String name, int code){
		this.name = name;
		this.code = code;
	}

	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public int getCode () {
		return code;
	}

	@Override
	public boolean isValid (int code) {
		return code >= 0 && code <= 9;
	}

	/**
	 * Parse the code and return the relative enum.
	 * @param code	The code
	 * @return		The relative enum
	 */
	public static WonderType parseValue(int code){
		switch (code){
			case 0: return C0_NONE;
			case 1: return C1_MULTIPLE_CONVERSIONS;
			case 2: return C2_BUILDING_RESISTANCE;
			case 3: return C3_REVEAL_SEA;
			case 4: return C4_MORE_POPULATION;
			case 5: return C5_REVEAL_BUILDINGS;
			case 6: return C6_REGENERATION;
			default: return null;
		}
	}
	
	@Override
	public String toString(){
		return buildUIName();
	}

}
