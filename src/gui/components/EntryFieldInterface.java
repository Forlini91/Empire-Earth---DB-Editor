package gui.components;

import java.awt.Dimension;

import datstructure.FieldStruct;


public interface AbstractEntryField {

	FieldStruct getEntryStruct();

	int getIndex();

	void setVal(Object value) throws IllegalArgumentException;
	
	Object getVal();
	
	public void setVisible(boolean visible);

	public void setMinimumSize(Dimension dimension);

	public void setPreferredSize(Dimension dimension);

	public void setMaximumSize(Dimension dimension);

	public void setOpaque(boolean opaque);
	
	public void resetColor();
	
	public Object getDefaultVal();

	boolean isAltered ();

	void refreshField();
	
}