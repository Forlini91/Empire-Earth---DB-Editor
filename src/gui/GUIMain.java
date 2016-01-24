package gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dbmanager.DBManager;
import dbstructure.EntryGroup;


public class GUIMain extends AbstractFrame {
	
	private static final long serialVersionUID = 3434390193760850398L;
	
	public static final String S_ABOUT = "EE - DB Editor\nVersion: 1.0\nCreated by Forlins & the EE Heaven community   \nGNU General Public License v3   ";
	public static final ImageIcon IMAGE_ICON = new ImageIcon(GUIMain.class.getResource("EE_Icon.png"));
	public static final ImageIcon IMAGE_LOGO_1 = new ImageIcon(GUIMain.class.getResource("EE_Logo_1.png"));
	public static final ImageIcon IMAGE_LOGO_2 = new ImageIcon(GUIMain.class.getResource("EE_Logo_2.png"));
	
	Container contentPane = getContentPane();
	
	public static void main (String[] args) {
		EventQueue.invokeLater(() -> {
			new GUIMain();
		});
	}
	
	public GUIMain () {
		super("Main");
		setBounds(AbstractGUI.getBounds(this,0.5, 0.5));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		dbLoad.addActionListener(evt -> {
			try {
				DBManager dbManager = DBManager.selectFile();
				if (dbManager != null){
					List<EntryGroup> entryGroups = dbManager.read();
					if (entryGroups.size() == 1){
						entryGroups.get(0).name = dbManager.datStructure.fileName;
					}
					goToMenu(new GUIEditor(dbManager.file, dbManager.datStructure, entryGroups), false, false, true, false);
				}
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(this, "Error", "An error occurred during the loading of the file:\n" + exc.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		});
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
	
}
