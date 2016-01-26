package gui.elements;

import java.awt.Dimension;

import dbstructure.EntryStruct;


public interface AbstractEntryField {
	
	EntryStruct getEntryStruct();
	
	int getIndex();
	
	boolean isFieldCompiled();
	
	void setVal(Object value);

	Object getVal();

	public void setVisible(boolean visible);
	
	public void setPreferredSize(Dimension dimension);
	
	public void resetColor();

}
