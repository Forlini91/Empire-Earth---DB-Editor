package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import datmanager.Core;
import datmanager.DatFile;
import gui.components.JButtonRed;
import gui.components.JToggleButtonRed;
import gui.ui.EEScrollBarUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;


public class DialogSelectFiles extends JDialog {

	private static final long serialVersionUID = 4743010835668668611L;
	private final JPanel contentPane = new JPanel();
	private GridBagLayoutExtended gridBagLayout = new GridBagLayoutExtended(new int[]{240, 240}, new int[]{400, 25, 35, 35}, new double[]{0.5, 0.5}, new double[]{1.0, 0, 0, 0});
	private JPanel scrollPanePanel = new JPanel();
	private JScrollPane scrollPane = new JScrollPane(scrollPanePanel);
	
	private JFileContainer[] checkBoxFiles;
	private boolean confirm = false;
	
	{
		setContentPane(contentPane);
		setBounds(Core.getBounds(this, 0.6, 0.6));
		JLabel label = new JLabel("Select which files to load");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JButton selectAllButton = new JButtonRed("Select all");
		JButton deselectAllButton = new JButtonRed("Deselect all");
		JButton okButton = new JButtonRed("OK");
		JButton cancelButton = new JButtonRed("Cancel");
		getRootPane().setDefaultButton(okButton);

		contentPane.setLayout(gridBagLayout);
		contentPane.setBackground(Core.UI_COLOR_BACKGROUND);
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		contentPane.add(scrollPane, new GridBagConstraintsExtended(0, 0, 20, 0, 0, 0, 2, 1));
		contentPane.add(selectAllButton, new GridBagConstraintsExtended(0, 0, 0, 2, 0, 1));
		contentPane.add(deselectAllButton, new GridBagConstraintsExtended(0, 2, 0, 0, 1, 1));
		contentPane.add(okButton, new GridBagConstraintsExtended(5, 0, 0, 0, 0, 2, 2, 1));
		contentPane.add(cancelButton, new GridBagConstraintsExtended(5, 0, 0, 0, 0, 3, 2, 1));
		scrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		scrollPane.setColumnHeaderView(label);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getColumnHeader().setOpaque(false);
		scrollPanePanel.setOpaque(false);
		scrollPanePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		label.setOpaque(false);
		selectAllButton.addActionListener(e -> {
			for (JFileContainer checkBoxFile : checkBoxFiles){
				if (checkBoxFile.isEnabled()) {
					checkBoxFile.setSelected(true);
				}
			}
		});
		deselectAllButton.addActionListener(e -> {
			for (JFileContainer checkBoxFile : checkBoxFiles){
				if (checkBoxFile.isEnabled()) {
					checkBoxFile.setSelected(false);
				}
			}
		});
		okButton.addActionListener(e -> {
			confirm = true;
			dispose();
		});
		cancelButton.addActionListener(e -> {
			dispose();
		});
	}

	public DialogSelectFiles (JFrame parent, List<DatFile> files, List<Boolean> loaded, boolean firstLoad) {
		super(parent, ModalityType.APPLICATION_MODAL);
		setTitle("Load files");
		checkBoxFiles = new JFileContainer[files.size()];
		int i;
		scrollPanePanel.setLayout(new GridLayout((int) Math.ceil(files.size()/5f), 5, 5, 5));
		for (i = 0; i < files.size(); i++){
			checkBoxFiles[i] = new JFileContainer(files.get(i), loaded.get(i), firstLoad);
			scrollPanePanel.add(checkBoxFiles[i]);
		}
	}

	public List<DatFile> getFilesToLoad(){
		setVisible(true);
		if (!confirm){
			return null;
		}
		List<DatFile> files = new ArrayList<>(checkBoxFiles.length);
		for (JFileContainer checkBoxFile : checkBoxFiles){
			if (checkBoxFile.isEnabled() && checkBoxFile.isSelected()){
				files.add(checkBoxFile.datFile);
			}
		}
		return files;
	}
	
	public static class JFileContainer extends JToggleButtonRed {
		private static final long serialVersionUID = 7255738274808055053L;
		DatFile datFile;
		private JFileContainer (DatFile datFile, boolean loaded, boolean firstLoad) {
			super(datFile.getName());
			this.datFile = datFile;
			if (loaded){
				setSelected(false);
				setBackground(Color.BLUE);
			} else {
				setSelected(firstLoad);
			}
			setPreferredSize(new Dimension(25, 25));
			setOpaque(false);
		}
	}

}
