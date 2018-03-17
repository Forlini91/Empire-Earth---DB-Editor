package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbevents.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.COMPLETE, AOC = ParseState.COMPLETE)
public class Events extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Events instance = new Events ();

	/**
	 * Creates a new {@link Events}
	 */
	private Events () {
		super ("Events", "dbevents.dat", false, 0, 1, 1, 0, 1, -1, 2, 125, 175);
	}

	@Override
	public void init () {
		extraField = new FieldStruct ("Effect", Effects.instance, 0);
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, new FieldStruct ("Num effects", FieldType.INTEGER, 4, false),
		};
		newEntryValues = new Object[] {
				"<New event>", 0, 0
		};
	}

	@Override
	public int indexExtraFields () {
		return fieldStructs.length - 1;
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
