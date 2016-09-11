package gui;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import datmanager.Core;
import datmanager.DatFile;
import datstructure.Entry;
import datstructure.EntryGroup;
import gui.components.JButtonRed;
import gui.components.JListEntry;
import gui.components.JSearchFieldEntry;
import gui.ui.EEScrollBarUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;

/**
 * In the {@code DialogSearchValuesResults} dialog the user can double click on any entry to get the full list of entries.
 * This dialog show this list of entries.
 * @author MarcoForlini
 */
public class DialogSearchValuesResultsList extends JDialog {
	
	private static final long serialVersionUID = 7589015334494498605L;
	
	/**
	 * Create a new {@link DialogSearchValuesResultsList}
	 * @param parent	The parent window
	 * @param list		The list of entries
	 * @param value		The selected value
	 */
	public DialogSearchValuesResultsList(Window parent, List<Entry> list, Object value){
		super(parent, ModalityType.DOCUMENT_MODAL);
		JLabel dlgLabel = new JLabel("All entries with this value:");
		JListEntry dlgList = new JListEntry(list);
		JScrollPane dlgScrollPane = new JScrollPane(dlgList);
		JSearchFieldEntry dlgSearch = new JSearchFieldEntry(dlgList);
		JButton dlgClose = new JButtonRed("Close");
		getContentPane().setBackground(GUI.COLOR_UI_BACKGROUND);
		dlgLabel.setOpaque(false);
		dlgScrollPane.setOpaque(false);
		dlgScrollPane.getViewport().setOpaque(false);
		dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());

		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		dlgClose.addActionListener(e2 -> dispose());

		setTitle("For value: " + value);
		setBounds(Core.getBounds(this, 0.45, 0.6));
		setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{30, 400, 25, 30, 50}, new double[]{1.0}, new double[]{0, 1.0, 0, 0, 0}));
		add(dlgLabel, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		add(dlgList.switchList, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		add(dlgSearch, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 3));
		add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 4));

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
								FrameEditor frameEditor = Core.openFile(DialogSearchValuesResultsList.this, datFile, true);
								frameEditor.goToEntry(entryGroup, selEntry);
							}
						}
					}
				}
			}
		});
	}
	
}
