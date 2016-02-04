package constants;


public enum AttributeCode implements EnumValue {
	
	NONE ("None", -1),
	C26_WOOD_GATHER_TIME ("Wood gather time", 26),
	C27_STONE ("Stone gather time", 27),
	C28_GOLD ("Gold gather time", 28),
	C29_STEEL ("Steel gather time", 29),
	C31_HUNT ("Hunt gather time", 31),
	C31_FARM ("Farm gather time", 32),
	;


	public final String name;
	public final int code;
	AttributeCode(String name, int code){
		this.name = name;
		this.code = code;
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
		return NONE;
	}

	@Override
	public String toString(){
		return "(" + code + ") " + name;
	}
	
}
