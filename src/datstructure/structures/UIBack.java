package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbuiback.dat
 *
 * @author MarcoForlini
 */
public class UIBack extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIBack instance = new UIBack();

	/**
	 * Creates a new {@link UIBack}
	 */
	private UIBack() {
		super("UI Back", "dbuiback.dat", true, 0, 1, 0, 0, 9, 10, 4, 125, 175);
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
