package gui.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

import gui.GUI;


/**
 * A JButton with red background and white text
 * 
 * @author MarcoForlini
 */
public class JButtonRed extends JButton {

	private static final long serialVersionUID = -7170626623468679349L;

	/**
	 * Create a new {@link JButtonRed}
	 * 
	 * @param text Text of the button
	 */
	public JButtonRed (String text) {
		super (text);
		super.setContentAreaFilled (false);
		setBackground (GUI.COLOR_UI_ELEMENT);
		setForeground (Color.WHITE);
	}

	@Override
	protected void paintComponent (Graphics g) {
		if (getModel ().isPressed ()) {
			g.setColor (GUI.COLOR_UI_ELEMENT_PRESSED);
		} else if (getModel ().isRollover ()) {
			g.setColor (GUI.COLOR_UI_ELEMENT_MOUSEOVER);
		} else {
			g.setColor (GUI.COLOR_UI_ELEMENT);
		}
		g.fillRect (0, 0, getWidth (), getHeight ());
		super.paintComponent (g);
	}

}
