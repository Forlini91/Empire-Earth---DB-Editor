package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;

/**
 * Represents the file dbsounds.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Sounds extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final Sounds instance = new Sounds();

	/**
	 * Creates a new {@link Sounds}
	 */
	private Sounds () {
		super("Sounds", "dbsounds.dat", true, 0, 1, 0, 2, 3, 4, 4, 125, 175);
	}
	
	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.STRING_SIZE, new FieldStruct("Pathname", 0), FieldStruct.NAME, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4
		};
		newEntryValues = new Object[]{
				0, "", "<New sound>", 0, -1, 0, 0, 0,
				0, 0, 0
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
