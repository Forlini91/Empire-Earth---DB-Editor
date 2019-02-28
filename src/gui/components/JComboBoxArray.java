package gui.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.JTextComponent;

import datmanager.ListSearcher;
import datmanager.Settings;
import datstructure.FieldStruct;

/**
 * A JComboBox which hold the content of an array
 *
 * @author MarcoForlini
 */
public class JComboBoxArray extends JComboBox<Integer> implements EntryFieldInterface, ItemListener, MouseListener, KeyListener {

	private static final long serialVersionUID = -5787229930995728192L;

	private static final BiPredicate<String, Integer> NAME_MATCHER = (text, code) -> text.contentEquals(Integer.toString(code));
	private static final BiPredicate<Integer, Integer> ID_MATCHER = (val, code) -> code == val || NAME_MATCHER.test(Integer.toString(val), code);

	private final Collection<Integer> data;
	private final ListSearcher<Integer> searcher = new ListSearcher<>(NAME_MATCHER, ID_MATCHER);
	private final JTextComponent textEditor = ((JTextComponent) getEditor().getEditorComponent());
	private final ComboPopup popup = (ComboPopup) getUI().getAccessibleChild(this, 0);
	private final FieldStruct fieldStruct;
	private final int index;
	private Object defaultVal = null;
	private boolean altered = false;

	private JComboBoxArray(FieldStruct fieldStruct, int index, DefaultComboBoxModel<Integer> model) {
		super(model);
		this.fieldStruct = fieldStruct;
		this.index = index;
		data = List.of(fieldStruct.arrValues);
		model.addAll(data);
		setToolTipText(fieldStruct.getDescription());
		setEditable(false);
		textEditor.addKeyListener(this);
	}

	/**
	 * Create a new {@link JComboBoxArray}
	 *
	 * @param fieldStruct The structure of the field
	 * @param index       Index of the field
	 */
	public JComboBoxArray(FieldStruct fieldStruct, int index) {
		this(fieldStruct, index, new DefaultComboBoxModel<>());
		addItemListener(this);
		addMouseListener(this);
	}

	@Override
	public void resetColor() {
		setForeground(null);
	}

	@Override
	public FieldStruct getEntryStruct() { return fieldStruct; }

	@Override
	public int getIndex() { return index; }

	@Override
	public Integer getVal() {
		final Integer obj = (Integer) getSelectedItem();
		if (Settings.DEBUG) {
			System.out.println("Getting: " + fieldStruct + " = " + obj);
		}
		return obj;
	}

	@Override
	public void setVal(Object value) {
		defaultVal = value;
		final int index = Arrays.binarySearch(fieldStruct.arrValues, (int) value);
		if (index >= 0) {
			setSelectedIndex(index);
			textEditor.setCaretPosition(0);
			altered = false;
			return;
		}
		setSelectedIndex(0);
		textEditor.setCaretPosition(0);
		altered = false;
	}

	@Override
	public void refreshField() {
		/* Do nothing */
	}

	@Override
	public boolean isAltered() { return altered; }

	@Override
	public Object getDefaultVal() { return defaultVal; }

	@Override
	public synchronized void addMouseListener(MouseListener l) {
		super.addMouseListener(l);
		if (textEditor != null) {
			textEditor.addMouseListener(l);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		altered = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		/* Do nothing */}

	@Override
	public void keyPressed(KeyEvent e) {
		/* Do nothing */}

	@Override
	public void keyReleased(KeyEvent e) {
		SwingUtilities.invokeLater(() -> {
			final String text = textEditor.getText();
			if (text == null || text.isEmpty()) {
				if (Settings.DEBUG) {
					System.out.println("Select: null");
				}
				setSelectedItem(null);
			} else if (e.getKeyCode() == KeyEvent.VK_TAB && isPopupVisible()) {
				setSelectedItem(popup.getList().getSelectedValue());
			} else {
				if (!isPopupVisible()) {
					showPopup();
				}
				final List<Integer> results = searcher.find(fieldStruct.arrValues, null, text);
				if (results != null) {
					final Integer enumValue = searcher.findNext();
					if (enumValue != null) {
						popup.getList().setSelectedValue(enumValue, true);
					}
				}
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			showPopup();
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

}
