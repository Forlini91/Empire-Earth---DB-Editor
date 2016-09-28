package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbbuttons.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class Buttons extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Buttons instance = new Buttons();

	/**
	 * Creates a new {@link Buttons}
	 */
	private Buttons () {
		super("Buttons", "dbbuttons.dat", true, 0, 1, 0, 0, 2, 3, 2, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Texture", Type.STRING, 100), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				new FieldStruct("<only used by espionage center>", Type.INTEGER), new FieldStruct("<only used by farm and espionage center>", Type.INTEGER), new FieldStruct("Position", Type.INTEGER), FieldStruct.UNKNOWN_INT4
		};
		newEntryValues = new Object[]{
				"<New button>", "textures\\zut_smileyface_00T", 0, -1, 0, 0, 0, -1
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
