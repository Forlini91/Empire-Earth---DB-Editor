package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbuicontrols.dat
 *
 * @author MarcoForlini
 */
public class UIControls extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIControls instance = new UIControls();

	/**
	 * Creates a new {@link UIControls}
	 */
	private UIControls() {
		super("UI Controls", "dbuicontrols.dat", true, 0, 1, 0, 0, 18, 19, 4, 125, 175);
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
