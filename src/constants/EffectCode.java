package constants;


public enum EffectCode implements EnumValue {
	
	NONE ("None", -1),
	C01_UNKNOWN ("Unknown 1", 1),
	C02_ALTER_ATTRIBUTE ("Alter attribute", 2),
	C04_UNKNOWN ("Unknown 1", 4),
	C06_SET_GRAPHIC ("Set graphic", 6),
	C08_ENABLE_TECH ("Enable tech", 8),
	C09_DISABLE_TECH ("Disable tech", 9),
	C10_UNKNOWN ("Unknown 10", 10),
	C12_UNKNOWN ("Unknown 12", 12),
	C15_UNKNOWN ("Unknown 15", 15),
	C17_UNKNOWN ("Unknown 17", 17),
	C18_UNKNOWN ("Unknown 18", 18),
	C19_REPLACE_OBJECTS ("Replace objects", 19),
	C20_UNKNOWN ("Unknown 20", 20),
	C21_UNKNOWN ("Unknown 21", 21),
	C22_UNKNOWN ("Unknown 22", 22),
	;


	public final String name;
	public final int code;
	EffectCode(String name, int code){
		this.name = name;
		this.code = code;
	}

	@Override
	public int getValue () {
		return code;
	}
	
	public static EffectCode parseValue(int code){
		for (EffectCode effectCode : values()){
			if (effectCode.code == code){
				return effectCode;
			}
		}
		return NONE;
	}

	@Override
	public String toString(){
		return name + ':' + ' ' + code;
	}
	
}
