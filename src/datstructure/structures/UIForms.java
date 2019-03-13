package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbuiforms.dat
 *
 * @author MarcoForlini
 */
public class UIForms extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIForms instance = new UIForms();

	/**
	 * Creates a new {@link UIForms}
	 */
	private UIForms() {
		super("UI Forms", "dbuiforms.dat", true, 0, 0, 0, 0, 2, 3, -1, 4, 125, 175); // TODO: Check this
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
