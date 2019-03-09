package datstructure;

import java.awt.Color;
import java.util.Comparator;

import constants.EnumValue;
import datmanager.Util;


/**
 * This object define a single field data: description, type, size, ...
 *
 * @author MarcoForlini
 */
public class FieldStruct implements Comparable<FieldStruct>, Cloneable {

	public static final Comparator<Object> valueComparator = (o1, o2) -> {
		if (o1 instanceof Link) {
			return ((Link) o1).target.compareTo(((Link) o2).target);
		} else if (o1 instanceof Entry) {
			return ((Entry) o1).compareTo((Entry) o2);
		} else if (o1 instanceof EnumValue) {
			return Integer.compare(((EnumValue) o1).ordinal(), ((EnumValue) o2).ordinal());
		} else if (o1 instanceof Integer) {
			return Integer.compare((Integer) o1, (Integer) o2);
		} else {
			return Float.compare((Float) o1, (Float) o2);
		}
	};




	/** Name of the field. */
	public final String name;
	/** Description of the field */
	public final String description;
	/** Type of the value contained in the field. */
	public final FieldType type;
	/** Size of the field in bytes. */
	public final int size;
	/** Information about our knowledge about this field */
	public final Knowledge knowledge; // TODO: UNUSED FOR NOW
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
