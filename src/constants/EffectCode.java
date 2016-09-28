package constants;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.function.Function;

import datmanager.Language;
import datstructure.Entry;
import datstructure.Link;


/**
 * Effect codes used in an Effect entry.
 * They defines the action to execute.
 * @author MarcoForlini
 */
public enum EffectCode implements EnumValue {

	/** Do nothing */
	NONE ("None", -1),
	/** Assign a new dbbutton entry to this object */
	C01_SET_BUTTON ("Set button", 1),
	/** Set/Mod the given attribute */
	C02_ALTER_ATTRIBUTE ("Alter attribute", 2),
	/** Unknown effect */
	C03_UNKNOWN ("Unknown effect", 3),
	/**
	 * Unknown effect. It's only used by entry 1358 (which is unused by the
	 * game)
	 */
	C04_UNKNOWN ("Unknown effect", 4),
	/** Unknown effect */
	C05_UNKNOWN ("Unknown effect", 5),
	/** Assign a new dbgraphic entry to this object */
	C06_SET_GRAPHIC ("Set graphic", 6),
	/** Unknown effect */
	C07_UNKNOWN ("Unknown effect", 7),
	/** Enable the selected tech */
	C08_ENABLE_TECH ("Enable tech", 8),
	/** Disable the selected tech */
	C09_DISABLE_TECH ("Disable tech", 9),
	/** Special effect used by entries 237 and 238 */
	C10_START_GAME ("Start game", 10),
	/** Unknown effect */
	C11_UNKNOWN ("Unknown effect", 11),
	/** Assign a new GUI background */
	C12_GUI_BACKGROUND ("GUI Background", 12),
	/** Unknown effect */
	C13_UNKNOWN ("Unknown effect", 13),
	/** Unknown effect */
	C14_UNKNOWN ("Unknown effect", 14),
	/** Assign a new action sound in slot 1 */
	C15_SET_ACTION_SOUND_1 ("Set action sound 1", 15),
	/** Unknown effect */
	C16_UNKNOWN ("Unknown effect", 16),
	/** Assign a new death sound */
	C17_SET_DEATH_SOUND ("Set death sound", 17),
	/** Assign a new selection sound in slot 1 */
	C18_SET_SELECTION_SOUND_1 ("Set selection sound 1", 18),
	/**
	 * Replace all objects of the given class with objects of another given
	 * class and tech (they will inherit health, power and upgrades)
	 */
	C19_UPDGRADE_ALL_OBJECTS ("Upgrade objects", 19),
	/** Assign a new action sound in slot 2 */
	C20_SET_ACTION_SOUND_2 ("Set action sound 2", 20),
	/** Assign a new selection sound in slot 2 */
	C21_SET_SELECTION_SOUND_2 ("Set selection sound 2", 21),
	/**
	 * Replace the current object with an object from the given class (it will
	 * inherit health and power, but not the upgrades)
	 */
	C22_REPLACE_OBJECTS ("Replace objects", 22),;

	/** Name to be shown in the UI */
	public final String name;

	/** Code used in the dat files */
	public final int code;

	/** Function which build the name of the entry */
	public Function <Entry, String> nameBuilder;

	EffectCode (String effectName, int effectCode) {
		name = effectName;
		code = effectCode;
		switch (effectCode) {
			case -1:
				nameBuilder = entry -> name;
				break;
			case 1:
				nameBuilder = entry -> name + ":  " + getObjectSetTech (entry) + "  >  " + getButton (entry);
				break;
			case 2:
				nameBuilder = entry -> name + ":  " + getAttribute (entry);
				break;
			case 6:
				nameBuilder = entry -> name + ":  " + getObjectTech (entry) + "  >  " + getGraphic (entry);
				break;
			case 8:
			case 9:
				nameBuilder = entry -> name + ":  " + getTech (entry);
				break;
			case 12:
				nameBuilder = entry -> name + ":  " + getGraphic (entry);
				break;
			case 15:
			case 17:
			case 18:
			case 20:
			case 21:
				nameBuilder = entry -> name + ":  " + getObjectSet (entry) + "  >  " + getSound (entry);
				break;
			case 19:
			case 22:
				nameBuilder = entry -> name + ":  " + getObjectSet (entry) + "  >  " + getObject2 (entry);
				break;
			default:
				nameBuilder = entry -> name;
		}
	}

	@Override
	public String getName () {
		return name;
	}

	@Override
	public int getCode () {
		return code;
	}

	@Override
	public boolean isValid (int code) {
		return code >= -1 && code <= 22;
	}

	@Override
	public EffectCode parseValue (int code) {
		switch (code) {
			case -1:
				return NONE;
			case 1:
				return C01_SET_BUTTON;
			case 2:
				return C02_ALTER_ATTRIBUTE;
			case 3:
				return C03_UNKNOWN;
			case 4:
				return C04_UNKNOWN;
			case 5:
				return C05_UNKNOWN;
			case 6:
				return C06_SET_GRAPHIC;
			case 7:
				return C07_UNKNOWN;
			case 8:
				return C08_ENABLE_TECH;
			case 9:
				return C09_DISABLE_TECH;
			case 10:
				return C10_START_GAME;
			case 11:
				return C11_UNKNOWN;
			case 12:
				return C12_GUI_BACKGROUND;
			case 13:
				return C13_UNKNOWN;
			case 14:
				return C14_UNKNOWN;
			case 15:
				return C15_SET_ACTION_SOUND_1;
			case 16:
				return C16_UNKNOWN;
			case 17:
				return C17_SET_DEATH_SOUND;
			case 18:
				return C18_SET_SELECTION_SOUND_1;
			case 19:
				return C19_UPDGRADE_ALL_OBJECTS;
			case 20:
				return C20_SET_ACTION_SOUND_2;
			case 21:
				return C21_SET_SELECTION_SOUND_2;
			case 22:
				return C22_REPLACE_OBJECTS;
			default:
				return null;
		}
	}

