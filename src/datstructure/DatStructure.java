package datstructure;


/**
 * This enumeration define all dat files structures, which will be used by the program to parse all the values in the files.
 * If a file structure is wrong, the loading will either fail or corrupted results will show in the editor window.
 * @author MarcoForlini
 *
 */
public enum DatStructure {
	
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
	DB_TECH_TREE("Technologies", "dbtechtree.dat", 1, true, 0, 1, 2, 46),
	DB_UPGRADE ("Upgrades", "dbupgrade.dat", 0, false, 0, 31, 32, -1),
	DB_UNIT_SET ("Unit sets", "dbunitset.dat", 0, false, 0, 1, 2, -1),
	DB_WEAPON_TO_HIT ("Weapons to hit", "dbweapontohit.dat", 0, false, 0, 1, 2, -1),

	;
	
	
	

	static {
		DB_AREA_EFFECT.extraEntry = null;
		DB_AREA_EFFECT.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Effect type", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Morale", Type.INTEGER, 4), new FieldStruct("Heal effect", Type.INTEGER, 4),
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
				FieldStruct.ID, new FieldStruct("Cost increment", Type.INTEGER, 4), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
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
		


		DB_HOTKEY.extraEntry = null;
		DB_HOTKEY.entries = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNCHANGED_INT4, FieldStruct.UNCHANGED_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_HOTKEY.defaultValues = null;
		


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
		


