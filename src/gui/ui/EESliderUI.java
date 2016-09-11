package gui.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 * A customized BasicSliderUI which prints the current value
 * @author MarcoForlini
 */
public class EESliderUI extends BasicSliderUI {

	private Color thumbColor;
	
	/**
	 * @param s			The slider
	 * @param tColor	The color
	 */
	public EESliderUI(JSlider s, Color tColor) {
		super(s);
		thumbColor=tColor;
	}

	@Override
	public void paint( Graphics g, JComponent c ) {
		recalculateIfInsetsChanged();
		recalculateIfOrientationChanged();
		Rectangle clip = g.getClipBounds();

		if ( slider.getPaintTrack() && clip.intersects( trackRect ) ) {
			paintTrack( g );
		}
		if ( slider.getPaintTicks() && clip.intersects( tickRect ) ) {
			paintTicks( g );
		}
		if ( slider.getPaintLabels() && clip.intersects( labelRect ) ) {
			paintLabels( g );
		}
		if ( slider.hasFocus() && clip.intersects( focusRect ) ) {
			paintFocus( g );
		}
		if ( clip.intersects( thumbRect ) ) {
			Color savedColor = slider.getBackground();
			slider.setBackground(thumbColor);
			paintThumb( g );
			slider.setBackground(savedColor);
		}
	}
}
