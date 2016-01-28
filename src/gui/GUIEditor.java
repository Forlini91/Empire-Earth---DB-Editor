package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import dbmanager.Core;
import dbmanager.DBManager;
import dbmanager.EntryGroupSearcher;
import dbmanager.EntrySearcher;
import dbstructure.DatStructure;
import dbstructure.Entry;
import dbstructure.EntryGroup;
import dbstructure.EntryStruct;
import dbstructure.EntryValueMap;
import dbstructure.Knowledge;
import gui.components.AbstractEntryField;
import gui.components.AbstractFrame;
import gui.components.AbstractGUI;
import gui.components.DialogCloseKeyListener;
import gui.components.JListEntry;
import gui.components.JPanelEntry;
import gui.components.JSearchFieldEntry;
import gui.ui.EEScrollBarUI;
import gui.ui.EESliderUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;


public class GUIEditor extends AbstractFrame {
	
	private static final long serialVersionUID = -3426470254615698936L;
	
	private final LayoutManager gbl_contentPane = new GridBagLayoutExtended(new int[]{160, 200, 100, 200, 200, 100}, new int[]{30, 400, 30, 30}, new double[]{0, 0, 0.5, 0, 0, 0.5}, new double[]{0.0, 1.0, 0});
	private final GridLayout gridLayout = new GridLayout(0, 4, 0, 0);
	private static final GridBagConstraints gbc_entryGroupListLabel = new GridBagConstraintsExtended(4, 4, 0, 0, 0, 0);
	private static final GridBagConstraints gbc_entryGroupListPane = new GridBagConstraintsExtended(4, 4, 0, 0, 0, 1);
	private static final GridBagConstraints gbc_entryGroupSearchField = new GridBagConstraintsExtended(4, 4, 0, 0, 0, 2);
	private static final GridBagConstraints gbc_entryListLabel = new GridBagConstraintsExtended(4, 4, 0, 0, 1, 0);
	private static final GridBagConstraints gbc_entryListPane = new GridBagConstraintsExtended(4, 4, 0, 0, 1, 1);
	private static final GridBagConstraints gbc_entrySearchField = new GridBagConstraintsExtended(4, 4, 0, 0, 1, 2);
	private static final GridBagConstraints gbc_fieldsLabel = new GridBagConstraintsExtended(4, 4, 0, 0, 2, 0, 4, 1);
	private static final GridBagConstraints gbc_fieldsPane  = new GridBagConstraintsExtended(4, 4, 0, 4, 2, 1, 4, 2);
	private static final GridBagConstraints gbc_resetButton = new GridBagConstraintsExtended(4, 4, 4, 0, 0, 3);
	private static final GridBagConstraints gbc_saveButton = new GridBagConstraintsExtended(4, 4, 4, 0, 1, 3);
	private static final GridBagConstraints gbc_hideUnused = new GridBagConstraintsExtended(4, 4, 4, 0, 2, 3);
	private static final GridBagConstraints gbc_removeID = new GridBagConstraintsExtended(4, 4, 4, 0, 3, 3);
	private static final GridBagConstraints gbc_addID = new GridBagConstraintsExtended(4, 4, 4, 4, 4, 3);
	
	public final File file;
	private DatStructure datStructure;
	private List<EntryGroup> entryGroups;
	private List<JPanelEntry> entryPanels;
	private EntryGroup currentEntryGroup;
	private Entry currentEntry = null;
	private Component rightClicked = null;
	private Set<JPanelEntry> marked = new HashSet<>(30);
	private int numSavedFields;
	

