package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;

import dbmanager.DBManager;
import dbmanager.SearchEntry;
import dbmanager.SearchEntryGroup;
import dbstructure.DatStructure;
import dbstructure.Entry;
import dbstructure.EntryGroup;
import dbstructure.EntryStruct;
import gui.elements.EntryPanel;
import gui.elements.GridBagConstraintsExtended;
import gui.elements.GridBagLayoutExtended;
import gui.elements.JListEx;
import gui.elements.JTextFieldEx;
import gui.elements.ValueField;


public class GUIEditor extends AbstractFrame {

	private static final long serialVersionUID = -3426470254615698936L;

	private static final int[] columnWidths = {100, 15, 100, 15, 100, 15, 100};
	private static final double[] columnWeights = {1.0, 0.25, 1.0, 0.25, 1.0, 0.25, 1.0};

	private final LayoutManager gbl_contentPane = new GridBagLayoutExtended(new int[]{200, 200, 100, 200, 200, 100}, new int[]{30, 400, 30, 30}, new double[]{0, 0, 0.5, 0, 0, 0.5}, new double[]{0.0, 1.0, 0});
	private static final GridBagConstraints gbc_entryGroupListLabel = new GridBagConstraintsExtended(5, 5, 0, 0, 0, 0);
	private static final GridBagConstraints gbc_entryGroupListPane = new GridBagConstraintsExtended(5, 5, 0, 0, 0, 1);
	private static final GridBagConstraints gbc_entryGroupSearchField = new GridBagConstraintsExtended(5, 5, 0, 0, 0, 2);
	private static final GridBagConstraints gbc_entryListLabel = new GridBagConstraintsExtended(5, 5, 0, 0, 1, 0);
	private static final GridBagConstraints gbc_entryListPane = new GridBagConstraintsExtended(5, 5, 0, 0, 1, 1);
	private static final GridBagConstraints gbc_entrySearchField = new GridBagConstraintsExtended(5, 5, 0, 0, 1, 2);
	private static final GridBagConstraints gbc_fieldsLabel = new GridBagConstraintsExtended(5, 5, 0, 0, 2, 0, 4, 1);
	private static final GridBagConstraints gbc_fieldsPane  = new GridBagConstraintsExtended(5, 5, 0, 5, 2, 1, 4, 2);
	private static final GridBagConstraints gbc_resetButton = new GridBagConstraintsExtended(5, 5, 5, 0, 0, 3);
	private static final GridBagConstraints gbc_saveButton = new GridBagConstraintsExtended(5, 5, 5, 0, 1, 3);
	private static final GridBagConstraints gbc_removeID = new GridBagConstraintsExtended(5, 5, 5, 0, 3, 3);
	private static final GridBagConstraints gbc_addID = new GridBagConstraintsExtended(5, 5, 5, 5, 4, 3);

	private File file;
	private DatStructure datStructure;
	private List<EntryGroup> entryGroups;
	private EntryGroup currentEntryGroup;
	private Entry currentEntry = null;
	private Component rightClicked = null;

	
	private JPanel contentPane = new JPanel();
	private JLabel entryGroupListLabel = new JLabel("Group");
	private JLabel entryListLabel = new JLabel("Entries");
	private JLabel fieldsLabel = new JLabel("Fields");
	private JListEx<EntryGroup> entryGroupList = new JListEx<EntryGroup>();
	private JListEx<Entry> entryList = new JListEx<Entry>();
	private JScrollPane entryGroupListPane = new JScrollPane(entryGroupList);
	private JScrollPane entryListPane = new JScrollPane(entryList);
	private JPanel panelFields = new JPanel();
	private JScrollPane fieldsPane = new JScrollPane(panelFields);
	private JTextField entryGroupSearchField = new JTextFieldEx(null, -1, "Search group");
	private JTextField entrySearchField = new JTextFieldEx(null, -1, "Search entry");
	private JButton reset = new JButton("Reset entry");
	private JButton save = new JButton("Save entry");
	private SearchEntryGroup entryGroupSearch;
	private SearchEntry entrySearch;
	private JButton addID = new JButton("Add ID");
	private JButton removeID = new JButton("Remove ID");
	
