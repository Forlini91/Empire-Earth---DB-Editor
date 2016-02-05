package constants;


public enum EffectCode implements EnumValue {
	
	NONE ("None", -1),
	C01_SET_BUTTON ("Set button", 1),
	C02_ALTER_ATTRIBUTE ("Alter attribute", 2),
	C04_UNKNOWN ("Unknown 1", 4),
	C06_SET_GRAPHIC ("Set graphic", 6),
	C08_ENABLE_TECH ("Enable tech", 8),
	C09_DISABLE_TECH ("Disable tech", 9),
	C10_START_GAME ("Start game", 10),
	C12_GUI_BACKGROUND ("GUI Background", 12),
	C15_SET_ACTION_SOUND_1 ("Set action sound 1", 15),
	C17_SET_DEATH_SOUND ("Set death sound", 17),
	C18_SET_SELECTION_SOUND_1 ("Set selection sound 1", 18),
	C19_REPLACE_ALL_OBJECTS ("Replace all objects", 19),
	C20_SET_ACTION_SOUND_2 ("Set action sound 2", 20),
	C21_SET_SELECTION_SOUND_2 ("Set selection sound 2", 21),
	C22_REPLACE_OBJECT ("Replace object", 22),
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
		return name + ": " + code;
	}
	
}
