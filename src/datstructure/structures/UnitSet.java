package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbunitset.dat
 *
 * @author MarcoForlini
 */
public class UnitSet extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UnitSet instance = new UnitSet();

	/**
	 * Creates a new {@link UnitSet}
	 */
	private UnitSet() {
		super("Unit sets", "dbunitset.dat", true, 0, 1, 0, 0, 1, 2, 3, 150, 200);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New unit set>", 0, -1, 0, 0, 0, 0, 0,
				0, 0, 0, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, 0
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