	private List<EntryPanel> entryPanels;
	private int numSavedFields;
	private int row = 0;
	private int col = 0;

	private final JPopupMenu fieldMenu = new JPopupMenu();
	private final JMenuItem fieldMenuSearchValues = new JMenuItem("Show all values used for this field");
	private final JMenuItem fieldMenuSearchFields = new JMenuItem("Show all fields with the same value");
	private final JMenuItem fieldMenuSearchUnusedFields = new JMenuItem("Mark all unused/dual value fields");
	
	private final JPopupMenu entryListMenu = new JPopupMenu();
	private final JMenuItem entryListMenuAdd = new JMenuItem("Add entry");
	private final JMenuItem entryListMenuRemove = new JMenuItem("Remove entry");
	private final JMenuItem entryListMenuMoveTo = new JMenuItem("Move to group...");
	
	{
		setBounds(AbstractGUI.getBounds(this, 0.85, 0.85));
		setIconImage(GUIMain.IMAGE_ICON.getImage());
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setContentPane(contentPane);
		
		contentPane.setLayout(gbl_contentPane);
		contentPane.add(entryGroupListLabel, gbc_entryGroupListLabel);
		contentPane.add(entryListLabel, gbc_entryListLabel);
		contentPane.add(fieldsLabel, gbc_fieldsLabel);
		contentPane.add(entryGroupListPane, gbc_entryGroupListPane);
		contentPane.add(entryListPane, gbc_entryListPane);
		contentPane.add(fieldsPane, gbc_fieldsPane);
		contentPane.add(entryGroupSearchField, gbc_entryGroupSearchField);
		contentPane.add(entrySearchField, gbc_entrySearchField);
		contentPane.add(reset, gbc_resetButton);
		contentPane.add(save, gbc_saveButton);
		contentPane.add(addID, gbc_addID);
		contentPane.add(removeID, gbc_removeID);
		
		entryGroupListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		entryGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		entryGroupList.addListSelectionListener(this::dataSetListSelectionListener);
		entryGroupSearchField.addKeyListener(new SearchEntryGroupListener());
		
		entryListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		entryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		entryList.addListSelectionListener(this::dataListSelectionListener);
		entryList.addMouseListener(new JShowPopupMenu(entryList, entryListMenu));
		entryListMenu.add(entryListMenuAdd);
		entryListMenu.add(entryListMenuRemove);
		entryListMenu.add(entryListMenuMoveTo);
		entryListMenuAdd.addActionListener(e -> entryListMenuAdd());
		entryListMenuRemove.addActionListener(e -> entryListMenuRemove());
		entryListMenuMoveTo.addActionListener(e -> entryListMoveTo());
		entrySearchField.addKeyListener(new SearchDataListener());
		
		fieldsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fieldsPane.getVerticalScrollBar().setUnitIncrement(10);
		fieldsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		fieldMenu.add(fieldMenuSearchValues);
		fieldMenu.add(fieldMenuSearchFields);
		fieldMenu.add(fieldMenuSearchUnusedFields);
		fieldMenuSearchValues.addActionListener(e -> fieldMenuSearchValues());
		fieldMenuSearchFields.addActionListener(e -> fieldMenuSearchField());
		fieldMenuSearchUnusedFields.addActionListener(e -> findUnusedFields());
		
		save.addActionListener(e -> saveEntry());
		reset.addActionListener(e -> loadEntry(currentEntry));
		addID.addActionListener(e -> addField());
		removeID.addActionListener(e -> removeField());
	}

	
	
	
	

	

	
	

