package constants;


/**
 * Area type codes used in an Area entry.
 * They define the effect applied to objects within the area.
 * @author MarcoForlini
 */
public enum AreaEffectType implements EnumValue {

	/** No effect in this area */
	C0_NONE ("None", 0),
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
	
	/**
	 * Parse the code and return the relative enum.
	 * @param code	The code
	 * @return		The relative enum
	 */
	public static AreaEffectType parseValue(int code){
		for (AreaEffectType effectCode : values()){
			if (effectCode.code == code){
				return effectCode;
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		return buildUIName();
	}
	
}
