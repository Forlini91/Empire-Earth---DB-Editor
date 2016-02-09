package constants;


public enum ControlType implements EnumValue {

	C_1_NONE ("None", -1),
	C0_Update_Controls ("Update controls", 0),
	C1001_Slider ("Slider", 1000),
	C1002_Check ("Check", 1001),
	C1003_Uncheck ("Uncheck", 1002),
	C1004_List_select ("List select", 1003),
	C1004_Double_click ("Double click", 1004),
	C1007_Passwoed ("Password", 1007),
	C1008_Single_click ("Single click", 1008),
	C1009_Done_typing ("Done typing", 1009),
	C1010_Hovering ("Hovering", 1010),
	C1011_Not_hovering ("Not hovering", 1011),
	C1012_Chat ("Chat", 1012),
	;
	
	
	public final String name;
	public final int code;
	ControlType(String name, int code){
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

	public static ControlType parseValue(int code){
		for (ControlType effectCode : values()){
			if (effectCode.code == code){
				return effectCode;
			}
		}
		return ControlType.C_1_NONE;
	}
	
	@Override
	public String toString(){
		return "(" + code + ") " + name;
	}

}
