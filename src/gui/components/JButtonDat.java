package gui.components;

import java.awt.Window;

import datmanager.Core;
import datmanager.DatFile;

public class JButtonDat extends JButtonRed {

	private static final long serialVersionUID = -1271897565206154222L;
	
	public JButtonDat(Window parent, DatFile datFile){
		super (datFile.datStructure.name);
		addActionListener(e -> {
			Core.openFile(parent, datFile);
		});
	}
}