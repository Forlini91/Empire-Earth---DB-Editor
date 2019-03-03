package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbcolortable.dat
 *
 * @author MarcoForlini
 */
public class ColorTable extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final ColorTable instance = new ColorTable();

	/**
	 * Creates a new {@link ColorTable}
	 */
	private ColorTable() {
		super("Color table", "dbcolortable.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	// COMPLETED
	@Override
	public void customInit() throws IOException {
	}

	@Override
	public int indexExtraFields() {
		return -1;
	}

	@Override
	public boolean hasCustomEntryName() {
		return false;
	}

	@Override
	public String getCustomEntryName(int index, List<Object> values) {
		return null;
	}

	@Override
	public String getEntryDescription(Entry entry) {
		return null;
	}

}
