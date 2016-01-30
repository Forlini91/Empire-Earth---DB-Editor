package gui.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

import datmanager.Core;

public class JToggleButtonRed extends JToggleButton {

	private static final long serialVersionUID = -4858968926244969621L;
	
	{
		setBackground(Core.UI_COLOR_ELEMENT);
		setForeground(Color.WHITE);
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	}
	
	public JToggleButtonRed(String name){
		super(name);
		super.setContentAreaFilled(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isSelected()) {
			g.setColor(Core.UI_COLOR_ELEMENT3);
		} else if (getModel().isRollover()) {
			g.setColor(Core.UI_COLOR_ELEMENT2);
		} else {
			g.setColor(Core.UI_COLOR_ELEMENT);
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

}
