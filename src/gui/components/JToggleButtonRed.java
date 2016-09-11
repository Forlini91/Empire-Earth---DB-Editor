package gui.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

import gui.GUI;


/**
 * A JToggleButton with red color and white text
 * @author MarcoForlini
 */
public class JToggleButtonRed extends JToggleButton {

	private static final long serialVersionUID = -4858968926244969621L;
	
	/**
	 * Create a new {@link JToggleButtonRed}
	 * @param text	Text of the check box
	 */
	public JToggleButtonRed(String text){
		super(text);
		super.setContentAreaFilled(false);
		setBackground(GUI.COLOR_UI_ELEMENT);
		setForeground(Color.WHITE);
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isSelected()) {
			g.setColor(GUI.COLOR_UI_ELEMENT3);
		} else if (getModel().isRollover()) {
			g.setColor(GUI.COLOR_UI_ELEMENT2);
		} else {
			g.setColor(GUI.COLOR_UI_ELEMENT);
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

}
