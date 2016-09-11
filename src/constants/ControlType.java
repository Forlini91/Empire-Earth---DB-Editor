package constants;


/**
 * Controls codes used in an UI contro event entry.
 * They define the event condition of an UI element.
 * @author MarcoForlini
 */
public enum ControlType implements EnumValue {
	
	/** No condition */
	NONE ("None", -1),
	/** Unknown event */
	C0_Update_Controls ("Update controls", 0),
	/** Move slider */
	C1000_Slider ("Slider", 1000),
	/** Check a checkbox */
	C1001_Check ("Check", 1001),
	/** Uncheck a checkbox */
	C1002_Uncheck ("Uncheck", 1002),
	/** Select an element in the list */
	C1003_List_select ("List select", 1003),
	/** Double click an element */
	C1004_Double_click ("Double click", 1004),
	/** Input password */
	C1007_Password ("Password", 1007),
	/** Click an element */
	C1008_Single_click ("Single click", 1008),
	/** Stop typing in the field */
	C1009_Done_typing ("Done typing", 1009),
	/** Move your mouse on the element */
	C1010_Hovering ("Hovering", 1010),
	/** Mouve your mouse away from the element */
	C1011_Not_hovering ("Not hovering", 1011),
	/** Type in the chat box */
	C1012_Chat ("Chat", 1012),
	;


	/** Name to be shown in the UI */
	public final String name;
	
	/** Code used in the dat files */
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
	public int getCode () {
		return code;
	}
	
	/**
	 * Parse the code and return the relative enum.
	 * @param code	The code
	 * @return		The relative enum
	 */
	public static ControlType parseValue(int code){
		for (ControlType effectCode : values()){
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
