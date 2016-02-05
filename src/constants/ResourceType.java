package constants;


public enum ResourceType implements EnumValue {

	C0_None ("None", 0),
	C1_All ("All", 0),
	C2_Food ("Food", 2),
	C3_Wood ("Wood", 3),
	C4_Stone ("Stone", 4),
	C5_Gold ("Gold", 5),
	C6_Iron ("Iron", 6),
	;
	
	
	public final String name;
	public final int code;
	ResourceType(String name, int code){
		this.name = name;
		this.code = code;
	}
	
	@Override
	public int getValue () {
		return code;
	}

	public static ResourceType parseValue(int code){
		for (ResourceType effectCode : values()){
			if (effectCode.code == code){
				return effectCode;
			}
		}
		return C0_None;
	}
	
	@Override
	public String toString(){
		return "(" + code + ") " + name;
	}

}
