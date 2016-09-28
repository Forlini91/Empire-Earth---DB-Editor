package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbuicontrols.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class UIControls extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final UIControls instance = new UIControls();
	
	/**
	 * Creates a new {@link UIControls}
	 */
	private UIControls () {
		super("UI Controls", "dbuicontrols.dat", true, 0, 1, 0, 0, 18, 19, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Sub", Type.STRING, 100), new FieldStruct("Sub", Type.STRING, 100), new FieldStruct("Sub", Type.STRING, 100),
				new FieldStruct("Sub", Type.STRING, 100), new FieldStruct("Sub", Type.STRING, 100), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, new FieldStruct("Screen height from...", Type.FLOAT), new FieldStruct("... to", Type.FLOAT),
				new FieldStruct("Screen width from... ", Type.FLOAT), new FieldStruct("... to", Type.FLOAT), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				ID_UI_FORM, new FieldStruct("Belongs to tab (0=all)", Type.INTEGER), new FieldStruct("Type", Type.INTEGER), new FieldStruct("Control index in form", Type.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Red", Type.INTEGER), new FieldStruct("Green", Type.INTEGER), new FieldStruct("Blue", Type.INTEGER),
				FieldStruct.ID_LANGUAGE, FieldStruct.ID_LANGUAGE, ID_UI_FONT, new FieldStruct("Red", Type.INTEGER),
				new FieldStruct("Green", Type.INTEGER), new FieldStruct("Blue", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Red", Type.INTEGER), new FieldStruct("Green", Type.INTEGER), new FieldStruct("Blue", Type.INTEGER), FieldStruct.UNUSED_INT4,
				new FieldStruct("Red", Type.INTEGER), new FieldStruct("Green", Type.INTEGER), new FieldStruct("Blue", Type.INTEGER), FieldStruct.UNKNOWN_INT4,
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
	
}
