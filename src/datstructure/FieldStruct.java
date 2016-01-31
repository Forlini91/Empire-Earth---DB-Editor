package datstructure;

import java.awt.Color;

/**
 * This object define a single field data: description, type, size, ...
 * @author MarcoForlini
 *
 */
public class FieldStruct{
	
	//char NOCHAR = 65484;

	private static final Color COLOR_ID = Color.BLUE;
	private static final Color COLOR_LINK = new Color(50, 200, 50);
	
	/** Common field: A 100 chars String which define the entry's name. */
	public static final FieldStruct NAME = new FieldStruct("Name", Type.STRING, 100, Knowledge.KNOWN, COLOR_ID);
	/** Common field: A 4 bytes integer which define the entry's sequence number. */
	public static final FieldStruct SEQ_NUMBER = new FieldStruct("Sequence number", Type.INTEGER, 4, Knowledge.KNOWN, COLOR_ID);
	/** Common field: A 4 bytes integer which define the entry's ID. */
	public static final FieldStruct ID = new FieldStruct("ID", Type.INTEGER, 4, Knowledge.KNOWN, COLOR_ID);

	/** Unique field: A 4 bytes integer which point to an area effect's ID. */
	public static final FieldStruct ID_AREA_EFFECT = new FieldStruct("Area Effect ID", DatStructure.DB_AREA_EFFECT);
	/** Unique field: A 4 bytes integer which point to a button's ID. */
	public static final FieldStruct ID_BUTTON = new FieldStruct("Button ID", DatStructure.DB_BUTTONS);
	/** Unique field: A 4 bytes integer which point to a family's ID. */
	public static final FieldStruct ID_FAMILY = new FieldStruct("Family ID", DatStructure.DB_FAMILY);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct ID_OBJECT = new FieldStruct("Object ID", DatStructure.DB_OBJECT);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct ID_TECH = new FieldStruct("Tech ID", DatStructure.DB_TECH_TREE);
	/** Unique field: A 4 bytes integer which point to an hotkey's ID. */
	public static final FieldStruct ID_HOTKEY = new FieldStruct("Hotkey ID", DatStructure.DB_HOTKEY);
	/** Unique field: A 4 bytes integer which point to an upgrade's ID. */
	public static final FieldStruct ID_UPGRADE = new FieldStruct("Updrade ID", DatStructure.DB_UPGRADE);
	/** Unique field: A 4 bytes integer which point to an unit set's ID. */
	public static final FieldStruct ID_UNIT_SET = new FieldStruct("Unit set ID", DatStructure.DB_UNIT_SET);
	/** Unique field: A 4 bytes integer which point to a weapon to hit's ID. */
	public static final FieldStruct ID_WEAPON_TO_HIT = new FieldStruct("Weapon to hit ID", DatStructure.DB_WEAPON_TO_HIT);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct TECH_FROM_OBJECT = new FieldStruct("Build from object", DatStructure.DB_OBJECT);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct OBJECT_BUILD_TECH = new FieldStruct ("Can build tech", DatStructure.DB_TECH_TREE);
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
	/** Link to the given structure. Only used by ID fields*/
	public final DatStructure linkToStruct;
	


	public FieldStruct (String name, DatStructure linkToStruct) {
		this.name = name;
		this.linkToStruct = linkToStruct;
		type = Type.ID;
		size = 4;
		knowledge = Knowledge.KNOWN;
		color = COLOR_LINK;
		editable = true;
	}

	public FieldStruct (String name){
		this.name = name;
		type = Type.BOOLEAN;
		size = 1;
		knowledge = Knowledge.KNOWN;
		color = Color.BLACK;
		editable = true;
		linkToStruct = null;
	}

	public FieldStruct (String name, Type type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
		knowledge = Knowledge.KNOWN;
		editable = true;
		color = Color.BLACK;
		linkToStruct = null;
	}

	public FieldStruct (String name, Type type, int size, boolean editable) {
		this.name = name;
		this.type = type;
		this.size = size;
		knowledge = Knowledge.KNOWN;
		color = Color.BLACK;
		this.editable = editable;
		linkToStruct = null;
	}

	public FieldStruct (String name, Type type, int size, Knowledge knowledge) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		color = Color.BLACK;
		editable = true;
		linkToStruct = null;
	}

	public FieldStruct (String name, Type type, int size, Knowledge knowledge, Color color) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.color = color;
		editable = true;
		linkToStruct = null;
	}
	
	public FieldStruct (String name, Type type, int size, Knowledge knowledge, Color color, boolean editable) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.color = color;
		this.editable = editable;
		linkToStruct = null;
	}


	
	@Override
	public String toString(){
		return name + ' ' + '(' + type + ' ' + size + ')';
	}
	
}
