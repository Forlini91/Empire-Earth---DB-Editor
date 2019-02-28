package datstructure;

import java.awt.Color;

import constants.EnumValue;
import datmanager.Util;
import gui.GUI;


/**
 * This object define a single field data: description, type, size, ...
 *
 * @author MarcoForlini
 */
public class FieldStruct implements Comparable <FieldStruct>, Cloneable {

	/** Common field: A 100 chars String which define the entry's name. */
	public static final FieldStruct	NAME		= new FieldStruct ("Name", FieldType.STRING, 100, Knowledge.KNOWN, GUI.COLOR_FIELD_ID);
	/** Common field: A 4 bytes integer which define the entry's sequence number. */
	public static final FieldStruct	SEQ_NUMBER	= new FieldStruct ("Sequence number", FieldType.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_ID);
	/** Common field: A 4 bytes integer which define the entry's ID. */
	public static final FieldStruct	ID			= new FieldStruct ("ID", FieldType.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_ID);
	/** Unique field: A 4 bytes integer which point to an entry in the file Language.dll */
	public static final FieldStruct	ID_LANGUAGE	= new FieldStruct ("Language ID", FieldType.LANGUAGE);
	/** Common field: A 4 bytes integer which alter the lenght of a string field */
	public static final FieldStruct	STRING_SIZE	= new FieldStruct ("String size", FieldType.INTEGER, 4, false);
	/** Alter health percent, for Game Variant */



