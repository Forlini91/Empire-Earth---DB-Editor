package gui.components;

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


public class JComboBoxEntry extends JComboBox <Entry> implements AbstractEntryField, MouseListener, KeyListener {
	
	private static final long serialVersionUID = -5787229930995728192L;
	private static final BiPredicate<String, Entry> NAME_MATCHER = (text, entry) -> entry.isDefined() && entry.toString().toLowerCase().contains(text);
	private static final BiPredicate<Integer, Entry> ID_MATCHER = (val, entry) -> entry.ID == val;

	private ListSearcher <Entry> searcher = new ListSearcher<>(NAME_MATCHER, ID_MATCHER);
	private JTextComponent editor = ((JTextComponent) getEditor().getEditorComponent());
	private FieldStruct fieldStruct;
	private int index;
	private List<Entry> allEntries = null;
	private DatStructure linkToStruct;

	public JComboBoxEntry(FieldStruct fieldStruct, int index){
		this.fieldStruct = fieldStruct;
		this.index = index;
		DatStructure datStructure = fieldStruct.getLinkToStruct();
		if (datStructure != null){
			DatContent datContent = Core.DATA.get(datStructure);
			if (datContent != null){
				allEntries = datContent.getAllEntries();
				setModel(new DefaultComboBoxModel<>(new Vector<>(allEntries)));
			}
		}
		linkToStruct = fieldStruct.getLinkToStruct();
		setEditable(true);
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
		if (obj != null && obj instanceof Entry){
			return ((Entry) obj).ID;
		} else {
			return -1;
		}
	}
	
	@Override
	public void setVal(Object value){
		DatContent content = Core.DATA.get(linkToStruct);
		if (content == null){
			System.out.println("CRASH: " + linkToStruct + " -> " + Core.DATA.entrySet() + "  ->  " + value);
			return;
		}
		for (EntryGroup entryGroup : content){
			Entry entry = entryGroup.map.get(value);
			if (entry != null){
				setSelectedItem(entry);
				return;
			}
		}
		setSelectedItem(null);
	}


	
	@Override
	public void keyTyped (KeyEvent e) {
		SwingUtilities.invokeLater(() -> {
			String text = editor.getText();
			if (text == null || text.isEmpty()){
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
		showPopup();
	}
	
	@Override public void mousePressed (MouseEvent e) {}
	@Override public void mouseReleased (MouseEvent e) {}
	@Override public void mouseEntered (MouseEvent e) {}
	@Override public void mouseExited (MouseEvent e) {}
	
}
