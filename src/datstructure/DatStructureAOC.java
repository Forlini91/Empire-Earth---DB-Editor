package datstructure;

/**
 * This enumeration define all dat files structures, which will be used by the program to parse all the values in the files.
 * If a file structure is wrong, the loading will either fail or corrupted results will show in the editor window.
 * @author MarcoForlini
 *
 */
public enum DatStructureAOC implements DatStructure {
	
	DB_AI_UNIT_TARGETING	("AI Unit Targeting", "dbaiunittargeting.dat", 0, false, 0, 1, 2, -1),
	DB_AMBIENT_SOUNDS		("Ambient sounds", "dbambientsounds.dat", 0, true, 0, 1, 2, -1),
	DB_AREA_EFFECT			("Area effects", "dbareaeffect.dat", 0, true, 0, 1, 2, -1),
	DB_BUTTONS				("Buttons", "dbbuttons.dat", 0, true, 0, 2, 3, -1),
	DB_CALAMITY				("Calamities", "dbcalamity.dat", 0, false, 0, 7, 8, -1),
	DB_CIVILIZATION			("Civilizations", "dbcivilization.dat", 0, false, 0, -1, -1 , -1),
	DB_CIV_POWER			("Powers", "dbcivpowers.dat", 0, false, 0, 1, 2, -1),
	DB_CLIFF_TERRAN			("Cliff terrain", "dbcliffterrain.dat", 0, false, 0, 1, 2, -1),
	DB_FAMILY				("Families", "dbfamily.dat", 0, false, 0, 1, 2, -1),
	DB_GAME_VARIANT			("Game variants", "dbgamevariant.dat", 0, false, 0, 1, 2, -1),
	DB_GFX_EFFECTS			("GFX Effects", "dbgfxeffects.dat", 0, true, 0, 1, 2, -1),
	DB_GRAPHICS				("Graphics", "dbgraphics.dat", 0, true, 66, 67, 68, -1),
	DB_MUSIC				("Musics", "dbmusic.dat", 0, true, 2, 0, 1, -1),
	DB_OBJECTS				("Objects", "dbobjects.dat", 0, true, 0, 1, 5, -1),
	DB_PREMADE_CIVS			("Premade civilizations", "dbpremadecivs.dat", 0, false, 2, 0, 1, -1),
	DB_SOUNDS				("Sounds", "dbsounds.dat", 0, true, 1, 2, 3, -1),
	DB_STARTING_RESOURCHES	("Starting resourches", "dbstartingresources.dat", 0, true, 0, 1, 2, -1),
	DB_TECH_TREE			("Technologies", "dbtechtree.dat", 1, true, 0, 1, 2, 46),
	DB_TERRAIN				("Terrain", "dbterrain.dat", 0, true, 1, 2, 3, -1),
	DB_TERRAIN_TYPE			("Terrain type", "dbterraintype.dat", 0, false, 0, 1, 2, -1),
	DB_UI_FONT				("UI Fonts", "dbuifonts.dat", 0, false, 0, 1, 2, -1),
	DB_UI_HOTKEY			("UI Hotkeys", "dbuihotkey.dat", 0, false, 2, 0, 1, -1),
	DB_UNIT_BEHAVIOR		("Unit behavior", "dbunitbehavior.dat", 0, false, 0, 1, 2, -1),
	DB_UNIT_SET				("Unit sets", "dbunitset.dat", 0, false, 0, 1, 2, -1),
	DB_UPGRADE				("Upgrades", "dbupgrade.dat", 0, true, 0, 31, 32, -1),
	DB_WEAPON_TO_HIT		("Weapons to hit", "dbweapontohit.dat", 0, false, 0, 1, 2, -1),
	;
	
	
	
