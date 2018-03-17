package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbmusic.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Music extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Music instance = new Music ();

	/**
	 * Creates a new {@link Music}
	 */
	private Music () {
		super ("Musics", "dbmusic.dat", true, 0, 1, 0, 2, 0, 1, 3, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, new FieldStruct ("Another name", FieldType.STRING, 56),
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, new FieldStruct ("Time", FieldType.FLOAT), FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNUSED_INT1
		};
		newEntryValues = new Object[] {
				0, -1, "<New music>", "<New music>", 0f, 0f, 0f, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 204
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
