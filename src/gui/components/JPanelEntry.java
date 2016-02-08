package gui.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datstructure.FieldStruct;
import gui.FrameEditor;

public class JPanelEntry extends JPanel {

	private static final long serialVersionUID = -8432430218424230659L;

	public final int index;
	public final FieldStruct fieldStruct;
	public JLabel label;
	public AbstractEntryField field;

	public JPanelEntry (FrameEditor frameEditor, FieldStruct fieldStruct, int index){
		this.index = index;
		this.fieldStruct = fieldStruct;

		label = new JLabelEntry(fieldStruct, index);
		label.setPreferredSize(new Dimension(100, 25));
		switch(fieldStruct.getType()){
			case BOOLEAN:
				field = new JToggleBoxEntry(frameEditor, fieldStruct, index);
				break;
			case ID:
				field = new JComboBoxEntry(frameEditor, fieldStruct, index);
				break;
			case ENUM:
				field = new JComboBoxEnum(frameEditor, fieldStruct, index);
				break;
			case RANGE:
				field = new JComboBoxArray(frameEditor, fieldStruct, index);
				break;
			case LANGUAGE:
				field = new JComboBoxLanguage(frameEditor, fieldStruct, index);
				break;
			default:
				field = new JTextFieldEntry(frameEditor, fieldStruct, index);
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
		if (field.isAltered()){
			//			System.out.println(field.getEntryStruct() + ": save value " + field.getVal());
			return field.getVal();
		} else {
			return field.getDefaultVal();
		}
	}
	
	public void refreshField(){
		field.refreshField();
	}

}