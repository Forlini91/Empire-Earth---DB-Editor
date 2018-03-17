package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbstartingresources.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.COMPLETE, AOC = ParseState.COMPLETE)
public class StartingResourches extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final StartingResourches instance = new StartingResourches ();

	/**
	 * Creates a new {@link StartingResourches}
	 */
	private StartingResourches () {
		super ("Starting resourches", "dbstartingresources.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.ID_LANGUAGE,
				new FieldStruct ("Starting food", FieldType.INTEGER), new FieldStruct ("Starting wood", FieldType.INTEGER), new FieldStruct ("Starting stone", FieldType.INTEGER), new FieldStruct ("Starting gold", FieldType.INTEGER),
				new FieldStruct ("Starting iron", FieldType.INTEGER)
		};
		newEntryValues = new Object[] {
				"<New starting resourches>", -1, -1, -1, 600, 600, 200, 400, 400
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
