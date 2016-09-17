package constants;


/**
 * Unit type codes used in an Object entry.
 * They defines the unit type.
 * @author MarcoForlini
 */
public enum TerrainFamily implements EnumValue {

	/** Ambient */
	C00_AMBIENT ("Ambient", 0),
	/** Unknown */
	C01_UNKNOWN ("Unknown", 1),
	/** Rocky terrain */
	C02_ROCKY ("Rocky", 2),
	/** Unknown */
	C03_UNKNOWN ("Unknown", 3),
	/** Muddy terrain */
	C04_MUDDY ("Muddy", 4),
	/** Frosty terrain */
	C05_FROSTY ("Frosty", 5),
	/** Grassy terrrain */
	C06_GRASSY ("Grassy", 6),
	/** Unknown */
	C07_UNKNOWN ("Unkwnown", 7),
	/** Artificial */
	C08_ARTIFICIAL ("Artificial", 8),
	/** Cliff */
	C09_CLIFF ("Cliff", 9),
	/** Unknown */
	C10_UNKNOWN ("Unknown", 10),
	/** Sandy terrain */
	C11_SANDY ("Sandy", 11),
	/** Space terrain */
	C12_SPACE ("Space", 12),
	;
	
	
	/** Name to be shown in the UI */
	public final String name;

	/** Code used in the dat files */
	public final int code;
	

	TerrainFamily(String name, int code){
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
		return code >= 0 && code <= 12;
	}

	@Override
	public TerrainFamily parseValue(int code){
		switch (code){
			case 0: return C00_AMBIENT;
			case 1: return C01_UNKNOWN;
			case 2: return C02_ROCKY;
			case 3: return C03_UNKNOWN;
			case 4: return C04_MUDDY;
			case 5: return C05_FROSTY;
			case 6: return C06_GRASSY;
			case 7: return C07_UNKNOWN;
			case 8: return C08_ARTIFICIAL;
			case 9: return C09_CLIFF;
			case 10: return C10_UNKNOWN;
			case 11: return C11_SANDY;
			case 12: return C12_SPACE;
			default: return null;
		}
	}
	
	@Override
	public String toString(){
		return buildUIName();
	}

}
