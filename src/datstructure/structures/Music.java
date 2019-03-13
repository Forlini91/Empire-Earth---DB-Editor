package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbmusic.dat
 *
 * @author MarcoForlini
 */
public class Music extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Music instance = new Music();

	/**
	 * Creates a new {@link Music}
	 */
	private Music() {
		super("Musics", "dbmusic.dat", true, 0, 1, 0, 2, 0, 1, -1, 3, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				0, -1, "<New music>", "<New music>", 0f, 0f, 0f, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 204
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
