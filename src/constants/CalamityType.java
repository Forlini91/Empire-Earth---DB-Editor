package constants;

/**
 * Effect codes used in an Effect entry.
 * They defines the action to execute.
 * 
 * @author MarcoForlini
 */
public enum CalamityType implements EnumValue {

	NONE ("None", 0), C01_UNKNOWN ("Unknown", 1), C02_UNKNOWN ("Unknown", 2), C03_AREA_DAMAGE ("Increasing area", 3), C04_AREA_DAMAGE ("Fixed area", 4), C05_UNKNOWN ("Unknown", 5), C06_UNKNOWN ("Unknown", 6), C07_UNKNOWN ("Disease", 7), C08_UNKNOWN ("Unknown", 8), C09_VOLCANO ("Volcano damage", 9), C10_FIRE ("Fire damage", 10), C11_UNKNOWN ("Unknown", 11), C12_UNKNOWN ("Unknown", 12), C13_INCREASE_RESISTANCE ("Increase damage resistance", 13), C14_TIME_WARP ("Time warp", 14), C15_TELEPORT ("Teleport self", 15), C16_RESONATOR ("Resonator damage",
			16),
	C17_DECREASE_RESISTANCE ("Decrease damage resistance", 17), C18_UNKNOWN ("Damage single target", 18), C19_MIND_CONTROL ("Mind control", 19), C20_TRANSORM_TO_PILUM ("Transform to pilum", 20), C21_LOGIC_BOMB ("Freeze building", 21), C22_EXPLOSIVE ("Explosive", 22), C23_CLOAK ("Cloak", 23), C24_METEOR_STORM ("Meteor storm", 24), C25_LOS ("LOS", 25), C26_PARATROOPERS ("Paratroopers", 26),
	;





	/** Name to be shown in the UI */
	public final String	name;

	/** Code used in the dat files */
	public final int	code;


	CalamityType (String effectName, int effectCode) {
		name = effectName;
		code = effectCode;
	}

	@Override
	public String getName () {
		return name;
	}

	@Override
	public int getCode () {
		return code;
	}


	@Override
	public CalamityType parseValue (int code) {
		switch (code) {
			case 0:
				return NONE;
			case 1:
				return C01_UNKNOWN;
			case 2:
				return C02_UNKNOWN;
			case 3:
				return C03_AREA_DAMAGE;
			case 4:
				return C04_AREA_DAMAGE;
			case 5:
				return C05_UNKNOWN;
			case 6:
				return C06_UNKNOWN;
			case 7:
				return C07_UNKNOWN;
			case 8:
				return C08_UNKNOWN;
			case 9:
				return C09_VOLCANO;
			case 10:
				return C10_FIRE;
			case 11:
				return C11_UNKNOWN;
			case 12:
				return C12_UNKNOWN;
			case 13:
				return C13_INCREASE_RESISTANCE;
			case 14:
				return C14_TIME_WARP;
			case 15:
				return C15_TELEPORT;
			case 16:
				return C16_RESONATOR;
			case 17:
				return C17_DECREASE_RESISTANCE;
			case 18:
				return C18_UNKNOWN;
			case 19:
				return C19_MIND_CONTROL;
			case 20:
				return C20_TRANSORM_TO_PILUM;
			case 21:
				return C21_LOGIC_BOMB;
			case 22:
				return C22_EXPLOSIVE;
			case 23:
				return C23_CLOAK;
			case 24:
				return C24_METEOR_STORM;
			case 25:
				return C25_LOS;
			case 26:
				return C26_PARATROOPERS;
			default:
				return null;
		}
	}

	@Override
	public String toString () {
		return buildUIName ();
	}

}
