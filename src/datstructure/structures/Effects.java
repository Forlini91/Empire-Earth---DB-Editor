package datstructure.structures;

import java.io.IOException;
import java.util.List;

import constants.EffectCode;
import datmanager.Core;
import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbeffects.dat
 *
 * @author MarcoForlini
 */
public class Effects extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Effects instance = new Effects();

	/**
	 * Creates a new {@link Effects}
	 */
	private Effects() {
		super("Effects", "dbeffects.dat", false, 0, 1, 1, -1, 0, 1, -1, 3, 200, 275);
	}

	@Override
	public void customInit() throws IOException {
		nameBuilder = (entry) -> EffectCode.parse((int) entry.get(8)).nameBuilder.apply(entry);
		if (!Core.isAOC()) { // File structure has been changed in AOC
			newEntryValues = new Object[] {
					0, 0, 0f, 0f, 0f, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, 0,
					0,
			};

		} else {
			newEntryValues = new Object[] {
					0, 0, 0f, 0f, 0f, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, 0,
					0, 0, 0, 0, 0, 0,
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
