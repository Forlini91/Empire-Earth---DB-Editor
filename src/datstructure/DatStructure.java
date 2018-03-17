package datstructure;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import datmanager.Core;
import datmanager.DatFile;
import datmanager.Settings;
import datstructure.DatStructureParse.ParseState;
import datstructure.structures.AIUnitTargeting;
import datstructure.structures.AmbientSounds;
import datstructure.structures.Animals;
import datstructure.structures.AreaEffect;
import datstructure.structures.Buttons;
import datstructure.structures.CPBehavior;
import datstructure.structures.Calamity;
import datstructure.structures.CivPower;
import datstructure.structures.Civilization;
import datstructure.structures.CliffTerrain;
import datstructure.structures.ColorTable;
import datstructure.structures.Effects;
import datstructure.structures.Events;
import datstructure.structures.Family;
import datstructure.structures.GFXEffects;
import datstructure.structures.GameVariant;
import datstructure.structures.Graphics;
import datstructure.structures.Music;
import datstructure.structures.Objects;
import datstructure.structures.PremadeCivs;
import datstructure.structures.RandomMap;
import datstructure.structures.Sounds;
import datstructure.structures.StartingResourches;
import datstructure.structures.TechTree;
import datstructure.structures.Terrain;
import datstructure.structures.TerrainGrayTextures;
import datstructure.structures.TerrainType;
import datstructure.structures.UIBack;
import datstructure.structures.UIControlEvents;
import datstructure.structures.UIControls;
import datstructure.structures.UIFonts;
import datstructure.structures.UIFormEvents;
import datstructure.structures.UIForms;
import datstructure.structures.UIHotkey;
import datstructure.structures.UnitBehavior;
import datstructure.structures.UnitSet;
import datstructure.structures.Upgrade;
import datstructure.structures.WeaponToHit;
import datstructure.structures.World;


/**
 * Represents the structure of the entries of a file
 *
 * @author MarcoForlini
 */
public abstract class DatStructure {

	/** All structures used by Vanilla files */
	public static final DatStructure[] ALL_FILES;
	static {
		if (!Core.AOC) {
			ALL_FILES = new DatStructure[] {
					AIUnitTargeting.instance, AmbientSounds.instance, Animals.instance, AreaEffect.instance, Buttons.instance,
					Calamity.instance, Civilization.instance, CliffTerrain.instance, ColorTable.instance, CPBehavior.instance,
					Effects.instance, Events.instance, Family.instance, GameVariant.instance, GFXEffects.instance,
					Graphics.instance, Music.instance, Objects.instance, PremadeCivs.instance, RandomMap.instance,
					Sounds.instance, StartingResourches.instance, TechTree.instance, Terrain.instance, TerrainGrayTextures.instance,
					TerrainType.instance, UIBack.instance, UIControlEvents.instance, UIControls.instance, UIFonts.instance,
					UIFormEvents.instance, UIForms.instance, UIHotkey.instance, UnitBehavior.instance, UnitSet.instance,
					Upgrade.instance, WeaponToHit.instance, World.instance
			};
		} else {
			ALL_FILES = new DatStructure[] {
					AIUnitTargeting.instance, AmbientSounds.instance, Animals.instance, AreaEffect.instance, Buttons.instance,
					Calamity.instance, Civilization.instance, CivPower.instance, CliffTerrain.instance, ColorTable.instance,
					CPBehavior.instance, Effects.instance, Events.instance, Family.instance, GameVariant.instance,
					GFXEffects.instance, Graphics.instance, Music.instance, Objects.instance, PremadeCivs.instance,
					RandomMap.instance, Sounds.instance, StartingResourches.instance, TechTree.instance, Terrain.instance,
					TerrainGrayTextures.instance, TerrainType.instance, UIBack.instance, UIControlEvents.instance, UIControls.instance,
					UIFonts.instance, UIFormEvents.instance, UIForms.instance, UIHotkey.instance, UnitBehavior.instance,
					UnitSet.instance, Upgrade.instance, WeaponToHit.instance, World.instance
			};
		}
	}



