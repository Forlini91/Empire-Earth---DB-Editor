package gui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JCheckBox;


/**
 * JListExtended which support 2 lists: a provided JCheckBox can alternate between them at will.
 *
 * @author MarcoForlini
 * @param <E> Type of elements
 */
public class JListFilter <E> extends JListExtended <E> {

	private static final long	serialVersionUID	= -1460528354644591567L;

	/** A JCheckBox which allow the user to toggle the filter. */
	public JCheckBox			switchFilter;

	Predicate <E>				filter;


	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param filter The method to filter the values
	 */
	public JListFilter (Predicate <E> filter) {
		this (false, filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param allowMove Allow the user to change the order of elements
	 * @param filter The method to filter the values
	 */
	public JListFilter (boolean allowMove, Predicate <E> filter) {
		this (new ArrayList <> (0), allowMove, e -> true);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list The list of elements
	 * @param filter The method to filter the values
	 */
	public JListFilter (List <E> list, Predicate <E> filter) {
		this (list, false, filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list The list of elements
	 * @param allowMove Allow the user to change the order of elements
	 * @param filter The method to filter the values
	 */
	public JListFilter (List <E> list, boolean allowMove, Predicate <E> filter) {
		this (list, allowMove, "Filter elements", true, filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list The list of elements
	 * @param swicthText Text of the "switch filter" JCheckBox
	 * @param filterInitialState Initial state of the "switch filter" JCheckBox
	 * @param filter The method to filter the values
	 */
	public JListFilter (List <E> list, String swicthText, boolean filterInitialState, Predicate <E> filter) {
		this (list, false, swicthText, filterInitialState, filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list The list of elements
	 * @param allowMove Allow the user to change the order of elements
	 * @param swicthText Text of the "switch filter" JCheckBox
	 * @param filterInitialState Initial state of the "switch filter" JCheckBox
	 * @param filter The method to filter the values
	 */
	public JListFilter (List <E> list, boolean allowMove, String swicthText, boolean filterInitialState, Predicate <E> filter) {
		this (list, allowMove, new JCheckBoxExtended (swicthText, filterInitialState), filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list The list of elements
	 * @param switchFilter The "switch filter" checkbox
	 * @param filter The method to filter the values
	 */
	public JListFilter (List <E> list, JCheckBox switchFilter, Predicate <E> filter) {
		this (list, false, switchFilter, filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list The list of elements
	 * @param allowMove Allow the user to change the order of elements
	 * @param switchFilter The "switch filter" checkbox
	 * @param filter The method to filter the values
	 */
	public JListFilter (List <E> list, boolean allowMove, JCheckBox switchFilter, Predicate <E> filter) {
		super (list, allowMove);
		this.switchFilter = switchFilter;
		this.filter = filter;
		switchFilter.addActionListener (e -> refresh ());
		refresh ();
	}


	@Override
	public void refresh () {
		if (switchFilter != null) {
			if (filter != null && switchFilter.isSelected ()) {
				E elem = getSelectedElement ();
				model.clear ();
				model.addIf (list, filter);
				setSelectedElement (elem);
			} else {
				super.refresh ();
			}
		}
	}

}
