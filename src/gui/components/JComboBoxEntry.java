package gui.components;

import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import datmanager.Core;
import datstructure.DatContent;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.FieldStruct;


public class JComboBoxEntry extends JComboBox <Entry> implements AbstractEntryField {
	
	private static final long serialVersionUID = -5787229930995728192L;

	private FieldStruct fieldStruct;
	private int index;
	private Entry defaultSelection = null;
	private List<Entry> allEntries = null;

	public void setData(FieldStruct fieldStruct, int index, Entry defaultSelection, boolean editable){
		this.fieldStruct = fieldStruct;
		this.index = index;
		DatStructure datStructure = fieldStruct.type.datStructure;
		if (datStructure != null){
			DatContent datContent = Core.DATA.get(datStructure);
			if (datContent != null){
				allEntries = datContent.getAllEntries();
				setModel(new DefaultComboBoxModel<>(new Vector<>(allEntries)));
			}
		}
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
		int index = allEntries.indexOf(value);
		setSelectedIndex(index);
	}

}
