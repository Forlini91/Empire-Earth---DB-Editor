package gui.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.function.BiPredicate;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.JTextComponent;

import constants.EffectCode;
import constants.EnumValue;
import datmanager.ListSearcher;
import datmanager.Settings;
import datstructure.FieldStruct;
import gui.FrameEditor;


/**
 * A JComboBox which hold the content of an enum
 *
 * @author MarcoForlini
 */
public class JComboBoxEnumEffect extends JComboBox <EffectCode> implements EntryFieldInterface, ItemListener, MouseListener, KeyListener {

	private static final long								serialVersionUID	= -5787229930995728192L;
	private static final BiPredicate <String, EffectCode>	NAME_MATCHER		= (text, effectCode) -> effectCode.name.toLowerCase ().contains (text);
	private static final BiPredicate <Integer, EffectCode>	ID_MATCHER			= (val, effectCode) -> effectCode.code == val || NAME_MATCHER.test (val.toString (), effectCode);

	private FieldStruct										fieldStruct;
	private int												index;
	private FrameEditor										frameEditor;

	private ListSearcher <EffectCode>						searcher			= new ListSearcher <> (NAME_MATCHER, ID_MATCHER);
	private JTextComponent									textEditor			= ((JTextComponent) getEditor ().getEditorComponent ());
	private Object											defaultVal			= null;
	private boolean											altered				= false;

	/**
	 * Create a new {@link JComboBoxEnumEffect}
	 *
	 * @param fieldStruct The field structure
	 * @param index Index of the field
	 * @param frameEditor The FrameEditor object
	 */
	public JComboBoxEnumEffect (FieldStruct fieldStruct, int index, FrameEditor frameEditor) {
		super (EffectCode.values ());
		this.fieldStruct = fieldStruct;
		this.index = index;
		this.frameEditor = frameEditor;
		setToolTipText (fieldStruct.getDescription ());
		setEditable (true);
		addItemListener (this);
		addMouseListener (this);
		textEditor.addKeyListener (this);
	}

	@Override
	public synchronized void addMouseListener (MouseListener l) {
		super.addMouseListener (l);
		if (textEditor != null) {
			textEditor.addMouseListener (l);
		}
	}

	@Override
	public void resetColor () {
		setForeground (null);
	}

	@Override
	public FieldStruct getEntryStruct () {
		return fieldStruct;
	}

	@Override
	public int getIndex () {
		return index;
	}

	@Override
	public Integer getVal () {
		EffectCode obj = (EffectCode) getSelectedItem ();
		if (Settings.DEBUG) {
			System.out.println ("Getting: " + fieldStruct + " = " + obj);
		}
		return obj.code;
	}

	@Override
	public void setVal (Object value) {
		defaultVal = value;
		for (EnumValue enumValue : fieldStruct.enumValues) {
			if (value.equals (enumValue.getCode ())) {
				setSelectedItem (enumValue);
				textEditor.setCaretPosition (0);
				altered = false;
				return;
			}
		}
		setSelectedItem (value);
		textEditor.setCaretPosition (0);
		altered = false;
		updateEditor ();
	}

	@Override
	public void refreshField () {/* Do nothing */}

	@Override
	public boolean isAltered () {
		return altered;
	}

	@Override
	public Object getDefaultVal () {
		return defaultVal;
	}


	/**
	 * Update the fields of the editor basing on the value selected here
	 */
	public void updateEditor () {
		boolean[] toEnable = { true, true, false, false, false, false, false, false, true, false, false, false, false, false };
		switch ((EffectCode) getSelectedItem ()) {
			case C01_SET_BUTTON:
				toEnable[5] = toEnable[10] = toEnable[13] = true;
				break;
			case C02_ALTER_ATTRIBUTE:
				toEnable[2] = toEnable[3] = toEnable[4] = toEnable[5] = toEnable[7] = toEnable[11] = true;
				break;
			case C06_SET_GRAPHIC:
				toEnable[5] = toEnable[9] = toEnable[10] = true;
				break;
			case C08_ENABLE_TECH:
			case C09_DISABLE_TECH:
				toEnable[10] = true;
				break;
			case C10_START_GAME:
				toEnable[10] = true;
				break;
			case C12_GUI_BACKGROUND:
				toEnable[9] = true;
				break;
			case C15_SET_ACTION_SOUND_1:
			case C17_SET_DEATH_SOUND:
			case C18_SET_SELECTION_SOUND_1:
			case C20_SET_ACTION_SOUND_2:
			case C21_SET_SELECTION_SOUND_2:
				toEnable[5] = toEnable[12] = true;
				break;
			case C19_UPDGRADE_ALL_OBJECTS:
				toEnable[5] = toEnable[6] = toEnable[10] = true;
				break;
			case C22_REPLACE_OBJECTS:
				toEnable[5] = toEnable[6] = true;
				break;
			default:
				for (int i = 2; i < 14; i++) {
					toEnable[i] = true;
				}
		}
		for (int i = 2; i < 14; i++) {
			frameEditor.setFieldEnabled (i, toEnable[i]);
		}
	}


	@Override
	public void itemStateChanged (ItemEvent e) {
		altered = true;
		updateEditor ();
	}

	@Override
	public void keyTyped (KeyEvent e) {/* Do nothing */}

	@Override
	public void keyPressed (KeyEvent e) {/* Do nothing */}

	@Override
	public void keyReleased (KeyEvent e) {
		SwingUtilities.invokeLater ( () -> {
			String text = textEditor.getText ();
			if (text == null || text.isEmpty ()) {
				if (Settings.DEBUG) {
					System.out.println ("Select: null");
				}
				setSelectedItem (null);
			} else if (e.getKeyCode () == KeyEvent.VK_TAB && isPopupVisible ()) {
				ComboPopup popup = (ComboPopup) getUI ().getAccessibleChild (this, 0);
				setSelectedItem (popup.getList ().getSelectedValue ());
			} else {
				if (!isPopupVisible ()) {
					showPopup ();
				}
				List <EffectCode> results = searcher.find (EffectCode.values (), null, text);
				if (results != null) {
					EffectCode result = searcher.findNext ();
					if (result != null) {
						ComboPopup popup = (ComboPopup) getUI ().getAccessibleChild (this, 0);
						popup.getList ().setSelectedValue (result, true);
					}
				}
			}
		});
	}

	@Override
	public void mouseClicked (MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton (e)) {
			showPopup ();
		}
	}

	@Override
	public void mousePressed (MouseEvent e) {/* Do nothing */}

	@Override
	public void mouseReleased (MouseEvent e) {/* Do nothing */}

	@Override
	public void mouseEntered (MouseEvent e) {/* Do nothing */}

	@Override
	public void mouseExited (MouseEvent e) {/* Do nothing */}

}
