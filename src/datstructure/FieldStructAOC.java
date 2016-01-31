package datstructure;

import java.awt.Color;

/**
 * This object define a single field data: description, type, size, ...
 * @author MarcoForlini
 *
 */
public class FieldStructAOC implements FieldStruct {
	
	/** Common field: A 100 chars String which define the entry's name. */
	public static final FieldStructAOC NAME = new FieldStructAOC("Name", Type.STRING, 100, Knowledge.KNOWN, COLOR_ID);
	/** Common field: A 4 bytes integer which define the entry's sequence number. */
	public static final FieldStructAOC SEQ_NUMBER = new FieldStructAOC("Sequence number", Type.INTEGER, 4, Knowledge.KNOWN, COLOR_ID);
	/** Common field: A 4 bytes integer which define the entry's ID. */
	public static final FieldStructAOC ID = new FieldStructAOC("ID", Type.INTEGER, 4, Knowledge.KNOWN, COLOR_ID);

	/** Unique field: A 4 bytes integer which point to an area effect's ID. */
	public static final FieldStructAOC ID_AREA_EFFECT = new FieldStructAOC("Area Effect ID", DatStructureAOC.DB_AREA_EFFECT);
	/** Unique field: A 4 bytes integer which point to a button's ID. */
	public static final FieldStructAOC ID_BUTTON = new FieldStructAOC("Button ID", DatStructureAOC.DB_BUTTONS);
	/** Unique field: A 4 bytes integer which point to a family's ID. */
	public static final FieldStructAOC ID_FAMILY = new FieldStructAOC("Family ID", DatStructureAOC.DB_FAMILY);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStructAOC ID_OBJECT = new FieldStructAOC("Object ID", DatStructureAOC.DB_OBJECT);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStructAOC ID_TECH = new FieldStructAOC("Tech ID", DatStructureAOC.DB_TECH_TREE);
	/** Unique field: A 4 bytes integer which point to an hotkey's ID. */
	public static final FieldStructAOC ID_HOTKEY = new FieldStructAOC("Hotkey ID", DatStructureAOC.DB_HOTKEY);
	/** Unique field: A 4 bytes integer which point to an upgrade's ID. */
	public static final FieldStructAOC ID_UPGRADE = new FieldStructAOC("Updrade ID", DatStructureAOC.DB_UPGRADE);
	/** Unique field: A 4 bytes integer which point to an unit set's ID. */
	public static final FieldStruct ID_UNIT_SET = new FieldStructAOC("Unit set ID", DatStructureAOC.DB_UNIT_SET);
	/** Unique field: A 4 bytes integer which point to a weapon to hit's ID. */
	public static final FieldStructAOC ID_WEAPON_TO_HIT = new FieldStructAOC("Weapon to hit ID", DatStructureAOC.DB_WEAPON_TO_HIT);
	/** Unique field: A 4 bytes integer which point to an object's ID. */
	public static final FieldStructAOC TECH_FROM_OBJECT = new FieldStructAOC("Build from object", DatStructureAOC.DB_OBJECT);
	/** Unique field: A 4 bytes integer which point to a tech's ID. */
	public static final FieldStructAOC OBJECT_BUILD_TECH = new FieldStructAOC ("Can build tech", DatStructureAOC.DB_TECH_TREE);
	/** Unique field: A 4 bytes integer which point to an entry in the file Language.dll */
	public static final FieldStructAOC ID_LANGUAGE = new FieldStructAOC("Language ID", Type.INTEGER, 4);
	
