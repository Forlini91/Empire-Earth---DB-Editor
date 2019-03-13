package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbterrain.dat
 *
 * @author MarcoForlini
 */
public class Terrain extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Terrain instance = new Terrain();

	/**
	 * Creates a new {@link Terrain}
	 */
	private Terrain() {
		super("Terrain", "dbterrain.dat", true, 0, 1, 0, 2, 3, 4, 6, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				0, "", "<New terrain>", 0, -1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0f, 0f, 0
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
