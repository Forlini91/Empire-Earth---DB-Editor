package gui.components;

import java.util.ArrayList;
import java.util.List;

import datstructure.Entry;

/**
 * A JList which can hold the entries
 *
 * @author MarcoForlini
 */
public class JListEntry extends JListLocalized<Entry> {

	private static final long serialVersionUID = -1460528354644591567L;

	/**
	 * Create a new JListEntry
	 */
	public JListEntry() {
		this(false);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param allowMove Allow the user to change the order of elements
	 */
	public JListEntry(boolean allowMove) {
		this(new ArrayList<>(0), allowMove, true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 */
	public JListEntry(List<Entry> list) {
		this(list, false);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list      The list of elements
	 * @param allowMove Allow the user to change the order of elements
	 */
	public JListEntry(List<Entry> list, boolean allowMove) {
		this(list, allowMove, true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list               The list of elements
	 * @param allowMove          Allow the user to change the order of elements
	 * @param filterInitialState Initial state of the "switch filter" JCheckBox
	 */
	public JListEntry(List<Entry> list, boolean allowMove, boolean filterInitialState) {
		super(list, allowMove, filterInitialState, Entry::isDefined);
	}

	@Override
	public boolean canMove() {
		return allowMove && filterOverride == null && !filterToggle.isSelected() && model.getSize() > 1 && model.getElementAt(0).datStructure.newEntryValues != null;
	}

}
