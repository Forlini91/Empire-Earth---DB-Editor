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
import javax.swing.JToggleButton;

import dbmanager.Core;
import gui.GUIMain.FileDat;
import gui.components.AbstractGUI;
import gui.ui.EEScrollBarUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;


public class GUIFiles extends JDialog {

	private static final long serialVersionUID = 6953966147035750602L;
	private final JPanel contentPane = new JPanel();
	private GridBagLayoutExtended gridBagLayout = new GridBagLayoutExtended(new int[]{200, 200}, new int[]{400, 25, 35, 35}, new double[]{0.5, 0.5}, new double[]{1.0, 0, 0, 0});
	private JPanel scrollPanePanel = new JPanel();
	private JScrollPane scrollPane = new JScrollPane(scrollPanePanel);
	
	private JFileContainer[] checkBoxFiles;
	private boolean confirm = false;
	
	{
		setContentPane(contentPane);
		setBounds(AbstractGUI.getBounds(this, 0.4, 0.6));
		JLabel label = new JLabel("Select which files to load");
		JButton selectAllButton = new JButton("Select all");
		JButton deselectAllButton = new JButton("Deselect all");
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		getRootPane().setDefaultButton(okButton);

		contentPane.setLayout(gridBagLayout);
		contentPane.setBackground(Core.uiColorBackground);
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
		label.setOpaque(false);
		selectAllButton.setBackground(Core.uiColorElement);
		selectAllButton.setForeground(Color.WHITE);
		selectAllButton.addActionListener(e -> {
			for (JFileContainer checkBoxFile : checkBoxFiles){
				if (checkBoxFile.isEnabled()) {
					checkBoxFile.setSelected(true);
				}
			}
		});
		deselectAllButton.setBackground(Core.uiColorElement);
		deselectAllButton.setForeground(Color.WHITE);
		deselectAllButton.addActionListener(e -> {
			for (JFileContainer checkBoxFile : checkBoxFiles){
				if (checkBoxFile.isEnabled()) {
					checkBoxFile.setSelected(false);
				}
			}
		});
		okButton.setBackground(Core.uiColorElement);
		okButton.setForeground(Color.WHITE);
		okButton.addActionListener(e -> {
			confirm = true;
			dispose();
		});
		cancelButton.setBackground(Core.uiColorElement);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.addActionListener(e -> {
			dispose();
		});
	}

	public GUIFiles (JFrame parent, List<FileDat> files, List<Boolean> loaded, boolean firstLoad) {
		super(parent, ModalityType.APPLICATION_MODAL);
		setTitle("Load files");
		checkBoxFiles = new JFileContainer[files.size()];
		int i;
		scrollPanePanel.setLayout(new GridLayout((int) Math.ceil(files.size()/2f), 2, 5, 5));
		for (i = 0; i < files.size(); i++){
			checkBoxFiles[i] = new JFileContainer(files.get(i), loaded.get(i), firstLoad);
			scrollPanePanel.add(checkBoxFiles[i]);
		}
	}

	public List<FileDat> getFilesToLoad(){
		setVisible(true);
		if (!confirm){
			return null;
		}
		List<FileDat> files = new ArrayList<>(checkBoxFiles.length);
		for (JFileContainer checkBoxFile : checkBoxFiles){
			if (checkBoxFile.isEnabled() && checkBoxFile.isSelected()){
				files.add(checkBoxFile.file);
			}
		}
		return files;
	}
	
	private static class JFileContainer extends JToggleButton {
		private static final long serialVersionUID = 1L;
		private FileDat file;
		private JFileContainer (FileDat file, boolean loaded, boolean firstLoad) {
			this.file = file;
			setText(file.getName());
			if (loaded){
				setSelected(true);
				setEnabled(false);
			} else {
				setSelected(firstLoad);
			}
			setPreferredSize(new Dimension(25, 25));
			setOpaque(false);
		}
	}

}
