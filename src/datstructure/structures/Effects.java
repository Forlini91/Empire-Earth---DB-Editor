package datstructure.structures;

import java.util.List;

import constants.AttributeCode;
import constants.EffectCode;
import constants.TerrainFamily;
import datmanager.Core;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbeffects.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.MISSING_UNKNOWN)
public class Effects extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Effects instance = new Effects ();

	/**
	 * Creates a new {@link Effects}
	 */
	private Effects () {
		super ("Effects", "dbeffects.dat", false, 0, 1, 1, -1, 0, 1, 3, 200, 275);
	}

	@Override
	public void init () {
		if (!Core.AOC) { // File structure has been changed in AOC
			nameBuilder = (entry) -> EffectCode.C01_SET_BUTTON.parseValue ((int) entry.get (8)).nameBuilder.apply (entry);
			fieldStructs = new FieldStruct[] {
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Set base attribute", "The given value will replace the base value", FieldType.FLOAT), new FieldStruct ("Alter base attribute", "The given value will add/subtract from the current base value", FieldType.FLOAT),
					new FieldStruct ("Alter attribute multiplier", "The given value will add/subtract from the current multiplier", FieldType.FLOAT), ID_OBJECT, new FieldStruct ("New object", Objects.instance, 0), ID_UNIT_SET,
					new FieldStruct ("Effect code", EffectCode.values ()), new FieldStruct ("New graphic", Graphics.instance, 0), ID_TECH, new FieldStruct ("Attribute", AttributeCode.values ()),
					new FieldStruct ("New sound", Sounds.instance, 0), new FieldStruct ("New button", Buttons.instance, 0), new FieldStruct ("New area effect", AreaEffect.instance, 0), new FieldStruct ("Terrain family", TerrainFamily.values ()),
					FieldStruct.UNUSED_INT4,
			};
			newEntryValues = new Object[] {
					0, 0, 0f, 0f, 0f, -1, -1, -1, 11
							- 1,
					-1, -1, -1, -1, -1, -1, 0,
					0,
			};

		} else {

			nameBuilder = (entry) -> EffectCode.C01_SET_BUTTON.parseValue ((int) entry.get (8)).nameBuilder.apply (entry);
			fieldStructs = new FieldStruct[] {
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Set base attribute", "The given value will replace the base value", FieldType.FLOAT), new FieldStruct ("Alter base attribute", "The given value will add/subtract from the current base value", FieldType.FLOAT),
					new FieldStruct ("Alter attribute multiplier", "The given value will add/subtract from the current multiplier", FieldType.FLOAT), new FieldStruct ("Object 1", Objects.instance, 0), new FieldStruct ("Object 2", Objects.instance, 0), ID_UNIT_SET,
					new FieldStruct ("Effect code", EffectCode.values ()), new FieldStruct ("New graphic", Graphics.instance, 0), ID_TECH, new FieldStruct ("Attribute", AttributeCode.values ()),
					new FieldStruct ("New sound", Sounds.instance, 0), new FieldStruct ("New button", Buttons.instance, 0), new FieldStruct ("New area effect", AreaEffect.instance, 0), new FieldStruct ("Terrain family", TerrainFamily.values ()),
					FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
					FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
			};
			newEntryValues = new Object[] {
					0, 0, 0f, 0f, 0f, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, 0,
					0, 0, 0, 0, 0, 0,
			};
		}
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
