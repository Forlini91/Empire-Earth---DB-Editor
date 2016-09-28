package datstructure.structures;

import constants.TechType;
import datmanager.Core;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Knowledge;
import datstructure.Type;

/**
 * Represents the file dbtechtree.dat
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class TechTree extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final TechTree instance = new TechTree ();

	/**
	 * Creates a new {@link TechTree}
	 */
	private TechTree () {
		super ("Technologies", "dbtechtree.dat", true, 1, 1, 0, 0, 1, 2, 4, 175, 225);
	}

	@Override
	public void init () {
		if ( !Core.AOC) { // File structure has been changed in AOC
			extraField = ID_TECH_FROM_OBJECT;
			fieldStructs = new FieldStruct[] {
					FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Starting epoch", Type.INTEGER),
					new FieldStruct ("Ending epoch", Type.INTEGER), new FieldStruct ("Event ID", Events.instance, 0), FieldStruct.UNKNOWN_INT4, new FieldStruct ("Wood cost", Type.INTEGER),
					new FieldStruct ("Stone cost", Type.INTEGER), new FieldStruct ("<Only Impassable tile object and Invisible capital>", Type.INTEGER), new FieldStruct ("Gold cost", Type.INTEGER), FieldStruct.UNUSED_INT4,
					new FieldStruct ("Iron cost", Type.INTEGER), new FieldStruct ("Food cost", Type.INTEGER),
					new FieldStruct ("Build time", Type.INTEGER), new FieldStruct ("Unlocked by tech", TechTree.instance, 0),
					new FieldStruct ("Unlocked by power", TechTree.instance, 0), new FieldStruct ("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER), new FieldStruct ("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER), new FieldStruct ("Object ID", Objects.instance, -1),
					ID_BUTTON, new FieldStruct ("Is object?", Type.INTEGER), FieldStruct.ID_LANGUAGE, new FieldStruct ("Tech type", TechType.values ()),
					new FieldStruct ("<4 in all Epochs, 0 everywhere else>", Type.INTEGER), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct ("Calamity", Calamity.instance, 0),
					new FieldStruct ("Hotkey ID", UIHotkey.instance, 0), new FieldStruct ("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), new FieldStruct ("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
					FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_BOOL1, new FieldStruct ("Only in scenario"), new FieldStruct ("<All powers and power techs use 0>"),
					new FieldStruct ("<All powers and power techs use 0>"), new FieldStruct ("Epoch number", Type.INTEGER), new FieldStruct ("Buildings to advance epoch", Type.INTEGER), new FieldStruct ("Epoch IDs", Type.INTEGER),
					new FieldStruct ("Last build object", Objects.instance, 0, false), new FieldStruct ("Num of tech builders", Type.INTEGER, 4, false)
			};
			newEntryValues = new Object[] {
					"<New technology>", -1, 0, 0, 15, -1, -1, 0,
					0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, -1, -1, 0, 0, 0,
					0, 0, 0, 0, -1, 0, 0, 0,
					0, 0f, 0f, 0f, 0f, 0, 0, 1,
					1, 0, 0, 0, -1, 0
			};

		} else {

			extraField = ID_TECH_FROM_OBJECT;
			fieldStructs = new FieldStruct[] {
					FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Starting epoch", Type.INTEGER),
					new FieldStruct ("Ending epoch", Type.INTEGER), new FieldStruct ("Event ID", Events.instance, 0), FieldStruct.UNKNOWN_INT4, new FieldStruct ("Wood cost", Type.INTEGER),
					new FieldStruct ("Stone cost", Type.INTEGER), new FieldStruct ("<Only Impassable tile object and Invisible capital>", Type.INTEGER), new FieldStruct ("Gold cost", Type.INTEGER), FieldStruct.UNUSED_INT4,
					new FieldStruct ("Iron cost", Type.INTEGER), new FieldStruct ("Food cost", Type.INTEGER),
					new FieldStruct ("Build time", Type.INTEGER), new FieldStruct ("Unlocked by tech", TechTree.instance, -1),
					new FieldStruct ("Unlocked by power", TechTree.instance, -1), new FieldStruct ("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER), new FieldStruct ("<-1 in Epoch Space, 0 everywhere else>", Type.INTEGER), new FieldStruct ("Object ID", Objects.instance, -1),
					ID_BUTTON, new FieldStruct ("Is object?", Type.INTEGER), FieldStruct.ID_LANGUAGE, new FieldStruct ("Tech type", TechType.values ()),
					new FieldStruct ("<4 in all Epochs, 0 everywhere else>", Type.INTEGER), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct ("Calamity", Calamity.instance, 0),
					new FieldStruct ("Hotkey ID", UIHotkey.instance, 0), new FieldStruct ("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), new FieldStruct ("<Only Monoteism and Mech Minotaur use this>", Type.INTEGER, 4, Knowledge.UNKNOWN), FieldStruct.UNUSED_INT4,
					FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
					FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_BOOL1, new FieldStruct ("Only in scenario"), new FieldStruct ("<All powers and power techs use 0>"),
					new FieldStruct ("<All powers and power techs use 0>"), FieldStruct.UNUSED_INT4, new FieldStruct ("Epoch number", Type.INTEGER), new FieldStruct ("Buildings to advance epoch", Type.INTEGER),
					new FieldStruct ("Epoch IDs", Type.INTEGER), new FieldStruct ("Last build object", Objects.instance, 0, false), new FieldStruct ("Num of tech builders", Type.INTEGER, 4, false)
			};
			newEntryValues = new Object[] {
					"<New technology>", -1, 0, 0, 15, -1, -1, 0,
					0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, -1, -1, 0, 0, 0,
					0, 0, 0, 0, -1, 0, 0, 0,
					0, 0f, 0f, 0f, 0f, 0, 0, 1,
					1, 0, 0, 0, 0, -1, 0
			};
		}
	}

	@Override
	public int indexExtraFields () {
		return fieldStructs.length - 1;
	}

}
