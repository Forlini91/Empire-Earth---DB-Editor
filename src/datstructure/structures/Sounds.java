package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbsounds.dat
 *
 * @author MarcoForlini
 */
public class Sounds extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Sounds instance = new Sounds();

	/**
	 * Creates a new {@link Sounds}
	 */
	private Sounds() {
		super("Sounds", "dbsounds.dat", true, 0, 1, 0, 2, 3, 4, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				0, "", "<New sound>", 0, -1, 0, 0, 0,
				0, 0, 0
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
