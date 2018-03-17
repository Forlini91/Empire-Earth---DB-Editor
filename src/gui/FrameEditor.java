package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JDialog;
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

import datmanager.Core;
import datmanager.DatFile;
import datmanager.DatFile.EntryLocation;
import datmanager.Settings;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.EntryValueMap;
import datstructure.FieldStruct;
import datstructure.Knowledge;
import datstructure.Link;
import datstructure.structures.Events;
import datstructure.structures.Family;
import datstructure.structures.Graphics;
import datstructure.structures.Objects;
import datstructure.structures.TechTree;
import datstructure.structures.UnitSet;
import datstructure.structures.Upgrade;
import gui.components.EntryFieldInterface;
import gui.components.JButtonRed;
import gui.components.JComboBoxField;
import gui.components.JListDouble;
import gui.components.JListEntry;
import gui.components.JPanelEntry;
import gui.components.JScrollPaneRed;
import gui.components.JSearchFieldEntry;
import gui.components.JTextFieldField;
import gui.ui.EEScrollBarUI;
import gui.ui.EESliderUI;
import gui.ui.GridBagConstraintsExtended;
import gui.ui.GridBagLayoutExtended;
import gui.ui.GridLayoutExtended;


/**
 * The main editor windows, with all entries and their fields.
 *
 * @author MarcoForlini
 */
public class FrameEditor extends JFrame implements WindowListener, WindowFocusListener {


	private static final long			serialVersionUID		= -3426470254615698936L;

	private final GridBagLayoutExtended	gbl_contentPane			= new GridBagLayoutExtended (new int[] { 0, 0, 100, 200, 200, 100 }, new int[] { 400, 32, 32 }, new double[] { 0, 0, 0.5, 0, 0, 0.5 }, new double[] { 1.0, 0.0, 0 });
	private final GridLayout			gridLayout				= new GridLayoutExtended (false, false, 0, 4, 0, 0);
	private final GridBagConstraints	gbc_entryGroupListPane	= new GridBagConstraintsExtended (4, 4, 0, 0, 0, 0);
	private final GridBagConstraints	gbc_entryListPane		= new GridBagConstraintsExtended (4, 4, 0, 0, 1, 0);
	private final GridBagConstraints	gbc_entrySearchField	= new GridBagConstraintsExtended (4, 4, 0, 0, 1, 1);
	private final GridBagConstraints	gbc_scrollPaneFields	= new GridBagConstraintsExtended (4, 4, 0, 4, 2, 0, 4, 1);
	private final GridBagConstraints	gbc_entryDescription	= new GridBagConstraintsExtended (4, 4, 0, 4, 2, 1, 4, 1);
	private final GridBagConstraints	gbc_switchList			= new GridBagConstraintsExtended (4, 4, 4, 0, 0, 1);
	private final GridBagConstraints	gbc_resetButton			= new GridBagConstraintsExtended (4, 4, 4, 0, 0, 2);
	private final GridBagConstraints	gbc_saveButton			= new GridBagConstraintsExtended (4, 4, 4, 0, 1, 2);
	private final GridBagConstraints	gbc_removeID			= new GridBagConstraintsExtended (4, 4, 4, 0, 3, 2);
	private final GridBagConstraints	gbc_addID				= new GridBagConstraintsExtended (4, 4, 4, 4, 4, 2);
	private static final int			GRID_MIN_ENTRY_SLOTS	= 32;

	private final DatFile				techFile				= TechTree.instance.datFile;
	private final DatFile				familyFile				= Family.instance.datFile;
	private final DatFile				upgradeFile				= Upgrade.instance.datFile;
	private final DatFile				graphicFile				= Graphics.instance.datFile;
	private final boolean				isDbObject, isDbUnitSet, isDbTechTree, isDbEvents;


	/** The data loaded */
	public DatFile				datFile;

	/** Base fields */
	public List <JPanelEntry>	baseFields;

	/** Extra fields (dbtechtree.dat and dbevents.dat) */
	public List <JPanelEntry>	extraFields;

	/** Selected entry group */
	public EntryGroup			currentEntryGroup;

	/** Selected entry */
	public Entry				currentEntry	= null;

	/** Copied entry */
	public Entry				copyEntry		= null;



	private Component					rightClicked		= null;
	private Set <JPanelEntry>			marked				= new HashSet <> (80);

	private JPanel						contentPane			= new JPanel ();
	private JListDouble <EntryGroup>	entryGroupList		= new JListDouble <> (false);
	private JListEntry					entryList			= new JListEntry ();
	private JScrollPane					entryGroupListPane	= new JScrollPaneRed (entryGroupList, "Epochs");
	private JScrollPane					entryListPane		= new JScrollPaneRed (entryList, "Entries");
	private JPanel						panelFields			= new JPanel ();
	private JScrollPane					scrollPaneFields	= new JScrollPaneRed (panelFields, "Fields");
	private JSearchFieldEntry			entrySearchField	= new JSearchFieldEntry (entryList);
	private JLabel						entryDescription	= new JLabel ("");



	private JButton	menuBarSaveFile			= new JButtonRed ("Save to file");
	private JMenu	menuBarNumColumns		= new JMenu ("Num columns");
	private JPanel	menuBarNumColumnsPanel	= new JPanel ();
	private JLabel	numColumnsLabel			= new JLabel ("Columns: 4");
	private JSlider	numColumnsSlider		= new JSlider ();
	private JButton	menuBarList				= new JButtonRed ("List entries");
	private JButton	menuBarAdvancedSearch	= new JButtonRed ("Advanced search");
	private JButton	reset					= new JButtonRed ("Reset entry");
	private JButton	save					= new JButtonRed ("Save entry");
	private JButton	addField				= new JButtonRed ("Add field");
	private JButton	removeField				= new JButtonRed ("Remove field");


