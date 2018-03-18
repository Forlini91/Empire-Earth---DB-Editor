package gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import datmanager.Core;
import datmanager.Settings;
import gui.components.JImagePanel;


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


	JImagePanel				imagePanel		= new JImagePanel (image);
	private final JLabel	labelVersion	= new JLabel ("");

	/**
	 * Creates a new {@link EESplashScreen}
	 */
	public EESplashScreen () {
		initGUI ();
	}

	private void initGUI () {
		setTitle ("Empire Earth - DB Editor");
		setIconImage (IMAGE_ICON.getImage ());
		imagePanel.setBorder (new EtchedBorder (EtchedBorder.RAISED, null, null));
		setContentPane (imagePanel);

		if (image != null) {
			setSize (image.getWidth (), image.getHeight ()); // (600, 237);
		} else {
			setSize (5, 5);
		}
		setLocationRelativeTo (null);
		setUndecorated (true);

		imagePanel.setLayout (new BorderLayout (0, 0));
		labelVersion.setHorizontalAlignment (SwingConstants.RIGHT);
		imagePanel.add (labelVersion, BorderLayout.SOUTH);
		labelVersion.setText ("Version: " + Settings.VERSION + ' ');
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

}
