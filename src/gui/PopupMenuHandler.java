package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.swing.JPopupMenu;

/**
 * This class show a popup for the selected field.
 * @author MarcoForlini
 *
 */
public class PopupMenuHandler extends MouseAdapter {

	/** The popup menu */
	public JPopupMenu popupMenu;

	/** Check if the menu can be opened */
	public Predicate<MouseEvent> canOpenMenu;

	/** The action to execute when pressing the mouse on an element */
	public Consumer<MouseEvent> mousePressedHandler;

	/**
	 * Create a new {@link PopupMenuHandler}
	 * @param popupMenu				The popup menu
	 * @param canOpenMenu			Check if the menu can be opened
	 * @param mousePressedHandler	The action to execute when clicking on an element
	 */
	public PopupMenuHandler(JPopupMenu popupMenu, Predicate<MouseEvent> canOpenMenu, Consumer<MouseEvent> mousePressedHandler){
		this.popupMenu = popupMenu;
		this.canOpenMenu = canOpenMenu;
		this.mousePressedHandler = mousePressedHandler;
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
	
	/**
	 * Show the menu
	 * @param e		The mouse event
	 */
	public void showMenu(MouseEvent e){
		if (canOpenMenu.test(e)) {
			if (e.isPopupTrigger()) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
}