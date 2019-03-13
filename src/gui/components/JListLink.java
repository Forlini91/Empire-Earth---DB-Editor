package gui.components;

import java.util.ArrayList;
import java.util.List;

import datstructure.Link;

/**
 * A JList which can hold the entries
 *
 * @author MarcoForlini
 */
public class JListLink extends JListLocalized<Link> {

	private static final long serialVersionUID = -1460528354644591567L;

	/**
	 * Create a new JListEntry
	 */
	public JListLink() {
		this(false);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param allowMove Allow the user to change the order of elements
	 */
	public JListLink(boolean allowMove) {
		this(new ArrayList<>(0), allowMove, true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 */
	public JListLink(List<Link> list) {
		this(list, false);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list      The list of elements
	 * @param allowMove Allow the user to change the order of elements
	 */
	public JListLink(List<Link> list, boolean allowMove) {
		this(list, allowMove, true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list               The list of elements
	 * @param allowMove          Allow the user to change the order of elements
	 * @param filterInitialState Initial state of the "switch filter" JCheckBox
	 */
	public JListLink(List<Link> list, boolean allowMove, boolean filterInitialState) {
		super(list, allowMove, filterInitialState, Link::isValid);
	}

}
