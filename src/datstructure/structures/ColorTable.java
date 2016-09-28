package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbcolortable.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class ColorTable extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final ColorTable instance = new ColorTable();
	
	/**
	 * Creates a new {@link ColorTable}
	 */
	private ColorTable () {
		super("Color table", "dbcolortable.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	//COMPLETED
	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Player - Red", Type.FLOAT),
				new FieldStruct ("Player - Green", Type.FLOAT), new FieldStruct ("Player - Blue", Type.FLOAT), new FieldStruct ("Model - Red", Type.FLOAT), new FieldStruct ("Model - Green", Type.FLOAT),
				new FieldStruct ("Model - Blue", Type.FLOAT), new FieldStruct ("Lighting - Red", Type.FLOAT), new FieldStruct ("Lighting - Green", Type.FLOAT), new FieldStruct ("Lighting - Blue", Type.FLOAT),
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
