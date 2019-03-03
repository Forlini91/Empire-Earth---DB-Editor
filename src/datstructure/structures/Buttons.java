package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbbuttons.dat
 *
 * @author MarcoForlini
 */
public class Buttons extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Buttons instance = new Buttons();

	/**
	 * Creates a new {@link Buttons}
	 */
	private Buttons() {
		super("Buttons", "dbbuttons.dat", true, 0, 1, 0, 0, 2, 3, 2, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New button>", "textures\\zut_smileyface_00T", 0, -1, 0, 0, 0, -1
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
