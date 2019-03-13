package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbuihotkey.dat
 *
 * @author MarcoForlini
 */
public class UIHotkey extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIHotkey instance = new UIHotkey();

	/**
	 * Creates a new {@link UIHotkey}
	 */
	private UIHotkey() {
		super("UI Hotkeys", "dbuihotkey.dat", true, 0, 1, 0, 2, 0, 1, -1, 4, 125, 175);
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
