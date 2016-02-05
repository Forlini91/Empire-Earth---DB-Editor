package gui.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;
import java.util.function.BiPredicate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.JTextComponent;

import datmanager.Core;
import datmanager.ListSearcher;
import datstructure.DatContent;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;
import gui.FrameEditor;


public class JComboBoxEntry extends JComboBox <Entry> implements AbstractEntryField, MouseListener, KeyListener, ItemListener {
	
	private static final long serialVersionUID = -5787229930995728192L;
	private static final BiPredicate<String, Entry> NAME_MATCHER = (text, entry) -> entry.isDefined() && entry.toString().toLowerCase().contains(text);
	private static final BiPredicate<Integer, Entry> ID_MATCHER = (val, entry) -> entry.ID == val;

	private ListSearcher <Entry> searcher = new ListSearcher<>(NAME_MATCHER, ID_MATCHER);
	private JTextComponent editor = ((JTextComponent) getEditor().getEditorComponent());
	private FieldStruct fieldStruct;
	private int index;
	private List<Entry> allEntries = null;
	private DatStructure linkToStruct;
	private Object defaultVal = null;
	private boolean altered = false;

	public JComboBoxEntry(FrameEditor frameEditor, FieldStruct fieldStruct, int index){
		this.fieldStruct = fieldStruct;
		this.index = index;
		linkToStruct = fieldStruct.getLinkToStruct();
		if (linkToStruct != null){
			DatContent datContent = Core.DATA.get(linkToStruct);
			if (datContent != null){
				allEntries = datContent.getAllEntries();
				setModel(new DefaultComboBoxModel<>(new Vector<>(allEntries)));
			}
		}
		setEditable(true);
		addMouseListener(this);
		addItemListener(this);
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
		if (obj != null && obj instanceof Entry){
			return ((Entry) obj).ID;
		} else if (defaultVal != null){
			return defaultVal;
		} else {
			return fieldStruct.defaultValue;
		}
	}
	
	@Override
	public void setVal(Object value){
		defaultVal = value;
		DatContent content = Core.DATA.get(linkToStruct);
		if (content == null){
			return;
		}
		for (EntryGroup entryGroup : content){
			Entry entry = entryGroup.map.get(value);
			if (entry != null){
				setSelectedItem(entry);
				altered = false;
				return;
			}
		}
		setSelectedItem(null);
		altered = false;
	}

	@Override
	public void refreshField () {
		Object sel = getSelectedItem();
		if (linkToStruct != null){
			DatContent datContent = Core.DATA.get(linkToStruct);
			if (datContent != null){
				allEntries = datContent.getAllEntries();
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


	
	@Override
	public void keyTyped (KeyEvent e) {
		SwingUtilities.invokeLater(() -> {
			String text = editor.getText();
			if (text == null || text.isEmpty()){
				System.out.println("Select: null");
				setSelectedItem(null);
			} else {
				showPopup();
				List<Entry> results = searcher.find(allEntries, null, text);
				if (results != null){
					Entry entry = searcher.findNext();
					if (entry != null){
						ComboPopup popup = (ComboPopup) getUI().getAccessibleChild(this, 0);
						popup.getList().setSelectedValue(entry, true);
					}
				}
			}
		});
	}
	
	@Override public void keyPressed (KeyEvent e) {}
	@Override public void keyReleased (KeyEvent e) {}
	
	@Override
	public void mouseClicked (MouseEvent e) {
		Object selectedItem = getSelectedItem();
		if (e.isControlDown() && selectedItem != null && selectedItem instanceof Entry){
			Entry selectedEntry = (Entry) selectedItem;
			DatContent datContent = Core.DATA.get(linkToStruct);
			if (datContent != null){
				for (EntryGroup entryGroup : datContent){
					if (entryGroup.map.containsKey(selectedEntry.ID)){
						FrameEditor frameEditor = Core.openFile(this, datContent, e.isShiftDown());
						frameEditor.goToEntry(entryGroup, selectedEntry);
						break;
					}
				}
			}
		} else if (SwingUtilities.isLeftMouseButton(e)){
			showPopup();
		}
	}
	
	@Override public void mousePressed (MouseEvent e) {}
	@Override public void mouseReleased (MouseEvent e) {}
	@Override public void mouseEntered (MouseEvent e) {}
	@Override public void mouseExited (MouseEvent e) {}

	@Override
	public void itemStateChanged (ItemEvent e) {
		altered = true;
	}
	
}
