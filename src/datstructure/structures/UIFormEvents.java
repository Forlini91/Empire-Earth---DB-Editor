package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbuiformevents.dat
 *
 * @author MarcoForlini
 */
public class UIFormEvents extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIFormEvents instance = new UIFormEvents();

	/**
	 * Creates a new {@link UIFormEvents}
	 */
	private UIFormEvents() {
		super("UI FormsEvents", "dbuiformevents.dat", true, 0, 0, 0, 0, 1, 2, 2, 125, 175);
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
