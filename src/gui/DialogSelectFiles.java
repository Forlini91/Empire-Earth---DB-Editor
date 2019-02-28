package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import datmanager.DatFile;
import datstructure.DatStructure;
import gui.components.JButtonRed;
import gui.components.JToggleButtonRed;
import gui.misc.EEScrollBarUI;
import gui.misc.GridBagConstraintsExtended;
import gui.misc.GridBagLayoutExtended;

/**
 * Dialog which allow the user to select the files he want to load
 *
 * @author MarcoForlini
 */
public class DialogSelectFiles extends JDialog {

	private static final long serialVersionUID = 4743010835668668611L;
	private final static GridBagLayoutExtended gridBagLayout = new GridBagLayoutExtended(new int[] { 240, 240 }, new int[] { 400, 25, 35, 35 }, new double[] { 0.5, 0.5 }, new double[] { 1.0, 0, 0, 0 });

	private final JPanel contentPane = new JPanel();
	private final JPanel scrollPanePanel = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane(scrollPanePanel);

	private final Map<DatStructure, DatFileButton> datFileButtonsMap;
	private final List<DatFileButton> datFileButtons;
	private boolean confirm = false;

	/**
	 * Create a new {@link DialogSelectFiles}
	 *
	 * @param parent    The parent window
	 * @param files     The list of available files
	 * @param loaded    The list of already loaded files
	 * @param firstLoad If true, this is the first load
	 */
	public DialogSelectFiles(JFrame parent, List<DatFile> files, boolean firstLoad) {
		super(parent, ModalityType.DOCUMENT_MODAL);
		setTitle("Load files");
		setContentPane(contentPane);
		setBounds(GUI.getBounds(this, 0.6, 0.7));

		final JLabel label = new JLabel("Select which files to load");
		final JButton selectAllButton = new JButtonRed("Select all");
		final JButton deselectAllButton = new JButtonRed("Deselect all");
		final JButton okButton = new JButtonRed("OK");
		final JButton cancelButton = new JButtonRed("Cancel");
		getRootPane().setDefaultButton(okButton);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setOpaque(false);

		contentPane.setLayout(gridBagLayout);
		contentPane.setBackground(GUI.COLOR_UI_BACKGROUND);
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
		scrollPanePanel.setLayout(new GridLayout((int) Math.ceil(files.size() / 5f), 5, 5, 5));

		datFileButtonsMap = new HashMap<>(files.size());
		datFileButtons = new ArrayList<>(files.size());
		for (final var datFile : files) {
			final var fileButton = new DatFileButton(datFile, firstLoad);
			datFileButtons.add(fileButton);
			scrollPanePanel.add(fileButton);
			datFileButtonsMap.put(fileButton.datFile.datStructure, fileButton);
		}

		selectAllButton.addActionListener(e -> {
			for (final DatFileButton checkBoxFile : datFileButtons) {
				if (checkBoxFile.isEnabled()) {
					checkBoxFile.setSelected(true);
				}
			}
		});
		deselectAllButton.addActionListener(e -> {
			for (final DatFileButton checkBoxFile : datFileButtons) {
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

	/**
	 * Check all toggles and return the chosen files
	 *
	 * @return The files to load
	 */
	public List<DatFile> getFilesToLoad() {
		setVisible(true);
		// Showing the dialog will halt this method here

		// This line will execute once the dialog has been closed
		if (confirm) {
			final var selectedFiles = datFileButtons.stream().filter(DatFileButton::isEnableAndSelected).map(fileButton -> fileButton.datFile).collect(Collectors.toList());
			if (!selectedFiles.isEmpty()) {
				return selectedFiles;
			}
		}

		return null;
	}

	/**
	 * A JToggleButton which can hold a dat file data
	 *
	 * @author MarcoForlini
	 */
	private class DatFileButton extends JToggleButtonRed implements MouseListener, ChangeListener {

		private static final long serialVersionUID = 1L;

		/** The dat file */
		public final DatFile datFile;

		/**
		 * Create a new {@link DatFileButton}
		 *
		 * @param datFile         The dat file associated to this button
		 * @param loaded          If true, the file has already been loaded
		 * @param firstLoad       If true, this is the first load
		 * @param scrollPanePanel
		 */
		public DatFileButton(DatFile datFile, boolean firstLoad) {
			super(datFile.getName());
			this.datFile = datFile;

			if (datFile.isLoaded()) {
				setSelected(false);
				setEnabled(false);
				setBackground(Color.BLUE);
			} else {
				setSelected(firstLoad);
			}
			setPreferredSize(new Dimension(25, 25));
			setOpaque(false);
			addMouseListener(this);
			addChangeListener(this);
		}

		private void highlightRequirements(DatFileButton fileButton) {
			for (final DatStructure requiredStructure : fileButton.datFile.datStructure.requirements) {
				final DatFileButton button = datFileButtonsMap.get(requiredStructure);
				if (button != null && button.isEnabled() && !button.isSelected() && button.highlight != GUI.COLOR_UI_ELEMENT_HIGHLIGHT) {
					button.highlight = GUI.COLOR_UI_ELEMENT_HIGHLIGHT;
					button.repaint();
					highlightRequirements(button);
				}
			}
		}

		private void highlightInverseRequirements(DatFileButton fileButton) {
			final DatStructure datStructure = fileButton.datFile.datStructure;
			for (final DatFileButton button : datFileButtons) {
				if (/* button != null && */ button.isEnabled() && button.isSelected() && button.highlight != GUI.COLOR_UI_ELEMENT_PRESSED_HIGHLIGHT && button.datFile.datStructure.requirements.contains(datStructure)) {
					button.highlight = GUI.COLOR_UI_ELEMENT_PRESSED_HIGHLIGHT;
					button.repaint();
					highlightInverseRequirements(button);
				}
			}
		}

		public boolean isEnableAndSelected() { return isEnabled() && isSelected(); }

		@Override
		public void setEnabled(boolean enabled) {
			if (!enabled || !datFile.isLoaded()) {
				super.setEnabled(enabled);
			}
		}

		@Override
		public void setSelected(boolean selected) {
			if (!selected || !datFile.isLoaded()) {
				super.setSelected(selected);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			if (isEnabled()) {
				if (isSelected()) {
					highlightInverseRequirements(this);
					highlight = GUI.COLOR_UI_ELEMENT_PRESSED_HIGHLIGHT;
				} else {
					highlightRequirements(this);
					highlight = GUI.COLOR_UI_ELEMENT_HIGHLIGHT;
				}
				repaint();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			if (isEnabled()) {
				for (final DatFileButton button : datFileButtons) {
					if (button.isEnabled() && button.highlight != null) {
						button.highlight = null;
						button.repaint();
					}
				}
				repaint();
			}
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (isEnabled()) {
				mouseExited(arg0);
				mouseEntered(arg0);
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			/* Do nothing */}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			/* Do nothing */}

		@Override
		public void stateChanged(ChangeEvent e) {
			if (isEnabled()) {
				if (isSelected()) { // Enable all requirements as well
					for (final DatStructure requiredDatStructure : datFile.datStructure.requirements) {
						final DatFileButton requiredButton = datFileButtonsMap.get(requiredDatStructure);
						if (requiredButton != null && !requiredButton.isSelected()) {
							requiredButton.setSelected(true);
						}
					}
				} else { // Disable all files which requires this
					for (final DatFileButton button : datFileButtons) {
						if (button.isSelected() && button.datFile.datStructure.requirements.contains(datFile.datStructure)) {
							button.setSelected(false);
						}
					}
				}
			}
		}

	}

}