	public GUIEditor (File file, DatStructure datStructure, List<EntryGroup> entryGroups) {
		super("Editor - " + datStructure.fileName);
		this.file = file;
		this.datStructure = datStructure;
		this.entryGroups = entryGroups;

		int nEntries = datStructure.entries.length;
		entryGroupSearch = new SearchEntryGroup(entryGroups);
		entryPanels = new ArrayList<>(datStructure.indexCountExtra < 0 ? nEntries : nEntries+20);

		int nRows = (nEntries+3)/4;
		int[] rowsHeights = new int[nRows];
		double[] rowsWeights = new double[nRows];
		Arrays.fill(rowsHeights, 0);
		Arrays.fill(rowsWeights, 1);
		panelFields.setLayout(new GridBagLayoutExtended(columnWidths, rowsHeights, columnWeights, rowsWeights));

		entryGroupList.setVector(entryGroups);
		if (entryGroups.size() > 0){
			entryListMenuMoveTo.setVisible(entryGroups.size() > 1);
			entryGroupList.setSelectedIndex(0, true);
		}

		setTitle("EE - DB Editor: " + datStructure.fileName);
		setVisible(true);
	}
	
	
	

	
	
	public void addEntryPanel(EntryPanel entryPanel){
		panelFields.add(entryPanel, new GridBagConstraintsExtended(GridBagConstraints.HORIZONTAL, 5, 5, 5, (col==3?5:0), col, row));
		if (col < 3){
			col++;
		} else {
			row++;
			col = 0;
		}
	}

	private void buildFields(Entry entries){
		int numBaseFields = datStructure.entries.length;
		int numPlacedFields = entryPanels.size();
		numSavedFields = entries.values.size();
		EntryStruct entry;
		row = 0;
		col = 0;
		
		panelFields.removeAll();
		for (int i = 0; i < numBaseFields; i++){
			if (i >= numPlacedFields){
				entry = datStructure.entries[i];
				EntryPanel entryPanel = new EntryPanel(entry, i);
				entryPanels.add(entryPanel);
				Component component = (Component) entryPanel.field;
				component.addMouseListener(new JShowPopupMenu(component, fieldMenu));
			}
			addEntryPanel(entryPanels.get(i));
		}
		numPlacedFields = entryPanels.size();
		entry = datStructure.extraEntry;
		for (int i = numBaseFields; i < numSavedFields; i++){
			if (i >= numPlacedFields){
				entryPanels.add(new EntryPanel(entry, i));
			}
			addEntryPanel(entryPanels.get(i));
		}
		for (int i = numPlacedFields; i > numSavedFields; i--){
			entryPanels.remove(i-1);
		}
		
		if (datStructure.extraEntry != null){
			addID.setVisible(true);
			if (numSavedFields > numBaseFields){
				removeID.setVisible(true);
			}
		} else {
			addID.setVisible(false);
			removeID.setVisible(false);
		}
	}
	
	public void addField(){
		EntryPanel entryPanel = new EntryPanel(datStructure.extraEntry, entryPanels.size());
		entryPanels.add(entryPanel);
		addEntryPanel(entryPanel);
		removeID.setEnabled(true);
		entryPanel = entryPanels.get(datStructure.indexCountExtra);
		int value = (int) entryPanel.getVal();
		entryPanel.setVal(value+1);
		panelFields.updateUI();
	}

	public void removeField(){
		int index = entryPanels.size()-1;
		if (index >= datStructure.entries.length){
			EntryPanel entryPanel = entryPanels.remove(index);
			panelFields.remove(index);
			index--;
			if (col > 0){
				col--;
			} else {
				row--;
				col = 3;
			}
			if (index == datStructure.entries.length-1){
				removeID.setEnabled(false);
			}
			entryPanel = entryPanels.get(datStructure.indexCountExtra);
			int value = (int) entryPanel.getVal();
			entryPanel.setVal(value-1);
			panelFields.updateUI();
		}
	}


	
	
	
	
	@Override
	public void onCloseEvent (WindowEvent e) {
		dispose();
	}
	
	
	
	
	
	public void loadEntry(Entry entry){
		System.out.println("Load entry: " + entry);
		int n = entry.values.size();
		for (int i = 0; i < n; i++){
			entryPanels.get(i).setVal(entry.values.get(i));
		}
	}
	
