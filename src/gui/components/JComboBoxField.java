package gui.components;

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
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.JTextComponent;

import datmanager.Core;
import datmanager.DatFile;
import datmanager.DatFile.EntryLocation;
import datmanager.ListSearcher;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;
import gui.FrameEditor;


/**
 * A JComboBox which can hold the value of a field
 * @author MarcoForlini
 */
public class JComboBoxField extends JComboBox <Entry> implements EntryFieldInterface, MouseListener, KeyListener, ItemListener {
	
	private static final long serialVersionUID = -5787229930995728192L;

	private ListSearcher <Entry> searcher = new ListSearcher<>(ListSearcher.ENTRY_NAME_MATCHER, ListSearcher.ENTRY_ID_MATCHER);
	private JTextComponent textEditor = ((JTextComponent) getEditor().getEditorComponent());
	private FieldStruct fieldStruct;
	private int index;
	private List<Entry> allEntries = null;
	private DatStructure linkToStruct;
	private Object defaultVal = null;
	private boolean altered = false;

	/**
	 * Create a new {@link JComboBoxField}
	 * @param fieldStruct	The field structure
	 * @param index			Index of the field
	 */
	public JComboBoxField(FieldStruct fieldStruct, int index){
		this.fieldStruct = fieldStruct;
		this.index = index;
		linkToStruct = fieldStruct.getLinkToStruct();
		if (linkToStruct != null){
			DatFile datFile = linkToStruct.datFile;
			if (datFile != null){
				allEntries = datFile.getAllEntries();
				setModel(new DefaultComboBoxModel<>(new Vector<>(allEntries)));
			}
		}
		setEditable(true);
		addMouseListener(this);
		addItemListener(this);
		textEditor.addKeyListener(this);
	}
	
	@Override
	public synchronized void addMouseListener (MouseListener l) {
		super.addMouseListener(l);
		if (textEditor != null){
			textEditor.addMouseListener(l);
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
		//System.out.println("Getting: " + fieldStruct + " = " + obj + '(' + fieldStruct.defaultValue + '/' + defaultVal + ')');
		if (obj != null) {
			if (obj instanceof Entry){
				return ((Entry) obj).ID;
			} else if (obj instanceof String){
				return Integer.valueOf((String) obj);
			} else if (defaultVal != null){
				return defaultVal;
			} else {
				return fieldStruct.defaultValue;
			}
		}
		return fieldStruct.defaultValue;
	}
	
	@Override
	public void setVal(Object value){
		defaultVal = value;
		DatFile datFile = linkToStruct.datFile;
		if (datFile == null){
			setSelectedItem(value);
			return;
		}
		EntryLocation entryLocation = datFile.findEntry(value);
		if (entryLocation != null){
			setSelectedItem(entryLocation.entry);
		} else {
			setSelectedItem(null);
		}
		textEditor.setCaretPosition(0);
		altered = false;
	}

	@Override
	public void refreshField () {
		Object sel = getSelectedItem();
		if (linkToStruct != null){
			DatFile datFile = linkToStruct.datFile;
			if (datFile != null){
				allEntries = datFile.getAllEntries();
				setModel(new DefaultComboBoxModel<>(new Vector<>(allEntries)));
			}
		}
		setSelectedItem(sel);
	}
	
	@Override
	public boolean isAltered () {
		return altered;
	}
	
	@Override
	public Object getDefaultVal () {
		return defaultVal;
	}


	
	@Override public void keyTyped (KeyEvent e) {/*Do nothing*/}
	@Override public void keyPressed (KeyEvent e) {/*Do nothing*/}
	@Override public void keyReleased (KeyEvent e) {
		SwingUtilities.invokeLater(() -> {
			String text = textEditor.getText();
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
				List<Entry> results = searcher.find(allEntries, null, text);
				if (results != null){
					Entry entry = searcher.findNext();
					System.out.println("Find: " + entry);
					if (entry != null){
						ComboPopup popup = (ComboPopup) getUI().getAccessibleChild(this, 0);
						popup.getList().setSelectedValue(entry, true);
					}
				}
			}
		});
	}
	
	@Override
	public void mouseClicked (MouseEvent e) {
		Object selectedItem = getSelectedItem();
		if (e.isControlDown() && selectedItem != null && selectedItem instanceof Entry){
			Entry selectedEntry = (Entry) selectedItem;
			DatFile datFile = linkToStruct.datFile;
			if (datFile != null){
				EntryGroup entryGroup = datFile.findGroup(selectedEntry);
				if (entryGroup != null){
					FrameEditor frameEditor = Core.openFile(this, datFile, e.isShiftDown());
					frameEditor.goToEntry(entryGroup, selectedEntry);
				}
			}
		} else if (SwingUtilities.isLeftMouseButton(e)){
			showPopup();
		}
	}
	
	@Override public void mousePressed (MouseEvent e) {/*Do nothing*/}
	@Override public void mouseReleased (MouseEvent e) {/*Do nothing*/}
	@Override public void mouseEntered (MouseEvent e) {/*Do nothing*/}
	@Override public void mouseExited (MouseEvent e) {/*Do nothing*/}

	@Override
	public void itemStateChanged (ItemEvent e) {
		altered = true;
	}
	
}
