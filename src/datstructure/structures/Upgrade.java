package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbupgrade.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Upgrade extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Upgrade instance = new Upgrade ();

	/**
	 * Creates a new {@link Upgrade}
	 */
	private Upgrade () {
		super ("Upgrades", "dbupgrade.dat", true, 0, 1, 1, 0, 31, 32, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, new FieldStruct ("Attack mult", FieldType.FLOAT), new FieldStruct ("Health mult", FieldType.FLOAT), new FieldStruct ("Speed mult", FieldType.FLOAT),
				new FieldStruct ("Range mult", FieldType.FLOAT), new FieldStruct ("Shock armor mult", FieldType.FLOAT), new FieldStruct ("Arrow armor mult", FieldType.FLOAT), new FieldStruct ("Pierce armor mult", FieldType.FLOAT),
				new FieldStruct ("Gun armor mult", FieldType.FLOAT), new FieldStruct ("Laser armor mult", FieldType.FLOAT), new FieldStruct ("Missile armor mult", FieldType.FLOAT), new FieldStruct ("Fuel/Power mult", FieldType.FLOAT),
				FieldStruct.UNUSED_INT4, new FieldStruct ("Area mult", FieldType.FLOAT), FieldStruct.UNUSED_INT4, new FieldStruct ("Attack cost mult", FieldType.FLOAT),
				new FieldStruct ("Health cost mult", FieldType.FLOAT), new FieldStruct ("Speed cost mult", FieldType.FLOAT), new FieldStruct ("Range cost mult", FieldType.FLOAT), new FieldStruct ("Shock armor cost mult", FieldType.FLOAT),
				new FieldStruct ("Arrow armor cost mult", FieldType.FLOAT), new FieldStruct ("Pierce armor cost mult", FieldType.FLOAT), new FieldStruct ("Gun armor cost mult", FieldType.FLOAT), new FieldStruct ("Laser armor cost mult", FieldType.FLOAT),
				new FieldStruct ("Missile armor cost mult", FieldType.FLOAT), new FieldStruct ("Fuel/Power cost mult", FieldType.FLOAT), FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT,
				new FieldStruct ("Area cost mult", FieldType.FLOAT), FieldStruct.UNUSED_INT4, new FieldStruct ("Max upgrades per stat", FieldType.INTEGER), FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, FieldStruct.UNKNOWN_INT4, new FieldStruct ("<It seems a starting epoch>", FieldType.INTEGER), FieldStruct.UNKNOWN_INT4
		};
		newEntryValues = new Object[] {
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

	@Override
	public boolean hasCustomEntryName () {
		return false;
	}

	@Override
	public String getCustomEntryName (int index, List <Object> values) {
		return null;
	}

	@Override
	public String getEntryDescription (Entry entry) {
		return null;
	}

}
