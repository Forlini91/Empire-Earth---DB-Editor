package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbstartingresources.dat
 *
 * @author MarcoForlini
 */
public class StartingResourches extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final StartingResourches instance = new StartingResourches();

	/**
	 * Creates a new {@link StartingResourches}
	 */
	private StartingResourches() {
		super("Starting resourches", "dbstartingresources.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New starting resourches>", -1, -1, -1, 600, 600, 200, 400, 400
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
