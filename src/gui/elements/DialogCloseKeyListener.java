package gui.elements;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;

/**
 * Listener for the dialogs. Allow the user to close the dialog with the ESC key
 * @author MarcoForlini
 *
 */
public class DialogCloseKeyListener extends KeyAdapter {
	private final JDialog dialog;
	public DialogCloseKeyListener(JDialog dialog){
		this.dialog = dialog;
	}
	
	@Override
	public void keyPressed (KeyEvent e) {
		System.out.println("Key pressed: " + e.getKeyChar() + " " + e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			dialog.dispose();
		}
	}
}