	/** Unique field: A 4 bytes integer which point to an area effect's ID. */
	public static final FieldStruct ID_AREA_EFFECT = new FieldStruct("Area Effect ID", DB_AREA_EFFECT);
	/** Unique field: A 4 bytes integer which point to a button's ID. */
	public static final FieldStruct ID_BUTTON = new FieldStruct("Button ID", DB_BUTTONS);
	/** Unique field: A 4 bytes integer which point to a civilization's ID. */
	public static final FieldStruct ID_CIVILIZATION = new FieldStruct("Civilization ID", DB_CIVILIZATION);
	/** Unique field: A 4 bytes integer which point to a family's ID. */
	public static final FieldStruct ID_FAMILY = new FieldStruct("Family ID", DB_FAMILY);
	/** Unique field: A 4 bytes integer which point to a graphic's ID. */
	public static final FieldStruct ID_GRAPHIC = new FieldStruct("Graphic ID", DB_GRAPHICS);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct ID_OBJECT = new FieldStruct("Object ID", DB_OBJECTS, 0);
	/** Unique field: A 4 bytes integer which point to a sound ID. */
	public static final FieldStruct ID_SOUND = new FieldStruct("Sound ID", DB_SOUNDS);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct ID_TECH = new FieldStruct("Tech ID", DB_TECH_TREE);
	/** Unique field: A 4 bytes integer which point to an hotkey's ID. */
	public static final FieldStruct ID_UI_HOTKEY = new FieldStruct("Hotkey ID", DB_UI_HOTKEY);
	/** Unique field: A 4 bytes integer which point to an unit set's ID. */
	public static final FieldStruct ID_UNIT_SET = new FieldStruct("Unit set ID", DB_UNIT_SET);
	/** Unique field: A 4 bytes integer which point to an upgrade's ID. */
	public static final FieldStruct ID_UPGRADE = new FieldStruct("Updrade ID", DB_UPGRADE, 0);
	/** Unique field: A 4 bytes integer which point to a weapon to hit's ID. */
	public static final FieldStruct ID_WEAPON_TO_HIT = new FieldStruct("Weapon to hit ID", DB_WEAPON_TO_HIT);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct ID_TECH_FROM_OBJECT = new FieldStruct("Build from object", DB_OBJECTS, 0);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct ID_OBJECT_BUILD_TECH = new FieldStruct ("Can build tech", DB_TECH_TREE);
	/** Unique field: A 4 bytes integer which point to a button's ID. */
	public static final FieldStruct ID_BUTTON_COMMAND = new FieldStruct("Button/Command ID", DB_BUTTONS);
	/** Special field: A 4 bytes float which define an unused ID. */
	public static final FieldStruct UNUSED_GFX_EFFECT = new FieldStruct("Unused", Type.ID, 4, Knowledge.NEVER_USED, DB_GFX_EFFECTS);
	/** Special field: A 4 bytes float which define a (still) unknown ID. */
	public static final FieldStruct UNKNOWN_GFX_EFFECT = new FieldStruct("Unknown", Type.ID, 4, Knowledge.UNKNOWN, DB_GFX_EFFECTS);

	
	
