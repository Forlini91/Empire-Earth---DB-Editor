package datmanager;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import gui.GUI;


/**
 * Represent a single entry in the hardcoded language files
 *
 * @author MarcoForlini
 */
public class Language implements Comparable<Language> {

	/** Vector of language entries */
	private static Vector<Language> LIST = new Vector<>(0);

	/** Map every language code to the relative language entry */
	private static Map<Integer, Language> MAP = Collections.emptyMap();



	/** Language ID */
	public final int ID;

	/** Language text */
	public final String text;

	static {
		updateLanguages();
	}

	public static void updateLanguages() {
		final File languageFile = new File(Core.getDataDirectory(), "language.txt");
		if (languageFile.exists() && languageFile.canRead()) {
			LIST.clear();
			try (Scanner scanner = new Scanner(languageFile)) {
				while (scanner.hasNextLine()) {
					final var line = scanner.nextLine();
					if (line != null && line.length() > 0) {
						LIST.add(new Language(line));
					}
				}
				MAP = LIST.stream().collect(Collectors.toMap(l -> l.ID, l -> l));
			} catch (final Exception e) {
				JOptionPane.showMessageDialog(null, "An error occurred while reading the language file", "Language file", JOptionPane.WARNING_MESSAGE, GUI.IMAGE_ICON);
				Util.printException(null, e, true);
			}
		}
	}

	public static Vector<Language> getList() { return LIST; }

	public static Map<Integer, Language> getMap() { return MAP; }





	/**
	 * Create a new language entry with the given raw text read from the file.
	 *
	 * @param entry The raw text
	 */
	public Language(String entry) {
		final int indexID = entry.indexOf(',');
		ID = Integer.valueOf(entry.substring(0, indexID));
		text = entry.substring(indexID + 3, entry.length() - 1);
	}

	/**
	 * Create a new language entry with the given code and text
	 *
	 * @param ID   The ID
	 * @param text The text
	 */
	public Language(int ID, String text) {
		this.ID = ID;
		this.text = text;
	}

	/**
	 * Create a new language entyr with the given entry (ID, text)
	 *
	 * @param entry The entry (ID, text)
	 */
	public Language(Entry<Integer, String> entry) {
		ID = entry.getKey();
		text = entry.getValue();
	}

	@Override
	public int compareTo(Language o) {
		return Integer.compare(ID, o.ID);
	}

	@Override
	public String toString() {
		return "(" + ID + ") " + text;
	}

}
