package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbunitset.dat
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.COMPLETE, AOC = ParseState.COMPLETE)
public class UnitSet extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UnitSet instance = new UnitSet ();

	/**
	 * Creates a new {@link UnitSet}
	 */
	private UnitSet () {
		super ("Unit sets", "dbunitset.dat", true, 0, 1, 0, 0, 1, 2, 3, 150, 200);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_FAMILY,
				ID_FAMILY, ID_FAMILY, ID_FAMILY, ID_FAMILY,
				ID_FAMILY, ID_FAMILY, ID_FAMILY, ID_OBJECT,
				ID_OBJECT, ID_OBJECT, ID_OBJECT, ID_OBJECT,
				ID_OBJECT, ID_OBJECT, ID_OBJECT, new FieldStruct ("Include unit set...", UnitSet.instance, 0),
				new FieldStruct ("Exclude unit set", UnitSet.instance, 0), new FieldStruct ("Is the first set in the list", Type.INTEGER)
		};
		newEntryValues = new Object[] {
				"<New unit set>", 0, -1, 0, 0, 0, 0, 0,
				0, 0, 0, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, 0
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
