package datstructure.structures;

import constants.ControlType;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;

/**
 * Represents the file dbuicontrolevents.dat
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.COMPLETE, AOC = ParseState.COMPLETE)
public class UIControlEvents extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final UIControlEvents instance = new UIControlEvents ();

	/**
	 * Creates a new {@link UIControlEvents}
	 */
	private UIControlEvents () {
		super ("UI Control events", "dbuicontrolevents.dat", true, 0, 1, 0, 0, 1, 2, 3, 150, 200);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Control", UIControls.instance, 0),
				new FieldStruct ("Control event type", ControlType.values ())
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
