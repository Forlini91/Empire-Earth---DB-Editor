package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbupgrade.dat
 *
 * @author MarcoForlini
 */
public class Upgrade extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Upgrade instance = new Upgrade();

	/**
	 * Creates a new {@link Upgrade}
	 */
	private Upgrade() {
		super("Upgrades", "dbupgrade.dat", true, 0, 1, 1, 0, 31, 32, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New upgrade>", 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0, 0f, 0, 0f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0, 0f, 0f, 0, 0, 0,
				0, 0, 0, 0
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
