package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;

/**
 * Represents the file dbterraintype.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class TerrainType extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final TerrainType instance = new TerrainType();
	
	/**
	 * Creates a new {@link TerrainType}
	 */
	private TerrainType () {
		super("Terrain type", "dbterraintype.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.ID_LANGUAGE, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		newEntryValues = new Object[]{
				"<New terrain type>", -1, -1, 0, -1, 0, 0, 0
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
