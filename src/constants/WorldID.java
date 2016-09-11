package constants;


/**
 * List of global variables defined in the dbworld.dat
 * @author MarcoForlini
 */
public enum WorldID implements EnumValue {
	
	/** Nothing */
	C_01_NULL ("<Undefined>", -1),
	/** Nothing */
	C00_NULL ("<Undefined>", 0),
	/** Max gatherers on a resource */
	C22_MAX_GATHERERS ("Max gatherers", 22),
	/** Max population in a scenario */
	C26_POPULATION_CAP ("Global population cap", 26),
	/** Wounder vicory timer */
	C34_WONDER_COUNTDOWN ("Wonder countdown", 34),
	/** Fraction of max population stolen from other players */
	C36_COLISEUM_BONUS ("Coliseum population owner/others", 36),
	/** Base health points regen X second. Cumulative with variable 51. Total = baseValue * (1 + multValue*(currentAge - 2)) */
	C37_ZEUS_TEMPLE_BONUS_BASE ("Zeus temple base health recover", 37),
	/** Max number of upgrade points (default: 10) */
	C47_MAX_UPGRADES ("Max upgrade points", 47),
	/** Building health bonus */
	C50_ISHTAR_GATE_BONUS ("Ishtar gate health bonus", 50),
	/** Mult health points regen X second. Total = baseValue * (1 + multValue*(currentAge - 2)) */
	C51_ZEUS_TEMPLE_BONUS_MULT ("Zeus temple more health recover X epoch", 51),
	/** Lighthouse range */
	C74_LIGHTHOUSE_RANGE ("Lighthouse range", 74),
	/** Civilization points available in random maps */
	C76_CIV_POINTS ("Random map civilization points", 76),
	/** Powers cost increase for every power already bought */
	C86_POWER_CIV_POINTS ("Power increase civilization points cost", 86)
	;


	/** Name to be shown in the UI */
	public final String name;

	/** Code used in the dat files */
	public final int code;
	

	WorldID(String name, int code){
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
	public static WorldID parseValue(int code){
		for (WorldID effectCode : values()){
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
