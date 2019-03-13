package datstructure.structures;

import java.io.IOException;
import java.util.List;

import constants.WorldID;
import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbworld.dat
 *
 * @author MarcoForlini
 */
public class World extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final World instance = new World();

	/**
	 * Creates a new {@link World}
	 */
	private World() {
		super("World", "dbworld.dat", true, 0, 1, 0, -1, 2, 3, -1, 2, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		nameBuilder = (entry) -> {
			final WorldID wID = WorldID.parse(entry.getID());
			return (wID != null) ? wID.name : "<Unknown>";
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
