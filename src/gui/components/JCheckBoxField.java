package gui.components;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import datstructure.FieldStruct;

public class JCheckBoxEntry extends JCheckBox implements AbstractEntryField, ChangeListener {
	
	private static final long serialVersionUID = -7297266293793163972L;

	private FieldStruct fieldStruct;
	private int index;
	private Object defaultVal = null;
	private boolean altered = false;
	
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
		addChangeListener(this);
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
		defaultVal = value;
		if (value instanceof Integer){
			int val = (int) value;
			if (val == 0){
				setSelected(false);
			} else if (val == 1){
				setSelected(true);
			} else {
				throw new IllegalArgumentException("This value is not boolean: " + value);
			}
		} else {
			throw new IllegalArgumentException("This value is not boolean: " + value);
		}
		altered = false;
	}
	
	@Override
	public void refreshField () {}
	
	@Override
	public boolean isAltered () {
		return altered;
	}
	
	@Override
	public Object getDefaultVal () {
		return defaultVal;
	}

	@Override
	public void stateChanged (ChangeEvent e) {
		altered = true;
	}
	
}
