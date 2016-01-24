package dbstructure;

public enum DatStructure {
	
	
	
	DB_TECH_TREE("dbtechtree.dat", 1, true, 0, 1, 2, 46,
			new EntryStruct("Tech builder", Type.INTEGER, 4), EntryStruct.NAME, EntryStruct.SEQ_NUMBER,
			new EntryStruct("Tech ID", Type.INTEGER, 4, false, EntryStruct.ID_COLOR), new EntryStruct("Starting epoch", Type.INTEGER, 4), new EntryStruct("Ending epoch", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, new EntryStruct("Wood cost", Type.INTEGER, 4), new EntryStruct("Stone cost", Type.INTEGER, 4), new EntryStruct("<Only Impassable tile object and Invisible capital>", Type.INTEGER, 4),
			new EntryStruct("Gold cost", Type.INTEGER, 4), EntryStruct.UNUSED_INT4, new EntryStruct("Iron cost", Type.INTEGER, 4), new EntryStruct("Food cost", Type.INTEGER, 4),
			new EntryStruct("Build time", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, new EntryStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4),
			new EntryStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4), new EntryStruct("Object ID", Type.INTEGER, 4), new EntryStruct("Button ID", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, new EntryStruct("<4 in all Epochs Space, 0 everywhere else>", Type.INTEGER, 4), EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, EntryStruct.UNKNOWN_INT4, new EntryStruct("Hotkey ID", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, new EntryStruct(null, Type.FLAG, 1), new EntryStruct("Only in scenario", Type.FLAG, 1), new EntryStruct(null, Type.FLAG, 1), new EntryStruct(null, Type.FLAG, 1),
			EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, new EntryStruct("Tech builder", Type.INTEGER, 4, false, EntryStruct.ID_COLOR), new EntryStruct("Num of tech builders", Type.INTEGER, 4, false, EntryStruct.ID_COLOR)
			),
	
	
	DB_FAMILY ("dbfamily.dat", 0, true, 0, 1, 2, -1,
			null, EntryStruct.NAME, EntryStruct.SEQ_NUMBER,
			new EntryStruct("Family ID", Type.INTEGER, 4, false, EntryStruct.ID_COLOR), EntryStruct.UNKNOWN_INT4, new EntryStruct("Frigate", Type.INTEGER, 4), new EntryStruct("Machine gun", Type.INTEGER, 4),
			new EntryStruct("Galley", Type.INTEGER, 4), new EntryStruct("Tank", Type.INTEGER, 4), new EntryStruct("AT Gun", Type.INTEGER, 4), new EntryStruct("Catapult, Bombard", Type.INTEGER, 4),
			new EntryStruct("AA Tower, Stinger, Flat halftrack", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4, new EntryStruct("Sea king", Type.INTEGER, 4), new EntryStruct("Field weapon", Type.INTEGER, 4),
			EntryStruct.UNKNOWN_INT4, new EntryStruct("Fighter, Cruiser", Type.INTEGER, 4), new EntryStruct("Cavalry spear", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, new EntryStruct("Cavalry gun", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4, new EntryStruct("Submarine", Type.INTEGER, 4),
			new EntryStruct("Ram, Sampson", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4, new EntryStruct("Archer, Javelin", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, new EntryStruct("AT Tank", Type.INTEGER, 4), new EntryStruct("Battleship", Type.INTEGER, 4),
			new EntryStruct("AT Helicopter", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4,
			new EntryStruct("Crossbow", Type.INTEGER, 4), new EntryStruct("Carrier fighter", Type.INTEGER, 4), new EntryStruct("Gunship Helicopter", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, new EntryStruct("Tower", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, new EntryStruct("Sword", Type.INTEGER, 4), new EntryStruct("Cavalry sword", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, new EntryStruct("Bazooka", Type.INTEGER, 4), new EntryStruct("Spear", Type.INTEGER, 4), new EntryStruct("Cavalry archer", Type.INTEGER, 4),
			EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, new EntryStruct("Anti-Missile Battery", Type.INTEGER, 4), new EntryStruct("Capitol ship", Type.INTEGER, 4), new EntryStruct("Space fighter", Type.INTEGER, 4),
			new EntryStruct("Space corvette", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4, new EntryStruct("Space turret", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4
			),

	DB_WEAPON_TO_HIT ("dbweapontohit.dat", 0, false, 0, 1, 2, -1,
			null, EntryStruct.NAME, EntryStruct.SEQ_NUMBER,
			new EntryStruct("Weapon To Hit ID", Type.INTEGER, 4, false, EntryStruct.ID_COLOR), new EntryStruct("Shock mult", Type.INTEGER, 4), new EntryStruct("Arrow mult", Type.INTEGER, 4),
			new EntryStruct("Pierce mult", Type.INTEGER, 4), new EntryStruct("Gun mult", Type.INTEGER, 4), new EntryStruct("Laser mult", Type.INTEGER, 4), new EntryStruct("Missile mult", Type.INTEGER, 4)
			),
	
	DB_UPGRADE ("dbupgrade.dat", 0, true, 0, -1, -1, -1,
			null,
			EntryStruct.NAME, new EntryStruct("Attack mult", Type.FLOAT, 4), new EntryStruct("Health mult", Type.FLOAT, 4), new EntryStruct("Speed mult", Type.FLOAT, 4),
			new EntryStruct("Range mult", Type.FLOAT, 4), new EntryStruct("Shock armor mult", Type.FLOAT, 4), new EntryStruct("Arrow armor mult", Type.FLOAT, 4), new EntryStruct("Pierce armor mult", Type.FLOAT, 4),
			new EntryStruct("Gun armor mult", Type.FLOAT, 4), new EntryStruct("Laser armor mult", Type.FLOAT, 4), new EntryStruct("Missile armor mult", Type.FLOAT, 4), new EntryStruct("Fuel/Power mult", Type.FLOAT, 4),
			EntryStruct.UNUSED_INT4, new EntryStruct("Area mult", Type.FLOAT, 4), EntryStruct.UNUSED_INT4, new EntryStruct("Attack cost mult", Type.FLOAT, 4),
			new EntryStruct("Health cost mult", Type.FLOAT, 4), new EntryStruct("Speed cost mult", Type.FLOAT, 4), new EntryStruct("Range cost mult", Type.FLOAT, 4), new EntryStruct("Shock armor cost mult", Type.FLOAT, 4),
			new EntryStruct("Arrow armor cost mult", Type.FLOAT, 4), new EntryStruct("Pierce armor cost mult", Type.FLOAT, 4), new EntryStruct("Gun armor cost mult", Type.FLOAT, 4), new EntryStruct("Laser armor cost mult", Type.FLOAT, 4),
			new EntryStruct("Missile armor cost mult", Type.FLOAT, 4), new EntryStruct("Fuel/Power cost mult", Type.FLOAT, 4), EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4,
			new EntryStruct("Area cost mult", Type.FLOAT, 4), EntryStruct.UNUSED_INT4, new EntryStruct("Max upgrades per stat", Type.INTEGER, 4), EntryStruct.SEQ_NUMBER,
			new EntryStruct ("Upgrade ID", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNUSED_INT4
			),

	DB_AREA_EFFECT("dbareaeffect.dat", 0, true, 0, 1, 2, -1,
			null, EntryStruct.NAME, EntryStruct.SEQ_NUMBER,
			new EntryStruct("Area Effect ID", Type.INTEGER, 4, false, EntryStruct.ID_COLOR), EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4,
			EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNKNOWN_INT4,
			new EntryStruct("Morale", Type.INTEGER, 4), new EntryStruct("Heal effect", Type.INTEGER, 4), EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, new EntryStruct("Area size", Type.FLOAT, 4), EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4
			),
	
	DB_BUTTONS ("dbbuttons.dat", 0, true, 0, 2, 3, -1,
			null, EntryStruct.NAME,
			new EntryStruct("Texture", Type.STRING, 100), EntryStruct.SEQ_NUMBER,
			new EntryStruct("Button ID", Type.INTEGER, 4, false, EntryStruct.ID_COLOR), new EntryStruct("<only used by espionage center>", Type.UKNONWN, 4), new EntryStruct("<only used by farm and espionage center>", Type.UKNONWN, 4), new EntryStruct("Position", Type.INTEGER, 4), EntryStruct.UNKNOWN_INT4
			),

	DB_CIVILIZATION ("dbcivilization.dat", 0, false, 0, 1, 2, -1,
			null, EntryStruct.NAME, EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, new EntryStruct("Language ID", Type.INTEGER, 4),
			new EntryStruct("Civilization ID", Type.INTEGER, 4, false, EntryStruct.ID_COLOR), new EntryStruct("Cost increment", Type.INTEGER, 4),
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT,
			EntryStruct.UNUSED_FLOAT, EntryStruct.UNUSED_FLOAT, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_INT4
			),

	DB_STARTING_RESOURCHES ("dbstartingresources.dat", 0, true, 0, 1, 2, -1,
			null, EntryStruct.NAME, EntryStruct.SEQ_NUMBER, new EntryStruct("Starting Resourches ID", Type.INTEGER, 4, false, EntryStruct.ID_COLOR),
			new EntryStruct("Language ID", Type.INTEGER, 4), new EntryStruct("Starting food", Type.INTEGER, 4), new EntryStruct("Starting wood", Type.INTEGER, 4),
			new EntryStruct("Starting stone", Type.INTEGER, 4), new EntryStruct("Starting gold", Type.INTEGER, 4), new EntryStruct("Starting iron", Type.INTEGER, 4)
			),

	DB_GAME_VARIANT ("dbgamevariant.dat", 0, true, 0, 1, 2, -1,
			null, EntryStruct.NAME, EntryStruct.SEQ_NUMBER,
			new EntryStruct("Game Variant ID", Type.INTEGER, 4, false, EntryStruct.ID_COLOR), EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_INT4, EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, new EntryStruct("Gather rate multiplier", Type.FLOAT, 4),
			EntryStruct.UNKNOWN_INT4, new EntryStruct("Language ID", Type.INTEGER, 4), EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			new EntryStruct("Wonder cost multiplier", Type.FLOAT, 4), EntryStruct.UNKNOWN_INT4, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4, EntryStruct.UNUSED_INT4,
			EntryStruct.UNUSED_INT4, new EntryStruct("Available in random maps", Type.INTEGER, 4), EntryStruct.UNUSED_INT4
			),

	DB_MUSIC ("dbmusic.dat", 0, true, 2, 0, 1, -1,
			null, EntryStruct.SEQ_NUMBER, new EntryStruct("Music ID", Type.INTEGER, 4), EntryStruct.NAME,
			new EntryStruct("Another name", Type.STRING, 56), EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT, EntryStruct.UNKNOWN_FLOAT,
			EntryStruct.UNCHANGED_4, EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1,
			EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1,
			EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1,
			EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1, EntryStruct.UNCHANGED_1
			),
	
	DB_UIHOTKEY ("dbuihotkey.dat", 0, true, 2, 0, 1, -1,
			null, EntryStruct.SEQ_NUMBER, new EntryStruct("Hotkey ID", Type.INTEGER, 4, false, EntryStruct.ID_COLOR),
			EntryStruct.NAME, EntryStruct.UNCHANGED_4, EntryStruct.UNCHANGED_4, EntryStruct.UNCHANGED_4, EntryStruct.UNKNOWN_INT4
			),
	
	;
	
	
	public String fileName;			//null if value is unknown
	public int alterNumEntries;		//dbtechtree declare 1 less entry, because Epochs are not counted...
	public boolean supportNumAlteration;	//if true, you can add/remove entries. If false, the file expect the given entries (no more, no less).
	public int indexName;			//index of the entry in the array, which define the Name
	public int indexSequence;		//index of the entry in the array, which define the Sequence number
	public int indexID;				//index of the entry in the array, which define the ID
	public int indexCountExtra;		//index of the entry in the array, which define the number of extra entries
	public EntryStruct extraEntry;	//if not null, the entry can have extra fields of this type
	public EntryStruct[] entries;	//array of fields

	DatStructure(String fileName, int alterNumEntries, boolean supportNumAlteration, int indexName, int indexSequence, int indexID, int indexCountExtra, EntryStruct extraEntry, EntryStruct...entries){
		this.fileName = fileName;
		this.alterNumEntries = alterNumEntries;
		this.supportNumAlteration = supportNumAlteration;
		this.entries = entries;
		this.extraEntry = extraEntry;
		this.indexName = indexName;
		this.indexSequence = indexSequence;
		this.indexID = indexID;
		this.indexCountExtra = indexCountExtra;
	}
	
	
	

	
}
