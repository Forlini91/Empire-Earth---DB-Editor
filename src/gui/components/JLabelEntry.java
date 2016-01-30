package gui.components;

import javax.swing.JLabel;

import datstructure.FieldStruct;

public class JLabelEntry extends JLabel {
	
	private static final long serialVersionUID = 8120384566122030551L;
	
	public JLabelEntry(FieldStruct fieldStruct, int index){
		setText(index + " (" + fieldStruct.size + ") " + fieldStruct.name);
		setToolTipText(getText());
		setForeground(fieldStruct.color);
	}

}
