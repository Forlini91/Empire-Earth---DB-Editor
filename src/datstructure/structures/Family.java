package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbfamily.dat
 *
 * @author MarcoForlini
 */
public class Family extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Family instance = new Family();

	/**
	 * Creates a new {@link Family}
	 */
	private Family() {
		super("Families", "dbfamily.dat", true, 0, 1, 0, 0, 1, 2, -1, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New family>", 0, -1, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100,
		};
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
