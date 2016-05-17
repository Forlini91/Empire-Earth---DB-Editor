package constants;


public enum UnitType implements EnumValue {

	C00_AMBIENT ("Ambient", 0),
	C01_RESOURCE ("Resource", 1),
	C02_BUILDING ("Building", 2),
	C03_AIR_UNIT ("Air unit", 3),
	C04_SEA_UNIT ("Sea unit", 4),
	C05_LAND_UNIT ("Land unit", 5),
	C06_WORKER_UNIT ("Worker", 6),
	C07_HELICOPTER ("Helicopter unit", 7),
	C09_PROJECTILE ("Projectile", 9),
	C10_UNIT_UNDERWATER ("Underwater unit", 10),
	C11_BOMB ("Bomb", 11),
	C12_MISSILE ("Missile", 12),
	C13_HERO_HEALER ("Hero - Strategist", 13),
	C16_LAND_TRANSPORT ("Land transport", 16),
	C17_HELI_TRANSPORT ("Air transport", 17),
	C18_SHIP_TRANSPORT ("Sea transport", 18),
	C20_MISSILE_UNDERWATER ("Underwater missile", 20),
	C21_CARRIER ("Carrier", 21),
	C22_SHIP_FISHING ("Fishing ship", 22),
	C23_HERO_MORALE ("Hero - Warrior", 23),
	C25_GATE ("Gate", 25),
	C26_CYBORG_MEDIC ("Medic cyborg", 26),
	C27_ANIMAL_SEA ("Sea animal", 27),
	C28_ANIMAL_AIR ("Air animal", 28),
	C29_FORTRESS ("Fortress building", 29),
	C30_PRIEST_TOWER ("Priest tower", 30),
	C31_SPACE_BATTLESHIP ("Space battleship", 31),
	C32_SPACE_CARRIER ("Space carrier", 32),
	C33_SPACE_TRANSPORT ("Space transport", 33),
	C34_SPACE_FIGHTER ("Space fighter", 34)
	;
	
	
	public final String name;
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
	public int getValue () {
		return code;
	}

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
		return name + ": " + code;
	}

}
