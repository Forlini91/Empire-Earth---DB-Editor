package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;

/**
 * Represents the file dbaiunittargeting.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class AIUnitTargeting extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final AIUnitTargeting instance = new AIUnitTargeting();
	
	/**
	 * Creates a new {@link AIUnitTargeting}
	 */
	private AIUnitTargeting () {
		super("AI Unit Targeting", "dbaiunittargeting.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNCHANGED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
	}
	
	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
