package gui.components;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import constants.Element;
import dbstructure.EntryStruct;


@SuppressWarnings ("serial")
public class JComboBoxEntry<E extends Element> extends JComboBox <E> implements AbstractEntryField {

	private EntryStruct entryStruct;
	private int index;
	private E defaultSelection = null;
	private Class<E> enumClass;
	
	public void setData(EntryStruct entryStruct, int index, Class<E> enumClass, E defaultSelection, boolean editable){
		this.entryStruct = entryStruct;
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
	public EntryStruct getEntryStruct () {
		return entryStruct;
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
