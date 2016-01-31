package datstructure;


/**
 * This enumeration define all dat files structures, which will be used by the program to parse all the values in the files.
 * If a file structure is wrong, the loading will either fail or corrupted results will show in the editor window.
 * @author MarcoForlini
 *
 */
public enum DatStructureAOC implements DatStructure {
	
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
		DB_AREA_EFFECT.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID, new FieldStructAOC("Effect type", Type.INTEGER, 4),
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Morale", Type.INTEGER, 4), new FieldStructAOC("Heal effect", Type.INTEGER, 4),
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Area size", Type.FLOAT, 4),
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4
		};
		DB_AREA_EFFECT.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0f, 0f, 0f, 0f, 0f, 0, 0, -858993664
		};



		DB_BUTTONS.extraEntry = null;
		DB_BUTTONS.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, new FieldStructAOC("Texture", Type.STRING, 100), FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID,
				new FieldStructAOC("<only used by espionage center>", Type.INTEGER, 4), new FieldStructAOC("<only used by farm and espionage center>", Type.INTEGER, 4), new FieldStructAOC("Position", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4
		};
		DB_BUTTONS.defaultValues = new Object[]{
				Entry.STRING_UNDEFINED, Entry.STRING_UNDEFINED, 0, -1, 0, 0, 0, -1
		};
		


		DB_CALAMITY.extraEntry = null;
		DB_CALAMITY.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.SEQ_NUMBER,
				FieldStructAOC.ID, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4
		};
		DB_CALAMITY.defaultValues = null;



		DB_CIVILIZATION.extraEntry = null;
		DB_CIVILIZATION.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.ID_LANGUAGE,
				FieldStructAOC.ID, new FieldStructAOC("Cost increment", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT, FieldStructAOC.UNUSED_FLOAT,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4
		};
		DB_CIVILIZATION.defaultValues = null;



		DB_FAMILY.extraEntry = null;
		DB_FAMILY.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID, FieldStructAOC.UNKNOWN_INT4,
				new FieldStructAOC("Frigate", Type.INTEGER, 4), new FieldStructAOC("Machine gun", Type.INTEGER, 4), new FieldStructAOC("Galley", Type.INTEGER, 4), new FieldStructAOC("Tank", Type.INTEGER, 4),
				new FieldStructAOC("AT Gun", Type.INTEGER, 4), new FieldStructAOC("Catapult, Bombard", Type.INTEGER, 4), new FieldStructAOC("AA Tower, Stinger, Flat halftrack", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4,
				new FieldStructAOC("Sea king", Type.INTEGER, 4), new FieldStructAOC("Field weapon", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Fighter, Cruiser", Type.INTEGER, 4),
				new FieldStructAOC("Cavalry spear", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Cavalry gun", Type.INTEGER, 4),
				FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Submarine", Type.INTEGER, 4), new FieldStructAOC("Ram, Sampson", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4,
				new FieldStructAOC("Archer, Javelin", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				new FieldStructAOC("AT Tank", Type.INTEGER, 4), new FieldStructAOC("Battleship", Type.INTEGER, 4), new FieldStructAOC("AT Helicopter", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Crossbow", Type.INTEGER, 4), new FieldStructAOC("Carrier fighter", Type.INTEGER, 4),
				new FieldStructAOC("Gunship Helicopter", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Tower", Type.INTEGER, 4),
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Sword", Type.INTEGER, 4),
				new FieldStructAOC("Cavalry sword", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Bazooka", Type.INTEGER, 4),
				new FieldStructAOC("Spear", Type.INTEGER, 4), new FieldStructAOC("Cavalry archer", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Fishing boat", Type.INTEGER, 4), new FieldStructAOC("Anti-Missile Battery", Type.INTEGER, 4),
				new FieldStructAOC("Capitol ship", Type.INTEGER, 4), new FieldStructAOC("Space fighter", Type.INTEGER, 4), new FieldStructAOC("Space corvette", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4,
				new FieldStructAOC("Space turret", Type.INTEGER, 4), FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4
		};
		DB_FAMILY.defaultValues = null;



		DB_GAME_VARIANT.extraEntry = null;
		DB_GAME_VARIANT.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNCHANGED_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, new FieldStructAOC("Gather rate multiplier", Type.FLOAT, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.ID_LANGUAGE,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, new FieldStructAOC("Wonder cost multiplier", Type.FLOAT, 4), FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Available in random maps", Type.INTEGER, 4),
				FieldStructAOC.UNUSED_INT4
		};
		DB_GAME_VARIANT.defaultValues = null;
		


		DB_HOTKEY.extraEntry = null;
		DB_HOTKEY.entries = new FieldStructAOC[]{
				FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID, FieldStructAOC.NAME, FieldStructAOC.UNCHANGED_INT4,
				FieldStructAOC.UNCHANGED_INT4, FieldStructAOC.UNCHANGED_INT4, FieldStructAOC.UNKNOWN_INT4
		};
		DB_HOTKEY.defaultValues = null;
		


		DB_MUSIC.extraEntry = null;
		DB_MUSIC.entries = new FieldStructAOC[]{
				FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID, FieldStructAOC.NAME, new FieldStructAOC("Another name", Type.STRING, 56),
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNCHANGED_INT4,
				FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1,
				FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1,
				FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1,
				FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNCHANGED_BOOL1, FieldStructAOC.UNUSED_INT1
		};
		DB_MUSIC.defaultValues = new Object[]{
				-1, -1, Entry.STRING_UNDEFINED, Entry.STRING_UNDEFINED.substring(0, 56), 0f, 0f, 0f, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 204
		};
		


		DB_OBJECT.extraEntry = null;
		DB_OBJECT.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID_FAMILY, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.ID, new FieldStructAOC("Health", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4,
				new FieldStructAOC("Min range", Type.FLOAT, 4), new FieldStructAOC("Max range", Type.FLOAT, 4), new FieldStructAOC("Line of sight", Type.FLOAT, 4), new FieldStructAOC("Attack speed", Type.FLOAT, 4),
				new FieldStructAOC("Area of effect", Type.FLOAT, 4), new FieldStructAOC("Speed", Type.FLOAT, 4), new FieldStructAOC("Acceleration/Deceleration", Type.FLOAT, 4), new FieldStructAOC("Idle turning speed", Type.FLOAT, 4),
				new FieldStructAOC("Moving turning speed", Type.FLOAT, 4), FieldStructAOC.UNKNOWN_FLOAT, new FieldStructAOC("<PROBABLY> Is air unit", Type.INTEGER, 4), new FieldStructAOC("Flight time", Type.INTEGER, 4),
				FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Attack angle", Type.FLOAT, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.ID_WEAPON_TO_HIT,
				new FieldStructAOC("Attack", Type.INTEGER, 4), new FieldStructAOC("Shock armor", Type.INTEGER, 4), new FieldStructAOC("Arrow armor", Type.INTEGER, 4), new FieldStructAOC("Pierce armor", Type.INTEGER, 4),
				new FieldStructAOC("Gun armor", Type.INTEGER, 4), new FieldStructAOC("Laser armor", Type.INTEGER, 4), new FieldStructAOC("Missile armor", Type.INTEGER, 4), new FieldStructAOC("Projectile ID", Type.INTEGER, 4),
				new FieldStructAOC("Family attack multiplier index", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Graphic size", Type.FLOAT, 4), new FieldStructAOC("Unit type", Type.STRING, 52),
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNCHANGED_INT4, FieldStructAOC.ID_BUTTON, new FieldStructAOC("Graphic ID", Type.INTEGER, 4),
				new FieldStructAOC("Build rate", Type.FLOAT, 4), new FieldStructAOC("Iron gather rate", Type.FLOAT, 4), new FieldStructAOC("Farm gather rate", Type.FLOAT, 4), new FieldStructAOC("Gold gather rate", Type.FLOAT, 4),
				new FieldStructAOC("Stone gather rate", Type.FLOAT, 4), new FieldStructAOC("Wood gather rate", Type.FLOAT, 4), new FieldStructAOC("Hunt gather rate", Type.FLOAT, 4), new FieldStructAOC("Forage gather rate", Type.FLOAT, 4),
				new FieldStructAOC("Sound ID 1", Type.INTEGER, 4), new FieldStructAOC("Sound ID 2", Type.INTEGER, 4), new FieldStructAOC("Sound ID 3", Type.INTEGER, 4), new FieldStructAOC("Sound ID 4", Type.INTEGER, 4),
				new FieldStructAOC("Sound ID 5", Type.INTEGER, 4), new FieldStructAOC("Sound ID 6", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Action button 1", Type.INTEGER, 4),
				new FieldStructAOC("Action button 2", Type.INTEGER, 4), new FieldStructAOC("Action button 3", Type.INTEGER, 4), new FieldStructAOC("Action button 4", Type.INTEGER, 4), new FieldStructAOC("Action button 5", Type.INTEGER, 4),
				new FieldStructAOC("Action button 6", Type.INTEGER, 4), new FieldStructAOC("Action button 7", Type.INTEGER, 4), new FieldStructAOC("Action button 8", Type.INTEGER, 4), new FieldStructAOC("Action button 9", Type.INTEGER, 4),
				FieldStructAOC.UNCHANGED_INT4, new FieldStructAOC("Square occupied", Type.INTEGER, 4), new FieldStructAOC("Resource type", Type.INTEGER, 4), new FieldStructAOC("Resource amount", Type.INTEGER, 4),
				new FieldStructAOC("Always face camera", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Rotting time", Type.FLOAT, 4), new FieldStructAOC("Population count", Type.INTEGER, 4),
				new FieldStructAOC("Transport capacity", Type.INTEGER, 4), new FieldStructAOC("Show area effect stat"), new FieldStructAOC("Show repair stat"), new FieldStructAOC("Show shock armor stat"),
				new FieldStructAOC("Show pierce armor stat"), new FieldStructAOC("Show arrow armor stat"), new FieldStructAOC("Show laser armor stat"), new FieldStructAOC("Show gun armor stat"),
				new FieldStructAOC("Show missile armor stat"), new FieldStructAOC("Show range", Type.INTEGER, 4), new FieldStructAOC("Morale bonus", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT1, FieldStructAOC.UNKNOWN_INT1, FieldStructAOC.UNKNOWN_INT1, FieldStructAOC.UNUSED_INT1,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Flight altitude", Type.INTEGER, 4), FieldStructAOC.ID_LANGUAGE,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Health regeneration", Type.INTEGER, 4), new FieldStructAOC("Can garrison in air transports"),
				new FieldStructAOC("Can garrison in land transports"), new FieldStructAOC("Can garrison in sea transports"), FieldStructAOC.UNUSED_INT1, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Circle selection size", Type.FLOAT, 4), new FieldStructAOC("Shadow size", Type.FLOAT, 4),  FieldStructAOC.UNUSED_INT4,
				new FieldStructAOC("<Some kind of size>", Type.FLOAT, 4), new FieldStructAOC("Physical size", Type.FLOAT, 4), new FieldStructAOC("Units can walk above this"), FieldStructAOC.UNKNOWN_INT1,
				FieldStructAOC.UNKNOWN_INT1, new FieldStructAOC("Proiettile trajectory is parabolic",  Type.INTEGER, 1), FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				new FieldStructAOC("Ammo amount", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_BOOL1,
				FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Category (Heroes use 27)", Type.INTEGER, 4),
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.ID_UPGRADE, new FieldStructAOC("<Used by units/buildings who can convert>", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_FLOAT, new FieldStructAOC("Show in scenari editor"),
				FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_BOOL1, new FieldStructAOC("Can be killed with Delete"),
				FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_INT1, FieldStructAOC.UNUSED_INT1, new FieldStructAOC("Low health effect ID", Type.INTEGER, 4),
				new FieldStructAOC("Death effect ID", Type.INTEGER, 4), new FieldStructAOC("Start of attack ID", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Permanent effect", Type.INTEGER, 4), FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Projectile effect", Type.INTEGER, 4),
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, new FieldStructAOC("<only used by capital ship/yamato>", Type.INTEGER, 4), new FieldStructAOC("<only used by volcano-projectile and meteor>", Type.INTEGER, 4), new FieldStructAOC("Attack effect ID", Type.INTEGER, 4),
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("<only used by ships>", Type.FLOAT, 4), FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Terrain: Grass, Snow"),
				new FieldStructAOC("Elevation: Deep water"), new FieldStructAOC("Terrain: Rock, Stones and Artificial"), FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_BOOL1,
				FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNKNOWN_BOOL1,
				FieldStructAOC.UNKNOWN_BOOL1, new FieldStructAOC("Terrain: Asphalt"), new FieldStructAOC("Elevation: cliffs"), new FieldStructAOC("Elevation: Shallow water"),
				FieldStructAOC.UNKNOWN_BOOL1, new FieldStructAOC("Terrain: Sand"), new FieldStructAOC("Terrain: Space"), FieldStructAOC.UNKNOWN_BOOL1,
				FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNUSED_INT1, FieldStructAOC.UNUSED_INT1, FieldStructAOC.ID_AREA_EFFECT,
				FieldStructAOC.ID_AREA_EFFECT, FieldStructAOC.ID_AREA_EFFECT, FieldStructAOC.ID_AREA_EFFECT, FieldStructAOC.ID_AREA_EFFECT,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Carry capacity", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNUSED_BOOL1, FieldStructAOC.UNKNOWN_BOOL1,
				FieldStructAOC.UNUSED_INT1, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				new FieldStructAOC("Debris on death", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Min stealth radius", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4,
				new FieldStructAOC("Citizens garrison", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Object upgrade to", DatStructureAOC.DB_TECH_TREE), new FieldStructAOC ("Plane build mode", Type.INTEGER, 4),
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, new FieldStructAOC("Friendly damage", Type.FLOAT, 4), FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Num buildable techs", Type.INTEGER, 4),
				FieldStructAOC.ID_TECH, new FieldStructAOC("Units can walk above this"), new FieldStructAOC("Stealth"), FieldStructAOC.UNKNOWN_BOOL1,
				new FieldStructAOC("<only used by units with fuel>", Type.INTEGER, 1), FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Spawn on death", Type.INTEGER, 4), new FieldStructAOC("Power", Type.INTEGER, 4),
				new FieldStructAOC("Power recover rate", Type.INTEGER, 4), new FieldStructAOC("Default stance", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_BOOL1, FieldStructAOC.UNUSED_BOOL1, FieldStructAOC.UNKNOWN_BOOL1, new FieldStructAOC("Can attack area"),
				new FieldStructAOC("Garrison heal rate", Type.INTEGER, 4), FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_FLOAT, new FieldStructAOC("Can walk through trees", Type.INTEGER, 4),
				new FieldStructAOC("If 1, it's a melee unit", Type.INTEGER, 4), FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, new FieldStructAOC("<Only used by Piramid>", Type.INTEGER, 4),
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.OBJECT_BUILD_TECH,
				FieldStructAOC.OBJECT_BUILD_TECH, FieldStructAOC.UNKNOWN_INT4
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
		DB_STARTING_RESOURCHES.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID, FieldStructAOC.ID_LANGUAGE,
				new FieldStructAOC("Starting food", Type.INTEGER, 4), new FieldStructAOC("Starting wood", Type.INTEGER, 4), new FieldStructAOC("Starting stone", Type.INTEGER, 4), new FieldStructAOC("Starting gold", Type.INTEGER, 4),
				new FieldStructAOC("Starting iron", Type.INTEGER, 4)
		};
		DB_STARTING_RESOURCHES.defaultValues = new Object[]{
				"<New Starting Resourches>", -1, -1, -1, 500, 500, 500, 500, 500
		};
		


		DB_TECH_TREE.extraEntry = FieldStructAOC.TECH_FROM_OBJECT;
		DB_TECH_TREE.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID, new FieldStructAOC("Starting epoch", Type.INTEGER, 4),
				new FieldStructAOC("Ending epoch", Type.INTEGER, 4), FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, new FieldStructAOC("Wood cost", Type.INTEGER, 4),
				new FieldStructAOC("Stone cost", Type.INTEGER, 4), new FieldStructAOC("<Only Impassable tile object and Invisible capital>", Type.INTEGER, 4), new FieldStructAOC("Gold cost", Type.INTEGER, 4), FieldStructAOC.UNUSED_INT4,
				new FieldStructAOC("Iron cost", Type.INTEGER, 4), new FieldStructAOC("Food cost", Type.INTEGER, 4),
				new FieldStructAOC("Build time", Type.INTEGER, 4), new FieldStructAOC("Unlocked by tech", DB_TECH_TREE),
				new FieldStructAOC("Unlocked by power", DB_TECH_TREE), new FieldStructAOC("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4), new FieldStructAOC("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER, 4), FieldStructAOC.ID_OBJECT,
				FieldStructAOC.ID_BUTTON, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.ID_LANGUAGE, FieldStructAOC.UNKNOWN_INT4,
				new FieldStructAOC("<4 in all Epochs, 0 everywhere else>", Type.INTEGER, 4), FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Type of tech", Type.INTEGER, 4),
				FieldStructAOC.ID_HOTKEY, new FieldStructAOC("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), new FieldStructAOC("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), FieldStructAOC.UNUSED_INT4,
				FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_FLOAT,
				FieldStructAOC.UNKNOWN_FLOAT, FieldStructAOC.UNKNOWN_BOOL1, new FieldStructAOC("Only in scenario"), new FieldStructAOC("<All powers and power techs use 0>"),
				new FieldStructAOC("<All powers and power techs use 0>"), FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.TECH_FROM_OBJECT, new FieldStructAOC("Num of tech builders", Type.INTEGER, 4, false)
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
		DB_UPGRADE.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, new FieldStructAOC("Attack mult", Type.FLOAT, 4), new FieldStructAOC("Health mult", Type.FLOAT, 4), new FieldStructAOC("Speed mult", Type.FLOAT, 4),
				new FieldStructAOC("Range mult", Type.FLOAT, 4), new FieldStructAOC("Shock armor mult", Type.FLOAT, 4), new FieldStructAOC("Arrow armor mult", Type.FLOAT, 4), new FieldStructAOC("Pierce armor mult", Type.FLOAT, 4),
				new FieldStructAOC("Gun armor mult", Type.FLOAT, 4), new FieldStructAOC("Laser armor mult", Type.FLOAT, 4), new FieldStructAOC("Missile armor mult", Type.FLOAT, 4), new FieldStructAOC("Fuel/Power mult", Type.FLOAT, 4),
				FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Area mult", Type.FLOAT, 4), FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Attack cost mult", Type.FLOAT, 4),
				new FieldStructAOC("Health cost mult", Type.FLOAT, 4), new FieldStructAOC("Speed cost mult", Type.FLOAT, 4), new FieldStructAOC("Range cost mult", Type.FLOAT, 4), new FieldStructAOC("Shock armor cost mult", Type.FLOAT, 4),
				new FieldStructAOC("Arrow armor cost mult", Type.FLOAT, 4), new FieldStructAOC("Pierce armor cost mult", Type.FLOAT, 4), new FieldStructAOC("Gun armor cost mult", Type.FLOAT, 4), new FieldStructAOC("Laser armor cost mult", Type.FLOAT, 4),
				new FieldStructAOC("Missile armor cost mult", Type.FLOAT, 4), new FieldStructAOC("Fuel/Power cost mult", Type.FLOAT, 4), FieldStructAOC.UNUSED_INT4, FieldStructAOC.UNKNOWN_FLOAT,
				new FieldStructAOC("Area cost mult", Type.FLOAT, 4), FieldStructAOC.UNUSED_INT4, new FieldStructAOC("Max upgrades per stat", Type.INTEGER, 4), FieldStructAOC.SEQ_NUMBER,
				FieldStructAOC.ID, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4
		};
		DB_UPGRADE.defaultValues = null;
		


		DB_UNIT_SET.extraEntry = null;
		DB_UNIT_SET.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4,
				FieldStructAOC.UNKNOWN_INT4, FieldStructAOC.UNKNOWN_INT4
		};
		DB_UNIT_SET.defaultValues = null;
		


		DB_WEAPON_TO_HIT.extraEntry = null;
		DB_WEAPON_TO_HIT.entries = new FieldStructAOC[]{
				FieldStructAOC.NAME, FieldStructAOC.SEQ_NUMBER, FieldStructAOC.ID, new FieldStructAOC("Shock mult", Type.INTEGER, 4),
				new FieldStructAOC("Gun mult", Type.INTEGER, 4), new FieldStructAOC("Arrow mult", Type.INTEGER, 4), new FieldStructAOC("Pierce mult", Type.INTEGER, 4), new FieldStructAOC("Laser mult", Type.INTEGER, 4),
				new FieldStructAOC("Missile mult", Type.INTEGER, 4)
		};
		DB_WEAPON_TO_HIT.defaultValues = null;


		
		System.out.println("Check entries definitions:");
		int count;
		for (DatStructureAOC datStructure : DatStructureAOC.values()){
			count = 0;
			for (FieldStructAOC fieldStruct : datStructure.entries){
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
	public FieldStructAOC extraEntry;
	
	/** This array define the description/type/size of all fields of a single entry in the file.
	 * You can expect the sum of the sizes of these entries must match the size of an entry in the file. */
	public FieldStructAOC[] entries;

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
	public FieldStructAOC getExtraEntry () {
		return extraEntry;
	}

	@Override
	public FieldStructAOC[] getEntries () {
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
