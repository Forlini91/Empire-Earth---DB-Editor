package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datmanager.Core;

/**
 * The program splash screen
 * @author MarcoForlini
 */
public class EESplashScreen extends JFrame {

	private static final long serialVersionUID = -5296485583875576204L;
	private static final BufferedImage image;
	private static final String[] buttons = new String[]{"Vanilla", "Art of Conquest", "Exit"};
	private static final ImageIcon IMAGE_ICON = new ImageIcon(GUI.class.getResource("EE_Icon.png"));
	static {
		BufferedImage img;
		try {
			img = ImageIO.read(EESplashScreen.class.getResource("EE_SplashScreen.jpg"));
		} catch (IOException e) {
			img = null;
			Core.printException(null, e, "Error while loading the splash screen", "Error");
		}
		image = img;
	}


	/**
	 * Creates a new {@link EESplashScreen}
	 */
	public EESplashScreen() {
		setTitle("Empire Earth - DB Editor");
		setIconImage(IMAGE_ICON.getImage());
		if (image != null){
			setContentPane(new JImagePanel());
			setSize(600, 237);
		} else {
			setSize(5, 5);
		}
		setLocationRelativeTo(null);
		setUndecorated(true);
	}
	
	/**
	 * Ask for the editor type
	 * @return	true if AOC, false for vanilla, null to exit
	 */
	public Boolean askEditorType(){
		setVisible(true);
		int answer = JOptionPane.showOptionDialog(this, "Vanilla or AOC?", "Empire Earth - DB Editor", 0, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
		setVisible(false);
		switch (answer){
			case 0: return false;
			case 1: return true;
			default: System.exit(0); return null;
		}
	}



	/**
	 * A panel which display an image
	 * @author MarcoForlini
	 */
	public class JImagePanel extends JPanel {

		private static final long serialVersionUID = 8333910792059819240L;

		@SuppressWarnings ("synthetic-access")
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null);
		}

	}

}
