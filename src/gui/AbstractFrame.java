package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

@SuppressWarnings ("serial")
public abstract class AbstractFrame extends JFrame implements AbstractGUI {
	
	private final String name;
	private static AbstractGUI mainMenu = null;
	private AbstractGUI previousMenu;
	protected boolean loadedOnce = false;
	protected boolean loaded = false;
	
	public AbstractFrame(String name){
		if (mainMenu == null){
			mainMenu = this;
		}
		this.name = name;
		addWindowListener(windowEvents);
	}
	
	/**
	 * WindowsAdapter events.
	 */
	private WindowAdapter windowEvents = new WindowAdapter(){
		@Override
		public void windowOpened(WindowEvent e) {
			super.windowOpened(e);
			onOpenEvent(e);
			loadedOnce = true;
		}
		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
			onCloseEvent(e);
			loaded = false;
			previousMenu();
		}
		@Override
		public void windowActivated(WindowEvent e) {
			super.windowActivated(e);
			if (loadedOnce && !loaded){
				onReopenEvent(e);
			}
			loaded = true;
			onActivateEvent(e);
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
			super.windowDeactivated(e);
			onDeactivateEvent(e);
			if (!isVisible()){
				loaded = false;
			}
		}
		@Override
		public void windowStateChanged(WindowEvent e) {
			super.windowStateChanged(e);
			onStateChangeEvent(e);
			if (!isVisible()){
				loaded = false;
			}
		}
		@Override
		public void windowGainedFocus(WindowEvent e) {
			super.windowGainedFocus(e);
			onGainFocusEvent(e);
		}
		@Override
		public void windowLostFocus(WindowEvent e) {
			super.windowLostFocus(e);
			onLostFocusEvent(e);
			if (!isVisible()){
				loaded = false;
			}
		}
	};

	@Override
	public void setPreviousMenu(AbstractGUI previousMenu) {
		this.previousMenu = previousMenu;
	}
	
	/**
	 * Return the previous menu
	 * @return	The previous menu
	 */
	@Override
	public AbstractGUI getPreviousMenu(){
		return previousMenu;
	}
	
	/**
	 * Update the menu when opened.
	 */
	@Override
	public void onOpenEvent(WindowEvent e){};
	
	/**
	 * Update the menu when reopened.
	 */
	@Override
	public void onReopenEvent(WindowEvent e){};
	
	/**
	 * Update the menu when closed.
	 */
	@Override
	public void onCloseEvent(WindowEvent e){};
	
	/**
	 * Update the menu when activated.
	 */
	@Override
	public void onActivateEvent(WindowEvent e){};
	
	/**
	 * Update the menu when deactivated.
	 */
	@Override
	public void onDeactivateEvent(WindowEvent e){};
	
	/**
	 * Update the menu when state change.
	 */
	@Override
	public void onStateChangeEvent(WindowEvent e){};
	
	/**
	 * Update the menu when closed.
	 */
	@Override
	public void onGainFocusEvent(WindowEvent e){};
	
	/**
	 * Update the menu when closed.
	 */
	@Override
	public void onLostFocusEvent(WindowEvent e){};
	
	/**
	 * Open the specified menu.
	 * @param menu		The menu to open
	 * @pram closeThis	The current menu is closed
	 * @param savePrevious	The specified menu will register this as the previous menu
	 */
	@Override
	public void goToMenu(AbstractGUI menu, boolean freezeThis, boolean closeThis, boolean savePrevious, boolean deletePrevious){
		if (menu == null) {
			return;
		}
		if (savePrevious) {
			menu.setPreviousMenu(this);
			System.out.println(menu + " set previous: " + this);
		} else if (deletePrevious) {
			menu.setPreviousMenu(null);
			System.out.println(menu + " set previous: null");
		}
		if (closeThis) {
			setVisible(false);
		} else if (freezeThis){
			setEnabled(false);
		}
		menu.setVisible(true);
		menu.setEnabled(true);
	}
	
	/**
	 * Close this menu and goto the previous menu.
	 */
	@Override
	public void previousMenu(){
		if (previousMenu == null) {
			System.out.println(getClass().getSimpleName() + " > load previous > " + null);
			return;
		}
		System.out.println(getClass().getSimpleName() + " > load previous > " + previousMenu.getClass().getSimpleName());
		goToMenu(previousMenu, false, true, false, false);
	}
	
	/**
	 * Close this menu and goto the main menu.
	 */
	@Override
	public void mainMenu(){
		goToMenu(mainMenu, false, true, false, true);
	}

	@Override
	public String toString(){
		return name;
	}

}
