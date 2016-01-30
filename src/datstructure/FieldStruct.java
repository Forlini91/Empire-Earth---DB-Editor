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

	public static final char b00 = 0x00;
	public static final char bCC = 65484;
	public static final String STRING_UNDEFINED = new String(new char[]{
			b00, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
	});

	/** Common field: A 100 chars String which define the entry's name. */
	public static final FieldStruct NAME = new FieldStruct("Name", Type.STRING, 100, Knowledge.KNOWN, COLOR_ID);
	/** Common field: A 4 bytes integer which define the entry's sequence number. */
	public static final FieldStruct SEQ_NUMBER = new FieldStruct("Sequence number", Type.INTEGER, 4, Knowledge.KNOWN, COLOR_ID, false);
	/** Common field: A 4 bytes integer which define the entry's ID. */
	public static final FieldStruct ID = new FieldStruct("ID", Type.INTEGER, 4, Knowledge.KNOWN, COLOR_ID, false);
	
	/** Unique field: A 4 bytes integer which point to an area effect's ID. */
	public static final FieldStruct ID_AREA_EFFECT = new FieldStruct("Area Effect ID", Type.ID_AREA_EFFECT, 4, Knowledge.KNOWN, COLOR_LINK);
	/** Unique field: A 4 bytes integer which point to a button's ID. */
	public static final FieldStruct ID_BUTTON = new FieldStruct("Button ID", Type.ID_BUTTON, 4, Knowledge.KNOWN, COLOR_LINK);
	/** Unique field: A 4 bytes integer which point to a family's ID. */
	public static final FieldStruct ID_FAMILY = new FieldStruct("Family ID", Type.ID_FAMILY, 4, Knowledge.KNOWN, COLOR_LINK);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct ID_OBJECT = new FieldStruct("Object ID", Type.ID_OBJECT, 4, Knowledge.KNOWN, COLOR_LINK);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct ID_TECH = new FieldStruct("Tech ID", Type.ID_TECH, 4, Knowledge.KNOWN, COLOR_LINK);
	/** Unique field: A 4 bytes integer which point to an hotkey's ID. */
	public static final FieldStruct ID_HOTKEY = new FieldStruct("Hotkey ID", Type.ID_HOTKEY, 4, Knowledge.KNOWN, COLOR_LINK);
	/** Unique field: A 4 bytes integer which point to an upgrade's ID. */
	public static final FieldStruct ID_UPGRADE = new FieldStruct("Updrade ID", Type.ID_UPGRADE, 4, Knowledge.KNOWN, COLOR_LINK);
	/** Unique field: A 4 bytes integer which point to an unit set's ID. */
	public static final FieldStruct ID_UNIT_SET = new FieldStruct("Unit set ID", Type.ID_UNIT_SET, 4, Knowledge.KNOWN, COLOR_LINK);
	/** Unique field: A 4 bytes integer which point to a weapon to hit's ID. */
	public static final FieldStruct ID_WEAPON_TO_HIT = new FieldStruct("Weapon to hit ID", Type.ID_WEAPON_TO_HIT, 4, Knowledge.KNOWN, COLOR_LINK);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStruct TECH_FROM_OBJECT = new FieldStruct("Build from object", Type.ID_OBJECT, 4, Knowledge.KNOWN);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStruct OBJECT_BUILD_TECH = new FieldStruct ("Can build tech", Type.ID_TECH, 4, Knowledge.KNOWN);

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

	
	
	public FieldStruct (String name){
		this.name = name;
		type = Type.BOOLEAN;
		size = 1;
		knowledge = Knowledge.KNOWN;
		color = Color.BLACK;
		editable = true;
	}
	
	public FieldStruct (String name, Type type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
		knowledge = Knowledge.KNOWN;
		editable = true;
		color = Color.BLACK;
	}
	
	public FieldStruct (String name, Type type, int size, boolean editable) {
		this.name = name;
		this.type = type;
		this.size = size;
		knowledge = Knowledge.KNOWN;
		color = Color.BLACK;
		this.editable = editable;
	}
	
	public FieldStruct (String name, Type type, int size, Knowledge knowledge) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		color = Color.BLACK;
		editable = true;
	}
	
	public FieldStruct (String name, Type type, int size, Knowledge knowledge, Color color) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.color = color;
		editable = true;
	}

	public FieldStruct (String name, Type type, int size, Knowledge knowledge, Color color, boolean editable) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.color = color;
		this.editable = editable;
	}
	
	

	@Override
	public String toString(){
		return name + ' ' + '(' + type + ' ' + size + ')';
	}

}
