package datstructure;


/**
 * This enumeration define all dat files structures, which will be used by the program to parse all the values in the files.
 * If a file structure is wrong, the loading will either fail or corrupted results will show in the editor window.
 * @author MarcoForlini
 *
 */
public enum DatStructureVanilla implements DatStructure {

	DB_AREA_EFFECT("Area effects", "dbareaeffect.dat", 0, true, 0, 1, 2, -1),
	DB_BUTTONS ("Buttons", "dbbuttons.dat", 0, true, 0, 2, 3, -1),
	DB_CALAMITY ("Calamities", "dbcalamity.dat", 0, false, 0, 7, 8, -1),
	DB_CIVILIZATION ("Civilizations", "dbcivilization.dat", 0, false, 0, -1, -1 , -1),
	DB_FAMILY ("Families", "dbfamily.dat", 0, false, 0, 1, 2, -1),
	DB_GAME_VARIANT ("Game variants", "dbgamevariant.dat", 0, false, 0, 1, 2, -1),
	DB_HOTKEY ("Hotkeys", "dbuihotkey.dat", 0, false, 2, 0, 1, -1),
	DB_MUSIC ("Musics", "dbmusic.dat", 0, true, 2, 0, 1, -1),
	DB_OBJECT ("Objects", "dbobjects.dat", 0, true, 0, 1, 5, -1),
	DB_STARTING_RESOURCHES ("Starting resourches", "dbstartingresources.dat", 0, true, 0, 1, 2, -1),
	DB_TECH_TREE("Technologies", "dbtechtree.dat", 1, true, 0, 1, 2, 45),
	DB_UPGRADE ("Upgrades", "dbupgrade.dat", 0, false, 0, 31, 32, -1),
	DB_UNIT_SET ("Unit sets", "dbunitset.dat", 0, false, 0, 1, 2, -1),
	DB_WEAPON_TO_HIT ("Weapons to hit", "dbweapontohit.dat", 0, false, 0, 1, 2, -1),
	