	/** Unique field: A 4 bytes integer which point to an area effect's ID. */
	public static final FieldStruct	ID_AREA_EFFECT			= new FieldStruct ("Area Effect ID", AreaEffect.instance, 0);
	/** Unique field: A 4 bytes integer which point to a button's ID. */
	public static final FieldStruct	ID_BUTTON				= new FieldStruct ("Button ID", Buttons.instance, 0);
	/** Unique field: A 4 bytes integer which point to a family's ID. */
	public static final FieldStruct	ID_FAMILY				= new FieldStruct ("Family ID", Family.instance, 0);
	/** Unique field: A 4 bytes integer which point to a graphic's ID. */
	public static final FieldStruct	ID_GRAPHIC				= new FieldStruct ("Graphic ID", Graphics.instance, 0);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct	ID_OBJECT				= new FieldStruct ("Object ID", Objects.instance, 0);
	/** Unique field: A 4 bytes integer which point to a sound ID. */
	public static final FieldStruct	ID_SOUND				= new FieldStruct ("Sound ID", Sounds.instance, 0);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct	ID_TECH					= new FieldStruct ("Tech ID", TechTree.instance, 0);
	/** Unique field: A 4 bytes integer which point to a terrain's ID. */
	public static final FieldStruct	ID_TERRAIN				= new FieldStruct ("Terrain ID", Terrain.instance, 0);
	/** Unique field: A 4 bytes integer which point to an hotkey's ID. */
	public static final FieldStruct	ID_UI_HOTKEY			= new FieldStruct ("Hotkey ID", UIHotkey.instance, 0);
	/** Unique field: A 4 bytes integer which point to an hotkey's ID. */
	public static final FieldStruct	ID_UI_FORM				= new FieldStruct ("Form ID", UIForms.instance, 0);
	/** Unique field: A 4 bytes integer which point to an unit set's ID. */
	public static final FieldStruct	ID_UI_FONT				= new FieldStruct ("Font ID", UIFonts.instance, 0);
	/** Unique field: A 4 bytes integer which point to an unit set's ID. */
	public static final FieldStruct	ID_UNIT_SET				= new FieldStruct ("Unit set ID", UnitSet.instance, 0);
	/** Unique field: A 4 bytes integer which point to an upgrade's ID. */
	public static final FieldStruct	ID_UPGRADE				= new FieldStruct ("Updrade ID", Upgrade.instance, 0);
	/** Unique field: A 4 bytes integer which point to a weapon to hit's ID. */
	public static final FieldStruct	ID_WEAPON_TO_HIT		= new FieldStruct ("Weapon to hit ID", WeaponToHit.instance, 0);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct	ID_TECH_FROM_OBJECT		= new FieldStruct ("Build from object", Objects.instance, 0);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct	ID_OBJECT_BUILD_TECH	= new FieldStruct ("Can build tech", TechTree.instance, 0);
	/** Unique field: A 4 bytes integer which point to a button's ID. */
	public static final FieldStruct	ID_BUTTON_COMMAND		= new FieldStruct ("Button/Command ID", Buttons.instance, 0);
	/** Special field: A 4 bytes float which define an unused ID. */
	public static final FieldStruct	ID_GFX_UNUSED			= new FieldStruct ("Unused GFX effect", GFXEffects.instance, 0, Knowledge.NEVER_USED);
	/** Special field: A 4 bytes float which define a (still) unknown ID. */
	public static final FieldStruct	ID_GFX_UNKNOWN			= new FieldStruct ("Unknown GFX effect", GFXEffects.instance, 0, Knowledge.UNKNOWN);





	/**
	 * Initialize the structures outside the constructor.
	 * This is needed as files can reference each other.
	 * Example: dbObject and dbTechTree reference each other.
	 * if we initialize the fields in the constructor, then one of them will try
	 * to access the other {@link DatStructure} and call its constructor, which
	 * in turn have a field which try to access this object, which is still in
	 * the constructor and so it's not defined yet, causing a runtime error.
	 * else if we delay the fields initialization like this, then the objects
	 * are already defined and stored in the variables, so they can be accessed
	 * without problems.
	 */
	public static void initAllStructures () {
		if (Settings.DEBUG) {
			System.out.println ("Initialize structures");
		}
		for (DatStructure datStructure : ALL_FILES) {
			datStructure.init ();
			datStructure.afterInit ();
			if (Settings.DEBUG) {
				datStructure.debugDatStructure ();
			}
		}
		if (Settings.DEBUG) {
			System.out.println ("Initialized all " + ALL_FILES.length + " structures");
		}
	}




	/** DatFile associated with this DatStructure */
	public DatFile					datFile			= null;

	/** Name of the structure. Used for GUI purposes. */
	public final String				name;

	/** Name of the file. It must match exactly the dat filename. */
	public final String				fileName;

