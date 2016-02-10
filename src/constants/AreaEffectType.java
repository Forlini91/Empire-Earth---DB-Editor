package constants;


public enum AreaEffectType implements EnumValue {
	
	C0_NONE ("None", 0),
	C1_HEALING ("Healing", 1),
	C2_FIXED_MORALE ("Fixed morale", 2),
	C3_ANTI_CALAMITY ("Anti calamity", 3),
	C4_ANTI_CONVERSION ("Anti conversion", 4),
	C5_DYNAMIC_MORALE ("Dynamic morale", 5),
	C6_HERO_MORALE ("Hero morale", 6),
	C7_ECON_BONUS ("Economic bonus", 7),
	C8_CLOAK ("Cloaking", 8)
	;


	public final String name;
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
	public int getValue () {
		return code;
	}
	
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
		return name + ": " + code;
	}
	
}
