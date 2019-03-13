package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datmanager.Core;
import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbcivilization.dat
 *
 * @author MarcoForlini
 */
public class Civilization extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Civilization instance = new Civilization();

	/**
	 * Creates a new {@link Civilization}
	 */
	private Civilization() {
		super("Civilizations", "dbcivilization.dat", true, 0, 0, 1, 2, 0, 1, 3, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		if (!Core.isAOC()) { // File structure has been changed in AOC
			newEntryValues = new Object[] {
					0, -1, "<New civilization>", 0, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
			};
		} else {
			newEntryValues = new Object[] {
					0, -1, "<New civilization>", 0, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f,
			};
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
