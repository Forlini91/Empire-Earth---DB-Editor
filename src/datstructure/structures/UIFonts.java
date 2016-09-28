package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbuifonts.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class UIFonts extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIFonts instance = new UIFonts();

	/**
	 * Creates a new {@link UIFonts}
	 */
	private UIFonts () {
		super("UI Fonts", "dbuifonts.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}
	
	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Font type", Type.INTEGER),
				new FieldStruct("Font size", Type.INTEGER), new FieldStruct("Quality", Type.INTEGER), new FieldStruct("Bold"), new FieldStruct("Italic"),
				new FieldStruct("Underline"), new FieldStruct("Has shadow"), FieldStruct.UNUSED_INT4
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
