package datstructure.structures;

import java.util.List;

import constants.WorldID;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbworld.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class World extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final World instance = new World ();

	/**
	 * Creates a new {@link World}
	 */
	private World () {
		super ("World", "dbworld.dat", true, 0, 1, 0, -1, 2, 3, 2, 125, 175);
	}

	@Override
	public void init () {
		nameBuilder = (entry) -> {
			WorldID wID = WorldID.C00_NULL.parseValue (entry.getID ());
			return (wID != null) ? wID.name : "<Unknown>";
		};
		fieldStructs = new FieldStruct[] {
				new FieldStruct ("Float/Min float", FieldType.FLOAT), new FieldStruct ("Max float", FieldType.FLOAT), FieldStruct.SEQ_NUMBER, new FieldStruct ("ID", WorldID.values (), false),
				new FieldStruct ("Int/Min int", FieldType.INTEGER), new FieldStruct ("Max int", FieldType.INTEGER), FieldStruct.UNKNOWN_INT4
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