	;



	
	static {
		DB_AREA_EFFECT.extraEntry = null;
		DB_AREA_EFFECT.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID, new FieldStructVanilla("Effect type", Type.INTEGER, 4),
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Morale", Type.INTEGER, 4), new FieldStructVanilla("Heal effect", Type.INTEGER, 4),
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Area size", Type.FLOAT, 4),
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4
		};
		DB_AREA_EFFECT.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0f, 0f, 0f, 0f, 0f, 0, 0, -858993664
		};
		
		
		
		DB_BUTTONS.extraEntry = null;
		DB_BUTTONS.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, new FieldStructVanilla("Texture", Type.STRING, 100), FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID,
				new FieldStructVanilla("<only used by espionage center>", Type.INTEGER, 4), new FieldStructVanilla("<only used by farm and espionage center>", Type.INTEGER, 4), new FieldStructVanilla("Position", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4
		};
		DB_BUTTONS.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, Entry.STRING_UNDEFINED, 0, -1, 0, 0, 0, -1
		};

		
		
		DB_CALAMITY.extraEntry = null;
		DB_CALAMITY.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.SEQ_NUMBER,
				FieldStructVanilla.ID, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4
		};
		DB_CALAMITY.defaultValues = null;
		
		
		
		DB_CIVILIZATION.extraEntry = null;
		DB_CIVILIZATION.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.ID_LANGUAGE,
				FieldStructVanilla.ID, new FieldStructVanilla("Cost increment", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT,
				FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT,
				FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT,
				FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT,
				FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT,
				FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT,
				FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNUSED_FLOAT, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4
		};
		DB_CIVILIZATION.defaultValues = null;
		
		
		
		DB_FAMILY.extraEntry = null;
		DB_FAMILY.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID, FieldStructVanilla.UNKNOWN_INT4,
				new FieldStructVanilla("Frigate", Type.INTEGER, 4), new FieldStructVanilla("Machine gun", Type.INTEGER, 4), new FieldStructVanilla("Galley", Type.INTEGER, 4), new FieldStructVanilla("Tank", Type.INTEGER, 4),
				new FieldStructVanilla("AT Gun", Type.INTEGER, 4), new FieldStructVanilla("Catapult, Bombard", Type.INTEGER, 4), new FieldStructVanilla("AA Tower, Stinger, Flat halftrack", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4,
				new FieldStructVanilla("Sea king", Type.INTEGER, 4), new FieldStructVanilla("Field weapon", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Fighter, Cruiser", Type.INTEGER, 4),
				new FieldStructVanilla("Cavalry spear", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Cavalry gun", Type.INTEGER, 4),
				FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Submarine", Type.INTEGER, 4), new FieldStructVanilla("Ram, Sampson", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4,
				new FieldStructVanilla("Archer, Javelin", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				new FieldStructVanilla("AT Tank", Type.INTEGER, 4), new FieldStructVanilla("Battleship", Type.INTEGER, 4), new FieldStructVanilla("AT Helicopter", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Crossbow", Type.INTEGER, 4), new FieldStructVanilla("Carrier fighter", Type.INTEGER, 4),
				new FieldStructVanilla("Gunship Helicopter", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Tower", Type.INTEGER, 4),
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Sword", Type.INTEGER, 4),
				new FieldStructVanilla("Cavalry sword", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Bazooka", Type.INTEGER, 4),
				new FieldStructVanilla("Spear", Type.INTEGER, 4), new FieldStructVanilla("Cavalry archer", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Fishing boat", Type.INTEGER, 4), new FieldStructVanilla("Anti-Missile Battery", Type.INTEGER, 4),
				new FieldStructVanilla("Capitol ship", Type.INTEGER, 4), new FieldStructVanilla("Space fighter", Type.INTEGER, 4), new FieldStructVanilla("Space corvette", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4,
				new FieldStructVanilla("Space turret", Type.INTEGER, 4), FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4
		};
		DB_FAMILY.defaultValues = null;
		
		
		
		DB_GAME_VARIANT.extraEntry = null;
		DB_GAME_VARIANT.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNCHANGED_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, new FieldStructVanilla("Gather rate multiplier", Type.FLOAT, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.ID_LANGUAGE,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, new FieldStructVanilla("Wonder cost multiplier", Type.FLOAT, 4), FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Available in random maps", Type.INTEGER, 4),
				FieldStructVanilla.UNUSED_INT4
		};
		DB_GAME_VARIANT.defaultValues = null;

		
		
		DB_HOTKEY.extraEntry = null;
		DB_HOTKEY.entries = new FieldStructVanilla[]{
				FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID, FieldStructVanilla.NAME, FieldStructVanilla.UNCHANGED_INT4,
				FieldStructVanilla.UNCHANGED_INT4, FieldStructVanilla.UNCHANGED_INT4, FieldStructVanilla.UNKNOWN_INT4
		};
		DB_HOTKEY.defaultValues = null;

		
		
		DB_MUSIC.extraEntry = null;
		DB_MUSIC.entries = new FieldStructVanilla[]{
				FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID, FieldStructVanilla.NAME, new FieldStructVanilla("Another name", Type.STRING, 56),
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNCHANGED_INT4,
				FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1,
				FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1,
				FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1,
				FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNCHANGED_BOOL1, FieldStructVanilla.UNUSED_INT1
		};
		DB_MUSIC.defaultValues = new Object[]{
				-1, -1, Entry.STRING_UNDEFINED, Entry.STRING_UNDEFINED.substring(0, 56), 0f, 0f, 0f, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 204
		};

		
		
		DB_OBJECT.extraEntry = null;
		DB_OBJECT.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID_FAMILY, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.ID, new FieldStructVanilla("Health", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4,
				new FieldStructVanilla("Min range", Type.FLOAT, 4), new FieldStructVanilla("Max range", Type.FLOAT, 4), new FieldStructVanilla("Line of sight", Type.FLOAT, 4), new FieldStructVanilla("Attack speed", Type.FLOAT, 4),
				new FieldStructVanilla("Area of effect", Type.FLOAT, 4), new FieldStructVanilla("Speed", Type.FLOAT, 4), new FieldStructVanilla("Acceleration/Deceleration", Type.FLOAT, 4), new FieldStructVanilla("Idle turning speed", Type.FLOAT, 4),
				new FieldStructVanilla("Moving turning speed", Type.FLOAT, 4), FieldStructVanilla.UNKNOWN_FLOAT, new FieldStructVanilla("<PROBABLY> Is air unit", Type.INTEGER, 4), new FieldStructVanilla("Flight time", Type.INTEGER, 4),
				FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Attack angle", Type.FLOAT, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.ID_WEAPON_TO_HIT,
				new FieldStructVanilla("Attack", Type.INTEGER, 4), new FieldStructVanilla("Shock armor", Type.INTEGER, 4), new FieldStructVanilla("Arrow armor", Type.INTEGER, 4), new FieldStructVanilla("Pierce armor", Type.INTEGER, 4),
				new FieldStructVanilla("Gun armor", Type.INTEGER, 4), new FieldStructVanilla("Laser armor", Type.INTEGER, 4), new FieldStructVanilla("Missile armor", Type.INTEGER, 4), new FieldStructVanilla("Projectile ID", Type.INTEGER, 4),
				new FieldStructVanilla("Family attack multiplier index", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Graphic size", Type.FLOAT, 4), new FieldStructVanilla("Unit type", Type.STRING, 52),
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNCHANGED_INT4, FieldStructVanilla.ID_BUTTON, new FieldStructVanilla("Graphic ID", Type.INTEGER, 4),
				new FieldStructVanilla("Build rate", Type.FLOAT, 4), new FieldStructVanilla("Iron gather rate", Type.FLOAT, 4), new FieldStructVanilla("Farm gather rate", Type.FLOAT, 4), new FieldStructVanilla("Gold gather rate", Type.FLOAT, 4),
				new FieldStructVanilla("Stone gather rate", Type.FLOAT, 4), new FieldStructVanilla("Wood gather rate", Type.FLOAT, 4), new FieldStructVanilla("Hunt gather rate", Type.FLOAT, 4), new FieldStructVanilla("Forage gather rate", Type.FLOAT, 4),
				new FieldStructVanilla("Sound ID 1", Type.INTEGER, 4), new FieldStructVanilla("Sound ID 2", Type.INTEGER, 4), new FieldStructVanilla("Sound ID 3", Type.INTEGER, 4), new FieldStructVanilla("Sound ID 4", Type.INTEGER, 4),
				new FieldStructVanilla("Sound ID 5", Type.INTEGER, 4), new FieldStructVanilla("Sound ID 6", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Action button 1", Type.INTEGER, 4),
				new FieldStructVanilla("Action button 2", Type.INTEGER, 4), new FieldStructVanilla("Action button 3", Type.INTEGER, 4), new FieldStructVanilla("Action button 4", Type.INTEGER, 4), new FieldStructVanilla("Action button 5", Type.INTEGER, 4),
				new FieldStructVanilla("Action button 6", Type.INTEGER, 4), new FieldStructVanilla("Action button 7", Type.INTEGER, 4), new FieldStructVanilla("Action button 8", Type.INTEGER, 4), new FieldStructVanilla("Action button 9", Type.INTEGER, 4),
				FieldStructVanilla.UNCHANGED_INT4, new FieldStructVanilla("Square occupied", Type.INTEGER, 4), new FieldStructVanilla("Resource type", Type.INTEGER, 4), new FieldStructVanilla("Resource amount", Type.INTEGER, 4),
				new FieldStructVanilla("Always face camera", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Rotting time", Type.FLOAT, 4), new FieldStructVanilla("Population count", Type.INTEGER, 4),
				new FieldStructVanilla("Transport capacity", Type.INTEGER, 4), new FieldStructVanilla("Show area effect stat"), new FieldStructVanilla("Show repair stat"), new FieldStructVanilla("Show shock armor stat"),
				new FieldStructVanilla("Show pierce armor stat"), new FieldStructVanilla("Show arrow armor stat"), new FieldStructVanilla("Show laser armor stat"), new FieldStructVanilla("Show gun armor stat"),
				new FieldStructVanilla("Show missile armor stat"), new FieldStructVanilla("Show range", Type.INTEGER, 4), new FieldStructVanilla("Morale bonus", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT1, FieldStructVanilla.UNKNOWN_INT1, FieldStructVanilla.UNKNOWN_INT1, FieldStructVanilla.UNUSED_INT1,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Flight altitude", Type.INTEGER, 4), FieldStructVanilla.ID_LANGUAGE,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Health regeneration", Type.INTEGER, 4), new FieldStructVanilla("Can garrison in air transports"),
				new FieldStructVanilla("Can garrison in land transports"), new FieldStructVanilla("Can garrison in sea transports"), FieldStructVanilla.UNUSED_INT1, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Circle selection size", Type.FLOAT, 4), new FieldStructVanilla("Shadow size", Type.FLOAT, 4),  FieldStructVanilla.UNUSED_INT4,
				new FieldStructVanilla("<Some kind of size>", Type.FLOAT, 4), new FieldStructVanilla("Physical size", Type.FLOAT, 4), new FieldStructVanilla("Units can walk above this"), FieldStructVanilla.UNKNOWN_INT1,
				FieldStructVanilla.UNKNOWN_INT1, new FieldStructVanilla("Proiettile trajectory is parabolic",  Type.INTEGER, 1), FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				new FieldStructVanilla("Ammo amount", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1,
				FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Category (Heroes use 27)", Type.INTEGER, 4),
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.ID_UPGRADE, new FieldStructVanilla("<Used by units/buildings who can convert>", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_FLOAT, new FieldStructVanilla("Show in scenari editor"),
				FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1, new FieldStructVanilla("Can be killed with Delete"),
				FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_INT1, FieldStructVanilla.UNUSED_INT1, new FieldStructVanilla("Low health effect ID", Type.INTEGER, 4),
				new FieldStructVanilla("Death effect ID", Type.INTEGER, 4), new FieldStructVanilla("Start of attack ID", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Permanent effect", Type.INTEGER, 4), FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Projectile effect", Type.INTEGER, 4),
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("<only used by capital ship/yamato>", Type.INTEGER, 4), new FieldStructVanilla("<only used by volcano-projectile and meteor>", Type.INTEGER, 4), new FieldStructVanilla("Attack effect ID", Type.INTEGER, 4),
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("<only used by ships>", Type.FLOAT, 4), FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Terrain: Grass, Snow"),
				new FieldStructVanilla("Elevation: Deep water"), new FieldStructVanilla("Terrain: Rock, Stones and Artificial"), FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1,
				FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1,
				FieldStructVanilla.UNKNOWN_BOOL1, new FieldStructVanilla("Terrain: Asphalt"), new FieldStructVanilla("Elevation: cliffs"), new FieldStructVanilla("Elevation: Shallow water"),
				FieldStructVanilla.UNKNOWN_BOOL1, new FieldStructVanilla("Terrain: Sand"), new FieldStructVanilla("Terrain: Space"), FieldStructVanilla.UNKNOWN_BOOL1,
				FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNUSED_INT1, FieldStructVanilla.UNUSED_INT1, FieldStructVanilla.ID_AREA_EFFECT,
				FieldStructVanilla.ID_AREA_EFFECT, FieldStructVanilla.ID_AREA_EFFECT, FieldStructVanilla.ID_AREA_EFFECT, FieldStructVanilla.ID_AREA_EFFECT,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Carry capacity", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNUSED_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1,
				FieldStructVanilla.UNUSED_INT1, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				new FieldStructVanilla("Debris on death", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Min stealth radius", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4,
				new FieldStructVanilla("Citizens garrison", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Object upgrade to", DatStructureVanilla.DB_TECH_TREE), new FieldStructVanilla ("Plane build mode", Type.INTEGER, 4),
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, new FieldStructVanilla("Friendly damage", Type.FLOAT, 4), FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Num buildable techs", Type.INTEGER, 4),
				FieldStructVanilla.ID_TECH, new FieldStructVanilla("Units can walk above this"), new FieldStructVanilla("Stealth"), FieldStructVanilla.UNKNOWN_BOOL1,
				new FieldStructVanilla("<only used by units with fuel>", Type.INTEGER, 1), FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Spawn on death", Type.INTEGER, 4), new FieldStructVanilla("Power", Type.INTEGER, 4),
				new FieldStructVanilla("Power recover rate", Type.INTEGER, 4), new FieldStructVanilla("Default stance", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_BOOL1, FieldStructVanilla.UNUSED_BOOL1, FieldStructVanilla.UNKNOWN_BOOL1, new FieldStructVanilla("Can attack area"),
				new FieldStructVanilla("Garrison heal rate", Type.INTEGER, 4), FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_FLOAT, new FieldStructVanilla("Can walk through trees", Type.INTEGER, 4),
				new FieldStructVanilla("If 1, it's a melee unit", Type.INTEGER, 4), FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("<Only used by Piramid>", Type.INTEGER, 4),
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH, FieldStructVanilla.OBJECT_BUILD_TECH,
				FieldStructVanilla.OBJECT_BUILD_TECH,
		};
		DB_OBJECT.defaultValues = new Object[]{
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
				-858993460, -858993460, -858993460, -1, -1, -1,
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
				-1, -1, -1, -1, -1
		};

		
		
		DB_STARTING_RESOURCHES.extraEntry = null;
		DB_STARTING_RESOURCHES.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID, FieldStructVanilla.ID_LANGUAGE,
				new FieldStructVanilla("Starting food", Type.INTEGER, 4), new FieldStructVanilla("Starting wood", Type.INTEGER, 4), new FieldStructVanilla("Starting stone", Type.INTEGER, 4), new FieldStructVanilla("Starting gold", Type.INTEGER, 4),
				new FieldStructVanilla("Starting iron", Type.INTEGER, 4)
		};
		DB_STARTING_RESOURCHES.defaultValues = new Object[]{
				"<New Starting Resourches>", -1, -1, -1, 500, 500, 500, 500, 500
		};

		
		
		DB_TECH_TREE.extraEntry = FieldStructVanilla.TECH_FROM_OBJECT;
		DB_TECH_TREE.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID, new FieldStructVanilla("Starting epoch", Type.INTEGER, 4),
				new FieldStructVanilla("Ending epoch", Type.INTEGER, 4), FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, new FieldStructVanilla("Wood cost", Type.INTEGER, 4),
				new FieldStructVanilla("Stone cost", Type.INTEGER, 4), new FieldStructVanilla("<Only Impassable tile object and Invisible capital>", Type.INTEGER, 4), new FieldStructVanilla("Gold cost", Type.INTEGER, 4), FieldStructVanilla.UNUSED_INT4,
				new FieldStructVanilla("Iron cost", Type.INTEGER, 4), new FieldStructVanilla("Food cost", Type.INTEGER, 4),
				new FieldStructVanilla("Build time", Type.INTEGER, 4), new FieldStructVanilla("Unlocked by tech", DB_TECH_TREE),
				new FieldStructVanilla("Unlocked by power", DB_TECH_TREE), new FieldStructVanilla("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4), new FieldStructVanilla("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4), FieldStructVanilla.ID_OBJECT,
				FieldStructVanilla.ID_BUTTON, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.ID_LANGUAGE, FieldStructVanilla.UNKNOWN_INT4,
				new FieldStructVanilla("<4 in all Epochs, 0 everywhere else>", Type.INTEGER, 4), FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Type of tech", Type.INTEGER, 4),
				FieldStructVanilla.ID_HOTKEY, new FieldStructVanilla("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), new FieldStructVanilla("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), FieldStructVanilla.UNUSED_INT4,
				FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_FLOAT,
				FieldStructVanilla.UNKNOWN_FLOAT, FieldStructVanilla.UNKNOWN_BOOL1, new FieldStructVanilla("Only in scenario"), new FieldStructVanilla("<All powers and power techs use 0>"),
				new FieldStructVanilla("<All powers and power techs use 0>"), FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.TECH_FROM_OBJECT, new FieldStructVanilla("Num of tech builders", Type.INTEGER, 4, false)
		};
		DB_TECH_TREE.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, -1, 0, 0, 15, -1, -1, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, -1, -1, -859045888, 0, 0,
				0, 0, 0, 0, -1, -858993460, -858993460, -858993460,
				-858993460, 0f, 0f, 0f, 0f, 0, 0, 1,
				1, -872415232, 0, -858993460, -1, 0
		};

		
		
		DB_UPGRADE.extraEntry = null;
		DB_UPGRADE.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, new FieldStructVanilla("Attack mult", Type.FLOAT, 4), new FieldStructVanilla("Health mult", Type.FLOAT, 4), new FieldStructVanilla("Speed mult", Type.FLOAT, 4),
				new FieldStructVanilla("Range mult", Type.FLOAT, 4), new FieldStructVanilla("Shock armor mult", Type.FLOAT, 4), new FieldStructVanilla("Arrow armor mult", Type.FLOAT, 4), new FieldStructVanilla("Pierce armor mult", Type.FLOAT, 4),
				new FieldStructVanilla("Gun armor mult", Type.FLOAT, 4), new FieldStructVanilla("Laser armor mult", Type.FLOAT, 4), new FieldStructVanilla("Missile armor mult", Type.FLOAT, 4), new FieldStructVanilla("Fuel/Power mult", Type.FLOAT, 4),
				FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Area mult", Type.FLOAT, 4), FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Attack cost mult", Type.FLOAT, 4),
				new FieldStructVanilla("Health cost mult", Type.FLOAT, 4), new FieldStructVanilla("Speed cost mult", Type.FLOAT, 4), new FieldStructVanilla("Range cost mult", Type.FLOAT, 4), new FieldStructVanilla("Shock armor cost mult", Type.FLOAT, 4),
				new FieldStructVanilla("Arrow armor cost mult", Type.FLOAT, 4), new FieldStructVanilla("Pierce armor cost mult", Type.FLOAT, 4), new FieldStructVanilla("Gun armor cost mult", Type.FLOAT, 4), new FieldStructVanilla("Laser armor cost mult", Type.FLOAT, 4),
				new FieldStructVanilla("Missile armor cost mult", Type.FLOAT, 4), new FieldStructVanilla("Fuel/Power cost mult", Type.FLOAT, 4), FieldStructVanilla.UNUSED_INT4, FieldStructVanilla.UNKNOWN_FLOAT,
				new FieldStructVanilla("Area cost mult", Type.FLOAT, 4), FieldStructVanilla.UNUSED_INT4, new FieldStructVanilla("Max upgrades per stat", Type.INTEGER, 4), FieldStructVanilla.SEQ_NUMBER,
				FieldStructVanilla.ID, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4
		};
		DB_UPGRADE.defaultValues = null;

		
		
		DB_UNIT_SET.extraEntry = null;
		DB_UNIT_SET.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4,
				FieldStructVanilla.UNKNOWN_INT4, FieldStructVanilla.UNKNOWN_INT4
		};
		DB_UNIT_SET.defaultValues = null;

		
		
		DB_WEAPON_TO_HIT.extraEntry = null;
		DB_WEAPON_TO_HIT.entries = new FieldStructVanilla[]{
				FieldStructVanilla.NAME, FieldStructVanilla.SEQ_NUMBER, FieldStructVanilla.ID, new FieldStructVanilla("Shock mult", Type.INTEGER, 4),
				new FieldStructVanilla("Gun mult", Type.INTEGER, 4), new FieldStructVanilla("Arrow mult", Type.INTEGER, 4), new FieldStructVanilla("Pierce mult", Type.INTEGER, 4), new FieldStructVanilla("Laser mult", Type.INTEGER, 4),
				new FieldStructVanilla("Missile mult", Type.INTEGER, 4)
		};
		DB_WEAPON_TO_HIT.defaultValues = null;
		
		

		System.out.println("Check entries definitions:");
		int count;
		for (DatStructureVanilla datStructure : DatStructureVanilla.values()){
			count = 0;
			for (FieldStructVanilla fieldStruct : datStructure.entries){
				count += fieldStruct.size;
			}
			System.out.println('\t' + datStructure.fileName + ':' + ' ' + count + "  >  fields: " + datStructure.entries.length + "  |  defaults: " + (datStructure.defaultValues != null ? datStructure.defaultValues.length : "null"));
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
	public FieldStructVanilla extraEntry;

	/** This array define the description/type/size of all fields of a single entry in the file.
	 * You can expect the sum of the sizes of these entries must match the size of an entry in the file. */
	public FieldStructVanilla[] entries;
	
	/** Default values used by Unknown/New entries. */
	public Object[] defaultValues;
	
	
	
	DatStructureVanilla(String name, String fileName, int alterNumEntries, boolean supportNumAlteration, int indexName, int indexSequence, int indexID, int indexCountExtra){
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
	public FieldStructVanilla getExtraEntry () {
		return extraEntry;
	}
	
	@Override
	public FieldStructVanilla[] getEntries () {
		return entries;
	}
	
	@Override
	public Object[] getDefaultValues () {
		return defaultValues;
	}

	@Override
	public int compareTo (DatStructure datStructure) {
		return compareTo((DatStructureVanilla) datStructure);
	}

}
