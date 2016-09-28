package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbuiback.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class UIBack extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final UIBack instance = new UIBack();

	/**
	 * Creates a new {@link UIBack}
	 */
	private UIBack () {
		super("UI Back", "dbuiback.dat", true, 0, 1, 0, 0, 9, 10, 4, 125, 175);
	}
	
	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Texture atlas", Type.STRING, 100), new FieldStruct("Screen height from...", Type.FLOAT), new FieldStruct("... to", Type.FLOAT),
				new FieldStruct("Screen width from... ", Type.FLOAT), new FieldStruct("... to", Type.FLOAT), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_UI_FORM,
				new FieldStruct("Min Y pixel coord" , Type.INTEGER), new FieldStruct("Min X pixel coord", Type.INTEGER), new FieldStruct("Max (inclusive) Y pixel coord", Type.INTEGER), new FieldStruct("Max (inclusive) X pixel coord", Type.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("X axis tiles", Type.INTEGER), new FieldStruct("Y axis tiles", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT4,
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
