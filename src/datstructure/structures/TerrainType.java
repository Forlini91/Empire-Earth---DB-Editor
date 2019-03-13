package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbterraintype.dat
 *
 * @author MarcoForlini
 */
public class TerrainType extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final TerrainType instance = new TerrainType();

	/**
	 * Creates a new {@link TerrainType}
	 */
	private TerrainType() {
		super("Terrain type", "dbterraintype.dat", true, 0, 1, 0, 0, 1, 2, 5, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New terrain type>", -1, -1, 0, -1, 0, 0, 0
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
