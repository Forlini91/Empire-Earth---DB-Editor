package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbuicontrols.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class UIControls extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIControls instance = new UIControls ();

	/**
	 * Creates a new {@link UIControls}
	 */
	private UIControls () {
		super ("UI Controls", "dbuicontrols.dat", true, 0, 1, 0, 0, 18, 19, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, new FieldStruct ("Sub", FieldType.STRING, 100), new FieldStruct ("Sub", FieldType.STRING, 100), new FieldStruct ("Sub", FieldType.STRING, 100),
				new FieldStruct ("Sub", FieldType.STRING, 100), new FieldStruct ("Sub", FieldType.STRING, 100), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, new FieldStruct ("Screen height from...", FieldType.FLOAT), new FieldStruct ("... to", FieldType.FLOAT),
				new FieldStruct ("Screen width from... ", FieldType.FLOAT), new FieldStruct ("... to", FieldType.FLOAT), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				ID_UI_FORM, new FieldStruct ("Belongs to tab (0=all)", FieldType.INTEGER), new FieldStruct ("Type", FieldType.INTEGER), new FieldStruct ("Control index in form", FieldType.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, new FieldStruct ("Red", FieldType.INTEGER), new FieldStruct ("Green", FieldType.INTEGER), new FieldStruct ("Blue", FieldType.INTEGER),
				FieldStruct.ID_LANGUAGE, FieldStruct.ID_LANGUAGE, ID_UI_FONT, new FieldStruct ("Red", FieldType.INTEGER),
				new FieldStruct ("Green", FieldType.INTEGER), new FieldStruct ("Blue", FieldType.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct ("Red", FieldType.INTEGER), new FieldStruct ("Green", FieldType.INTEGER), new FieldStruct ("Blue", FieldType.INTEGER), FieldStruct.UNUSED_INT4,
				new FieldStruct ("Red", FieldType.INTEGER), new FieldStruct ("Green", FieldType.INTEGER), new FieldStruct ("Blue", FieldType.INTEGER), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.ID_LANGUAGE,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT4,
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
