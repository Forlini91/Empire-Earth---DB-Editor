package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datmanager.Language;
import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbcpbehavior.dat
 *
 * @author MarcoForlini
 */
public class CPBehavior extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final CPBehavior instance = new CPBehavior();

	/**
	 * Creates a new {@link CPBehavior}
	 */
	private CPBehavior() {
		super("CP Behavior", "dbcpbehavior.dat", true, 0, 1, 0, -1, 0, 1, 3, 125, 175);
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
		return true;
	}

	@Override
	public String getCustomEntryName(int index, List<Object> values) {
		final Language l = Language.getMap().get(28000 + index);
		return l != null ? l.text : "<Undefined>";
	}

	@Override
	public String getEntryDescription(Entry entry) {
		final Language l = Language.getMap().get(28500 + entry.getID());
		return l != null ? l.text : "";
	}

}
