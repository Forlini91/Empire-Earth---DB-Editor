package gui.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import dbmanager.Core;

public class EEScrollBarUI extends BasicScrollBarUI {
	
	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		g.setColor(Core.uiColorBackground);
		g.translate(trackBounds.x, trackBounds.y);
		g.fillRect(0, 0, trackBounds.width-4, trackBounds.height);
		g.translate(-trackBounds.x, -trackBounds.y);
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		super.paintThumb(g, c, thumbBounds);
		g.setColor(Core.uiColorElement);
		g.translate(thumbBounds.x, thumbBounds.y);
		g.fillRect(2, 2, thumbBounds.width - 4, thumbBounds.height - 4);
		g.translate(-thumbBounds.x, -thumbBounds.y);
	}

	@Override
	protected JButton createDecreaseButton(int orientation) {
		BasicArrowButton button = new BasicArrowButton(orientation,
				UIManager.getColor("ScrollBar.thumb"),
				UIManager.getColor("ScrollBar.thumbShadow"),
				Color.WHITE,
				UIManager.getColor("ScrollBar.thumbHighlight"));
		button.setBackground(Core.uiColorElement);
		button.setForeground(Color.WHITE);
		return button;
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		JButton button = new BasicArrowButton(orientation,
				UIManager.getColor("ScrollBar.thumb"),
				UIManager.getColor("ScrollBar.thumbShadow"),
				Color.WHITE,
				UIManager.getColor("ScrollBar.thumbHighlight"));
		button.setBackground(Core.uiColorElement);
		button.setForeground(Color.WHITE);
		return button;
	}
}
