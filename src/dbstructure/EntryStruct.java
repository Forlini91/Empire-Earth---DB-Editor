package dbstructure;

import java.awt.Color;

public class EntryStruct{
	
	public static final Color ID_COLOR = Color.BLUE;
	public static final Color UNCHANGE_COLOR = new Color(64, 192, 64);
	
	public static final char NOCHAR = 65484;
	public static final char b00 = 0x00;
	public static final char bCC = 65484;
	public static final char bFF = 0xFF;
	public static final String STRING_UNDEFINED = new String(new char[]{
			b00, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
			bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC, bCC,
	});
	public static final String STRING_NONAME = "<No name>";

	public static final EntryStruct NAME = new EntryStruct("Name", Type.STRING, 100);
	public static final EntryStruct SEQ_NUMBER = new EntryStruct("Sequence number", Type.INTEGER, 4, false, EntryStruct.ID_COLOR);
	
	public static final EntryStruct UNCHANGED_1 = new EntryStruct("Never changes", Type.UKNONWN, 1, true, UNCHANGE_COLOR);
	public static final EntryStruct UNCHANGED_4 = new EntryStruct("Never changes", Type.UKNONWN, 4, true, UNCHANGE_COLOR);
	public static final EntryStruct UNUSED_INT1 = new EntryStruct("Never used", Type.UKNONWN, 1, true, Color.ORANGE);
	public static final EntryStruct UNUSED_INT4 = new EntryStruct("Never used", Type.UKNONWN, 4, true, Color.ORANGE);
	public static final EntryStruct UNUSED_FLOAT = new EntryStruct("Never used", Type.UKNONWN, 4, true, Color.ORANGE);
	public static final EntryStruct UNKNOWN_INT1 = new EntryStruct(null, Type.UKNONWN, 1, true, Color.RED);
	public static final EntryStruct UNKNOWN_INT4 = new EntryStruct(null, Type.UKNONWN, 4, true, Color.RED);
	public static final EntryStruct UNKNOWN_FLOAT = new EntryStruct(null, Type.FLOAT, 4, true, Color.RED);
	


	public final String name;
	public final Type type;
	public final int size;
	public final boolean editable;
	public final Object defaultValue;
	public final Color color;
	
	public EntryStruct (String name, Type type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
		editable = true;
		switch(type){
			case STRING: defaultValue = STRING_NONAME; break;
			case FLOAT: defaultValue = 0f; break;
			default: defaultValue = 0;
		}
		color = Color.BLACK;
	}
	
	public EntryStruct (String name, Type type, int size, boolean editable, Color color) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.editable = editable;
		switch(type){
			case STRING: defaultValue = STRING_NONAME; break;
			case FLOAT: defaultValue = 0f; break;
			default: defaultValue = 0;
		}
		this.color = color;
	}
	
	@Override
	public String toString(){
		return (name == null ? "<Unknown>" : name) + " (" + type + ' ' + size + ')';
	}
	
}
