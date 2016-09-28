package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import datmanager.DatFile;
import datstructure.DatStructure;
import gui.components.JButtonRed;
import operations.ConditionOperator;
import operations.Operator;


/**
 * A dialog which build a new {@link ConditionOperator} or edit an existing one
 * @author MarcoForlini
 */
public class DialogConditionBuilder extends JDialog {
	
	private static final long serialVersionUID = 3920757751703086221L;
	
	private static GridBagLayout gbl_contentPanel = new GridBagLayout();
	private static GridBagConstraints
	gbc_lblName = new GridBagConstraints(),
	gbc_lblField = new GridBagConstraints(),
	gbc_comboBoxField = new GridBagConstraints(),
	gbc_lblOperator = new GridBagConstraints(),
	gbc_comboBoxOperator = new GridBagConstraints(),
	gbc_lblValue = new GridBagConstraints(),
	gbc_textField = new GridBagConstraints(),
	gbc_btnCancel = new GridBagConstraints(),
	gbc_btnOk = new GridBagConstraints();

	
	
	
	
	private ConditionOperator condition = null;
	
	private JPanel contentPanel = new JPanel();
	private final JLabel
	lblField = new JLabel("Field"),
	lblOperator = new JLabel("Operator"),
	lblValue = new JLabel("Value");

	private final JLabel lblName = new JLabel();
	private final JComboBox<String> comboBoxField = new JComboBox<>();
	private final JComboBox<Operator> comboBoxOperator = new JComboBox<>();
	private final JTextField textField = new JTextField();

	private final JButton
	btnOk = new JButtonRed("OK"),
	btnCancel = new JButtonRed("Cancel");
	


	{
		setBounds(GUI.getBounds(this, 450, 225));
		setResizable(false);
		comboBoxOperator.setModel(new DefaultComboBoxModel <>(Operator.values()));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancel.addActionListener(e -> dispose());
		contentPanel.setLayout(gbl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.setBackground(GUI.COLOR_UI_BACKGROUND);
		contentPanel.add(lblName, gbc_lblName);
		contentPanel.add(lblField, gbc_lblField);
		contentPanel.add(comboBoxField, gbc_comboBoxField);
		contentPanel.add(lblOperator, gbc_lblOperator);
		contentPanel.add(comboBoxOperator, gbc_comboBoxOperator);
		contentPanel.add(lblValue, gbc_lblValue);
		contentPanel.add(textField, gbc_textField);
		contentPanel.add(btnOk, gbc_btnOk);
		contentPanel.add(btnCancel, gbc_btnCancel);
		setContentPane(contentPanel);
		getRootPane().setDefaultButton(btnOk);
		getRootPane().registerKeyboardAction((e) -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}



	private DialogConditionBuilder (Window parent, DatFile datFile) {
		super(parent, ModalityType.DOCUMENT_MODAL);
		setDatFile(datFile);
		setTitle("Create new filter for " + datFile.datStructure);
	}
	
	private DialogConditionBuilder (Window parent, ConditionOperator condition){
		super(parent, ModalityType.DOCUMENT_MODAL);
		setDatFile(condition.datFile);
		setTitle("Edit filter " + condition);
		this.condition = condition;
		comboBoxField.setSelectedIndex(condition.index+1);
		comboBoxOperator.setSelectedItem(condition.operator);
		textField.setText(condition.value.toString());
	}
	
	
	
	private void setDatFile(DatFile datFile){
		DatStructure datStructure = datFile.datStructure;
		lblName.setText("File: " + datStructure);
		int length = datStructure.fieldStructs.length;
		boolean extraField = datStructure.extraField != null;
		String[] fields = new String[length + (extraField ? 2 : 1)];
		fields[0] = "<Any field>";
		for (int i = 0; i < length; i++){
			fields[i+1] = "(" + i + ") " + datStructure.fieldStructs[i].toString();
		}
		if (extraField){
			fields[length+1] = "(EXTRA FIELD) " + datStructure.extraField.toString();
		}
		comboBoxField.setModel(new DefaultComboBoxModel<>(fields));
		
		btnOk.addActionListener(e -> {
			String text = textField.getText();
			if (text.isEmpty()){
				JOptionPane.showMessageDialog(this, "Can't create a new filter without a value", "Missing value", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int index = comboBoxField.getSelectedIndex()-1;
			Operator operator = (Operator) comboBoxOperator.getSelectedItem();
			Object value;
			try {
				value = Float.parseFloat(text);
			} catch (NumberFormatException nfe) {
				value = text;
			}

			if (condition == null){
				condition = new ConditionOperator(datFile, index, operator, value);
			} else {
				condition.setData(datFile, index, operator, value);
			}
			setVisible(false);
		});
	}
	
	
	/**
	 * Open a new dialog to build a new condition
	 * @param parent	The parent window
	 * @param datFile 	The dat file
	 * @return the new condition
	 */
	public static ConditionOperator buildCondition (Window parent, DatFile datFile) {
		DialogConditionBuilder builder = new DialogConditionBuilder(parent, datFile);
		builder.setVisible(true);
		return builder.condition;
	}
	
	/**
	 * Open a new dialog to modify the given condition
	 * @param parent	The parent window
	 * @param condition	The condition to edit
	 */
	public static void buildCondition (Window parent, ConditionOperator condition) {
		DialogConditionBuilder builder = new DialogConditionBuilder(parent, condition);
		builder.setVisible(true);
	}
	
	
	

	static {
		gbl_contentPanel.columnWidths = new int[] {125, 125};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.3, 0.7};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.insets = new Insets(5, 5, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		gbc_lblName.gridwidth = 2;
		
		gbc_lblField.fill = GridBagConstraints.BOTH;
		gbc_lblField.insets = new Insets(0, 5, 5, 5);
		gbc_lblField.gridx = 0;
		gbc_lblField.gridy = 1;
		
		gbc_comboBoxField.fill = GridBagConstraints.BOTH;
		gbc_comboBoxField.insets = new Insets(0, 5, 5, 5);
		gbc_comboBoxField.gridx = 1;
		gbc_comboBoxField.gridy = 1;
		
		gbc_lblOperator.fill = GridBagConstraints.BOTH;
		gbc_lblOperator.insets = new Insets(0, 5, 5, 5);
		gbc_lblOperator.gridx = 0;
		gbc_lblOperator.gridy = 2;
		
		gbc_comboBoxOperator.fill = GridBagConstraints.BOTH;
		gbc_comboBoxOperator.insets = new Insets(0, 5, 5, 5);
		gbc_comboBoxOperator.gridx = 1;
		gbc_comboBoxOperator.gridy = 2;
		
		gbc_lblValue.fill = GridBagConstraints.BOTH;
		gbc_lblValue.insets = new Insets(0, 5, 5, 5);
		gbc_lblValue.gridx = 0;
		gbc_lblValue.gridy = 3;
		
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		gbc_textField.insets = new Insets(0, 5, 5, 5);
		
		gbc_btnOk.fill = GridBagConstraints.BOTH;
		gbc_btnOk.insets = new Insets(20, 5, 5, 5);
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 4;
		gbc_btnOk.gridwidth = 2;

		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 5, 5, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 5;
		gbc_btnCancel.gridwidth = 2;

	}

}
