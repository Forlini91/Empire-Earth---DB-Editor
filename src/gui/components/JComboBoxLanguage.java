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

import datmanager.Language;
import datmanager.ListSearcher;
import datmanager.Settings;
import datstructure.FieldStruct;


/**
 * A JCheckBox which hold the list of languages
 * @author MarcoForlini
 */
public class JComboBoxLanguage extends JComboBox <Language> implements EntryFieldInterface, ItemListener, MouseListener, KeyListener {
	
	private static final long serialVersionUID = -5787229930995728192L;

	private static final BiPredicate<String, Language> NAME_MATCHER = (text, lang) -> lang.text.toLowerCase().contains(text);
	private static final BiPredicate<Integer, Language> ID_MATCHER = (val, lang) -> {
		String text = val.toString();
		return Integer.toString(lang.ID).contains(text) || NAME_MATCHER.test(text, lang);
	};

	private ListSearcher <Language> searcher = new ListSearcher<>(NAME_MATCHER, ID_MATCHER);
	private JTextComponent textComponent = ((JTextComponent) getEditor().getEditorComponent());
	private FieldStruct fieldStruct;
	private int index;
	private Object defaultVal = null;
	private boolean altered = false;

	/**
	 * Create a new {@link JComboBoxLanguage}
	 * @param fieldStruct	The field structure
	 * @param index			Index of the field
	 */
	public JComboBoxLanguage(FieldStruct fieldStruct, int index){
		super(Language.LIST);
		this.fieldStruct = fieldStruct;
		this.index = index;
		setEditable(true);
		addItemListener(this);
		addMouseListener(this);
		textComponent.addKeyListener(this);
	}

	@Override
	public synchronized void addMouseListener (MouseListener l) {
		super.addMouseListener(l);
		if (textComponent != null){
			textComponent.addMouseListener(l);
		}
	}
	
	@Override
	public void resetColor () {
		setForeground(null);
	}
	
	@Override
	public FieldStruct getEntryStruct () {
		return fieldStruct;
	}
	
	@Override
	public int getIndex(){
		return index;
	}
	
	@Override
	public Object getVal(){
		Object obj = getSelectedItem();
		if (Settings.DEBUG) {
			System.out.println("Getting: " + fieldStruct + " = " + obj + " (Defaults: " + fieldStruct.defaultValue + '/' + defaultVal + ')');
		}
		if (obj != null){
			if (obj instanceof Language) {
				return ((Language) obj).ID;
			}  else if (obj instanceof String){
				return Integer.valueOf((String) obj);
			} else if (obj instanceof Integer){
				return (int) obj;
			} else {
				return -1;
			}
		}
		return -1;
	}
	
	@Override
	public void setVal(Object value){
		defaultVal = value;
		if (value != null && value instanceof Integer){
			int code = (int) value;
			Language le = Language.MAP.get(code);
			if (le != null){
				setSelectedItem(le);
				textComponent.setCaretPosition(0);
				altered = false;
				return;
			}
		}
		setSelectedItem(value);
		textComponent.setCaretPosition(0);
		altered = false;
	}

	@Override
	public void refreshField() {/*Do nothing*/}
	
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
	}
	
	@Override public void keyTyped (KeyEvent e) {/*Do nothing*/}
	@Override public void keyPressed (KeyEvent e) {/*Do nothing*/}
	@Override public void keyReleased (KeyEvent e) {
		SwingUtilities.invokeLater(() -> {
			String text = textComponent.getText();
			if (text == null || text.isEmpty()){
				if (Settings.DEBUG) {
					System.out.println("Select: null");
				}
				setSelectedItem(null);
			} else if (e.getKeyCode() == KeyEvent.VK_TAB && isPopupVisible()){
				ComboPopup popup = (ComboPopup) getUI().getAccessibleChild(this, 0);
				setSelectedItem(popup.getList().getSelectedValue());
			} else {
				if (!isPopupVisible()) {
					showPopup();
				}
				List<Language> results = searcher.find(Language.LIST, null, text);
				if (results != null){
					Language enumValue = searcher.findNext();
					if (enumValue != null){
						ComboPopup popup = (ComboPopup) getUI().getAccessibleChild(this, 0);
						popup.getList().setSelectedValue(enumValue, true);
					}
				}
			}
		});
	}

	@Override
	public void mouseClicked (MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)){
			showPopup();
		}
	}

	@Override public void mousePressed (MouseEvent e) {/*Do nothing*/}
	@Override public void mouseReleased (MouseEvent e) {/*Do nothing*/}
	@Override public void mouseEntered (MouseEvent e) {/*Do nothing*/}
	@Override public void mouseExited (MouseEvent e) {/*Do nothing*/}
	
}
