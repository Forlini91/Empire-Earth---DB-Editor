package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datmanager.Core;


/**
 * The program splash screen
 *
 * @author MarcoForlini
 */
public class EESplashScreen extends JFrame {

	private static final long serialVersionUID = 1L;


	private static final BufferedImage	image		= Core.readBufferedImage (EESplashScreen.class.getResource ("EE_SplashScreen.jpg"));
	private static final String[]		buttons		= new String[] { "Vanilla", "Art of Conquest", "Exit" };
	private static final ImageIcon		IMAGE_ICON	= new ImageIcon (GUI.class.getResource ("EE_Icon.png"));



	/**
	 * Creates a new {@link EESplashScreen}
	 */
	public EESplashScreen () {
		setTitle ("Empire Earth - DB Editor");
		setIconImage (IMAGE_ICON.getImage ());
		if (image != null) {
			setContentPane (new JImagePanel (image));
			setSize (image.getWidth (), image.getHeight ());
			// setSize (600, 237);
		} else {
			setSize (5, 5);
		}
		setLocationRelativeTo (null);
		setUndecorated (true);
	}

	/**
	 * Ask for the editor type
	 *
	 * @return true if AOC, false for vanilla, null to exit
	 */
	public boolean getEditorMode () {
		setVisible (true);
		int answer = JOptionPane.showOptionDialog (this, "Vanilla or AOC?", "Empire Earth - DB Editor", 0, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
		setVisible (false);
		switch (answer) {
			case 0:
				return false;
			case 1:
				return true;
			default:
				System.exit (0);
				return false;
		}
	}



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

}
