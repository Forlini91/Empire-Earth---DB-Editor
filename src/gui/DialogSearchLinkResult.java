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

import datmanager.DatFile;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.Link;
import gui.components.JButtonRed;
import gui.components.JListExtended;
import gui.components.JScrollPaneRed;
import gui.components.JSearchFieldLink;
import gui.misc.EEScrollBarUI;
import gui.misc.GridBagConstraintsExtended;
import gui.misc.GridBagLayoutExtended;

/**
 * This dialog show all results of the search "Links to this entry".
 * @author MarcoForlini
 *
 */
public class DialogSearchLinkResult extends JDialog {
	
	private static final long serialVersionUID = 2493133528817012871L;
	
	/**
	 * Create a new {@link DialogSearchLinkResult}
	 * @param parent		The parent frame
	 * @param pointedEntry	The pointed entry
	 * @param links			The list of links
	 */
	public DialogSearchLinkResult (Frame parent, Entry pointedEntry, List<Link> links) {
		super(parent, ModalityType.DOCUMENT_MODAL);
		
		JListExtended<Link> dlgList = new JListExtended<>(links);
		JScrollPane dlgScrollPane = new JScrollPaneRed(dlgList, "All links to this entry");
		JSearchFieldLink dlgSearch = new JSearchFieldLink(dlgList);
		JButton dlgClose = new JButtonRed("Close");
		getContentPane().setBackground(GUI.COLOR_UI_BACKGROUND);
		dlgScrollPane.setOpaque(false);
		dlgScrollPane.getViewport().setOpaque(false);
		dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		
		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		dlgClose.addActionListener(al -> dispose());
		
		setTitle("Links to entry: " + pointedEntry);
		setBounds(GUI.getBounds(this, 0.6, 0.8));
		setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{400, 30, 50}, new double[]{1.0}, new double[]{1.0, 0, 0}));
		add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		add(dlgSearch, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 2));

		dlgList.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked (MouseEvent e) {
				int index = dlgList.getSelectedIndex();
				if (e.getClickCount() == 2) {
					Link selLink = dlgList.get(index);
					if (selLink != null) {
						DatFile datFile = selLink.target.datStructure.datFile;
						if (datFile != null){
							EntryGroup entryGroup = datFile.findGroup(selLink.target);
							if (entryGroup != null){
								FrameEditor frameEditor = datFile.openInEditor(parent, true);
								frameEditor.goToEntry(entryGroup, selLink.target);
							}
						}
					}
				}
			}
		});
	}
	
}
