package constants;


public enum AttributeCode implements EnumValue {

	C_1_NONE ("None", -1),
	C00_NONE ("None", 0),
	C01_ATTACK ("Attack", 1),
	C02_NAME ("Name", 1),
	C03_RANGE ("Range", 3),
	C04_LOS ("LOS", 4),
	C06_HEALTH ("Health", 6),
	C09_SPEED ("Speed", 9),
	C23_POP_LIMIT ("Pop limit", 23),
	C26_WOOD_GATHER ("Wood gather time", 26),
	C27_STONE_GATHER ("Stone gather time", 27),
	C28_GOLD_GATHER ("Gold gather time", 28),
	C29_STEEL_GATHER ("Steel gather time", 29),
	C31_HUNT_GATHER ("Hunt gather time", 31),
	C32_FORAGE_GATHER ("Forage gather time", 32),
	C33_FARM_GATHER ("Farm gather time", 33),
	C36_CONVERT_PRIEST ("Can convert priests", 36),
	C37_CONVERT_BUILDING ("Can convert buildings", 37),
	C38_COMMERCIAL_TAXES ("Commercial taxes", 38),
	C40_REPAIR_SPEED ("Repair speed", 40),
	C41_POWER_RECOVER_RATE ("Power recover rate", 41),
	C42_PATH_FINDING ("Path finding", 42),
	C43_HEALTH_RECOVER_RATE ("Health recover rate", 43),
	C44_POWER_AMOUNT ("Power amount", 44),
	C45_NAME ("Name", 45),
	;
	
	
	public final String name;
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
	public int getValue () {
		return code;
	}

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
		return name + ": " + code;
	}

}