		DB_OBJECT.extraEntry = null;
		DB_OBJECT.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID_FAMILY, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.ID, new FieldStruct("Health", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Min range", Type.FLOAT, 4), new FieldStruct("Max range", Type.FLOAT, 4), new FieldStruct("Line of sight", Type.FLOAT, 4), new FieldStruct("Attack speed", Type.FLOAT, 4),
				new FieldStruct("Area of effect", Type.FLOAT, 4), new FieldStruct("Speed", Type.FLOAT, 4), new FieldStruct("Acceleration/Deceleration", Type.FLOAT, 4), new FieldStruct("Idle turning speed", Type.FLOAT, 4),
				new FieldStruct("Moving turning speed", Type.FLOAT, 4), FieldStruct.UNKNOWN_FLOAT, new FieldStruct("<PROBABLY> Is air unit", Type.INTEGER, 4), new FieldStruct("Flight time", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Attack angle", Type.FLOAT, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.ID_WEAPON_TO_HIT,
				new FieldStruct("Attack", Type.INTEGER, 4), new FieldStruct("Shock armor", Type.INTEGER, 4), new FieldStruct("Arrow armor", Type.INTEGER, 4), new FieldStruct("Pierce armor", Type.INTEGER, 4),
				new FieldStruct("Gun armor", Type.INTEGER, 4), new FieldStruct("Laser armor", Type.INTEGER, 4), new FieldStruct("Missile armor", Type.INTEGER, 4), new FieldStruct("Projectile ID", Type.INTEGER, 4),
				new FieldStruct("Family attack multiplier index", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct("Graphic size", Type.FLOAT, 4), new FieldStruct("Unit type", Type.STRING, 52),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNCHANGED_INT4, FieldStruct.ID_BUTTON, new FieldStruct("Graphic ID", Type.INTEGER, 4),
				new FieldStruct("Build rate", Type.FLOAT, 4), new FieldStruct("Iron gather rate", Type.FLOAT, 4), new FieldStruct("Farm gather rate", Type.FLOAT, 4), new FieldStruct("Gold gather rate", Type.FLOAT, 4),
				new FieldStruct("Stone gather rate", Type.FLOAT, 4), new FieldStruct("Wood gather rate", Type.FLOAT, 4), new FieldStruct("Hunt gather rate", Type.FLOAT, 4), new FieldStruct("Forage gather rate", Type.FLOAT, 4),
				new FieldStruct("Sound ID 1", Type.INTEGER, 4), new FieldStruct("Sound ID 2", Type.INTEGER, 4), new FieldStruct("Sound ID 3", Type.INTEGER, 4), new FieldStruct("Sound ID 4", Type.INTEGER, 4),
				new FieldStruct("Sound ID 5", Type.INTEGER, 4), new FieldStruct("Sound ID 6", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct("Action button 1", Type.INTEGER, 4),
				new FieldStruct("Action button 2", Type.INTEGER, 4), new FieldStruct("Action button 3", Type.INTEGER, 4), new FieldStruct("Action button 4", Type.INTEGER, 4), new FieldStruct("Action button 5", Type.INTEGER, 4),
				new FieldStruct("Action button 6", Type.INTEGER, 4), new FieldStruct("Action button 7", Type.INTEGER, 4), new FieldStruct("Action button 8", Type.INTEGER, 4), new FieldStruct("Action button 9", Type.INTEGER, 4),
				FieldStruct.UNCHANGED_INT4, new FieldStruct("Square occupied", Type.INTEGER, 4), new FieldStruct("Resource type", Type.INTEGER, 4), new FieldStruct("Resource amount", Type.INTEGER, 4),
				new FieldStruct("Always face camera", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct("Rotting time", Type.FLOAT, 4), new FieldStruct("Population count", Type.INTEGER, 4),
				new FieldStruct("Transport capacity", Type.INTEGER, 4), new FieldStruct("Show area effect stat"), new FieldStruct("Show repair stat"), new FieldStruct("Show shock armor stat"),
				new FieldStruct("Show pierce armor stat"), new FieldStruct("Show arrow armor stat"), new FieldStruct("Show laser armor stat"), new FieldStruct("Show gun armor stat"),
				new FieldStruct("Show missile armor stat"), new FieldStruct("Show range", Type.INTEGER, 4), new FieldStruct("Morale bonus", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNUSED_INT1,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, new FieldStruct("Flight altitude", Type.INTEGER, 4), FieldStruct.ID_LANGUAGE,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Health regeneration", Type.INTEGER, 4), new FieldStruct("Can garrison in air transports"),
				new FieldStruct("Can garrison in land transports"), new FieldStruct("Can garrison in sea transports"), FieldStruct.UNUSED_INT1, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Circle selection size", Type.FLOAT, 4), new FieldStruct("Shadow size", Type.FLOAT, 4),  FieldStruct.UNUSED_INT4,
				new FieldStruct("<Some kind of size>", Type.FLOAT, 4), new FieldStruct("Physical size", Type.FLOAT, 4), new FieldStruct("Units can walk above this"), FieldStruct.UNKNOWN_INT1,
				FieldStruct.UNKNOWN_INT1, new FieldStruct("Proiettile trajectory is parabolic",  Type.INTEGER, 1), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				new FieldStruct("Ammo amount", Type.INTEGER, 4), FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Category (Heroes use 27)", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.ID_UPGRADE, new FieldStruct("<Used by units/buildings who can convert>", Type.INTEGER, 4), FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Show in scenari editor"),
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Can be killed with Delete"),
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNUSED_INT1, new FieldStruct("Low health effect ID", Type.INTEGER, 4),
				new FieldStruct("Death effect ID", Type.INTEGER, 4), new FieldStruct("Start of attack ID", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Permanent effect", Type.INTEGER, 4), FieldStruct.UNUSED_INT4, new FieldStruct("Projectile effect", Type.INTEGER, 4),
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, new FieldStruct("<only used by capital ship/yamato>", Type.INTEGER, 4), new FieldStruct("<only used by volcano-projectile and meteor>", Type.INTEGER, 4), new FieldStruct("Attack effect ID", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("<only used by ships>", Type.FLOAT, 4), FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Terrain: Grass, Snow"),
				new FieldStruct("Elevation: Deep water"), new FieldStruct("Terrain: Rock, Stones and Artificial"), FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Terrain: Asphalt"), new FieldStruct("Elevation: cliffs"), new FieldStruct("Elevation: Shallow water"),
				FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Terrain: Sand"), new FieldStruct("Terrain: Space"), FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_INT1, FieldStruct.UNUSED_INT1, FieldStruct.ID_AREA_EFFECT,
				FieldStruct.ID_AREA_EFFECT, FieldStruct.ID_AREA_EFFECT, FieldStruct.ID_AREA_EFFECT, FieldStruct.ID_AREA_EFFECT,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Carry capacity", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNUSED_INT1, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Debris on death", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct("Min stealth radius", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Citizens garrison", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct("Object upgrade to", DatStructure.DB_TECH_TREE), new FieldStruct ("Plane build mode", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Friendly damage", Type.FLOAT, 4), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Num buildable techs", Type.INTEGER, 4),
				FieldStruct.ID_TECH, new FieldStruct("Units can walk above this"), new FieldStruct("Stealth"), FieldStruct.UNKNOWN_BOOL1,
				new FieldStruct("<only used by units with fuel>", Type.INTEGER, 1), FieldStruct.UNKNOWN_INT4, new FieldStruct("Spawn on death", Type.INTEGER, 4), new FieldStruct("Power", Type.INTEGER, 4),
				new FieldStruct("Power recover rate", Type.INTEGER, 4), new FieldStruct("Default stance", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Can attack area"),
				new FieldStruct("Garrison heal rate", Type.INTEGER, 4), FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Can walk through trees", Type.INTEGER, 4),
				new FieldStruct("If 1, it's a melee unit", Type.INTEGER, 4), FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("<Only used by Piramid>", Type.INTEGER, 4),
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH, FieldStruct.OBJECT_BUILD_TECH,
				FieldStruct.OBJECT_BUILD_TECH, FieldStruct.UNKNOWN_INT4
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
		


		DB_STARTING_RESOURCHES.extraEntry = null;
		DB_STARTING_RESOURCHES.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.ID_LANGUAGE,
				new FieldStruct("Starting food", Type.INTEGER, 4), new FieldStruct("Starting wood", Type.INTEGER, 4), new FieldStruct("Starting stone", Type.INTEGER, 4), new FieldStruct("Starting gold", Type.INTEGER, 4),
				new FieldStruct("Starting iron", Type.INTEGER, 4)
		};
		DB_STARTING_RESOURCHES.defaultValues = new Object[]{
				"<New Starting Resourches>", -1, -1, -1, 500, 500, 500, 500, 500
		};
		


		DB_TECH_TREE.extraEntry = FieldStruct.TECH_FROM_OBJECT;
		DB_TECH_TREE.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Starting epoch", Type.INTEGER, 4),
				new FieldStruct("Ending epoch", Type.INTEGER, 4), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Wood cost", Type.INTEGER, 4),
				new FieldStruct("Stone cost", Type.INTEGER, 4), new FieldStruct("<Only Impassable tile object and Invisible capital>", Type.INTEGER, 4), new FieldStruct("Gold cost", Type.INTEGER, 4), FieldStruct.UNUSED_INT4,
				new FieldStruct("Iron cost", Type.INTEGER, 4), new FieldStruct("Food cost", Type.INTEGER, 4),
				new FieldStruct("Build time", Type.INTEGER, 4), new FieldStruct("Unlocked by tech", DB_TECH_TREE),
				new FieldStruct("Unlocked by power", DB_TECH_TREE), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4), FieldStruct.ID_OBJECT,
				FieldStruct.ID_BUTTON, FieldStruct.UNKNOWN_INT4, FieldStruct.ID_LANGUAGE, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("<4 in all Epochs, 0 everywhere else>", Type.INTEGER, 4), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Type of tech", Type.INTEGER, 4),
				FieldStruct.ID_HOTKEY, new FieldStruct("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), new FieldStruct("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Only in scenario"), new FieldStruct("<All powers and power techs use 0>"),
				new FieldStruct("<All powers and power techs use 0>"), FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.TECH_FROM_OBJECT, new FieldStruct("Num of tech builders", Type.INTEGER, 4, false)
		};
		DB_TECH_TREE.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, -1, 0, 0, 15, -1, -1, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, -1, -1, -859045888, 0, 0,
				0, 0, 0, 0, -1, -858993460, -858993460, -858993460,
				-858993460, 0f, 0f, 0f, 0f, 0, 0, 1,
				1, -872415232, 0, 0, -858993460, -1, 0
		};
		


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
		DB_UPGRADE.defaultValues = null;
		


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
		


		DB_WEAPON_TO_HIT.extraEntry = null;
		DB_WEAPON_TO_HIT.entries = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Shock mult", Type.INTEGER, 4),
				new FieldStruct("Gun mult", Type.INTEGER, 4), new FieldStruct("Arrow mult", Type.INTEGER, 4), new FieldStruct("Pierce mult", Type.INTEGER, 4), new FieldStruct("Laser mult", Type.INTEGER, 4),
				new FieldStruct("Missile mult", Type.INTEGER, 4)
		};
		DB_WEAPON_TO_HIT.defaultValues = null;


		
		System.out.println("Check entries definitions:");
		int count;
		for (DatStructure datStructure : DatStructure.values()){
			count = 0;
			for (FieldStruct fieldStruct : datStructure.entries){
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
	public FieldStruct extraEntry;
	
	/** This array define the description/type/size of all fields of a single entry in the file.
	 * You can expect the sum of the sizes of these entries must match the size of an entry in the file. */
	public FieldStruct[] entries;

	/** Default values used by Unknown/New entries. */
	public Object[] defaultValues;



	DatStructure(String name, String fileName, int alterNumEntries, boolean supportNumAlteration, int indexName, int indexSequence, int indexID, int indexCountExtra){
		this.name = name;
		this.fileName = fileName;
		adjustNumEntries = alterNumEntries;
		supportNumEntriesAlteration = supportNumAlteration;
		this.indexName = indexName;
		this.indexSequence = indexSequence;
		this.indexID = indexID;
		this.indexCountExtra = indexCountExtra;
	}
	
}
