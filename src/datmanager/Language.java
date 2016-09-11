package datmanager;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import gui.GUI;

/**
 * Represent a single entry in the hardcoded language files
 * @author MarcoForlini
 */
public class Language implements Comparable<Language> {
	
	/** Language ID */
	public final int ID;
	
	/** Language text */
	public final String text;
	
	/** Vector of language entries */
	public static final Vector<Language> LIST;
	
	/** Map every language code to the relative language entry */
	public static final Map<Integer, Language> MAP;
	
	static {
		Vector<Language> v = new Vector<>(6000);
		Map<Integer, Language> m;
		try (InputStream str = Core.class.getResourceAsStream(Core.AOC ? "Language AOC.txt" : "Language Vanilla.txt");
				Scanner br = new Scanner(str)) {
			while (br.hasNextLine()){
				v.addElement(new Language(br.nextLine()));
			}
			m = v.stream().collect(Collectors.toMap(l -> l.ID, l -> l));
		} catch (Exception e){
			m = new HashMap<>(0);
			JOptionPane.showMessageDialog(null, "An error occurred while reading the language file:\n" + e.getMessage() + '\n' + e.getCause(), "Language file", JOptionPane.WARNING_MESSAGE, GUI.IMAGE_ICON);
		}
		LIST = v;
		MAP = m;
	}
	
	
	
	
	
	/**
	 * Create a new language entry with the given raw text read from the file.
	 * @param entry	The raw text
	 */
	public Language (String entry){
		int indexID = entry.indexOf(',');
		ID = Integer.valueOf(entry.substring(0, indexID));
		text = entry.substring(indexID+3, entry.length()-1);
	}
	
	/**
	 * Create a new language entry with the given code and text
	 * @param ID	The ID
	 * @param text	The text
	 */
	public Language (int ID, String text){
		this.ID = ID;
		this.text = text;
	}
	
	/**
	 * Create a new language entyr with the given entry (ID, text)
	 * @param entry		The entry (ID, text)
	 */
	public Language (Entry<Integer, String> entry){
		ID = entry.getKey();
		text = entry.getValue();
	}
	
	@Override
	public int compareTo (Language o) {
		return Integer.compare(ID, o.ID);
	}
	
	@Override
	public String toString(){
		return "(" + ID + ") " + text;
	}
	
}
