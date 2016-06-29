package datmanager;

import java.util.Map.Entry;

public class LanguageEntry implements Comparable<LanguageEntry> {

	public final int code;
	public final String text;

	public LanguageEntry (String entry){
		int indexID = entry.indexOf(',');
		code = Integer.valueOf(entry.substring(0, indexID));
		text = entry.substring(indexID+3, entry.length()-1);
	}

	public LanguageEntry (int code, String text){
		this.code = code;
		this.text = text;
	}
	
	public LanguageEntry (Entry<Integer, String> entry){
		code = entry.getKey();
		text = entry.getValue();
	}
	
	@Override
	public int compareTo (LanguageEntry o) {
		return Integer.compare(code, o.code);
	}

	@Override
	public String toString(){
		return "(" + code + ") " + text;
	}
	
}
