package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbevents.dat
 *
 * @author MarcoForlini
 */
public class Events extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Events instance = new Events();

	/**
	 * Creates a new {@link Events}
	 */
	private Events() {
		super("Events", "dbevents.dat", false, 0, 1, 1, 0, 1, -1, -1, 2, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		extraField = DatStructure.getCommonField("EVENT_EXTRA_FIELD"); // new FieldStruct("Effect", Effects.instance, 0);
		newEntryValues = new Object[] {
				"<New event>", 0, 0
		};
	}

	@Override
	public int indexExtraFields() {
		return fieldStructs.length - 1;
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
