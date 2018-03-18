package gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import datmanager.Settings;
import datstructure.Entry;


/**
 * A JList which can hold the entries
 *
 * @author MarcoForlini
 */
public class JListEntry extends JListDouble <Entry> {

	private static final long	serialVersionUID	= -1460528354644591567L;

	/** The entries can be moved */
	public boolean				allowMove			= false;
	public boolean				searching			= false;

	/**
	 * Create a new JListEntry
	 */
	public JListEntry () {
		this (new ArrayList <> (0));
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param array The array of elements
	 */
	public JListEntry (Entry[] array) {
		this (Arrays.asList (array), true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 */
	public JListEntry (List <Entry> list) {
		this (list, buildListClean (list), true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 * @param hideUnused The "hide" checkbox state
	 */
	public JListEntry (List <Entry> list, boolean hideUnused) {
		this (list, buildListClean (list), hideUnused);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 * @param listClean The list of not-undefined elements
	 */
	public JListEntry (List <Entry> list, List <Entry> listClean) {
		this (list, listClean, true);
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 * @param listClean The list of not-undefined elements
	 * @param hideUnused The "hide" checkbox state
	 */
	public JListEntry (List <Entry> list, List <Entry> listClean, boolean hideUnused) {
		this (list, listClean, new JCheckBoxExtended ("Hide undefined entries", hideUnused));
	}

	/**
	 * Create a new JListEntry
	 *
	 * @param list The list of elements
	 * @param listClean The list of not-undefined elements
	 * @param switchList The "hide" checkbox
	 */
	public JListEntry (List <Entry> list, List <Entry> listClean, JCheckBox switchList) {
		super (list, listClean, switchList);
		registerKeyboardAction ( (e) -> moveUp (e), KeyStroke.getKeyStroke (KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_FOCUSED);
		registerKeyboardAction ( (e) -> moveUp (e), KeyStroke.getKeyStroke (KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK), JComponent.WHEN_FOCUSED);
		registerKeyboardAction ( (e) -> moveDown (e), KeyStroke.getKeyStroke (KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_FOCUSED);
		registerKeyboardAction ( (e) -> moveDown (e), KeyStroke.getKeyStroke (KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK), JComponent.WHEN_FOCUSED);
	}

	/**
	 * Move an element up, if allowed
	 *
	 * @param e The action event
	 */
	public void moveUp (ActionEvent e) {
		if (Settings.DEBUG) {
			System.out.println ("Move up " + getSelectedValue ());
		}
		if (allowMove && !searching && hasFocus ()) {
			int amount = (e.getModifiers () & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK ? 10 : 1;
			int index = getSelectedIndex ();
			if (switchList.isSelected ()) {
				Entry sel = listClean.get (index);
				index = list.indexOf (sel);
			}
			if (index >= 0) {
				if (index - amount < 0) {
					amount = index;
				}
				Entry sel = list.get (index);
				while (amount > 0) {
					Entry selUp = list.get (index - 1);
					list.set (index, selUp);
					list.set (index - 1, sel);
					index--;
					amount--;
				}
				listClean = buildListClean (list);
				refresh ();
			}
		}
	}

	/**
	 * Move an element down, if allowed
	 *
	 * @param e The action event
	 */
	public void moveDown (ActionEvent e) {
		if (Settings.DEBUG) {
			System.out.println ("Move down " + getSelectedValue ());
		}
		if (allowMove && !searching && hasFocus ()) {
			int amount = (e.getModifiers () & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK ? 10 : 1;
			int index = getSelectedIndex ();
			if (switchList.isSelected ()) {
				Entry sel = listClean.get (index);
				index = list.indexOf (sel);
			}
			if (index >= 0) {
				if (index + amount >= list.size ()) {
					amount = list.size () - index - 1;
				}
				Entry sel = list.get (index);
				while (amount > 0) {
					Entry selUp = list.get (index + 1);
					list.set (index, selUp);
					list.set (index + 1, sel);
					index++;
					amount--;
				}
				listClean = buildListClean (list);
				refresh ();
			}
		}
	}


	@Override
	public void setList (Entry[] newList) {
		list = Arrays.asList (newList);
		listClean = buildListClean (list);
		refresh ();
	}

	@Override
	public void setList (List <Entry> newList) {
		list = newList;
		listClean = buildListClean (list);
		refresh ();
	}


	/**
	 * Build and return a "clean" list: a list without undefined fields.
	 *
	 * @param list The list of elements
	 * @return The list of not-undefined elements
	 */
	public static List <Entry> buildListClean (List <Entry> list) {
		int n = list.size ();
		List <Entry> newListClean = new ArrayList <> ();
		Entry entry;
		for (int i = 0; i < n; i++) {
			entry = list.get (i);
			if (entry.isDefined ()) {
				newListClean.add (list.get (i));
			}
		}
		return newListClean;
	}

	@Override
	public void refresh () {
		super.refresh ();
		allowMove = list.size () > 0 && list.get (0).datStructure.newEntryValues != null;
	}


	/**
	 * Select the element in the mouse event location
	 *
	 * @param e The mouse event
	 */
	public void selectElement (MouseEvent e) {
		int selected = locationToIndex (e.getPoint ());
		if (selected >= 0) {
			setSelectedIndex (selected);
		}
	}

}