	/** Special field: A 1 byte boolean which define an unknown field which always hold the same value. */
	public static final FieldStruct	UNCHANGED_BOOL1	= new FieldStruct ("Never changes", FieldType.BOOLEAN, 1, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 4 bytes boolean which define an unknown field which always hold the same value. */
	public static final FieldStruct	UNCHANGED_BOOL4	= new FieldStruct ("Never changes", FieldType.BOOLEAN, 4, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 1 byte integer which define an unknown field which always hold the same value. */
	public static final FieldStruct	UNCHANGED_INT1	= new FieldStruct ("Never changes", FieldType.INTEGER, 1, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 4 bytes integer which define an unknown field which always hold the same value. */
	public static final FieldStruct	UNCHANGED_INT4	= new FieldStruct ("Never changes", FieldType.INTEGER, 4, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 4 bytes float which define an unknown field which always hold the same value. */
	public static final FieldStruct	UNCHANGED_FLOAT	= new FieldStruct ("Never changes", FieldType.FLOAT, 4, Knowledge.NEVER_CHANGE, Color.GRAY);

	/** Special field: A 1 byte boolean which define an unknown and never used field. */
	public static final FieldStruct	UNUSED_BOOL1	= new FieldStruct ("Never used", FieldType.BOOLEAN, 1, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 4 bytes boolean which define an unknown and never used field. */
	public static final FieldStruct	UNUSED_BOOL4	= new FieldStruct ("Never used", FieldType.BOOLEAN, 4, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 1 byte integer which define an unknown and never used field. */
	public static final FieldStruct	UNUSED_INT1		= new FieldStruct ("Never used", FieldType.INTEGER, 1, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 4 bytes integer which define an unknown and never used field. */
	public static final FieldStruct	UNUSED_INT4		= new FieldStruct ("Never used", FieldType.INTEGER, 4, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 4 bytes float which define an unknown and never used field. */
	public static final FieldStruct	UNUSED_FLOAT	= new FieldStruct ("Never used", FieldType.FLOAT, 4, Knowledge.NEVER_USED, Color.GRAY);

	/** Special field: A 1 byte boolean which define a (still) unknown field. */
	public static final FieldStruct	UNKNOWN_BOOL1	= new FieldStruct ("Unknown", FieldType.BOOLEAN, 1, Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 4 bytes boolean which define a (still) unknown field. */
	public static final FieldStruct	UNKNOWN_BOOL4	= new FieldStruct ("Unknown", FieldType.BOOLEAN, 4, Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 1 byte integer which define a (still) unknown field. */
	public static final FieldStruct	UNKNOWN_INT1	= new FieldStruct ("Unknown", FieldType.INTEGER, 1, Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 4 bytes integer which define a (still) unknown field. */
	public static final FieldStruct	UNKNOWN_INT4	= new FieldStruct ("Unknown", FieldType.INTEGER, 4, Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 4 bytes float which define a (still) unknown field. */
	public static final FieldStruct	UNKNOWN_FLOAT	= new FieldStruct ("Unknown", FieldType.FLOAT, 4, Knowledge.UNKNOWN, Color.RED);





	/** Name of the field. */
	public final String			name;
	/** Description of the field */
	public final String			description;
	/** Type of the value contained in the field. */
	public final FieldType			type;
	/** Size of the field in bytes. */
	public final int			size;
	/** Information about our knowledge about this field */
	public final Knowledge		knowledge;
	/** If false, the user shouldn't touch this field */
	public final boolean		editable;
	/** Color used in the GUI for this field */
	public final Color			color;
	/** Link to the given structure. Only used by ID fields */
	public final DatStructure	linkToStruct;
	/** Fixed list of available values */
	public final EnumValue[]	enumValues;
	/** Fixed array of values */
	public final Integer[]		arrValues;
	/** Default value to the given field, when no ID is selected */
	public final Object			defaultValue;
	/** Index of the field which hold the size of the string field. (Yeah... some strings length is not a fixed 100) */
	public final int			indexSize;




	/**
	 * Creates a new {@link FieldStruct} with all values
	 *
	 * @param name
	 * @param description
	 * @param type
	 * @param size
	 * @param knowledge
	 * @param color
	 * @param editable
	 * @param linkToStruct
	 * @param enumValues
	 * @param arrValues
	 * @param defaultValue
	 * @param indexSize
	 */
	public FieldStruct (String name, String description, FieldType type, int size, Knowledge knowledge, Color color, boolean editable,
			DatStructure linkToStruct, EnumValue[] enumValues, Integer[] arrValues, Object defaultValue, int indexSize) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.color = color;
		this.editable = editable;
		this.linkToStruct = linkToStruct;
		this.enumValues = enumValues;
		this.arrValues = arrValues;
		this.indexSize = indexSize;
		this.defaultValue = defaultValue;
	}


	/**
	 * Create a new boolean FieldStruct with the given name
	 *
	 * @param name Name of the field
	 */
	public FieldStruct (String name) {
		this (name, null, FieldType.BOOLEAN, 1, Knowledge.KNOWN, Color.BLACK, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new boolean FieldStruct with the given name
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 */
	public FieldStruct (String name, String description) {
		this (name, description, FieldType.BOOLEAN, 1, Knowledge.KNOWN, Color.BLACK, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new 4 bytes FieldStruct with the given name and type
	 *
	 * @param name Name of the field
	 * @param type Type of the field
	 */
	public FieldStruct (String name, FieldType type) {
		this (name, null, type, 4, Knowledge.KNOWN, Color.BLACK, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new 4 bytes FieldStruct with the given name and type
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param type Type of the field
	 */
	public FieldStruct (String name, String description, FieldType type) {
		this (name, description, type, 4, Knowledge.KNOWN, Color.BLACK, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name Name of the field
	 * @param type Type of the field
	 * @param size Size of the field
	 */
	public FieldStruct (String name, FieldType type, int size) {
		this (name, null, type, size, Knowledge.KNOWN, Color.BLACK, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param type Type of the field
	 * @param size Size of the field
	 */
	public FieldStruct (String name, String description, FieldType type, int size) {
		this (name, description, type, size, Knowledge.KNOWN, Color.BLACK, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name Name of the field
	 * @param type Type of the field
	 * @param size Size of the field
	 * @param editable If false, the field can't be edited
	 */
	public FieldStruct (String name, FieldType type, int size, boolean editable) {
		this (name, null, type, size, Knowledge.KNOWN, Color.BLACK, editable, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param type Type of the field
	 * @param size Size of the field
	 * @param editable If false, the field can't be edited
	 */
	public FieldStruct (String name, String description, FieldType type, int size, boolean editable) {
		this (name, description, type, size, Knowledge.KNOWN, Color.BLACK, editable, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name Name of the field
	 * @param type Type of the field
	 * @param size Size of the field
	 * @param knowledge The current knowledge we have about this field
	 */
	public FieldStruct (String name, FieldType type, int size, Knowledge knowledge) {
		this (name, null, type, size, knowledge, Color.BLACK, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param type Type of the field
	 * @param size Size of the field
	 * @param knowledge The current knowledge we have about this field
	 */
	public FieldStruct (String name, String description, FieldType type, int size, Knowledge knowledge) {
		this (name, description, type, size, knowledge, Color.BLACK, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name Name of the field
	 * @param type Type of the field
	 * @param size Size of the field
	 * @param knowledge The current knowledge we have about this field
	 * @param color The color used by the field
	 */
	public FieldStruct (String name, FieldType type, int size, Knowledge knowledge, Color color) {
		this (name, null, type, size, knowledge, color, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param type Type of the field
	 * @param size Size of the field
	 * @param knowledge The current knowledge we have about this field
	 * @param color The color used by the field
	 */
	public FieldStruct (String name, String description, FieldType type, int size, Knowledge knowledge, Color color) {
		this (name, description, type, size, knowledge, color, true, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, which is a link to another entry
	 *
	 * @param name Name of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 */
	public FieldStruct (String name, DatStructure linkToStruct, Object defaultValue) {
		this (name, null, FieldType.LINK, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, true, linkToStruct, null, null, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, which is a link to another entry
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 */
	public FieldStruct (String name, String description, DatStructure linkToStruct, Object defaultValue) {
		this (name, description, FieldType.LINK, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, true, linkToStruct, null, null, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, which is a link to another entry
	 *
	 * @param name Name of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 * @param editable If false, the field can't be edited
	 */
	public FieldStruct (String name, DatStructure linkToStruct, Object defaultValue, boolean editable) {
		this (name, null, FieldType.LINK, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, editable, linkToStruct, null, null, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, which is a link to another entry
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 * @param editable If false, the field can't be edited
	 */
	public FieldStruct (String name, String description, DatStructure linkToStruct, Object defaultValue, boolean editable) {
		this (name, description, FieldType.LINK, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, editable, linkToStruct, null, null, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and knowledge, which is a link to another entry
	 *
	 * @param name Name of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 * @param knowledge The current knowledge we have about this field
	 */
	public FieldStruct (String name, DatStructure linkToStruct, Object defaultValue, Knowledge knowledge) {
		this (name, null, FieldType.LINK, 4, knowledge, GUI.COLOR_FIELD_LINK, true, linkToStruct, null, null, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and knowledge, which is a link to another entry
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 * @param knowledge The current knowledge we have about this field
	 */
	public FieldStruct (String name, String description, DatStructure linkToStruct, Object defaultValue, Knowledge knowledge) {
		this (name, description, FieldType.LINK, 4, knowledge, GUI.COLOR_FIELD_LINK, true, linkToStruct, null, null, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and list of values
	 *
	 * @param name Name of the field
	 * @param enumValues Array with all available values for this field
	 */
	public FieldStruct (String name, EnumValue[] enumValues) {
		this (name, null, FieldType.ENUM, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, true, null, enumValues, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and list of values
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param enumValues Array with all available values for this field
	 */
	public FieldStruct (String name, String description, EnumValue[] enumValues) {
		this (name, description, FieldType.ENUM, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, true, null, enumValues, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and list of values
	 *
	 * @param name Name of the field
	 * @param enumValues Array with all available values for this field
	 * @param editable If false, the field can't be edited
	 */
	public FieldStruct (String name, EnumValue[] enumValues, boolean editable) {
		this (name, null, FieldType.ENUM, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, editable, null, enumValues, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and list of values
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param enumValues Array with all available values for this field
	 * @param editable If false, the field can't be edited
	 */
	public FieldStruct (String name, String description, EnumValue[] enumValues, boolean editable) {
		this (name, description, FieldType.ENUM, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, editable, null, enumValues, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and list of values
	 *
	 * @param name Name of the field
	 * @param arrValues Array with all integer values available for this field
	 */
	public FieldStruct (String name, Integer[] arrValues) {
		this (name, null, FieldType.RANGE, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, true, null, null, arrValues, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and list of values
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param arrValues Array with all integer values available for this field
	 */
	public FieldStruct (String name, String description, Integer[] arrValues) {
		this (name, description, FieldType.RANGE, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, true, null, null, arrValues, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and index of the field with the size.
	 *
	 * @param name Name of the field
	 * @param indexSize Index of field which hold the size of this field
	 */
	public FieldStruct (String name, int indexSize) {
		this (name, null, FieldType.STRING, 0, Knowledge.KNOWN, Color.BLACK, true, null, null, null, -1, indexSize);
	}


	/**
	 * Create a new FieldStruct with the given name and index of the field with the size.
	 *
	 * @param name Name of the field
	 * @param description Description of the field
	 * @param indexSize Index of field which hold the size of this field
	 */
	public FieldStruct (String name, String description, int indexSize) {
		this (name, description, FieldType.STRING, 0, Knowledge.KNOWN, Color.BLACK, true, null, null, null, -1, indexSize);
	}





	/**
	 * Gets the name of the field
	 *
	 * @return The name of the field
	 */
	public String getName () {
		return name;
	}

	/**
	 * Gets the description of the field, if not null, or the name otherwise
	 *
	 * @return The description if not null, the name otherwise
	 */
	public String getDescription () {
		return description != null ? description : name;
	}

	/**
	 * Gets the type of the field
	 *
	 * @return The type of the field
	 */
	public FieldType getType () {
		return type;
	}

	/**
	 * Gets the size of the field
	 *
	 * @return The size of the field
	 */
	public int getSize () {
		return size;
	}

	/**
	 * Gets the knowledge about this field
	 *
	 * @return The knowledge about this field
	 */
	public Knowledge getKnowledge () {
		return knowledge;
	}

	/**
	 * Check if the field is editable
	 *
	 * @return true if it's editable, false otherwise
	 */
	public boolean isEditable () {
		return editable;
	}

	/**
	 * Gets the color of the field
	 *
	 * @return The color of the field
	 */
	public Color getColor () {
		return color;
	}

	/**
	 * Gets the link to the other file
	 *
	 * @return The link to the other file
	 */
	public DatStructure getLinkToStruct () {
		return linkToStruct;
	}

	/**
	 * Gets the index of field which hold the size of this field
	 *
	 * @return The index of field which hold the size of this field
	 */
	public int getIndexSize () {
		return indexSize;
	}

	@Override
	public String toString () {
		return name + ' ' + '(' + type + ' ' + size + ')';
	}


	@Override
	public int compareTo (FieldStruct o) {
		return Integer.signum (name.compareTo (o.name));
	}

	@Override
	protected FieldStruct clone () {
		try {
			return (FieldStruct) super.clone ();
		} catch (CloneNotSupportedException e) { /* Won't happen... */
			Util.printException (null, e, "You should not see this error...", "Error", true);
			return null;
		}
	}

}
