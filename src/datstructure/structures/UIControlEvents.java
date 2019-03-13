package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbuicontrolevents.dat
 *
 * @author MarcoForlini
 */
public class UIControlEvents extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIControlEvents instance = new UIControlEvents();

	/**
	 * Creates a new {@link UIControlEvents}
	 */
	private UIControlEvents() {
		super("UI Control events", "dbuicontrolevents.dat", true, 0, 1, 0, 0, 1, 2, -1, 3, 150, 200);
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
