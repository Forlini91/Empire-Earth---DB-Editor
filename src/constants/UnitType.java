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

	@Override
	public boolean isValid (int code) {
		return code >= 0 && code <= 34;
	}
	
	@Override
	public UnitType parseValue(int code){
		switch (code){
			case 0: return C00_AMBIENT;
			case 1: return C01_RESOURCE;
			case 2: return C02_BUILDING;
			case 3: return C03_AIR_UNIT;
			case 4: return C04_SEA_UNIT;
			case 5: return C05_LAND_UNIT;
			case 6: return C06_WORKER_UNIT;
			case 7: return C07_HELICOPTER;
			case 8: return C08_UNKNOWN;
			case 9: return C09_PROJECTILE;
			case 10: return C10_UNIT_UNDERWATER;
			case 11: return C11_BOMB;
			case 12: return C12_MISSILE;
			case 13: return C13_HERO_HEALER;
			case 14: return C14_UNKNOWN;
			case 15: return C15_UNKNOWN;
			case 16: return C16_LAND_TRANSPORT;
			case 17: return C17_HELI_TRANSPORT;
			case 18: return C18_SHIP_TRANSPORT;
			case 19: return C19_UNKNOWN;
			case 20: return C20_MISSILE_UNDERWATER;
			case 21: return C21_CARRIER;
			case 22: return C22_SHIP_FISHING;
			case 23: return C23_HERO_MORALE;
			case 24: return C24_UNKNOWN;
			case 25: return C25_GATE;
			case 26: return C26_CYBORG_MEDIC;
			case 27: return C27_ANIMAL_SEA;
			case 28: return C28_ANIMAL_AIR;
			case 29: return C29_FORTRESS;
			case 30: return C30_PRIEST_TOWER;
			case 31: return C31_SPACE_BATTLESHIP;
			case 32: return C32_SPACE_CARRIER;
			case 33: return C33_SPACE_TRANSPORT;
			case 34: return C34_SPACE_FIGHTER;
			default: return null;
		}
	}

	@Override
	public String toString(){
		return buildUIName();
	}
	
}