	public void saveEntry(){
		int numBaseFields = datStructure.entries.length;
		List<Object> values = currentEntry.values;
		for (int i = 0; i < numBaseFields; i++){
			if (datStructure.entries[i].editable){
				values.set(i, entryPanels.get(i).getVal());
			}
		}

		if (datStructure.extraEntry != null){
			numSavedFields = values.size();
			int numPlacedFields = entryPanels.size();
			for (int i = numBaseFields; i < numPlacedFields; i++){
				if (i < numSavedFields){
					values.set(i, entryPanels.get(i).getVal());
				} else {
					values.add(entryPanels.get(i).getVal());
				}
			}
			numSavedFields = values.size();
			for (int i = numPlacedFields; i < numSavedFields; i++){
				values.remove(i);
			}
			values.set(datStructure.indexCountExtra, entryPanels.get(datStructure.indexCountExtra).getVal());
		}
		System.out.println("Save entry: " + currentEntry);
		DBManager manager = new DBManager(datStructure, file);
		try {
			manager.save(entryGroups);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	

	
	

	public class SearchEntryGroupListener implements KeyListener {
		@Override
		public void keyPressed (KeyEvent e) {
			Object find = null;
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				entryGroupSearch.find(entryGroupSearchField.getText());
				if (e.isShiftDown()){
					find = entryGroupSearch.getNext();
				} else {
					find = entryGroupSearch.getPrevious();
				}
				if (find != null){
					entryGroupList.setSelectedElement((EntryGroup) find, true);
				}
			}
		}
		
		@Override
		public void keyReleased (KeyEvent e) {}
		
		@Override
		public void keyTyped (KeyEvent e) {}
	}

	public class SearchDataListener extends KeyAdapter {
		@Override public void keyPressed (KeyEvent e) {
			Object find = null;
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				entrySearch.find(entrySearchField.getText());
				if (e.isShiftDown()){
					find = entrySearch.getNext();
				} else {
					find = entrySearch.getPrevious();
				}
				if (find != null){
					entryList.setSelectedElement((Entry) find, true);
				}
			}
		}
	}


	public void dataSetListSelectionListener (ListSelectionEvent e){
		if (e == null || !e.getValueIsAdjusting()){
			EntryGroup selected = entryGroupList.getSelectedElement();
			if (selected != null) {
				currentEntryGroup = selected;
				entryList.setVector(currentEntryGroup.entries);
				entrySearch = new SearchEntry(currentEntryGroup.entries);
				if (currentEntryGroup.entries.size() > 0){
					entryList.setSelectedIndex(0, true);
				}
			}
		}
	}

	public void dataListSelectionListener (ListSelectionEvent e){
		if (e == null || !e.getValueIsAdjusting()){
			Entry selected = entryList.getSelectedElement();
			if (selected != null){
				currentEntry = selected;
				buildFields(currentEntry);
				loadEntry(currentEntry);
			}
		}
	}

	
	
	public class JShowPopupMenu extends MouseAdapter {
		public Component component;
		public JPopupMenu popupMenu;
		public JShowPopupMenu(Component component, JPopupMenu popupMenu){
			this.component = component;
			this.popupMenu = popupMenu;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			rightClicked = (Component) e.getSource();
			if (component instanceof JListEx){
				JListEx<?> jlist = (JListEx<?>) component;
				int selected = jlist.locationToIndex(e.getPoint());
				if (selected >= 0){
					jlist.setSelectedIndex(selected);
				}
			}
			showMenu(e);
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			showMenu(e);
		}

