package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
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

import com.sun.glass.events.KeyEvent;

import datmanager.Core;
import datstructure.DatContent;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.EntryValueMap;
import datstructure.FieldStruct;
import datstructure.Knowledge;
import gui.components.AbstractEntryField;
import gui.components.JButtonRed;
import gui.components.JListDouble;
import gui.components.JListEntry;
import gui.components.JPanelEntry;
import gui.components.JScrollPaneRed;
import gui.components.JSearchFieldEntry;
import gui.ui.EEScrollBarUI;
import gui.ui.EESliderUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;
import gui.ui.GridLayoutExtended;


public class FrameEditor extends JFrame implements WindowListener, WindowFocusListener {
	
	
	private static final long serialVersionUID = -3426470254615698936L;
	
	private final LayoutManager gbl_contentPane = new GridBagLayoutExtended(new int[]{160, 200, 100, 200, 200, 100}, new int[]{400, 30, 30}, new double[]{0, 0, 0.5, 0, 0, 0.5}, new double[]{1.0, 0.0, 0});
	private final GridLayout gridLayout = new GridLayoutExtended(false, false, 0, 4, 0, 0);
	private static final GridBagConstraints gbc_entryGroupListPane = new GridBagConstraintsExtended(4, 4, 0, 0, 0, 0);
	private static final GridBagConstraints gbc_entryListPane = new GridBagConstraintsExtended(4, 4, 0, 0, 1, 0);
	private static final GridBagConstraints gbc_entrySearchField = new GridBagConstraintsExtended(4, 4, 0, 0, 1, 1);
	private static final GridBagConstraints gbc_scrollPaneFields  = new GridBagConstraintsExtended(4, 4, 0, 4, 2, 0, 4, 2);
	private static final GridBagConstraints gbc_switchList = new GridBagConstraintsExtended(4, 4, 4, 0, 0, 1);
	private static final GridBagConstraints gbc_resetButton = new GridBagConstraintsExtended(4, 4, 4, 0, 0, 2);
	private static final GridBagConstraints gbc_saveButton = new GridBagConstraintsExtended(4, 4, 4, 0, 1, 2);
	private static final GridBagConstraints gbc_removeID = new GridBagConstraintsExtended(4, 4, 4, 0, 3, 2);
	private static final GridBagConstraints gbc_addID = new GridBagConstraintsExtended(4, 4, 4, 4, 4, 2);
	private static final int GRID_MIN_ENTRY_SLOTS = 24;
	
	public DatContent datContent;
	public List<JPanelEntry> entryPanels;
	public EntryGroup currentEntryGroup;
	public Entry currentEntry = null;
	private Component rightClicked = null;
	private Set<JPanelEntry> marked = new HashSet<>(30);
	private int numSavedFields;
	private boolean saved = false;

	private JPanel contentPane = new JPanel();
	private JListDouble<EntryGroup> entryGroupList = new JListDouble<EntryGroup>("Hide unused fields", false);
	private JListEntry entryList = new JListEntry("Hide unused fields");
	private JScrollPane entryGroupListPane = new JScrollPaneRed(entryGroupList, "Group");
	private JScrollPane entryListPane = new JScrollPaneRed(entryList, "Entries");
	private JPanel panelFields = new JPanel();
	private JScrollPane scrollPaneFields = new JScrollPaneRed(panelFields, "Fields");
	private JSearchFieldEntry entrySearchField = new JSearchFieldEntry(entryList);
	private JButton reset = new JButtonRed("Reset entry");
	private JButton save = new JButtonRed("Save entry");
	private JButton addID = new JButtonRed("Add ID");
	private JButton removeID = new JButtonRed("Remove ID");
	private JSlider numColumnsSlider;

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
		setBounds(Core.getBounds(this, 0.85, 0.85));
		setIconImage(FrameMain.IMAGE_ICON.getImage());
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setContentPane(contentPane);
		

