package gui.components;

import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.border.Border;

import datmanager.DatFile;

/**
 * A JButton which open a window
 * @author MarcoForlini
 */
public class JButtonDat extends JButtonRed {
	
	private static final long serialVersionUID = -1271897565206154222L;

	/**
	 * The default border of this button
	 */
	public final Border defaultBorder = getBorder();
	
	/**
	 * Create a new {@link JButtonDat}
	 * @param parent		The parent window
	 * @param datFile		The datFile to load
	 */
	public JButtonDat(Window parent, DatFile datFile){
		super (datFile.datStructure.name);
		datFile.datButton = this;
		addActionListener(e -> {
			datFile.openInEditor(parent, (e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK);
		});
	}
	
}