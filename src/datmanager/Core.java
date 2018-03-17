package datmanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import datstructure.DatStructure;
import gui.EESplashScreen;
import gui.FrameMain;
import gui.components.JScrollPaneRed;


/**
 * Core class. Contains the method main, the main data loaded by the program and some useful methods
 *
 * @author MarcoForlini
 */
public class Core {

	/** Convert a float number to string with a specific number of decimals and round */
	public static final NumberFormat numberFormat = NumberFormat.getInstance (Locale.ENGLISH);


	/** If true, the editor is in AOC mode */
	private static boolean AOC = false;

	/**
	 * Check if the editor is in AOC mode
	 *
	 * @return true if editor is in AOC mode, false otherwise
	 */
	public static Boolean isAOC () {
		return AOC;
	}


	static {
		numberFormat.setMinimumFractionDigits (1);
		numberFormat.setMaximumFractionDigits (6);
		numberFormat.setGroupingUsed (false);
		numberFormat.setRoundingMode (RoundingMode.HALF_EVEN);
	}



	public static void main (String[] args) {
		AOC = new EESplashScreen ().getEditorMode ();
		new Thread (Language::getList).start (); // This makes the Language class initialize in background... SSSHHH!!!
		new Thread (DatStructure::initAllStructures).start ();
		FrameMain.instance.setVisible (true);
	}



	public static BufferedImage readBufferedImage (URL url) {
		try {
			return ImageIO.read (url);
		} catch (IOException e) {
			Core.printException (null, e, "Error while loading the image: " + url.getFile (), "Error", true);
			return null;
		}
	}


	public static boolean isNumber (String text) {
		try {
			Float.valueOf (text);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	public static Float toNumber (String text) {
		try {
			return Float.valueOf (text);
		} catch (Throwable e) {
			return null;
		}
	}



	/**
	 * Convert a throwable's stack trace to String
	 *
	 * @param e The throwable
	 * @return Its stack trace
	 */
	public static String buildStackTrace (Throwable e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream ();
		PrintStream ps = new PrintStream (baos);
		e.printStackTrace (ps);
		return new String (baos.toByteArray (), StandardCharsets.UTF_8);
	}


	/**
	 * Show a message about an error and ask the user to see the stack trace of the given exception
	 *
	 * @param parent The parent component
	 * @param e The exception
	 * @param message Message to display
	 * @param title Title of the message
	 * @param alsoPrintToConsole if <code>true</code>, also print the stack trace to console
	 */
	public static void printException (Component parent, Throwable e, String message, String title, boolean alsoPrintToConsole) {
		String[] exceptionChoices = { "Close", "Show stack trace" };
		if (JOptionPane.showOptionDialog (parent, message, title, 0, JOptionPane.ERROR_MESSAGE, null, exceptionChoices, exceptionChoices[0]) == 1) {
			printException (parent, e, alsoPrintToConsole);
		}
	}

	/**
	 * Show a message with the stack trace of the given exception
	 *
	 * @param parent The parent component
	 * @param e The exception
	 * @param alsoPrintToConsole if <code>true</code>, also print the stack trace to console
	 */
	public static void printException (Component parent, Throwable e, boolean alsoPrintToConsole) {
		if (alsoPrintToConsole) {
			e.printStackTrace ();
		}
		JTextArea area = new JTextArea (buildStackTrace (e));
		area.setForeground (Color.RED);
		JScrollPane scrollPane = new JScrollPaneRed (area);
		scrollPane.setPreferredSize (new Dimension (800, 500));
		printError (parent, scrollPane, "Exception: stack trace");
	}

	/**
	 * Show a message about an error
	 *
	 * @param parent The parent component
	 * @param message Message to display
	 * @param title Title of the message
	 */
	public static void printError (Component parent, Object message, String title) {
		JOptionPane.showMessageDialog (parent, message, title, JOptionPane.ERROR_MESSAGE);
	}


	/**
	 * Show a message about a warning
	 *
	 * @param parent The parent component
	 * @param message Message to display
	 * @param title Title of the message
	 */
	public static void printWarning (Component parent, Object message, String title) {
		JOptionPane.showMessageDialog (parent, message, title, JOptionPane.WARNING_MESSAGE);
	}





	/** No need to instantiate this */
	private Core () {}

}
