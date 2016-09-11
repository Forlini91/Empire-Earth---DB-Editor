package gui.components;

import javax.swing.JLabel;

import datstructure.FieldStruct;

/**
 * A JLabel which descript the field
 * @author MarcoForlini
 *
 */
public class JLabelField extends JLabel {

	private static final long serialVersionUID = 8120384566122030551L;

	/**
	 * Create a new {@link JLabelField}
	 * @param fieldStruct	The field structure
	 * @param index			Index of the field
	 */
	public JLabelField(FieldStruct fieldStruct, int index){
		setText(index + " (" + fieldStruct.getSize() + ") " + fieldStruct.getName());
		setToolTipText(getText());
		setForeground(fieldStruct.getColor());
	}
	
}
