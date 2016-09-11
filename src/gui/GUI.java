package gui;

import java.awt.Color;

import javax.swing.ImageIcon;

import datmanager.Core;

/**
 * Class which store the global UI elements/settings used in many places
 * @author MarcoForlini
 */
public class GUI {
	
	/** Icon used in the top-left corner in all frames/dialog */
	public static final ImageIcon IMAGE_ICON = new ImageIcon(GUI.class.getResource("EE_Icon" + (Core.AOC ? "_AOC" : "") + ".png"));
	
	/** EE logo used in the frame {@code FrameMain} */
	public static final ImageIcon IMAGE_LOGO = new ImageIcon(GUI.class.getResource("EE_Logo" + (Core.AOC ? "_AOC" : "") + ".png"));
	
	/** EE-Heaven logo used in the ABOUT dialog */
	public static final ImageIcon IMAGE_EE_HEAVEN_LOGO = new ImageIcon(GUI.class.getResource("EE_Heaven.png"));

	
	
	/** Background used in all frames, windows and dialogs */
	public static final Color COLOR_UI_BACKGROUND = new Color(249, 241, 224);

	/** Color used in all buttons */
	public static final Color COLOR_UI_ELEMENT = new Color (150, 15, 15);

	/** Color used in all buttons (mouse over) */
	public static final Color COLOR_UI_ELEMENT2 = COLOR_UI_ELEMENT.brighter();

	/** Color used in all buttons (button pressed) */
	public static final Color COLOR_UI_ELEMENT3 = COLOR_UI_ELEMENT2.brighter();

	

	/** Color used by normal fields */
	public static final Color COLOR_FIELD_NORMAL = Color.BLACK;
	
	/** Color used by ID fields */
	public static final Color COLOR_FIELD_ID = Color.BLUE;

	/** Color used by disabled ID fields */
	public static final Color COLOR_FIELD_ID_DISABLED = Color.CYAN.darker();

	/** Color used by LINK fields */
	public static final Color COLOR_FIELD_LINK = new Color(50, 200, 50);

	/** Color used by "strange" fields */
	public static final Color COLOR_FIELD_UNCERTAIN = new Color(180, 180, 0);

}
