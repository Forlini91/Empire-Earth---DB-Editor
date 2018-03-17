package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbgamevariant.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class GameVariant extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final GameVariant instance = new GameVariant ();

	/**
	 * Creates a new {@link GameVariant}
	 */
	private GameVariant () {
		super ("Game variants", "dbgamevariant.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void init () {
		FieldStruct ALTER_HEALTH_BY = new FieldStruct ("Alter health percent... ", FieldType.FLOAT, 4);
		FieldStruct ID_UNIT_SET_GV = new FieldStruct (".. of Set:", UnitSet.instance, 0);
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ALTER_HEALTH_BY,
				ID_UNIT_SET_GV, ALTER_HEALTH_BY, ID_UNIT_SET_GV, ALTER_HEALTH_BY,
				ID_UNIT_SET_GV, ALTER_HEALTH_BY, ID_UNIT_SET_GV, ALTER_HEALTH_BY,
				ID_UNIT_SET_GV, ALTER_HEALTH_BY, ID_UNIT_SET_GV, ALTER_HEALTH_BY,
				ID_UNIT_SET_GV, ALTER_HEALTH_BY, ID_UNIT_SET_GV, ALTER_HEALTH_BY,
				ID_UNIT_SET_GV, ALTER_HEALTH_BY, ID_UNIT_SET_GV, FieldStruct.UNCHANGED_FLOAT,
				new FieldStruct ("Epoch advancement cost multiplier", "A global multiplier for all epochs advancement", FieldType.FLOAT), new FieldStruct ("Gather time multiplier", "A global multiplier for all gather times", FieldType.FLOAT), new FieldStruct ("Alter max morale (dynamic)", "Alter the max morale allowed by capitols and town centers by the given amount", FieldType.INTEGER), FieldStruct.ID_LANGUAGE,
				new FieldStruct ("First epoch advancement cost multiplier", "Affect the very first epoch advancement cost you make", FieldType.FLOAT), new FieldStruct ("Second epoch advancement cost multiplier", "Affect the second epoch advancement cost you make", FieldType.FLOAT), new FieldStruct ("Cost multiplier...", FieldType.FLOAT), ID_UNIT_SET_GV,
				FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT,
				FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, FieldStruct.UNUSED_FLOAT, new FieldStruct ("Available in random maps", FieldType.BOOLEAN, 4),
				FieldStruct.UNUSED_FLOAT
		};
		newEntryValues = new Object[] {
				"<New game variant>", 0, -1, 0f, -1, 0f, -1, 0f,
				-1, 0f, -1, 0f, -1, 0f, -1, 0f,
				-1, 0f, -1, 0f, -1, 0f, -1, 0f,
				1.0f, 1.0f, 0, 0, 1.0f, 1.0f, 0f, -1,
				1.0f, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 0
		};
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
