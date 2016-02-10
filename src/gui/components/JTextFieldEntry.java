package gui.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import datstructure.FieldStruct;
import datstructure.Type;
import gui.FrameEditor;


public class JTextFieldEntry extends JTextField implements AbstractEntryField, FocusListener, KeyListener, DocumentListener {
	
	private static final long serialVersionUID = -7134081240220832439L;
	private static final Color BROWN = new Color(127, 51, 0);

	private final FieldStruct fieldStruct;
	private final int index;
	private final Color defaultColor;
	private final FrameEditor frameEditor;
	private Object defaultVal = null;
	private boolean altered = false;
	

	public JTextFieldEntry(FrameEditor frameEditor, FieldStruct fieldStruct, int index){
		this.frameEditor = frameEditor;
		this.fieldStruct = fieldStruct;
		this.index = index;
		if (fieldStruct.getColor() == Color.RED){
			defaultColor = BROWN;
		} else {
			defaultColor = fieldStruct.getColor();
		}
		setColumns(10);
		setEditable(fieldStruct.isEditable());
		setForeground(defaultColor);
		setCaretPosition(0);

		addFocusListener(this);
		if (fieldStruct.indexStringLengthExtra >= 0){
			addKeyListener(this);
		}
		getDocument().addDocumentListener(this);
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
	public Object getVal(){
		switch(fieldStruct.getType()){
			case STRING:
				return getText();
			case FLOAT:
				return Float.valueOf(getText());
			default:
				if (getText().isEmpty()){
					return 0;
				} else {
					return Integer.valueOf(getText());
				}
		}
	}

	@Override
	public void setVal (Object value) {
		defaultVal = value;
		if (fieldStruct.getType() == Type.STRING){
			setText(((String) value).trim());
		} else {
			setText(String.valueOf(value));
		}
		altered = false;
	}
	
	@Override
	public boolean isAltered (){
		return altered;
	}

	@Override
	public Object getDefaultVal () {
		return defaultVal;
	}

	@Override
	public void refreshField () {}

	@Override
	public void focusGained(FocusEvent e) {
		repaint();
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		repaint();
	}
	
	@Override
	public void keyTyped (KeyEvent e) {
		SwingUtilities.invokeLater(() -> {
			//			System.out.println("Edit: " + fieldStruct + "  >  " + getText().length());
			frameEditor.baseFields.get(fieldStruct.indexStringLengthExtra).setVal(getText().length());
		});
	}
	
	@Override
	public void keyPressed (KeyEvent e) {}
	
	@Override
	public void keyReleased (KeyEvent e) {}

	
	
	@Override
	public void insertUpdate (DocumentEvent e) {
		altered = true;
	}

	@Override
	public void removeUpdate (DocumentEvent e) {
		altered = true;
	}

	@Override
	public void changedUpdate (DocumentEvent e) {
		altered = true;
	}

}
