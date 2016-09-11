package gui.components;

import javax.swing.JCheckBox;

/**
 * Nothing special. Just a JCheckBox which automatically use setOpaque(false);
 * @author MarcoForlini
 */
public class JCheckBoxExtended extends JCheckBox {

	private static final long serialVersionUID = -2637697975478416647L;
	
	/**
	 * Create a new {@link JCheckBoxExtended}
	 * @param text	Text of the checkbox
	 */
	public JCheckBoxExtended (String text) {
		this(text, false);
	}
	
	/**
	 * Create a new {@link JCheckBoxExtended}
	 * @param text		Text of the checkbox
	 * @param selected	The initial state
	 */
	public JCheckBoxExtended (String text, boolean selected) {
		super(text, selected);
		setOpaque(false);
	}

}
