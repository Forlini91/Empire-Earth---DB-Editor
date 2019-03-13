package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datmanager.Core;
import datstructure.DatStructure;
import datstructure.Entry;

/**
 * Represents the file dbtechtree.dat
 *
 * @author MarcoForlini
 */
public class TechTree extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final TechTree instance = new TechTree();

	/**
	 * Creates a new {@link TechTree}
	 */
	private TechTree() {
		super("Technologies", "dbtechtree.dat", true, 1, 1, 0, 0, 1, 2, 22, 4, 175, 225);
	}

	@Override
	public void customInit() throws IOException {
		extraField = DatStructure.getCommonField("ID_TECH_FROM_OBJECT");
		if (!Core.isAOC()) { // File structure has been changed in AOC
			newEntryValues = new Object[] {
					"<New technology>", -1, 0, 0, 15, -1, -1, 0,
					0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, -1, -1, 0, 0, 0,
					0, 0, 0, 0, -1, 0, 0, 0,
					0, 0f, 0f, 0f, 0f, 0, 0, 1,
					1, 0, 0, 0, -1, 0
			};

		} else {
			newEntryValues = new Object[] {
					"<New technology>", -1, 0, 0, 15, -1, -1, 0,
					0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, -1, -1, 0, 0, 0,
					0, 0, 0, 0, -1, 0, 0, 0,
					0, 0f, 0f, 0f, 0f, 0, 0, 1,
					1, 0, 0, 0, 0, -1, 0
			};
		}
	}

	@Override
	public int indexExtraFields() {
		return fieldStructs.length - 1;
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
