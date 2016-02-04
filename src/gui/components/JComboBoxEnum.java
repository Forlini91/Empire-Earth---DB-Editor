package gui.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import constants.EnumValue;
import datstructure.FieldStruct;
import gui.FrameEditor;


public class JComboBoxEnum extends JComboBox <EnumValue> implements AbstractEntryField, ItemListener {

	private static final long serialVersionUID = -5787229930995728192L;
	
	private FieldStruct fieldStruct;
	private int index;
	private Object defaultVal = null;
	private boolean altered = false;
	
	public JComboBoxEnum(FrameEditor frameEditor, FieldStruct fieldStruct, int index){
		super(fieldStruct.values);
		this.fieldStruct = fieldStruct;
		this.index = index;
		setEditable(true);
		addItemListener(this);
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
		//		System.out.println("Getting: " + fieldStruct + " = " + obj + '(' + fieldStruct.defaultValue + '/' + defaultVal + ')');
		if (obj != null && obj instanceof EnumValue){
			return ((EnumValue) obj).getValue();
		} else {
			return obj;
		}
	}

	@Override
	public void setVal(Object value){
		defaultVal = value;
		for (EnumValue enumValue : fieldStruct.values){
			if (value.equals(enumValue.getValue())){
				setSelectedItem(enumValue);
				altered = false;
				return;
			}
		}
		setSelectedItem(value);
		altered = false;
	}

	@Override
	public boolean isAltered () {
		return altered;
	}

	@Override
	public Object getDefaultVal () {
		return defaultVal;
	}
	
	@Override
	public void itemStateChanged (ItemEvent e) {
		altered = true;
	}

}
