package datstructure.structures;

import java.util.List;

import constants.FormEventType;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;


/**
 * Represents the file dbuiformevents.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.COMPLETE, AOC = ParseState.COMPLETE)
public class UIFormEvents extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIFormEvents instance = new UIFormEvents ();

	/**
	 * Creates a new {@link UIFormEvents}
	 */
	private UIFormEvents () {
		super ("UI FormsEvents", "dbuiformevents.dat", true, 0, 0, 0, 0, 1, 2, 2, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, ID_UI_FORM,
				new FieldStruct ("Event type", FormEventType.values ())
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
