package constants;


/**
 * Attribute codes used in an Effect entry.
 * They define the attribute set/mod by the effect (only if the effect type is "Alter Attribute").
 * @author MarcoForlini
 */
public enum AttributeCode implements EnumValue {

	/** No attribute */
	C_1_NONE ("None", -1),
	/** Attack damage */
	C01_ATTACK ("Attack", 1),
	/** Unknown attribute. Maybe Min range? */
	C02_UNKNOWN ("Unknown", 2),
	/** Max range */
	C03_RANGE ("Range", 3),
	/** Max LOS */
	C04_LOS ("LOS", 4),
	/** Unknown attribute */
	C05_UNKNOWN ("Unknown", 5),
	/** Health */
	C06_HEALTH ("Health", 6),
	/** Unknown attribute */
	C07_UNKNOWN ("Unknown", 7),
	/** Unknown attribute */
	C08_UNKNOWN ("Unknown", 8),
	/** Max speed */
	C09_SPEED ("Speed", 9),
	/** Unknown attribute */
	C10_UNKNOWN ("Unknown", 10),
	/** Unknown attribute */
	C11_UNKNOWN ("Unknown", 11),
	/** Unknown attribute */
	C12_UNKNOWN ("Unknown", 12),
	/** Unknown attribute */
	C13_UNKNOWN ("Unknown", 13),
	/** Unknown attribute */
	C14_UNKNOWN ("Unknown", 14),
	/** Unknown attribute */
	C15_UNKNOWN ("Unknown", 15),
	/** Unknown attribute */
	C16_UNKNOWN ("Unknown", 16),
	/** Unknown attribute */
	C17_UNKNOWN ("Unknown", 17),
	/** Unknown attribute */
	C18_UNKNOWN ("Unknown", 18),
	/** Unknown attribute */
	C19_UNKNOWN ("Unknown", 19),
	/** Unknown attribute */
	C20_UNKNOWN ("Unknown", 20),
	/** Unknown attribute */
	C21_UNKNOWN ("Unknown", 21),
	/** Unknown attribute */
	C22_UNKNOWN ("Unknown", 22),
	/** Max population limit */
	C23_POP_LIMIT ("Pop limit", 23),
	/** Unknown attribute */
	C24_UNKNOWN ("Unknown", 24),
	/** Unknown attribute */
	C25_UNKNOWN ("Unknown", 25),
	/** Wood gather time (time, not speed!) */
	C26_WOOD_GATHER ("Wood gather time", 26),
	/** Stone gather time (time, not speed!) */
	C27_STONE_GATHER ("Stone gather time", 27),
	/** Gold gather time (time, not speed!) */
	C28_GOLD_GATHER ("Gold gather time", 28),
	/** Iron gather time (time, not speed!) */
	C29_IRON_GATHER ("Iron gather time", 29),
	/** Unknown attribute */
	C30_UNKNOWN ("Unknown", 30),
	/** Hunt gather time (time, not speed!) */
	C31_HUNT_GATHER ("Hunt gather time", 31),
	/** Forage gather time (time, not speed!) */
	C32_FORAGE_GATHER ("Forage gather time", 32),
	/** Farm gather time (time, not speed!) */
	C33_FARM_GATHER ("Farm gather time", 33),
	/** Unknown attribute */
	C34_UNKNOWN ("Unknown", 34),
	/** Unknown attribute */
	C35_UNKNOWN ("Unknown", 35),
	/** Flag: can convert priests */
	C36_CONVERT_PRIEST ("Can convert priests", 36),
	/** Flat: can convert buildings */
	C37_CONVERT_BUILDING ("Can convert buildings", 37),
	/** Commercial taxes amount */
	C38_COMMERCIAL_TAXES ("Commercial taxes", 38),
	/** Unknown attribute */
	C39_UNKNOWN ("Unknown", 39),
	/** Repair speed */
	C40_AREA_EFFECT ("Area effect", 40),
	/** Power recover rate */
	C41_POWER_RECOVER_RATE ("Power recover rate", 41),
	/** Used by path finding power */
	C42_TERRAIN_FAMILY ("Allowed terrain family", 42),
	/** Health recover rate */
	C43_HEALTH_RECOVER_RATE ("Health recover rate", 43),
	/** Power amount */
	C44_POWER_AMOUNT ("Power amount", 44),
	/** Name */
	C45_NAME ("Name", 45),
	;
	
	
	/** Name to be shown in the UI */
	public final String name;
	
	/** Code used in the dat files */
	public final int code;

	
	AttributeCode(String name, int code){
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
		return code >= -1 && code <= 45;
	}

	

	@Override
	public AttributeCode parseValue(int code){
		switch (code){
			case -1: return C_1_NONE;
			case 1: return C01_ATTACK;
			case 2: return C02_UNKNOWN;
			case 3: return C03_RANGE;
			case 4: return C04_LOS;
			case 5: return C05_UNKNOWN;
			case 6: return C06_HEALTH;
			case 7: return C07_UNKNOWN;
			case 8: return C08_UNKNOWN;
			case 9: return C09_SPEED;
			case 10: return C10_UNKNOWN;
			case 11: return C11_UNKNOWN;
			case 12: return C12_UNKNOWN;
			case 13: return C13_UNKNOWN;
			case 14: return C14_UNKNOWN;
			case 15: return C15_UNKNOWN;
			case 16: return C16_UNKNOWN;
			case 17: return C17_UNKNOWN;
			case 18: return C18_UNKNOWN;
			case 19: return C19_UNKNOWN;
			case 20: return C20_UNKNOWN;
			case 21: return C21_UNKNOWN;
			case 22: return C22_UNKNOWN;
			case 23: return C23_POP_LIMIT;
			case 24: return C24_UNKNOWN;
			case 25: return C25_UNKNOWN;
			case 26: return C26_WOOD_GATHER;
			case 27: return C27_STONE_GATHER;
			case 28: return C28_GOLD_GATHER;
			case 29: return C29_IRON_GATHER;
			case 30: return C30_UNKNOWN;
			case 31: return C31_HUNT_GATHER;
			case 32: return C32_FORAGE_GATHER;
			case 33: return C33_FARM_GATHER;
			case 34: return C34_UNKNOWN;
			case 35: return C35_UNKNOWN;
			case 36: return C36_CONVERT_PRIEST;
			case 37: return C37_CONVERT_BUILDING;
			case 38: return C38_COMMERCIAL_TAXES;
			case 39: return C39_UNKNOWN;
			case 40: return C40_AREA_EFFECT;
			case 41: return C41_POWER_RECOVER_RATE;
			case 42: return C42_TERRAIN_FAMILY;
			case 43: return C43_HEALTH_RECOVER_RATE;
			case 44: return C44_POWER_AMOUNT;
			case 45: return C45_NAME;
			default: return null;
		}
	}
	
	@Override
	public String toString(){
		return buildUIName();
	}

}
