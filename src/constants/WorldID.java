package constants;

import java.util.Arrays;

/**
 * List of global variables defined in the dbworld.dat
 * 
 * @author MarcoForlini
 */
public enum WorldID implements EnumValue {

	/** Nothing */
	C_01_NULL ("<Undefined>", -1),
	/** Nothing */
	C00_NULL ("<Undefined>", 0),
	/** Max gatherers on a resource */
	C22_MAX_GATHERERS ("Gatherers per resource (max)", 22),
	/** Max population in a scenario */
	C26_POPULATION_CAP ("Global population cap", 26),
	/** Wounder vicory timer */
	C34_WONDER_COUNTDOWN ("Wonder countdown", 34),
	/** Fraction of max population stolen from other players */
	C36_COLISEUM_BONUS ("Coliseum population owner/others", 36),
	/** Base health points regen X second. Cumulative with variable 51. Total = baseValue * (1 + multValue*(currentAge - 2)) */
	C37_ZEUS_TEMPLE_BONUS_BASE ("Zeus temple base health recover", 37),
	/** Min/Max game speed */
	C43_GAME_SPEED ("Game speed (min/max)", 43),
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
	C86_POWER_CIV_POINTS ("Power increase civilization points cost", 86);

	private static final int[] codes;
	static {
		WorldID[] values = values ();
		int n = values.length;
		codes = new int[n];
		for (int i = 0; i < n; i++) {
			codes[i] = values[i].code;
		}
		Arrays.sort (codes);
	}

	/** Name to be shown in the UI */
	public final String name;

	/** Code used in the dat files */
	public final int code;


	WorldID (String name, int code) {
		this.name = name;
		this.code = code;
	}

	@Override
	public String getName () {
		return name;
	}

	@Override
	public int getCode () {
		return code;
	}

	@Override
	public WorldID parseValue (int code) {
		int index = Arrays.binarySearch (codes, code);
		if (index >= 0) {
			return values ()[index];
		}
		return null;
	}

	@Override
	public String toString () {
		return buildUIName ();
	}

}
