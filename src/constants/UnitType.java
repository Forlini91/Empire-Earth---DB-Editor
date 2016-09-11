package constants;


/**
 * Unit type codes used in an Object entry.
 * They defines the unit type.
 * @author MarcoForlini
 */
public enum UnitType implements EnumValue {

	/** Ambient */
	C00_AMBIENT ("Ambient", 0),
	/** Resource */
	C01_RESOURCE ("Resource", 1),
	/** Building */
	C02_BUILDING ("Building", 2),
	/** Aircraft */
	C03_AIR_UNIT ("Aircraft", 3),
	/** Ship */
	C04_SEA_UNIT ("Ship", 4),
	/** Land unit */
	C05_LAND_UNIT ("Land unit", 5),
	/** Worker/Builder/Gatherer */
	C06_WORKER_UNIT ("Worker", 6),
	/** Helicopter */
	C07_HELICOPTER ("Helicopter", 7),
	/** Unknown type */
	C08_UNKNOWN ("Unknown", 8),
	/** Projectile */
	C09_PROJECTILE ("Projectile", 9),
	/** Submarine */
	C10_UNIT_UNDERWATER ("Submarine", 10),
	/** Bomb */
	C11_BOMB ("Bomb", 11),
	/** Missile */
	C12_MISSILE ("Missile", 12),
	/** Hero - Strategist */
	C13_HERO_HEALER ("Hero - Strategist", 13),
	/** Unknown type */
	C14_UNKNOWN ("Unknown", 14),
	/** Unknown type */
	C15_UNKNOWN ("Unknown", 15),
	/** Land transport */
	C16_LAND_TRANSPORT ("Land transport", 16),
	/** Air transport */
	C17_HELI_TRANSPORT ("Air transport", 17),
	/** Sea transport */
	C18_SHIP_TRANSPORT ("Sea transport", 18),
	/** Unknown type */
	C19_UNKNOWN ("Unknown", 19),
	/** Torpedo */
	C20_MISSILE_UNDERWATER ("Torpedo", 20),
	/** Carrier */
	C21_CARRIER ("Carrier", 21),
	/** Fishing ship */
	C22_SHIP_FISHING ("Fishing ship", 22),
	/** Hero - Warrior */
	C23_HERO_MORALE ("Hero - Warrior", 23),
	/** Unknown type */
	C24_UNKNOWN ("Unknown", 24),
	/** Gate */
	C25_GATE ("Gate", 25),
	/** Cyborg medic */
	C26_CYBORG_MEDIC ("Cyborg medic", 26),
	/** Sea animal */
	C27_ANIMAL_SEA ("Sea animal", 27),
	/** Air animal */
	C28_ANIMAL_AIR ("Air animal", 28),
	/** Fortress */
	C29_FORTRESS ("Fortress", 29),
	/** Priest tower */
	C30_PRIEST_TOWER ("Priest tower", 30),
	/** Space battleship */
	C31_SPACE_BATTLESHIP ("Space battleship", 31),
	/** Space carrier */
	C32_SPACE_CARRIER ("Space carrier", 32),
	/** Space transport */
	C33_SPACE_TRANSPORT ("Space transport", 33),
	/** Space fighter */
	C34_SPACE_FIGHTER ("Space fighter", 34)
	;
	
	
	/** Name to be shown in the UI */
	public final String name;

	/** Code used in the dat files */
	public final int code;
	

	UnitType(String name, int code){
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
	public static UnitType parseValue(int code){
		for (UnitType effectCode : values()){
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
