package datstructure;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

import constants.AreaEffectType;
import constants.AttributeCode;
import constants.CalamityTargetType;
import constants.CalamityType;
import constants.ControlType;
import constants.EffectCode;
import constants.GFXEffectType;
import constants.ObjectType;
import constants.ResourceType;
import constants.TechType;
import constants.TerrainFamily;
import constants.UnitType;
import constants.WonderType;
import constants.WorldID;
import datmanager.Core;
import datmanager.DatFile;
import gui.GUI;

/**
 * Represents the structure of the entries of a file
 * @author MarcoForlini
 */
public class DatStructure {

	@SuppressWarnings("javadoc")
	public static final DatStructure
	
	DB_AI_UNIT_TARGETING	 = new DatStructure	("AI Unit Targeting", "dbaiunittargeting.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_AMBIENT_SOUNDS		 = new DatStructure	("Ambient sounds", "dbambientsounds.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_ANIMALS				 = new DatStructure	("Animals", "dbanimals.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_AREA_EFFECT			 = new DatStructure	("Area effects", "dbareaeffect.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_BUTTONS				 = new DatStructure	("Buttons", "dbbuttons.dat", true, 0, 1, 0, 0, 2, 3, 2),
	DB_CALAMITY				 = new DatStructure	("Calamities", "dbcalamity.dat", true, 0, 1, 0, 0, 7, 8, 4),
	DB_CIVILIZATION			 = new DatStructure	("Civilizations", "dbcivilization.dat", true, 0, 0, 1, 2, 0, 1, 4),
	DB_CIV_POWER /*AOC*/	 = new DatStructure	("Powers", "dbcivpowers.dat", true, 0, 1, 0, 0, 1, 2, 3),
	DB_CLIFF_TERRAIN		 = new DatStructure	("Cliff terrain", "dbcliffterrain.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_COLOR_TABLE			 = new DatStructure	("Color table", "dbcolortable.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_CP_BEHAVIOR			 = new DatStructure	("CP Behavior", "dbcpbehavior.dat", true, 0, 1, 0, -1, 0, 1, 4),
	DB_EFFECTS				 = new DatStructure	("Effects", "dbeffects.dat", false, 0, 1, 1, -1, 0, 1, 3),
	DB_EVENTS				 = new DatStructure	("Events", "dbevents.dat", false, 0, 1, 1, 0, 1, -1, 2),
	DB_FAMILY				 = new DatStructure	("Families", "dbfamily.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_GAME_VARIANT			 = new DatStructure	("Game variants", "dbgamevariant.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_GFX_EFFECTS			 = new DatStructure	("GFX Effects", "dbgfxeffects.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_GRAPHICS				 = new DatStructure	("Graphics", "dbgraphics.dat", true, 0, 1, 1, 66, 67, 68, 4),
	DB_MUSIC				 = new DatStructure	("Musics", "dbmusic.dat", true, 0, 1, 0, 2, 0, 1, 3),
	DB_OBJECTS				 = new DatStructure	("Objects", "dbobjects.dat", true, 0, 1, 0, 0, 1, 5, 4),
	DB_PREMADE_CIVS			 = new DatStructure	("Premade civilizations", "dbpremadecivs.dat", true, 0, 1, 0, 2, 0, 1, 2),
	DB_SOUNDS				 = new DatStructure	("Sounds", "dbsounds.dat", true, 0, 1, 0, 2, 3, 4, 4),
	DB_STARTING_RESOURCHES	 = new DatStructure	("Starting resourches", "dbstartingresources.dat", true, 0, 1, 0, 0, 1, 2, 3),
	DB_TECH_TREE			 = new DatStructure	("Technologies", "dbtechtree.dat", true, 1, 1, 0, 0, 1, 2, 4),
	DB_TERRAIN				 = new DatStructure	("Terrain", "dbterrain.dat", true, 0, 1, 0, 2, 3, 4, 4),
	DB_TERRAIN_GRAY_TEXTURES = new DatStructure	("Terrain gray textures", "dbterraingraytextures.dat", true, 0, 1, 0, 2, 3, 4, 4),
	DB_TERRAIN_TYPE			 = new DatStructure	("Terrain type", "dbterraintype.dat", true, 0, 1, 0, 0, 1, 2, 4),
	DB_UI_BACK				 = new DatStructure	("UI Back", "dbuiback.dat", true, 0, 1, 0, 0, 9, 10, 4),
	DB_UI_CONTROL_EVENTS	 = new DatStructure	("UI Control events", "dbuicontrolevents.dat", true, 0, 1, 0, 0, 1, 2, 3),
	DB_UI_CONTROLS			 = new DatStructure	("UI Controls", "dbuicontrols.dat", true, 0, 1, 0, 0, 18, 19, 4),
	DB_UI_FONT				 = new DatStructure	("UI Fonts", "dbuifonts.dat", true, 0, 1, 0, 0, 1, 2, 3),
	DB_UI_FORMS				 = new DatStructure	("UI Forms", "dbuiforms.dat", true, 0, 0, 0, 0, 2, 3, 4),//TODO: Check this
	DB_UI_HOTKEY			 = new DatStructure	("UI Hotkeys", "dbuihotkey.dat", true, 0, 1, 0, 2, 0, 1, 4),
	DB_UNIT_BEHAVIOR		 = new DatStructure	("Unit behavior", "dbunitbehavior.dat", true, 0, 1, 0, 0, 1, 2, 3),
	DB_UNIT_SET				 = new DatStructure	("Unit sets", "dbunitset.dat", true, 0, 1, 0, 0, 1, 2, 3),
	DB_UPGRADE				 = new DatStructure	("Upgrades", "dbupgrade.dat", true, 0, 1, 1, 0, 31, 32, 4),
	DB_WEAPON_TO_HIT		 = new DatStructure	("Weapons to hit", "dbweapontohit.dat", true, 0, 1, 0, 0, 1, 2, 3),
	DB_WORLD				 = new DatStructure	("World", "dbworld.dat", true, 0, 1, 0, -1, 2, 3, 2)
	;

	/** All structures used by Vanilla files */
	public static final DatStructure[] ALL_VANILLA = {
			DB_AI_UNIT_TARGETING, DB_AMBIENT_SOUNDS, DB_ANIMALS, DB_AREA_EFFECT, DB_BUTTONS,
			DB_CALAMITY, DB_CIVILIZATION, DB_CLIFF_TERRAIN, DB_COLOR_TABLE, DB_CP_BEHAVIOR,
			DB_EFFECTS, DB_EVENTS, DB_FAMILY, DB_GAME_VARIANT, DB_GFX_EFFECTS,
			DB_GRAPHICS, DB_MUSIC, DB_OBJECTS, DB_PREMADE_CIVS, DB_SOUNDS,
			DB_STARTING_RESOURCHES, DB_TECH_TREE, DB_TERRAIN, DB_TERRAIN_GRAY_TEXTURES, DB_TERRAIN_TYPE,
			DB_UI_BACK, DB_UI_CONTROL_EVENTS, DB_UI_CONTROLS, DB_UI_FONT, DB_UI_FORMS, DB_UI_HOTKEY,
			DB_UNIT_BEHAVIOR, DB_UNIT_SET, DB_UPGRADE, DB_WEAPON_TO_HIT, DB_WORLD
	};
	
	/** All structures used by AOC files */
	public static final DatStructure[] ALL_AOC = {
			DB_AI_UNIT_TARGETING, DB_AMBIENT_SOUNDS, DB_ANIMALS, DB_AREA_EFFECT, DB_BUTTONS,
			DB_CALAMITY, DB_CIVILIZATION, DB_CIV_POWER, DB_CLIFF_TERRAIN, DB_COLOR_TABLE, DB_CP_BEHAVIOR,
			DB_EFFECTS, DB_EVENTS, DB_FAMILY, DB_GAME_VARIANT, DB_GFX_EFFECTS,
			DB_GRAPHICS, DB_MUSIC, DB_OBJECTS, DB_PREMADE_CIVS, DB_SOUNDS,
			DB_STARTING_RESOURCHES, DB_TECH_TREE, DB_TERRAIN, DB_TERRAIN_GRAY_TEXTURES, DB_TERRAIN_TYPE,
			DB_UI_BACK, DB_UI_CONTROL_EVENTS, DB_UI_CONTROLS, DB_UI_FONT, DB_UI_FORMS,DB_UI_HOTKEY,
			DB_UNIT_BEHAVIOR, DB_UNIT_SET, DB_UPGRADE, DB_WEAPON_TO_HIT, DB_WORLD
	};
	
	
	
	/** Unique field: A 4 bytes integer which point to an area effect's ID. */
	public static final FieldStruct ID_AREA_EFFECT = new FieldStruct("Area Effect ID", DB_AREA_EFFECT, 0);
	/** Unique field: A 4 bytes integer which point to a button's ID. */
	public static final FieldStruct ID_BUTTON = new FieldStruct("Button ID", DB_BUTTONS, 0);
	/** Unique field: A 4 bytes integer which point to a family's ID. */
	public static final FieldStruct ID_FAMILY = new FieldStruct("Family ID", DB_FAMILY, 0);
	/** Unique field: A 4 bytes integer which point to a graphic's ID. */
	public static final FieldStruct ID_GRAPHIC = new FieldStruct("Graphic ID", DB_GRAPHICS, 0);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct ID_OBJECT = new FieldStruct("Object ID", DB_OBJECTS, 0);
	/** Unique field: A 4 bytes integer which point to a sound ID. */
	public static final FieldStruct ID_SOUND = new FieldStruct("Sound ID", DB_SOUNDS, 0);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct ID_TECH = new FieldStruct("Tech ID", DB_TECH_TREE, 0);
	/** Unique field: A 4 bytes integer which point to a terrain's ID. */
	public static final FieldStruct ID_TERRAIN = new FieldStruct("Terrain ID", DB_TERRAIN, 0);
	/** Unique field: A 4 bytes integer which point to an hotkey's ID. */
	public static final FieldStruct ID_UI_HOTKEY = new FieldStruct("Hotkey ID", DB_UI_HOTKEY, 0);
	/** Unique field: A 4 bytes integer which point to an hotkey's ID. */
	public static final FieldStruct ID_UI_FORM = new FieldStruct("Form ID", DB_UI_FORMS, 0);
	/** Unique field: A 4 bytes integer which point to an unit set's ID. */
	public static final FieldStruct ID_UI_FONT = new FieldStruct("Font ID", DB_UI_FONT, 0);
	/** Unique field: A 4 bytes integer which point to an unit set's ID. */
	public static final FieldStruct ID_UNIT_SET = new FieldStruct("Unit set ID", DB_UNIT_SET, 0);
	/** Unique field: A 4 bytes integer which point to an upgrade's ID. */
	public static final FieldStruct ID_UPGRADE = new FieldStruct("Updrade ID", DB_UPGRADE, 0);
	/** Unique field: A 4 bytes integer which point to a weapon to hit's ID. */
	public static final FieldStruct ID_WEAPON_TO_HIT = new FieldStruct("Weapon to hit ID", DB_WEAPON_TO_HIT, 0);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct ID_TECH_FROM_OBJECT = new FieldStruct("Build from object", DB_OBJECTS, 0);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct ID_OBJECT_BUILD_TECH = new FieldStruct ("Can build tech", DB_TECH_TREE, 0);
	/** Unique field: A 4 bytes integer which point to a button's ID. */
	public static final FieldStruct ID_BUTTON_COMMAND = new FieldStruct("Button/Command ID", DB_BUTTONS, 0);
	/** Special field: A 4 bytes float which define an unused ID. */
	public static final FieldStruct ID_GFX_UNUSED = new FieldStruct("Unused GFX effect", DB_GFX_EFFECTS, 0, Knowledge.NEVER_USED);
	/** Special field: A 4 bytes float which define a (still) unknown ID. */
	public static final FieldStruct ID_GFX_UNKNOWN = new FieldStruct("Unknown GFX effect", DB_GFX_EFFECTS, 0, Knowledge.UNKNOWN);


	/** Initialize the structures after the user decided Vanilla or AOC */
	public static void init(){
		System.out.println("Check entries definitions:");

		DB_AI_UNIT_TARGETING.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNCHANGED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		



		DB_AMBIENT_SOUNDS.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_SOUND,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_FLOAT
		};
		DB_AMBIENT_SOUNDS.newEntryValues = new Object[]{
				"<New ambient sound>", 0, -1, -1, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0, 0f,
		};
		



		DB_ANIMALS.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_OBJECT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Min gestation time", Type.INTEGER),
				new FieldStruct("Max gestation time", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("Min wander range", Type.INTEGER), new FieldStruct("Max wander range", Type.INTEGER),
				new FieldStruct("Min wander wait time", Type.INTEGER), new FieldStruct("Max wander wait time", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Newborn size", Type.FLOAT), new FieldStruct("Newborn grow rate", Type.FLOAT), new FieldStruct("Max family members", Type.INTEGER), FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Max number of animals who can produce offspring", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		};
		DB_ANIMALS.newEntryValues = new Object[]{
				"<New animal>", 0, -1, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0f,
				0, 0, 0, 0, 0, 0, 0, 0,
				0f, 0f, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0,
		};
		

		
		
		
		DB_AREA_EFFECT.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Effect type", AreaEffectType.values()),
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Effect on units", DB_GFX_EFFECTS, 0), new FieldStruct("Apply on unit set", DB_UNIT_SET, 0), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, ID_GRAPHIC, new FieldStruct("Max morale", Type.INTEGER), new FieldStruct("Heal effect", Type.INTEGER),
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Area size", Type.FLOAT),
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		DB_AREA_EFFECT.newEntryValues = new Object[]{
				"<New area effect>", 0, -1, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0f,
				0f, 0f, 0f, 0f, 0, 0, 0
		};
		



		DB_BUTTONS.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Texture", Type.STRING, 100), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				new FieldStruct("<only used by espionage center>", Type.INTEGER), new FieldStruct("<only used by farm and espionage center>", Type.INTEGER), new FieldStruct("Position", Type.INTEGER), FieldStruct.UNKNOWN_INT4
		};
		DB_BUTTONS.newEntryValues = new Object[]{
				"<New button>", "textures\\zut_smileyface_00T", 0, -1, 0, 0, 0, -1
		};
		
		


		DB_CALAMITY.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Area damage", Type.FLOAT), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, new FieldStruct("Calamity type", CalamityType.values()), new FieldStruct ("Target type", CalamityTargetType.values()), new FieldStruct("Duration", Type.INTEGER),
				FieldStruct.UNUSED_INT4, new FieldStruct("Cast effect", DB_GFX_EFFECTS, 0), new FieldStruct("Hit area effect", DB_GFX_EFFECTS, 0), new FieldStruct("Hit target effect", DB_GFX_EFFECTS, 0),
				new FieldStruct("Hit target effect", DB_GFX_EFFECTS, 0), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				ID_OBJECT, new FieldStruct("Param 1", Type.INTEGER), new FieldStruct("Param 2", Type.INTEGER), new FieldStruct("Param 3", Type.INTEGER),
				new FieldStruct("Param 4", Type.INTEGER), new FieldStruct("Param 5", Type.INTEGER), new FieldStruct("Param 6", Type.INTEGER), FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNUSED_BOOL1, ID_SOUND,
				ID_SOUND, new FieldStruct("Calamity cost", Type.INTEGER), ID_UNIT_SET, FieldStruct.ID_LANGUAGE,
				FieldStruct.UNKNOWN_INT4
		};
		DB_CALAMITY.newEntryValues = new Object[]{
				"<New calamity>", 0f, 0f, 0f, 0f, 0f, 0f, 0,
				0, 0, 0, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, -1, -1, 0, 0, 0,
				0
		};
		



		DB_CLIFF_TERRAIN.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_TERRAIN,
				ID_TERRAIN, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		DB_CLIFF_TERRAIN.newEntryValues = new Object[]{
				"<New cliff terrain>", 0, -1, 0, 0, 0, 1
		};
		
		
		
		
		DB_COLOR_TABLE.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Player - Red", Type.FLOAT),
				new FieldStruct ("Player - Green", Type.FLOAT), new FieldStruct ("Player - Blue", Type.FLOAT), new FieldStruct ("Model - Red", Type.FLOAT), new FieldStruct ("Model - Green", Type.FLOAT),
				new FieldStruct ("Model - Blue", Type.FLOAT), new FieldStruct ("Lighting - Red", Type.FLOAT), new FieldStruct ("Lighting - Green", Type.FLOAT), new FieldStruct ("Lighting - Blue", Type.FLOAT),
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
		};
		



		DB_CP_BEHAVIOR.fieldStructs = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4
		};
		



		DB_EVENTS.extraField = new FieldStruct("Effect", DB_EFFECTS, 0);
		DB_EVENTS.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, new FieldStruct("Num effects", Type.INTEGER, 4, false),
		};
		DB_EVENTS.newEntryValues = new Object[]{
				"<New event>", 0, -1
		};
		



		DB_FAMILY.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Hovercraft, UFO, Rock thrower", Type.INTEGER),
				new FieldStruct("Frigate", Type.INTEGER), new FieldStruct("Machine gun", Type.INTEGER), new FieldStruct("Galley", Type.INTEGER), new FieldStruct("Tank", Type.INTEGER),
				new FieldStruct("AT Gun", Type.INTEGER), new FieldStruct("Catapult, Bombard", Type.INTEGER), new FieldStruct("AA Tower, Stinger, Flat halftrack", Type.INTEGER), new FieldStruct("<Unused by objects>", Type.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN),
				new FieldStruct("Sea king", Type.INTEGER), new FieldStruct("Field weapon", Type.INTEGER), new FieldStruct("<Unused by objects>", Type.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Fighter, Cruiser", Type.INTEGER),
				new FieldStruct("Cavalry spear", Type.INTEGER), new FieldStruct("Pre-atomic gun infantry", Type.INTEGER), new FieldStruct("ICBM", Type.INTEGER), new FieldStruct("Cavalry gun", Type.INTEGER),
				new FieldStruct("Halbedier", Type.INTEGER), new FieldStruct("Submarine", Type.INTEGER), new FieldStruct("Ram, Sampson", Type.INTEGER), new FieldStruct("Animals", Type.INTEGER),
				new FieldStruct("Archer, Javelin", Type.INTEGER), new FieldStruct("<Unused by objects>", Type.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Mech Minotaur", Type.INTEGER), new FieldStruct("<Unused by objects>", Type.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN),
				new FieldStruct("Elite Guard", Type.INTEGER), new FieldStruct("Grigor II, Mech Hyperion", Type.INTEGER), new FieldStruct("Mech Zeus", Type.INTEGER), new FieldStruct("Mech Pandora", Type.INTEGER),
				new FieldStruct("Mech Ares", Type.INTEGER), new FieldStruct("AT Aircraft", Type.INTEGER), new FieldStruct("Bomber", Type.INTEGER), new FieldStruct("Modern/Future gun infantry", Type.INTEGER),
				new FieldStruct("AT Tank", Type.INTEGER), new FieldStruct("Battleship", Type.INTEGER), new FieldStruct("AT Helicopter", Type.INTEGER), new FieldStruct("Mech Poseidon, Mech Hades", Type.INTEGER),
				new FieldStruct("Medic, Mech Apollo", Type.INTEGER), new FieldStruct("Citizen, Diplomat", Type.INTEGER), new FieldStruct("Crossbow, Sniper, Mech Tempest", Type.INTEGER), new FieldStruct("Carrier fighter", Type.INTEGER),
				new FieldStruct("Gunship Helicopter", Type.INTEGER), new FieldStruct("Persian cavalry", Type.INTEGER), new FieldStruct("Barbarian, Celtic, Gallic", Type.INTEGER), new FieldStruct("Tower", Type.INTEGER),
				new FieldStruct("Flamethrower, Howitzer", Type.INTEGER), new FieldStruct("Viking", Type.INTEGER), new FieldStruct("Priest, Priest Tower", Type.INTEGER), new FieldStruct("Sword", Type.INTEGER),
				new FieldStruct("Cavalry sword", Type.INTEGER), new FieldStruct("Partisan", Type.INTEGER), new FieldStruct("Atomic Submarine", Type.INTEGER), new FieldStruct("Bazooka", Type.INTEGER),
				new FieldStruct("Spear", Type.INTEGER), new FieldStruct("Cavalry archer", Type.INTEGER), new FieldStruct("Heroes", Type.INTEGER), new FieldStruct("Hand Cannoner", Type.INTEGER),
				new FieldStruct("Mortar", Type.INTEGER), new FieldStruct("Red Baron", Type.INTEGER), new FieldStruct("Fishing boat", Type.INTEGER), new FieldStruct("Anti-Missile Battery", Type.INTEGER),
				new FieldStruct("Capitol ship", Type.INTEGER), new FieldStruct("Space fighter", Type.INTEGER), new FieldStruct("Space corvette", Type.INTEGER), new FieldStruct("ICBM (Power)", Type.INTEGER),
				new FieldStruct("Space turret", Type.INTEGER), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		DB_FAMILY.newEntryValues = new Object[]{
				"<New family>", 0, -1, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100,
		};
		



		DB_GAME_VARIANT.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.ALTER_HEALTH_BY,
				ID_UNIT_SET, FieldStruct.ALTER_HEALTH_BY, ID_UNIT_SET, FieldStruct.ALTER_HEALTH_BY,
				ID_UNIT_SET, FieldStruct.ALTER_HEALTH_BY, ID_UNIT_SET, FieldStruct.ALTER_HEALTH_BY,
				ID_UNIT_SET, FieldStruct.ALTER_HEALTH_BY, ID_UNIT_SET, FieldStruct.ALTER_HEALTH_BY,
				ID_UNIT_SET, FieldStruct.ALTER_HEALTH_BY, ID_UNIT_SET, FieldStruct.ALTER_HEALTH_BY,
				ID_UNIT_SET, FieldStruct.ALTER_HEALTH_BY, ID_UNIT_SET, FieldStruct.UNCHANGED_FLOAT,
				new FieldStruct("Epoch advancement cost multiplier", Type.FLOAT), new FieldStruct("Gather time multiplier", Type.FLOAT), new FieldStruct("Alter max morale (dynamic)", Type.INTEGER), FieldStruct.ID_LANGUAGE,
				new FieldStruct("First epoch advancement cost multiplier", Type.FLOAT), new FieldStruct("Second epoch advancement cost multiplier", Type.FLOAT), new FieldStruct("Cost multiplier", Type.FLOAT), ID_UNIT_SET,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Available in random maps", Type.INTEGER),
				FieldStruct.UNUSED_INT4
		};
		DB_GAME_VARIANT.newEntryValues = new Object[]{
				"<New game variant>", 0, -1, 0f, -1, 0f, -1, 0f,
				-1, 0f, -1, 0f, -1, 0f, -1, 0f,
				-1, 0f, -1, 0f, -1, 0f, -1, 0f,
				1.0f, 1.0f, 0, 0, 1.0f, 1.0f, 0f, -1,
				1.0f, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 0
		};
		



		DB_GFX_EFFECTS.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("GFX Effect type", GFXEffectType.values()),
				FieldStruct.UNKNOWN_INT4, ID_GRAPHIC, FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Size", Type.FLOAT),
				FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Min spawn time", Type.INTEGER), new FieldStruct("Max spawn time", Type.INTEGER), new FieldStruct("Duration", Type.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, ID_GFX_UNKNOWN,
				ID_GFX_UNKNOWN, ID_GFX_UNKNOWN, FieldStruct.ID_LANGUAGE, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_GFX_EFFECTS.newEntryValues = new Object[]{
				"<New graphic effect>", 0, -1, -1, -1, -1, 1f, 1f,
				0f, 0, 0, 0, 0, 0, 0, -1,
				-1, -1, 0, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0,
				0, 0
		};
		
		


		DB_GRAPHICS.fieldStructs = new FieldStruct[]{
				FieldStruct.STRING_SIZE, new FieldStruct("Name 1", 0), FieldStruct.STRING_SIZE, new FieldStruct("Model path", 2),
				FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 4), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 6),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 8), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 10),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 12), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 14),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 16), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 18),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 20), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 22),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 24), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 26)   ,
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 28), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 30),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 32), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 34),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 36), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 38),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 40), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 42),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 44), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 46),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 48), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 50),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 52), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 54),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 56), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 58),
				FieldStruct.STRING_SIZE, new FieldStruct("Model path", 60), FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 62),
				FieldStruct.STRING_SIZE, new FieldStruct("Texture path", 64), FieldStruct.NAME, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, ID_SOUND, ID_SOUND,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, ID_SOUND, ID_SOUND,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, ID_SOUND, ID_SOUND,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, ID_SOUND, ID_SOUND,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, ID_SOUND, ID_SOUND,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, ID_SOUND, ID_SOUND,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, new FieldStruct("<Some entries have link to themselves", DB_GRAPHICS, 0), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT,	//FF FF FF FF
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4
		};
		DB_GRAPHICS.newEntryValues = new Object[]{
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", "<New graphic>", 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				-1, -1, 0, 0, 0, 0, 0, 0,
				0, 0, -1, -1, 0, 0, 0, 0,
				0, 0, 0, 0, -1, -1, 0, 0,
				0, 0, 0, 0, 0, 0, -1, -1,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, -1, -1, 0, 0,
				0, 0, 0, 0, 0, 0, -1, -1,
				0, 0, 0, 0, 0, 0, 0, 0,
				-1, -1, 0, 0, 0, 0, 0, 0,
				0, 0, -1, -1, 0, 0, 0, 0,
				0, 0, 0, 0, -1, -1, 0, 0,
				0, 0, 0, 0, 0, 0, -1, -1,
				0, 0, 0, 0, 0, 0, 0, 0,
				-1, -1, 0, 0, 0, 0, 0, 0,
				0, 0, -1, -1, 0, 0, 0, 0,
				0, -1, 0, 0, -1, -1, 0, 1f,
				0, 0f, 0, 0, 0
		};
		
		


		DB_MUSIC.fieldStructs = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, new FieldStruct("Another name", Type.STRING, 56),
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Time", Type.FLOAT), FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1,
				FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNCHANGED_BOOL1, FieldStruct.UNUSED_INT1
		};
		DB_MUSIC.newEntryValues = new Object[]{
				0, -1, "<New music>", "<New music>", 0f, 0f, 0f, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 204
		};
		
		


		DB_PREMADE_CIVS.fieldStructs = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.UNKNOWN_INT4,
				FieldStruct.ID_LANGUAGE, FieldStruct.ID_LANGUAGE,
		};
		
		


		DB_SOUNDS.fieldStructs = new FieldStruct[]{
				FieldStruct.STRING_SIZE, new FieldStruct("Pathname", 0), FieldStruct.NAME, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4
		};
		DB_SOUNDS.newEntryValues = new Object[]{
				0, "", "<New sound>", 0, -1, 0, 0, 0,
				0, 0, 0
		};
		



		DB_STARTING_RESOURCHES.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.ID_LANGUAGE,
				new FieldStruct("Starting food", Type.INTEGER), new FieldStruct("Starting wood", Type.INTEGER), new FieldStruct("Starting stone", Type.INTEGER), new FieldStruct("Starting gold", Type.INTEGER),
				new FieldStruct("Starting iron", Type.INTEGER)
		};
		DB_STARTING_RESOURCHES.newEntryValues = new Object[]{
				"<New starting resourches>", -1, -1, -1, 600, 600, 200, 400, 400
		};
		
		


		DB_TERRAIN.fieldStructs = new FieldStruct[]{
				FieldStruct.STRING_SIZE, new FieldStruct("Pathname", 0), FieldStruct.NAME, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, new FieldStruct("Terrain gray type", DB_TERRAIN_GRAY_TEXTURES, 0), FieldStruct.ID_LANGUAGE, new FieldStruct("Valid", Type.BOOLEAN, 0),
				new FieldStruct("Terrain family", TerrainFamily.values()), new FieldStruct("Tyles on X axis", Type.INTEGER), new FieldStruct("Tiles on Y axis", Type.INTEGER), new FieldStruct("Available: 0-No, 1-Animated, 65536-Static", Type.INTEGER, 0),
				new FieldStruct("Terrain type", DB_TERRAIN_TYPE, 0), new FieldStruct("Object from ID (1)", DB_OBJECTS, 0), new FieldStruct("Object from ID (2)", DB_OBJECTS, 0), new FieldStruct("Object from ID (3)", DB_OBJECTS, 0),
				new FieldStruct("Object to ID (1)", DB_OBJECTS, 0), new FieldStruct("Object to ID (2)", DB_OBJECTS, 0), new FieldStruct("Object to ID (3)", DB_OBJECTS, 0), new FieldStruct ("<Probably> Chance spawn object per tile", Type.INTEGER),
				new FieldStruct("<Used by animated terrains>", Type.INTEGER), new FieldStruct("<Used by animated terrains>", Type.INTEGER), new FieldStruct("Color: Red", Type.INTEGER), new FieldStruct("Color: Green", Type.INTEGER),
				new FieldStruct("Color: Blue", Type.INTEGER), FieldStruct.UNUSED_INT4, new FieldStruct("Amb Marsh 1", Type.FLOAT), new FieldStruct("Amb Marsh 2", Type.FLOAT),
				new FieldStruct("Is an ambient terrain? (Yes = 16777216)", Type.INTEGER)
		};
		DB_TERRAIN.newEntryValues = new Object[]{
				0, "", "<New terrain>", 0, -1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0f, 0f, 0
		};
		



		DB_TERRAIN_GRAY_TEXTURES.fieldStructs = new FieldStruct[]{
				FieldStruct.STRING_SIZE, new FieldStruct("Pathname", 0), FieldStruct.NAME, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, new FieldStruct("Color: Red", Type.INTEGER), new FieldStruct("Color: Green", Type.INTEGER),
				new FieldStruct("Color: Blue", Type.INTEGER)
		};
		DB_TERRAIN_GRAY_TEXTURES.newEntryValues = new Object[]{
				0, "", "<New terrain gray>", 0, -1, 0, 0, 0
		};
		


		
		DB_TERRAIN_TYPE.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.ID_LANGUAGE, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4
		};
		DB_TERRAIN_TYPE.newEntryValues = new Object[]{
				"<New terrain type>", -1, -1, 0, -1, 0, 0, 0
		};
		

		
		
		DB_UI_BACK.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Texture atlas", Type.STRING, 100), new FieldStruct("Screen height from...", Type.FLOAT), new FieldStruct("... to", Type.FLOAT),
				new FieldStruct("Screen width from... ", Type.FLOAT), new FieldStruct("... to", Type.FLOAT), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_UI_FORM,
				new FieldStruct("Min Y pixel coord" , Type.INTEGER), new FieldStruct("Min X pixel coord", Type.INTEGER), new FieldStruct("Max (inclusive) Y pixel coord", Type.INTEGER), new FieldStruct("Max (inclusive) X pixel coord", Type.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("X axis tiles", Type.INTEGER), new FieldStruct("Y axis tiles", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT4,
		};


		DB_UI_CONTROL_EVENTS.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Control", DB_UI_CONTROLS, 0),
				new FieldStruct("Control event type", ControlType.values())
		};
		

		
		
		DB_UI_CONTROLS.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Sub", Type.STRING, 100), new FieldStruct("Sub", Type.STRING, 100), new FieldStruct("Sub", Type.STRING, 100),
				new FieldStruct("Sub", Type.STRING, 100), new FieldStruct("Sub", Type.STRING, 100), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, new FieldStruct("Screen height from...", Type.FLOAT), new FieldStruct("... to", Type.FLOAT),
				new FieldStruct("Screen width from... ", Type.FLOAT), new FieldStruct("... to", Type.FLOAT), FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				ID_UI_FORM, new FieldStruct("Belongs to tab (0=all)", Type.INTEGER), new FieldStruct("Type", Type.INTEGER), new FieldStruct("Control index in form", Type.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, new FieldStruct("Red", Type.INTEGER), new FieldStruct("Green", Type.INTEGER), new FieldStruct("Blue", Type.INTEGER),
				FieldStruct.ID_LANGUAGE, FieldStruct.ID_LANGUAGE, ID_UI_FONT, new FieldStruct("Red", Type.INTEGER), 
				new FieldStruct("Green", Type.INTEGER), new FieldStruct("Blue", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4,
				new FieldStruct("Red", Type.INTEGER), new FieldStruct("Green", Type.INTEGER), new FieldStruct("Blue", Type.INTEGER), FieldStruct.UNUSED_INT4,
				new FieldStruct("Red", Type.INTEGER), new FieldStruct("Green", Type.INTEGER), new FieldStruct("Blue", Type.INTEGER), FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.ID_LANGUAGE,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT4,
		};
		

		

		DB_UI_FONT.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Font type", Type.INTEGER),
				new FieldStruct("Font size", Type.INTEGER), new FieldStruct("Quality", Type.INTEGER), new FieldStruct("Bold"), new FieldStruct("Italic"),
				new FieldStruct("Underline"), new FieldStruct("Has shadow"), FieldStruct.UNUSED_INT4
		};

		//TODO WIP
		DB_UI_FORMS.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID,
				new FieldStruct("Controls", Type.INTEGER), new FieldStruct("Tabs", Type.INTEGER), FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Viewport Width", Type.INTEGER), new FieldStruct("Viewport Height", Type.INTEGER),
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
				FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
				FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1
		};

		DB_UI_HOTKEY.fieldStructs = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNCHANGED_INT4, FieldStruct.UNCHANGED_INT4, new FieldStruct("Key scan code", Type.INTEGER)
		};
		
		


		DB_UNIT_BEHAVIOR.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Run away from enemy"),
				new FieldStruct("Ignore enemy outside LOS"), new FieldStruct("Attack enemy"), new FieldStruct("Follow enemy"), new FieldStruct("Run away if attacked"),
				new FieldStruct("Return to initial location"), FieldStruct.UNUSED_BOOL1, FieldStruct.UNUSED_BOOL1, FieldStruct.UNUSED_INT4,
				FieldStruct.UNUSED_INT4
		};
		
		


		DB_UNIT_SET.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_FAMILY,
				ID_FAMILY, ID_FAMILY, ID_FAMILY, ID_FAMILY,
				ID_FAMILY, ID_FAMILY, ID_FAMILY, ID_OBJECT,
				ID_OBJECT, ID_OBJECT, ID_OBJECT, ID_OBJECT,
				ID_OBJECT, ID_OBJECT, ID_OBJECT, new FieldStruct("Include unit set...", DB_UNIT_SET, 0),
				new FieldStruct("Exclude unit set", DB_UNIT_SET, 0), new FieldStruct("Is the first set in the list", Type.INTEGER)
		};
		DB_UNIT_SET.newEntryValues = new Object[]{
				"<New unit set>", 0, -1, 0, 0, 0, 0, 0,
				0, 0, 0, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, 0
		};
		
		


		DB_UPGRADE.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, new FieldStruct("Attack mult", Type.FLOAT), new FieldStruct("Health mult", Type.FLOAT), new FieldStruct("Speed mult", Type.FLOAT),
				new FieldStruct("Range mult", Type.FLOAT), new FieldStruct("Shock armor mult", Type.FLOAT), new FieldStruct("Arrow armor mult", Type.FLOAT), new FieldStruct("Pierce armor mult", Type.FLOAT),
				new FieldStruct("Gun armor mult", Type.FLOAT), new FieldStruct("Laser armor mult", Type.FLOAT), new FieldStruct("Missile armor mult", Type.FLOAT), new FieldStruct("Fuel/Power mult", Type.FLOAT),
				FieldStruct.UNUSED_INT4, new FieldStruct("Area mult", Type.FLOAT), FieldStruct.UNUSED_INT4, new FieldStruct("Attack cost mult", Type.FLOAT),
				new FieldStruct("Health cost mult", Type.FLOAT), new FieldStruct("Speed cost mult", Type.FLOAT), new FieldStruct("Range cost mult", Type.FLOAT), new FieldStruct("Shock armor cost mult", Type.FLOAT),
				new FieldStruct("Arrow armor cost mult", Type.FLOAT), new FieldStruct("Pierce armor cost mult", Type.FLOAT), new FieldStruct("Gun armor cost mult", Type.FLOAT), new FieldStruct("Laser armor cost mult", Type.FLOAT),
				new FieldStruct("Missile armor cost mult", Type.FLOAT), new FieldStruct("Fuel/Power cost mult", Type.FLOAT), FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT,
				new FieldStruct("Area cost mult", Type.FLOAT), FieldStruct.UNUSED_INT4, new FieldStruct("Max upgrades per stat", Type.INTEGER), FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, FieldStruct.UNKNOWN_INT4, new FieldStruct("<It seems a starting epoch>", Type.INTEGER), FieldStruct.UNKNOWN_INT4
		};
		DB_UPGRADE.newEntryValues = new Object[]{
				"<New upgrade>", 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0f, 0f, 0, 0f, 0, 0f,
				0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
				0f, 0f, 0, 0f, 0f, 0, 0, 0,
				0, 0, 0, 0
		};
		
		


		DB_WEAPON_TO_HIT.fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Shock mult", Type.INTEGER),
				new FieldStruct("Arrow mult", Type.INTEGER), new FieldStruct("Pierce mult", Type.INTEGER), new FieldStruct("Gun mult", Type.INTEGER), new FieldStruct("Laser mult", Type.INTEGER),
				new FieldStruct("Missile mult", Type.INTEGER)
		};
		
		
		
		
		DB_WORLD.fieldStructs = new FieldStruct[]{
				new FieldStruct("Float/Min float", Type.FLOAT), new FieldStruct("Max float", Type.FLOAT), FieldStruct.SEQ_NUMBER, new FieldStruct("ID", WorldID.values(), false),
				new FieldStruct("Int/Min int", Type.INTEGER), new FieldStruct("Max int", Type.INTEGER), FieldStruct.UNKNOWN_INT4
		};
		DB_WORLD.nameBuilder = (entry) -> {
			WorldID wID = WorldID.C00_NULL.parseValue(entry.getID());
			return (wID != null) ? wID.name : "<Unknown>";
		};
		




		if (!Core.AOC){
			DB_CIVILIZATION.fieldStructs = new FieldStruct[]{
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.ID_LANGUAGE,
					new FieldStruct("Cost increment", Type.INTEGER), ID_UNIT_SET, new FieldStruct("Attack bonus", Type.FLOAT), new FieldStruct("Attack bonus cost", Type.FLOAT),
					new FieldStruct("Health bonus", Type.FLOAT), new FieldStruct("Health bonus cost", Type.FLOAT), new FieldStruct("Attack speed bonus", Type.FLOAT), new FieldStruct("Attack speed bonus cost", Type.FLOAT),
					new FieldStruct("Armor bonus", Type.FLOAT), new FieldStruct("Armor bonus cost", Type.FLOAT), new FieldStruct("Range bonus", Type.FLOAT), new FieldStruct("Range bonus cost", Type.FLOAT),
					new FieldStruct("Speed bonus", Type.FLOAT), new FieldStruct("Speed bonus cost", Type.FLOAT), new FieldStruct("Flight time bonus", Type.FLOAT), new FieldStruct("Flight time bonus cost", Type.FLOAT),
					new FieldStruct("Area damage bonus", Type.FLOAT), new FieldStruct("Area damage bonus cost", Type.FLOAT), new FieldStruct("Hunting & foraging bonus", Type.FLOAT), new FieldStruct("Hunting & foraging bonus cost", Type.FLOAT),
					new FieldStruct("Wood cutting bonus", Type.FLOAT), new FieldStruct("Wood cutting bonus cost", Type.FLOAT), new FieldStruct("Gold mining bonus", Type.FLOAT), new FieldStruct("Gold mining bonus cost", Type.FLOAT),
					new FieldStruct("Farming bonus", Type.FLOAT), new FieldStruct("Farming bonus cost", Type.FLOAT), new FieldStruct("Stone mining bonus", Type.FLOAT), new FieldStruct("Stone mining bonus cost", Type.FLOAT),
					new FieldStruct("Iron mining bonus", Type.FLOAT), new FieldStruct("Iron mining bonus cost", Type.FLOAT), new FieldStruct("Build time reduction", Type.FLOAT), new FieldStruct("Build time reduction cost", Type.FLOAT),
					new FieldStruct("Conversion resistance bonus", Type.FLOAT), new FieldStruct("Conversion resistance bonus cost", Type.FLOAT), new FieldStruct("Fishing bonus", Type.FLOAT), new FieldStruct("Fishing bonus cost", Type.FLOAT),
					new FieldStruct("Repair bonus", Type.FLOAT), new FieldStruct("Repair bonus cost", Type.FLOAT), new FieldStruct("Area effect bonus", Type.FLOAT), new FieldStruct("Area effect bonus cost", Type.FLOAT),
					new FieldStruct("Mountain combat bonus", Type.FLOAT), new FieldStruct("Mountain combat bonus cost", Type.FLOAT), new FieldStruct("Population cap bonus", Type.FLOAT), new FieldStruct("Population cap bonus cost", Type.FLOAT),
					new FieldStruct("Area conversion bonus", Type.FLOAT), new FieldStruct("Area conversion bonus cost", Type.FLOAT), new FieldStruct("Cost reduction", Type.FLOAT), new FieldStruct("Cost reduction cost", Type.FLOAT),
					new FieldStruct("Night-time LOS bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Night-time LOS bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Slavery bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Slavery bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Capitol upgrade bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Capitol upgrade bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Heroes bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Heroes bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 1 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 1 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 2 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 2 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 3 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 3 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 4 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 4 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 5 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 5 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 6 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 6 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 7 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 7 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 8 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 8 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 9 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 9 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 10 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 10 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN)
			};
			DB_CIVILIZATION.newEntryValues = new Object[]{
					0, -1, "<New civilization>", 0, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
			};
			



			DB_EFFECTS.nameBuilder = (entry) -> EffectCode.C01_SET_BUTTON.parseValue((int) entry.get(8)).nameBuilder.apply(entry);
			DB_EFFECTS.fieldStructs = new FieldStruct[]{
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Set base attribute", Type.FLOAT), new FieldStruct("Alter base attribute", Type.FLOAT),
					new FieldStruct("Alter attribute multiplier", Type.FLOAT), new FieldStruct("Upgrade these objects...", DB_OBJECTS, 0), new FieldStruct("... to these objects", DB_OBJECTS, 0), ID_UNIT_SET,
					new FieldStruct("Effect code", EffectCode.values()), new FieldStruct("New graphic", DB_GRAPHICS, 0), ID_TECH, new FieldStruct("Attribute code", AttributeCode.values()),
					ID_SOUND, ID_BUTTON, new FieldStruct("New area effect", DB_AREA_EFFECT, 0), FieldStruct.UNUSED_INT4,
					FieldStruct.UNUSED_INT4,
			};
			DB_EFFECTS.newEntryValues = new Object[]{
					0, 0, 0f, 0f, 0f, -1, -1, -1,11
					-1, -1, -1, -1, -1, -1, -1, 0,
					0,
			};
			



			DB_OBJECTS.fieldStructs = new FieldStruct[]{
					FieldStruct.NAME, FieldStruct.SEQ_NUMBER, ID_FAMILY, FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNKNOWN_INT4, FieldStruct.ID, new FieldStruct("Health", Type.INTEGER), FieldStruct.UNKNOWN_INT4,
					new FieldStruct("Min range", Type.FLOAT), new FieldStruct("Max range", Type.FLOAT), new FieldStruct("Line of sight", Type.FLOAT), new FieldStruct("Attack reload time", Type.FLOAT),
					new FieldStruct("Area of effect", Type.FLOAT), new FieldStruct("Speed", Type.FLOAT), new FieldStruct("Acceleration/Deceleration", Type.FLOAT), new FieldStruct("Idle turning speed", Type.FLOAT),
					new FieldStruct("Moving turning speed", Type.FLOAT), FieldStruct.UNKNOWN_FLOAT, new FieldStruct("<PROBABLY> Is air unit", Type.INTEGER), new FieldStruct("Flight time", Type.INTEGER),
					FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Attack angle", Type.FLOAT), new FieldStruct("<PROBABLY> Attack from the side", Type.INTEGER), ID_WEAPON_TO_HIT,
					new FieldStruct("Attack", Type.INTEGER), new FieldStruct("Shock armor", Type.INTEGER), new FieldStruct("Arrow armor", Type.INTEGER), new FieldStruct("Pierce armor", Type.INTEGER),
					new FieldStruct("Gun armor", Type.INTEGER), new FieldStruct("Laser armor", Type.INTEGER), new FieldStruct("Missile armor", Type.INTEGER), new FieldStruct("Projectile ID", DB_OBJECTS, 0),
					new FieldStruct("Family attack multiplier index", IntStream.range(0, 66).boxed().toArray(Integer[]::new)), FieldStruct.UNKNOWN_INT4, new FieldStruct("Graphic size", Type.FLOAT), new FieldStruct("Unit type", Type.STRING, 52),
					new FieldStruct("Unit type", UnitType.values()), FieldStruct.UNUSED_INT4, ID_BUTTON, ID_GRAPHIC,
					new FieldStruct("Build rate", Type.FLOAT), new FieldStruct("Iron gather rate", Type.FLOAT), new FieldStruct("Farm gather rate", Type.FLOAT), new FieldStruct("Gold gather rate", Type.FLOAT),
					new FieldStruct("Stone gather rate", Type.FLOAT), new FieldStruct("Wood gather rate", Type.FLOAT), new FieldStruct("Hunt gather rate", Type.FLOAT), new FieldStruct("Forage gather rate", Type.FLOAT),
					new FieldStruct("Creation sound", DB_SOUNDS, 0), new FieldStruct("Action sound 1", DB_SOUNDS, 0), new FieldStruct("Action sound 2", DB_SOUNDS, 0), new FieldStruct("Selection sound 1", DB_SOUNDS, 0),
					new FieldStruct("Selection sound 2", DB_SOUNDS, 0), new FieldStruct("Death sound", DB_SOUNDS, 0), new FieldStruct("<PROBABLY> Can build", Type.BOOLEAN), ID_BUTTON_COMMAND,
					ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND,
					ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND,
					FieldStruct.UNCHANGED_INT4, new FieldStruct("Square occupied", Type.INTEGER), new FieldStruct("Resource type", ResourceType.values()), new FieldStruct("Resource amount", Type.FLOAT),
					new FieldStruct("Always face camera", Type.INTEGER), FieldStruct.UNKNOWN_INT4, new FieldStruct("Rotting time", Type.FLOAT), new FieldStruct("Population count", Type.INTEGER),
					new FieldStruct("Transport capacity", Type.INTEGER), new FieldStruct("Show area effect stat"), new FieldStruct("Show repair stat"), new FieldStruct("Show shock armor stat"),
					new FieldStruct("Show pierce armor stat"), new FieldStruct("Show arrow armor stat"), new FieldStruct("Show laser armor stat"), new FieldStruct("Show gun armor stat"),
					new FieldStruct("Show missile armor stat"), new FieldStruct("Show range", Type.INTEGER), new FieldStruct("Morale bonus", Type.INTEGER), FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, new FieldStruct("Flight altitude", Type.FLOAT),
					FieldStruct.ID_LANGUAGE, FieldStruct.UNKNOWN_INT4, new FieldStruct("Healer heal amount", Type.INTEGER), new FieldStruct("Health regeneration", Type.INTEGER),
					new FieldStruct("Can garrison in air transports"), new FieldStruct("Can garrison in land transports"), new FieldStruct("Can garrison in sea transports"), FieldStruct.UNUSED_INT1,
					new FieldStruct("Object type", ObjectType.values()), FieldStruct.UNKNOWN_INT4, new FieldStruct("Circle selection size", Type.FLOAT), new FieldStruct("Shadow size", Type.FLOAT),
					FieldStruct.UNUSED_INT4, new FieldStruct("Formation size", Type.FLOAT), new FieldStruct("Physical size", Type.FLOAT), new FieldStruct("Interact/obstruct/bump other objects"),
					FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_FLOAT,
					new FieldStruct("Initial projectile angle", Type.FLOAT), new FieldStruct("Ammo amount", Type.INTEGER), new FieldStruct("<All bombers use this>"), FieldStruct.UNKNOWN_BOOL1,
					FieldStruct.UNKNOWN_BOOL1, new FieldStruct("<PROBABLY> Parabolic projectile"), FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
					new FieldStruct("Category (Heroes use 27)", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNKNOWN_INT4, ID_UPGRADE, new FieldStruct("<Used by units/buildings who can convert>", Type.INTEGER), FieldStruct.UNKNOWN_FLOAT,
					new FieldStruct("Can be owned by a player"), new FieldStruct("Can be owned by \"World\""), FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
					new FieldStruct("Can be killed with Delete"), FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNUSED_INT1,
					new FieldStruct("Low health effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Death effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Start of attack ID", DB_GFX_EFFECTS, 0), ID_GFX_UNKNOWN,
					new FieldStruct("Movement effect", DB_GFX_EFFECTS, 0), ID_GFX_UNKNOWN, new FieldStruct("Heal other effect ID", DB_GFX_EFFECTS, 0), ID_GFX_UNKNOWN,
					ID_GFX_UNKNOWN,	new FieldStruct("Get hit effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Permanent effect", DB_GFX_EFFECTS, 0), ID_GFX_UNUSED,
					new FieldStruct("Projectile effect", DB_GFX_EFFECTS, 0), ID_GFX_UNUSED, ID_GFX_UNUSED, ID_GFX_UNUSED,
					ID_GFX_UNUSED, ID_GFX_UNUSED, ID_GFX_UNKNOWN, new FieldStruct("<only used by volcano-projectile and meteor>", Type.INTEGER),
					new FieldStruct("Attack effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Disease effect ID 1", DB_GFX_EFFECTS, 0), new FieldStruct("Disease effect ID 2", DB_GFX_EFFECTS, 0), new FieldStruct("Diffraction shield effect ID", DB_GFX_EFFECTS, 0),
					new FieldStruct("Pulse cannon effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Battle cry effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Bomb hole effect ID", DB_GFX_EFFECTS, 0), ID_GFX_UNUSED,
					ID_GFX_UNKNOWN, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("<only used by ships>", Type.FLOAT),
					FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
					new FieldStruct("Terrain: Grass, Snow"), new FieldStruct("Elevation: Deep water"), new FieldStruct("Terrain: Rock, Stones and Artificial"), FieldStruct.UNKNOWN_BOOL1,
					FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
					FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Terrain: Asphalt"), new FieldStruct("Elevation: cliffs"),
					new FieldStruct("Elevation: Shallow water"), FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Terrain: Sand"), new FieldStruct("Terrain: Space"),
					FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNUSED_INT1, FieldStruct.UNUSED_INT1,
					ID_AREA_EFFECT, ID_AREA_EFFECT, ID_AREA_EFFECT, ID_AREA_EFFECT,
					FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Carry capacity", Type.FLOAT),
					FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1,
					FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_INT1, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
					new FieldStruct("Debris on death", DB_OBJECTS, 0), new FieldStruct("Debris on death", DB_OBJECTS, 0), FieldStruct.UNKNOWN_INT4, new FieldStruct("Min stealth radius", Type.INTEGER),
					new FieldStruct ("<It seems \"Interaction type\">", Type.INTEGER), new FieldStruct("Max citizens garrison", Type.INTEGER), new FieldStruct("Initial citizens garrison", Type.INTEGER), new FieldStruct("Garrison upgrade object to", DB_TECH_TREE, 0),
					new FieldStruct ("Plane refuel location", Type.INTEGER), new FieldStruct("<Seems related to ships...>", Type.INTEGER), new FieldStruct("Wonder bonus", WonderType.values()), new FieldStruct ("Effect apply to", DB_UNIT_SET, 0),
					FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Friendly damage mult", Type.FLOAT), new FieldStruct("Garrison citizens bonus", Type.FLOAT),
					FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
					new FieldStruct("Num techs it can build", Type.INTEGER, 4, false), ID_TECH, new FieldStruct("Is building?"), new FieldStruct("Stealth"),
					FieldStruct.UNKNOWN_BOOL1, new FieldStruct("<Seems related to aircraft, but the clubman...>", Type.INTEGER, 1), FieldStruct.UNKNOWN_INT4, new FieldStruct("Spawn on death", DB_OBJECTS, 0),
					new FieldStruct("Power", Type.INTEGER), new FieldStruct("Power recover rate", Type.INTEGER), new FieldStruct("Default stance", DB_UNIT_BEHAVIOR, 0), new FieldStruct("Conversion time", Type.INTEGER),
					FieldStruct.UNKNOWN_FLOAT, new FieldStruct("<It seems heal power, but the roman legionnaire...>", Type.FLOAT, 4, Knowledge.UNKNOWN), FieldStruct.UNKNOWN_FLOAT, new FieldStruct("<PROBABLY> Load range", Type.FLOAT),
					new FieldStruct("<PROBABLY> Unload range", Type.FLOAT), new FieldStruct("Can walk through trees"), FieldStruct.UNUSED_BOOL1, FieldStruct.UNKNOWN_BOOL1,
					new FieldStruct("Can attack area"), new FieldStruct("<PROBABLY> Melee attack"), new FieldStruct("Garrison heal rate", Type.INTEGER, 1), FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1,
					new FieldStruct("Unknown string", Type.STRING, 100), new FieldStruct("Unknown string", Type.STRING, 100),
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
					ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
			};
			DB_OBJECTS.newEntryValues = new Object[]{
					"<New object>", 0, -1, -1, 0, -1, 0, 0,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0, 0, 0f, 0f, 0, -1,
					0, 0, 0, 0, 0, 0, 0, -1,
					-1, 0, 0f, "<Object type>", 0, 0, -1, -1,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					-1, -1, -1, -1, -1, -1, 0, -1,
					-1, -1, -1, -1, -1, -1, -1, -1,
					-1, 0, 0, 0f, 0, -1, 0f, 0,
					0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0f, -1, 0f,
					0, 0, 0, 0, 0, 0, 0, 204,
					0, 0, 0f, 0f, 0, 0f, 0f, 0,
					1, 204, 204, 0f, 0f, 0, 0, 0,
					0, 0, -1, 0, 0, 0, 0, 0,
					0, 0, 0, 0f, 1, 0, 0, 0,
					0, 0, 204, 204, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, 0f, 0, 0, 0, 0,
					1, 1, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1, 1, 1, 0,
					0, 0, 204, 204, -1, -1, -1, -1,
					0, 0, 0, 0f, 0, 0, 0, 0,
					0, 204, 0, 0, 0, 0, 0, 0,
					0, 0, 0, -1, 0, 0, 0, 0,
					0f, 0f, 0f, 0f, 0f, 0, 0, 0,
					0, -1, 1, 0, 0, 204, 0, 0,
					0, 0, 0, 0, 0f, 0f, 0f, 0f,
					0f, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0,
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
					-1, -1, -1, -1, -1, -1, -1, -1,
			};
			



			DB_TECH_TREE.extraField = ID_TECH_FROM_OBJECT;
			DB_TECH_TREE.fieldStructs = new FieldStruct[]{
					FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Starting epoch", Type.INTEGER),
					new FieldStruct("Ending epoch", Type.INTEGER), new FieldStruct("Event ID", DB_EVENTS, 0), FieldStruct.UNKNOWN_INT4, new FieldStruct("Wood cost", Type.INTEGER),
					new FieldStruct("Stone cost", Type.INTEGER), new FieldStruct("<Only Impassable tile object and Invisible capital>", Type.INTEGER), new FieldStruct("Gold cost", Type.INTEGER), FieldStruct.UNUSED_INT4,
					new FieldStruct("Iron cost", Type.INTEGER), new FieldStruct("Food cost", Type.INTEGER),
					new FieldStruct("Build time", Type.INTEGER), new FieldStruct("Unlocked by tech", DB_TECH_TREE, 0),
					new FieldStruct("Unlocked by power", DB_TECH_TREE, 0), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER), new FieldStruct("Object ID", DB_OBJECTS, -1),
					ID_BUTTON, new FieldStruct("Is object?", Type.INTEGER), FieldStruct.ID_LANGUAGE, new FieldStruct("Tech type", TechType.values()),
					new FieldStruct("<4 in all Epochs, 0 everywhere else>", Type.INTEGER), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Calamity", DB_CALAMITY, 0),
					new FieldStruct("Hotkey ID", DB_UI_HOTKEY, 0), new FieldStruct("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), new FieldStruct("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
					FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Only in scenario"), new FieldStruct("<All powers and power techs use 0>"),
					new FieldStruct("<All powers and power techs use 0>"), new FieldStruct("Epoch number", Type.INTEGER), new FieldStruct("Buildings to advance epoch", Type.INTEGER), new FieldStruct("Epoch IDs", Type.INTEGER),
					new FieldStruct("Last build object", DB_OBJECTS, 0, false), new FieldStruct("Num of tech builders", Type.INTEGER, 4, false)
			};
			DB_TECH_TREE.newEntryValues = new Object[]{
					"<New technology>", -1, 0, 0, 15, -1, -1, 0,
					0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, -1, -1, 0, 0, 0,
					0, 0, 0, 0, -1, 0, 0, 0,
					0, 0f, 0f, 0f, 0f, 0, 0, 1,
					1, 0, 0, 0, -1, 0
			};
			



		} else {



			DB_CIVILIZATION.fieldStructs = new FieldStruct[]{
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.ID_LANGUAGE,
					new FieldStruct("Cost increment", Type.INTEGER), ID_UNIT_SET, new FieldStruct("Attack bonus", Type.FLOAT), new FieldStruct("Attack bonus cost", Type.FLOAT),
					new FieldStruct("Health bonus", Type.FLOAT), new FieldStruct("Health bonus cost", Type.FLOAT), new FieldStruct("Attack speed bonus", Type.FLOAT), new FieldStruct("Attack speed bonus cost", Type.FLOAT),
					new FieldStruct("Armor bonus", Type.FLOAT), new FieldStruct("Armor bonus cost", Type.FLOAT), new FieldStruct("Range bonus", Type.FLOAT), new FieldStruct("Range bonus cost", Type.FLOAT),
					new FieldStruct("Speed bonus", Type.FLOAT), new FieldStruct("Speed bonus cost", Type.FLOAT), new FieldStruct("Flight time bonus", Type.FLOAT), new FieldStruct("Flight time bonus cost", Type.FLOAT),
					new FieldStruct("Area damage bonus", Type.FLOAT), new FieldStruct("Area damage bonus cost", Type.FLOAT), new FieldStruct("Hunting & foraging bonus", Type.FLOAT), new FieldStruct("Hunting & foraging bonus cost", Type.FLOAT),
					new FieldStruct("Wood cutting bonus", Type.FLOAT), new FieldStruct("Wood cutting bonus cost", Type.FLOAT), new FieldStruct("Gold mining bonus", Type.FLOAT), new FieldStruct("Gold mining bonus cost", Type.FLOAT),
					new FieldStruct("Farming bonus", Type.FLOAT), new FieldStruct("Farming bonus cost", Type.FLOAT), new FieldStruct("Stone mining bonus", Type.FLOAT), new FieldStruct("Stone mining bonus cost", Type.FLOAT),
					new FieldStruct("Iron mining bonus", Type.FLOAT), new FieldStruct("Iron mining bonus cost", Type.FLOAT), new FieldStruct("Build time reduction", Type.FLOAT), new FieldStruct("Build time reduction cost", Type.FLOAT),
					new FieldStruct("Conversion resistance bonus", Type.FLOAT), new FieldStruct("Conversion resistance bonus cost", Type.FLOAT), new FieldStruct("Fishing bonus", Type.FLOAT), new FieldStruct("Fishing bonus cost", Type.FLOAT),
					new FieldStruct("Repair bonus", Type.FLOAT), new FieldStruct("Repair bonus cost", Type.FLOAT), new FieldStruct("Area effect bonus", Type.FLOAT), new FieldStruct("Area effect bonus cost", Type.FLOAT),
					new FieldStruct("Mountain combat bonus", Type.FLOAT), new FieldStruct("Mountain combat bonus cost", Type.FLOAT), new FieldStruct("Population cap bonus", Type.FLOAT), new FieldStruct("Population cap bonus cost", Type.FLOAT),
					new FieldStruct("Area conversion bonus", Type.FLOAT), new FieldStruct("Area conversion bonus cost", Type.FLOAT), new FieldStruct("Cost reduction", Type.FLOAT), new FieldStruct("Cost reduction cost", Type.FLOAT),
					new FieldStruct("Night-time LOS bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Night-tme LOS bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Slavery bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Slavery bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Capitol upgrade bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Capitol upgrade bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Heroes bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Heroes bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 1 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 1 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 2 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 2 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 3 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 3 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 4 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 4 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 5 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 5 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 6 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 6 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 7 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 7 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 8 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 8 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 9 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 9 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 10 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 10 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
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
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
			};
			DB_CIVILIZATION.newEntryValues = new Object[]{
					0, -1, "<New civilization>", 0, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f,
			};
			



			DB_CIV_POWER.fieldStructs = new FieldStruct[]{
					FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Cost", Type.FLOAT),
					ID_UNIT_SET, ID_TECH, new FieldStruct("Set amount", Type.INTEGER), new FieldStruct("GFX Effect", DB_GFX_EFFECTS, 0),
					FieldStruct.UNUSED_INT4, new FieldStruct("Mod amount", Type.FLOAT), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
					FieldStruct.UNUSED_INT4, ID_OBJECT
			};
			


			DB_EFFECTS.nameBuilder = (entry) -> EffectCode.C01_SET_BUTTON.parseValue((int) entry.get(8)).nameBuilder.apply(entry);
			DB_EFFECTS.fieldStructs = new FieldStruct[]{
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Set base attribute", Type.FLOAT), new FieldStruct("Alter base attribute", Type.FLOAT),
					new FieldStruct("Alter attribute multiplier", Type.FLOAT), new FieldStruct("Object 1", DB_OBJECTS, 0), new FieldStruct("Object 2", DB_OBJECTS, 0), ID_UNIT_SET,
					new FieldStruct("Effect code", EffectCode.values()), new FieldStruct("New graphic", DB_GRAPHICS, 0), ID_TECH, new FieldStruct("Attribute code", AttributeCode.values()),
					ID_SOUND, ID_BUTTON, new FieldStruct("New area effect", DB_AREA_EFFECT, 0), FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
					FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
			};
			DB_EFFECTS.newEntryValues = new Object[]{
					0, 0, 0f, 0f, 0f, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, 0,
					0, 0, 0, 0, 0, 0,
			};
			



			DB_OBJECTS.fieldStructs = new FieldStruct[]{
					FieldStruct.NAME, FieldStruct.SEQ_NUMBER, ID_FAMILY, FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNKNOWN_INT4, FieldStruct.ID, new FieldStruct("Health", Type.INTEGER), FieldStruct.UNKNOWN_INT4,
					new FieldStruct("Min range", Type.FLOAT), new FieldStruct("Max range", Type.FLOAT), new FieldStruct("Line of sight", Type.FLOAT), new FieldStruct("Attack reload time", Type.FLOAT),
					new FieldStruct("Area of effect", Type.FLOAT), new FieldStruct("Speed", Type.FLOAT), new FieldStruct("Acceleration/Deceleration", Type.FLOAT), new FieldStruct("Idle turning speed", Type.FLOAT),
					new FieldStruct("Moving turning speed", Type.FLOAT), FieldStruct.UNKNOWN_FLOAT, new FieldStruct("<PROBABLY> Is air unit", Type.INTEGER), new FieldStruct("Flight time", Type.INTEGER),
					FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Attack angle", Type.FLOAT), new FieldStruct("<PROBABLY> Attack from the side", Type.INTEGER), ID_WEAPON_TO_HIT,
					new FieldStruct("Attack", Type.INTEGER), new FieldStruct("Shock armor", Type.INTEGER), new FieldStruct("Arrow armor", Type.INTEGER), new FieldStruct("Pierce armor", Type.INTEGER),
					new FieldStruct("Gun armor", Type.INTEGER), new FieldStruct("Laser armor", Type.INTEGER), new FieldStruct("Missile armor", Type.INTEGER), new FieldStruct("Projectile ID", DB_OBJECTS, 0),
					new FieldStruct("Family attack multiplier index", IntStream.range(0, 66).boxed().toArray(Integer[]::new)), FieldStruct.UNKNOWN_INT4, new FieldStruct("Graphic size", Type.FLOAT), new FieldStruct("Unit type", Type.STRING, 52),
					new FieldStruct("Unit type", UnitType.values()), FieldStruct.UNUSED_INT4, ID_BUTTON, ID_GRAPHIC,
					new FieldStruct("Build rate", Type.FLOAT), new FieldStruct("Iron gather rate", Type.FLOAT), new FieldStruct("Farm gather rate", Type.FLOAT), new FieldStruct("Gold gather rate", Type.FLOAT),
					new FieldStruct("Stone gather rate", Type.FLOAT), new FieldStruct("Wood gather rate", Type.FLOAT), new FieldStruct("Hunt gather rate", Type.FLOAT), new FieldStruct("Forage gather rate", Type.FLOAT),
					new FieldStruct("Creation sound", DB_SOUNDS, 0), new FieldStruct("Action sound 1", DB_SOUNDS, 0), new FieldStruct("Action sound 2", DB_SOUNDS, 0), new FieldStruct("Selection sound 1", DB_SOUNDS, 0),
					new FieldStruct("Selection sound 2", DB_SOUNDS, 0), new FieldStruct("Death sound", DB_SOUNDS, 0), new FieldStruct("<PROBABLY> Can build", Type.BOOLEAN), ID_BUTTON_COMMAND,
					ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND,
					ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND, ID_BUTTON_COMMAND,
					FieldStruct.UNCHANGED_INT4, new FieldStruct("Square occupied", Type.INTEGER), new FieldStruct("Resource type", ResourceType.values()), new FieldStruct("Resource amount", Type.FLOAT),
					new FieldStruct("Always face camera", Type.INTEGER), FieldStruct.UNKNOWN_INT4, new FieldStruct("Rotting time", Type.FLOAT), new FieldStruct("Population count", Type.INTEGER),
					new FieldStruct("Transport capacity", Type.INTEGER), new FieldStruct("Show area effect stat"), new FieldStruct("Show repair stat"), new FieldStruct("Show shock armor stat"),
					new FieldStruct("Show pierce armor stat"), new FieldStruct("Show arrow armor stat"), new FieldStruct("Show laser armor stat"), new FieldStruct("Show gun armor stat"),
					new FieldStruct("Show missile armor stat"), new FieldStruct("Show range", Type.INTEGER), new FieldStruct("Morale bonus", Type.INTEGER), FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, new FieldStruct("Flight altitude", Type.FLOAT),
					FieldStruct.ID_LANGUAGE, FieldStruct.UNKNOWN_INT4, new FieldStruct("Healer heal amount", Type.INTEGER), new FieldStruct("Health regeneration", Type.INTEGER),
					new FieldStruct("Can garrison in air transports"), new FieldStruct("Can garrison in land transports"), new FieldStruct("Can garrison in sea transports"), FieldStruct.UNUSED_INT1,
					new FieldStruct("Object type", ObjectType.values()), FieldStruct.UNKNOWN_INT4, new FieldStruct("Circle selection size", Type.FLOAT), new FieldStruct("Shadow size", Type.FLOAT),
					FieldStruct.UNUSED_INT4, new FieldStruct("Formation size", Type.FLOAT), new FieldStruct("Physical size", Type.FLOAT), new FieldStruct("Interact/obstruct/bump other objects"),
					FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_FLOAT,
					new FieldStruct("Initial projectile angle", Type.FLOAT), new FieldStruct("Ammo amount", Type.INTEGER), new FieldStruct("<All bombers use this>"), FieldStruct.UNKNOWN_BOOL1,
					FieldStruct.UNKNOWN_BOOL1, new FieldStruct("<PROBABLY> Parabolic projectile"), FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
					new FieldStruct("Category (Heroes use 27)", Type.INTEGER), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNKNOWN_INT4, ID_UPGRADE, new FieldStruct("<Used by units/buildings who can convert>", Type.INTEGER), FieldStruct.UNKNOWN_FLOAT,
					new FieldStruct("Can be owned by a player"), new FieldStruct("Can be owned by \"World\""), FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
					new FieldStruct("Can be killed with Delete"), FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_INT1, FieldStruct.UNUSED_INT1,
					new FieldStruct("Low health effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Death effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Start of attack ID", DB_GFX_EFFECTS, 0), ID_GFX_UNKNOWN,
					new FieldStruct("Movement effect", DB_GFX_EFFECTS, 0), ID_GFX_UNKNOWN, new FieldStruct("Heal other effect ID", DB_GFX_EFFECTS, 0), ID_GFX_UNKNOWN,
					ID_GFX_UNKNOWN, new FieldStruct("Get hit effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Permanent effect", DB_GFX_EFFECTS, 0), ID_GFX_UNUSED,
					new FieldStruct("Projectile effect", DB_GFX_EFFECTS, 0), ID_GFX_UNUSED, ID_GFX_UNUSED, ID_GFX_UNUSED,
					ID_GFX_UNUSED, ID_GFX_UNUSED, ID_GFX_UNKNOWN, new FieldStruct("<only used by volcano-projectile and meteor>", Type.INTEGER),
					new FieldStruct("Attack effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Disease effect ID 1", DB_GFX_EFFECTS, 0), new FieldStruct("Disease effect ID 2", DB_GFX_EFFECTS, 0), new FieldStruct("Diffraction shield effect ID", DB_GFX_EFFECTS, 0),
					new FieldStruct("Pulse cannon effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Battle cry effect ID", DB_GFX_EFFECTS, 0), new FieldStruct("Bomb hole effect ID", DB_GFX_EFFECTS, 0), ID_GFX_UNUSED,
					ID_GFX_UNKNOWN, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, new FieldStruct("<only used by ships>", Type.FLOAT),
					FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
					new FieldStruct("Terrain: Grass, Snow"), new FieldStruct("Elevation: Deep water"), new FieldStruct("Terrain: Rock, Stones and Artificial"), FieldStruct.UNKNOWN_BOOL1,
					FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1,
					FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Terrain: Asphalt"), new FieldStruct("Elevation: cliffs"),
					new FieldStruct("Elevation: Shallow water"), FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Terrain: Sand"), new FieldStruct("Terrain: Space"),
					FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_INT1, FieldStruct.UNUSED_INT1,
					ID_AREA_EFFECT, ID_AREA_EFFECT, ID_AREA_EFFECT, ID_AREA_EFFECT,
					FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Carry capacity", Type.FLOAT),
					FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_BOOL1,
					FieldStruct.UNKNOWN_BOOL1, FieldStruct.UNUSED_INT1, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
					new FieldStruct("Debris on death", DB_OBJECTS, 0), new FieldStruct("Debris on death", DB_OBJECTS, 0), FieldStruct.UNKNOWN_INT4, new FieldStruct("Min stealth radius", Type.INTEGER),
					new FieldStruct ("<It seems \"Interaction type\">", Type.INTEGER), new FieldStruct("Max citizens garrison", Type.INTEGER), new FieldStruct("Initial citizens garrison", Type.INTEGER), new FieldStruct("Garrison upgrade object to", DB_TECH_TREE, 0),
					new FieldStruct ("Plane refuel location", Type.INTEGER), new FieldStruct("<Seems related to ships...>", Type.INTEGER), new FieldStruct("Wonder bonus", WonderType.values()), new FieldStruct ("Effect apply to", DB_UNIT_SET, 0),
					FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Friendly damage mult", Type.FLOAT), new FieldStruct("Garrison citizens bonus", Type.FLOAT),
					FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
					new FieldStruct("Num techs it can build", Type.INTEGER, 4, false), ID_TECH, new FieldStruct("Is building?"), new FieldStruct("Stealth"),
					FieldStruct.UNKNOWN_BOOL1, new FieldStruct("<Seems related to aircraft, but the clubman...>", Type.INTEGER, 1), FieldStruct.UNKNOWN_INT4, new FieldStruct("Spawn on death", DB_OBJECTS, 0),
					new FieldStruct("Power", Type.INTEGER), new FieldStruct("Power recover rate", Type.INTEGER), new FieldStruct("Default stance", DB_UNIT_BEHAVIOR, 0), new FieldStruct("Conversion time", Type.INTEGER),
					FieldStruct.UNKNOWN_FLOAT, new FieldStruct("<It seems heal power, but the roman legionnaire...>", Type.FLOAT, 4, Knowledge.UNKNOWN), FieldStruct.UNKNOWN_FLOAT, new FieldStruct("<PROBABLY> Load range", Type.FLOAT),
					new FieldStruct("<PROBABLY> Unload range", Type.FLOAT), new FieldStruct("Can walk through trees"), FieldStruct.UNUSED_BOOL1, FieldStruct.UNKNOWN_BOOL1,
					new FieldStruct("Can attack area"), new FieldStruct("<PROBABLY> Melee attack"), new FieldStruct("Garrison heal rate", Type.INTEGER, 1), FieldStruct.UNKNOWN_INT1, FieldStruct.UNKNOWN_INT1,
					new FieldStruct("Unknown string", Type.STRING, 100), new FieldStruct("Unknown string", Type.STRING, 100),
					FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH,
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
					ID_OBJECT_BUILD_TECH, ID_OBJECT_BUILD_TECH, FieldStruct.UNKNOWN_INT4
			};
			DB_OBJECTS.newEntryValues = new Object[]{
					"<New object>", 0, -1, -1, 0, -1, 0, 0,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0, 0, 0f, 0f, 0, -1,
					0, 0, 0, 0, 0, 0, 0, -1,
					-1, 0, 0f, "<Object type>", 0, 0, -1, -1,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					-1, -1, -1, -1, -1, -1, 0, -1,
					-1, -1, -1, -1, -1, -1, -1, -1,
					-1, 0, 0, 0f, 0, -1, 0f, 0,
					0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0f, -1, 0f,
					0, 0, 0, 0, 0, 0, 0, 204,
					0, 0, 0f, 0f, 0, 0f, 0f, 0,
					1, 204, 204, 0f, 0f, 0, 0, 0,
					0, 0, -1, 0, 0, 0, 0, 0,
					0, 0, 0, 0f, 1, 0, 0, 0,
					0, 0, 204, 204, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, 0f, 0, 0, 0, 0,
					1, 1, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1, 1, 1, 0,
					0, 0, 204, 204, -1, -1, -1, -1,
					0, 0, 0, 0f, 0, 0, 0, 0,
					0, 204, 0, 0, 0, 0, 0, 0,
					0, 0, 0, -1, 0, 0, 0, 0,
					0f, 0f, 0f, 0f, 0f, 0, 0, 0,
					0, -1, 1, 0, 0, 204, 0, 0,
					0, 0, 0, 0, 0f, 0f, 0f, 0f,
					0f, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0,
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
					-1, -1, -1, -1, -1, -1, -1, -1,
					0
			};
			



			DB_TECH_TREE.extraField = ID_TECH_FROM_OBJECT;
			DB_TECH_TREE.fieldStructs = new FieldStruct[]{
					FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Starting epoch", Type.INTEGER),
					new FieldStruct("Ending epoch", Type.INTEGER), new FieldStruct("Event ID", DB_EVENTS, 0), FieldStruct.UNKNOWN_INT4, new FieldStruct("Wood cost", Type.INTEGER),
					new FieldStruct("Stone cost", Type.INTEGER), new FieldStruct("<Only Impassable tile object and Invisible capital>", Type.INTEGER), new FieldStruct("Gold cost", Type.INTEGER), FieldStruct.UNUSED_INT4,
					new FieldStruct("Iron cost", Type.INTEGER), new FieldStruct("Food cost", Type.INTEGER),
					new FieldStruct("Build time", Type.INTEGER), new FieldStruct("Unlocked by tech", DB_TECH_TREE, -1),
					new FieldStruct("Unlocked by power", DB_TECH_TREE, -1), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER), new FieldStruct("Object ID", DB_OBJECTS, -1),
					ID_BUTTON, new FieldStruct("Is object?", Type.INTEGER), FieldStruct.ID_LANGUAGE, new FieldStruct("Tech type", TechType.values()),
					new FieldStruct("<4 in all Epochs, 0 everywhere else>", Type.INTEGER), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Calamity", DB_CALAMITY, 0),
					new FieldStruct("Hotkey ID", DB_UI_HOTKEY, 0), new FieldStruct("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), new FieldStruct("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), FieldStruct.UNUSED_INT4,
					FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
					FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_BOOL1, new FieldStruct("Only in scenario"), new FieldStruct("<All powers and power techs use 0>"),
					new FieldStruct("<All powers and power techs use 0>"), FieldStruct.UNUSED_INT4, new FieldStruct("Epoch number", Type.INTEGER), new FieldStruct("Buildings to advance epoch", Type.INTEGER),
					new FieldStruct("Epoch IDs", Type.INTEGER), new FieldStruct("Last build object", DB_OBJECTS, 0, false), new FieldStruct("Num of tech builders", Type.INTEGER, 4, false)
			};
			DB_TECH_TREE.newEntryValues = new Object[]{
					"<New technology>", -1, 0, 0, 15, -1, -1, 0,
					0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, -1, -1, 0, 0, 0,
					0, 0, 0, 0, -1, 0, 0, 0,
					0, 0f, 0f, 0f, 0f, 0, 0, 1,
					1, 0, 0, 0, 0, -1, 0
			};
			
		}


		
		if (!Core.AOC) {
			printData(ALL_VANILLA);
		} else {
			printData(ALL_AOC);
		}
	}

	private static void printData(DatStructure[] datStructures){
		int count;
		for (DatStructure datStructure : datStructures){
			count = 0;
			for (FieldStruct fieldStruct : datStructure.fieldStructs){
				count += fieldStruct.size;
			}
			System.out.println("\t\t" + datStructure.fileName + ':' + ' ' + count + "  >  fields: " + datStructure.fieldStructs.length + "  |  defaults: " + (datStructure.newEntryValues != null ? datStructure.newEntryValues.length : "null"));
		}
	}







	/** DatFile associated with this DatStructure */
	public DatFile datFile = null;

	/** Name of the structure. Used for GUI purposes. */
	public String name;

	/** Name of the file. It must match exactly the dat filename. */
	public String fileName;

	/** Define the number of elements at the beginning? */
	public boolean defineNumEntries;

	/** The game define a counter "num entries" at the beginning of each group in the file.
	 *  This field alter the counter when reading and writing, to adjust the real number of entries in the file.
	 * For now, only dbtechtree.dat require this, due to its particular structure.
	 * In dbtechtree there is more than one group, and each counter says N, but there are actually N+1 entries (because there's also the "Epoch" entry, which is not counted). */
	public int adjustNumEntries;

	/** Min SequenceNumber for defined objects */
	public int minSeq;

	/** Min ID for defined objects */
	public int minID;

	/** Index of the field which hold the entry name. It's -1 if entries have no name. */
	public int indexName;

	/** Index of the field which hold the entry sequence number. It's -1 entries have no sequence number. */
	public int indexSequence;

	/** Index of the field which hold the entry ID. It's -1 if entries have no ID. */
	public int indexID;

	/** This field define the type/size of extra fields, which are all identical (if the entry size can be dynamic).
	 * Only dbtechtree.dat and dbevent.dat use this.
	 * It's null if not used. */
	public FieldStruct extraField = null;

	/** This array define the description/type/size of all fields of a single entry in the file.
	 * You can expect the sum of the sizes of these entries must match the size of an entry in the file. */
	public FieldStruct[] fieldStructs;

	/** Default values used by Unknown/New entries. */
	public Object[] newEntryValues = null;

	/** Optional function to calculate the name */
	public Function<Entry, String> nameBuilder = null;

	/** Default number of columns for the UI */
	public int defaultColumns;
	
	/** All fields with links */
	public FieldStruct[] linkFields;





	/**
	 * Create a new DatStructure
	 * @param name				Displayed name
	 * @param fileName			File name
	 * @param defineNumEntries	If true, the file contains the number of entries.
	 * @param adjustNumEntries	This is an offset, to adjust the number of entries
	 * @param minSeq			Minimum allowed sequence number
	 * @param minID				Minimum allowed ID
	 * @param indexName			Index of the name (-1 if there's no name)
	 * @param indexSequence		Index of the sequence number (-1 if there's no sequence number)
	 * @param indexID			Index of the ID (-1 if there's no ID)
	 * @param defaultColumns	Default number of columns displayed in the UI.
	 */
	public DatStructure(String name, String fileName, boolean defineNumEntries, int adjustNumEntries, int minSeq, int minID, int indexName, int indexSequence, int indexID, int defaultColumns){
		this.name = name;
		this.fileName = fileName;
		this.defineNumEntries = defineNumEntries;
		this.adjustNumEntries = adjustNumEntries;
		this.minSeq = minSeq;
		this.minID = minID;
		this.indexName = indexName;
		this.indexSequence = indexSequence;
		this.indexID = indexID;
		this.defaultColumns = defaultColumns;
	}



	/**
	 * Gets the index of the field which hold the number of extra fields in the entry
	 * @return	The index of the field which hold the number of extra fields in the entry
	 */
	public int getIndexExtraFields (){
		return extraField != null ? fieldStructs.length - 1 : -1;
	}


	/**
	 * Compare this object with the passed object
	 * @param datStructure	The other object
	 * @return				The result of the comparation
	 */
	public int compareTo(DatStructure datStructure){
		return Integer.signum(name.compareTo(datStructure.name));
	}

	/**
	 * Gets the number of bytes for each entry
	 * @return	The number of bytes for each entry
	 */
	public int getNumBytes (){
		return Arrays.stream(fieldStructs).parallel().mapToInt(x -> x.size).sum();
	}


	@Override
	public String toString(){
		return name;
	}

}
