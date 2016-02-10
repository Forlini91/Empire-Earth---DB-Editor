package gui;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import datmanager.Core;
import datstructure.Entry;
import datstructure.EntryGroup;
import gui.components.AbstractJListExtended;
import gui.components.JButtonRed;
import gui.components.JListExtended;
import gui.components.JScrollPaneRed;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;

/**
 * This dialog let the player choose a new group for the selected element, and move the element there.
 * @author MarcoForlini
 *
 */
public class DialogMoveEntryToGroup extends JDialog {

	private static final long serialVersionUID = -4853886086958587136L;

	public DialogMoveEntryToGroup (JFrame parent, List<EntryGroup> entryGroups, AbstractJListExtended <EntryGroup> entryGroupList, AbstractJListExtended <Entry> entryList, EntryGroup sourceGroup, Entry entry){
		super(parent, ModalityType.DOCUMENT_MODAL);
		List<EntryGroup> dialogListGroups = new ArrayList<>(entryGroups);
		dialogListGroups.remove(sourceGroup);
		JListExtended<EntryGroup> dialogList = new JListExtended<>(dialogListGroups);
		JScrollPane scrollPane = new JScrollPaneRed(dialogList, "Select the new group for " + entry);
		JButton dlgConfirm = new JButtonRed("Confirm");
		JButton dlgCancel = new JButtonRed("Cancel");
		dlgConfirm.addActionListener(al -> {
			EntryGroup targetGroup = dialogList.getSelectedElement();
			if (targetGroup == null) {
				return;
			}
			try {
				Integer maxID = targetGroup.entries.parallelStream().max((x,y) -> Integer.compare(x.ID, y.ID)).get().ID;
				if (entry.datStructure.getIndexID() >= 0){
					entry.values.set(entry.datStructure.getIndexID(), maxID+1);
				}
				int previousID = entry.ID;
				entry.ID = maxID;
				sourceGroup.entries.remove(entry);
				targetGroup.entries.add(entry);
				entryList.setList(sourceGroup.entries);
				entryGroupList.setSelectedElement(targetGroup);
				entryList.setSelectedElement(entry);
				JOptionPane.showMessageDialog(this, "The entry \"" + entry.getName() + "\" changed its ID from " + previousID + " to " + maxID + "\n\nDon't forget to update all entries which have a link to to this entry,\nbecause they're still using the old ID " + previousID, "Operation completed", JOptionPane.INFORMATION_MESSAGE);
			} catch (NoSuchElementException e){
				JOptionPane.showMessageDialog(this, "An error occurred while moving the entry. No data has been altered", "Error", JOptionPane.ERROR_MESSAGE);
			}
			dispose();
		});
		dlgCancel.addActionListener(al -> dispose());

		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		setTitle("Move entry to another group");
		setBounds(Core.getBounds(this, 0.3, 0.6));
		getContentPane().setBackground(Core.UI_COLOR_BACKGROUND);
		setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{400, 50, 50}, new double[]{1.0}, new double[]{0.8, 0.1, 0.1}));
		add(scrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		add(dlgConfirm, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		add(dlgCancel, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 2));

		setVisible(true);
	}
	
}
