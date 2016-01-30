package gui.components;

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

import datmanager.Core;
import datmanager.DatFile;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;
import datstructure.Type;
import gui.FrameEditor;


public class JTextFieldEntry extends JTextField implements AbstractEntryField, MouseListener, FocusListener, DocumentListener {

	private static final long serialVersionUID = -7134081240220832439L;
	private static final Color BROWN = new Color(127, 51, 0);
	
	private FieldStruct fieldStruct;
	private final int index;
	private final Color defaultColor;
	private JTextFieldEntryUI textUI = new JTextFieldEntryUI();
	
	private String lastText = null;
	
	private final DatStructure datStructure;
	public EntryGroup pointToGroup = null;
	public Entry pointToEntry = null;
	
	public JTextFieldEntry(FieldStruct fieldStruct, int index, String hint){
		this.fieldStruct = fieldStruct;
		this.index = index;
		if (fieldStruct.color == Color.RED){
			defaultColor = BROWN;
		} else {
			defaultColor = fieldStruct.color;
		}
		if (fieldStruct.type.datStructure != null){
			datStructure = fieldStruct.type.datStructure.get();
		} else {
			datStructure = null;
		}
		
		setUI(textUI);
		setColumns(10);
		setEditable(fieldStruct.editable);
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
	public FieldStruct getEntryStruct () {
		return fieldStruct;
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
		switch(fieldStruct.type){
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
		if (fieldStruct.type == Type.STRING){
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
	}


	
	private void checkResult(String text){
		if (fieldStruct != null){
			if (fieldStruct.type == Type.STRING){
				if (text.length() > fieldStruct.size) {
					JTextFieldEntry.super.setText(text.substring(0, 100));
					Toolkit.getDefaultToolkit().beep();
				}
			} else {
				try {
					if (fieldStruct.type == Type.FLOAT) {
						Float.valueOf(text);
					} else {
						int value = Integer.valueOf(text);
						if (fieldStruct.type == Type.BOOLEAN){
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
			List <EntryGroup> dbManager = Core.DATA.get(datStructure);
			if (dbManager != null){
				try{
					Object[] results = Core.findEntryByID(datStructure, Integer.valueOf(getText()));
					if (results != null){
						EntryGroup findGroup = (EntryGroup) results[0];
						Entry findEntry = (Entry) results[1];
						if (findGroup != pointToGroup || findEntry != pointToEntry){
							pointToGroup = findGroup;
							pointToEntry = findEntry;
							//System.out.println("Field " + fieldStruct.toString() + "   >   Matches " + findGroup + " > " + findEntry);
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
			DatFile datFile = Core.DAT_FILES.get(datStructure);
			if (datFile != null){
				FrameEditor frameEditor = Core.openFile(this, datFile);
				frameEditor.goToEntry(pointToGroup, pointToEntry);
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
		if (!getText().equals(lastText)){
			scanEntry();
			lastText = getText();
		}
	}
	
	@Override
	public void removeUpdate (DocumentEvent e) {
		if (!getText().equals(lastText)){
			scanEntry();
			lastText = getText();
		}
	}
	
	@Override
	public void changedUpdate (DocumentEvent e) {
		if (!getText().equals(lastText)){
			scanEntry();
			lastText = getText();
		}
	}
	
}
