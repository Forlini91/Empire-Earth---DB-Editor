package gui.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JCheckBox;

import datstructure.Entry;


/**
 * A JList which can hold the entries
 *
 * @author MarcoForlini
 */
public class JListEntry extends JListFilter <Entry> {

	private static final long	serialVersionUID	= -1460528354644591567L;

	public Predicate <Entry>	filterOverride		= null;

	/**
	 * Create a new JListEntry
	 */
	public JListEntry () {
		this (false);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param allowMove Allow the user to change the order of elements
	 */
	public JListEntry (boolean allowMove) {
		this (new ArrayList <> (0), allowMove, true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param array The array of elements
	 */
	public JListEntry (Entry[] array) {
		this (array, false);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param array The array of elements
	 * @param allowMove Allow the user to change the order of elements
	 */
	public JListEntry (Entry[] array, boolean allowMove) {
		this (new ArrayList <> (Arrays.asList (array)), allowMove, true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 */
	public JListEntry (List <Entry> list) {
		this (list, false);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 * @param allowMove Allow the user to change the order of elements
	 */
	public JListEntry (List <Entry> list, boolean allowMove) {
		this (list, allowMove, true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 * @param allowMove Allow the user to change the order of elements
	 * @param filterInitialState Initial state of the "switch filter" JCheckBox
	 */
	public JListEntry (List <Entry> list, boolean allowMove, boolean filterInitialState) {
		super (list, allowMove, "Hide undefined entries", filterInitialState, Entry::isDefined);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 * @param allowMove Allow the user to change the order of elements
	 * @param switchFilter The "switch filter" JCheckBox
	 */
	public JListEntry (List <Entry> list, boolean allowMove, JCheckBox switchFilter) {
		super (list, allowMove, switchFilter, Entry::isDefined);
	}


	@Override
	public void refresh () {
		if (filterOverride != null) {
			Entry entry = getSelectedElement ();
			model.clear ();
			model.addIf (list, filterOverride);
			setSelectedElement (entry);
		} else {
			super.refresh ();
		}
	}


	@Override
	public boolean canMove () {
		return allowMove && filterOverride == null && !switchFilter.isSelected () &&
				model.getSize () > 1 && model.getElementAt (0).datStructure.newEntryValues != null;
	}

}
