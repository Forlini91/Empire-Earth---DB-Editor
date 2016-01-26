package gui.elements;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import dbstructure.EntryStruct;
import dbstructure.Type;


@SuppressWarnings ("serial")
public class JTextFieldEntry extends JTextField implements AbstractEntryField {
	
	private static final Color brown = new Color(127, 51, 0);
	private static final Color unfocused = new Color(160, 160, 160);
	private static final String DON_T_LEAVE_ME_EMPTY = "Don't leave me empty!";

	private EntryStruct entryStruct;
	private final int index;
	private final String hint;
	private boolean showingHint = false;
	private final Color defaultColor;

	public JTextFieldEntry(EntryStruct entryStruct, int index, String hint){
		this.entryStruct = entryStruct;
		this.index = index;
		if (entryStruct.color == Color.RED){
			defaultColor = brown;
		} else {
			defaultColor = entryStruct.color;
		}
		setForeground(defaultColor);

		if (hint == null){
			this.hint = DON_T_LEAVE_ME_EMPTY;
		} else {
			this.hint = hint;
		}
		setColumns(10);
		if (entryStruct != null){
			setEditable(entryStruct.editable);
		}
		addFocusListener(new MyFocusListener());
		
		if (getText().isEmpty()){
			setText(null);
			setForeground(unfocused);
			showingHint = true;
		}
		setCaretPosition(0);
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
		if (entryStruct.type.ordinal() >= 6){
			setText(((String) value).trim());
		} else {
			setText(String.valueOf(value));
		}
	}

	public class MyFocusListener implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {
			if(getText().isEmpty()) {
				JTextFieldEntry.super.setText(null);
				setForeground(defaultColor);
				showingHint = false;
			}
		}
		@Override
		public void focusLost(FocusEvent e) {
			String text = JTextFieldEntry.super.getText();
			if(text.isEmpty()){
				setText(null);
			} else {
				checkResult(text);
			}
		}
	}

	@Override
	public void setText(String text){
		if(text == null || text.isEmpty()) {
			super.setText(hint);
			setForeground(unfocused);
			showingHint = true;
		} else {
			super.setText(text);
			setForeground(defaultColor);
			showingHint = false;
		}
		setCaretPosition(0);
	}

	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
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

}
