package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Type;

/**
 * Represents the file dbweapontohit.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.COMPLETE, AOC = ParseState.COMPLETE)
public class WeaponToHit extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final WeaponToHit instance = new WeaponToHit();

	/**
	 * Creates a new {@link WeaponToHit}
	 */
	private WeaponToHit () {
		super("Weapons to hit", "dbweapontohit.dat", true, 0, 1, 0, 0, 1, 2, 3, 125, 175);
	}
	
	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Shock mult", Type.INTEGER),
				new FieldStruct("Arrow mult", Type.INTEGER), new FieldStruct("Pierce mult", Type.INTEGER), new FieldStruct("Gun mult", Type.INTEGER), new FieldStruct("Laser mult", Type.INTEGER),
				new FieldStruct("Missile mult", Type.INTEGER)
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

}
