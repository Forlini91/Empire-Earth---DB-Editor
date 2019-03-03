package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbunitbehavior.dat
 *
 * @author MarcoForlini
 */
public class UnitBehavior extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UnitBehavior instance = new UnitBehavior();

	/**
	 * Creates a new {@link UnitBehavior}
	 */
	private UnitBehavior() {
		super("Unit behavior", "dbunitbehavior.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}

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
