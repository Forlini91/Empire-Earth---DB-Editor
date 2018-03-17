package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbuifonts.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class UIFonts extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIFonts instance = new UIFonts ();

	/**
	 * Creates a new {@link UIFonts}
	 */
	private UIFonts () {
		super ("UI Fonts", "dbuifonts.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Font type", FieldType.INTEGER),
				new FieldStruct ("Font size", FieldType.INTEGER), new FieldStruct ("Quality", FieldType.INTEGER), new FieldStruct ("Bold"), new FieldStruct ("Italic"),
				new FieldStruct ("Underline"), new FieldStruct ("Has shadow"), FieldStruct.UNUSED_INT4
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
