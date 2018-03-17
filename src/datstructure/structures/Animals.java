package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbanimals.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Animals extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Animals instance = new Animals ();

	/**
	 * Creates a new {@link Animals}
	 */
	private Animals () {
		super ("Animals", "dbanimals.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_OBJECT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct ("Min gestation time", FieldType.INTEGER),
				new FieldStruct ("Max gestation time", FieldType.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct ("Min wander range", FieldType.INTEGER), new FieldStruct ("Max wander range", FieldType.INTEGER),
				new FieldStruct ("Min wander wait time", FieldType.INTEGER), new FieldStruct ("Max wander wait time", FieldType.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct ("Newborn size", FieldType.FLOAT), new FieldStruct ("Newborn grow rate", FieldType.FLOAT), new FieldStruct ("Max family members", FieldType.INTEGER), FieldStruct.UNKNOWN_INT4,
				new FieldStruct ("Max number of animals who can produce offspring", FieldType.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		};
		newEntryValues = new Object[] {
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
