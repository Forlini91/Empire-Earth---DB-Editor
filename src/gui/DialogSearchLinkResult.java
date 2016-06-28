package gui;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import datmanager.Core;
import datstructure.Entry;
import gui.components.EntryFieldInterface;
import gui.components.JButtonRed;
import gui.components.JListEntry;
import gui.components.JScrollPaneRed;
import gui.components.JSearchFieldEntry;
import gui.ui.EEScrollBarUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;

/**
 * This dialog show all results of the search "Links to this entry".
 * @author MarcoForlini
 *
 */
public class DialogSearchLinkResult extends JDialog {
	
	private static final long serialVersionUID = 2493133528817012871L;
	
	/**
	 * Create a new {@link DialogSearchLinkResult}
	 * @param owner			The parent window
	 * @param entries		The list of entries
	 * @param field			The selected field
	 */
	public DialogSearchLinkResult (Frame owner, Entry pointedEntry, List<Entry> entries, EntryFieldInterface field) {
		super(owner, ModalityType.DOCUMENT_MODAL);
		
		JListEntry dlgList = new JListEntry(entries);
		JScrollPane dlgScrollPane = new JScrollPaneRed(dlgList, "All links to this entry");
		JSearchFieldEntry dlgSearch = new JSearchFieldEntry(dlgList);
		JButton dlgClose = new JButtonRed("Close");
		getContentPane().setBackground(Core.UI_COLOR_BACKGROUND);
		dlgScrollPane.setOpaque(false);
		dlgScrollPane.getViewport().setOpaque(false);
		dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		
		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		dlgClose.addActionListener(al -> dispose());
		
		setTitle("Links to entry: " + pointedEntry);
		setBounds(Core.getBounds(this, 0.6, 0.8));
		setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{400, 30, 25, 50}, new double[]{1.0}, new double[]{1.0, 0, 0, 0}));
		add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		add(dlgSearch, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		add(dlgList.switchList, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));
	}
	
}
