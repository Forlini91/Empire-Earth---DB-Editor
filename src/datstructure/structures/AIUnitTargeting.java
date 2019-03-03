package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbaiunittargeting.dat
 *
 * @author MarcoForlini
 */
public class AIUnitTargeting extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final AIUnitTargeting instance = new AIUnitTargeting();

	/**
	 * Creates a new {@link AIUnitTargeting}
	 */
	private AIUnitTargeting() {
		super("AI Unit Targeting", "dbaiunittargeting.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
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
