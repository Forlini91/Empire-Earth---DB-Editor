package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbuihotkey.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class UIHotkey extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final UIHotkey instance = new UIHotkey();
	
	/**
	 * Creates a new {@link UIHotkey}
	 */
	private UIHotkey () {
		super("UI Hotkeys", "dbuihotkey.dat", true, 0, 1, 0, 2, 0, 1, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.UNCHANGED_INT4,
				FieldStruct.UNCHANGED_INT4, FieldStruct.UNCHANGED_INT4, new FieldStruct("Key scan code", Type.INTEGER)
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
