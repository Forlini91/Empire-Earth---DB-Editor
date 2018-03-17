package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbuiback.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class UIBack extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIBack instance = new UIBack ();

	/**
	 * Creates a new {@link UIBack}
	 */
	private UIBack () {
		super ("UI Back", "dbuiback.dat", true, 0, 1, 0, 0, 9, 10, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, new FieldStruct ("Texture atlas", FieldType.STRING, 100), new FieldStruct ("Screen height from...", FieldType.FLOAT), new FieldStruct ("... to", FieldType.FLOAT),
				new FieldStruct ("Screen width from... ", FieldType.FLOAT), new FieldStruct ("... to", FieldType.FLOAT), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_UI_FORM,
				new FieldStruct ("Min Y pixel coord", FieldType.INTEGER), new FieldStruct ("Min X pixel coord", FieldType.INTEGER), new FieldStruct ("Max (inclusive) Y pixel coord", FieldType.INTEGER), new FieldStruct ("Max (inclusive) X pixel coord", FieldType.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct ("X axis tiles", FieldType.INTEGER), new FieldStruct ("Y axis tiles", FieldType.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT4,
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
