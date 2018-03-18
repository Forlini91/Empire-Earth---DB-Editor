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

import constants.AttributeCode;
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
public class JComboBoxEnumAttribute extends JComboBox <AttributeCode> implements EntryFieldInterface, ItemListener, MouseListener, KeyListener {

	private static final long									serialVersionUID	= -5787229930995728192L;
	private static final BiPredicate <String, AttributeCode>	NAME_MATCHER		= (text, attributeCode) -> attributeCode.name.toLowerCase ().contains (text);
	private static final BiPredicate <Integer, AttributeCode>	ID_MATCHER			= (val, attributeCode) -> attributeCode.code == val || NAME_MATCHER.test (val.toString (), attributeCode);

	private FieldStruct											fieldStruct;
	private int													index;
	private FrameEditor											frameEditor;

	private ListSearcher <AttributeCode>						searcher			= new ListSearcher <> (NAME_MATCHER, ID_MATCHER);
	private JTextComponent										textEditor			= ((JTextComponent) getEditor ().getEditorComponent ());
	private Object												defaultVal			= null;
	private boolean												altered				= false;

	/**
	 * Create a new {@link JComboBoxEnumAttribute}
	 *
	 * @param fieldStruct The field structure
	 * @param index Index of the field
	 * @param frameEditor The FrameEditor object
	 */
	public JComboBoxEnumAttribute (FieldStruct fieldStruct, int index, FrameEditor frameEditor) {
		super (AttributeCode.values ());
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
		updateState ();
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

	@Override
	public void itemStateChanged (ItemEvent e) {
		altered = true;
		updateState ();
	}


	void updateState () {
		AttributeCode attribute = (AttributeCode) getSelectedItem ();
		frameEditor.setFieldEnabled (14, attribute == AttributeCode.C40_AREA_EFFECT);
		frameEditor.setFieldEnabled (15, attribute == AttributeCode.C42_TERRAIN_FAMILY);
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
				List <AttributeCode> results = searcher.find (AttributeCode.values (), null, text);
				if (results != null) {
					AttributeCode result = searcher.findNext ();
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
