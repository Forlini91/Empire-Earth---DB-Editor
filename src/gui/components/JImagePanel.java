/**
 *
 */
package gui.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


/**
 * A panel which display an image
 *
 * @author MarcoForlini
 */
public class JImagePanel extends JPanel {

	private static final long	serialVersionUID	= 8333910792059819240L;

	public BufferedImage		image;

	public JImagePanel (BufferedImage image) {
		this.image = image;
	}

	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent (g);
		g.drawImage (image, 0, 0, null);
	}

}
