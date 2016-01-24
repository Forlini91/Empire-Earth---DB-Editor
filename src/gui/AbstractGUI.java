package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;

public interface AbstractGUI {
	
	void mainMenu ();
	
	void goToMenu(AbstractGUI menu, boolean freezeThis, boolean closeThis, boolean savePrevious, boolean deletePrevious);

	void previousMenu ();

	void setPreviousMenu(AbstractGUI previousMenu);
	
	AbstractGUI getPreviousMenu ();

	void onLostFocusEvent (WindowEvent e);
	
	void onGainFocusEvent (WindowEvent e);
	
	void onStateChangeEvent (WindowEvent e);
	
	void onDeactivateEvent (WindowEvent e);
	
	void onActivateEvent (WindowEvent e);
	
	void onCloseEvent (WindowEvent e);
	
	void onReopenEvent (WindowEvent e);
	
	void onOpenEvent (WindowEvent e);
	
	static Rectangle getBounds(Component component, double widthPercent, double heightPercent){
		GraphicsConfiguration gc = component.getGraphicsConfiguration();
		Rectangle rBounds = gc.getBounds();
		Dimension dimension = new Dimension((int) (rBounds.width*widthPercent), (int) (rBounds.height*heightPercent));
		Point point = new Point((rBounds.width / 2) - (dimension.width / 2), (rBounds.height / 2) - (dimension.height / 2) - 25);
		return new Rectangle(point, dimension);
	}

	void setVisible(boolean visible);

	boolean isVisible();
	
	void setEnabled(boolean enabled);
	
	boolean isEnabled();

	
}
