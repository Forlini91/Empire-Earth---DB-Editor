package gui.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbstructure.EntryStruct;

public class JPanelEntry extends JPanel {

	private static final long serialVersionUID = 990173999618803006L;

	public final int index;
	public final EntryStruct entryStruct;
	public JLabel label;
	public AbstractEntryField field;

	public JPanelEntry (EntryStruct entryStruct, int index){
		this.index = index;
		this.entryStruct = entryStruct;

		label = new JLabelEntry(entryStruct, index);
		label.setPreferredSize(new Dimension(100, 25));
		switch(entryStruct.type){
			case BOOLEAN:
				field = new JToggleBoxEntry(entryStruct, index); break;
			default:
				field = new JTextFieldEntry(entryStruct, index, null);
		}
		field.setPreferredSize(new Dimension(100, 25));
		setLayout(new GridLayout(2, 0, 0, 0));
		add(label);
		add((Component) field);
		setBorder(new EmptyBorder(4, 4, 4, 4));
		setOpaque(false);
		label.setOpaque(false);
	}

	public void setVal(Object val){
		field.setVal(val);
	}

	public Object getVal(){
		return field.getVal();
	}
}
