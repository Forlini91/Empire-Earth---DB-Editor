package constants;


/**
 * Object type codes used in an Object entry.
 * They defines the object type.
 * @author MarcoForlini
 */
public enum ObjectType implements EnumValue {

	/** Building and World */
	C0_Buildings_World ("Buildings & World", 0),
	/** Air unit */
	C1_Aircrafts ("Aircrafts", 1),
	/** Heros and Healers */
	C2_Heroes_Medics ("Heroes & Medics", 2),
	/** Archer */
	C3_Archers ("Archers", 3),
	/** Machines */
	C4_Siege_AATowers ("Siege & AA Towers", 4),
	/** Light land units */
	C5_Infantry_Cyborgs ("Infantry & Cyborgs", 5),
	/** Religion */
	C6_Priests_Prophets ("Priests & Prophets", 6),
	/** Civilians */
	C7_Citizens_Diplomats ("Citizens & Diplomats", 7),
	/** Heavy land units */
	C8_Cavalry_Tanks ("Cavalry & Tanks", 8),
	/** Sea units */
	C9_Ships_Submarines ("Ships & Submarines", 9),
	;
	
	
	/** Name to be shown in the UI */
	public final String name;

	/** Code used in the dat files */
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
	public int getCode () {
		return code;
	}

	/**
	 * Parse the code and return the relative enum.
	 * @param code	The code
	 * @return		The relative enum
	 */
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
		return buildUIName();
	}

}
