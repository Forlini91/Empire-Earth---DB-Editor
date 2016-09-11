package constants;

/**
 * Effect codes used in an Effect entry.
 * They defines the action to execute.
 * @author MarcoForlini
 */
@SuppressWarnings ("javadoc")
public enum CalamityType implements EnumValue {
	
	NONE ("None", 0),
	C01_UNKNOWN ("Unknown", 1),
	C02_UNKNOWN ("Unknown", 2),
	C03_AREA_DAMAGE ("Increasing area", 3),
	C04_AREA_DAMAGE ("Fixed area", 4),
	C05_UNKNOWN ("Unknown", 5),
	C06_UNKNOWN ("Unknown", 6),
	C07_UNKNOWN ("Disease", 7),
	C08_UNKNOWN ("Unknown", 8),
	C09_VOLCANO ("Volcano damage", 9),
	C10_FIRE ("Fire damage", 10),
	C11_UNKNOWN ("Unknown", 11),
	C12_UNKNOWN ("Unknown", 12),
	C13_INCREASE_RESISTANCE ("Increase damage resistance", 13),
	C14_TIME_WARP ("Time warp", 14),
	C15_TELEPORT ("Teleport self", 15),
	C16_RESONATOR ("Resonator damage", 16),
	C17_DECREASE_RESISTANCE ("Decrease damage resistance", 17),
	C18_UNKNOWN ("Damage single target", 18),
	C19_MIND_CONTROL ("Mind control", 19),
	C20_TRANSORM_TO_PILUM ("Transform to pilum", 20),
	C21_LOGIC_BOMB ("Freeze building", 21),
	C22_EXPLOSIVE ("Explosive", 22),
	C23_CLOAK ("Cloak", 23),
	C24_METEOR_STORM ("Meteor storm", 24),
	C25_LOS ("LOS", 25),
	C26_PARATROOPERS ("Paratroopers", 26),
	;
	

	
	
	
	/** Name to be shown in the UI */
	public final String name;

	/** Code used in the dat files */
	public final int code;
	

	CalamityType(String effectName, int effectCode){
		name = effectName;
		code = effectCode;
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
	public static CalamityType parseValue(int code){
		for (CalamityType effectCode : values()){
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
