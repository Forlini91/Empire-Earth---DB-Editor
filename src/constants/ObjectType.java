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

	@Override
	public boolean isValid (int code) {
		return code >= 0 && code <= 9;
	}
	
	/**
	 * Parse the code and return the relative enum.
	 * @param code	The code
	 * @return		The relative enum
	 */
	public static ObjectType parseValue(int code){
		switch (code){
			case 0: return C0_Buildings_World;
			case 1: return C1_Aircrafts;
			case 2: return C2_Heroes_Medics;
			case 3: return C3_Archers;
			case 4: return C4_Siege_AATowers;
			case 5: return C5_Infantry_Cyborgs;
			case 6: return C6_Priests_Prophets;
			case 7: return C7_Citizens_Diplomats;
			case 8: return C8_Cavalry_Tanks;
			case 9: return C9_Ships_Submarines;
			default: return null;
		}
	}

	@Override
	public String toString(){
		return buildUIName();
	}
	
}