	@Override
	public String toString () {
		return buildUIName ();
	}

	/**
	 * Extract the object/set from the given entry
	 * @param entry The entry
	 * @return The object /set
	 */
	public static Entry getObjectSet (Entry entry) {
		Link link;
		link = entry.get (5);
		if (link.target.isDefined ()) {
			return link.target;
		}
		link = entry.get (7);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the object/tech from the given entry
	 * @param entry The entry
	 * @return The object from/tech
	 */
	public static Entry getObjectTech (Entry entry) {
		Link link;
		link = entry.get (5);
		if (link.target.isDefined ()) {
			return link.target;
		}
		link = entry.get (10);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the object/set/tech from the given entry
	 * @param entry The entry
	 * @return The object from/set/tech
	 */
	public static Entry getObjectSetTech (Entry entry) {
		Link link;
		link = entry.get (5);
		if (link.target.isDefined ()) {
			return link.target;
		}
		link = entry.get (7);
		if (link.target.isDefined ()) {
			return link.target;
		}
		link = entry.get (10);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the object 1 from the given entry
	 * @param entry The entry
	 * @return The object 1
	 */
	public static Entry getObject1 (Entry entry) {
		Link link = entry.get (5);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the object 2 from the given entry
	 * @param entry The entry
	 * @return The object 2
	 */
	public static Entry getObject2 (Entry entry) {
		Link link = entry.get (6);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the graphic from the given entry
	 * @param entry The entry
	 * @return The graphic
	 */
	public static Entry getGraphic (Entry entry) {
		Link link = entry.get (9);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the tech from the given entry
	 * @param entry The entry
	 * @return The tech
	 */
	public static Entry getTech (Entry entry) {
		Link link = entry.get (10);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the sound from the given entry
	 * @param entry The entry
	 * @return The sound
	 */
	public static Entry getSound (Entry entry) {
		Link link = entry.get (12);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the button from the given entry
	 * @param entry The entry
	 * @return The button
	 */
	public static Entry getButton (Entry entry) {
		Link link = entry.get (13);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the area effect from the given entry
	 * @param entry The entry
	 * @return The area effect
	 */
	public static Entry getAreaEffect (Entry entry) {
		Link link = entry.get (14);
		if (link.target.isDefined ()) {
			return link.target;
		}
		return Entry.nullEntry;
	}

	/**
	 * Extract the area effect from the given entry
	 * @param entry The entry
	 * @return The area effect
	 */
	public static TerrainFamily getTerrainFamily (Entry entry) {
		int code = entry.get (15);
		return TerrainFamily.C00_AMBIENT.parseValue (code);
	}

	/**
	 * Get the attribute name/code
	 * @param entry The entry
	 * @return The attribute name/code
	 */
	public static Object getAttribute (Entry entry) {
		int attributeCode = (int) entry.get (11);
		AttributeCode attribute = AttributeCode.C01_ATTACK.parseValue (attributeCode);
		if (attribute != null) {
			switch (attribute) {
				case C23_POP_LIMIT:
				case C38_COMMERCIAL_TAXES:
					return attribute.name + ' ' + getAttributeValue (entry);
				case C40_AREA_EFFECT:
					return getObjectSet (entry) + "  >  " + attribute.name + " = " + getAreaEffect (entry);
				case C42_TERRAIN_FAMILY:
					return getObjectSet (entry) + "  >  " + attribute.name + " (" + getTerrainFamily (entry) + ") = "
							+ ((Float) entry.get (2) > 0 ? "enable" : "disable");
				case C45_NAME:
					Float langID = entry.get (2);
					if (langID > 0) {
						return getObjectSet (entry) + "  >  " + attribute.name + " = "
								+ Language.MAP.get (langID.intValue ());
					}
					return getObjectSet (entry) + "  >  " + attribute.name + " = (" + langID + ')';
				default:
					return getObjectSetTech (entry) + "  >  " + attribute.name + getAttributeValue (entry);
			}
		}
		return getObjectSetTech (entry) + "  >  " + attributeCode;
	}

	private static DecimalFormat df = new DecimalFormat ("#.##");
	static {
		df.setRoundingMode (RoundingMode.HALF_DOWN);
		df.setDecimalSeparatorAlwaysShown (false);
		df.setPositivePrefix ("+");
	}

	/**
	 * Build and return a string which represents the attribute's
	 * set/alter/alter mult value
	 * @param entry The entry
	 * @return The string for the attribute's value
	 */
	public static String getAttributeValue (Entry entry) {
		float val = entry.get (2); // Set value to "val"
		if (val != 0) {
			return " = " + val;
		}
		val = entry.get (3); // Alter value by "val"
		if (val != 0) {
			return (val > 0 ? " + " + val : " - " + -val);
		}
		val = 100 * (Float) entry.get (4); // Alter multiplier by "val"
		return ' ' + df.format (val) + '%';
	}

}
