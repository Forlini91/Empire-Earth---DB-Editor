package datmanager;

import java.util.Iterator;

public class StringIterator implements Iterator<String> {
	private final String text;
	private final char separatorC;
	private final String separatorS;

	private int pos = 0;

	public StringIterator(String text, char separator) {
		this.text = text;
		separatorC = separator;
		separatorS = null;
	}

	public StringIterator(String text, String separator) {
		this.text = text;
		separatorS = separator;
		separatorC = ' ';
	}

	@Override
	public boolean hasNext() {
		return pos < text.length();
	}

	@Override
	public String next() {
		int to;
		if (separatorS != null) {
			to = text.indexOf(separatorS, pos);
		} else {
			to = text.indexOf(separatorC, pos);
		}

		final String subStr;
		if (to < 0) {
			subStr = text.substring(pos);
			pos = Integer.MAX_VALUE; // Can't be lower than any other INT array size
		} else {
			subStr = text.substring(pos, to);
			pos = to + 1;
		}
		return subStr;
	}

	public String nextStripped() {
		return next().strip();
	}

}
