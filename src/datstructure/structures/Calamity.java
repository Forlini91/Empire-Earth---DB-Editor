package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbcalamity.dat
 *
 * @author MarcoForlini
 */
public class Calamity extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Calamity instance = new Calamity();

	/**
	 * Creates a new {@link Calamity}
	 */
	private Calamity() {
		super("Calamities", "dbcalamity.dat", true, 0, 1, 0, 0, 7, 8, 39, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New calamity>", 0f, 0f, 0f, 0f, 0f, 0f, 0,
				0, 0, 0, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, -1, -1, 0, 0, 0,
				0
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