		JButton menuBarFile = new JButtonRed("Save to file");
		menuBarFile.addActionListener(e -> {
			Core.saveFile(FrameEditor.this, datContent);
			saved = false;
		});
		menuBarFile.setMnemonic(KeyEvent.VK_S);
		JMenu menuBarNumColumns = new JMenu("Num columns");
		JPanel menuBarNumColumnsPanel = new JPanel();
		JLabel numColumnsLabel = new JLabel("Columns: 4");
		numColumnsSlider = new JSlider();
		menuBarNumColumnsPanel.setLayout(new GridLayout(2, 1, 0, 0));
		menuBarNumColumnsPanel.add(numColumnsLabel);
		menuBarNumColumnsPanel.add(numColumnsSlider);
		menuBarNumColumns.add(menuBarNumColumnsPanel);
		numColumnsSlider.setUI(new EESliderUI(numColumnsSlider, Core.UI_COLOR_ELEMENT));
		numColumnsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		numColumnsSlider.setMinimum(2);
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
		menuBarNumColumns.setBackground(Core.UI_COLOR_ELEMENT);
		menuBarNumColumns.setForeground(Color.WHITE);
		menuBarNumColumns.setOpaque(true);
		menuBarNumColumnsPanel.setBackground(Core.UI_COLOR_BACKGROUND);
		numColumnsLabel.setOpaque(false);
		numColumnsSlider.setOpaque(false);
		menuBar.add(menuBarFile);
		menuBar.add(menuBarNumColumns);
		menuBar.setBackground(Core.UI_COLOR_BACKGROUND);
		menuBar.setOpaque(true);
		setJMenuBar(menuBar);

		contentPane.setLayout(gbl_contentPane);
		contentPane.add(entryGroupListPane, gbc_entryGroupListPane);
		contentPane.add(entryListPane, gbc_entryListPane);
		contentPane.add(scrollPaneFields, gbc_scrollPaneFields);
		contentPane.add(entrySearchField, gbc_entrySearchField);
		contentPane.add(reset, gbc_resetButton);
		contentPane.add(save, gbc_saveButton);
		contentPane.add(entryList.switchList, gbc_switchList);
		contentPane.add(addID, gbc_addID);
		contentPane.add(removeID, gbc_removeID);
		contentPane.setBackground(Core.UI_COLOR_BACKGROUND);
		scrollPaneFields.getVerticalScrollBar().setUI(new EEScrollBarUI());
		scrollPaneFields.getHorizontalScrollBar().setUI(new EEScrollBarUI());
		panelFields.setBackground(Core.UI_COLOR_BACKGROUND);

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
		
		entryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		entryList.addListSelectionListener(e -> {
			if (e == null || !e.getValueIsAdjusting()){
				Entry selected = entryList.getSelectedElement();
				if (selected != null){
					currentEntry = selected;
					panelFields.setVisible(false);
					if (currentEntry.datStructure.getExtraEntry() != null || entryPanels.size() <= 0){
						buildFields(currentEntry);
					}
					loadEntry(currentEntry);
					panelFields.setVisible(true);
				}
			}
		});
		entryList.addMouseListener(new PopupMenuListHandler(entryListMenu, entryList, ()->{
			return datContent.datStructure.getDefaultValues() != null;
		}));
		entryList.switchList.setHorizontalAlignment(SwingConstants.RIGHT);
		entryList.switchList.setHorizontalTextPosition(SwingConstants.LEFT);
		entryListPane.getVerticalScrollBar().setUI(new EEScrollBarUI());
		entryListPane.getHorizontalScrollBar().setUI(new EEScrollBarUI());