	private final JPopupMenu	fieldMenu					= new JPopupMenu ();
	private final JMenuItem		fieldMenuSearchValues		= new JMenuItem ("Show all values used for this field");
	private final JMenuItem		fieldMenuSearchFields		= new JMenuItem ("Show all fields with the same value");
	private final JMenuItem		fieldMenuMarkUnusedFields	= new JMenuItem ("Mark all unused/interesting fields");
	private final JMenuItem		fieldMenuUnmarkUnusedFields	= new JMenuItem ("Remove marks");
	private final JMenuItem		fieldMenuRefreshList		= new JMenuItem ("Refresh list");
	private final JMenuItem		fieldMenuOpenLink			= new JMenuItem ("Open link");
	private final JMenuItem		fieldMenuNextFree			= new JMenuItem ("Find next free ID/number");

	private final JPopupMenu	entryListMenu				= new JPopupMenu ();
	private final JMenuItem		entryListMenuAdd			= new JMenuItem ("Add entry");
	private final JMenuItem		entryListMenuRemove			= new JMenuItem ("Remove entry");
	private final JMenuItem		entryListMenuDuplicate		= new JMenuItem ("Duplicate entry");
	private final JMenuItem		entryListMenuCopyData		= new JMenuItem ("Copy entry fields");
	private final JMenuItem		entryListMenuPasteData		= new JMenuItem ("Paste entry fields");
	private final JMenuItem		entryListMenuMoveTo			= new JMenuItem ("Move to epoch...");
	private final JMenuItem		entryListMenuShowLinks		= new JMenuItem ("Show all links to this entry");
	private final JMenuItem		entryListMenuGoToFamily		= new JMenuItem ("Go to Family");
	private final JMenuItem		entryListMenuGoToGraphic	= new JMenuItem ("Go to Graphic");
	private final JMenuItem		entryListMenuGoToUpgrade	= new JMenuItem ("Go to Upgrade");
	private final JMenuItem		entryListMenuGoToTech		= new JMenuItem ("Go to Technology");
	private final JMenuItem		entryListMenuGoToParentSet	= new JMenuItem ("Go to parent set");

	private final JMenuBar		menuBar						= new JMenuBar ();
	private int					numBaseFields				= 0;
	private int					numPlacedExtraFields		= 0;
	private int					indexCountExtra				= -1;
	private JPanelEntry			panelCountExtra				= null;
	private FieldStruct			extraFieldStructure			= null;





