package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbterraingraytextures.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.COMPLETE, AOC = ParseState.COMPLETE)
public class TerrainGrayTextures extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final TerrainGrayTextures instance = new TerrainGrayTextures ();

	/**
	 * Creates a new {@link TerrainGrayTextures}
	 */
	private TerrainGrayTextures () {
		super ("Terrain gray textures", "dbterraingraytextures.dat", true, 0, 1, 0, 2, 3, 4, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.STRING_SIZE, new FieldStruct ("Pathname", 0), FieldStruct.NAME, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, new FieldStruct ("Color: Red", FieldType.INTEGER), new FieldStruct ("Color: Green", FieldType.INTEGER),
				new FieldStruct ("Color: Blue", FieldType.INTEGER)
		};
		newEntryValues = new Object[] {
				0, "", "<New terrain gray>", 0, -1, 0, 0, 0
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
