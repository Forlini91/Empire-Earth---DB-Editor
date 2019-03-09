package gui.components;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import datstructure.FieldStruct;

/**
 * A CheckBox which hold the value of a field
 * @author MarcoForlini
 */
public class JCheckBoxField extends JCheckBox implements EntryFieldInterface, ChangeListener {

	private static final long serialVersionUID = -7297266293793163972L;
	
	private FieldStruct fieldStruct;
	private int index;
	private Object defaultVal = null;
	private boolean altered = false;

	/**
	 * Create a new {@link JCheckBoxField}
	 * @param fieldStruct	The field structure
	 * @param index			The index of the field
	 */
	public JCheckBoxField(FieldStruct fieldStruct, int index){
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
	public FieldStruct getFieldStruct () {
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
	public void refreshField () {/*Do nothing*/}

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
