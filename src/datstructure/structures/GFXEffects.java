package datstructure.structures;

import java.util.List;

import constants.GFXEffectType;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbgfxeffects.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class GFXEffects extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final GFXEffects instance = new GFXEffects ();

	/**
	 * Creates a new {@link GFXEffects}
	 */
	private GFXEffects () {
		super ("GFX Effects", "dbgfxeffects.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("GFX Effect type", GFXEffectType.values ()),
				FieldStruct.UNKNOWN_INT4, ID_GRAPHIC, FieldStruct.UNKNOWN_FLOAT, new FieldStruct ("Size", FieldType.FLOAT),
				FieldStruct.UNKNOWN_FLOAT, new FieldStruct ("Min spawn time", FieldType.INTEGER), new FieldStruct ("Max spawn time", FieldType.INTEGER), new FieldStruct ("Duration", FieldType.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, ID_GFX_UNKNOWN,
				ID_GFX_UNKNOWN, ID_GFX_UNKNOWN, FieldStruct.ID_LANGUAGE, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		newEntryValues = new Object[] {
				"<New graphic effect>", 0, -1, -1, -1, -1, 1f, 1f,
				0f, 0, 0, 0, 0, 0, 0, -1,
				-1, -1, 0, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0,
				0, 0
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
