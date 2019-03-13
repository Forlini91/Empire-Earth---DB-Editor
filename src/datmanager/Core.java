package datmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;

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

	private static String gameDirectory = null;

	/**
	 * Check if the editor is in AOC mode
	 *
	 * @return true if editor is in AOC mode, false otherwise
	 */
	public static boolean isAOC() { return AOC; }

	private static final File DataDirectory = Paths.get("EEEditorData").toFile();

	public static File getDataDirectory() { return new File(DataDirectory, Core.isAOC() ? "AOC" : "Vanilla"); }

	public static String getGameDirectory() { return gameDirectory; }

	public static void main(String[] args) {
		final EESplashScreen splashScreen = new EESplashScreen();
		splashScreen.setVisible(true);

		final int answer = JOptionPane.showOptionDialog(splashScreen, "Vanilla or AOC?", "Empire Earth - DB Editor", 0, JOptionPane.QUESTION_MESSAGE, null, editorModeChoices, editorModeChoices[0]);
		if (answer == 2) {
			System.exit(0);
		} else {
			AOC = (answer == 1);
		}

		final Thread registryThread = new Thread(Core::readGameFolder);
		final Thread languages = new Thread(Language::getList); // This makes the Language class initialize in background... SSSHHH!!!
		final Thread datStructures = new Thread(DatStructure::initAllStructures);
		registryThread.start();
		languages.start();
		datStructures.start();

		try {
			registryThread.join();
			languages.join();
			datStructures.join();
		} catch (final InterruptedException e) {
			Util.printException(splashScreen, e, true);
			return;
		}

		splashScreen.setVisible(false);
		FrameMain.instance.setVisible(true);
	}

	private static void readGameFolder() {
		final String location = AOC ? "HKCU\\Software\\Mad Doc Software\\EE-AOC" : "HKCU\\Software\\SSSI\\Empire Earth";
		try {
			final Process processVolume = readRegistry(location, "Installed From Volume");
			final Process processDirectory = readRegistry(location, "Installed From Directory");
			processVolume.waitFor();
			processDirectory.waitFor();
			try (Scanner volumeScanner = new Scanner(processVolume.getInputStream());
					Scanner directoryScanner = new Scanner(processDirectory.getInputStream())) {
				final var volume = volumeScanner.tokens().collect(Collectors.joining(" "));
				final var directory = directoryScanner.tokens().collect(Collectors.joining(" "));
				gameDirectory = volume.substring(volume.indexOf("REG_SZ") + 6).strip() + directory.substring(directory.indexOf("REG_SZ") + 6).strip();
				System.out.println("Found game base directory: " + gameDirectory);
			}
		} catch (final IOException | InterruptedException exc) {
		}
	}

	private static Process readRegistry(String location, String key) throws IOException {
		return Runtime.getRuntime().exec("reg query \"" + location + "\" /v \"" + key + "\"");
	}

	/** No need to instantiate this */
	private Core() {
	}

}
