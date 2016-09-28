package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbanimals.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Animals extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Animals instance = new Animals();

	/**
	 * Creates a new {@link Animals}
	 */
	private Animals () {
		super("Animals", "dbanimals.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_OBJECT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Min gestation time", Type.INTEGER),
				new FieldStruct("Max gestation time", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Min wander range", Type.INTEGER), new FieldStruct("Max wander range", Type.INTEGER),
				new FieldStruct("Min wander wait time", Type.INTEGER), new FieldStruct("Max wander wait time", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Newborn size", Type.FLOAT), new FieldStruct("Newborn grow rate", Type.FLOAT), new FieldStruct("Max family members", Type.INTEGER), FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Max number of animals who can produce offspring", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		};
		newEntryValues = new Object[]{
				"<New animal>", 0, -1, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0f,
				0, 0, 0, 0, 0, 0, 0, 0,
				0f, 0f, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0,
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
