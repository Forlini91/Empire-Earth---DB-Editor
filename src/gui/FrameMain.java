package gui;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import datmanager.Core;
import datmanager.DatFile;
import datmanager.Settings;
import datmanager.Util;
import datstructure.DatStructure;
import gui.components.JButtonDat;
import gui.components.JButtonRed;
import gui.components.JScrollPaneRed;
import gui.misc.EEScrollBarUI;
import gui.misc.GridBagConstraintsExtended;
import gui.misc.GridBagLayoutExtended;

/**
 * The main window
 *
 * @author MarcoForlini
 */
public class FrameMain extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1973882004055163035L;

	/** Text used in the ABOUT dialog */
	public static final String S_ABOUT = "EE - DB Editor\nVersion: " + Settings.VERSION + "\nCreated by Forlins & the EE Heaven community   \nGNU General Public License v3   ";

	/** Singletone intance of FrameMain */
	public static final FrameMain instance = new FrameMain();

	/** After the first load, the load dialog won't enable anymore all files by default */
	public boolean firstLoad = true;
	private final JPanel contentPane = new JPanel();
	private final JPanel scrollPanePanel = new JPanel();
	private final JScrollPane scrollPane = new JScrollPaneRed(scrollPanePanel);
	private final JButton dbLoad;

	/**
	 * Create and show the new window
	 */
	public FrameMain() {
		super("Empire Earth - " + (Core.isAOC() ? "Art of Conquest - " : "") + "DB Editor");

		dbLoad = new JButtonRed("Load dat files");
		final JButton dbInfo = new JButtonRed("About");
		dbLoad.addActionListener(this::loadFiles);
		dbInfo.addActionListener(evt -> JOptionPane.showMessageDialog(this, S_ABOUT, "About", JOptionPane.INFORMATION_MESSAGE, GUI.IMAGE_EE_HEAVEN_LOGO));

		final JLabel scrollPaneLabel = new JLabel("Loaded data");
		scrollPaneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPaneLabel.setOpaque(false);

		final JPanel labelContainer = new JPanel();
		labelContainer.add(scrollPaneLabel);
		labelContainer.setOpaque(false);

		scrollPanePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		scrollPanePanel.setOpaque(false);

		scrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(6);
		scrollPane.setColumnHeaderView(labelContainer);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getColumnHeader().setOpaque(false);
		scrollPane.setVisible(false);

		contentPane.setBackground(GUI.COLOR_UI_BACKGROUND);
		contentPane.setLayout(new GridBagLayoutExtended(new int[] { 680, 0 }, new int[] { 280, 50, 50 }, new double[] { 0, 1.0 }, new double[] { 1.0, 0.0, 0.0 }));
		contentPane.add(new JLabel(GUI.IMAGE_LOGO), new GridBagConstraintsExtended(5, 25, 5, 25, 0, 0));
		contentPane.add(dbLoad, new GridBagConstraintsExtended(10, 25, 5, 25, 0, 1));
		contentPane.add(dbInfo, new GridBagConstraintsExtended(5, 25, 10, 25, 0, 2));

		setContentPane(contentPane);
		setBounds(GUI.getBounds(this, 680, 400));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setAutoRequestFocus(false);
		setIconImage(GUI.IMAGE_ICON.getImage());
		addWindowListener(this);
	}

	private List<DatFile> getFilesInDirectory() {
		File lastDirectory = null;
		List<DatFile> allFiles;
		do {
			final File selectedDirectory = Util.selectDBDirectory(this, lastDirectory);
			if (selectedDirectory == null) {
				return null;
			}
			allFiles = Arrays.stream(DatStructure.GetLoadedStructures()).map(datStructure -> new DatFile(selectedDirectory, datStructure)).filter(DatFile::exists).collect(Collectors.toList());

			if (!allFiles.isEmpty()) {
				break;
			}

			JOptionPane.showMessageDialog(this, "There's no dat file in this directory!", "Error", JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON);
			lastDirectory = selectedDirectory;
		} while (true);

		final DialogSelectFiles frameFiles = new DialogSelectFiles(this, allFiles, firstLoad);
		final List<DatFile> selectedFiles = frameFiles.getFilesToLoad();
		if (selectedFiles == null || selectedFiles.isEmpty()) {
			return null;
		}
		return selectedFiles;
	}

	/**
	 * Load all selected files
	 *
	 * @param evt The action event
	 */
	public void loadFiles(ActionEvent evt) {
		final List<DatFile> files = getFilesInDirectory();
		if (files != null) {
			setEnabled(false);
			new Thread(() -> loadFilesThread(files, this::onLoadSucceed, this::onLoadFail)).start();
		}
	}

	/**
	 * Load the given list of files and disable (but not freeze) the calling window until finished.
	 *
	 * @param filesToLoad   The files to load
	 * @param onLoadSucceed The code to run if loading succeed
	 * @param onLoadFail    The code to run if loading fails
	 */
	private void loadFilesThread(List<DatFile> filesToLoad, Consumer<Collection<DatFile>> onLoadSucceed, Consumer<Collection<DatFile>> onLoadFail) {
		final DialogProgressBar progressDialog = new DialogProgressBar("Loading...", filesToLoad.size(), true);

		final List<LoadThread> threads = new ArrayList<>(filesToLoad.size());
		for (int i = 0; i < filesToLoad.size(); i++) {
			final var thread = new LoadThread(filesToLoad.get(i), i, progressDialog::updatePercPart);
			thread.start();
			threads.add(thread);
		}
		// loadError(datFile, e);

		try {
			final var maxTime = System.currentTimeMillis() + Settings.LOAD_MAX_WAIT;
			for (final var thread : threads) {
				while (thread.isAlive() && System.currentTimeMillis() <= maxTime) {
					Thread.sleep(50);
				}
			}
		} catch (final InterruptedException e) {
			Util.printException(this, e, true);
			return;
		}

		if (progressDialog.isDisplayable()) {
			progressDialog.dispose();
		}

		final var filesNotLoaded = threads.stream().filter(LoadThread::isFailed).peek(t -> showLoadError(t.getDatFile(), t.getError())).map(LoadThread::getDatFile).collect(Collectors.toList());
		if (filesNotLoaded.size() > 0) {
			onLoadFail.accept(filesNotLoaded);
		}

		final var filesLoaded = threads.stream().filter(LoadThread::isCompleted).map(LoadThread::getDatFile).collect(Collectors.toList());
		if (filesLoaded.size() >= 0) {
			onLoadSucceed.accept(filesLoaded);
		}
		setEnabled(true);
		requestFocus();
	}

	private static final String[] loadErrorChoices = { "Close", "Get \"Wofies Multidecompressor\" from Empire Earth Heaven", "Show stack trace" };

	private void showLoadError(DatFile datFile, Throwable e) {
		do {
			switch (JOptionPane.showOptionDialog(this, "An error occurred during the loading of " + datFile + ".\nAre you sure this file has been extracted from "
					+ (Core.isAOC() ? "Art of Conquest's (and not Vanilla game's)" : "Empire Earth's (and not Art of Conquest's)")
					+ " archive Data.ssa?\nAlso, have you decrypted the files with \"Wofies Multidecompressor\"?\nYou know, the files you extract from Data.ssa must also be decrypted (and EE Studio doesn't correctly decrypt them).\nFirst decrypt the files you've extracted, then retry the loading",
					"Error", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON, loadErrorChoices, loadErrorChoices[0])) {
				case 1:
					try {
						final URI uri = new URI("http://ee.heavengames.com/downloads/showfile.php?fileid=2543");
						if (Desktop.isDesktopSupported()) {
							Desktop.getDesktop().browse(uri);
						} else {
							JOptionPane.showMessageDialog(this, "Can't open the link", "Error", JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON);
						}
					} catch (final Exception e2) {
						JOptionPane.showMessageDialog(this, "Can't open the link", "Error", JOptionPane.ERROR_MESSAGE, GUI.IMAGE_ICON);
					}
					continue;
				case 2:
					Util.printException(this, e, true);
					continue;
				default:
					return;
			}
		} while (true);
	}

	private void onLoadSucceed(Collection<DatFile> loaded) {
		dbLoad.setVisible(false);

		DatFile.LOADED.addAll(loaded);
		for (final DatFile datFileLoaded : DatFile.LOADED) {
			datFileLoaded.buildLinks();
		}
		DatFile.LOADED.forEach(df -> df.dummyEntryGroup.sort(null));

		final int gridRows = Math.max(10, DatFile.LOADED.size());
		scrollPanePanel.setVisible(false);
		scrollPanePanel.removeAll();
		scrollPanePanel.setLayout(new GridLayout((int) Math.ceil(gridRows / 3f), 3, 6, 6));
		for (final DatFile datFile : DatFile.LOADED.parallelStream().sorted().toArray(DatFile[]::new)) {
			scrollPanePanel.add(new JButtonDat(this, datFile));
		}
		if (firstLoad) {
			contentPane.add(scrollPane, new GridBagConstraintsExtended(10, 10, 10, 10, 1, 0, 0, 3));
			firstLoad = false;
		}
		scrollPanePanel.setVisible(true);
		scrollPanePanel.revalidate();
		scrollPane.setVisible(true);
		setBounds(GUI.getBounds(this, 1280, 0.7));
	}

	private void onLoadFail(Collection<DatFile> notLoaded) {
		Util.printError(this, "I couldn't load all files. Missing files:\n" + notLoaded.stream().map(DatFile::getName).collect(Collectors.joining(", ")), "Load failed");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		final Set<DatFile> unsaved = new HashSet<>();
		for (final DatFile datFile : DatFile.LOADED) {
			if (datFile.isLoaded() && datFile.isUnsaved()) {
				unsaved.add(datFile);
			}
		}
		if (!unsaved.isEmpty()) {
			final String[] choices = { "Save", "Don't save", "Cancel" };
			switch (JOptionPane.showOptionDialog(this, "There are unsaved changes. Do you want to save all unsaved files?", "Unsaved changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, GUI.IMAGE_ICON, choices, choices[0])) {
				case 2:
				case JOptionPane.CLOSED_OPTION:
					return;
				case 0:
					unsaved.forEach(datFile -> datFile.saveFile(this));
					// Don't do break. We're going to exit anyway.
				case 1:
					System.exit(0);
			}
		} else {
			System.exit(0);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		/* Do nothing */}

	@Override
	public void windowClosed(WindowEvent e) {
		/* Do nothing */}

	@Override
	public void windowIconified(WindowEvent e) {
		/* Do nothing */}

	@Override
	public void windowDeiconified(WindowEvent e) {
		/* Do nothing */}

	@Override
	public void windowActivated(WindowEvent e) {
		/* Do nothing */}

	@Override
	public void windowDeactivated(WindowEvent e) {
		/* Do nothing */}

}
