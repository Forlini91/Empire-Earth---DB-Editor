package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbupgrade.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Upgrade extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final Upgrade instance = new Upgrade();

	/**
	 * Creates a new {@link Upgrade}
	 */
	private Upgrade () {
		super("Upgrades", "dbupgrade.dat", true, 0, 1, 1, 0, 31, 32, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Attack mult", Type.FLOAT), new FieldStruct("Health mult", Type.FLOAT), new FieldStruct("Speed mult", Type.FLOAT),
				new FieldStruct("Range mult", Type.FLOAT), new FieldStruct("Shock armor mult", Type.FLOAT), new FieldStruct("Arrow armor mult", Type.FLOAT), new FieldStruct("Pierce armor mult", Type.FLOAT),
				new FieldStruct("Gun armor mult", Type.FLOAT), new FieldStruct("Laser armor mult", Type.FLOAT), new FieldStruct("Missile armor mult", Type.FLOAT), new FieldStruct("Fuel/Power mult", Type.FLOAT),
				FieldStruct.UNUSED_INT4, new FieldStruct("Area mult", Type.FLOAT), FieldStruct.UNUSED_INT4, new FieldStruct("Attack cost mult", Type.FLOAT),
				new FieldStruct("Health cost mult", Type.FLOAT), new FieldStruct("Speed cost mult", Type.FLOAT), new FieldStruct("Range cost mult", Type.FLOAT), new FieldStruct("Shock armor cost mult", Type.FLOAT),
				new FieldStruct("Arrow armor cost mult", Type.FLOAT), new FieldStruct("Pierce armor cost mult", Type.FLOAT), new FieldStruct("Gun armor cost mult", Type.FLOAT), new FieldStruct("Laser armor cost mult", Type.FLOAT),
				new FieldStruct("Missile armor cost mult", Type.FLOAT), new FieldStruct("Fuel/Power cost mult", Type.FLOAT), FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT,
				new FieldStruct("Area cost mult", Type.FLOAT), FieldStruct.UNUSED_INT4, new FieldStruct("Max upgrades per stat", Type.INTEGER), FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, FieldStruct.UNKNOWN_INT4, new FieldStruct("<It seems a starting epoch>", Type.INTEGER), FieldStruct.UNKNOWN_INT4
		};
		newEntryValues = new Object[]{
				"<New upgrade>", 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0, 0f, 0, 0f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0, 0f, 0f, 0, 0, 0,
				0, 0, 0, 0
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
