package constants;


public enum TechType implements EnumValue {

	C0_Research ("Research", 0),
	C1_Object ("Object", 1),
	C2_Epoch ("Epoch", 2),
	C3_Replace ("Replace", 3),
	C4_Wonder ("Wonder", 4),
	C5_Calamity ("Calamity", 5),
	C6_Wall ("Wall", 6),
	C7_Upgrade ("Upgrade", 7),
	C9_Garrison_Upgrade ("Garrison upgrade", 9),
	;
	
	
	public final String name;
	public final int code;
	TechType(String name, int code){
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

	public static TechType parseValue(int code){
		for (TechType effectCode : values()){
			if (effectCode.code == code){
				return effectCode;
			}
		}
		return C0_Research;
	}
	
	@Override
	public String toString(){
		return "(" + code + ") " + name;
	}

}
