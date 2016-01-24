package gui.elements;

import javax.swing.JLabel;

import dbstructure.EntryStruct;

public class JLabelEx extends JLabel {

	private static final long serialVersionUID = 5486028957542202352L;

	public JLabelEx(EntryStruct entryStruct, int index){
		if (entryStruct.name != null){
			setText(index + " " + entryStruct.name);
			setToolTipText(entryStruct.name);
		} else {
			setText(index + " Unknown");
		}
		setForeground(entryStruct.color);
	}
	
}
