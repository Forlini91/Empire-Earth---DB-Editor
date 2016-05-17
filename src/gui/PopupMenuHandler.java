package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import javax.swing.JPopupMenu;

/**
 * This class show a popup for the selected field.
 * @author MarcoForlini
 *
 */
public class PopupMenuFieldHandler extends MouseAdapter {
	public JPopupMenu popupMenu;
	public Consumer<MouseEvent> mousePressedHandler;
	
	public PopupMenuFieldHandler(JPopupMenu popupMenu, Consumer<MouseEvent> mousePresseHandler){
		this.popupMenu = popupMenu;
		mousePressedHandler = mousePresseHandler;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mousePressedHandler.accept(e);
		showMenu(e);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		showMenu(e);
	}

	public void showMenu(MouseEvent e){
		if (e.isPopupTrigger()) {
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}
}