/**
 *
 */
package gui.misc;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.text.JTextComponent;


/**
 * @author MarcoForlini
 */
public class SelectAllFocusListener implements FocusListener {

	public static SelectAllFocusListener instance = new SelectAllFocusListener ();

	private SelectAllFocusListener () {}

	@Override
	public void focusGained (FocusEvent e) {
		((JTextComponent) e.getSource ()).selectAll ();
	}

	@Override
	public void focusLost (FocusEvent e) {
		((JTextComponent) e.getSource ()).select (0, 0);
	}

}
