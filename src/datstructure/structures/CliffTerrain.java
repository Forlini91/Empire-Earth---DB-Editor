package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;

/**
 * Represents the file dbcliffterrain.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class CliffTerrain extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final CliffTerrain instance = new CliffTerrain();

	/**
	 * Creates a new {@link CliffTerrain}
	 */
	private CliffTerrain () {
		super("Cliff terrain", "dbcliffterrain.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}
	
	//COMPLETED
	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_TERRAIN,
				ID_TERRAIN, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		newEntryValues = new Object[]{
				"<New cliff terrain>", 0, -1, 0, 0, 0, 1
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
