package datstructure;

import java.awt.Color;

import constants.EnumValue;

/**
 * This object define a single field data: description, type, size, ...
 * @author MarcoForlini
 *
 */
public class FieldStruct {

	public static final Color COLOR_ID = Color.BLUE;
	public static final Color COLOR_LINK = new Color(50, 200, 50);

	
	
	/** Common field: A 100 chars String which define the entry's name. */
	public static final FieldStruct NAME = new FieldStruct("Name", Type.STRING, 100, Knowledge.KNOWN, COLOR_ID);
	/** Common field: A 4 bytes integer which define the entry's sequence number. */
	public static final FieldStruct SEQ_NUMBER = new FieldStruct("Sequence number", Type.INTEGER, 4, Knowledge.KNOWN, COLOR_ID);
	/** Common field: A 4 bytes integer which define the entry's ID. */
	public static final FieldStruct ID = new FieldStruct("ID", Type.INTEGER, 4, Knowledge.KNOWN, COLOR_ID);
	/** Common field: A 4 bytes integer which alter the lenght of a string field */
	public static final FieldStruct STRING_SIZE_EXTRA = new FieldStruct("String size extra", Type.INTEGER, 4, false);
	/** Unique field: A 4 bytes integer which point to an entry in the file Language.dll */
	public static final FieldStruct ID_LANGUAGE = new FieldStruct("Language ID", Type.INTEGER, 4);



