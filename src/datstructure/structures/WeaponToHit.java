package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbweapontohit.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.COMPLETE, AOC = ParseState.COMPLETE)
public class WeaponToHit extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final WeaponToHit instance = new WeaponToHit ();

	/**
	 * Creates a new {@link WeaponToHit}
	 */
	private WeaponToHit () {
		super ("Weapons to hit", "dbweapontohit.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Shock mult", FieldType.INTEGER),
				new FieldStruct ("Arrow mult", FieldType.INTEGER), new FieldStruct ("Pierce mult", FieldType.INTEGER), new FieldStruct ("Gun mult", FieldType.INTEGER), new FieldStruct ("Laser mult", FieldType.INTEGER),
				new FieldStruct ("Missile mult", FieldType.INTEGER)
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
