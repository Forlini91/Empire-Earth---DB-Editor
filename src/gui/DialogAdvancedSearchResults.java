package gui;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import datmanager.DatFile;
import datstructure.Entry;
import datstructure.EntryGroup;
import gui.components.JButtonRed;
import gui.components.JListFilter;
import gui.components.JScrollPaneRed;
import gui.components.JSearchFieldEntry;
import gui.misc.EEScrollBarUI;
import gui.misc.GridBagConstraintsExtended;
import gui.misc.GridBagLayoutExtended;


/**
 * This dialog show all results of the search "Fields with the same value".
 *
 * @author MarcoForlini
 */
public class DialogAdvancedSearchResults extends JDialog {

	private static final long serialVersionUID = 2493133528817012871L;

	/**
	 * Create a new {@link DialogAdvancedSearchResults}
	 *
	 * @param parent The parent frame
	 * @param entries The list of entries
	 * @param datFile The {@link DatFile} of the entries
	 */
	public DialogAdvancedSearchResults (Window parent, List <Entry> entries, DatFile datFile) {
		super (parent, ModalityType.DOCUMENT_MODAL);
		JListFilter <Entry> dlgList = new JListFilter <> (entries, Entry::isDefined);
		JScrollPane dlgScrollPane = new JScrollPaneRed (dlgList, "Results:");
		JSearchFieldEntry dlgSearch = new JSearchFieldEntry (dlgList);
		JButton dlgClose = new JButtonRed ("Close");
		getContentPane ().setBackground (GUI.COLOR_UI_BACKGROUND);
		dlgScrollPane.setOpaque (false);
		dlgScrollPane.getViewport ().setOpaque (false);
		dlgScrollPane.getVerticalScrollBar ().setUI (new EEScrollBarUI ());
		dlgScrollPane.getHorizontalScrollBar ().setUI (new EEScrollBarUI ());

		getRootPane ().registerKeyboardAction ( (e) -> dispose (), KeyStroke.getKeyStroke (KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		dlgClose.addActionListener (al -> dispose ());

		setTitle ("Search result in file " + datFile.datStructure);
		setBounds (GUI.getBounds (this, 0.6, 0.8));
		setLayout (new GridBagLayoutExtended (new int[] { 200 }, new int[] { 400, 30, 25, 50 }, new double[] { 1.0 }, new double[] { 1.0, 0, 0, 0 }));
		add (dlgScrollPane, new GridBagConstraintsExtended (5, 5, 0, 5, 0, 0));
		add (dlgSearch, new GridBagConstraintsExtended (5, 5, 0, 5, 0, 1));
		add (dlgList.switchFilter, new GridBagConstraintsExtended (5, 5, 0, 5, 0, 2));
		add (dlgClose, new GridBagConstraintsExtended (5, 5, 5, 5, 0, 3));

		dlgList.addMouseListener (new MouseAdapter () {
			@Override
			public void mouseClicked (MouseEvent e) {
				int index = dlgList.getSelectedIndex ();
				if (index >= 0 && e.getClickCount () == 2) {
					Entry selEntry = dlgList.get (index);
					if (selEntry != null) {
						DatFile datFile = selEntry.datStructure.datFile;
						if (datFile != null) {
							EntryGroup entryGroup = datFile.findGroup (selEntry);
							if (entryGroup != null) {
								FrameEditor frameEditor = datFile.openInEditor (DialogAdvancedSearchResults.this, true);
								frameEditor.goToEntry (entryGroup, selEntry);
							}
						}
					}
				}
			}
		});
	}

}
