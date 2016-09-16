package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import datmanager.Core;
import datmanager.DatFile;
import datstructure.Entry;
import gui.components.JListExtended;
import operations.Condition;
import operations.ConditionOperator;


/**
 * A dialog to assemble the condition of an advanced search
 * @author MarcoForlini
 */
public class DialogConditionAssembler extends JDialog {

	private static final long serialVersionUID = -2533895457792486893L;
	
	private JLabel lblFilters = new JLabel("Filters");
	private JListExtended<ConditionOperator> filtersList = new JListExtended<>(new ArrayList<>());
	private JPanel buttonPane = new JPanel();
	private JButton btnRemoveFilter = new JButton("Remove filter");
	private JButton btnAddFilter = new JButton("Add filter");
	private JButton cancelButton = new JButton("Cancel");
	private JButton okButton = new JButton("OK");
	
	/**
	 * Create the dialog.
	 * @param parent	The parent window
	 * @param datFile 	The dat file
	 */
	public DialogConditionAssembler (Window parent, DatFile datFile) {
		super(parent, ModalityType.DOCUMENT_MODAL);
		setTitle("Advanced search in " + datFile.datStructure);
		setBounds(Core.getBounds(this, 500, 400));

		lblFilters.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilters.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		filtersList.addMouseListener(new MouseAdapter(){
			@SuppressWarnings ("synthetic-access")
			@Override
			public void mouseClicked (MouseEvent e) {
				int index = filtersList.getSelectedIndex();
				if (index >= 0 && e.getClickCount() == 2) {
					ConditionOperator condition = filtersList.get(index);
					if (condition != null) {
						DialogConditionBuilder.buildCondition(DialogConditionAssembler.this, condition);
						filtersList.refresh();
					}
				}
			}
		});

		btnAddFilter.addActionListener(e -> {
			ConditionOperator newCondition = DialogConditionBuilder.buildCondition(this, datFile);
			if (newCondition != null){
				filtersList.list.add(newCondition);
				filtersList.refresh();
			}
		});
		btnRemoveFilter.addActionListener(e -> {
			int index = filtersList.getSelectedIndex();
			if (index >= 0) {
				filtersList.list.remove(index);
				filtersList.refresh();
			}
		});
		cancelButton.addActionListener(e -> setVisible(false));
		okButton.addActionListener(e -> {
			if (filtersList.list.isEmpty()){
				JOptionPane.showMessageDialog(this, "Can't search without filters", "Missing filters", JOptionPane.ERROR_MESSAGE);
				return;
			}
			List<ConditionOperator> conds = filtersList.list;
			Condition c = Condition.and(conds.toArray(new ConditionOperator[conds.size()]));
			List<Entry> results = datFile
					.getAllEntries(false)
					.parallelStream()
					.filter(c::match)
					.collect(Collectors.toList());
			JDialog d = new DialogAdvancedSearchResults(parent, results, datFile);
			d.setVisible(true);
		});
		
		buttonPane.setLayout(new GridLayout(2, 2, 5, 5));
		buttonPane.add(btnRemoveFilter);
		buttonPane.add(btnAddFilter);
		buttonPane.add(cancelButton);
		buttonPane.add(okButton);

		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 5));
		contentPane.add(lblFilters, BorderLayout.NORTH);
		contentPane.add(filtersList, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(okButton);
		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

}