	private JPanel contentPane = new JPanel();
	private JLabel entryGroupListLabel = new JLabel("Group");
	private JLabel entryListLabel = new JLabel("Entries");
	private JLabel fieldsLabel = new JLabel("Fields");
	private JListEntry<EntryGroup> entryGroupList = new JListEntry<EntryGroup>();
	private JListEntry<Entry> entryList = new JListEntry<Entry>();
	private JScrollPane entryGroupListPane = new JScrollPane(entryGroupList);
	private JScrollPane entryListPane = new JScrollPane(entryList);
	private JPanel panelFields = new JPanel();
	private JScrollPane scrollPaneFields = new JScrollPane(panelFields);
	private JSearchFieldEntry<EntryGroup> entryGroupSearchField = new JSearchFieldEntry<>(new EntryGroupSearcher(entryGroupList));
	private JSearchFieldEntry<Entry> entrySearchField = new JSearchFieldEntry<>(new EntrySearcher(entryList));
	private JButton reset = new JButton("Reset entry");
	private JButton save = new JButton("Save entry");
	private JButton addID = new JButton("Add ID");
	private JButton removeID = new JButton("Remove ID");

	private final JPopupMenu fieldMenu = new JPopupMenu();
	private final JMenuItem fieldMenuSearchValues = new JMenuItem("Show all values used for this field");
	private final JMenuItem fieldMenuSearchFields = new JMenuItem("Show all fields with the same value");
	private final JMenuItem fieldMenuMarkUnusedFields = new JMenuItem("Mark all unused/dual value fields");
	private final JMenuItem fieldMenuUnmarkUnusedFields = new JMenuItem("Remove marks");

	private final JPopupMenu entryListMenu = new JPopupMenu();
	private final JMenuItem entryListMenuAdd = new JMenuItem("Add entry");
	private final JMenuItem entryListMenuRemove = new JMenuItem("Remove entry");
	private final JMenuItem entryListMenuMoveTo = new JMenuItem("Move to group...");
	
	private final JMenuBar menuBar = new JMenuBar();
	
	



	
	{
		setBounds(AbstractGUI.getBounds(this, 0.85, 0.85));
		setIconImage(GUIMain.IMAGE_ICON.getImage());
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setContentPane(contentPane);
		

		JMenu menuBarNumColumns = new JMenu("Num columns");
		JPanel menuBarNumColumnsPanel = new JPanel();
		JLabel numColumnsLabel = new JLabel("Columns: 4");
		JSlider numColumnsSlider = new JSlider();
		menuBarNumColumnsPanel.setLayout(new GridLayout(2, 1, 0, 0));
		menuBarNumColumnsPanel.add(numColumnsLabel);
		menuBarNumColumnsPanel.add(numColumnsSlider);
		menuBarNumColumns.add(menuBarNumColumnsPanel);
		menuBar.add(menuBarNumColumns);
		numColumnsSlider.setUI(new EESliderUI(numColumnsSlider, Core.uiColorElement));
		numColumnsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		numColumnsSlider.setMinimum(4);
		numColumnsSlider.setMaximum(32);
		numColumnsSlider.setValue(4);
		numColumnsSlider.setSnapToTicks(true);
		numColumnsSlider.setMinorTickSpacing(1);
		numColumnsSlider.setMajorTickSpacing(2);
		numColumnsSlider.setPreferredSize(new Dimension(250, numColumnsSlider.getPreferredSize().height));
		numColumnsSlider.addChangeListener(e -> {
			gridLayout.setColumns(numColumnsSlider.getValue());
			numColumnsLabel.setText("Columns: " + numColumnsSlider.getValue());
			panelFields.invalidate();
		});
		menuBarNumColumns.setBackground(Core.uiColorElement);
		menuBarNumColumns.setForeground(Color.WHITE);
		menuBarNumColumns.setOpaque(true);
		menuBarNumColumnsPanel.setBackground(Core.uiColorBackground);
		numColumnsLabel.setOpaque(false);
		numColumnsSlider.setOpaque(false);
		menuBar.setBackground(Core.uiColorBackground);
		menuBar.setOpaque(true);
		setJMenuBar(menuBar);

		contentPane.setLayout(gbl_contentPane);
		contentPane.add(entryGroupListLabel, gbc_entryGroupListLabel);
		contentPane.add(entryListLabel, gbc_entryListLabel);
		contentPane.add(fieldsLabel, gbc_fieldsLabel);
		contentPane.add(entryGroupListPane, gbc_entryGroupListPane);
		contentPane.add(entryListPane, gbc_entryListPane);
		contentPane.add(scrollPaneFields, gbc_fieldsPane);
		contentPane.add(entryGroupSearchField, gbc_entryGroupSearchField);
		contentPane.add(entrySearchField, gbc_entrySearchField);
		contentPane.add(reset, gbc_resetButton);
		contentPane.add(save, gbc_saveButton);
		contentPane.add(entryList.hideUnusedBox, gbc_hideUnused);
		contentPane.add(addID, gbc_addID);
		contentPane.add(removeID, gbc_removeID);
		contentPane.setBackground(Core.uiColorBackground);
		scrollPaneFields.getVerticalScrollBar().setUI(new EEScrollBarUI());
		scrollPaneFields.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		panelFields.setBackground(Core.uiColorBackground);

		entryGroupListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		entryGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		entryGroupList.addListSelectionListener(e -> {
			if (e == null || !e.getValueIsAdjusting()){
				EntryGroup selected = entryGroupList.getSelectedElement();
				if (selected != null) {
					currentEntryGroup = selected;
					entryList.setList(currentEntryGroup.entries);
					if (currentEntryGroup.entries.size() > 0){
						entryList.setSelectedIndex(0);
					}
				}
			}
		});
		entryGroupListPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		entryGroupListPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		
		entryListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		entryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		entryList.addListSelectionListener(e -> {
			if (e == null || !e.getValueIsAdjusting()){
				Entry selected = entryList.getSelectedElement();
				if (selected != null){
					currentEntry = selected;
					buildFields(currentEntry);
					loadEntry(currentEntry);
				}
			}
		});
		entryList.addMouseListener(new JShowPopupMenuList(entryList, entryListMenu));
		entryListPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		entryListPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());

