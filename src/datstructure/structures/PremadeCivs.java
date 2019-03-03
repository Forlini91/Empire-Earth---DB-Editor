package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbpremadecivs.dat
 *
 * @author MarcoForlini
 */
public class PremadeCivs extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final PremadeCivs instance = new PremadeCivs();

	/**
	 * Creates a new {@link PremadeCivs}
	 */
	private PremadeCivs() {
		super("Premade civilizations", "dbpremadecivs.dat", true, 0, 1, 0, 2, 0, 1, 2, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
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
