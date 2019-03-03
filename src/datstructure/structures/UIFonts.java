package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbuifonts.dat
 *
 * @author MarcoForlini
 */
public class UIFonts extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIFonts instance = new UIFonts();

	/**
	 * Creates a new {@link UIFonts}
	 */
	private UIFonts() {
		super("UI Fonts", "dbuifonts.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
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
