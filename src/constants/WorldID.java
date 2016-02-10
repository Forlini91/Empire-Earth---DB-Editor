package constants;


public enum WorldID implements EnumValue {
	
	C_01_NULL ("<Undefined>", -1),
	C00_NULL ("<Undefined>", 0),
	C22_MAX_GATHERERS ("Max gatherers", 22),
	C26_POPULATION_CAP ("Global population cap", 26),
	C47_MAX_UPGRADES ("Max upgrade points", 47),
	C76_CIV_POINTS ("Random map civilization points", 76),
	C86_POWER_CIV_POINTS ("Power increase civilization points cost", 86)
	;


	public final String name;
	public final int code;
	WorldID(String name, int code){
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
	
	public static WorldID parseValue(int code){
		for (WorldID effectCode : values()){
			if (effectCode.code == code){
				return effectCode;
			}
		}
		return null;
	}

	@Override
	public String toString(){
		return "(" + code + ") " + name;
	}
	
}