	static {
		DB_AI_UNIT_TARGETING.extraEntry = null;
		DB_AI_UNIT_TARGETING.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNCHANGED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_AI_UNIT_TARGETING.defaultValues = null;



		DB_AMBIENT_SOUNDS.extraEntry = null;
		DB_AMBIENT_SOUNDS.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_FLOAT
		};
		DB_AMBIENT_SOUNDS.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, 0, -1, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, -858993664
		};
		
		
		
		DB_AREA_EFFECT.extraEntry = null;
		DB_AREA_EFFECT.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Effect type", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Effect on units", DB_GFX_EFFECTS), new FieldStruct("Apply on unit set", DB_UNIT_SET), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Max morale", Type.INTEGER, 4), new FieldStruct("Heal effect", Type.INTEGER, 4),
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Area size", Type.FLOAT, 4),
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		DB_AREA_EFFECT.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0f, 0f, 0f, 0f, 0f, 0, 0, -858993664
		};



		DB_BUTTONS.extraEntry = null;
		DB_BUTTONS.entries = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Texture", Type.STRING, 100), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				new FieldStruct("<only used by espionage center>", Type.INTEGER, 4), new FieldStruct("<only used by farm and espionage center>", Type.INTEGER, 4), new FieldStruct("Position", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4
		};
		DB_BUTTONS.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, Entry.STRING_UNDEFINED, 0, -1, 0, 0, 0, -1
		};
		


		DB_CALAMITY.extraEntry = null;
		DB_CALAMITY.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_CALAMITY.defaultValues = null;



		DB_CIVILIZATION.extraEntry = null;
		DB_CIVILIZATION.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.ID_LANGUAGE,
				new FieldStruct("Cost increment", Type.INTEGER, 4), ID_UNIT_SET, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_CIVILIZATION.defaultValues = null;



		DB_CIV_POWER.extraEntry = null;
		DB_CIV_POWER.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Cost", Type.FLOAT, 4),
				FieldStruct.UNKNOWN_INT4, ID_TECH, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_CIV_POWER.defaultValues = null;



		DB_CLIFF_TERRAN.extraEntry = null;
		DB_CLIFF_TERRAN.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		DB_CLIFF_TERRAN.defaultValues = null;



		DB_FAMILY.extraEntry = null;
		DB_FAMILY.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Frigate", Type.INTEGER, 4), new FieldStruct("Machine gun", Type.INTEGER, 4), new FieldStruct("Galley", Type.INTEGER, 4), new FieldStruct("Tank", Type.INTEGER, 4),
				new FieldStruct("AT Gun", Type.INTEGER, 4), new FieldStruct("Catapult, Bombard", Type.INTEGER, 4), new FieldStruct("AA Tower, Stinger, Flat halftrack", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Sea king", Type.INTEGER, 4), new FieldStruct("Field weapon", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct("Fighter, Cruiser", Type.INTEGER, 4),
				new FieldStruct("Cavalry spear", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Cavalry gun", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Submarine", Type.INTEGER, 4), new FieldStruct("Ram, Sampson", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Archer, Javelin", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("AT Tank", Type.INTEGER, 4), new FieldStruct("Battleship", Type.INTEGER, 4), new FieldStruct("AT Helicopter", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Crossbow", Type.INTEGER, 4), new FieldStruct("Carrier fighter", Type.INTEGER, 4),
				new FieldStruct("Gunship Helicopter", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Tower", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Sword", Type.INTEGER, 4),
				new FieldStruct("Cavalry sword", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Bazooka", Type.INTEGER, 4),
				new FieldStruct("Spear", Type.INTEGER, 4), new FieldStruct("Cavalry archer", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Fishing boat", Type.INTEGER, 4), new FieldStruct("Anti-Missile Battery", Type.INTEGER, 4),
				new FieldStruct("Capitol ship", Type.INTEGER, 4), new FieldStruct("Space fighter", Type.INTEGER, 4), new FieldStruct("Space corvette", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Space turret", Type.INTEGER, 4), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		DB_FAMILY.defaultValues = null;



		DB_GAME_VARIANT.extraEntry = null;
		DB_GAME_VARIANT.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNCHANGED_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Gather rate multiplier", Type.FLOAT, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.ID_LANGUAGE,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Wonder cost multiplier", Type.FLOAT, 4), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Available in random maps", Type.INTEGER, 4),
				FieldStruct.UNUSED_INT4
		};
		DB_GAME_VARIANT.defaultValues = null;



		DB_GFX_EFFECTS.extraEntry = null;
		DB_GFX_EFFECTS.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.ID_LANGUAGE, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_GFX_EFFECTS.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, 0, -1, -1, -1, -1, 1f, 1f,
				0f, 0, 0, 0, 0, 0, 0, -1,
				-1, -1, 0, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0,
				-858993664, 0
		};


		DB_GRAPHICS.extraEntry = null;
		DB_GRAPHICS.entries = new FieldStruct[]{
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Name 1", 0, 0), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Model path", 0, 2),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 4), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 6),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 8), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 10),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 12), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 14),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 16), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 18),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 20), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 22),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 24), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 26)   ,
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 28), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 30),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 32), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 34),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 36), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 38),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 40), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 42),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 44), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 46),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 48), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 50),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 52), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 54),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 56), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 58),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 60), FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 62),
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Texture path", 0, 64), FieldStruct.NAME, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4
		};
		DB_GRAPHICS.defaultValues = new Object[]{
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", Entry.STRING_UNDEFINED, 0, 0, -858993664, 0, 0,
				-858993664, 0, 0, 0, -858993664, 0, 0, 0,
				-1, -1, -859045888, 0, 0, 0, -858993664, 0,
				0, 0, -1, -1, -859045888, 0, 0, 0,
				-858993664, 0, 0, 0, -1, -1, -859045888, 0,
				0, 0, -858993664, 0, 0, 0, -1, -1,
				-859045888, 0, 0, 0, -858993664, 0, -859045888, 0,
				0, 0, -858993664, 0, -859045888, 0, 0, 0,
				-858993664, 0, 0, 0, -1, -1, -859045888, 0,
				0, 0, -858993664, 0, 0, 0, -1, -1,
				-859045888, 0, 0, 0, -858993664, 0, 0, 0,
				-1, -1, -859045888, 0, 0, 0, -858993664, 0,
				0, 0, -1, -1, -859045888, 0, 0, 0,
				-859045888, 0, 0, 0, -1, -1, -859045888, 0,
				0, 0, -858993664, 0, 0, 0, -1, -1,
				-859045888, 0, 0, 0, -858993664, 0, 0, 0,
				-1, -1, -859045888, 0, 0, 0, -858993664, 0,
				0, 0, -1, -1, -858993664, 0, -858993664, 0,
				0, -1, 0, 0, -1, -1, -858993664, 1f,
				0, 0f, 0, 0, -858993664
		};
		


		DB_MUSIC.extraEntry = null;
		DB_MUSIC.entries = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, new FieldStruct("Another name", Type.STRING, 56),
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNUSED_INT1
		};
		DB_MUSIC.defaultValues = new Object[]{
				-1, -1, Entry.STRING_UNDEFINED, Entry.STRING_UNDEFINED.substring(0, 56), 0f, 0f, 0f, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 204
		};
		


		DB_OBJECTS.extraEntry = null;
		DB_OBJECTS.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, ID_FAMILY, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.ID, new FieldStruct("Health", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Min range", Type.FLOAT, 4), new FieldStruct("Max range", Type.FLOAT, 4), new FieldStruct("Line of sight", Type.FLOAT, 4), new FieldStruct("Attack speed", Type.FLOAT, 4),
				new FieldStruct("Area of effect", Type.FLOAT, 4), new FieldStruct("Speed", Type.FLOAT, 4), new FieldStruct("Acceleration/Deceleration", Type.FLOAT, 4), new FieldStruct("Idle turning speed", Type.FLOAT, 4),
				new FieldStruct("Moving turning speed", Type.FLOAT, 4), FieldStruct.UNKNOWN_FLOAT, new FieldStruct("<PROBABLY> Is air unit", Type.INTEGER, 4), new FieldStruct("Flight time", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Attack angle", Type.FLOAT, 4), FieldStruct.UNKNOWN_INT4, ID_WEAPON_TO_HIT,
				new FieldStruct("Attack", Type.INTEGER, 4), new FieldStruct("Shock armor", Type.INTEGER, 4), new FieldStruct("Arrow armor", Type.INTEGER, 4), new FieldStruct("Pierce armor", Type.INTEGER, 4),
				new FieldStruct("Gun armor", Type.INTEGER, 4), new FieldStruct("Laser armor", Type.INTEGER, 4), new FieldStruct("Missile armor", Type.INTEGER, 4), new FieldStruct("Projectile ID", DB_OBJECTS),
				new FieldStruct("Family attack multiplier index", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct("Graphic size", Type.FLOAT, 4), new FieldStruct("Unit type", Type.STRING, 52),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, ID_BUTTON, ID_GRAPHIC,
				new FieldStruct("Build rate", Type.FLOAT, 4), new FieldStruct("Iron gather rate", Type.FLOAT, 4), new FieldStruct("Farm gather rate", Type.FLOAT, 4), new FieldStruct("Gold gather rate", Type.FLOAT, 4),
				new FieldStruct("Stone gather rate", Type.FLOAT, 4), new FieldStruct("Wood gather rate", Type.FLOAT, 4), new FieldStruct("Hunt gather rate", Type.FLOAT, 4), new FieldStruct("Forage gather rate", Type.FLOAT, 4),
				ID_SOUND, ID_SOUND, ID_SOUND, ID_SOUND,
				ID_SOUND, ID_SOUND, new FieldStruct("<PROBABLY> Can build", Type.BOOLEAN, 4), ID_BUTTON_COMMAND,
				ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND,
				ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND,
				FieldStruct.UNCHANGED_INT4, new FieldStruct("Square occupied", Type.INTEGER, 4), new FieldStruct("Resource type", Type.INTEGER, 4), new FieldStruct("Resource amount", Type.FLOAT, 4),
				new FieldStruct("Always face camera", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct("Rotting time", Type.FLOAT, 4), new FieldStruct("Population count", Type.INTEGER, 4),
				new FieldStruct("Transport capacity", Type.INTEGER, 4), new FieldStruct("Show area effect stat"), new FieldStruct("Show repair stat"), new FieldStruct("Show shock armor stat"),
				new FieldStruct("Show pierce armor stat"), new FieldStruct("Show arrow armor stat"), new FieldStruct("Show laser armor stat"), new FieldStruct("Show gun armor stat"),
				new FieldStruct("Show missile armor stat"), new FieldStruct("Show range", Type.INTEGER, 4), new FieldStruct("Morale bonus", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNUSED_INT1,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, new FieldStruct("Flight altitude", Type.FLOAT, 4), FieldStruct.ID_LANGUAGE,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Health regeneration", Type.INTEGER, 4), new FieldStruct("Can garrison in air transports"),
				new FieldStruct("Can garrison in land transports"), new FieldStruct("Can garrison in sea transports"), FieldStruct.UNUSED_INT1, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Circle selection size", Type.FLOAT, 4), new FieldStruct("Shadow size", Type.FLOAT, 4),  FieldStruct.UNUSED_INT4,
				new FieldStruct("<Some kind of size>", Type.FLOAT, 4), new FieldStruct("Physical size", Type.FLOAT, 4), new FieldStruct("Units can walk above this"), FieldStruct.UNKNOWN_INT1,
				FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				new FieldStruct("Ammo amount", Type.INTEGER, 4), new FieldStruct("<All bombers use this>"), FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Category (Heroes use 27)", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				ID_UPGRADE, new FieldStruct("<Used by units/buildings who can convert>", Type.INTEGER, 4), FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Show in scenari editor"),
				new FieldStruct("<PROBABLY> Belongs to \"World\""), FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Can be killed with Delete"),
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNUSED_INT1, new FieldStruct("Low health effect ID", DB_GFX_EFFECTS),
				new FieldStruct("Death effect ID", DB_GFX_EFFECTS), new FieldStruct("Start of attack ID", DB_GFX_EFFECTS), UNKNOWN_GFX_EFFECT, new FieldStruct("Movement effect", DB_GFX_EFFECTS),
				UNKNOWN_GFX_EFFECT, new FieldStruct("Heal other effect ID", DB_GFX_EFFECTS), UNKNOWN_GFX_EFFECT, UNKNOWN_GFX_EFFECT,
				new FieldStruct("Get hit effect ID", DB_GFX_EFFECTS), new FieldStruct("Permanent effect", DB_GFX_EFFECTS), UNUSED_GFX_EFFECT, new FieldStruct("Projectile effect", DB_GFX_EFFECTS),
				UNUSED_GFX_EFFECT, UNUSED_GFX_EFFECT, UNUSED_GFX_EFFECT, UNUSED_GFX_EFFECT,
				UNUSED_GFX_EFFECT, new FieldStruct("<only used by capital ship/yamato>", DB_GFX_EFFECTS), new FieldStruct("<only used by volcano-projectile and meteor>", Type.INTEGER, 4), new FieldStruct("Attack effect ID", DB_GFX_EFFECTS),
				new FieldStruct("Disease effect ID 1", DB_GFX_EFFECTS), new FieldStruct("Disease effect ID 2", DB_GFX_EFFECTS), new FieldStruct("Diffraction shield effect ID", DB_GFX_EFFECTS), new FieldStruct("Pulse cannon effect ID", DB_GFX_EFFECTS),
				new FieldStruct("Battle cry effect ID", DB_GFX_EFFECTS), new FieldStruct("Bomb hole effect ID", DB_GFX_EFFECTS), UNUSED_GFX_EFFECT, UNKNOWN_GFX_EFFECT,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("<only used by ships>", Type.FLOAT, 4), FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Terrain: Grass, Snow"),
				new FieldStruct("Elevation: Deep water"), new FieldStruct("Terrain: Rock, Stones and Artificial"), FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Terrain: Asphalt"), new FieldStruct("Elevation: cliffs"), new FieldStruct("Elevation: Shallow water"),
				FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Terrain: Sand"), new FieldStruct("Terrain: Space"), FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_INT1, FieldStruct.UNUSED_INT1, ID_AREA_EFFECT,
				ID_AREA_EFFECT, ID_AREA_EFFECT, ID_AREA_EFFECT, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Carry capacity", Type.FLOAT, 4), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNUSED_INT1, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Debris on death", DB_OBJECTS, 0), FieldStruct.UNKNOWN_INT4, new FieldStruct("Min stealth radius", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Max citizens garrison", Type.INTEGER, 4), new FieldStruct("Initial citizens garrison", Type.INTEGER, 4), new FieldStruct("Object upgrade to", DB_TECH_TREE), new FieldStruct ("Plane refuel location", Type.INTEGER, 4),
				new FieldStruct("<Seems related to ships...>", Type.INTEGER, 4), new FieldStruct("Wonder bonus", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Friendly damage mult", Type.FLOAT, 4), new FieldStruct("Garrison citizens bonus", Type.FLOAT, 4), FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Num buildable techs", Type.INTEGER, 4),
				ID_TECH, new FieldStruct("Units can walk above this"), new FieldStruct("Stealth"), FieldStruct.UNKNOWN_BOOL1,
				new FieldStruct("<only used by units with fuel>", Type.INTEGER, 1), FieldStruct.UNKNOWN_INT4, new FieldStruct("Spawn on death", DB_OBJECTS, 0), new FieldStruct("Power", Type.INTEGER, 4),
				new FieldStruct("Power recover rate", Type.INTEGER, 4), new FieldStruct("Default stance", DB_UNIT_BEHAVIOR), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT,
				new FieldStruct("<It seems heal power, but the roman legionnaire...>", Type.FLOAT, 4, Knowledge.UNKNOWN), FieldStruct.UNKNOWN_FLOAT, new FieldStruct("<PROBABLY> Load range", Type.FLOAT, 4), new FieldStruct("<PROBABLY> Unload range", Type.FLOAT, 4),
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Can attack area"),
				new FieldStruct("Garrison heal rate", Type.INTEGER, 4), FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Can walk through trees", Type.INTEGER, 4),
				new FieldStruct("If 1, it's a melee unit", Type.INTEGER, 4), FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("<Only used by Pyramid>", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
				ID_OBJECT_BUILD_TECH, FieldStruct.UNKNOWN_INT4
		};
		DB_OBJECTS.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, 0, -1, -1, 0, -1, 0, 0,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, -858993664, 0, 0, 0f, -858993664, -1,
				0, 0, 0, 0, 0, 0, 0, -1,
				-1, -858993664, 0f, Entry.STRING_UNDEFINED.substring(0, 52), -1, 0, -1, -1,
				0f, 0f, -1.07374176E8, 0f, 0f, 0f, 0f, 0f,
				-1, -1, -1, -1, -1, -1, 0, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, 0, 0, 0, -858993664, -1, 0f, 0,
				0, 0, 0, 0, 0 ,0 ,0 ,0,
				0, -858993664, 0, 0, 0, 0, 204, 204,
				0f, -1, 0, 0, 0, 0, 0, 0,
				0, 0, 204, 0, -858993664, 0f, 0f, 0,
				0f, 0f, 0, 1, 204, 204, 0f, 0f,
				0, 0, 0, 0, 0, -1, 0, 0,
				0, 0, 0, -858993663, 0, 0, 0f, 1,
				0, 0, 0, 0, 0, 204, 204, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, 0f, 0,
				0, 0, 0, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 0, 0, 0, 204, 204, -1,
				-1, -1, -1, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 204, 0, 0, 0,
				0, 0, 0, 0, 0, 0, -1, 0,
				0, 0, 0, 0f, 0f, 0f, 0f, 0,
				0, -858993664, 0, 0, -1, 1, 0, 0,
				204, -858993460, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				-859045888, -858993460, -1.07374176E8, -858993460, -858993460, -858993460, -1.07374176E8, -858993460,
				-858993460, -858993460, -858993460, -858993460, -858993460, -858993460, -858993460, -858993460,
				-858993460, -858993460, -858993460, -858993460, -858993460, -858993460, -858993460, -858993460,
				-858993460, -858993460, -858993460, -858993460, -1.07374176E8, -858993460, -858993460, -858993460,
				-858993460, -858993460, -858993460, -858993460, -858993460, -858993460, -858993460, -858993460,
				-858993460, -858993460, -858993460, -858993460, -858993460, -858993460, -858993460, -858993460,
				-858993460, -858993460, -858993460, 0, 0, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -858993664
		};



		DB_PREMADE_CIVS.extraEntry = null;
		DB_PREMADE_CIVS.entries = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.UNKNOWN_INT4,
				FieldStruct.ID_LANGUAGE, FieldStruct.ID_LANGUAGE,
				
		};
		DB_PREMADE_CIVS.defaultValues = null;
		


		DB_SOUNDS.extraEntry = null;
		DB_SOUNDS.entries = new FieldStruct[]{
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Pathname", 100, 0), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4
		};
		DB_SOUNDS.defaultValues = new Object[]{
				0, Entry.STRING_UNDEFINED, 0, -1, 0, 0, 0, -858993664,
				0, 0
		};



		DB_STARTING_RESOURCHES.extraEntry = null;
		DB_STARTING_RESOURCHES.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.ID_LANGUAGE,
				new FieldStruct("Starting food", Type.INTEGER, 4), new FieldStruct("Starting wood", Type.INTEGER, 4), new FieldStruct("Starting stone", Type.INTEGER, 4), new FieldStruct("Starting gold", Type.INTEGER, 4),
				new FieldStruct("Starting iron", Type.INTEGER, 4)
		};
		DB_STARTING_RESOURCHES.defaultValues = new Object[]{
				"<New Starting Resourches>", -1, -1, -1, 500, 500, 500, 500, 500
		};
		


		DB_TECH_TREE.extraEntry = ID_TECH_FROM_OBJECT;
		DB_TECH_TREE.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Starting epoch", Type.INTEGER, 4),
				new FieldStruct("Ending epoch", Type.INTEGER, 4), new FieldStruct("(Only techs) sequence number", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct("Wood cost", Type.INTEGER, 4),
				new FieldStruct("Stone cost", Type.INTEGER, 4), new FieldStruct("<Only Impassable tile object and Invisible capital>", Type.INTEGER, 4), new FieldStruct("Gold cost", Type.INTEGER, 4), FieldStruct.UNUSED_INT4,
				new FieldStruct("Iron cost", Type.INTEGER, 4), new FieldStruct("Food cost", Type.INTEGER, 4),
				new FieldStruct("Build time", Type.INTEGER, 4), new FieldStruct("Unlocked by tech", DB_TECH_TREE),
				new FieldStruct("Unlocked by power", DB_TECH_TREE), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4), ID_OBJECT,
				ID_BUTTON, new FieldStruct("Is object?", Type.INTEGER, 4), FieldStruct.ID_LANGUAGE, new FieldStruct("Type of tech", Type.INTEGER, 4),
				new FieldStruct("<4 in all Epochs, 0 everywhere else>", Type.INTEGER, 4), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4,
				ID_UI_HOTKEY, new FieldStruct("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), new FieldStruct("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Only in scenario"), new FieldStruct("<All powers and power techs use 0>"),
				new FieldStruct("<All powers and power techs use 0>"), FieldStruct.UNUSED_INT4, new FieldStruct("Epoch number", Type.INTEGER, 4), new FieldStruct("<2> if it's an epoch", Type.INTEGER, 4),
				new FieldStruct("(Epochs) ID starts from...", Type.INTEGER, 4), ID_TECH_FROM_OBJECT, new FieldStruct("Num of tech builders", Type.INTEGER, 4, false)
		};
		DB_TECH_TREE.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, -1, 0, 0, 15, -1, -1, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, -1, -1, -859045888, 0, 0,
				0, 0, 0, 0, -1, -858993460, -858993460, -858993460,
				-858993460, 0f, 0f, 0f, 0f, 0, 0, 1,
				1, -872415232, 0, 0, -858993460, -1, 0
		};


		
		DB_TERRAIN.extraEntry = null;
		DB_TERRAIN.entries = new FieldStruct[]{
				FieldStruct.STRING_SIZE_EXTRA, new FieldStruct("Pathname", 100, 0), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				FieldStruct.UNKNOWN_INT4, FieldStruct.ID_LANGUAGE, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4
		};
		DB_TERRAIN.defaultValues = new Object[]{
				0, Entry.STRING_UNDEFINED, 0, -1, 0, 0, 0, 0,
				0, 0, -872415232, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, -858993460, -858993460, -858993460,
				0, 0f, 0f, 0
		};


		
		DB_TERRAIN_TYPE.extraEntry = null;
		DB_TERRAIN_TYPE.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.ID_LANGUAGE, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_TERRAIN_TYPE.defaultValues = new Object[]{
				"<New Starting Resourches>", -1, -1, -1, 500, 500, 500, 500, 500
		};



		DB_UI_FONT.extraEntry = null;
		DB_UI_FONT.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4
		};
		DB_UI_FONT.defaultValues = null;
		


		DB_UI_HOTKEY.extraEntry = null;
		DB_UI_HOTKEY.entries = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNCHANGED_INT4, FieldStruct.UNCHANGED_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_UI_HOTKEY.defaultValues = null;
		


		DB_UNIT_BEHAVIOR.extraEntry = null;
		DB_UNIT_BEHAVIOR.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		DB_UNIT_BEHAVIOR.defaultValues = null;
		


		DB_UNIT_SET.extraEntry = null;
		DB_UNIT_SET.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_UNIT_SET.defaultValues = null;
		


		DB_UPGRADE.extraEntry = null;
		DB_UPGRADE.entries = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Attack mult", Type.FLOAT, 4), new FieldStruct("Health mult", Type.FLOAT, 4), new FieldStruct("Speed mult", Type.FLOAT, 4),
				new FieldStruct("Range mult", Type.FLOAT, 4), new FieldStruct("Shock armor mult", Type.FLOAT, 4), new FieldStruct("Arrow armor mult", Type.FLOAT, 4), new FieldStruct("Pierce armor mult", Type.FLOAT, 4),
				new FieldStruct("Gun armor mult", Type.FLOAT, 4), new FieldStruct("Laser armor mult", Type.FLOAT, 4), new FieldStruct("Missile armor mult", Type.FLOAT, 4), new FieldStruct("Fuel/Power mult", Type.FLOAT, 4),
				FieldStruct.UNUSED_INT4, new FieldStruct("Area mult", Type.FLOAT, 4), FieldStruct.UNUSED_INT4, new FieldStruct("Attack cost mult", Type.FLOAT, 4),
				new FieldStruct("Health cost mult", Type.FLOAT, 4), new FieldStruct("Speed cost mult", Type.FLOAT, 4), new FieldStruct("Range cost mult", Type.FLOAT, 4), new FieldStruct("Shock armor cost mult", Type.FLOAT, 4),
				new FieldStruct("Arrow armor cost mult", Type.FLOAT, 4), new FieldStruct("Pierce armor cost mult", Type.FLOAT, 4), new FieldStruct("Gun armor cost mult", Type.FLOAT, 4), new FieldStruct("Laser armor cost mult", Type.FLOAT, 4),
				new FieldStruct("Missile armor cost mult", Type.FLOAT, 4), new FieldStruct("Fuel/Power cost mult", Type.FLOAT, 4), FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT,
				new FieldStruct("Area cost mult", Type.FLOAT, 4), FieldStruct.UNUSED_INT4, new FieldStruct("Max upgrades per stat", Type.INTEGER, 4), FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_UPGRADE.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0, 0f, 0, 0f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0, 0f, 0f, 0, 0, 0,
				0, 0, 0, -858993664
		};
		


		DB_WEAPON_TO_HIT.extraEntry = null;
		DB_WEAPON_TO_HIT.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Shock mult", Type.INTEGER, 4),
				new FieldStruct("Gun mult", Type.INTEGER, 4), new FieldStruct("Arrow mult", Type.INTEGER, 4), new FieldStruct("Pierce mult", Type.INTEGER, 4), new FieldStruct("Laser mult", Type.INTEGER, 4),
				new FieldStruct("Missile mult", Type.INTEGER, 4)
		};
		DB_WEAPON_TO_HIT.defaultValues = null;


		
		System.out.println("Check entries definitions:");
		int count;
		for (DatStructure datStructure : values()){
			count = 0;
			for (FieldStruct fieldStruct : datStructure.getEntries()){
				count += fieldStruct.size;
			}
			System.out.println('\t' + datStructure.getFileName() + ':' + ' ' + count + "  >  fields: " + datStructure.getEntries().length + "  |  defaults: " + (datStructure.getDefaultValues() != null ? datStructure.getDefaultValues().length : "null"));
		}
	}










	/** Name of the structure. Used for GUI purposes. */
	public String name;
	
	/** Name of the file. It must match exactly the dat filename. */
	public String fileName;
	
	/** The game define a counter "num entries" at the beginning of each group in the file.
	 *  This field alter the counter when reading and writing, to adjust the real number of entries in the file.
	 * For now, only dbtechtree.dat require this, due to its particular structure.
	 * In dbtechtree there is more than one group, and each counter says N, but there are actually N+1 entries (because there's also the "Epoch" entry, which is not counted). */
	public int adjustNumEntries;
	
	/** If true, you can add/remove entries in the file.
	 * Some files have a fixed number of entries you can't or shouldn't change. */
	public boolean supportNumEntriesAlteration;
	
	/** Index of the field which hold the entry name. It's -1 if entries have no name. */
	public int indexName;
	
	/** Index of the field which hold the entry sequence number. It's -1 entries have no sequence number. */
	public int indexSequence;		//index of the entry in the array, which define the Sequence number
	
	/** Index of the field which hold the entry ID. It's -1 if entries have no ID. */
	public int indexID;				//index of the entry in the array, which define the ID
	
	/** Index of the field which hold the number of extra fields (if the entry size can be dynamic).
	 * It's -1 if entries have a fixed size and can't have extra fields.
	 * For now, only dbtechtree.dat require this. */
	public int indexCountExtra;		//index of the entry in the array, which define the number of extra entries
	
	/** This field define the type/size of extra fields, which are all identical (if the entry size can be dynamic).
	 * For now, only dbtechtree.dat require this, due to its particular structure.
	 * It's null if not used. */
	public FieldStruct extraEntry;
	
	/** This array define the description/type/size of all fields of a single entry in the file.
	 * You can expect the sum of the sizes of these entries must match the size of an entry in the file. */
	public FieldStruct[] entries;

	/** Default values used by Unknown/New entries. */
	public Object[] defaultValues;



	DatStructureAOC(String name, String fileName, int alterNumEntries, boolean supportNumAlteration, int indexName, int indexSequence, int indexID, int indexCountExtra){
		this.name = name;
		this.fileName = fileName;
		adjustNumEntries = alterNumEntries;
		supportNumEntriesAlteration = supportNumAlteration;
		this.indexName = indexName;
		this.indexSequence = indexSequence;
		this.indexID = indexID;
		this.indexCountExtra = indexCountExtra;
	}



	
	@Override
	public String getName () {
		return name;
	}

	@Override
	public String getFileName () {
		return fileName;
	}
	
	@Override
	public int getAdjustNumEntries () {
		return adjustNumEntries;
	}
	
	@Override
	public boolean isSupportNumEntriesAlteration () {
		return supportNumEntriesAlteration;
	}
	
	@Override
	public int getIndexName () {
		return indexName;
	}

	@Override
	public int getIndexSequence () {
		return indexSequence;
	}

	@Override
	public int getIndexID () {
		return indexID;
	}
	
	@Override
	public int getIndexCountExtra () {
		return indexCountExtra;
	}

	@Override
	public FieldStruct getExtraEntry () {
		return extraEntry;
	}

	@Override
	public FieldStruct[] getEntries () {
		return entries;
	}

	@Override
	public Object[] getDefaultValues () {
		return defaultValues;
	}
	
	
	
	
	@Override
	public int compareTo (DatStructure datStructure) {
		return compareTo((DatStructureAOC) datStructure);
	}
	
}