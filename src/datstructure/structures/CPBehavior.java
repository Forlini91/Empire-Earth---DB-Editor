package datstructure.structures;

import java.util.List;

import datmanager.Language;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbcpbehavior.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class CPBehavior extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final CPBehavior instance = new CPBehavior ();

	/**
	 * Creates a new {@link CPBehavior}
	 */
	private CPBehavior () {
		super ("CP Behavior", "dbcpbehavior.dat", true, 0, 1, 0, -1, 0, 1, 3, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.UNKNOWN_INT4, new FieldStruct ("Property 1 - Max value", FieldType.INTEGER, 4),
				new FieldStruct ("Property 1 - Default value (int)", FieldType.INTEGER, 4), new FieldStruct ("Property 1 - Default value (float)", FieldType.FLOAT, 4), new FieldStruct ("Property 2 - Max value", FieldType.INTEGER, 4), new FieldStruct ("Property 2 - Default value (int)", FieldType.INTEGER, 4),
				new FieldStruct ("Property 2 - Default value (float)", FieldType.FLOAT, 4), FieldStruct.UNKNOWN_INT4, new FieldStruct ("Property 3 - Max value", FieldType.INTEGER, 4),
				new FieldStruct ("Property 3 - Default value (int)", FieldType.INTEGER, 4), new FieldStruct ("Property 3 - Default value (float)", FieldType.FLOAT, 4),
				FieldStruct.UNKNOWN_INT4
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

	@Override
	public boolean hasCustomEntryName () {
		return true;
	}

	@Override
	public String getCustomEntryName (int index, List <Object> values) {
		Language l = Language.MAP.get (28000 + index);
		return l != null ? l.text : "<Undefined>";
	}

	@Override
	public String getEntryDescription (Entry entry) {
		Language l = Language.MAP.get (28500 + entry.getID ());
		return l != null ? l.text : "";
	}

}
