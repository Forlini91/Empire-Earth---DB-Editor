package gui.components;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import constants.Element;
import datstructure.FieldStruct;


public class JComboBoxEntry<E extends Element> extends JComboBox <E> implements AbstractEntryField {
	
	private static final long serialVersionUID = -5787229930995728192L;

	private FieldStruct fieldStruct;
	private int index;
	private E defaultSelection = null;
	private Class<E> enumClass;

	public void setData(FieldStruct fieldStruct, int index, Class<E> enumClass, E defaultSelection, boolean editable){
		this.fieldStruct = fieldStruct;
		this.index = index;
		this.enumClass = enumClass;
		setModel(new DefaultComboBoxModel <E>(enumClass.getEnumConstants()));
		this.defaultSelection = defaultSelection;
		setEditable(editable);
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
		return (getSelectedItem() != defaultSelection);
	}
	
	@Override
	public Object getVal(){
		return getSelectedItem();
	}
	
	@Override
	public void setVal(Object value){
		for (E obj : enumClass.getEnumConstants()){
			if (value.equals(obj)) {
				setSelectedItem(obj);
				return;
			}
		}
		throw new IllegalArgumentException("You can't use this value");
	}

}
