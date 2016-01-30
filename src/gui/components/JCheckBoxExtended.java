package gui.components;

import javax.swing.JCheckBox;

/**
 * Nothing special. Just a JCheckBox which automatically use setOpaque(false);
 * @author MarcoForlini
 *
 */
public class JCheckBoxExtended extends JCheckBox {

	private static final long serialVersionUID = -2637697975478416647L;
	
	public JCheckBoxExtended (String text) {
		this(text, false);
	}
	
	public JCheckBoxExtended (String text, boolean selected) {
		super(text, selected);
		setOpaque(false);
	}

}
