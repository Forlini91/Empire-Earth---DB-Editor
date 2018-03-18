package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import datmanager.Core;


/**
 * Class which store the global UI elements/settings used in many places
 *
 * @author MarcoForlini
 */
public class GUI {

	/** Icon used in the top-left corner in all frames/dialog */
	public static final ImageIcon	IMAGE_ICON				= new ImageIcon (GUI.class.getResource (Core.isAOC () ? "EE_Icon_AOC.png" : "EE_Icon.png"));

	/** EE logo used in the frame {@code FrameMain} */
	public static final ImageIcon	IMAGE_LOGO				= new ImageIcon (GUI.class.getResource (Core.isAOC () ? "EE_Logo_AOC.png" : "EE_Logo.png"));

	/** EE-Heaven logo used in the ABOUT dialog */
	public static final ImageIcon	IMAGE_EE_HEAVEN_LOGO	= new ImageIcon (GUI.class.getResource ("EE_Heaven.png"));



	/** Background used in all frames, windows and dialogs */
	public static final Color	COLOR_UI_BACKGROUND					= new Color (249, 241, 224);

	/** Color used in all buttons */
	public static final Color	COLOR_UI_ELEMENT					= new Color (150, 15, 15);

	/** Color used in all buttons */
	public static final Color	COLOR_UI_ELEMENT_DISABLED			= COLOR_UI_ELEMENT.darker ().darker ();

	/** Color used in all buttons (mouse over) */
	public static final Color	COLOR_UI_ELEMENT_MOUSEOVER			= COLOR_UI_ELEMENT.brighter ();

	/** Color used in all buttons (button pressed) */
	public static final Color	COLOR_UI_ELEMENT_PRESSED			= COLOR_UI_ELEMENT_MOUSEOVER.brighter ();

	/** Color used in all buttons (button highlighted) */
	public static final Color	COLOR_UI_ELEMENT_HIGHLIGHT			= new Color (15, 150, 15);

	/** Color used in all buttons (button pressed & highlighted) */
	public static final Color	COLOR_UI_ELEMENT_PRESSED_HIGHLIGHT	= new Color (15, 150, 150);



	/** Color used by normal fields */
	public static final Color	COLOR_FIELD_NORMAL		= Color.BLACK;

	/** Color used by ID fields */
	public static final Color	COLOR_FIELD_ID			= Color.BLUE;

	/** Color used by disabled ID fields */
	public static final Color	COLOR_FIELD_ID_DISABLED	= Color.CYAN.darker ();

	/** Color used by LINK fields */
	public static final Color	COLOR_FIELD_LINK		= new Color (50, 200, 50);

	/** Color used by "strange" fields */
	public static final Color	COLOR_FIELD_UNCERTAIN	= new Color (180, 180, 0);



	/**
	 * Calculate the bounds of the given component
	 *
	 * @param component The component
	 * @param width The width in pixels. If &le; 1, it will be read as fraction of the screen size
	 * @param height The height in pixels. If &le; 1, it will be read as fraction of the screen size
	 * @return The bounds for the passed component.
	 */
	public static Rectangle getBounds (Component component, double width, double height) {
		GraphicsConfiguration gc = component.getGraphicsConfiguration ();
		Rectangle rBounds = gc.getBounds ();
		Dimension dimension = new Dimension ((int) (width <= 1 ? (rBounds.width * width) : width), (int) (height <= 1 ? (rBounds.height * height) : height));
		Point point = new Point ((rBounds.width / 2) - (dimension.width / 2), (rBounds.height / 2) - (dimension.height / 2) - 25);
		return new Rectangle (point, dimension);
	}

}
