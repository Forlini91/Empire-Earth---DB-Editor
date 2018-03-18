package gui;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import datmanager.DatFile;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.EntryValueMap;
import gui.components.EntryFieldInterface;
import gui.components.JButtonRed;
import gui.components.JCheckBoxExtended;
import gui.components.JListExtended;
import gui.misc.EEScrollBarUI;
import gui.misc.GridBagConstraintsExtended;
import gui.misc.GridBagLayoutExtended;


/**
 * This dialog show all results of the search "All values used in this field".
 * Each value may be used by many entries, and the user can double click on a result to get the full list of entries.
 */
public class DialogSearchValuesResults extends JDialog {

	private static final long serialVersionUID = 4717671766146876755L;

	/**
	 * Create a new {@link DialogSearchValuesResults}
	 *
	 * @param parent The parent window
	 * @param entryValueMap The map of values
	 * @param field The selected field
	 */
	public DialogSearchValuesResults (Window parent, EntryValueMap entryValueMap, EntryFieldInterface field) {
		super (parent, ModalityType.DOCUMENT_MODAL);

		JLabel dlgLabel = new JLabel ("All values and entries which use them (double click for full list or open links):");
		EntryValueMap entryValueMapClean = entryValueMap.applyFilter (Entry::isDefined);
		List <List <Entry>> listValues = new ArrayList <> (entryValueMap.map.values ());
		List <Object> listKeys = new ArrayList <> (entryValueMap.map.keySet ());
		List <List <Entry>> listValuesClean = new ArrayList <> (entryValueMapClean.map.values ());
		List <Object> listKeysClean = new ArrayList <> (entryValueMapClean.map.keySet ());
		JListExtended <List <Entry>> dlgList = new JListExtended <> (listValuesClean, false);
		JListExtended <Object> rowHeaderList = new JListExtended <> (listKeysClean, false);
		JCheckBoxExtended switchFilter = new JCheckBoxExtended ("Hide undefined fiels", true);
		switchFilter.addActionListener (e -> {
			boolean sel = switchFilter.isSelected ();
			dlgList.setList (sel ? listValuesClean : listValues);
			rowHeaderList.setList (sel ? listKeysClean : listKeys);
		});

		JScrollPane dlgScrollPane = new JScrollPane (dlgList);
		dlgScrollPane.setRowHeaderView (rowHeaderList);
		dlgList.setBorder (new EmptyBorder (0, 5, 0, 5));
		rowHeaderList.setBorder (new EmptyBorder (0, 5, 0, 5));
		JButton dlgClose = new JButtonRed ("Close");
		getContentPane ().setBackground (GUI.COLOR_UI_BACKGROUND);
		rowHeaderList.setBackground (GUI.COLOR_UI_ELEMENT);
		DefaultListCellRenderer x = (DefaultListCellRenderer) rowHeaderList.getCellRenderer ();
		x.setBackground (GUI.COLOR_UI_ELEMENT);
		rowHeaderList.setForeground (Color.WHITE);

		dlgLabel.setOpaque (false);
		dlgScrollPane.setOpaque (false);
		dlgScrollPane.getViewport ().setOpaque (false);
		dlgScrollPane.getVerticalScrollBar ().setUI (new EEScrollBarUI ());
		dlgScrollPane.getHorizontalScrollBar ().setUI (new EEScrollBarUI ());
		dlgList.addMouseListener (new MouseAdapter () {
			@Override
			public void mouseReleased (MouseEvent e) {
				int index = dlgList.getSelectedIndex ();
				rowHeaderList.setSelectedIndex (index);
			}

			@Override
			public void mouseClicked (MouseEvent e) {
				int index = dlgList.getSelectedIndex ();
				if (e.getClickCount () == 2) {
					List <Entry> selEntries = dlgList.get (index);
					if (selEntries != null && !selEntries.isEmpty ()) {
						if (selEntries.size () > 1) {
							JDialog d = new DialogSearchValuesResultsList (DialogSearchValuesResults.this, selEntries, rowHeaderList.get (index));
							d.setVisible (true);
						} else {
							Entry selEntry = selEntries.get (0);
							if (selEntry != null) {
								DatFile datFile = selEntry.datStructure.datFile;
								if (datFile != null) {
									EntryGroup entryGroup = datFile.findGroup (selEntry);
									if (entryGroup != null) {
										FrameEditor frameEditor = datFile.openInEditor (DialogSearchValuesResults.this, true);
										frameEditor.goToEntry (entryGroup, selEntry);
									}
								}
							}
						}
					}
				}
			}

			@Override
			public void mouseDragged (MouseEvent e) {
				int index = dlgList.getSelectedIndex ();
				rowHeaderList.setSelectedIndex (index);
			}
		});
		rowHeaderList.addMouseListener (new MouseAdapter () {
			@Override
			public void mouseReleased (MouseEvent e) {
				int index = rowHeaderList.getSelectedIndex ();
				dlgList.setSelectedIndex (index);
			}

			@Override
			public void mouseClicked (MouseEvent e) {
				int index = rowHeaderList.getSelectedIndex ();
				if (e.getClickCount () == 2) {
					List <Entry> selEntries = dlgList.get (index);
					if (selEntries != null && !selEntries.isEmpty ()) {
						if (selEntries.size () > 1) {
							JDialog d = new DialogSearchValuesResultsList (DialogSearchValuesResults.this, selEntries, rowHeaderList.get (index));
							d.setVisible (true);
						} else {
							Entry selEntry = selEntries.get (0);
							if (selEntry != null) {
								DatFile datFile = selEntry.datStructure.datFile;
								if (datFile != null) {
									EntryGroup entryGroup = datFile.findGroup (selEntry);
									if (entryGroup != null) {
										FrameEditor frameEditor = datFile.openInEditor (DialogSearchValuesResults.this, true);
										frameEditor.goToEntry (entryGroup, selEntry);
									}
								}
							}
						}
					}
				}
			}

			@Override
			public void mouseDragged (MouseEvent e) {
				int index = dlgList.getSelectedIndex ();
				rowHeaderList.setSelectedIndex (index);
			}
		});

		getRootPane ().registerKeyboardAction ( (e) -> dispose (), KeyStroke.getKeyStroke (KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		dlgClose.addActionListener (al -> dispose ());

		setTitle ("For field: " + field.getIndex () + " - " + field.getEntryStruct ());
		setBounds (GUI.getBounds (this, 0.6, 0.8));
		setLayout (new GridBagLayoutExtended (new int[] { 200 }, new int[] { 30, 400, 25, 50 }, new double[] { 1.0 }, new double[] { 0, 1.0, 0, 0 }));
		add (dlgLabel, new GridBagConstraintsExtended (5, 5, 0, 5, 0, 0));
		add (dlgScrollPane, new GridBagConstraintsExtended (5, 5, 0, 5, 0, 1));
		add (switchFilter, new GridBagConstraintsExtended (5, 5, 0, 5, 0, 2));
		add (dlgClose, new GridBagConstraintsExtended (5, 5, 5, 5, 0, 3));
	}

}
