package gui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import datmanager.DatFile;
import datstructure.Entry;
import datstructure.EntryGroup;
import gui.components.EntryFieldInterface;
import gui.components.JButtonRed;
import gui.components.JListEntry;
import gui.components.JScrollPaneRed;
import gui.components.JSearchTextField;
import gui.misc.EEScrollBarUI;
import gui.misc.GridBagConstraintsExtended;
import gui.misc.GridBagLayoutExtended;

/**
 * This dialog show all results of the search "Fields with the same value".
 *
 * @author MarcoForlini
 */
public class DialogSearchFieldResults extends JDialog {

	private static final long serialVersionUID = 2493133528817012871L;

	/**
	 * Create a new {@link DialogSearchFieldResults}
	 *
	 * @param parent  The parent frame
	 * @param entries The list of entries
	 * @param field   The selected field
	 */
	public DialogSearchFieldResults(Frame parent, DatFile datFile, List<Entry> entries, EntryFieldInterface field) {
		super(parent, ModalityType.DOCUMENT_MODAL);

		final JListEntry dlgList = new JListEntry(entries);
		final JScrollPane dlgScrollPane = new JScrollPaneRed(dlgList, "All entries with the same value: " + field.getVal());
		final JSearchTextField<Entry> dlgSearch = new JSearchTextField<>(dlgList, Entry::filterGenerator);
		final JButton dlgClose = new JButtonRed("Close");

		getContentPane().setBackground(GUI.COLOR_UI_BACKGROUND);
		dlgScrollPane.setOpaque(false);
		dlgScrollPane.getViewport().setOpaque(false);
		dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());

		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		dlgClose.addActionListener(al -> dispose());

		setTitle("For field: " + field.getIndex() + " - " + field.getFieldStruct());
		setBounds(GUI.getBounds(this, 0.6, 0.8));
		setLayout(new GridBagLayoutExtended(new int[] { 200 }, new int[] { 400, 30, 25, 50 }, new double[] { 1.0 }, new double[] { 1.0, 0, 0, 0 }));

		final JPanel entryListOptionsPanel = new JPanel();
		entryListOptionsPanel.setLayout(new GridLayout(1, 2));
		if (datFile.datStructure.indexLanguage >= 0) {
			entryListOptionsPanel.add(dlgList.localizeToggle);
		}
		entryListOptionsPanel.add(dlgList.filterToggle);
		dlgList.filterToggle.setHorizontalAlignment(SwingConstants.RIGHT);
		dlgList.filterToggle.setHorizontalTextPosition(SwingConstants.RIGHT);

		add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		add(dlgSearch, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		add(entryListOptionsPanel, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));

		dlgList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final int index = dlgList.getSelectedIndex();
				if (e.getClickCount() == 2) {
					final Entry selEntry = dlgList.get(index);
					if (selEntry != null) {
						final DatFile datFile = selEntry.datStructure.datFile;
						if (datFile != null) {
							final EntryGroup entryGroup = datFile.findGroup(selEntry);
							if (entryGroup != null) {
								final FrameEditor frameEditor = datFile.openInEditor(DialogSearchFieldResults.this, true);
								frameEditor.goToEntry(entryGroup, selEntry);
							}
						}
					}
				}
			}
		});

		dlgSearch.addSearchListener(text -> {
			dlgList.filterToggle.setEnabled(text == null);
		});
	}

}
