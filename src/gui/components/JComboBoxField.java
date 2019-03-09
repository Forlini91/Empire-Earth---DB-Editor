package gui.components;

import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.JTextComponent;

import datmanager.DatFile;
import datmanager.ListSearcher;
import datmanager.Settings;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;
import gui.FrameEditor;
import gui.PopupMenuHandler;


/**
 * A JComboBox which can hold the value of a field
 *
 * @author MarcoForlini
 */
public class JComboBoxField extends JComboBox<Entry> implements EntryFieldInterface, MouseListener, KeyListener, ItemListener, FocusListener {

	private static final long serialVersionUID = -5787229930995728192L;

	/** The list of entries */
	public List<Entry> allEntries;
	/** The linked DatStructure */
	public final DatStructure linkToStruct;

	private final ListSearcher<Entry> searcher = new ListSearcher<>(ListSearcher.ENTRY_NAME_MATCHER, ListSearcher.ENTRY_ID_MATCHER);
	private final JTextComponent textEditor = ((JTextComponent) getEditor().getEditorComponent());
	private final int index;
	private final FieldStruct fieldStruct;
	private Object defaultVal = null;
	private boolean altered = false;

	/**
	 * Create a new {@link JComboBoxField}
	 *
	 * @param fieldStruct The field structure
	 * @param index       Index of the field
	 */
	public JComboBoxField(FieldStruct fieldStruct, int index) {
		this.fieldStruct = fieldStruct;
		this.index = index;
		linkToStruct = fieldStruct.getLinkToStruct();
		allEntries = linkToStruct.datFile.getAllEntries(true);
		setToolTipText(fieldStruct.getDescription());
		setModel(new DefaultComboBoxModel<>(new Vector<>(allEntries)));
		addMouseListener(this);
		textEditor.addMouseListener(this);
		addItemListener(this);
		if (fieldStruct.editable) {
			setEnabled(true);
			setEditable(true);
			textEditor.addFocusListener(this);
			textEditor.addKeyListener(this);
		} else {
			setEnabled(false);
		}
	}

	@Override
	public void setPopupMenu(PopupMenuHandler l) {
		addMouseListener(l);
		textEditor.addMouseListener(l);
	}

	@Override
	public void resetColor() {
		setForeground(null);
	}

	@Override
	public FieldStruct getFieldStruct() { return fieldStruct; }

	@Override
	public int getIndex() { return index; }

	@Override
	public Object getVal() {
		final Object obj = getSelectedItem();
		if (Settings.DEBUG) {
			System.out.println("Getting: " + fieldStruct + " = " + obj + " (Defaults: " + fieldStruct.defaultValue + '/' + defaultVal + ')');
		}
		if (obj != null) {
			if (obj instanceof Entry) {
				return ((Entry) obj).getID();
			} else if (obj instanceof String) {
				return Integer.valueOf((String) obj);
			} else if (defaultVal != null) {
				return defaultVal;
			} else {
				return fieldStruct.defaultValue;
			}
		}
		return fieldStruct.defaultValue;
	}

	@Override
	public void setVal(Object value) {
		defaultVal = value;
		final DatFile datFile = linkToStruct.datFile;
		if (datFile == null) {
			setSelectedItem(value);
			return;
		}
		final Entry entry = datFile.findEntry(value);
		if (entry != null) {
			setSelectedItem(entry);
		} else {
			setSelectedItem(null);
		}
		textEditor.setCaretPosition(0);
		altered = false;
	}

	@Override
	public void refreshField() {
		final Object sel = getSelectedItem();
		if (linkToStruct != null) {
			final DatFile datFile = linkToStruct.datFile;
			if (datFile != null) {
				allEntries = datFile.getAllEntries(true);
				setModel(new DefaultComboBoxModel<>(new Vector<>(allEntries)));
			}
		}
		setSelectedItem(sel);
	}

	@Override
	public boolean isAltered() { return altered; }

	@Override
	public Object getDefaultVal() { return defaultVal; }



	@Override
	public void keyTyped(KeyEvent e) {
		/* Do nothing */}

	@Override
	public void keyPressed(KeyEvent e) {
		/* Do nothing */}

	@Override
	public void keyReleased(KeyEvent e) {
		if (isEnabled()) {
			SwingUtilities.invokeLater(() -> {
				final int key = e.getKeyCode();
				if (key == KeyEvent.VK_UNDEFINED) {
					return;
				}
				final String text = textEditor.getText();
				if (text == null || text.isEmpty()) {
					if (Settings.DEBUG) {
						System.out.println("Select: null");
					}
					setSelectedItem(null);
					return;
				}
				final boolean isEnter = key == KeyEvent.VK_ENTER;
				if (isEnter && !e.isControlDown() && !e.isShiftDown()) {
					final ComboPopup popup = (ComboPopup) getUI().getAccessibleChild(this, 0);
					final JList<?> list = popup.getList();
					if (list.getSelectedValue() != null) {
						setSelectedItem(list.getSelectedValue());
					} else {
						setSelectedItem(searcher.getCurrent());
					}
					hidePopup();
					return;
				}
				switch (key) {
					case KeyEvent.VK_CONTROL:
					case KeyEvent.VK_SHIFT:
					case KeyEvent.VK_ALT:
					case KeyEvent.VK_ALT_GRAPH:
						return;
				}
				if (!e.isActionKey()) {
					if (!isPopupVisible()) {
						showPopup();
					}
					final boolean lastSearch = searcher.getCurrent() != null;
					final List<Entry> results = searcher.find(allEntries, null, text);
					if (results != null) {
						final Entry entry = isEnter && e.isShiftDown() ? searcher.findPrevious() : searcher.findNext();
						if (Settings.DEBUG) {
							System.out.println("Find: " + entry);
						}
						if (entry != null) {
							final ComboPopup popup = (ComboPopup) getUI().getAccessibleChild(this, 0);
							popup.getList().setSelectedValue(entry, true);
						} else if (lastSearch) {
							Toolkit.getDefaultToolkit().beep();
						}
					}
				}
			});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isEnabled()) {
			final Object selectedItem = getSelectedItem();
			if (e.isControlDown() && selectedItem != null && selectedItem instanceof Entry) {
				final Entry selectedEntry = (Entry) selectedItem;
				if (!selectedEntry.isValidLinkTarget()) {
					return;
				}
				final DatFile datFile = linkToStruct.datFile;
				if (datFile != null) {
					final EntryGroup entryGroup = datFile.findGroup(selectedEntry);
					if (entryGroup != null) {
						final FrameEditor frameEditor = datFile.openInEditor(this, e.isShiftDown());
						frameEditor.goToEntry(entryGroup, selectedEntry);
					}
				}
			} else if (fieldStruct.editable && SwingUtilities.isLeftMouseButton(e)) {
				showPopup();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/* Do nothing */}

	@Override
	public void mouseReleased(MouseEvent e) {
		/* Do nothing */}

	@Override
	public void mouseEntered(MouseEvent e) {
		/* Do nothing */}

	@Override
	public void mouseExited(MouseEvent e) {
		/* Do nothing */}

	@Override
	public void itemStateChanged(ItemEvent e) {
		altered = true;
	}

	@Override
	public void focusLost(FocusEvent e) {
		final ComboPopup cp = (ComboPopup) getUI().getAccessibleChild(JComboBoxField.this, 0);
		Object item = null;
		item = cp.getList().getSelectedValue();
		if (item == null) {
			item = searcher.getCurrent();
		}
		if (item != null) {
			setSelectedItem(item);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		textEditor.setCaretPosition(textEditor.getText().length());
		textEditor.moveCaretPosition(0);
	}



}
