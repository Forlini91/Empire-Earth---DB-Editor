package constants;


/**
 * Area type codes used in an Area entry.
 * They define the effect applied to objects within the area.
 * @author MarcoForlini
 */
public enum AreaEffectType implements EnumValue {
	
	/** No effect in this area */
	NONE ("None", 0),
	/** Objects recover HP */
	C1_HEALING ("Healing", 1),
	/** Objects receive a fixed morale */
	C2_FIXED_MORALE ("Fixed morale", 2),
	/** Can't cast calamities in this area, and existing calamities will deal no damage */
	C3_ANTI_CALAMITY ("Anti calamity", 3),
	/** Can't convert an object in this area */
	C4_ANTI_CONVERSION ("Anti conversion", 4),
	/** Objects receive a dynamic morale (dependent on houses) */
	C5_DYNAMIC_MORALE ("Dynamic morale", 5),
	/** Objects receive a fixed morale from a hero */
	C6_HERO_MORALE ("Hero morale", 6),
	/** Gatherers receive an economy bonus on the resources they bring to a gather point */
	C7_ECON_BONUS ("Economic bonus", 7),
	/** Objects are cloaked */
	C8_CLOAK ("Cloaking", 8)
	;

	
	/** Name to be shown in the UI */
	public final String name;

	/** Code used in the dat files */
	public final int code;

	AreaEffectType(String name, int code){
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
		return code >= 0 && code <= 8;
	}
	
	/**
	 * Parse the code and return the relative enum.
	 * @param code	The code
	 * @return		The relative enum
	 */
	public static AreaEffectType parseValue(int code){
		switch (code){
			case 0: return NONE;
			case 1: return C1_HEALING;
			case 2: return C2_FIXED_MORALE;
			case 3: return C3_ANTI_CALAMITY;
			case 4: return C4_ANTI_CONVERSION;
			case 5: return C5_DYNAMIC_MORALE;
			case 6: return C6_HERO_MORALE;
			case 7: return C7_ECON_BONUS;
			case 8: return C8_CLOAK;
			default: return null;
		}
	}

	@Override
	public String toString(){
		return buildUIName();
	}

}
