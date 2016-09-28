package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;

/**
 * Represents the file dbpremadecivs.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.MISSING_UNKNOWN, AOC = ParseState.MISSING_UNKNOWN)
public class PremadeCivs extends DatStructure {
	
	/**
	 * Unique instance of this structure
	 */
	public static final PremadeCivs instance = new PremadeCivs();

	/**
	 * Creates a new {@link PremadeCivs}
	 */
	private PremadeCivs () {
		super("Premade civilizations", "dbpremadecivs.dat", true, 0, 1, 0, 2, 0, 1, 2, 125, 175);
	}
	
	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.SEQ_NUMBER, FieldStruct.ID, FieldStruct.NAME, FieldStruct.UNKNOWN_INT4,
				FieldStruct.ID_LANGUAGE, FieldStruct.ID_LANGUAGE,
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}
	
}
