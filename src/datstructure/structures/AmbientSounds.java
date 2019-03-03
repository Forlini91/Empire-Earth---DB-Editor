package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbambientsounds.dat
 *
 * @author MarcoForlini
 */
public class AmbientSounds extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final AmbientSounds instance = new AmbientSounds();

	/**
	 * Creates a new {@link AmbientSounds}
	 */
	private AmbientSounds() {
		super("Ambient sounds", "dbambientsounds.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		newEntryValues = new Object[] {
				"<New ambient sound>", 0, -1, -1, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0, 0f,
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
