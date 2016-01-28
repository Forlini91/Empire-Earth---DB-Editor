package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicTextFieldUI;

import dbmanager.Core;
import dbstructure.DatStructure;
import dbstructure.Entry;
import dbstructure.EntryGroup;
import dbstructure.EntryStruct;
import dbstructure.Identity;
import dbstructure.Type;
import gui.GUIEditor;


@SuppressWarnings ("serial")
public class JTextFieldEntry extends JTextField implements AbstractEntryField, MouseListener, FocusListener, DocumentListener {
	
	private static final Color BROWN = new Color(127, 51, 0);

	private EntryStruct entryStruct;
	private final int index;
	private final Color defaultColor;
	private JTextFieldEntryUI textUI = new JTextFieldEntryUI();

	DatStructure datStructure = null;
	public EntryGroup pointToGroup = null;
	public Entry pointToEntry = null;

	public JTextFieldEntry(EntryStruct entryStruct, int index, String hint){
		this.entryStruct = entryStruct;
		this.index = index;
		if (entryStruct.color == Color.RED){
			defaultColor = BROWN;
		} else {
			defaultColor = entryStruct.color;
		}
		if (entryStruct.type.datStructure != null){
			datStructure = entryStruct.type.datStructure.get();
		}

		setUI(textUI);
		setColumns(10);
		setEditable(entryStruct.editable);
		setForeground(defaultColor);
		setCaretPosition(0);

		addFocusListener(this);
		addMouseListener(this);
		if (datStructure != null){
			getDocument().addDocumentListener(this);
		}
	}

	@Override
	public void resetColor () {
		setForeground(defaultColor);
	}
	
	@Override
	public EntryStruct getEntryStruct () {
		return entryStruct;
	}

	@Override
	public int getIndex(){
		return index;
	}
	
	@Override
	public boolean isFieldCompiled(){
		return !getText().isEmpty();
	}
	
	@Override
	public Object getVal(){
		switch(entryStruct.type){
			case STRING:
				return getText();
			case FLOAT:
				return Float.valueOf(getText());
			default:
				return Integer.valueOf(getText());
		}
	}

	@Override
	public void setVal (Object value) {
		if (entryStruct.type == Type.STRING){
			setText(((String) value).trim());
		} else {
			setText(String.valueOf(value));
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		repaint();
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		checkResult(getText());
		//repaint();
	}
	
	

	private void checkResult(String text){
		if (entryStruct != null){
			if (entryStruct.type == Type.STRING){
				if (text.length() > entryStruct.size) {
					JTextFieldEntry.super.setText(text.substring(0, 100));
					Toolkit.getDefaultToolkit().beep();
				}
			} else {
				try {
					if (entryStruct.type == Type.FLOAT) {
						Float.valueOf(text);
					} else {
						int value = Integer.valueOf(text);
						if (entryStruct.type == Type.BOOLEAN){
							if (value < 0) {
								JTextFieldEntry.super.setText("0");
								Toolkit.getDefaultToolkit().beep();
							} else if (value > 1) {
								JTextFieldEntry.super.setText("1");
								Toolkit.getDefaultToolkit().beep();
							}
						}
					}
				} catch (NumberFormatException exc) {
					setText(null);
					Toolkit.getDefaultToolkit().beep();
				}
			}
		}
	}


	public void scanEntry(){
		if (!getText().isEmpty()){
			List <EntryGroup> dbManager = Core.dbData.get(datStructure);
			if (dbManager != null){
				try{
					Identity[] identities = Core.findEntryByID(datStructure, Integer.valueOf(getText()));
					if (identities != null){
						EntryGroup findGroup = (EntryGroup) identities[0];
						Entry findEntry = (Entry) identities[1];
						if (findGroup != pointToGroup || findEntry != pointToEntry){
							pointToGroup = findGroup;
							pointToEntry = findEntry;
							//System.out.println("Field " + entryStruct.toString() + "   >   Matches " + findGroup + " > " + findEntry);
						}
						return;
					}
				} catch (NumberFormatException e){}
			}
		}
		pointToGroup = null;
		pointToEntry = null;
	}
	

	@Override
	public void mouseClicked (MouseEvent e) {
		if (e.isControlDown() && pointToEntry != null){
			GUIEditor editor = Core.dbEditors.get(datStructure);
			if (editor != null){
				System.out.println("Go to: " + editor.file.getName() + " > " + pointToGroup + " > " + pointToEntry);
				editor.setVisible(true);
				editor.goToEntry(pointToGroup, pointToEntry);
			}
		}
	}
	@Override public void mousePressed (MouseEvent e) {}
	@Override public void mouseReleased (MouseEvent e) {}
	@Override public void mouseEntered (MouseEvent e) {}
	@Override public void mouseExited (MouseEvent e) {}





	public class JTextFieldEntryUI extends BasicTextFieldUI {
		@Override
		protected void paintSafely(Graphics g) {
			super.paintSafely(g);
			if (datStructure != null) {
				String printText = printText();
				if (printText != null){
					g.setColor(Color.GRAY);
					int padding = (getHeight() - getFont().getSize()) / 2;
					int inset = 3;
					g.drawString(printText, inset + 10*getText().length(), getHeight() - padding - inset);
				}
			}
		}
		
		public String printText(){
			if (datStructure == null){
				if (getText().isEmpty()){
					return "<empty>";
				} else {
					return null;
				}
			} else if (getText().isEmpty()) {
				return '(' + datStructure.name + " ID)";
			} else if (pointToEntry == null){
				return "<unknown ID>";
			} else {
				return pointToEntry.getName();
			}
		}

	}
	
	
	
	
	
	@Override
	public void insertUpdate (DocumentEvent e) {
		scanEntry();
	}
	
	@Override
	public void removeUpdate (DocumentEvent e) {
		scanEntry();
	}
	
	@Override
	public void changedUpdate (DocumentEvent e) {
		scanEntry();
	}

}
