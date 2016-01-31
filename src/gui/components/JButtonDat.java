package gui.components;

import java.awt.Window;

import datmanager.Core;
import datstructure.DatContent;

public class JButtonDat extends JButtonRed {
	
	private static final long serialVersionUID = -1271897565206154222L;

	public JButtonDat(Window parent, DatContent datContent){
		super (datContent.datStructure.name);
		addActionListener(e -> {
			Core.openFile(parent, datContent);
		});
	}
}