	/** Special field: A 1 byte boolean which define an unknown field which always hold the same value. */
	public static final FieldStructAOC UNCHANGED_BOOL1 = new FieldStructAOC("Never changes", Type.BOOLEAN, 1, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 4 bytes boolean which define an unknown field which always hold the same value. */
	public static final FieldStruct UNCHANGED_BOOL4 = new FieldStructAOC("Never changes", Type.BOOLEAN, 4, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 1 byte integer which define an unknown field which always hold the same value. */
	public static final FieldStruct UNCHANGED_INT1 = new FieldStructAOC("Never changes", Type.INTEGER, 1, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 4 bytes integer which define an unknown field which always hold the same value. */
	public static final FieldStructAOC UNCHANGED_INT4 = new FieldStructAOC("Never changes", Type.INTEGER, 4, Knowledge.NEVER_CHANGE, Color.GRAY);
	/** Special field: A 4 bytes float which define an unknown field which always hold the same value. */
	public static final FieldStructAOC UNCHANGED_FLOAT = new FieldStructAOC("Never changes", Type.FLOAT, 4, Knowledge.NEVER_CHANGE, Color.GRAY);
	
	/** Special field: A 1 byte boolean which define an unknown and never used field. */
	public static final FieldStructAOC UNUSED_BOOL1 = new FieldStructAOC("Never used", Type.BOOLEAN, 1, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 4 bytes boolean which define an unknown and never used field. */
	public static final FieldStruct UNUSED_BOOL4 = new FieldStructAOC("Never used", Type.BOOLEAN, 4, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 1 byte integer which define an unknown and never used field. */
	public static final FieldStructAOC UNUSED_INT1 = new FieldStructAOC("Never used", Type.INTEGER, 1, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 4 bytes integer which define an unknown and never used field. */
	public static final FieldStructAOC UNUSED_INT4 = new FieldStructAOC("Never used", Type.INTEGER, 4, Knowledge.NEVER_USED, Color.GRAY);
	/** Special field: A 4 bytes float which define an unknown and never used field. */
	public static final FieldStructAOC UNUSED_FLOAT = new FieldStructAOC("Never used", Type.FLOAT, 4, Knowledge.NEVER_USED, Color.GRAY);
	
	/** Special field: A 1 byte boolean which define a (still) unknown field. */
	public static final FieldStructAOC UNKNOWN_BOOL1 = new FieldStructAOC("Unknown", Type.BOOLEAN, 1, Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 4 bytes boolean which define a (still) unknown field. */
	public static final FieldStruct UNKNOWN_BOOL4 = new FieldStructAOC("Unknown", Type.BOOLEAN, 4, Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 1 byte integer which define a (still) unknown field. */
	public static final FieldStructAOC UNKNOWN_INT1 = new FieldStructAOC("Unknown", Type.INTEGER, 1,  Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 4 bytes integer which define a (still) unknown field. */
	public static final FieldStructAOC UNKNOWN_INT4 = new FieldStructAOC("Unknown", Type.INTEGER, 4,  Knowledge.UNKNOWN, Color.RED);
	/** Special field: A 4 bytes float which define a (still) unknown field. */
	public static final FieldStructAOC UNKNOWN_FLOAT = new FieldStructAOC("Unknown", Type.FLOAT, 4,  Knowledge.UNKNOWN, Color.RED);
	


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
	


	public FieldStructAOC (String name, DatStructure linkToStruct) {
		this.name = name;
		this.linkToStruct = linkToStruct;
		type = Type.ID;
		size = 4;
		knowledge = Knowledge.KNOWN;
		color = COLOR_LINK;
		editable = true;
	}

	public FieldStructAOC (String name){
		this.name = name;
		type = Type.BOOLEAN;
		size = 1;
		knowledge = Knowledge.KNOWN;
		color = Color.BLACK;
		editable = true;
		linkToStruct = null;
	}

	public FieldStructAOC (String name, Type type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
		knowledge = Knowledge.KNOWN;
		editable = true;
		color = Color.BLACK;
		linkToStruct = null;
	}

	public FieldStructAOC (String name, Type type, int size, boolean editable) {
		this.name = name;
		this.type = type;
		this.size = size;
		knowledge = Knowledge.KNOWN;
		color = Color.BLACK;
		this.editable = editable;
		linkToStruct = null;
	}

	public FieldStructAOC (String name, Type type, int size, Knowledge knowledge) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		color = Color.BLACK;
		editable = true;
		linkToStruct = null;
	}

	public FieldStructAOC (String name, Type type, int size, Knowledge knowledge, Color color) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.color = color;
		editable = true;
		linkToStruct = null;
	}
	
	public FieldStructAOC (String name, Type type, int size, Knowledge knowledge, Color color, boolean editable) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.knowledge = knowledge;
		this.color = color;
		this.editable = editable;
		linkToStruct = null;
	}




	

	@Override
	public String getName () {
		return name;
	}

	@Override
	public Type getType () {
		return type;
	}

	@Override
	public int getSize () {
		return size;
	}
	
	@Override
	public Knowledge getKnowledge () {
		return knowledge;
	}
	
	@Override
	public boolean isEditable () {
		return editable;
	}
	
	@Override
	public Color getColor () {
		return color;
	}

	@Override
	public DatStructure getLinkToStruct () {
		return linkToStruct;
	}

	
	@Override
	public String toString(){
		return name + ' ' + '(' + type + ' ' + size + ')';
	}
	
}
