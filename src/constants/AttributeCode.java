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
	C42_PATH_FINDING ("<Used by path finding power>", 42),
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
	
	/**
	 * Parse the code and return the relative enum.
	 * @param code	The code
	 * @return		The relative enum
	 */
	public static AttributeCode parseValue(int code){
		for (AttributeCode effectCode : values()){
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
