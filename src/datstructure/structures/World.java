package datstructure.structures;

import constants.WorldID;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbworld.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class World extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final World instance = new World();

	/**
	 * Creates a new {@link World}
	 */
	private World () {
		super("World", "dbworld.dat", true, 0, 1, 0, -1, 2, 3, 2, 125, 175);
	}

	@Override
	public void init () {
		nameBuilder = (entry) -> {
			WorldID wID = WorldID.C00_NULL.parseValue(entry.getID());
			return (wID != null) ? wID.name : "<Unknown>";
		};
		fieldStructs = new FieldStruct[]{
				new FieldStruct("Float/Min float", Type.FLOAT), new FieldStruct("Max float", Type.FLOAT), FieldStruct.SEQ_NUMBER, new FieldStruct("ID", WorldID.values(), false),
				new FieldStruct("Int/Min int", Type.INTEGER), new FieldStruct("Max int", Type.INTEGER), FieldStruct.UNKNOWN_INT4
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
