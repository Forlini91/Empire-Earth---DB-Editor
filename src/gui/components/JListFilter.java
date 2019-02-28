package gui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javax.swing.JCheckBox;

/**
 * JListExtended which support 2 lists: a provided JCheckBox can alternate between them at will.
 *
 * @author MarcoForlini
 * @param <E> Type of elements
 */
public class JListFilter<E> extends JListExtended<E> {

	private static final long serialVersionUID = -1460528354644591567L;

	/** A JCheckBox which allow the user to toggle the filter. */
	public final JCheckBox filterToggle;

	private final Predicate<E> filter;
	public Predicate<E> filterOverride = null;

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param filter The method to filter the values
	 */
	public JListFilter(Predicate<E> filter) {
		this(false, filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param allowMove Allow the user to change the order of elements
	 * @param filter    The method to filter the values
	 */
	public JListFilter(boolean allowMove, Predicate<E> filter) {
		this(new ArrayList<>(0), allowMove, e -> true);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list   The list of elements
	 * @param filter The method to filter the values
	 */
	public JListFilter(List<E> list, Predicate<E> filter) {
		this(list, false, filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list      The list of elements
	 * @param allowMove Allow the user to change the order of elements
	 * @param filter    The method to filter the values
	 */
	public JListFilter(List<E> list, boolean allowMove, Predicate<E> filter) {
		this(list, allowMove, "Filter elements", true, filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list               The list of elements
	 * @param swicthText         Text of the "switch filter" JCheckBox
	 * @param filterInitialState Initial state of the "switch filter" JCheckBox
	 * @param filter             The method to filter the values
	 */
	public JListFilter(List<E> list, String swicthText, boolean filterInitialState, Predicate<E> filter) {
		this(list, false, swicthText, filterInitialState, filter);
	}

	/**
	 * Create a new {@link JListFilter}
	 *
	 * @param list               The list of elements
	 * @param allowMove          Allow the user to change the order of elements
	 * @param swicthText         Text of the "switch filter" JCheckBox
	 * @param filterInitialState Initial state of the "switch filter" JCheckBox
	 * @param filter             The method to filter the values
	 */
	public JListFilter(List<E> list, boolean allowMove, String switchText, boolean filterInitialState, Predicate<E> filter) {
		super(Objects.requireNonNull(list), allowMove);
		this.filter = Objects.requireNonNull(filter);
		this.filterToggle = new JCheckBoxExtended(switchText == null || switchText.isBlank() ? "Apply filter" : switchText, filterInitialState);
		filterToggle.addActionListener(e -> refresh());
		refresh();
	}

	@Override
	public void refresh() {
		if (filterOverride != null) {
			final E elem = getSelectedElement();
			model.clear();
			model.addIf(list, filterOverride);
			setSelectedElement(elem);
		} else if (filter != null && filterToggle.isSelected()) {
			final E elem = getSelectedElement();
			model.clear();
			model.addIf(list, filter);
			setSelectedElement(elem);
		} else {
			super.refresh();
		}
	}

	@Override
	public boolean canMove() {
		return allowMove && filterOverride == null && !filterToggle.isSelected() && model.getSize() > 1;
	}

}
