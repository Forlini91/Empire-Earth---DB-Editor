package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbuiforms.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class UIForms extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIForms instance = new UIForms ();

	/**
	 * Creates a new {@link UIForms}
	 */
	private UIForms () {
		super ("UI Forms", "dbuiforms.dat", true, 0, 0, 0, 0, 2, 3, 4, 125, 175); // TODO: Check this
	}

	@Override
	public void init () {
		// TODO WIP
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, new FieldStruct ("Sound path", FieldType.STRING, 100), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				new FieldStruct ("Controls", FieldType.INTEGER), new FieldStruct ("Tabs", FieldType.INTEGER), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, new FieldStruct ("Viewport Width", FieldType.INTEGER), new FieldStruct ("Viewport Height", FieldType.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1
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
