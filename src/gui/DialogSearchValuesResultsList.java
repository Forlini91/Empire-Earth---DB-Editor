package gui;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import datmanager.Core;
import datstructure.Entry;
import datstructure.EntryValueMap;
import gui.components.AbstractEntryField;
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
 * @see #DialogSearchValuesResults(Window, EntryValueMap, AbstractEntryField)
 */
public class DialogSearchValuesResultsList extends JDialog {
	
	private static final long serialVersionUID = 7589015334494498605L;

	public DialogSearchValuesResultsList(Window parent, List<Entry> list, Object value){
		super(parent, ModalityType.DOCUMENT_MODAL);
		JLabel dlgLabel = new JLabel("All entries with this value:");
		JListEntry dlgList = new JListEntry(list, "Hide unused fields");
		JScrollPane dlgScrollPane = new JScrollPane(dlgList);
		JSearchFieldEntry dlgSearch = new JSearchFieldEntry(dlgList);
		JButton dlgClose = new JButtonRed("Close");
		getContentPane().setBackground(Core.UI_COLOR_BACKGROUND);
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
		setVisible(true);
	}
	
}
