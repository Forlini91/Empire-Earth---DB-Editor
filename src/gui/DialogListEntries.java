package gui;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultEditorKit;

import datstructure.Entry;
import gui.components.JButtonRed;
import gui.components.JCheckBoxExtended;
import gui.components.JScrollPaneRed;
import gui.misc.EEScrollBarUI;
import gui.misc.GridBagConstraintsExtended;
import gui.misc.GridBagLayoutExtended;


/**
 * In the {@code DialogListEntries} dialog the user can see and copy/paste the full list of entries.
 * This dialog show this list of entries.
 *
 * @author MarcoForlini
 */
public class DialogListEntries extends JDialog {

	private static final long serialVersionUID = 8712716194082493065L;

	/**
	 * Create a new {@link DialogSearchFieldResults}
	 *
	 * @param parent  The parent frame
	 * @param entries The list of entries
	 */
	public DialogListEntries(Frame parent, List<Entry> entries) {
		super(parent, ModalityType.DOCUMENT_MODAL);

		final List<Entry> entriesClean = entries.stream().filter(Entry::isDefined).collect(Collectors.toList());

		final JTextArea textArea = new JTextArea();
		textArea.setLineWrap(false);
		textArea.setEditable(false);
		textArea.setInheritsPopupMenu(true);

		final JScrollPane dlgScrollPane = new JScrollPaneRed(textArea, "All entries");
		getContentPane().setBackground(GUI.COLOR_UI_BACKGROUND);
		dlgScrollPane.getViewport().setInheritsPopupMenu(true);
		dlgScrollPane.setOpaque(false);
		dlgScrollPane.getViewport().setOpaque(false);
		dlgScrollPane.getVerticalScrollBar().setInheritsPopupMenu(true);
		dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());

		final JPopupMenu popupMenu = new JPopupMenu();
		final JMenuItem menuSelectAll = new JMenuItem("Select all");
		menuSelectAll.addActionListener(e -> {
			textArea.selectAll();
			SwingUtilities.invokeLater(() -> dlgScrollPane.getVerticalScrollBar().setValue(0));
		});
		final JMenuItem menuCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		menuCopy.setText("Copy");
		popupMenu.add(menuSelectAll);
		popupMenu.add(menuCopy);
		SwingUtilities.invokeLater(() -> textArea.setComponentPopupMenu(popupMenu));

		final JCheckBoxExtended hideUndefined = new JCheckBoxExtended("Hide undefined", true);
		final JCheckBoxExtended hideID = new JCheckBoxExtended("Hide ID");
		final JCheckBoxExtended hideName = new JCheckBoxExtended("Hide name");

		hideUndefined.addActionListener(e -> buildList(textArea, hideUndefined, hideID, hideName, entries, entriesClean));
		hideID.addActionListener(e -> {
			hideName.setEnabled(!hideID.isSelected());
			buildList(textArea, hideUndefined, hideID, hideName, entries, entriesClean);
		});
		hideName.addActionListener(e -> {
			hideID.setEnabled(!hideName.isSelected());
			buildList(textArea, hideUndefined, hideID, hideName, entries, entriesClean);
		});
		final JButton dlgClose = new JButtonRed("Close");
		dlgClose.addActionListener(al -> dispose());

		setTitle("All entries");
		setBounds(GUI.getBounds(this, 0.6, 0.8));
		setLayout(new GridBagLayoutExtended(new int[] { 200 }, new int[] { 400, 25, 25, 25, 40 }, new double[] { 1.0 }, new double[] { 1.0, 0, 0, 0, 0, 0 }));
		add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		add(hideUndefined, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		add(hideID, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		add(hideName, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 3));
		add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 4));
		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		buildList(textArea, hideUndefined, hideID, hideName, entries, entriesClean);
	}

	/**
	 * Build the list of entries
	 *
	 * @param textArea      The text area
	 * @param hideUndefined CheckBox which hide the undefined fields
	 * @param hideID        CheckBox which hide the IDs
	 * @param hideName      CheckBox which hide the names
	 * @param entriesAll    The list of entries
	 * @param entriesClean  The list of clean entries
	 */
	public static void buildList(JTextArea textArea, JCheckBox hideUndefined, JCheckBox hideID, JCheckBox hideName, List<Entry> entriesAll, List<Entry> entriesClean) {
		final StringBuilder sb = new StringBuilder();
		Consumer<Entry> builder;
		if (hideID.isSelected()) {
			builder = entry -> sb.append(entry.getTrimmedName());
		} else if (hideName.isSelected()) {
			builder = entry -> sb.append(entry.getID());
		} else {
			builder = entry -> sb.append("(" + entry.getID() + ") " + entry.getTrimmedName());
		}
		final List<Entry> entries = hideUndefined.isSelected() ? entriesClean : entriesAll;
		final int n = entries.size();
		if (n > 0) {
			builder.accept(entries.get(0));
			for (int i = 1; i < n; i++) {
				sb.append('\n');
				builder.accept(entries.get(i));
			}
		}
		textArea.setText(sb.toString());
		textArea.setCaretPosition(0);
	}
}
