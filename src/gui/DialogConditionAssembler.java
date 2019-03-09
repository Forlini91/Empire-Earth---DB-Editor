package gui;


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import datmanager.DatFile;
import datstructure.Entry;
import gui.components.JButtonRed;
import gui.components.JListExtended;
import gui.components.JScrollPaneRed;
import gui.misc.EEScrollBarUI;
import gui.misc.GridBagConstraintsExtended;
import operations.Condition;
import operations.ConditionOperator;


/**
 * A dialog to assemble the condition of an advanced search
 *
 * @author MarcoForlini
 */
public class DialogConditionAssembler extends JDialog {

	private static final long serialVersionUID = -2533895457792486893L;

	private final JListExtended<ConditionOperator> filtersList = new JListExtended<>(new ArrayList<>());
	private final JScrollPane scrollPane = new JScrollPaneRed(filtersList, "Filters");
	private final JPanel buttonPane = new JPanel();
	private final JButton btnRemoveFilter = new JButtonRed("Remove filter");
	private final JButton btnAddFilter = new JButtonRed("Add filter");
	private final JButton cancelButton = new JButtonRed("Cancel");
	private final JButton searchMatchAllButton = new JButtonRed("Search (Match all)");
	private final JButton searchMatchAnyButton = new JButtonRed("Search (Match any)");

	private final DatFile datFile;

	/**
	 * Create the dialog.
	 *
	 * @param parent  The parent window
	 * @param datFile The dat file
	 */
	public DialogConditionAssembler(Window parent, DatFile datFile) {
		super(parent, ModalityType.DOCUMENT_MODAL);
		this.datFile = datFile;

		setTitle("Advanced search in " + datFile.datStructure);
		setBounds(GUI.getBounds(this, 500, 400));
		setResizable(false);

		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());

		filtersList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final int index = filtersList.getSelectedIndex();
				if (index >= 0 && e.getClickCount() == 2) {
					final ConditionOperator condition = filtersList.get(index);
					if (condition != null) {
						DialogConditionBuilder.buildCondition(DialogConditionAssembler.this, condition);
						filtersList.refresh();
					}
				}
			}
		});
		final GridBagLayout gbl_buttonPane = new GridBagLayout();
		gbl_buttonPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_buttonPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_buttonPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_buttonPane.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };

		btnAddFilter.addActionListener(e -> {
			final ConditionOperator newCondition = DialogConditionBuilder.buildCondition(this, datFile);
			if (newCondition != null) {
				filtersList.list.add(newCondition);
				filtersList.refresh();
			}
		});
		btnRemoveFilter.addActionListener(e -> {
			final int index = filtersList.getSelectedIndex();
			if (index >= 0) {
				filtersList.list.remove(index);
				filtersList.refresh();
			}
		});
		searchMatchAllButton.addActionListener(e -> search(parent, true));
		searchMatchAnyButton.addActionListener(e -> search(parent, false));

		buttonPane.setBackground(GUI.COLOR_UI_BACKGROUND);
		buttonPane.setLayout(gbl_buttonPane);
		buttonPane.add(btnRemoveFilter, new GridBagConstraintsExtended(GridBagConstraints.BOTH, 2, 0, 2, 2, 0, 0));
		buttonPane.add(btnAddFilter, new GridBagConstraintsExtended(GridBagConstraints.BOTH, 2, 2, 2, 0, 1, 0));
		buttonPane.add(searchMatchAllButton, new GridBagConstraintsExtended(GridBagConstraints.BOTH, 2, 0, 2, 2, 0, 1));
		buttonPane.add(searchMatchAnyButton, new GridBagConstraintsExtended(GridBagConstraints.BOTH, 2, 2, 2, 0, 1, 1));
		buttonPane.add(cancelButton, new GridBagConstraintsExtended(2, 0, 2, 0, 0, 2, 2, 1));

		final JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(GUI.COLOR_UI_BACKGROUND);
		contentPane.setLayout(new BorderLayout(5, 5));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		cancelButton.addActionListener(e -> setVisible(false));
		getRootPane().setDefaultButton(searchMatchAllButton);
		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	private void search(Window parent, boolean and) {
		if (filtersList.list.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Can't search without filters", "Missing filters",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		final ConditionOperator[] conds = filtersList.list.toArray(ConditionOperator[]::new);
		final Condition c = and ? Condition.and(conds) : Condition.or(conds);
		final List<Entry> results = datFile.getAllEntries(false).parallelStream().filter(c::match).collect(Collectors.toList());
		final JDialog d = new DialogAdvancedSearchResults(parent, results, datFile);
		d.setVisible(true);
	}


}
