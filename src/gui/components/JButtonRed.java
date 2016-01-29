package gui.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

import dbmanager.Core;

public class JButtonRed extends JButton {

	private static final long serialVersionUID = -486162278798103701L;
	
	{
		setBackground(Core.uiColorElement);
		setForeground(Color.WHITE);
	}
	
	public JButtonRed(String name){
		super(name);
		super.setContentAreaFilled(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
			g.setColor(Core.uiColorElement3);
		} else if (getModel().isRollover()) {
			g.setColor(Core.uiColorElement2);
		} else {
			g.setColor(Core.uiColorElement);
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

}
