package datstructure.structures;

import java.util.List;

import datmanager.Core;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;
import datstructure.Knowledge;
import gui.GUI;


/**
 * Represents the file dbcivilization.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class Civilization extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Civilization instance = new Civilization ();

	/**
	 * Creates a new {@link Civilization}
	 */
	private Civilization () {
		super ("Civilizations", "dbcivilization.dat", true, 0, 0, 1, 2, 0, 1, 4, 125, 175);
	}

	@Override
	public void init () {
		if (!Core.isAOC ()) { // File structure has been changed in AOC
			fieldStructs = new FieldStruct[] {
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.ID_LANGUAGE,
					new FieldStruct ("Cost increment", FieldType.INTEGER), ID_UNIT_SET, new FieldStruct ("Attack bonus", FieldType.FLOAT), new FieldStruct ("Attack bonus cost", FieldType.FLOAT),
					new FieldStruct ("Health bonus", FieldType.FLOAT), new FieldStruct ("Health bonus cost", FieldType.FLOAT), new FieldStruct ("Attack speed bonus", FieldType.FLOAT), new FieldStruct ("Attack speed bonus cost", FieldType.FLOAT),
					new FieldStruct ("Armor bonus", FieldType.FLOAT), new FieldStruct ("Armor bonus cost", FieldType.FLOAT), new FieldStruct ("Range bonus", FieldType.FLOAT), new FieldStruct ("Range bonus cost", FieldType.FLOAT),
					new FieldStruct ("Speed bonus", FieldType.FLOAT), new FieldStruct ("Speed bonus cost", FieldType.FLOAT), new FieldStruct ("Flight time bonus", FieldType.FLOAT), new FieldStruct ("Flight time bonus cost", FieldType.FLOAT),
					new FieldStruct ("Area damage bonus", FieldType.FLOAT), new FieldStruct ("Area damage bonus cost", FieldType.FLOAT), new FieldStruct ("Hunting & foraging bonus", FieldType.FLOAT), new FieldStruct ("Hunting & foraging bonus cost", FieldType.FLOAT),
					new FieldStruct ("Wood cutting bonus", FieldType.FLOAT), new FieldStruct ("Wood cutting bonus cost", FieldType.FLOAT), new FieldStruct ("Gold mining bonus", FieldType.FLOAT), new FieldStruct ("Gold mining bonus cost", FieldType.FLOAT),
					new FieldStruct ("Farming bonus", FieldType.FLOAT), new FieldStruct ("Farming bonus cost", FieldType.FLOAT), new FieldStruct ("Stone mining bonus", FieldType.FLOAT), new FieldStruct ("Stone mining bonus cost", FieldType.FLOAT),
					new FieldStruct ("Iron mining bonus", FieldType.FLOAT), new FieldStruct ("Iron mining bonus cost", FieldType.FLOAT), new FieldStruct ("Build time reduction", FieldType.FLOAT), new FieldStruct ("Build time reduction cost", FieldType.FLOAT),
					new FieldStruct ("Conversion resistance bonus", FieldType.FLOAT), new FieldStruct ("Conversion resistance bonus cost", FieldType.FLOAT), new FieldStruct ("Fishing bonus", FieldType.FLOAT), new FieldStruct ("Fishing bonus cost", FieldType.FLOAT),
					new FieldStruct ("Repair bonus", FieldType.FLOAT), new FieldStruct ("Repair bonus cost", FieldType.FLOAT), new FieldStruct ("Area effect bonus", FieldType.FLOAT), new FieldStruct ("Area effect bonus cost", FieldType.FLOAT),
					new FieldStruct ("Mountain combat bonus", FieldType.FLOAT), new FieldStruct ("Mountain combat bonus cost", FieldType.FLOAT), new FieldStruct ("Population cap bonus", FieldType.FLOAT), new FieldStruct ("Population cap bonus cost", FieldType.FLOAT),
					new FieldStruct ("Area conversion bonus", FieldType.FLOAT), new FieldStruct ("Area conversion bonus cost", FieldType.FLOAT), new FieldStruct ("Cost reduction", FieldType.FLOAT), new FieldStruct ("Cost reduction cost", FieldType.FLOAT),
					new FieldStruct ("Night-time LOS bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Night-time LOS bonus cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Slavery bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Slavery bonus cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Capitol upgrade bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Capitol upgrade bonus cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Heroes bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Heroes bonus cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 1 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 1 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 2 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 2 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 3 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 3 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 4 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 4 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 5 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 5 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 6 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 6 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 7 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 7 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 8 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 8 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 9 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 9 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 10 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 10 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN)
			};
			newEntryValues = new Object[] {
					0, -1, "<New civilization>", 0, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
			};

		} else {

			fieldStructs = new FieldStruct[] {
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.ID_LANGUAGE,
					new FieldStruct ("Cost increment", FieldType.INTEGER), ID_UNIT_SET, new FieldStruct ("Attack bonus", FieldType.FLOAT), new FieldStruct ("Attack bonus cost", FieldType.FLOAT),
					new FieldStruct ("Health bonus", FieldType.FLOAT), new FieldStruct ("Health bonus cost", FieldType.FLOAT), new FieldStruct ("Attack speed bonus", FieldType.FLOAT), new FieldStruct ("Attack speed bonus cost", FieldType.FLOAT),
					new FieldStruct ("Armor bonus", FieldType.FLOAT), new FieldStruct ("Armor bonus cost", FieldType.FLOAT), new FieldStruct ("Range bonus", FieldType.FLOAT), new FieldStruct ("Range bonus cost", FieldType.FLOAT),
					new FieldStruct ("Speed bonus", FieldType.FLOAT), new FieldStruct ("Speed bonus cost", FieldType.FLOAT), new FieldStruct ("Flight time bonus", FieldType.FLOAT), new FieldStruct ("Flight time bonus cost", FieldType.FLOAT),
					new FieldStruct ("Area damage bonus", FieldType.FLOAT), new FieldStruct ("Area damage bonus cost", FieldType.FLOAT), new FieldStruct ("Hunting & foraging bonus", FieldType.FLOAT), new FieldStruct ("Hunting & foraging bonus cost", FieldType.FLOAT),
					new FieldStruct ("Wood cutting bonus", FieldType.FLOAT), new FieldStruct ("Wood cutting bonus cost", FieldType.FLOAT), new FieldStruct ("Gold mining bonus", FieldType.FLOAT), new FieldStruct ("Gold mining bonus cost", FieldType.FLOAT),
					new FieldStruct ("Farming bonus", FieldType.FLOAT), new FieldStruct ("Farming bonus cost", FieldType.FLOAT), new FieldStruct ("Stone mining bonus", FieldType.FLOAT), new FieldStruct ("Stone mining bonus cost", FieldType.FLOAT),
					new FieldStruct ("Iron mining bonus", FieldType.FLOAT), new FieldStruct ("Iron mining bonus cost", FieldType.FLOAT), new FieldStruct ("Build time reduction", FieldType.FLOAT), new FieldStruct ("Build time reduction cost", FieldType.FLOAT),
					new FieldStruct ("Conversion resistance bonus", FieldType.FLOAT), new FieldStruct ("Conversion resistance bonus cost", FieldType.FLOAT), new FieldStruct ("Fishing bonus", FieldType.FLOAT), new FieldStruct ("Fishing bonus cost", FieldType.FLOAT),
					new FieldStruct ("Repair bonus", FieldType.FLOAT), new FieldStruct ("Repair bonus cost", FieldType.FLOAT), new FieldStruct ("Area effect bonus", FieldType.FLOAT), new FieldStruct ("Area effect bonus cost", FieldType.FLOAT),
					new FieldStruct ("Mountain combat bonus", FieldType.FLOAT), new FieldStruct ("Mountain combat bonus cost", FieldType.FLOAT), new FieldStruct ("Population cap bonus", FieldType.FLOAT), new FieldStruct ("Population cap bonus cost", FieldType.FLOAT),
					new FieldStruct ("Area conversion bonus", FieldType.FLOAT), new FieldStruct ("Area conversion bonus cost", FieldType.FLOAT), new FieldStruct ("Cost reduction", FieldType.FLOAT), new FieldStruct ("Cost reduction cost", FieldType.FLOAT),
					new FieldStruct ("Night-time LOS bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Night-tme LOS bonus cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Slavery bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Slavery bonus cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Capitol upgrade bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Capitol upgrade bonus cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Heroes bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Heroes bonus cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 1 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 1 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 2 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 2 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 3 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 3 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 4 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 4 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 5 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 5 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 6 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 6 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 7 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 7 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 8 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 8 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct ("Placeholder 9 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 9 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 10 bonus", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Placeholder 10 cost", FieldType.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
					FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
			};
			newEntryValues = new Object[] {
					0, -1, "<New civilization>", 0, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
					0f, 0f, 0f, 0f, 0f, 0f,
			};
		}
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

	@Override
	public boolean hasCustomEntryName () {
		return false;
	}

	@Override
	public String getCustomEntryName (int index, List <Object> values) {
		return null;
	}

	@Override
	public String getEntryDescription (Entry entry) {
		return null;
	}

}
