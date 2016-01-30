package gui;

import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import datmanager.Core;
import datstructure.Entry;
import datstructure.EntryValueMap;
import gui.components.AbstractEntryField;
import gui.components.DialogCloseKeyListener;
import gui.components.JButtonRed;
import gui.components.JListDouble;
import gui.ui.EEScrollBarUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;


/**
 * This dialog show all results of the search "All values used in this field".
 * Each value may be used by many entries, and the user can double click on a result to get the full list of entries.
 * @see #DialogSearchValuesResultsList
 */
public class DialogSearchValuesResults extends JDialog {
	
	private static final long serialVersionUID = 4717671766146876755L;
	
	public DialogSearchValuesResults (Window parent, EntryValueMap entryValueMap, AbstractEntryField field) {
		super(parent, ModalityType.APPLICATION_MODAL);
		
		JLabel dlgLabel = new JLabel("All values and entries which use them (double click for full list):");
		JListDouble<List<Entry>> dlgList = new JListDouble<>(new ArrayList<>(entryValueMap.map.values()), new ArrayList<>(entryValueMap.mapClean.values()), "Hide unused fields");
		JListDouble<Object> rowHeaderList = new JListDouble<>(new ArrayList<>(entryValueMap.map.keySet()), new ArrayList<>(entryValueMap.mapClean.keySet()), dlgList.switchList);
		JScrollPane dlgScrollPane = new JScrollPane(dlgList);
		JButton dlgClose = new JButtonRed("Close");
		getContentPane().setBackground(Core.UI_COLOR_BACKGROUND);
		dlgLabel.setOpaque(false);
		dlgScrollPane.setOpaque(false);
		dlgScrollPane.getViewport().setOpaque(false);
		dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		dlgList.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked (MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = dlgList.getSelectedIndex();
					List<Entry> selEntries = dlgList.get(index);
					if (selEntries != null) {
						new DialogSearchValuesResultsList(DialogSearchValuesResults.this, selEntries, rowHeaderList.get(index));
					}
				}
			}
		});
		
		DialogCloseKeyListener dlgKeyListener = new DialogCloseKeyListener(this);
		dlgLabel.addKeyListener(dlgKeyListener);
		dlgList.addKeyListener(dlgKeyListener);
		dlgList.switchList.addKeyListener(dlgKeyListener);
		rowHeaderList.addKeyListener(dlgKeyListener);
		dlgScrollPane.addKeyListener(dlgKeyListener);
		dlgClose.addKeyListener(dlgKeyListener);
		getContentPane().addKeyListener(dlgKeyListener);
		addKeyListener(dlgKeyListener);
		dlgClose.addActionListener(al -> dispose());

		setTitle("For field: " + field.getEntryStruct());
		setBounds(Core.getBounds(this, 0.6, 0.8));
		setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{30, 400, 25, 50}, new double[]{1.0}, new double[]{0, 1.0, 0, 0}));
		add(dlgLabel, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		add(dlgList.switchList, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));
		setVisible(true);
	}

}
