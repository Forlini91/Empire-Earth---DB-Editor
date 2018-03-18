package gui;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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
import gui.misc.GridBagConstraintsExtended;
import gui.misc.GridBagLayoutExtended;

/**
 * This dialog let the player choose a new group for the selected element, and move the element there.
 * @author MarcoForlini
 */
public class DialogMoveEntryToGroup extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = -8547149058851208651L;
	
	/**
	 * Create a new {@link DialogMoveEntryToGroup}
	 * @param parent			The parent window
	 * @param entryGroups		List of groups
	 * @param entryGroupList	JList of groups
	 * @param entryList			List of entries
	 * @param sourceGroup		Group which contain the selected entry
	 * @param entry				Selected entry
	 * @param onChange			Code to run if operation succeed
	 */
	public DialogMoveEntryToGroup (JFrame parent, List<EntryGroup> entryGroups, AbstractJListExtended <EntryGroup> entryGroupList, AbstractJListExtended <Entry> entryList, EntryGroup sourceGroup, Entry entry, Runnable onChange){
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
				int newID = targetGroup.entries.parallelStream().mapToInt(x -> x.getID()).max().getAsInt();
				int oldID = entry.getID();
				entry.setID(newID);
				sourceGroup.entries.remove(entry);
				targetGroup.entries.add(entry);
				sourceGroup.map.remove(oldID);
				targetGroup.map.put(newID, entry);
				entryList.setList(sourceGroup.entries);
				entryGroupList.setSelectedElement(targetGroup);
				entryList.setSelectedElement(entry);
				JOptionPane.showMessageDialog(this, "The entry \"" + entry.getTrimmedName() + "\" changed its ID from " + oldID + " to " + newID + "\nAll links have been automatically updated", "Operation completed", JOptionPane.INFORMATION_MESSAGE, GUI.IMAGE_ICON);
				onChange.run();
				entry.getLinksToEntry(false).parallelStream().forEach(link -> link.source.datStructure.datFile.setUnsaved(true));
			} catch (Exception e){
				Core.printException(null, e, "An error occurred while moving the entry. No data has been altered", "Error", true);
			}
			dispose();
		});
		dlgCancel.addActionListener(al -> dispose());

		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		setTitle("Move entry to another group");
		setBounds(GUI.getBounds(this, 0.3, 0.6));
		getContentPane().setBackground(GUI.COLOR_UI_BACKGROUND);
		setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{400, 50, 50}, new double[]{1.0}, new double[]{0.8, 0.1, 0.1}));
		add(scrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		add(dlgConfirm, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		add(dlgCancel, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 2));
	}

}
