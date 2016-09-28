package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;

/**
 * Represents the file dbambientsounds.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class AmbientSounds extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final AmbientSounds instance = new AmbientSounds();

	/**
	 * Creates a new {@link AmbientSounds}
	 */
	private AmbientSounds () {
		super("Ambient sounds", "dbambientsounds.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Sound ID", Sounds.instance, 0),
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_FLOAT
		};
		newEntryValues = new Object[]{
				"<New ambient sound>", 0, -1, -1, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0, 0f,
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
