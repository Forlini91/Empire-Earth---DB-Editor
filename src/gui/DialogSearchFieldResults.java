package gui;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import datmanager.Core;
import datmanager.DatFile;
import datstructure.Entry;
import datstructure.EntryGroup;
import gui.components.EntryFieldInterface;
import gui.components.JButtonRed;
import gui.components.JListDouble;
import gui.components.JScrollPaneRed;
import gui.components.JSearchFieldEntry;
import gui.ui.EEScrollBarUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;

/**
 * This dialog show all results of the search "Fields with the same value".
 * @author MarcoForlini
 *
 */
public class DialogSearchFieldResults extends JDialog {

	private static final long serialVersionUID = 2493133528817012871L;

	/**
	 * Create a new {@link DialogSearchFieldResults}
	 * @param parent		The parent frame
	 * @param entries		The list of entries
	 * @param entriesClean	The list of clean entries
	 * @param field			The selected field
	 */
	public DialogSearchFieldResults (Frame parent, List<Entry> entries, List<Entry> entriesClean, EntryFieldInterface field) {
		super(parent, ModalityType.DOCUMENT_MODAL);

		JListDouble<Entry> dlgList = new JListDouble<>(entries, entriesClean);
		JScrollPane dlgScrollPane = new JScrollPaneRed(dlgList, "All entries with the same value: " + field.getVal());
		JSearchFieldEntry dlgSearch = new JSearchFieldEntry(dlgList);
		JButton dlgClose = new JButtonRed("Close");
		getContentPane().setBackground(GUI.COLOR_UI_BACKGROUND);
		dlgScrollPane.setOpaque(false);
		dlgScrollPane.getViewport().setOpaque(false);
		dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());

		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		dlgClose.addActionListener(al -> dispose());

		setTitle("For field: " + field.getIndex() + " - " + field.getEntryStruct());
		setBounds(Core.getBounds(this, 0.6, 0.8));
		setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{400, 30, 25, 50}, new double[]{1.0}, new double[]{1.0, 0, 0, 0}));
		add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		add(dlgSearch, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		add(dlgList.switchList, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));

		dlgList.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked (MouseEvent e) {
				int index = dlgList.getSelectedIndex();
				if (e.getClickCount() == 2) {
					Entry selEntry = dlgList.get(index);
					if (selEntry != null) {
						DatFile datFile = selEntry.datStructure.datFile;
						if (datFile != null){
							EntryGroup entryGroup = datFile.findGroup(selEntry);
							if (entryGroup != null){
								FrameEditor frameEditor = Core.openFile(DialogSearchFieldResults.this, datFile, true);
								frameEditor.goToEntry(entryGroup, selEntry);
							}
						}
					}
				}
			}
		});
	}

}
