package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

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
				FieldStruct.NAME, new FieldStruct ("Sound path", Type.STRING, 100), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				new FieldStruct ("Controls", Type.INTEGER), new FieldStruct ("Tabs", Type.INTEGER), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, new FieldStruct ("Viewport Width", Type.INTEGER), new FieldStruct ("Viewport Height", Type.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
