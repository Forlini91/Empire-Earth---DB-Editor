package gui.components;

import java.awt.Color;

import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import datstructure.FieldStruct;
import gui.FrameEditor;

public class JToggleBoxEntry extends JToggleButton implements AbstractEntryField, ChangeListener {

	private static final long serialVersionUID = 2945166199101734683L;

	private FieldStruct fieldStruct;
	private int index;
	private Object defaultVal = null;
	private boolean altered = false;

	public JToggleBoxEntry(FrameEditor frameEditor, FieldStruct fieldStruct, int index){
		this.fieldStruct = fieldStruct;
		this.index = index;
		if (fieldStruct.getName() != null){
			setText(index + " " + fieldStruct.getName());
			setToolTipText(fieldStruct.getName());
		} else {
			setText(index + " Unknown");
			setForeground(Color.RED);
		}
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
				throw new IllegalArgumentException(fieldStruct + " > this value is not boolean: " + value);
			}
		} else {
			throw new IllegalArgumentException(fieldStruct + " > this value is not boolean: " + value);
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
