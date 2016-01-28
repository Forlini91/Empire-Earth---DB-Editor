package gui.components;

import java.awt.Color;

import javax.swing.JToggleButton;

import dbstructure.EntryStruct;

@SuppressWarnings ("serial")
public class JToggleBoxEntry extends JToggleButton implements AbstractEntryField {

	private EntryStruct entryStruct;
	private int index;

	public JToggleBoxEntry(EntryStruct entryStruct, int index){
		this.entryStruct = entryStruct;
		this.index = index;
		if (entryStruct.name != null){
			setText(index + " " + entryStruct.name);
			setToolTipText(entryStruct.name);
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
	public EntryStruct getEntryStruct () {
		return entryStruct;
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
			throw new IllegalStateException("Entry " + index + ' ' + entryStruct + " is defined as boolean and can't accept value: " + value);
		}
	}

}
