package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import dbmanager.Core;
import dbmanager.DBManager;
import dbstructure.DatStructure;
import dbstructure.EntryGroup;
import gui.components.AbstractFrame;
import gui.components.AbstractGUI;
import gui.ui.EEScrollBarUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;


public class GUIMain extends AbstractFrame {
	
	private static final long serialVersionUID = 1973882004055163035L;
	public static final String S_ABOUT = "EE - DB Editor\nVersion: 1.0\nCreated by Forlins & the EE Heaven community   \nGNU General Public License v3   ";
	public static final ImageIcon IMAGE_ICON = new ImageIcon(GUIMain.class.getResource("EE_Icon.png"));
	public static final ImageIcon IMAGE_LOGO_1 = new ImageIcon(GUIMain.class.getResource("EE_Logo_1.png"));
	public static final ImageIcon IMAGE_LOGO_2 = new ImageIcon(GUIMain.class.getResource("EE_Logo_2.png"));
	public boolean firstLoad = true;
	
	private final Container contentPane = getContentPane();
	private final JPanel scrollPanePanel = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane(scrollPanePanel);
	
	
	{
		JLabel image = new JLabel(IMAGE_LOGO_1);
		JLabel scrollPaneLabel = new JLabel("Loaded data");
		scrollPaneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel labelContainer = new JPanel();
		labelContainer.add(scrollPaneLabel);
		labelContainer.setOpaque(false);
		JButton dbLoad = new JButton("Load dat file");
		JButton dbInfo = new JButton("About");

		dbLoad.setBackground(Core.uiColorElement);
		dbInfo.setBackground(Core.uiColorElement);
		dbLoad.setForeground(Color.WHITE);
		dbInfo.setForeground(Color.WHITE);
		dbLoad.addActionListener(evt -> loadFiles());
		dbInfo.addActionListener(evt -> JOptionPane.showMessageDialog(this, S_ABOUT, "About", JOptionPane.INFORMATION_MESSAGE, IMAGE_LOGO_2));

		scrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(6);
		scrollPane.setColumnHeaderView(labelContainer);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getColumnHeader().setOpaque(false);
		scrollPane.setVisible(false);
		
		scrollPanePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		scrollPanePanel.setOpaque(false);
		scrollPaneLabel.setOpaque(false);

		contentPane.setLayout(new GridBagLayoutExtended(new int[]{650, 0}, new int[]{247, 50, 50}, new double[]{0, 1.0}, new double[]{1.0, 0.0, 0.0}));
		contentPane.setBackground(Core.uiColorBackground);
		contentPane.add(image, new GridBagConstraintsExtended(5, 25, 5, 25, 0, 0));
		contentPane.add(dbLoad, new GridBagConstraintsExtended(10, 25, 5, 25, 0, 1));
		contentPane.add(dbInfo, new GridBagConstraintsExtended(10, 25, 10, 25, 0, 2));
		contentPane.add(scrollPane, new GridBagConstraintsExtended(15, 25, 15, 25, 1, 0, 0, 3));

		setBounds(AbstractGUI.getBounds(this, 650, 0.5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setAutoRequestFocus(false);
		setIconImage(IMAGE_ICON.getImage());
		setTitle("Empire Earth - DB Editor");
	}
	
	
	public GUIMain () {
		super("Main");
		setVisible(true);
	}
	

	public File selectFile(File fromFile){
		JFileChooser chooseFile = new JFileChooser(fromFile != null ? fromFile : new File("."));
		chooseFile.setAcceptAllFileFilterUsed(false);
		chooseFile.setFileHidingEnabled(false);
		chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooseFile.addChoosableFileFilter(new FileNameExtensionFilter("EE \"db\" folder", "*"));
		chooseFile.setDialogTitle("Select the folder which contains the EE dat files");
		chooseFile.setDialogType(JFileChooser.OPEN_DIALOG);
		int response = chooseFile.showDialog(null, "Select folder");

		if (response == JFileChooser.APPROVE_OPTION) {
			File choosenFile = chooseFile.getSelectedFile();
			if (choosenFile.exists()){
				return choosenFile;
			}
		}
		return null;
	}

	

	public void loadFiles(){
		File selectedFolder = null;
		List<FileDat> files;
		List<Boolean> loaded;
		do{
			selectedFolder = selectFile(selectedFolder);
			if (selectedFolder == null){
				return;
			}
			String selectedFilePath = selectedFolder.getPath();
			files = new ArrayList<>(DatStructure.values().length);
			loaded = new ArrayList<>(files.size());
			boolean canLoadOne = false;
			for(DatStructure datStructure : DatStructure.values()){
				FileDat f = new FileDat(selectedFilePath + '\\' + datStructure.fileName, datStructure);
				if (f.exists()){
					files.add(f);
					if (Core.dbData.containsKey(datStructure)){
						loaded.add(true);
					} else {
						loaded.add(false);
						canLoadOne = true;
					}
				}
			}
			if (files.isEmpty()){
				JOptionPane.showMessageDialog(this, "There's no dat file in this directory!", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (!canLoadOne){
				JOptionPane.showMessageDialog(this, "All files in this directory are already loaded!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				break;
			}
		} while (true);

		GUIFiles guiFiles = new GUIFiles(this, files, loaded, firstLoad);
		files = guiFiles.getFilesToLoad();
		if (files == null || files.isEmpty()){
			return;
		}

		ConcurrentHashMap <DatStructure, List <EntryGroup>> dataLoad = new ConcurrentHashMap<>();
		AtomicInteger counter = new AtomicInteger(0);
		for (FileDat file : files){
			counter.incrementAndGet();
			Thread t = new Thread(() -> {
				try {
					DBManager dbManager = new DBManager(file.datStructure, file);
					List<EntryGroup> entryGroups = dbManager.read();
					dataLoad.put(file.datStructure, entryGroups);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				} finally {
					counter.decrementAndGet();
				}
			});
			t.start();
		}

		int wait = 50;
		while (counter.get() != 0 && wait-- > 0){
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				return;
			}
		}
		if (counter.get() != 0){
			JOptionPane.showMessageDialog(this, "An error occurred during the loading of the files", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			firstLoad = false;
			Core.dbData.putAll(dataLoad);
		}
		int gridRows = Math.max(10, files.size());
		scrollPanePanel.setLayout(new GridLayout(gridRows, 1, 10, 10));
		for (FileDat fileDat : files){
			scrollPanePanel.add(new JButtonDat(fileDat));
		}
		scrollPane.setVisible(true);
		scrollPanePanel.revalidate();
		setBounds(AbstractGUI.getBounds(this, 960, 0.65));
	}
	
	public void openFile(FileDat file){
		System.out.println("Open: " + file.getName());
		List<EntryGroup> entryGroups = Core.dbData.get(file.datStructure);
		if (entryGroups != null){
			GUIEditor dbEditor = Core.dbEditors.get(file.datStructure);
			if (dbEditor == null){
				dbEditor = new GUIEditor(file, file.datStructure, entryGroups);
				Core.dbEditors.put(file.datStructure, dbEditor);
			}
			goToMenu(dbEditor, false, false, true, false);
		} else {
			JOptionPane.showMessageDialog(this, "An error occurred during the loading of the file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	public class FileDat extends File {
		private static final long serialVersionUID = 4028033199491184179L;
		public final DatStructure datStructure;

		private FileDat(String pathname, DatStructure datStructure){
			super(pathname);
			this.datStructure = datStructure;
		}
	}
	
	public class JButtonDat extends JButton {
		private static final long serialVersionUID = 1732488546384886505L;
		private JButtonDat(FileDat fileDat){
			setText(fileDat.datStructure.name);
			setBackground(Core.uiColorElement);
			setForeground(Color.WHITE);
			addActionListener(e -> {
				openFile(fileDat);
			});
		}
	}

}