	/** Define the number of elements at the beginning? */
	public final boolean			defineNumEntries;

	/**
	 * The game define a counter "num entries" at the beginning of each group in
	 * the file.
	 * This field alter the counter when reading and writing, to adjust the real
	 * number of entries in the file.
	 * For now, only dbtechtree.dat require this, due to its particular
	 * structure.
	 * In dbtechtree there is more than one group, and each counter says N, but
	 * there are actually N+1 entries (because there's also the "Epoch" entry,
	 * which is not counted).
	 */
	public final int				adjustNumEntries;

	/** Min SequenceNumber for defined objects */
	public final int				minSeq;

	/** Min ID for defined objects */
	public final int				minID;

	/**
	 * Index of the field which hold the entry name. It's -1 if entries have no
	 * name.
	 */
	public final int				indexName;

	/**
	 * Index of the field which hold the entry sequence number. It's -1 entries
	 * have no sequence number.
	 */
	public final int				indexSequence;

	/**
	 * Index of the field which hold the entry ID. It's -1 if entries have no
	 * ID.
	 */
	public final int				indexID;

	/**
	 * This field define the type/size of extra fields, which are all identical
	 * (if the entry size can be dynamic).
	 * Only dbtechtree.dat and dbevent.dat use this.
	 * It's null if not used.
	 */
	public FieldStruct				extraField		= null;

	/**
	 * This array define the description/type/size of all fields of a single
	 * entry in the file.
	 * You can expect the sum of the sizes of these entries must match the size
	 * of an entry in the file.
	 */
	public FieldStruct[]			fieldStructs;

	/** Default values used by Unknown/New entries. */
	public Object[]					newEntryValues	= null;

	/** Optional function to calculate the name */
	public Function <Entry, String>	nameBuilder		= null;

	/** Default number of columns for the UI */
	public final int				defaultColumns;

	/** Size of the groups list and the Reset button in the editor */
	public final int				guiGroupsListSize;

	/** Size of the entries list and the Save Entry button in the editor */
	public final int				guiEntriesListSize;



	// Calculated fields
	/** Base size of an entry in bytes */
	public int					entrySize;

	/** All fields with dynamic size */
	public FieldStruct[]		dynamicSizeFields;

	public Set <DatStructure>	requirements;





	/**
	 * Create a new DatStructure
	 *
	 * @param name Displayed name
	 * @param fileName File name
	 * @param defineNumEntries If true, the file contains the number of entries.
	 * @param adjustNumEntries This is an offset, to adjust the number of entries
	 * @param minSeq Minimum allowed sequence number
	 * @param minID Minimum allowed ID
	 * @param indexName Index of the name (-1 if there's no name)
	 * @param indexSequence Index of the sequence number (-1 if there's no sequence number)
	 * @param indexID Index of the ID (-1 if there's no ID)
	 * @param defaultColumns Default number of columns displayed in the UI.
	 * @param guiGroupsListSize Size of the groups list and the Reset button in the editor
	 * @param guiEntriesListSize Size of the entries list and the Save Entry button in the editor
	 */
	public DatStructure (String name, String fileName, boolean defineNumEntries, int adjustNumEntries, int minSeq, int minID, int indexName, int indexSequence, int indexID, int defaultColumns, int guiGroupsListSize, int guiEntriesListSize) {
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
		this.guiGroupsListSize = guiGroupsListSize;
		this.guiEntriesListSize = guiEntriesListSize;
	}



	/**
	 * Gets the index of the field which hold the number of extra fields in the
	 * entry
	 *
	 * @return The index of the field which hold the number of extra fields in
	 *         the entry
	 */
	public abstract int indexExtraFields ();



	/**
	 * Initialize the structure of the file
	 */
	public abstract void init ();



	/**
	 * Initialize some (redundant) useful fields to anticipate some calculations
	 */
	public void afterInit () {
		entrySize = Arrays.stream (fieldStructs).parallel ().mapToInt (x -> x.size).sum ();
		dynamicSizeFields = Arrays.stream (fieldStructs).parallel ().filter (x -> x.indexSize >= 0).toArray (FieldStruct[]::new);
		requirements = Arrays.stream (fieldStructs).parallel ().filter (x -> x.type == FieldType.LINK).map (x -> x.linkToStruct).collect (Collectors.toSet ());
	}