	/** Special field: A 1 byte boolean which define an unknown field which always hold the same value. */
	public static final FieldStruct UNCHANGED_BOOL1 = new FieldStruct("Never changes", Type.BOOLEAN, 1, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 4 bytes boolean which define an unknown field which always hold the same value. */
	public static final FieldStruct UNCHANGED_BOOL4 = new FieldStruct("Never changes", Type.BOOLEAN, 4, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 1 byte integer which define an unknown field which always hold the same value. */
	public static final FieldStruct UNCHANGED_INT1 = new FieldStruct("Never changes", Type.INTEGER, 1, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 4 bytes integer which define an unknown field which always hold the same value. */
	public static final FieldStruct UNCHANGED_INT4 = new FieldStruct("Never changes", Type.INTEGER, 4, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 4 bytes float which define an unknown field which always hold the same value. */
	public static final FieldStruct UNCHANGED_FLOAT = new FieldStruct("Never changes", Type.FLOAT, 4, Knowledge.NEVER_CHANGE, Color.GRAY);

	/** Special field: A 1 byte boolean which define an unknown and never used field. */
	public static final FieldStruct UNUSED_BOOL1 = new FieldStruct("Never used", Type.BOOLEAN, 1, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 4 bytes boolean which define an unknown and never used field. */
	public static final FieldStruct UNUSED_BOOL4 = new FieldStruct("Never used", Type.BOOLEAN, 4, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 1 byte integer which define an unknown and never used field. */
	public static final FieldStruct UNUSED_INT1 = new FieldStruct("Never used", Type.INTEGER, 1, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 4 bytes integer which define an unknown and never used field. */
	public static final FieldStruct UNUSED_INT4 = new FieldStruct("Never used", Type.INTEGER, 4, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 4 bytes float which define an unknown and never used field. */
	public static final FieldStruct UNUSED_FLOAT = new FieldStruct("Never used", Type.FLOAT, 4, Knowledge.NEVER_USED, Color.GRAY);

	/** Special field: A 1 byte boolean which define a (still) unknown field. */
	public static final FieldStruct UNKNOWN_BOOL1 = new FieldStruct("Unknown", Type.BOOLEAN, 1, Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 4 bytes boolean which define a (still) unknown field. */
	public static final FieldStruct UNKNOWN_BOOL4 = new FieldStruct("Unknown", Type.BOOLEAN, 4, Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 1 byte integer which define a (still) unknown field. */
	public static final FieldStruct UNKNOWN_INT1 = new FieldStruct("Unknown", Type.INTEGER, 1,  Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 4 bytes integer which define a (still) unknown field. */
	public static final FieldStruct UNKNOWN_INT4 = new FieldStruct("Unknown", Type.INTEGER, 4,  Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 4 bytes float which define a (still) unknown field. */
	public static final FieldStruct UNKNOWN_FLOAT = new FieldStruct("Unknown", Type.FLOAT, 4,  Knowledge.UNKNOWN, Color.RED);
	
	


	/** Name or description of the field. */
	public final String name;
	/** Type of the value contained in the field. */
	public final Type type;
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
	public final EnumValue[] values;
	/** Default value to the given field, when no ID is selected */
	public final Object defaultValue;
	/** Index of the field which hold the extra size of the string field. (Yeah... some strings length is base+extra) */
	public final int indexStringLengthExtra;
	
	

	
	

	public FieldStruct (String name, DatStructure linkToStruct) {
		this.name = name;
		this.linkToStruct = linkToStruct;
		type = Type.ID;
		size = 4;
		knowledge = Knowledge.KNOWN;
		color = COLOR_LINK;
		editable = true;
		values = null;
		defaultValue = -1;
		indexStringLengthExtra = -1;
	}

	public FieldStruct (String name, DatStructure linkToStruct, int defaultValue) {
		this.name = name;
		this.linkToStruct = linkToStruct;
		this.defaultValue = defaultValue;
		type = Type.ID;
		size = 4;
		knowledge = Knowledge.KNOWN;
		color = COLOR_LINK;
		editable = true;
		values = null;
		indexStringLengthExtra = -1;
	}
	
	public FieldStruct (String name, EnumValue[] values) {
		this.name = name;
		this.values = values;
		type = Type.ENUM;
		size = 4;
		knowledge = Knowledge.KNOWN;
		color = COLOR_LINK;
		editable = true;
		linkToStruct = null;
		defaultValue = -1;
		indexStringLengthExtra = -1;
	}

	public <T> FieldStruct (String name, EnumValue[] values, EnumValue defaultValue) {
		this.name = name;
		this.values = values;
		this.defaultValue = defaultValue;
		type = Type.ENUM;
		size = 4;
		knowledge = Knowledge.KNOWN;
		color = COLOR_LINK;
		editable = true;
		linkToStruct = null;
		indexStringLengthExtra = -1;
	}

	public FieldStruct (String name, int size, int indexStringLengthExtra){
		this.name = name;
		this.size = size;
		this.indexStringLengthExtra = indexStringLengthExtra;
		type = Type.STRING;
		knowledge = Knowledge.KNOWN;
		color = Color.BLACK;
		editable = true;
		values = null;
		linkToStruct = null;
		defaultValue = -1;
	}

	public FieldStruct (String name){
		this.name = name;
		type = Type.BOOLEAN;
		size = 1;
		knowledge = Knowledge.KNOWN;
		color = Color.BLACK;
		editable = true;
		linkToStruct = null;
		values = null;
		indexStringLengthExtra = -1;
		defaultValue = -1;
	}
	
	public FieldStruct (String name, Type type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
		knowledge = Knowledge.KNOWN;
		editable = true;
		color = Color.BLACK;
		linkToStruct = null;
		values = null;
		defaultValue = -1;
		indexStringLengthExtra = -1;
	}
	
	public FieldStruct (String name, Type type, int size, boolean editable) {
		this.name = name;
		this.type = type;
		this.size = size;
		knowledge = Knowledge.KNOWN;
		color = Color.BLACK;
		this.editable = editable;
		linkToStruct = null;
		values = null;
		defaultValue = -1;
		indexStringLengthExtra = -1;
	}
	
	public FieldStruct (String name, Type type, int size, Knowledge knowledge) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		color = Color.BLACK;
		editable = true;
		linkToStruct = null;
		values = null;
		defaultValue = -1;
		indexStringLengthExtra = -1;
	}
	
	public FieldStruct (String name, Type type, int size, Knowledge knowledge, Color color) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.color = color;
		editable = true;
		linkToStruct = null;
		values = null;
		defaultValue = -1;
		indexStringLengthExtra = -1;
	}
	
	public FieldStruct (String name, Type type, int size, Knowledge knowledge, DatStructure linkToStruct) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.linkToStruct = linkToStruct;
		defaultValue = -1;
		color = COLOR_LINK;
		editable = true;
		values = null;
		indexStringLengthExtra = -1;
	}

	public FieldStruct (String name, Type type, int size, Knowledge knowledge, DatStructure linkToStruct, int defaultValue) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.linkToStruct = linkToStruct;
		this.defaultValue = defaultValue;
		color = COLOR_LINK;
		editable = true;
		values = null;
		indexStringLengthExtra = -1;
	}

	public FieldStruct (String name, Type type, int size, Knowledge knowledge, Color color, boolean editable) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.color = color;
		this.editable = editable;
		linkToStruct = null;
		values = null;
		defaultValue = -1;
		indexStringLengthExtra = -1;
	}
	
	
	
	

	
	public String getName () {
		return name;
	}
	
	public Type getType () {
		return type;
	}
	
	public int getSize () {
		return size;
	}

	public Knowledge getKnowledge () {
		return knowledge;
	}

	public boolean isEditable () {
		return editable;
	}

	public Color getColor () {
		return color;
	}
	
	public DatStructure getLinkToStruct () {
		return linkToStruct;
	}
	
	public int getIndexStringLengthExtra () {
		return indexStringLengthExtra;
	}

	@Override
	public String toString(){
		return name + ' ' + '(' + type + ' ' + size + ')';
	}

}
