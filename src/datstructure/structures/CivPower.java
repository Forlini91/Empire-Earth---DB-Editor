package datstructure.structures;

import java.util.List;

import datmanager.Core;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbcivpowers.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.NO_FILE, AOC = ParseState.UNKNOWN_PARSED)
public class CivPower extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final CivPower instance = new CivPower ();

	/**
	 * Creates a new {@link CivPower}
	 */
	private CivPower () {
		super ("Powers", "dbcivpowers.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}

	@Override
	public void init () {
		if (!Core.isAOC ()) { // This file has been added in AOC
			throw new IllegalStateException ("Vanilla game doesn't have powers");
		}

		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Cost", FieldType.FLOAT),
				ID_UNIT_SET, ID_TECH, new FieldStruct ("Set amount", FieldType.INTEGER), new FieldStruct ("GFX Effect", GFXEffects.instance, 0),
				FieldStruct.UNUSED_INT4, new FieldStruct ("Mod amount", FieldType.FLOAT), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, ID_OBJECT
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