		public void showMenu(MouseEvent e){
			if (component instanceof JListEx){
				if (!datStructure.supportNumAlteration){
					return;
				}
			}
			if (e.isPopupTrigger()) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	

	
	
	private void entryListMenuAdd (){
		EntryGroup lastEntryGroup = entryGroups.get(entryGroups.size()-1);
		Entry lastEntry = lastEntryGroup.entries.get(lastEntryGroup.entries.size() - 1);
		Entry newEntry = new Entry(datStructure, lastEntry.sequenceNumber+1, lastEntry.ID+1);
		currentEntryGroup.entries.add(newEntry);
		entryList.add(newEntry);
		entrySearch = new SearchEntry(currentEntryGroup.entries);
	}
	
	private void entryListMenuRemove () {
		if (currentEntry != null){
			if (JOptionPane.showConfirmDialog(GUIEditor.this, "You're going to delete " + currentEntry + "\nAre you sure?", "Delete entry", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, GUIMain.IMAGE_ICON) == 0) {
				currentEntryGroup.entries.remove(currentEntry);
				entryList.drop(currentEntry);
				entrySearch = new SearchEntry(currentEntryGroup.entries);
			}
		}
	}

	public void entryListMoveTo (){
		final EntryGroup sourceGroup = currentEntryGroup;
		if (sourceGroup == null) {
			return;
		}
		final int entryIndex = entryList.getSelectedIndex();
		if (entryIndex < 0) {
			return;
		}
		Entry entry = entryList.getSelectedElement();
		JDialog dialog = new JDialog(GUIEditor.this, ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Move entry to another group");
		dialog.setBounds(AbstractGUI.getBounds(dialog, 0.3, 0.6));
		dialog.setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{30, 400, 50, 50}, new double[]{1.0}, new double[]{0, 0.8, 0.1, 0.1}));
		JLabel label = new JLabel("Select the new group for " + entry);
		JListEx<EntryGroup> dialogList = new JListEx<>(entryGroups.toArray(new EntryGroup[entryGroups.size()]));
		dialogList.drop(currentEntryGroup);
		JScrollPane scrollPane = new JScrollPane(dialogList);
		JButton dlgConfirm = new JButton("Confirm");
		JButton dlgCancel = new JButton("Cancel");
		dlgConfirm.addActionListener(al -> {
			EntryGroup targetGroup = dialogList.getSelectedElement();
			if (targetGroup == null) {
				return;
			}
			sourceGroup.entries.remove(entryIndex);
			targetGroup.entries.add(entry);
			entryList.setVector(currentEntryGroup.entries);
			dialog.dispose();
		});
		dlgCancel.addActionListener(al -> dialog.dispose());
		dialog.add(label, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		dialog.add(scrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		dialog.add(dlgConfirm, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		dialog.add(dlgCancel, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));
		DialogKeyListener keyListener = new DialogKeyListener(dialog);
		label.addKeyListener(keyListener);
		dialogList.addKeyListener(keyListener);
		scrollPane.addKeyListener(keyListener);
		dlgConfirm.addKeyListener(keyListener);
		dlgCancel.addKeyListener(keyListener);
		dialog.getContentPane().addKeyListener(keyListener);
		dialog.addKeyListener(keyListener);
		dlgConfirm.requestFocus();
		dialog.setVisible(true);
	}
	
	

	public class EntryValueMap{
		public final Map<Object, List<Entry>> map;
		public final Map<Object, List<Entry>> mapClean;
		public EntryValueMap (Map <Object, List<Entry>> map, Map <Object, List<Entry>> mapClean) {
			this.map = map;
			this.mapClean = mapClean;
		}
	}
	
	
	/**
	 * Scan all fields and group entries by value
	 * @param index
	 * @param filterUnused
	 * @return
	 */
	public EntryValueMap getValuesMap(int index, boolean filterUnused){
		Map<Object, List<Entry>> valueEntryMap = new HashMap<>();
		Map<Object, List<Entry>> valueEntryMapClean = new HashMap<>();
		List<Entry> entries;
		Object value;
		for (EntryGroup entryGroup : entryGroups){
			for (Entry entry : entryGroup.entries){
				value = entry.values.get(index);
				if (!valueEntryMap.containsKey(value)){
					entries = new ArrayList<>();
					entries.add(entry);
					valueEntryMap.put(value, entries);
				} else {
					valueEntryMap.get(value).add(entry);
				}

				if (filterUnused && !entry.toString().equals("<Undefined>")){
					if (!valueEntryMapClean.containsKey(value)){
						entries = new ArrayList<>();
						entries.add(entry);
						valueEntryMapClean.put(value, entries);
					} else {
						valueEntryMapClean.get(value).add(entry);
					}
				}
			}
		}
		return new EntryValueMap (new TreeMap<>(valueEntryMap), new TreeMap<>(valueEntryMapClean));
	}
	
	
	/**
	 * Scan all entries and find all fields which are either unused (same value everywhere) or have up to 2 values (including flags/boolean)
	 */
	public void findUnusedFields(){
		EntryValueMap entryValueMap;
		EntryStruct entryStruct;
		int size;
		for (EntryPanel entryPanel : entryPanels){
			entryStruct = entryPanel.entryStruct;
			if (entryStruct.name == null || entryStruct.type == dbstructure.Type.UKNONWN) {
				entryValueMap = getValuesMap(entryPanel.index, true);
				size = entryValueMap.mapClean.size();
				if (size <= 2) {
					entryPanel.label.setBackground(Color.BLACK);
					entryPanel.label.setOpaque(true);
					if (size == 1){
						entryPanel.label.setForeground(EntryStruct.UNCHANGE_COLOR);
					} else {
						entryPanel.label.setForeground(Color.RED);
					}
				}
			}
		}
	}
	
	
	/**
	 * Search all values used in the selected field and the entries which uses these values.
	 * Since the list of entries can be very big, the user can double click on a result to get the full list.
	 * The double click is handled by
	 * @see #fieldMenuSearchValuesList()
	 */
	public void fieldMenuSearchValues (){
		ValueField field = (ValueField) rightClicked;
		EntryValueMap entryValueMap = getValuesMap(field.getIndex(), true);

		JDialog dialog = new JDialog(GUIEditor.this, ModalityType.APPLICATION_MODAL);
		JLabel dlgLabel = new JLabel("All values and entries which use them (double click for full list):");
		JListEx<List<Entry>> dlgList = new JListEx<>(entryValueMap.mapClean.values());
		JListEx<Object> rowHeaderList = new JListEx<>(entryValueMap.mapClean.keySet());
		JScrollPane dlgScrollPane = new JScrollPane(dlgList);
		JCheckBox dlgHideUnused = new JCheckBox("Hide undefined entries");
		JButton dlgClose = new JButton("Close");

		dlgList.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked (MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = dlgList.getSelectedIndex();
					fieldMenuSearchValuesList(dlgList.get(index), rowHeaderList.get(index));
				}
			}
		});
		rowHeaderList.setBackground(Color.LIGHT_GRAY);
		dlgScrollPane.setRowHeaderView(rowHeaderList);
		dlgHideUnused.setSelected(true);
		dlgHideUnused.addChangeListener(e -> {
			if (dlgHideUnused.isSelected()){
				dlgList.setVector(entryValueMap.mapClean.values());
				rowHeaderList.setVector(entryValueMap.mapClean.keySet());
			} else {
				dlgList.setVector(entryValueMap.map.values());
				rowHeaderList.setVector(entryValueMap.map.keySet());
			}
		});
		dlgClose.addActionListener(al -> dialog.dispose());

