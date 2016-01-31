package gui.components;

import java.awt.Color;

import javax.swing.JCheckBox;

import datstructure.FieldStruct;

public class JCheckBoxEntry extends JCheckBox implements AbstractEntryField {
	
	private static final long serialVersionUID = -7297266293793163972L;

	private FieldStruct fieldStruct;
	private int index;
	
	public JCheckBoxEntry(FieldStruct fieldStruct, int index){
		this.fieldStruct = fieldStruct;
		this.index = index;
		if (fieldStruct.name != null){
			setText(fieldStruct.name);
			setToolTipText(index + " " + fieldStruct.name);
		} else {
			setText(index + " Unknown");
			setForeground(Color.RED);
		}
		setHorizontalTextPosition(LEFT);
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
	public boolean isFieldCompiled(){
		return true;
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
			throw new IllegalStateException("Questo valore non � boolean: " + value);
		}
	}
	
}