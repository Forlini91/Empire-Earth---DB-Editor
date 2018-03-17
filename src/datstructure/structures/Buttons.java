package datstructure.structures;

import java.util.List;

import constants.ButtonPosition;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbbuttons.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Buttons extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Buttons instance = new Buttons ();

	/**
	 * Creates a new {@link Buttons}
	 */
	private Buttons () {
		super ("Buttons", "dbbuttons.dat", true, 0, 1, 0, 0, 2, 3, 2, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, new FieldStruct ("Texture", FieldType.STRING, 100), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				new FieldStruct ("<only used by espionage center>", FieldType.INTEGER), new FieldStruct ("<only used by farm and espionage center>", FieldType.INTEGER), new FieldStruct ("Position", ButtonPosition.values ()), FieldStruct.UNKNOWN_INT4
		};
		newEntryValues = new Object[] {
				"<New button>", "textures\\zut_smileyface_00T", 0, -1, 0, 0, 0, -1
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
