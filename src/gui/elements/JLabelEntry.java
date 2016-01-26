package gui.elements;

import javax.swing.JLabel;

import dbstructure.EntryStruct;

public class JLabelEntry extends JLabel {

	private static final long serialVersionUID = 5486028957542202352L;

	public JLabelEntry(EntryStruct entryStruct, int index){
		setText(index + " (" + entryStruct.size + ") " + entryStruct.name);
		setToolTipText(getText());
		setForeground(entryStruct.color);
	}
	
}
