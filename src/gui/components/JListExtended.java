package gui.components;


import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import datmanager.Settings;
import gui.misc.ListDataCollection;


/**
 * JList which keep the reference to the passed list.
 * You can change the list from outside this class, then use the command
 * refresh() to update the list.
 *
 * @author MarcoForlini
 * @param <E> The type of elements
 */
public class JListExtended <E> extends JList <E> implements AbstractJListExtended <E> {

	private static final long	serialVersionUID	= -1460528354644591567L;

	private static KeyStroke	ctrlDownStroke		= KeyStroke.getKeyStroke (KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK);
	private static KeyStroke	ctrlShiftDownStroke	= KeyStroke.getKeyStroke (KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK);
	private static KeyStroke	ctrlUpStroke		= KeyStroke.getKeyStroke (KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK);
	private static KeyStroke	ctrlShiftUpStroke	= KeyStroke.getKeyStroke (KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK);


	/** The list of elements in the list */
	public List <E>	list;

	/** The elements can be moved */
	public boolean	allowMove;


	ListDataCollection <E> model = new ListDataCollection <> ();


	/**
	 * Creates a new {@link JListExtended}
	 */
	public JListExtended () {
		this (new ArrayList <> (), false);
	}


	/**
	 * Creates a new {@link JListExtended}
	 *
	 * @param allowMove The elements can be moved
	 */
	public JListExtended (boolean allowMove) {
		this (new ArrayList <> (), allowMove);
	}


	/**
	 * Creates a new {@link JListExtended}
	 *
	 * @param newList The array of elements
	 */
	public JListExtended (E[] newList) {
		this (newList, false);
	}

	/**
	 * Creates a new {@link JListExtended}
	 *
	 * @param newList The array of elements
	 * @param allowMove The elements can be moved
	 */
	public JListExtended (E[] newList, boolean allowMove) {
		this (new ArrayList <> (Arrays.asList (newList)), allowMove);
	}

	/**
	 * Creates a new {@link JListExtended}
	 *
	 * @param list The list of elements
	 */
	public JListExtended (List <E> list) {
		this (list, false);
	}

	/**
	 * Creates a new {@link JListExtended}
	 *
	 * @param list The list of elements
	 * @param allowMove The elements can be moved
	 */
	public JListExtended (List <E> list, boolean allowMove) {
		this.list = list;
		this.allowMove = allowMove;
		setModel (model);
		setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
		setVisibleRowCount (10);

		registerKeyboardAction ( (e) -> moveUp (1), ctrlUpStroke, JComponent.WHEN_FOCUSED);
		registerKeyboardAction ( (e) -> moveUp (10), ctrlShiftUpStroke, JComponent.WHEN_FOCUSED);
		registerKeyboardAction ( (e) -> moveDown (1), ctrlDownStroke, JComponent.WHEN_FOCUSED);
		registerKeyboardAction ( (e) -> moveDown (10), ctrlShiftDownStroke, JComponent.WHEN_FOCUSED);
		refresh ();
	}


	@Override
	public List <E> getList () {
		return list;
	}

	@Override
	public void setList (E[] newList) {
		setList (new ArrayList <> (Arrays.asList (newList)));
	}

	@Override
	public void setList (List <E> newList) {
		list = newList;
		refresh ();
	}

	@Override
	public int getLength () {
		return model.getSize ();
	}

	@Override
	public E getSelectedElement () {
		return getSelectedValue ();
	}

	@Override
	public void setSelectedElement (E element) {
		setSelectedValue (element, true);
	}

	@Override
	public void setSelectedElement (int index) {
		setSelectedValue (model.getElementAt (index), true);
	}

	@Override
	public E get (int index) {
		return model.getElementAt (index);
	}

	@Override
	public int indexOf (E element) {
		return model.indexOf (element);
	}

	@Override
	public void refresh () {
		E elem = getSelectedElement ();
		model.setList (list);
		setSelectedElement (elem);
	}


	/**
	 * Move an element up, if allowed
	 *
	 * @param amount Number ot steps up
	 */
	public void moveUp (int amount) {
		if (Settings.DEBUG) {
			System.out.println ("Move up " + getSelectedValue ());
		}
		if (canMove () && hasFocus ()) {
			int index = getSelectedIndex ();
			if (index >= 0) {
				E sel = model.getElementAt (index);
				index = list.indexOf (sel);

				if (index - amount < 0) {
					amount = index;
				}
				while (amount > 0) {
					E selUp = list.get (index - 1);
					list.set (index, selUp);
					list.set (index - 1, sel);
					index--;
					amount--;
				}
				refresh ();
			}
		}
	}

	/**
	 * Move an element down, if allowed
	 *
	 * @param amount Number ot steps down
	 */
	public void moveDown (int amount) {
		if (Settings.DEBUG) {
			System.out.println ("Move down " + getSelectedValue ());
		}
		if (canMove () && hasFocus ()) {
			int index = getSelectedIndex ();
			if (index >= 0) {
				E sel = model.getElementAt (index);
				index = list.indexOf (sel);

				if (index + amount >= list.size ()) {
					amount = list.size () - index - 1;
				}
				while (amount > 0) {
					E selUp = list.get (index + 1);
					list.set (index, selUp);
					list.set (index + 1, sel);
					index++;
					amount--;
				}
				refresh ();
			}
		}
	}


	public boolean canMove () {
		return allowMove && model.getSize () > 1;
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
