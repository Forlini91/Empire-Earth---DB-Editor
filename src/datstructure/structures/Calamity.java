package datstructure.structures;

import constants.CalamityTargetType;
import constants.CalamityType;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbcalamity.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Calamity extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final Calamity instance = new Calamity();
	
	/**
	 * Creates a new {@link Calamity}
	 */
	private Calamity () {
		super("Calamities", "dbcalamity.dat", true, 0, 1, 0, 0, 7, 8, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Area damage", Type.FLOAT), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, new FieldStruct("Calamity type", CalamityType.values()), new FieldStruct ("Target type", CalamityTargetType.values()), new FieldStruct("Duration", Type.INTEGER),
				FieldStruct.UNUSED_INT4, new FieldStruct("Cast effect", GFXEffects.instance, 0), new FieldStruct("Hit area effect", GFXEffects.instance, 0), new FieldStruct("Hit target effect", GFXEffects.instance, 0),
				new FieldStruct("Hit target effect", GFXEffects.instance, 0), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				ID_OBJECT, new FieldStruct("Param 1", Type.INTEGER), new FieldStruct("Param 2", Type.INTEGER), new FieldStruct("Param 3", Type.INTEGER),
				new FieldStruct("Param 4", Type.INTEGER), new FieldStruct("Param 5", Type.INTEGER), new FieldStruct("Param 6", Type.INTEGER), FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNUSED_BOOL1, ID_SOUND,
				ID_SOUND, new FieldStruct("Calamity cost", Type.INTEGER), ID_UNIT_SET, FieldStruct.ID_LANGUAGE,
				FieldStruct.UNKNOWN_INT4
		};
		newEntryValues = new Object[]{
				"<New calamity>", 0f, 0f, 0f, 0f, 0f, 0f, 0,
				0, 0, 0, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, -1, -1, 0, 0, 0,
				0
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
