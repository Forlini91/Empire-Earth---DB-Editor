package datstructure.structures;

import datmanager.Core;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Knowledge;
import datstructure.Type;
import gui.GUI;

/**
 * Represents the file dbcivilization.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class Civilization extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Civilization instance = new Civilization();

	/**
	 * Creates a new {@link Civilization}
	 */
	private Civilization () {
		super("Civilizations", "dbcivilization.dat", true, 0, 0, 1, 2, 0, 1, 4, 125, 175);
	}
	
	@Override
	public void init () {
		if (!Core.AOC){		//File structure has been changed in AOC
			fieldStructs = new FieldStruct[]{
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.ID_LANGUAGE,
					new FieldStruct("Cost increment", Type.INTEGER), ID_UNIT_SET, new FieldStruct("Attack bonus", Type.FLOAT), new FieldStruct("Attack bonus cost", Type.FLOAT),
					new FieldStruct("Health bonus", Type.FLOAT), new FieldStruct("Health bonus cost", Type.FLOAT), new FieldStruct("Attack speed bonus", Type.FLOAT), new FieldStruct("Attack speed bonus cost", Type.FLOAT),
					new FieldStruct("Armor bonus", Type.FLOAT), new FieldStruct("Armor bonus cost", Type.FLOAT), new FieldStruct("Range bonus", Type.FLOAT), new FieldStruct("Range bonus cost", Type.FLOAT),
					new FieldStruct("Speed bonus", Type.FLOAT), new FieldStruct("Speed bonus cost", Type.FLOAT), new FieldStruct("Flight time bonus", Type.FLOAT), new FieldStruct("Flight time bonus cost", Type.FLOAT),
					new FieldStruct("Area damage bonus", Type.FLOAT), new FieldStruct("Area damage bonus cost", Type.FLOAT), new FieldStruct("Hunting & foraging bonus", Type.FLOAT), new FieldStruct("Hunting & foraging bonus cost", Type.FLOAT),
					new FieldStruct("Wood cutting bonus", Type.FLOAT), new FieldStruct("Wood cutting bonus cost", Type.FLOAT), new FieldStruct("Gold mining bonus", Type.FLOAT), new FieldStruct("Gold mining bonus cost", Type.FLOAT),
					new FieldStruct("Farming bonus", Type.FLOAT), new FieldStruct("Farming bonus cost", Type.FLOAT), new FieldStruct("Stone mining bonus", Type.FLOAT), new FieldStruct("Stone mining bonus cost", Type.FLOAT),
					new FieldStruct("Iron mining bonus", Type.FLOAT), new FieldStruct("Iron mining bonus cost", Type.FLOAT), new FieldStruct("Build time reduction", Type.FLOAT), new FieldStruct("Build time reduction cost", Type.FLOAT),
					new FieldStruct("Conversion resistance bonus", Type.FLOAT), new FieldStruct("Conversion resistance bonus cost", Type.FLOAT), new FieldStruct("Fishing bonus", Type.FLOAT), new FieldStruct("Fishing bonus cost", Type.FLOAT),
					new FieldStruct("Repair bonus", Type.FLOAT), new FieldStruct("Repair bonus cost", Type.FLOAT), new FieldStruct("Area effect bonus", Type.FLOAT), new FieldStruct("Area effect bonus cost", Type.FLOAT),
					new FieldStruct("Mountain combat bonus", Type.FLOAT), new FieldStruct("Mountain combat bonus cost", Type.FLOAT), new FieldStruct("Population cap bonus", Type.FLOAT), new FieldStruct("Population cap bonus cost", Type.FLOAT),
					new FieldStruct("Area conversion bonus", Type.FLOAT), new FieldStruct("Area conversion bonus cost", Type.FLOAT), new FieldStruct("Cost reduction", Type.FLOAT), new FieldStruct("Cost reduction cost", Type.FLOAT),
					new FieldStruct("Night-time LOS bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Night-time LOS bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Slavery bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Slavery bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Capitol upgrade bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Capitol upgrade bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Heroes bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Heroes bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 1 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 1 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 2 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 2 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 3 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 3 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 4 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 4 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 5 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 5 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 6 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 6 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 7 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 7 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 8 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 8 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 9 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 9 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 10 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 10 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN)
			};
			newEntryValues = new Object[]{
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

			fieldStructs = new FieldStruct[]{
					FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.ID_LANGUAGE,
					new FieldStruct("Cost increment", Type.INTEGER), ID_UNIT_SET, new FieldStruct("Attack bonus", Type.FLOAT), new FieldStruct("Attack bonus cost", Type.FLOAT),
					new FieldStruct("Health bonus", Type.FLOAT), new FieldStruct("Health bonus cost", Type.FLOAT), new FieldStruct("Attack speed bonus", Type.FLOAT), new FieldStruct("Attack speed bonus cost", Type.FLOAT),
					new FieldStruct("Armor bonus", Type.FLOAT), new FieldStruct("Armor bonus cost", Type.FLOAT), new FieldStruct("Range bonus", Type.FLOAT), new FieldStruct("Range bonus cost", Type.FLOAT),
					new FieldStruct("Speed bonus", Type.FLOAT), new FieldStruct("Speed bonus cost", Type.FLOAT), new FieldStruct("Flight time bonus", Type.FLOAT), new FieldStruct("Flight time bonus cost", Type.FLOAT),
					new FieldStruct("Area damage bonus", Type.FLOAT), new FieldStruct("Area damage bonus cost", Type.FLOAT), new FieldStruct("Hunting & foraging bonus", Type.FLOAT), new FieldStruct("Hunting & foraging bonus cost", Type.FLOAT),
					new FieldStruct("Wood cutting bonus", Type.FLOAT), new FieldStruct("Wood cutting bonus cost", Type.FLOAT), new FieldStruct("Gold mining bonus", Type.FLOAT), new FieldStruct("Gold mining bonus cost", Type.FLOAT),
					new FieldStruct("Farming bonus", Type.FLOAT), new FieldStruct("Farming bonus cost", Type.FLOAT), new FieldStruct("Stone mining bonus", Type.FLOAT), new FieldStruct("Stone mining bonus cost", Type.FLOAT),
					new FieldStruct("Iron mining bonus", Type.FLOAT), new FieldStruct("Iron mining bonus cost", Type.FLOAT), new FieldStruct("Build time reduction", Type.FLOAT), new FieldStruct("Build time reduction cost", Type.FLOAT),
					new FieldStruct("Conversion resistance bonus", Type.FLOAT), new FieldStruct("Conversion resistance bonus cost", Type.FLOAT), new FieldStruct("Fishing bonus", Type.FLOAT), new FieldStruct("Fishing bonus cost", Type.FLOAT),
					new FieldStruct("Repair bonus", Type.FLOAT), new FieldStruct("Repair bonus cost", Type.FLOAT), new FieldStruct("Area effect bonus", Type.FLOAT), new FieldStruct("Area effect bonus cost", Type.FLOAT),
					new FieldStruct("Mountain combat bonus", Type.FLOAT), new FieldStruct("Mountain combat bonus cost", Type.FLOAT), new FieldStruct("Population cap bonus", Type.FLOAT), new FieldStruct("Population cap bonus cost", Type.FLOAT),
					new FieldStruct("Area conversion bonus", Type.FLOAT), new FieldStruct("Area conversion bonus cost", Type.FLOAT), new FieldStruct("Cost reduction", Type.FLOAT), new FieldStruct("Cost reduction cost", Type.FLOAT),
					new FieldStruct("Night-time LOS bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Night-tme LOS bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Slavery bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Slavery bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Capitol upgrade bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Capitol upgrade bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Heroes bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Heroes bonus cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 1 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 1 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 2 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 2 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 3 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 3 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 4 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 4 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 5 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 5 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 6 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 6 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 7 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 7 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 8 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 8 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
					new FieldStruct("Placeholder 9 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 9 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 10 bonus", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Placeholder 10 cost", Type.FLOAT, 4, Knowledge.NEVER_USED, GUI.COLOR_FIELD_UNCERTAIN),
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
			newEntryValues = new Object[]{
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

}