		entryListMenu.add(entryListMenuAdd);
		entryListMenu.add(entryListMenuRemove);
		entryListMenu.add(entryListMenuMoveTo);
		entryListMenuAdd.addActionListener(e -> {
			Integer maxID = null;
			try{
				maxID = datContent.getAllEntries().parallelStream().max((x,y) -> Integer.compare(x.ID, y.ID)).get().ID;
			} catch (NoSuchElementException e1){
				JOptionPane.showMessageDialog(this, "An error occurred while adding the new entry. No data has been altered", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Entry newEntry = new Entry(datContent.datStructure, maxID+1);
			currentEntryGroup.entries.add(newEntry);
			currentEntryGroup.map.put(newEntry.ID, newEntry);
			entryList.setList(currentEntryGroup.entries);
		});
		entryListMenuRemove.addActionListener(e -> {
			if (currentEntry != null){
				if (JOptionPane.showConfirmDialog(this, "You're going to delete " + currentEntry + "\nAre you sure?", "Delete entry", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, FrameMain.IMAGE_ICON) == 0) {
					currentEntryGroup.entries.remove(currentEntry);
					currentEntryGroup.map.remove(currentEntry.ID, currentEntry);
					entryList.setList(currentEntryGroup.entries);
				}
			}
		});
		entryListMenuMoveTo.addActionListener(e -> {
			if (currentEntryGroup != null && !entryList.isSelectionEmpty()) {
				new DialogMoveEntryToGroup(this, datContent.entryGroups, entryList, currentEntryGroup, entryList.getSelectedElement());
			}
		});

		fieldMenu.add(fieldMenuSearchValues);
		fieldMenu.add(fieldMenuSearchFields);
		fieldMenu.add(fieldMenuMarkUnusedFields);
		fieldMenu.add(fieldMenuUnmarkUnusedFields);
		fieldMenuSearchValues.addActionListener(e -> {
			AbstractEntryField field = (AbstractEntryField) rightClicked;
			EntryValueMap entryValueMap = EntryValueMap.getValuesMap(datContent.entryGroups, field.getIndex(), true);
			new DialogSearchValuesResults(this, entryValueMap, field);
		});
		fieldMenuSearchFields.addActionListener(e -> showSearchFieldResults());
		fieldMenuMarkUnusedFields.addActionListener(e -> markUnusedFields());
		fieldMenuUnmarkUnusedFields.addActionListener(e -> unmarkUnusedFields());
		fieldMenuUnmarkUnusedFields.setVisible(false);
		
		panelFields.setLayout(gridLayout);
		panelFields.setOpaque(false);
		reset.addActionListener(e -> {
			panelFields.setVisible(false);
			if (currentEntry.datStructure.getExtraEntry() != null || entryPanels.size() <= 0) {
				buildFields(currentEntry);
			}
			loadEntry(currentEntry);
			panelFields.setVisible(true);
		});
		save.addActionListener(e -> saveEntry());
		addID.addActionListener(e -> addField());
		removeID.addActionListener(e -> removeField());
	}
	




	

	


	
	public FrameEditor (DatContent datContent) {
		super("Empire Earth - " + (Core.AOC ? "Art of Conquest - " : "") + datContent.datFile.getName());
		setVisible(false);
		this.datContent = datContent;
		
		int nFields = datContent.datStructure.getEntries().length;
		if (nFields < GRID_MIN_ENTRY_SLOTS) {
			gridLayout.setRows(GRID_MIN_ENTRY_SLOTS/4);
		} else {
			gridLayout.setRows(0);
		}
		entryPanels = new ArrayList<>(datContent.datStructure.getIndexCountExtra() < 0 ? nFields : nFields+20);
		
		entryGroupList.setList(datContent.entryGroups);
		if (datContent.entryGroups.size() > 0){
			entryListMenuMoveTo.setVisible(datContent.entryGroups.size() > 1);
			entryGroupList.setSelectedIndex(0);
		}

		setAutoRequestFocus(true);
		addWindowListener(this);
		addWindowFocusListener(this);
	}

	@Override
	public void windowGainedFocus (WindowEvent e) {
		panelFields.revalidate();
		panelFields.repaint();
	}
	
	@Override
	public void windowClosing (WindowEvent e) {
		if (saved){
			switch (JOptionPane.showConfirmDialog(this, "Some entries have been altered. Do you want to save them to the file?", "Save to file?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)){
				case 0:
					Core.saveFile(this, datContent);
				case 1:
					close();
			}
		} else{
			close();
		}
	}
	
	public void close(){
		Core.FRAME_EDITORS.remove(datContent);
		setVisible(false);
		dispose();
	}
	
	@Override public void windowOpened (WindowEvent e) {}
	@Override public void windowClosed (WindowEvent e) {}
	@Override public void windowIconified (WindowEvent e) {}
	@Override public void windowDeiconified (WindowEvent e) {}
	@Override public void windowActivated (WindowEvent e) {}
	@Override public void windowDeactivated (WindowEvent e) {}
	@Override public void windowLostFocus (WindowEvent e) {}

	
	
	
	


	private void buildFields(Entry entries){
		int numBaseFields = datContent.datStructure.getEntries().length;
		int numPlacedFields = entryPanels.size();
		numSavedFields = entries.values.size();
		FieldStruct fieldStruct;

		panelFields.removeAll();
		for (int i = 0; i < numBaseFields; i++){
			if (i >= numPlacedFields){
				fieldStruct = datContent.datStructure.getEntries()[i];
				JPanelEntry entryPanel = new JPanelEntry(this, fieldStruct, i);
				entryPanels.add(entryPanel);
				Component component = (Component) entryPanel.field;
				component.addMouseListener(new PopupMenuFieldHandler(fieldMenu, e -> {
					rightClicked = component;
				}));
			}
			panelFields.add(entryPanels.get(i));
		}
		numPlacedFields = entryPanels.size();
		fieldStruct = datContent.datStructure.getExtraEntry();
		for (int i = numBaseFields; i < numSavedFields; i++){
			if (i >= numPlacedFields){
				JPanelEntry entryPanel = new JPanelEntry(this, fieldStruct, i);
				entryPanels.add(entryPanel);
				Component component = (Component) entryPanel.field;
				component.addMouseListener(new PopupMenuFieldHandler(fieldMenu, e -> {
					rightClicked = component;
				}));
			}
			panelFields.add(entryPanels.get(i));
		}
		for (int i = numPlacedFields; i > numSavedFields; i--){
			entryPanels.remove(i-1);
		}
		for (int i = entryPanels.size(); i < GRID_MIN_ENTRY_SLOTS; i++) {
			panelFields.add(new JPanel());
		}


		if (datContent.datStructure.getExtraEntry() != null){
			addID.setVisible(true);
			if (numSavedFields > numBaseFields){
				removeID.setVisible(true);
			}
		} else {
			addID.setVisible(false);
			removeID.setVisible(false);
		}
		if (panelFields.getComponentCount() < GRID_MIN_ENTRY_SLOTS) {
			gridLayout.setRows(GRID_MIN_ENTRY_SLOTS/4);
		} else {
			gridLayout.setRows(0);
		}
		panelFields.invalidate();

	}

	public void addField(){
		JPanelEntry entryPanel = new JPanelEntry(this, datContent.datStructure.getExtraEntry(), entryPanels.size());
		entryPanels.add(entryPanel);
		panelFields.add(entryPanel);
		removeID.setEnabled(true);
		entryPanel = entryPanels.get(datContent.datStructure.getIndexCountExtra());
		int value = (int) entryPanel.getVal();
		entryPanel.setVal(value+1);
		if (panelFields.getComponentCount() < GRID_MIN_ENTRY_SLOTS) {
			gridLayout.setRows(GRID_MIN_ENTRY_SLOTS/4);
		} else {
			gridLayout.setRows(0);
		}
		panelFields.invalidate();
	}

	public void removeField(){
		int index = entryPanels.size()-1;
		if (index >= datContent.datStructure.getEntries().length){
			JPanelEntry entryPanel = entryPanels.remove(index);
			panelFields.remove(index);
			index--;
			if (index == datContent.datStructure.getEntries().length-1){
				removeID.setEnabled(false);
			}
			entryPanel = entryPanels.get(datContent.datStructure.getIndexCountExtra());
			int value = (int) entryPanel.getVal();
			entryPanel.setVal(value-1);
			if (panelFields.getComponentCount() < GRID_MIN_ENTRY_SLOTS) {
				gridLayout.setRows(GRID_MIN_ENTRY_SLOTS/4);
			} else {
				gridLayout.setRows(0);
			}
			panelFields.invalidate();
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
		int numBaseFields = datContent.datStructure.getEntries().length;
		DatStructure datStructure = datContent.datStructure;
		List<Object> values = currentEntry.values;
		for (int i = 0; i < numBaseFields; i++){
			if (datStructure.getEntries()[i].isEditable()){
				values.set(i, entryPanels.get(i).getVal());
			}
		}
		if (datStructure.getIndexID() >= 0){
			int oldID = currentEntry.ID;
			currentEntry.ID = (int) values.get(datStructure.getIndexID());
			if (oldID != currentEntry.ID){
				currentEntryGroup.map.remove(oldID, currentEntry);
				currentEntryGroup.map.put(currentEntry.ID, currentEntry);
			}
		}
		if (datStructure.getIndexSequence() >= 0){
			currentEntry.sequenceNumber = (int) values.get(datStructure.getIndexSequence());
		}
		
		if (datContent.datStructure.getExtraEntry() != null){
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
			values.set(datContent.datStructure.getIndexCountExtra(), entryPanels.get(datContent.datStructure.getIndexCountExtra()).getVal());
		}
		saved = true;
		entryList.refresh();
		System.out.println("Save entry: " + currentEntry);
	}

	public void goToEntry(EntryGroup entryGroup, Entry entry){
		System.out.println("Go to: " + datContent.datFile.getName() + " > " + entryGroup + " > " + entry);
		if (!isVisible()){
			setVisible(true);
		}
		entryGroupList.setSelectedElement(entryGroup);
		if (entryGroupList.getLength() > 0){
			entryList.setSelectedElement(entry);
		}
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

		for (EntryGroup entryGroup : datContent){
			for (Entry entry : entryGroup){
				if (index < entry.values.size()){
					entryValue = entry.values.get(index);
					if (value.equals(entryValue)){
						entries.add(entry);
						if (entry.isDefined()){ //if (index != datFile.datStructure.indexName && !entry.toString().equals("<Undefined>")){
							entriesClean.add(entry);
						}
					}
				}
			}
		}
		new DialogSearchFieldResults(this, entries, entriesClean, field);
	}






	/**
	 * Marks all fields which are either unused/unchanged (0/same value everywhere) or have up to 2 values (including flags/boolean).
	 * This is very useful to identify many unknown fields.
	 */
	public void markUnusedFields(){
		EntryValueMap entryValueMap;
		FieldStruct fieldStruct;
		int size;
		for (JPanelEntry entryPanel : entryPanels){
			fieldStruct = entryPanel.fieldStruct;
			if (fieldStruct.getKnowledge() != Knowledge.KNOWN) {
				entryValueMap = EntryValueMap.getValuesMap(datContent.entryGroups, entryPanel.index, true);
				size = entryValueMap.mapClean.size();
				if (size <= 2 || size == entryValueMap.counter) {
					marked.add(entryPanel);
					entryPanel.label.setBackground(Color.BLACK);
					entryPanel.label.setOpaque(true);
					if (size == 1){
						if (fieldStruct.getKnowledge() != Knowledge.NEVER_CHANGE && fieldStruct.getKnowledge() != Knowledge.NEVER_USED) {
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

	/**
	 * Remove all marks from the fields.
	 */
	public void unmarkUnusedFields(){
		Iterator<JPanelEntry> it = marked.iterator();
		JPanelEntry entryPanel;
		while(it.hasNext()){
			entryPanel = it.next();
			entryPanel.label.setOpaque(false);
			entryPanel.label.setBackground(null);
			entryPanel.label.setForeground(entryPanel.fieldStruct.getColor());
			entryPanel.field.resetColor();
			it.remove();
		}
		fieldMenuMarkUnusedFields.setVisible(true);
		fieldMenuUnmarkUnusedFields.setVisible(false);
	}


}
