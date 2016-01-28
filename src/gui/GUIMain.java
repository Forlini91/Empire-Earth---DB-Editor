package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import dbmanager.Core;
import dbmanager.DBManager;
import dbstructure.DatStructure;
import dbstructure.EntryGroup;
import gui.elements.AbstractFrame;
import gui.elements.AbstractGUI;
import gui.elements.FileFilterEx;


public class GUIMain extends AbstractFrame {

	private static final long serialVersionUID = 3434390193760850398L;

	public static final String S_ABOUT = "EE - DB Editor\nVersion: 1.0\nCreated by Forlins & the EE Heaven community   \nGNU General Public License v3   ";
	public static final ImageIcon IMAGE_ICON = new ImageIcon(GUIMain.class.getResource("EE_Icon.png"));
	public static final ImageIcon IMAGE_LOGO_1 = new ImageIcon(GUIMain.class.getResource("EE_Logo_1.png"));
	public static final ImageIcon IMAGE_LOGO_2 = new ImageIcon(GUIMain.class.getResource("EE_Logo_2.png"));
	volatile AtomicInteger counter = new AtomicInteger(0);

	Container contentPane = getContentPane();

	public static final FileFilter fileFilter = new FileFilterEx("EE data file", Core.allStructures.keySet());

	public GUIMain () {
		super("Main");
		setBounds(AbstractGUI.getBounds(this,0.5, 0.5));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAutoRequestFocus(false);
		setIconImage(IMAGE_ICON.getImage());
		setTitle("Empire Earth - DB Editor");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 50, 50};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0};
		contentPane.setLayout(gridBagLayout);
		
		JLabel image = new JLabel(IMAGE_LOGO_1);
		JButton dbLoad = new JButton("Load dat file");
		dbLoad.addActionListener(evt -> loadFile());
		JButton dbInfo = new JButton("About");
		dbInfo.addActionListener(evt -> JOptionPane.showMessageDialog(this, S_ABOUT, "About", JOptionPane.INFORMATION_MESSAGE, IMAGE_LOGO_2));
		
		GridBagConstraints gbc_image = new GridBagConstraints();
		gbc_image.fill = GridBagConstraints.BOTH;
		gbc_image.insets = new Insets(5, 25, 0, 25);
		gbc_image.gridx = 0;
		gbc_image.gridy = 0;

		GridBagConstraints gbc_dbLoad = new GridBagConstraints();
		gbc_dbLoad.fill = GridBagConstraints.BOTH;
		gbc_dbLoad.insets = new Insets(10, 25, 0, 25);
		gbc_dbLoad.gridx = 0;
		gbc_dbLoad.gridy = 1;
		
		GridBagConstraints gbc_dbInfo = new GridBagConstraints();
		gbc_dbInfo.fill = GridBagConstraints.BOTH;
		gbc_dbInfo.insets = new Insets(10, 25, 10, 25);
		gbc_dbInfo.gridx = 0;
		gbc_dbInfo.gridy = 2;
		
		contentPane.add(image, gbc_image);
		contentPane.add(dbLoad, gbc_dbLoad);
		contentPane.add(dbInfo, gbc_dbInfo);
		setVisible(true);
	}

	
	public File selectFile(){
		JFileChooser chooseFile = new JFileChooser(new File("."));
		chooseFile.setAcceptAllFileFilterUsed(false);
		chooseFile.setFileHidingEnabled(false);
		chooseFile.addChoosableFileFilter(fileFilter);
		chooseFile.setVisible(true);
		chooseFile.setDialogType(JFileChooser.OPEN_DIALOG);
		int response = chooseFile.showDialog(null, "Select");
		
		if (response == JFileChooser.APPROVE_OPTION) {
			File choosenFile = chooseFile.getSelectedFile();
			if (choosenFile.exists()){
				return choosenFile;
			}
		}
		return null;
	}
	
	public void loadFile(){
		File selectedFile = selectFile();
		if (selectedFile != null){
			String selectedFilePath = selectedFile.getParent();
			for(DatStructure datStructure : Core.allStructures.values()){
				if (!Core.dbData.containsKey(datStructure)){
					File f = new File(selectedFilePath + '\\' + datStructure.fileName);
					if (f.exists()){
						counter.incrementAndGet();
						Thread t = new Thread(() -> {
							try {
								DBManager dbManager = new DBManager(datStructure, f);
								List<EntryGroup> entryGroups = dbManager.read();
								Core.dbData.put(datStructure, entryGroups);
							} catch (Exception e) {
								System.err.println(e.getMessage());
							} finally {
								counter.decrementAndGet();
							}
						});
						t.start();
					}
				}
			}
			
			while (counter.get() != 0){
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					return;
				}
			}

			DatStructure datStructure = Core.allStructures.get(selectedFile.getName().toLowerCase());
			List<EntryGroup> entryGroups = Core.dbData.get(datStructure);
			if (entryGroups != null){
				GUIEditor dbEditor = Core.dbEditors.get(datStructure);
				if (dbEditor == null){
					dbEditor = new GUIEditor(selectedFile, datStructure, entryGroups);
					Core.dbEditors.put(datStructure, dbEditor);
				}
				goToMenu(dbEditor, false, false, true, false);
			} else {
				JOptionPane.showMessageDialog(this, "Error", "An error occurred during the loading of the file", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


}
