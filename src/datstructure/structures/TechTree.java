package datstructure.structures;

import java.util.List;

import constants.TechType;
import datmanager.Core;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;
import datstructure.Knowledge;

/**
 * Represents the file dbtechtree.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class TechTree extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final TechTree instance = new TechTree();

	/**
	 * Creates a new {@link TechTree}
	 */
	private TechTree() {
		super("Technologies", "dbtechtree.dat", true, 1, 1, 0, 0, 1, 2, 4, 175, 225);
	}

	@Override
	public void init() {
		if (!Core.isAOC()) { // File structure has been changed in AOC
			extraField = ID_TECH_FROM_OBJECT;
			fieldStructs = new FieldStruct[] {
					FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Starting epoch", FieldType.INTEGER),
					new FieldStruct("Ending epoch", FieldType.INTEGER), new FieldStruct("Event ID", Events.instance, 0), FieldStruct.UNKNOWN_INT4, new FieldStruct("Wood cost", FieldType.INTEGER),
					new FieldStruct("Stone cost", FieldType.INTEGER), new FieldStruct("<Only Impassable tile object and Invisible capital>", FieldType.INTEGER), new FieldStruct("Gold cost", FieldType.INTEGER), FieldStruct.UNUSED_INT4,
					new FieldStruct("Iron cost", FieldType.INTEGER), new FieldStruct("Food cost", FieldType.INTEGER),
					new FieldStruct("Build time", FieldType.INTEGER), new FieldStruct("Unlocked by tech", TechTree.instance, 0),
					new FieldStruct("Unlocked by power", TechTree.instance, 0), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", FieldType.INTEGER), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", FieldType.INTEGER),
					new FieldStruct("Object ID", Objects.instance, -1),
					ID_BUTTON, new FieldStruct("Is object?", FieldType.INTEGER), FieldStruct.ID_LANGUAGE, new FieldStruct("Tech type", TechType.values()),
					new FieldStruct("<4 in all Epochs, 0 everywhere else>", FieldType.INTEGER), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Calamity", Calamity.instance, 0),
					new FieldStruct("Hotkey ID", UIHotkey.instance, 0), new FieldStruct("<Only Monoteism and Mech Minotaur use this>", FieldType.INTEGER, 4, Knowledge.UNKNOWN),
					new FieldStruct("<Only Monoteism and Mech Minotaur use this>", FieldType.INTEGER, 4, Knowledge.UNKNOWN), FieldStruct.UNKNOWN_INT4,
					FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
					FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Researchable in editor trigger"), new FieldStruct("Only in scenario"), new FieldStruct("<All powers and power techs use 0>"),
					new FieldStruct("<All powers and power techs use 0>"), new FieldStruct("Epoch number", FieldType.INTEGER), new FieldStruct("Buildings to advance epoch", FieldType.INTEGER), new FieldStruct("Epoch IDs", FieldType.INTEGER),
					new FieldStruct("Last build object", Objects.instance, 0, false), new FieldStruct("Num of tech builders", FieldType.INTEGER, 4, false)
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
					FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Starting epoch", FieldType.INTEGER),
					new FieldStruct("Ending epoch", FieldType.INTEGER), new FieldStruct("Event ID", Events.instance, 0), FieldStruct.UNKNOWN_INT4, new FieldStruct("Wood cost", FieldType.INTEGER),
					new FieldStruct("Stone cost", FieldType.INTEGER), new FieldStruct("<Only Impassable tile object and Invisible capital>", FieldType.INTEGER), new FieldStruct("Gold cost", FieldType.INTEGER), FieldStruct.UNUSED_INT4,
					new FieldStruct("Iron cost", FieldType.INTEGER), new FieldStruct("Food cost", FieldType.INTEGER),
					new FieldStruct("Build time", FieldType.INTEGER), new FieldStruct("Unlocked by tech", TechTree.instance, -1),
					new FieldStruct("Unlocked by power", TechTree.instance, -1), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", FieldType.INTEGER), new FieldStruct("<-1 in Epoch Space, 0 everywhere else>", FieldType.INTEGER),
					new FieldStruct("Object ID", Objects.instance, -1),
					ID_BUTTON, new FieldStruct("Is object?", FieldType.INTEGER), FieldStruct.ID_LANGUAGE, new FieldStruct("Tech type", TechType.values()),
					new FieldStruct("<4 in all Epochs, 0 everywhere else>", FieldType.INTEGER), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, new FieldStruct("Calamity", Calamity.instance, 0),
					new FieldStruct("Hotkey ID", UIHotkey.instance, 0), new FieldStruct("<Only Monoteism and Mech Minotaur use this>", FieldType.INTEGER, 4, Knowledge.UNKNOWN),
					new FieldStruct("<Only Monoteism and Mech Minotaur use this>", FieldType.INTEGER, 4, Knowledge.UNKNOWN), FieldStruct.UNUSED_INT4,
					FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNKNOWN_FLOAT,
					FieldStruct.UNKNOWN_FLOAT, new FieldStruct("Researchable in editor trigger"), new FieldStruct("Only in scenario"), new FieldStruct("<All powers and power techs use 0>"),
					new FieldStruct("<All powers and power techs use 0>"), FieldStruct.UNUSED_INT4, new FieldStruct("Epoch number", FieldType.INTEGER), new FieldStruct("Buildings to advance epoch", FieldType.INTEGER),
					new FieldStruct("Epoch IDs", FieldType.INTEGER), new FieldStruct("Last build object", Objects.instance, 0, false), new FieldStruct("Num of tech builders", FieldType.INTEGER, 4, false)
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
	public int indexExtraFields() {
		return fieldStructs.length - 1;
	}

	@Override
	public boolean hasCustomEntryName() {
		return false;
	}

	@Override
	public String getCustomEntryName(int index, List<Object> values) {
		return null;
	}

	@Override
	public String getEntryDescription(Entry entry) {
		return null;
	}

}
