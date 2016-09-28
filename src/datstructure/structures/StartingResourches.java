package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbstartingresources.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.COMPLETE, AOC = ParseState.COMPLETE)
public class StartingResourches extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final StartingResourches instance = new StartingResourches();
	
	/**
	 * Creates a new {@link StartingResourches}
	 */
	private StartingResourches () {
		super("Starting resourches", "dbstartingresources.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.ID_LANGUAGE,
				new FieldStruct("Starting food", Type.INTEGER), new FieldStruct("Starting wood", Type.INTEGER), new FieldStruct("Starting stone", Type.INTEGER), new FieldStruct("Starting gold", Type.INTEGER),
				new FieldStruct("Starting iron", Type.INTEGER)
		};
		newEntryValues = new Object[]{
				"<New starting resourches>", -1, -1, -1, 600, 600, 200, 400, 400
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