	// Initializations independent from constructor arguments
	{
		setBounds (GUI.getBounds (this, 0.85, 0.85));
		setIconImage (GUI.IMAGE_ICON.getImage ());
		setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
		setContentPane (contentPane);

		menuBarSaveFile.addActionListener (e -> {
			datFile.saveFile (FrameEditor.this);
		});
		menuBarSaveFile.setMnemonic (KeyEvent.VK_Q);
		menuBarSaveFile.setToolTipText ("(ALT + Q) Save this file");

		menuBarList.addActionListener (e -> {
			JDialog d = new DialogListEntries (this, entryList.list, entryList.listClean);
			d.setVisible (true);
		});

		menuBarAdvancedSearch.addActionListener (e -> {
			JDialog d = new DialogConditionAssembler (this, datFile);
			d.setVisible (true);
		});

		menuBarNumColumnsPanel.setLayout (new GridLayout (2, 1, 0, 0));
		menuBarNumColumnsPanel.add (numColumnsLabel);
		menuBarNumColumnsPanel.add (numColumnsSlider);
		menuBarNumColumns.add (menuBarNumColumnsPanel);
		numColumnsLabel.setHorizontalAlignment (SwingConstants.LEFT);
		numColumnsSlider.setUI (new EESliderUI (numColumnsSlider, GUI.COLOR_UI_ELEMENT));
		numColumnsSlider.setMinimum (2);
		numColumnsSlider.setMaximum (32);
		numColumnsSlider.setValue (4);
		numColumnsSlider.setSnapToTicks (true);
		numColumnsSlider.setMinorTickSpacing (1);
		numColumnsSlider.setMajorTickSpacing (2);
		numColumnsSlider.setPreferredSize (new Dimension (250, numColumnsSlider.getPreferredSize ().height));
		numColumnsSlider.addChangeListener (e -> {
			int value = numColumnsSlider.getValue ();
			gridLayout.setColumns (value);
			numColumnsLabel.setText ("Columns: " + value);
			panelFields.revalidate ();
		});
		menuBarNumColumns.setBackground (GUI.COLOR_UI_ELEMENT);
		menuBarNumColumns.setForeground (Color.WHITE);
		menuBarNumColumns.setOpaque (true);
		menuBarNumColumnsPanel.setBackground (GUI.COLOR_UI_BACKGROUND);
		numColumnsLabel.setOpaque (false);
		numColumnsSlider.setOpaque (false);
		menuBar.add (menuBarSaveFile);
		menuBar.add (menuBarNumColumns);
		menuBar.add (menuBarList);
		menuBar.add (menuBarAdvancedSearch);
		menuBar.setBackground (GUI.COLOR_UI_BACKGROUND);
		menuBar.setOpaque (true);
		setJMenuBar (menuBar);

		contentPane.setLayout (gbl_contentPane);
		contentPane.add (entryGroupListPane, gbc_entryGroupListPane);
		contentPane.add (scrollPaneFields, gbc_scrollPaneFields);
		contentPane.add (entryDescription, gbc_entryDescription);
		contentPane.add (entrySearchField, gbc_entrySearchField);
		contentPane.add (reset, gbc_resetButton);
		contentPane.add (save, gbc_saveButton);
		contentPane.add (entryList.switchList, gbc_switchList);
		contentPane.add (addField, gbc_addID);
		contentPane.add (removeField, gbc_removeID);
		contentPane.setBackground (GUI.COLOR_UI_BACKGROUND);
		scrollPaneFields.getVerticalScrollBar ().setUI (new EEScrollBarUI ());
		scrollPaneFields.getHorizontalScrollBar ().setUI (new EEScrollBarUI ());
		panelFields.setBackground (GUI.COLOR_UI_BACKGROUND);
		entryDescription.setHorizontalAlignment (SwingConstants.CENTER);
		entryDescription.setVerticalAlignment (SwingConstants.CENTER);
		Font font = entryDescription.getFont ();
		font = new Font (font.getName (), font.getStyle (), font.getSize () - 2);
		entryDescription.setFont (font);

		entryGroupList.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
		entryGroupList.addListSelectionListener (e -> {
			if (e == null || !e.getValueIsAdjusting ()) {
				EntryGroup selected = entryGroupList.getSelectedElement ();
				if (selected != null) {
					currentEntryGroup = selected;
					entryList.setList (currentEntryGroup.entries);
					if (currentEntryGroup.entries.size () > 0) {
						entryList.setSelectedIndex (0);
					}
				}
			}
		});
		entryGroupListPane.getVerticalScrollBar ().setUI (new EEScrollBarUI ());
		entryGroupListPane.getHorizontalScrollBar ().setUI (new EEScrollBarUI ());

		entryList.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		entryList.addListSelectionListener (e -> {
			if (e == null || !e.getValueIsAdjusting ()) {
				Entry selected = entryList.getSelectedElement ();
				if (selected != null) {
					currentEntry = selected;
					loadEntry (currentEntry);
				}
			}
		});
		entryList.addMouseListener (new MouseAdapter () {
			@Override
			@SuppressWarnings ("synthetic-access")
			public void mousePressed (MouseEvent e) {
				entryList.selectElement (e);
				showMenu (e);
			}

			@Override
			public void mouseReleased (MouseEvent e) {
				showMenu (e);
			}

			@SuppressWarnings ("synthetic-access")
			public void showMenu (MouseEvent e) {
				if (e.isPopupTrigger ()) {
					entryListMenuGoToFamily.setEnabled (entryListMenuGoToFamily.isVisible () && isDbObject && familyFile != null && ((Link) currentEntry.get (2)).target.isValidLinkTarget ());
					entryListMenuGoToGraphic.setEnabled (entryListMenuGoToGraphic.isVisible () && isDbObject && graphicFile != null && ((Link) currentEntry.get (39)).target.isValidLinkTarget ());
					entryListMenuGoToUpgrade.setEnabled (entryListMenuGoToUpgrade.isVisible () && isDbObject && upgradeFile != null && ((Link) currentEntry.get (121)).target.isValidLinkTarget ());
					entryListMenuGoToTech.setEnabled (entryListMenuGoToTech.isVisible () && isDbObject && techFile != null && ((Link) currentEntry.get (225)).target.isValidLinkTarget ());
					entryListMenuGoToParentSet.setEnabled (entryListMenuGoToParentSet.isVisible () && isDbUnitSet && getParentEntry (datFile, currentEntry, 19) != EntryLocation.NULL);
					entryListMenu.show (e.getComponent (), e.getX (), e.getY ());
				}
			}
		});
		entryList.switchList.setHorizontalAlignment (SwingConstants.RIGHT);
		entryList.switchList.setHorizontalTextPosition (SwingConstants.LEFT);
		entryListPane.getVerticalScrollBar ().setUI (new EEScrollBarUI ());
		entryListPane.getHorizontalScrollBar ().setUI (new EEScrollBarUI ());

		entryListMenu.add (entryListMenuAdd);
		entryListMenu.add (entryListMenuRemove);
		entryListMenu.add (entryListMenuDuplicate);
		entryListMenu.add (entryListMenuCopyData);
		entryListMenu.add (entryListMenuPasteData);
		entryListMenu.add (entryListMenuMoveTo);
		entryListMenu.add (entryListMenuShowLinks);
		entryListMenu.add (entryListMenuGoToFamily);
		entryListMenu.add (entryListMenuGoToGraphic);
		entryListMenu.add (entryListMenuGoToUpgrade);
		entryListMenu.add (entryListMenuGoToTech);
		entryListMenu.add (entryListMenuGoToParentSet);
		entryListMenuPasteData.setVisible (false);
		entryListMenuAdd.addActionListener (e -> {
			try {
				int newSeq = datFile.getAllEntries (true).parallelStream ().mapToInt (Entry::getSequenceNumber).max ().getAsInt () + 1;
				int newID = currentEntryGroup.entries.parallelStream ().mapToInt (Entry::getID).max ().getAsInt () + 1;
				Entry newEntry = new Entry (datFile.datStructure, false, null, newSeq, newID);
				currentEntryGroup.entries.add (newEntry);
				currentEntryGroup.map.put (newID, newEntry);
				entryList.setList (currentEntryGroup.entries);
				entryList.setSelectedElement (newEntry);
			} catch (NoSuchElementException e1) {
				Core.printException (this, e1, "An error occurred while adding the new entry. No data has been altered", "Error", true);
				return;
			}
		});
		entryListMenuRemove.addActionListener (e -> {
			if (currentEntry != null) {
				if (JOptionPane.showConfirmDialog (this, "You're going to delete " + currentEntry + "\nAre you sure?", "Delete entry", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, GUI.IMAGE_ICON) == 0) {
					int index = entryList.getSelectedIndex ();
					currentEntryGroup.entries.remove (currentEntry);
					currentEntryGroup.map.remove (currentEntry.getID (), currentEntry);
					entryList.setList (currentEntryGroup.entries);
					int size = entryList.getLength ();
					if (size == 0) {
						currentEntry = null;
					} else if (index < size) {
						entryList.setSelectedIndex (index);
					} else {
						entryList.setSelectedIndex (size - 1);
					}
				}
			}
		});
		entryListMenuDuplicate.addActionListener (e -> {
			if (currentEntry != null) {
				try {
					int newSeq = datFile.getAllEntries (false).parallelStream ().mapToInt (Entry::getSequenceNumber).max ().getAsInt () + 1;
					int newID = currentEntryGroup.entries.parallelStream ().mapToInt (Entry::getID).max ().getAsInt () + 1;
					Entry newEntry = currentEntry.duplicate (newSeq, newID);
					currentEntryGroup.entries.add (newEntry);
					currentEntryGroup.map.put (newID, newEntry);
					entryList.setList (currentEntryGroup.entries);
					entryList.setSelectedElement (newEntry);
				} catch (NoSuchElementException e1) {
					Core.printException (this, e1, "An error occurred while adding the new entry. No data has been altered", "Error", true);
				}
			}
		});
		entryListMenuCopyData.addActionListener (e -> {
			if (currentEntry != null) {
				copyEntry = currentEntry;
				entryListMenuPasteData.setVisible (true);
			}
		});
		entryListMenuPasteData.addActionListener (e -> {
			if (currentEntry != null && copyEntry != null) {
				pasteEntry (copyEntry);
			}
		});
		entryListMenuMoveTo.addActionListener (e -> {
			if (currentEntryGroup != null && !entryList.isSelectionEmpty ()) {
				JDialog d = new DialogMoveEntryToGroup (this, datFile.entryGroups, entryGroupList, entryList, currentEntryGroup, entryList.getSelectedElement (), () -> datFile.setUnsaved (true));
				d.setVisible (true);
			}
		});
		entryListMenuShowLinks.addActionListener (e -> {
			if (currentEntry != null) {
				List <Link> linksToEntry = currentEntry.getLinksToEntry (false);
				JDialog d = new DialogSearchLinkResult (this, currentEntry, Link.getInverseLinks (linksToEntry, true));
				d.setVisible (true);
			}
		});
		entryListMenuGoToFamily.addActionListener (e -> {
			if (familyFile != null) {
				Link link = currentEntry.get (2);
				Entry entry = link.target;
				if (!entry.dummyEntry) {
					EntryGroup group = familyFile.findGroup (entry);
					if (group != null) {
						FrameEditor frameEditor = familyFile.openInEditor (this, (e.getModifiers () & ActionEvent.SHIFT_MASK) != 0);
						frameEditor.goToEntry (group, entry);
					}
				}
			}
		});
		entryListMenuGoToGraphic.addActionListener (e -> {
			if (graphicFile != null) {
				Link link = currentEntry.get (39);
				Entry entry = link.target;
				if (!entry.dummyEntry) {
					EntryGroup group = graphicFile.findGroup (entry);
					if (group != null) {
						FrameEditor frameEditor = graphicFile.openInEditor (this, (e.getModifiers () & ActionEvent.SHIFT_MASK) != 0);
						frameEditor.goToEntry (group, entry);
					}
				}
			}
		});
		entryListMenuGoToUpgrade.addActionListener (e -> {
			if (upgradeFile != null) {
				Link link = currentEntry.get (121);
				Entry entry = link.target;
				if (!entry.dummyEntry) {
					EntryGroup group = upgradeFile.findGroup (entry);
					if (group != null) {
						FrameEditor frameEditor = upgradeFile.openInEditor (this, (e.getModifiers () & ActionEvent.SHIFT_MASK) != 0);
						frameEditor.goToEntry (group, entry);
					}
				}
			}
		});
		entryListMenuGoToTech.addActionListener (e -> {
			if (techFile != null) {
				Link link = currentEntry.get (225);
				Entry entry = link.target;
				if (!entry.dummyEntry) {
					EntryGroup group = techFile.findGroup (entry);
					if (group != null) {
						FrameEditor frameEditor = techFile.openInEditor (this, (e.getModifiers () & ActionEvent.SHIFT_MASK) != 0);
						frameEditor.goToEntry (group, entry);
					}
				}
			}
		});
		entryListMenuGoToParentSet.addActionListener (e -> {
			EntryLocation location = getParentEntry (datFile, currentEntry, 19);
			if (location != EntryLocation.NULL) {
				FrameEditor frameEditor = datFile.openInEditor (this, (e.getModifiers () & ActionEvent.SHIFT_MASK) != 0);
				frameEditor.goToEntry (location.entryGroup, location.entry);
			}
		});


		fieldMenu.add (fieldMenuSearchValues);
		fieldMenu.add (fieldMenuSearchFields);
		fieldMenu.add (fieldMenuMarkUnusedFields);
		fieldMenu.add (fieldMenuUnmarkUnusedFields);
		fieldMenu.add (fieldMenuRefreshList);
		fieldMenu.add (fieldMenuOpenLink);
		fieldMenu.add (fieldMenuNextFree);
		fieldMenuSearchValues.addActionListener (e -> {
			try {
				EntryFieldInterface field = (EntryFieldInterface) rightClicked;
				EntryValueMap entryValueMap = EntryValueMap.getValuesMap (datFile.entryGroups, true, field.getIndex ());
				JDialog d = new DialogSearchValuesResults (this, entryValueMap, field);
				d.setVisible (true);
			} catch (Exception exc) {
				Core.printException (this, exc, "An error occurred while searching the values", "Error", true);
			}
		});
		fieldMenuSearchFields.addActionListener (e -> showSearchFieldResults ());
		fieldMenuMarkUnusedFields.addActionListener (e -> markUnusedFields ());
		fieldMenuUnmarkUnusedFields.addActionListener (e -> unmarkUnusedFields ());
		fieldMenuUnmarkUnusedFields.setVisible (false);
		fieldMenuRefreshList.addActionListener (e -> ((EntryFieldInterface) rightClicked).refreshField ());
		fieldMenuOpenLink.addActionListener (e -> {
			JComboBoxField field = (JComboBoxField) rightClicked;
			Object selectedItem = field.getSelectedItem ();
			if (selectedItem != null && selectedItem instanceof Entry) {
				Entry selectedEntry = (Entry) selectedItem;
				if (selectedEntry.isValidLinkTarget ()) {
					DatFile datFile = field.linkToStruct.datFile;
					if (datFile != null) {
						EntryGroup entryGroup = datFile.findGroup (selectedEntry);
						if (entryGroup != null) {
							FrameEditor frameEditor = datFile.openInEditor (this, (e.getModifiers () & KeyEvent.VK_SHIFT) != 0);
							frameEditor.goToEntry (entryGroup, selectedEntry);
						}
					}
				}
			}
		});
		fieldMenuNextFree.addActionListener (e -> {
			EntryFieldInterface field = (EntryFieldInterface) rightClicked;
			FieldStruct fieldStruct = field.getEntryStruct ();
			int highest;
			if (fieldStruct == FieldStruct.ID) {
				highest = currentEntryGroup.entries.parallelStream ().mapToInt (Entry::getID).max ().getAsInt () + 1;
			} else if (fieldStruct == FieldStruct.SEQ_NUMBER) {
				highest = datFile.getAllEntries (false).parallelStream ().mapToInt (Entry::getSequenceNumber).max ().getAsInt () + 1;
			} else {
				Core.printException (this, new IllegalStateException ("This is not an ID or Sequence Number field"), "An error occurred while checking the max ID/Number", "Error", true);
				return;
			}
			field.setVal (highest);
		});

		panelFields.setLayout (gridLayout);
		panelFields.setOpaque (false);
		reset.addActionListener (e -> {
			loadEntry (currentEntry);
		});
		reset.setMnemonic (KeyEvent.VK_R);
		save.setToolTipText ("(ALT + R) Reload entry from list");
		save.addActionListener (e -> saveEntry ());
		save.setMnemonic (KeyEvent.VK_S);
		save.setToolTipText ("(ALT + S) Save entry to list");
		addField.addActionListener (e -> addField ());
		removeField.addActionListener (e -> removeField ());
	}





