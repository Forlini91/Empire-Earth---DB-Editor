package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;

/**
 * Represents the file dbcpbehavior.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class CPBehavior extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final CPBehavior instance = new CPBehavior();
	
	/**
	 * Creates a new {@link CPBehavior}
	 */
	private CPBehavior () {
		super("CP Behavior", "dbcpbehavior.dat", true, 0, 1, 0, -1, 0, 1, 4, 125, 175);
	}
	
	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
