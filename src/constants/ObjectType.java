package constants;


public enum ObjectType implements EnumValue {

	C0_Buildings_World ("Buildings & World", 0),
	C1_Aircrafts ("Aircrafts", 1),
	C2_Heroes_Medics ("Heroes & Medics", 2),
	C3_Archers ("Archers", 3),
	C4_Siege_AATowers ("Siege & AA Towers", 4),
	C5_Infantry_Cyborgs ("Infantry & Cyborgs", 5),
	C6_Priests_Prophets ("Priests & Prophets", 6),
	C7_Citizens_Diplomats ("Citizens & Diplomats", 7),
	C8_Cavalry_Tanks ("Cavalry & Tanks", 8),
	C9_Ships_Submarines ("Ships & Submarines", 9),
	;
	
	
	public final String name;
	public final int code;
	ObjectType(String name, int code){
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

	public static ObjectType parseValue(int code){
		for (ObjectType effectCode : values()){
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