		entryListMenu.add(entryListMenuAdd);
		entryListMenu.add(entryListMenuRemove);
		entryListMenu.add(entryListMenuMoveTo);
		entryListMenuAdd.addActionListener(e -> {
			EntryGroup lastEntryGroup = entryGroups.get(entryGroups.size()-1);
			Entry lastEntry = lastEntryGroup.entries.get(lastEntryGroup.entries.size() - 1);
			Entry newEntry = new Entry(datStructure, lastEntry.sequenceNumber+1, lastEntry.ID+1);
			currentEntryGroup.entries.add(newEntry);
			entryList.setList(currentEntryGroup.entries);
		});
		entryListMenuRemove.addActionListener(e -> {
			if (currentEntry != null){
				if (JOptionPane.showConfirmDialog(GUIEditor.this, "You're going to delete " + currentEntry + "\nAre you sure?", "Delete entry", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, GUIMain.IMAGE_ICON) == 0) {
					currentEntryGroup.entries.remove(currentEntry);
					entryList.setList(currentEntryGroup.entries);
				}
			}
		});
		entryListMenuMoveTo.addActionListener(e -> entryListMoveTo());

		fieldsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPaneFields.getVerticalScrollBar().setUnitIncrement(10);
		//		fieldsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		fieldMenu.add(fieldMenuSearchValues);
		fieldMenu.add(fieldMenuSearchFields);
		fieldMenu.add(fieldMenuMarkUnusedFields);
		fieldMenu.add(fieldMenuUnmarkUnusedFields);
		fieldMenuSearchValues.addActionListener(e -> showSearchValuesResults());
		fieldMenuSearchFields.addActionListener(e -> showSearchFieldResults());
		fieldMenuMarkUnusedFields.addActionListener(e -> markUnusedFields());
		fieldMenuUnmarkUnusedFields.addActionListener(e -> unmarkUnusedFields());
		fieldMenuUnmarkUnusedFields.setVisible(false);
		panelFields.setLayout(gridLayout);

