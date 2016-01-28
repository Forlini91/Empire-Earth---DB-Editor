package gui.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class PlainDocumentEx extends PlainDocument {

	private static final long serialVersionUID = -6142113628328535633L;
	
	private int limit;
	
	PlainDocumentEx(int limit) {
		super();
		this.limit = limit;
	}
	
	@Override
	public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
		if (str == null) {
			return;
		}
		int l1 = getLength();
		int l2 = str.length();
		if (l1 + l2 <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}