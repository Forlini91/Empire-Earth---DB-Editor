package constants;


/**
 * Tech type codes used in a Technology entry.
 * They defines the technology type.
 * @author MarcoForlini
 */
public enum TechType implements EnumValue {
	
	/** Technology which improve the civilization (+15% food gather rate) */
	C0_Research ("Research", 0),
	/** Technology which allow to create/build an object */
	C1_Object ("Object", 1),
	/** Technology which allow to advance to a new epoch */
	C2_Epoch ("Epoch", 2),
	/** Technology which allow to replace an object with another one (Conscription, etc) */
	C3_Replace ("Replace", 3),
	/** Technology which allow to build a wonder */
	C4_Wonder ("Wonder", 4),
	/** Technology which allow to cast a calamity */
	C5_Calamity ("Calamity", 5),
	/** Technology which allow to build a wall */
	C6_Wall ("Wall", 6),
	/** Technology which allow to upgrade a class of objects (Upgrade Marine to Sentinel) */
	C7_Upgrade ("Upgrade class", 7),
	/** Unknown technology */
	C8_Unknown ("Unknown", 8),
	/** Technology which allow to upgrade a single object to a new one (Upgrade Settlement to Town center) */
	C9_Garrison_Upgrade ("Upgrade single object", 9),
	;


	/** Name to be shown in the UI */
	public final String name;
	
	/** Code used in the dat files */
	public final int code;

	
	TechType(String name, int code){
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
	
	@Override
	public TechType parseValue(int code){
		switch (code){
			case 0: return C0_Research;
			case 1: return C1_Object;
			case 2: return C2_Epoch;
			case 3: return C3_Replace;
			case 4: return C4_Wonder;
			case 5: return C5_Calamity;
			case 6: return C6_Wall;
			case 7: return C7_Upgrade;
			case 8: return C8_Unknown;
			case 9: return C9_Garrison_Upgrade;
			default: return null;
		}
	}

	@Override
	public String toString(){
		return buildUIName();
	}
	
}