		DialogKeyListener dlgKeyListener = new DialogKeyListener(dialog);
		dlgLabel.addKeyListener(dlgKeyListener);
		dlgList.addKeyListener(dlgKeyListener);
		rowHeaderList.addKeyListener(dlgKeyListener);
		dlgScrollPane.addKeyListener(dlgKeyListener);
		dlgHideUnused.addKeyListener(dlgKeyListener);
		dlgClose.addKeyListener(dlgKeyListener);
		dialog.getContentPane().addKeyListener(dlgKeyListener);
		dialog.addKeyListener(dlgKeyListener);
		
		dialog.setTitle("For field: " + field.getEntryStruct());
		dialog.setBounds(AbstractGUI.getBounds(dialog, 0.3, 0.6));
		dialog.setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{30, 400, 25, 50}, new double[]{1.0}, new double[]{0, 1.0, 0, 0}));
		dialog.add(dlgLabel, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		dialog.add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		dialog.add(dlgHideUnused, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		dialog.add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));
		dialog.setVisible(true);
	}

	
	/**
	 * Search all entries which have the same value in the selected field.
	 */
	public void fieldMenuSearchField(){
		ValueField field = (ValueField) rightClicked;
		int index = field.getIndex();
		Object value = field.getVal();
		Object entryValue;
		List<Entry> entries = new ArrayList<>();
		List<Entry> entriesClean = new ArrayList<>();
		
		for (EntryGroup entryGroup : entryGroups){
			for (Entry entry : entryGroup.entries){
				entryValue = entry.values.get(index);
				if (value.equals(entryValue)){
					entries.add(entry);
					if (index != datStructure.indexName && !entry.toString().equals("<Undefined>")){
						entriesClean.add(entry);
					}
				}
			}
		}

		JDialog dialog = new JDialog(GUIEditor.this, ModalityType.APPLICATION_MODAL);
		JLabel dlgLabel = new JLabel("All entries with the same value:");
		JListEx<Entry> dlgList = new JListEx<>(entriesClean);
		JScrollPane dlgScrollPane = new JScrollPane(dlgList);
		JCheckBox dlgHideUnused = new JCheckBox("Hide unused entries");
		JButton dlgClose = new JButton("Close");

		dlgHideUnused.setSelected(true);
		dlgHideUnused.addChangeListener(e -> {
			if (dlgHideUnused.isSelected()){
				dlgList.setVector(entriesClean);
			} else {
				dlgList.setVector(entries);
			}
		});
		dlgClose.addActionListener(al -> dialog.dispose());

		DialogKeyListener dlgKeyListener = new DialogKeyListener(dialog);
		dlgLabel.addKeyListener(dlgKeyListener);
		dlgList.addKeyListener(dlgKeyListener);
		dlgScrollPane.addKeyListener(dlgKeyListener);
		dlgHideUnused.addKeyListener(dlgKeyListener);
		dlgClose.addKeyListener(dlgKeyListener);
		dialog.getContentPane().addKeyListener(dlgKeyListener);
		dialog.addKeyListener(dlgKeyListener);

		dialog.setTitle("For field: " + field.getEntryStruct());
		dialog.setBounds(AbstractGUI.getBounds(dialog, 0.3, 0.6));
		dialog.setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{30, 400, 25, 50}, new double[]{1.0}, new double[]{0, 1.0, 0, 0}));
		dialog.add(dlgLabel, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		dialog.add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		dialog.add(dlgHideUnused, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		dialog.add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));
		dialog.setVisible(true);
	}




	/**
	 * When the user use the "Search values" feature he can double click on any entry to get the full list of entries.
	 * This class handle the double click handler.
	 * @author MarcoForlini
	 * @param the selected entry
	 * @param the selected value
	 * @see #fieldMenuSearchValues()
	 */
	public void fieldMenuSearchValuesList (List <Entry> list, Object value){
		if (list != null){
			JDialog dialog = new JDialog(GUIEditor.this, ModalityType.APPLICATION_MODAL);
			JLabel dlgLabel = new JLabel("All entries with this value:");
			JListEx<Entry> dlgList = new JListEx<>(list);
			JScrollPane dlgScrollPane = new JScrollPane(dlgList);
			JButton dlgClose = new JButton("Close");
			
			DialogKeyListener dlgKeyListener = new DialogKeyListener(dialog);
			dlgLabel.addKeyListener(dlgKeyListener);
			dlgList.addKeyListener(dlgKeyListener);
			dlgScrollPane.addKeyListener(dlgKeyListener);
			dlgClose.addKeyListener(dlgKeyListener);
			dialog.getContentPane().addKeyListener(dlgKeyListener);
			dialog.addKeyListener(dlgKeyListener);
			dlgClose.addActionListener(e2 -> dialog.dispose());
			
			dialog.setTitle("For value: " + value);
			dialog.setBounds(AbstractGUI.getBounds(dialog, 0.225, 0.45));
			dialog.setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{30, 400, 50}, new double[]{1.0}, new double[]{0, 1.0, 0}));
			dialog.add(dlgLabel, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
			dialog.add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
			dialog.add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 2));
			dialog.setVisible(true);
		}
	}


	
	/**
	 * Listener for the dialogs. Allow the user to close the dialog with the ESC key
	 * @author MarcoForlini
	 *
	 */
	public class DialogKeyListener extends KeyAdapter {
		private final JDialog dialog;
		public DialogKeyListener(JDialog dialog){
			this.dialog = dialog;
		}

		@Override
		public void keyPressed (KeyEvent e) {
			System.out.println("Key pressed: " + e.getKeyChar() + " " + e.getKeyCode());
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
				dialog.dispose();
			}
		}
	}

}
