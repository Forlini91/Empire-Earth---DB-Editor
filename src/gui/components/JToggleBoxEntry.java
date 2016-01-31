package gui.components;

import java.awt.Color;

import javax.swing.JToggleButton;

import datstructure.FieldStruct;

public class JToggleBoxEntry extends JToggleButton implements AbstractEntryField {
	
	private static final long serialVersionUID = 2945166199101734683L;
	
	private FieldStruct fieldStruct;
	private int index;
	
	public JToggleBoxEntry(FieldStruct fieldStruct, int index){
		this.fieldStruct = fieldStruct;
		this.index = index;
		if (fieldStruct.name != null){
			setText(index + " " + fieldStruct.name);
			setToolTipText(fieldStruct.name);
		} else {
			setText(index + " Unknown");
			setForeground(Color.RED);
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
		return (isSelected() ? 1 : 0);
	}

	@Override
	public void setVal (Object value) {
		int val = (int) value;
		if (val == 0){
			setSelected(false);
		} else if (val == 1){
			setSelected(true);
		} else {
			throw new IllegalStateException("Entry " + index + ' ' + fieldStruct + " is defined as boolean and can't accept value: " + value);
		}
	}
	
}