	/**
	 * Create a new FrameEditor
	 *
	 * @param datFile The data loaded
	 */
	public FrameEditor (DatFile datFile) {
		super ("Empire Earth - " + (Core.isAOC () ? "Art of Conquest - " : "") + datFile.getName ());
		setVisible (false);

		this.datFile = datFile;
		int nFields = datFile.datStructure.fieldStructs.length;
		baseFields = new ArrayList <> (nFields);
		extraFields = new ArrayList <> (20);
		indexCountExtra = datFile.datStructure.indexExtraFields ();
		buildBaseFields (datFile.datStructure);
		FieldStruct extraEntry = datFile.datStructure.extraField;
		if (extraEntry != null) {
			panelCountExtra = baseFields.get (indexCountExtra);
			extraFieldStructure = extraEntry;
		}
		numColumnsSlider.setValue (datFile.datStructure.defaultColumns);

		int x1 = datFile.datStructure.guiGroupsListSize;
		int x2 = datFile.datStructure.guiEntriesListSize;
		gbl_contentPane.columnWidths[0] = x1;
		gbl_contentPane.columnWidths[1] = x2;
		if (datFile.entryGroups.size () <= 1) {
			entryGroupListPane.setVisible (false);
			gbc_entryListPane.gridx = 0;
			gbc_entryListPane.gridwidth = 2;
			entryListPane.setPreferredSize (new Dimension (x1 + x2, entryListPane.getPreferredSize ().height));
		}
		contentPane.add (entryListPane, gbc_entryListPane);

		entryGroupList.setList (datFile.entryGroups);
		boolean allowNewEntry = datFile.datStructure.newEntryValues != null;
		entryListMenuAdd.setVisible (allowNewEntry);
		entryListMenuRemove.setVisible (allowNewEntry);
		entryListMenuDuplicate.setVisible (allowNewEntry);
		if (datFile.entryGroups.size () > 0) {
			entryListMenuMoveTo.setVisible (allowNewEntry && datFile.entryGroups.size () > 1);
			entryGroupList.setSelectedIndex (0);
		}
		isDbObject = datFile.datStructure == Objects.instance;
		isDbUnitSet = datFile.datStructure == UnitSet.instance;
		isDbTechTree = datFile.datStructure == TechTree.instance;
		isDbEvents = datFile.datStructure == Events.instance;
		entryListMenuGoToTech.setVisible (isDbObject);
		entryListMenuGoToFamily.setVisible (isDbObject);
		entryListMenuGoToGraphic.setVisible (isDbObject);
		entryListMenuGoToUpgrade.setVisible (isDbObject);
		entryListMenuGoToParentSet.setVisible (isDbUnitSet);

		setAutoRequestFocus (true);
		addWindowListener (this);
		addWindowFocusListener (this);
	}





