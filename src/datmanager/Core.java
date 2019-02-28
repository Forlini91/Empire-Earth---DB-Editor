package datmanager;

import javax.swing.JOptionPane;

import datstructure.DatStructure;
import gui.EESplashScreen;
import gui.FrameMain;

/**
 * Core class. Contains the method main, the main data loaded by the program and some useful methods
 *
 * @author MarcoForlini
 */
public class Core {

	private static final String[] editorModeChoices = new String[] { "Vanilla", "Art of Conquest", "Exit" };

	/** If true, the editor is in AOC mode */
	private static boolean AOC = false;

	/**
	 * Check if the editor is in AOC mode
	 *
	 * @return true if editor is in AOC mode, false otherwise
	 */
	public static boolean isAOC() { return AOC; }

	public static void main(String[] args) {
		final EESplashScreen splashScreen = new EESplashScreen();
		splashScreen.setVisible(true);
		final int answer = JOptionPane.showOptionDialog(splashScreen, "Vanilla or AOC?", "Empire Earth - DB Editor", 0, JOptionPane.QUESTION_MESSAGE, null, editorModeChoices, editorModeChoices[0]);
		if (answer == 2) {
			return;
		} else {
			AOC = (answer == 1);
		}

		final Thread languages = new Thread(Language::getList); // This makes the Language class initialize in background... SSSHHH!!!
		final Thread datStructures = new Thread(DatStructure::initAllStructures);
		languages.start();
		datStructures.start();

		try {
			languages.join();
			datStructures.join();
		} catch (final InterruptedException e) {
			Util.printException(splashScreen, e, true);
			return;
		}

		splashScreen.setVisible(false);
		FrameMain.instance.setVisible(true);
	}

	/** No need to instantiate this */
	private Core() {
	}

}
