package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;


/**
 * Represents the file dbunitbehavior.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class UnitBehavior extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UnitBehavior instance = new UnitBehavior ();

	/**
	 * Creates a new {@link UnitBehavior}
	 */
	private UnitBehavior () {
		super ("Unit behavior", "dbunitbehavior.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Run away from enemy"),
				new FieldStruct ("Ignore enemy outside LOS"), new FieldStruct ("Attack enemy"), new FieldStruct ("Follow enemy"), new FieldStruct ("Run away if attacked"),
				new FieldStruct ("Return to initial location"), FieldStruct.UNUSED_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4
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