	@Override
	public void windowGainedFocus (WindowEvent e) {
		panelFields.revalidate ();
		panelFields.repaint ();
	}

	@Override
	public void windowClosing (WindowEvent e) {
		if (datFile.isUnsaved () && datFile.frameEditors.size () <= 1) {
			switch (JOptionPane.showConfirmDialog (this, "Some entries have been altered. Do you want to save them to the file?\nChanges won't be lost anyway until you close the program, so you can open this window again and save later", "Save to file?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, GUI.IMAGE_ICON)) {
				case 0:
					datFile.saveFile (this);
				case 1:
					datFile.frameEditors.remove (this);
					dispose ();
			}
		} else {
			datFile.frameEditors.remove (this);
			dispose ();
		}
	}

	@Override
	public void windowOpened (WindowEvent e) {/* Do nothing */}

	@Override
	public void windowClosed (WindowEvent e) {/* Do nothing */}

	@Override
	public void windowIconified (WindowEvent e) {/* Do nothing */}

	@Override
	public void windowDeiconified (WindowEvent e) {/* Do nothing */}

	@Override
	public void windowActivated (WindowEvent e) {/* Do nothing */}

	@Override
	public void windowDeactivated (WindowEvent e) {/* Do nothing */}

	@Override
	public void windowLostFocus (WindowEvent e) {/* Do nothing */}





	private Entry getCurrentEntry () {
		return currentEntry;
	}

	private void updateGrid (boolean revalidate) {
		if (panelFields.getComponentCount () < GRID_MIN_ENTRY_SLOTS) {
			gridLayout.setRows (GRID_MIN_ENTRY_SLOTS / 4);
		} else {
			gridLayout.setRows (0);
		}
		if (revalidate) {
			scrollPaneFields.revalidate ();
			panelFields.revalidate ();
			panelFields.repaint ();
		}
	}

	private JPanelEntry createPanelEntry (FieldStruct fieldStruct, int i) {
		JPanelEntry panelEntry = new JPanelEntry (this, fieldStruct, i, this::getCurrentEntry);
		panelEntry.field.setPopupMenu (new PopupMenuHandler (fieldMenu,
				e -> true,
				e -> {
					rightClicked = (Component) panelEntry.field;
					fieldMenuRefreshList.setVisible (fieldStruct.linkToStruct != null);
					fieldMenuOpenLink.setVisible (fieldStruct.linkToStruct != null);
					fieldMenuNextFree.setVisible (fieldStruct == FieldStruct.ID || fieldStruct == FieldStruct.SEQ_NUMBER);
				}));
		return panelEntry;
	}

	private void buildBaseFields (DatStructure datStructure) {
		FieldStruct[] fieldStructs = datStructure.fieldStructs;
		int numBaseFields = fieldStructs.length;
		JPanelEntry panelEntry = null;
		for (int i = 0; i < numBaseFields; i++) {
			panelEntry = createPanelEntry (fieldStructs[i], i);
			baseFields.add (panelEntry);
			panelFields.add (panelEntry);
		}
	}

	private void buildExtraFields (Entry entry, int numExtraFields) {
		numBaseFields = entry.datStructure.fieldStructs.length;
		int nExtraFields;
		if (numExtraFields < 0 && indexCountExtra >= 0) {
			nExtraFields = entry.get (indexCountExtra);
		} else {
			nExtraFields = numExtraFields;
		}

		if (nExtraFields != numPlacedExtraFields) {
			panelFields.setVisible (false);
			if (nExtraFields < numPlacedExtraFields) {
				for (int i = numPlacedExtraFields; i > nExtraFields; i--) {
					panelFields.remove (numBaseFields + i - 1);
					numPlacedExtraFields--;
				}
			} else {
				JPanelEntry panelEntry;
				for (int i = numPlacedExtraFields; i < nExtraFields; i++) {
					if (i < extraFields.size ()) {
						panelEntry = extraFields.get (i);
					} else {
						panelEntry = createPanelEntry (extraFieldStructure, numBaseFields + i);
						extraFields.add (panelEntry);
					}
					panelFields.add (panelEntry);
					numPlacedExtraFields++;
				}
			}
		}

		if (indexCountExtra >= 0) {
			addField.setVisible (true);
			removeField.setVisible (true);
			removeField.setEnabled (nExtraFields > 0);
		} else {
			addField.setVisible (false);
			removeField.setVisible (false);
		}
		updateGrid (false);
		panelFields.setVisible (true);
	}



	/**
	 * Add a new extra field to the entry
	 */
	public void addField () {
		JPanelEntry panelEntry;
		if (numPlacedExtraFields < extraFields.size ()) {
			panelEntry = extraFields.get (numPlacedExtraFields);
		} else {
			panelEntry = createPanelEntry (extraFieldStructure, numBaseFields + numPlacedExtraFields);
			extraFields.add (panelEntry);
		}
		numPlacedExtraFields++;
		panelFields.add (panelEntry);
		removeField.setEnabled (true);
		panelCountExtra.setVal ((int) panelCountExtra.getVal () + 1);
		updateGrid (true);
	}

	/**
	 * Remove the last extra field from the entry
	 */
	public void removeField () {
		if (numPlacedExtraFields > 0) {
			numPlacedExtraFields--;
			int index = numBaseFields + numPlacedExtraFields;
			panelFields.remove (index);
			removeField.setEnabled (numPlacedExtraFields > 0);
			panelCountExtra.setVal ((int) panelCountExtra.getVal () - 1);
			updateGrid (true);
		} else {
			removeField.setEnabled (false);
		}
	}



	/**
	 * Load the given entry
	 *
	 * @param entry The entry to load
	 */
	public void loadEntry (Entry entry) {
		if (Settings.DEBUG) {
			System.out.println ("Load entry: " + entry);
		}
		if (entry.datStructure.extraField != null) {
			buildExtraFields (entry, entry.get (entry.datStructure.indexExtraFields ()));
		} else {
			buildExtraFields (entry, 0);
		}
		int n = entry.size ();
		for (int i = 0; i < numBaseFields; i++) {
			try {
				baseFields.get (i).setVal (entry.get (i));
			} catch (IllegalArgumentException e) {
				String message = "Error while writing value " + entry.get (i) + " in field (" + i + ") " + entry.datStructure.fieldStructs[i];
				Core.printException (this, e, message, "Error", true);
				throw new IllegalArgumentException (message, e);
			}
		}
		for (int i = 0, j = n - numBaseFields; i < j; i++) {
			extraFields.get (i).setVal (entry.get (numBaseFields + i));
		}
		String description = entry.datStructure.getEntryDescription (entry);
		entryDescription.setText (description != null ? "<HTML>" + description + "</HTML>" : null);
	}

	/**
	 * Paste the given entry's values in the fields.
	 *
	 * @param entry The entry to load
	 */
	public void pasteEntry (Entry entry) {
		if (Settings.DEBUG) {
			System.out.println ("Load entry: " + entry);
		}
		if (entry.datStructure.extraField != null) {
			buildExtraFields (entry, (int) entry.get (entry.datStructure.indexExtraFields ()));
		} else {
			buildExtraFields (entry, 0);
		}
		int n = entry.size ();
		int indName = entry.datStructure.indexName;
		int indID = entry.datStructure.indexID;
		int indSeq = entry.datStructure.indexSequence;
		for (int i = 0; i < numBaseFields; i++) {
			if (i != indName && i != indID && i != indSeq) {
				baseFields.get (i).setVal (entry.get (i));
			}
		}
		for (int i = 0, j = n - numBaseFields; i < j; i++) {
			extraFields.get (i).setVal (entry.get (numBaseFields + i));
		}
	}

	/**
	 * Save the current entry
	 */
	public void saveEntry () {
		DatStructure datStructure = datFile.datStructure;
		DB_SPECIFIC: {
			if (isDbObject) {
				int first = Core.isAOC () ? 253 : 251;
				Link val;
				for (int i = 0; i < 200; i++) {
					val = (Link) baseFields.get (first + i).getVal ();
					if (val.target.getID () < 0) {
						baseFields.get (224).setVal (i);
						break DB_SPECIFIC;
					}
				}
				baseFields.get (224).setVal (200);
			} else if (isDbTechTree) {
				int n = datStructure.fieldStructs.length;
				int nExtra = Integer.parseInt (((JTextFieldField) baseFields.get (n - 1).field).getText ());
				Object selected = null;
				for (int i = 0; i < nExtra; i++) {
					selected = ((JComboBoxField) extraFields.get (i).field).getSelectedItem ();
					if (selected == null || selected instanceof Entry == false || !((Entry) selected).isValidLinkTarget ()) {
						Core.printError (this, "Can't save this entry. Field " + (n + i) + " points to an invalid Object", "Error");
						return;
					}
				}
				JComboBoxField jcbf = ((JComboBoxField) baseFields.get (n - 2).field);
				if (!jcbf.allEntries.contains (selected)) {
					jcbf.refreshField ();
				}
				jcbf.setSelectedItem (selected);
			} else if (isDbEvents) {
				int n = datStructure.fieldStructs.length;
				int nExtra = Integer.parseInt (((JTextFieldField) baseFields.get (n - 1).field).getText ());
				Object selected = null;
				for (int i = 0; i < nExtra; i++) {
					selected = ((JComboBoxField) extraFields.get (i).field).getSelectedItem ();
					if (selected == null || selected instanceof Entry == false || !((Entry) selected).isValidLinkTarget ()) {
						Core.printError (this, "Can't save this entry. Field " + (n + i) + " points to an invalid Effect", "Error");
						return;
					}
				}
			}
		}


		for (int i = 0; i < numBaseFields; i++) {
			currentEntry.set (i, baseFields.get (i).getVal ());
		}
		int indexID = datStructure.indexID;
		int indexSeqNum = datStructure.indexSequence;
		if (indexID >= 0) {
			int curID = currentEntry.get (indexID);
			if (curID != currentEntry.getID ()) {
				currentEntryGroup.map.remove (currentEntry.getID (), currentEntry);
				currentEntry.getLinksToEntry (false).forEach (link -> link.source.datStructure.datFile.setUnsaved (true));
				currentEntry.setID (curID);
			}
		}
		currentEntryGroup.map.put (currentEntry.getID (), currentEntry);
		if (indexSeqNum >= 0) {
			currentEntry.setSequenceNumber (currentEntry.get (indexSeqNum));
		}

		if (indexCountExtra >= 0) {
			int numExtraFields = currentEntry.size () - numBaseFields;
			for (int i = 0; i < numPlacedExtraFields; i++) {
				if (i < numExtraFields) {
					currentEntry.set (numBaseFields + i, extraFields.get (i).getVal ());
				} else {
					currentEntry.add (extraFields.get (i).getVal ());
				}
			}
			numExtraFields = currentEntry.size () - numBaseFields;
			for (int i = numExtraFields; i > numPlacedExtraFields; i--) {
				currentEntry.remove (numBaseFields + i - 1);
			}
			currentEntry.set (indexCountExtra, baseFields.get (indexCountExtra).getVal ());
		}
		datFile.setUnsaved (true);
		entryList.refresh ();
		if (Settings.DEBUG) {
			System.out.println ("Save entry: " + currentEntry);
		}
	}



	/**
	 * Jump to the given entry in the given group
	 *
	 * @param entryGroup The group
	 * @param entry The entry
	 */
	public void goToEntry (EntryGroup entryGroup, Entry entry) {
		if (Settings.DEBUG) {
			System.out.println ("Go to: " + datFile.getName () + " > " + entryGroup + " > " + entry);
		}
		if (!entry.isDefined () && entryList.switchList.isSelected ()) {
			entryList.switchList.doClick ();
		}
		if (!isVisible ()) {
			setVisible (true);
		}
		entryGroupList.setSelectedElement (entryGroup);
		if (entryGroupList.getLength () > 0) {
			entryList.setSelectedElement (entry);
		}
	}



	/**
	 * Show all entries which have the same value in the selected field.
	 */
	public void showSearchFieldResults () {
		EntryFieldInterface field = (EntryFieldInterface) rightClicked;
		int index = field.getIndex ();
		Object value = field.getVal ();
		Object entryValue;
		List <Entry> entries = new ArrayList <> ();
		List <Entry> entriesClean = new ArrayList <> ();

		for (EntryGroup entryGroup : datFile) {
			for (Entry entry : entryGroup) {
				if (index < entry.size ()) {
					entryValue = entry.get (index);
					if (value.equals (entryValue)) {
						entries.add (entry);
						if (entry.isDefined ()) {
							entriesClean.add (entry);
						}
					}
				}
			}
		}
		JDialog d = new DialogSearchFieldResults (this, entries, entriesClean, field);
		d.setVisible (true);
	}





	/**
	 * Marks all fields which are either unused/unchanged (0/same value everywhere) or have up to 2 values (including flags/boolean).
	 * This is very useful to identify many unknown fields.
	 */
	public void markUnusedFields () {
		EntryValueMap entryValueMap;
		FieldStruct fieldStruct;
		int size;
		for (JPanelEntry entryPanel : baseFields) {
			fieldStruct = entryPanel.fieldStruct;
			try {
				if (fieldStruct.getKnowledge () != Knowledge.KNOWN) {
					entryValueMap = EntryValueMap.getValuesMap (datFile.entryGroups, true, entryPanel.index);
					size = entryValueMap.mapClean.size ();
					if (size <= 2 || size == entryValueMap.counter) {
						marked.add (entryPanel);
						entryPanel.label.setBackground (Color.BLACK);
						entryPanel.label.setOpaque (true);
						if (size == 1) {
							if (fieldStruct.getKnowledge () != Knowledge.NEVER_CHANGE && fieldStruct.getKnowledge () != Knowledge.NEVER_USED) {
								entryPanel.label.setForeground (Color.RED);
							}
						} else if (size == 2) {
							entryPanel.label.setForeground (Color.YELLOW);
						} else {
							entryPanel.label.setForeground (Color.BLUE);
						}
					}
				}
			} catch (Exception e) {
				Core.printException (this, e, "An error occurred while marking the field " + fieldStruct, "Error", true);
			}
		}
		if (marked.size () > 0) {
			fieldMenuMarkUnusedFields.setVisible (false);
			fieldMenuUnmarkUnusedFields.setVisible (true);
		}
	}

	/**
	 * Remove all marks from the fields.
	 */
	public void unmarkUnusedFields () {
		Iterator <JPanelEntry> it = marked.iterator ();
		JPanelEntry entryPanel;
		while (it.hasNext ()) {
			entryPanel = it.next ();
			entryPanel.label.setOpaque (false);
			entryPanel.label.setBackground (null);
			entryPanel.label.setForeground (entryPanel.fieldStruct.getColor ());
			entryPanel.field.resetColor ();
			it.remove ();
		}
		fieldMenuMarkUnusedFields.setVisible (true);
		fieldMenuUnmarkUnusedFields.setVisible (false);
	}


	/**
	 * Find the parent entry of the given entry, if any (an entry which contains this entry)
	 *
	 * @param datFile The datFile where to search
	 * @param childEntry The child entry
	 * @param indexLinkField Index of the field where to search the Link to the child entry.
	 * @return The parent entry
	 */
	private static EntryLocation getParentEntry (DatFile datFile, Entry childEntry, int indexLinkField) {
		int ID = childEntry.getID ();
		Link link;
		for (EntryGroup entryGroup : datFile.entryGroups) {
			for (Entry entry : entryGroup) {
				link = entry.get (indexLinkField);
				if (link != null && link.target.getID () == ID) {
					return new EntryLocation (entryGroup, entry);
				}
			}
		}
		return EntryLocation.NULL;
	}

}