		reset.setBackground(Core.uiColorElement);
		reset.setForeground(Color.WHITE);
		reset.addActionListener(e -> {
			buildFields(currentEntry);
			loadEntry(currentEntry);
		});
		save.setBackground(Core.uiColorElement);
		save.addActionListener(e -> saveEntry());
		save.setForeground(Color.WHITE);
		addID.setBackground(Core.uiColorElement);
		addID.addActionListener(e -> addField());
		addID.setForeground(Color.WHITE);
		removeID.setBackground(Core.uiColorElement);
		removeID.addActionListener(e -> removeField());
		removeID.setForeground(Color.WHITE);
	}
	




	

	


	
	public GUIEditor (File file, DatStructure datStructure, List<EntryGroup> entryGroups) {
		super("Editor - " + file.getName());
		this.file = file;
		this.datStructure = datStructure;
		this.entryGroups  = entryGroups;
		
		int nEntries = datStructure.entries.length;
		entryPanels = new ArrayList<>(datStructure.indexCountExtra < 0 ? nEntries : nEntries+20);
		
		entryGroupList.setList(entryGroups);
		if (entryGroups.size() > 0){
			entryListMenuMoveTo.setVisible(entryGroups.size() > 1);
			entryGroupList.setSelectedIndex(0);
		}

		setTitle("EE - DB Editor: " + datStructure.fileName);
		setAutoRequestFocus(true);
		setVisible(true);
	}

	@Override
	public void onGainFocusEvent (WindowEvent e) {
		super.onGainFocusEvent(e);
		panelFields.revalidate();
		panelFields.repaint();
	}
	
	@Override
	public void onCloseEvent (WindowEvent e) {
		super.onCloseEvent(e);
		Core.dbEditors.remove(this);
	}
	
	


	private void buildFields(Entry entries){
		int numBaseFields = datStructure.entries.length;
		int numPlacedFields = entryPanels.size();
		numSavedFields = entries.values.size();
		EntryStruct entry;

		panelFields.removeAll();
		for (int i = 0; i < numBaseFields; i++){
			if (i >= numPlacedFields){
				entry = datStructure.entries[i];
				JPanelEntry entryPanel = new JPanelEntry(entry, i);
				entryPanels.add(entryPanel);
				Component component = (Component) entryPanel.field;
				component.addMouseListener(new JShowPopupMenuField(fieldMenu));
			}
			panelFields.add(entryPanels.get(i));
		}
		numPlacedFields = entryPanels.size();
		entry = datStructure.extraEntry;
		for (int i = numBaseFields; i < numSavedFields; i++){
			if (i >= numPlacedFields){
				entryPanels.add(new JPanelEntry(entry, i));
			}
			panelFields.add(entryPanels.get(i));
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
		JPanelEntry entryPanel = new JPanelEntry(datStructure.extraEntry, entryPanels.size());
		entryPanels.add(entryPanel);
		panelFields.add(entryPanel);
		removeID.setEnabled(true);
		entryPanel = entryPanels.get(datStructure.indexCountExtra);
		int value = (int) entryPanel.getVal();
		entryPanel.setVal(value+1);
		panelFields.updateUI();
	}
	
	public void removeField(){
		int index = entryPanels.size()-1;
		if (index >= datStructure.entries.length){
			JPanelEntry entryPanel = entryPanels.remove(index);
			panelFields.remove(index);
			index--;
			if (index == datStructure.entries.length-1){
				removeID.setEnabled(false);
			}
			entryPanel = entryPanels.get(datStructure.indexCountExtra);
			int value = (int) entryPanel.getVal();
			entryPanel.setVal(value-1);
			panelFields.updateUI();
		}
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
		try {
			DBManager dbManager = new DBManager(datStructure, file);
			dbManager.save(entryGroups);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void goToEntry(EntryGroup entryGroup, Entry entry){
		if (!isVisible()){
			setVisible(true);
		}
		entryGroupList.setSelectedElement(entryGroup);
		if (entryGroupList.getLength() > 0){
			entryList.setSelectedElement(entry);
		}
	}



	


	
	
	

	
	
	
	


	public class JShowPopupMenuList extends MouseAdapter {
		public JListEntry<?> list;
		public JPopupMenu popupMenu;
		public JShowPopupMenuList(JListEntry<?> list, JPopupMenu popupMenu){
			this.list = list;
			this.popupMenu = popupMenu;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			JListEntry<?> jlist = list;
			int selected = jlist.locationToIndex(e.getPoint());
			if (selected >= 0){
				jlist.setSelectedIndex(selected);
			}
			showMenu(e);
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			showMenu(e);
		}
		
		public void showMenu(MouseEvent e){
			if (datStructure.supportNumAlteration){
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}
	}

	public class JShowPopupMenuField extends MouseAdapter {
		public JPopupMenu popupMenu;
		public JShowPopupMenuField(JPopupMenu popupMenu){
			this.popupMenu = popupMenu;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			rightClicked = (Component) e.getSource();
			showMenu(e);
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			showMenu(e);
		}
		
		public void showMenu(MouseEvent e){
			if (e.isPopupTrigger()) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}


	



	/**
	 * Show a dialog which allow the user to move the selected field to another group.
	 * Don't use on files without groups (only dbtechtree.dat has groups, so far).
	 */
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
		List<EntryGroup> dialogListGroups = new ArrayList<>(entryGroups);
		dialogListGroups.remove(currentEntryGroup);
		JListEntry<EntryGroup> dialogList = new JListEntry<>(dialogListGroups);
		JScrollPane scrollPane = new JScrollPane(dialogList);
		JButton dlgConfirm = new JButton("Confirm");
		JButton dlgCancel = new JButton("Cancel");
		dlgConfirm.addActionListener(al -> {
			EntryGroup targetGroup = dialogList.getSelectedElement();
			if (targetGroup == null) {
				return;
			}
			sourceGroup.entries.remove(entry);
			targetGroup.entries.add(entry);
			entryList.setList(currentEntryGroup.entries);
			dialog.dispose();
		});
		dlgCancel.addActionListener(al -> dialog.dispose());
		dialog.add(label, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		dialog.add(scrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		dialog.add(dlgConfirm, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		dialog.add(dlgCancel, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));
		DialogCloseKeyListener keyListener = new DialogCloseKeyListener(dialog);
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




	
	/**
	 * Scan all fields and group entries by value
	 * @param index	index of the field to read
	 * @param filterUndefined	if true, create a second map which only contains fully defined fields
	 * @return
	 */
	public EntryValueMap getValuesMap(int index, boolean filterUndefined){
		Map<Object, List<Entry>> valueEntryMap = new HashMap<>();
		Map<Object, List<Entry>> valueEntryMapClean = new HashMap<>();
		List<Entry> entries;
		Object value;
		int counter = 0;
		for (EntryGroup entryGroup : entryGroups){
			for (Entry entry : entryGroup.entries){
				counter++;
				value = entry.values.get(index);
				if (!valueEntryMap.containsKey(value)){
					entries = new ArrayList<>();
					entries.add(entry);
					valueEntryMap.put(value, entries);
				} else {
					valueEntryMap.get(value).add(entry);
				}
				
				if (filterUndefined && entry.ID >= 0 && entry.sequenceNumber >= 0){
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
		return new EntryValueMap (new TreeMap<>(valueEntryMap), new TreeMap<>(valueEntryMapClean), counter);
	}



	/**
	 * Show all entries which have the same value in the selected field.
	 */
	public void showSearchFieldResults(){
		AbstractEntryField field = (AbstractEntryField) rightClicked;
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
		JListEntry<Entry> dlgList = new JListEntry<>(entriesClean);
		JScrollPane dlgScrollPane = new JScrollPane(dlgList);
		JSearchFieldEntry<Entry> dlgSearch = new JSearchFieldEntry<>(new EntrySearcher(dlgList));
		JCheckBox dlgHideUnused = dlgList.hideUnusedBox;
		JButton dlgClose = new JButton("Close");
		dialog.getContentPane().setBackground(Core.uiColorBackground);
		dlgLabel.setOpaque(false);
		dlgScrollPane.setOpaque(false);
		dlgScrollPane.getViewport().setOpaque(false);
		dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		dlgClose.setBackground(Core.uiColorElement);
		dlgClose.setForeground(Color.WHITE);
		
		DialogCloseKeyListener dlgKeyListener = new DialogCloseKeyListener(dialog);
		dlgLabel.addKeyListener(dlgKeyListener);
		dlgList.addKeyListener(dlgKeyListener);
		dlgScrollPane.addKeyListener(dlgKeyListener);
		dlgHideUnused.addKeyListener(dlgKeyListener);
		dlgSearch.addKeyListener(dlgKeyListener);
		dlgClose.addKeyListener(dlgKeyListener);
		dialog.getContentPane().addKeyListener(dlgKeyListener);
		dialog.addKeyListener(dlgKeyListener);
		dlgClose.addActionListener(al -> dialog.dispose());
		
		dialog.setTitle("For field: " + field.getEntryStruct());
		dialog.setBounds(AbstractGUI.getBounds(dialog, 0.6, 0.8));
		dialog.setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{30, 400, 30, 25, 50}, new double[]{1.0}, new double[]{0, 1.0, 0, 0, 0}));
		dialog.add(dlgLabel, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		dialog.add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		dialog.add(dlgSearch, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		dialog.add(dlgHideUnused, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 3));
		dialog.add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 4));
		dialog.setVisible(true);
	}
	
	
	
	/**
	 * Show all values used in the selected field and also show the entries which uses these values.
	 * Since the list of entries can be very big, the user can double click on a result to get the full list.
	 * @see #fieldMenuSearchValuesResultsList(File, DatStructure, List)
	 * @see #getValuesMap(int, boolean)
	 */
	public void showSearchValuesResults (){
		AbstractEntryField field = (AbstractEntryField) rightClicked;
		EntryValueMap entryValueMap = getValuesMap(field.getIndex(), true);
		
		JDialog dialog = new JDialog(GUIEditor.this, ModalityType.APPLICATION_MODAL);
		JLabel dlgLabel = new JLabel("All values and entries which use them (double click for full list):");
		JListEntry<List<Entry>> dlgList = new JListEntry<>(new ArrayList<>(entryValueMap.map.values()), new ArrayList<>(entryValueMap.mapClean.values()));
		JListEntry<Object> rowHeaderList = new JListEntry<>(new ArrayList<>(entryValueMap.map.keySet()), new ArrayList<>(entryValueMap.mapClean.keySet()), dlgList.hideUnusedBox);
		JScrollPane dlgScrollPane = new JScrollPane(dlgList);
		JButton dlgClose = new JButton("Close");
		dialog.getContentPane().setBackground(Core.uiColorBackground);
		dlgLabel.setOpaque(false);
		dlgScrollPane.setOpaque(false);
		dlgScrollPane.getViewport().setOpaque(false);
		dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		dlgClose.setBackground(Core.uiColorElement);
		dlgClose.setForeground(Color.WHITE);
		
		DialogCloseKeyListener dlgKeyListener = new DialogCloseKeyListener(dialog);
		dlgLabel.addKeyListener(dlgKeyListener);
		dlgList.addKeyListener(dlgKeyListener);
		dlgList.hideUnusedBox.addKeyListener(dlgKeyListener);
		rowHeaderList.addKeyListener(dlgKeyListener);
		dlgScrollPane.addKeyListener(dlgKeyListener);
		dlgClose.addKeyListener(dlgKeyListener);
		dialog.getContentPane().addKeyListener(dlgKeyListener);
		dialog.addKeyListener(dlgKeyListener);
		dlgClose.addActionListener(al -> dialog.dispose());

		dialog.setTitle("For field: " + field.getEntryStruct());
		dialog.setBounds(AbstractGUI.getBounds(dialog, 0.6, 0.8));
		dialog.setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{30, 400, 25, 50}, new double[]{1.0}, new double[]{0, 1.0, 0, 0}));
		dialog.add(dlgLabel, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
		dialog.add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
		dialog.add(dlgList.hideUnusedBox, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
		dialog.add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));
		dialog.setVisible(true);
	}
	
	
	
	/**
	 * When the user use the "Search values" feature he can double click on any entry to get the full list of entries.
	 * This class handle the double click handler.
	 * @author MarcoForlini
	 * @param list the list with all entries
	 * @param value the value to filter the entries
	 * @see #showSearchValuesResults()
	 */
	public void showSearchValuesResultsList (List <Entry> list, Object value){
		if (list != null){
			JDialog dialog = new JDialog(GUIEditor.this, ModalityType.APPLICATION_MODAL);
			JLabel dlgLabel = new JLabel("All entries with this value:");
			JListEntry<Entry> dlgList = new JListEntry<>(list);
			JScrollPane dlgScrollPane = new JScrollPane(dlgList);
			JSearchFieldEntry<Entry> dlgSearch = new JSearchFieldEntry<>(new EntrySearcher(dlgList));
			JButton dlgClose = new JButton("Close");
			dialog.getContentPane().setBackground(Core.uiColorBackground);
			dlgLabel.setOpaque(false);
			dlgScrollPane.setOpaque(false);
			dlgScrollPane.getViewport().setOpaque(false);
			dlgScrollPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
			dlgScrollPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());
			dlgClose.setBackground(Core.uiColorElement);
			dlgClose.setForeground(Color.WHITE);

			DialogCloseKeyListener dlgKeyListener = new DialogCloseKeyListener(dialog);
			dlgLabel.addKeyListener(dlgKeyListener);
			dlgList.addKeyListener(dlgKeyListener);
			dlgScrollPane.addKeyListener(dlgKeyListener);
			dlgSearch.addKeyListener(dlgKeyListener);
			dlgClose.addKeyListener(dlgKeyListener);
			dialog.getContentPane().addKeyListener(dlgKeyListener);
			dialog.addKeyListener(dlgKeyListener);
			dlgClose.addActionListener(e2 -> dialog.dispose());

			dialog.setTitle("For value: " + value);
			dialog.setBounds(AbstractGUI.getBounds(dialog, 0.45, 0.6));
			dialog.setLayout(new GridBagLayoutExtended(new int[]{200}, new int[]{30, 400, 30, 50}, new double[]{1.0}, new double[]{0, 1.0, 0, 0}));
			dialog.add(dlgLabel, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 0));
			dialog.add(dlgScrollPane, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 1));
			dialog.add(dlgSearch, new GridBagConstraintsExtended(5, 5, 0, 5, 0, 2));
			dialog.add(dlgClose, new GridBagConstraintsExtended(5, 5, 5, 5, 0, 3));
			dialog.setVisible(true);
		}
	}
	
	
	
	/**
	 * Marks all fields which are either unused/unchanged (0/same value everywhere) or have up to 2 values (including flags/boolean).
	 * This can be very useful to identify many entries.
	 */
	public void markUnusedFields(){
		EntryValueMap entryValueMap;
		EntryStruct entryStruct;
		int size;
		for (JPanelEntry entryPanel : entryPanels){
			entryStruct = entryPanel.entryStruct;
			if (entryStruct.knowledge != Knowledge.KNOWN) {
				entryValueMap = getValuesMap(entryPanel.index, true);
				size = entryValueMap.mapClean.size();
				if (size <= 2 || size == entryValueMap.counter) {
					marked.add(entryPanel);
					entryPanel.label.setBackground(Color.BLACK);
					entryPanel.label.setOpaque(true);
					if (size == 1){
						if (entryStruct.knowledge != Knowledge.NEVER_CHANGE && entryStruct.knowledge != Knowledge.NEVER_USED) {
							entryPanel.label.setForeground(Color.RED);
						}
					} else if (size == 2){
						entryPanel.label.setForeground(Color.YELLOW);
					} else {
						entryPanel.label.setForeground(Color.BLUE);
					}
				}
			}
		}
		if (marked.size() > 0){
			fieldMenuMarkUnusedFields.setVisible(false);
			fieldMenuUnmarkUnusedFields.setVisible(true);
		}
	}
	
	public void unmarkUnusedFields(){
		Iterator<JPanelEntry> it = marked.iterator();
		JPanelEntry entryPanel;
		while(it.hasNext()){
			entryPanel = it.next();
			entryPanel.label.setOpaque(false);
			entryPanel.label.setBackground(null);
			entryPanel.label.setForeground(entryPanel.entryStruct.color);
			entryPanel.field.resetColor();
			it.remove();
		}
		fieldMenuMarkUnusedFields.setVisible(true);
		fieldMenuUnmarkUnusedFields.setVisible(false);
	}
	
}
