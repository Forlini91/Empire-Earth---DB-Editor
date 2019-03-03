package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbcliffterrain.dat
 *
 * @author MarcoForlini
 */
public class CliffTerrain extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final CliffTerrain instance = new CliffTerrain();

	/**
	 * Creates a new {@link CliffTerrain}
	 */
	private CliffTerrain() {
		super("Cliff terrain", "dbcliffterrain.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New cliff terrain>", 0, -1, 0, 0, 0, 1
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
