package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbcolortable.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class ColorTable extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final ColorTable instance = new ColorTable ();

	/**
	 * Creates a new {@link ColorTable}
	 */
	private ColorTable () {
		super ("Color table", "dbcolortable.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	// COMPLETED
	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Player - Red", FieldType.FLOAT),
				new FieldStruct ("Player - Green", FieldType.FLOAT), new FieldStruct ("Player - Blue", FieldType.FLOAT), new FieldStruct ("Model - Red", FieldType.FLOAT), new FieldStruct ("Model - Green", FieldType.FLOAT),
				new FieldStruct ("Model - Blue", FieldType.FLOAT), new FieldStruct ("Lighting - Red", FieldType.FLOAT), new FieldStruct ("Lighting - Green", FieldType.FLOAT), new FieldStruct ("Lighting - Blue", FieldType.FLOAT),
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
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
