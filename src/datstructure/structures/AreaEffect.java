package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbdbareaeffect.dat
 *
 * @author MarcoForlini
 */
public class AreaEffect extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final AreaEffect instance = new AreaEffect();

	/**
	 * Creates a new {@link AreaEffect}
	 */
	private AreaEffect() {
		super("Area effects", "dbareaeffect.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New area effect>", 0, -1, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0f,
				0f, 0f, 0f, 0f, 0, 0, 0
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
