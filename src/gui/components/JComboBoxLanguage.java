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

import datmanager.Core;
import datmanager.LanguageEntry;
import datmanager.ListSearcher;
import datstructure.FieldStruct;
import gui.FrameEditor;


public class JComboBoxLanguage extends JComboBox <LanguageEntry> implements AbstractEntryField, ItemListener, MouseListener, KeyListener {
	
	private static final long serialVersionUID = -5787229930995728192L;

	private static final BiPredicate<String, LanguageEntry> NAME_MATCHER = (text, lang) -> lang.text.toLowerCase().contains(text);
	private static final BiPredicate<Integer, LanguageEntry> ID_MATCHER = (val, lang) -> (""+lang.code).contains(""+val);

	private ListSearcher <LanguageEntry> searcher = new ListSearcher<>(NAME_MATCHER, ID_MATCHER);
	private JTextComponent editor = ((JTextComponent) getEditor().getEditorComponent());
	private FieldStruct fieldStruct;
	private int index;
	private Object defaultVal = null;
	private boolean altered = false;

	public JComboBoxLanguage(FrameEditor frameEditor, FieldStruct fieldStruct, int index){
		super(Core.languageVector);
		this.fieldStruct = fieldStruct;
		this.index = index;
		setEditable(true);
		addItemListener(this);
		addMouseListener(this);
		editor.addKeyListener(this);
	}

	@Override
	public synchronized void addMouseListener (MouseListener l) {
		super.addMouseListener(l);
		if (editor != null){
			editor.addMouseListener(l);
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
		//		System.out.println("Getting: " + fieldStruct + " = " + obj + '(' + fieldStruct.defaultValue + '/' + defaultVal + ')');
		if (obj != null){
			if (obj instanceof LanguageEntry) {
				return ((LanguageEntry) obj).code;
			} else {
				return obj;
			}
		} else {
			return -1;
		}
	}
	
	@Override
	public void setVal(Object value){
		defaultVal = value;
		if (value != null && value instanceof Integer){
			int code = (int) value;
			LanguageEntry le = Core.LANGUAGE.get(code);
			if (le != null){
				setSelectedItem(le);
				editor.setCaretPosition(0);
				altered = false;
				return;
			}
		}
		setSelectedItem(value);
		editor.setCaretPosition(0);
		altered = false;
	}

	@Override
	public void refreshField() {}
	
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
	
	@Override
	public void keyTyped (KeyEvent e) {
		
	}

	@Override public void keyPressed (KeyEvent e) {}
	@Override public void keyReleased (KeyEvent e) {
		SwingUtilities.invokeLater(() -> {
			String text = editor.getText();
			if (text == null || text.isEmpty()){
				System.out.println("Select: null");
				setSelectedItem(null);
			} else if (e.getKeyCode() == KeyEvent.VK_TAB && isPopupVisible()){
				ComboPopup popup = (ComboPopup) getUI().getAccessibleChild(this, 0);
				setSelectedItem(popup.getList().getSelectedValue());
			} else {
				if (!isPopupVisible()) {
					showPopup();
				}
				List<LanguageEntry> results = searcher.find(Core.languageVector, null, text);
				if (results != null){
					LanguageEntry enumValue = searcher.findNext();
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

	@Override public void mousePressed (MouseEvent e) {}
	@Override public void mouseReleased (MouseEvent e) {}
	@Override public void mouseEntered (MouseEvent e) {}
	@Override public void mouseExited (MouseEvent e) {}
	
}
