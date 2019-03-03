package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datmanager.Core;
import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbcivpowers.dat
 *
 * @author MarcoForlini
 */
public class CivPower extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final CivPower instance = new CivPower();

	/**
	 * Creates a new {@link CivPower}
	 */
	private CivPower() {
		super("Powers", "dbcivpowers.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		if (!Core.isAOC()) { // This file has been added in AOC
			throw new IllegalStateException("Vanilla game doesn't have powers");
		}
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
