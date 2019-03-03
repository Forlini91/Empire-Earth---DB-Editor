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
public class FieldStruct implements Comparable<FieldStruct>, Cloneable {

	/** Name of the field. */
	public final String name;
	/** Description of the field */
	public final String description;
	/** Type of the value contained in the field. */
	public final FieldType type;
	/** Size of the field in bytes. */
	public final int size;
	/** Information about our knowledge about this field */
	public final Knowledge knowledge;
	/** If false, the user shouldn't touch this field */
	public final boolean editable;
	/** Color used in the GUI for this field */
	public final Color color;
	/** Link to the given structure. Only used by ID fields */
	public final DatStructure linkToStruct;
	/** Fixed list of available values */
	public final EnumValue[] enumValues;
	/** Fixed array of values */
	public final Integer[] arrValues;
	/** Default value to the given field, when no ID is selected */
	public final Integer defaultValue;
	/** Index of the field which hold the size of the string field. (Yeah... some strings length is not a fixed 100) */
	public final int indexSize;




	/**
	 * Creates a new {@link FieldStruct} with all values
	 *
	 * @param type
	 * @param size
	 * @param name
	 * @param description
	 * @param editable
	 * @param knowledge
	 * @param color
	 * @param arrValues
	 * @param enumValues
	 * @param linkToStruct
	 * @param defaultValue
	 * @param indexSize
	 */
	public FieldStruct(FieldType type, int size, String name, String description, boolean editable, Knowledge knowledge, Color color,
			Integer[] arrValues, EnumValue[] enumValues, DatStructure linkToStruct, Integer defaultValue, int indexSize) {
		this.type = type;
		this.size = size;
		this.name = name;
		this.description = description;
		this.editable = editable;
		this.knowledge = knowledge;
		this.color = color;
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
	public FieldStruct(String name) {
		this(FieldType.BOOLEAN, 1, name, null, true, Knowledge.KNOWN, Color.BLACK, null, null, null, -1, -1);
	}


	/**
	 * Create a new boolean FieldStruct with the given name
	 *
	 * @param name        Name of the field
	 * @param description Description of the field
	 */
	public FieldStruct(String name, String description) {
		this(FieldType.BOOLEAN, 1, name, description, true, Knowledge.KNOWN, Color.BLACK, null, null, null, -1, -1);
	}


	/**
	 * Create a new 4 bytes FieldStruct with the given name and type
	 *
	 * @param name Name of the field
	 * @param type Type of the field
	 */
	public FieldStruct(String name, FieldType type) {
		this(type, 4, name, null, true, Knowledge.KNOWN, Color.BLACK, null, null, null, -1, -1);
	}


	/**
	 * Create a new 4 bytes FieldStruct with the given name and type
	 *
	 * @param name        Name of the field
	 * @param description Description of the field
	 * @param type        Type of the field
	 */
	public FieldStruct(String name, String description, FieldType type) {
		this(type, 4, name, description, true, Knowledge.KNOWN, Color.BLACK, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name Name of the field
	 * @param type Type of the field
	 * @param size Size of the field
	 */
	public FieldStruct(String name, FieldType type, int size) {
		this(type, size, name, null, true, Knowledge.KNOWN, Color.BLACK, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name     Name of the field
	 * @param type     Type of the field
	 * @param size     Size of the field
	 * @param editable If false, the field can't be edited
	 */
	public FieldStruct(String name, FieldType type, int size, boolean editable) {
		this(type, size, name, null, editable, Knowledge.KNOWN, Color.BLACK, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name      Name of the field
	 * @param type      Type of the field
	 * @param size      Size of the field
	 * @param knowledge The current knowledge we have about this field
	 */
	public FieldStruct(String name, FieldType type, int size, Knowledge knowledge) {
		this(type, size, name, null, true, knowledge, Color.BLACK, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, type and size
	 *
	 * @param name      Name of the field
	 * @param type      Type of the field
	 * @param size      Size of the field
	 * @param knowledge The current knowledge we have about this field
	 * @param color     The color used by the field
	 */
	public FieldStruct(String name, FieldType type, int size, Knowledge knowledge, Color color) {
		this(type, size, name, null, true, knowledge, color, null, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, which is a link to another entry
	 *
	 * @param name         Name of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 */
	public FieldStruct(String name, DatStructure linkToStruct, Integer defaultValue) {
		this(FieldType.LINK, 4, name, null, true, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, null, null, linkToStruct, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, which is a link to another entry
	 *
	 * @param name         Name of the field
	 * @param description  Description of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 */
	public FieldStruct(String name, String description, DatStructure linkToStruct, Integer defaultValue) {
		this(FieldType.LINK, 4, name, description, true, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, null, null, linkToStruct, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name, which is a link to another entry
	 *
	 * @param name         Name of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 * @param editable     If false, the field can't be edited
	 */
	public FieldStruct(String name, DatStructure linkToStruct, Integer defaultValue, boolean editable) {
		this(FieldType.LINK, 4, name, null, editable, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, null, null, linkToStruct, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and knowledge, which is a link to another entry
	 *
	 * @param name         Name of the field
	 * @param linkToStruct Link to this dat
	 * @param defaultValue Default value if the link can't be found
	 * @param knowledge    The current knowledge we have about this field
	 */
	public FieldStruct(String name, DatStructure linkToStruct, Integer defaultValue, Knowledge knowledge) {
		this(FieldType.LINK, 4, name, null, true, knowledge, GUI.COLOR_FIELD_LINK, null, null, linkToStruct, defaultValue, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and list of values
	 *
	 * @param name       Name of the field
	 * @param enumValues Array with all available values for this field
	 */
	public FieldStruct(String name, EnumValue[] enumValues) {
		this(FieldType.ENUM, 4, name, null, true, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, null, enumValues, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and list of values
	 *
	 * @param name       Name of the field
	 * @param enumValues Array with all available values for this field
	 * @param editable   If false, the field can't be edited
	 */
	public FieldStruct(String name, EnumValue[] enumValues, boolean editable) {
		this(FieldType.ENUM, 4, name, null, editable, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, null, enumValues, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and list of values
	 *
	 * @param name        Name of the field
	 * @param description Description of the field
	 * @param arrValues   Array with all integer values available for this field
	 */
	public FieldStruct(String name, String description, Integer[] arrValues) {
		this(FieldType.RANGE, 4, name, description, true, Knowledge.KNOWN, GUI.COLOR_FIELD_LINK, arrValues, null, null, -1, -1);
	}


	/**
	 * Create a new FieldStruct with the given name and index of the field with the size of the string.
	 *
	 * @param name      Name of the field
	 * @param indexSize Index of field which hold the size of the string
	 */
	public FieldStruct(String name, int indexSize) {
		this(FieldType.DYNAMIC_STRING, 0, name, null, true, Knowledge.KNOWN, Color.BLACK, null, null, null, -1, indexSize);
	}





	/**
	 * Gets the name of the field
	 *
	 * @return The name of the field
	 */
	public String getName() { return name; }

	/**
	 * Gets the description of the field, if not null, or the name otherwise
	 *
	 * @return The description if not null, the name otherwise
	 */
	public String getDescription() { return description != null ? description : name; }

	/**
	 * Gets the type of the field
	 *
	 * @return The type of the field
	 */
	public FieldType getType() { return type; }

	/**
	 * Gets the size of the field
	 *
	 * @return The size of the field
	 */
	public int getSize() { return size; }

	/**
	 * Gets the knowledge about this field
	 *
	 * @return The knowledge about this field
	 */
	public Knowledge getKnowledge() { return knowledge; }

	/**
	 * Check if the field is editable
	 *
	 * @return true if it's editable, false otherwise
	 */
	public boolean isEditable() { return editable; }

	/**
	 * Gets the color of the field
	 *
	 * @return The color of the field
	 */
	public Color getColor() { return color; }

	/**
	 * Gets the link to the other file
	 *
	 * @return The link to the other file
	 */
	public DatStructure getLinkToStruct() { return linkToStruct; }

	/**
	 * Gets the index of field which hold the size of the string
	 *
	 * @return The index of field which hold the size of the string
	 */
	public int getIndexSize() { return indexSize; }

	@Override
	public String toString() {
		return name + ' ' + '(' + type + ' ' + size + ')';
	}


	@Override
	public int compareTo(FieldStruct o) {
		return Integer.signum(name.compareTo(o.name));
	}

	@Override
	protected FieldStruct clone() {
		try {
			return (FieldStruct) super.clone();
		} catch (final CloneNotSupportedException e) { /* Won't happen... */
			Util.printException(null, e, "You should not see this error...", "Error", true);
			return null;
		}
	}

}