	private void debugDatStructure () {
		Class <? extends DatStructure> cl = getClass ();
		ParseState parseState;
		boolean noAnnotation = false;
		if (cl.isAnnotationPresent (DatStructureParse.class)) {
			noAnnotation = true;
			DatStructureParse parse = cl.getAnnotation (DatStructureParse.class);
			parseState = Core.AOC ? parse.AOC () : parse.Vanilla ();
		} else {
			parseState = ParseState.MISSING_UNKNOWN;
		}

		int count = 0;
		for (FieldStruct fieldStruct : fieldStructs) {
			count += fieldStruct.size;
		}
		System.out.println ("\t\tInitialize: " + String.format ("%1$-25s", fileName) + "   >   parse state = " + String.format ("%1$-15s", parseState.toString ()) + "  |  entry size (bytes) = " + String.format ("%1$-4d", count) + "  |  num. fields: " + String.format ("%1$-3d", fieldStructs.length) + "  |  defaults: " + (newEntryValues != null ? newEntryValues.length : "null"));

		switch (parseState) {
			case COMPLETE:
				if (Arrays.stream (fieldStructs).anyMatch (fs -> fs.knowledge == Knowledge.UNKNOWN)) {
					Core.printError (null, "The file " + this + " has been marked as \"completely parsed\" but there are still some unknown fields!", "Error in the definitions");
				} else if (Arrays.stream (fieldStructs).anyMatch (fs -> fs.knowledge != Knowledge.KNOWN)) {
					Core.printError (null, "The file " + this + " has been marked as \"completely parsed\" but there are still some unused fields!", "Error in the definitions");
				}
				break;

			case UNKNOWN_PARSED:
				if (Arrays.stream (fieldStructs).anyMatch (fs -> fs.knowledge == Knowledge.UNKNOWN)) {
					Core.printError (null, "The file " + this + " has been marked as \"unknown parsed\" but there are still some unknown fields!", "Error in the definitions");
				} else if (Arrays.stream (fieldStructs).allMatch (fs -> fs.knowledge == Knowledge.KNOWN)) {
					Core.printWarning (null, "The file " + this + " has been marked as \"unknown parsed\" but it's complete instead!", "Warning in the definitions");
				}
				break;

			case MISSING_UNKNOWN:
				if (!Arrays.stream (fieldStructs).anyMatch (fs -> fs.knowledge == Knowledge.UNKNOWN)) {
					if (Arrays.stream (fieldStructs).anyMatch (fs -> fs.knowledge != Knowledge.KNOWN)) {
						if (noAnnotation) {
							Core.printWarning (null, "The file " + this + " should be marked as \"unknown parsed\".\nAdd the annotation \"@DatStructureParse\" to the class definition", "Missing definition");
						} else {
							Core.printError (null, "The file " + this + " has been marked as \"missing unknown\" but all unknown have been parsed instead (but it's not fully parsed yet)!", "Error in the definitions");
						}
					} else {
						if (noAnnotation) {
							Core.printWarning (null, "The file " + this + " should be marked as \"complete\".\nAdd the annotation \"@DatStructureParse\" to the class definition", "Missing definition");
						} else {
							Core.printError (null, "The file " + this + " has been marked as \"missing unknown\" but it's complete instead.", "Error in the definitions");
						}
					}
				}
		}
	}



	/**
	 * Compare this object with the passed object
	 *
	 * @param datStructure The other object
	 * @return The result of the comparation
	 */
	public int compareTo (DatStructure datStructure) {
		return Integer.signum (name.compareTo (datStructure.name));
	}


	@Override
	public String toString () {
		return name;
	}

	/**
	 * If true, the entries in this DatStructure use custom ways to show their name
	 *
	 * @return true if there's a custom way to show the name of the entries
	 */
	public abstract boolean hasCustomEntryName ();

	/**
	 * Calculates and return the custom name for this entry, if any
	 *
	 * @param index index of the entry
	 * @param values values of the entry
	 * @return the custom name, if any, null otherwise
	 */
	public abstract String getCustomEntryName (int index, List <Object> values);


	/**
	 * Calculates and return the custom description for this entry, if any
	 *
	 * @param entry the entry
	 * @return the entry description, if any, null otherwise
	 */
	public abstract String getEntryDescription (Entry entry);


	public FieldStruct getFieldStruct (int index) {
		if (index < 0) {
			return null;
		} else if (index < fieldStructs.length) {
			return fieldStructs[index];
		} else if (extraField != null) {
			return extraField;
		} else {
			throw new IllegalArgumentException ("Can't find the a field with this index > " + index);
		}
	}

}
