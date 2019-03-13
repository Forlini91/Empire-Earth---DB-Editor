package gui.components;

import java.util.List;
import java.util.function.Predicate;

import datstructure.LocalizedObject;

public abstract class JListLocalized<E extends LocalizedObject> extends JListFilter<E> {

	private static final long serialVersionUID = 7813462806469757839L;

	public final JCheckBoxExtended localizeToggle;

	/**
	 * Create a new JListLocalized
	 *
	 * @param list               The list of elements
	 * @param allowMove          Allow the user to change the order of elements
	 * @param filterInitialState Initial state of the "switch filter" JCheckBox
	 * @param filter             The method to filter the values
	 */
	public JListLocalized(List<E> list, boolean allowMove, boolean filterInitialState, Predicate<E> filter) {
		super(list, allowMove, "Hide undefined", filterInitialState, filter);
		localizeToggle = new JCheckBoxExtended("Localize", false);
		localizeToggle.addActionListener(e -> refresh());
		setCellRenderer(new LocalizedListCellRenderer<E>(localizeToggle));
	}

}
