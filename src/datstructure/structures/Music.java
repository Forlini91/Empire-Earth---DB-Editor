package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbmusic.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Music extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Music instance = new Music();

	/**
	 * Creates a new {@link Music}
	 */
	private Music () {
		super("Musics", "dbmusic.dat", true, 0, 1, 0, 2, 0, 1, 3, 125, 175);
	}
	
	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, new FieldStruct("Another name", Type.STRING, 56),
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Time", Type.FLOAT), FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNUSED_INT1
		};
		newEntryValues = new Object[]{
				0, -1, "<New music>", "<New music>", 0f, 0f, 0f, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 204
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
