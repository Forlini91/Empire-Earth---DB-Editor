package datstructure.structures;

import java.util.List;

import constants.TerrainFamily;
import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * Represents the file dbterrain.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class Terrain extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Terrain instance = new Terrain ();

	/**
	 * Creates a new {@link Terrain}
	 */
	private Terrain () {
		super ("Terrain", "dbterrain.dat", true, 0, 1, 0, 2, 3, 4, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.STRING_SIZE, new FieldStruct ("Pathname", 0), FieldStruct.NAME, FieldStruct.SEQ_NUMBER,
				FieldStruct.ID, new FieldStruct ("Terrain gray type", TerrainGrayTextures.instance, 0), FieldStruct.ID_LANGUAGE, new FieldStruct ("Valid", FieldType.BOOLEAN, 0),
				new FieldStruct ("Terrain family", TerrainFamily.values ()), new FieldStruct ("Tyles on X axis", FieldType.INTEGER), new FieldStruct ("Tiles on Y axis", FieldType.INTEGER), new FieldStruct ("Available: 0-No, 1-Animated, 65536-Static", FieldType.INTEGER, 0),
				new FieldStruct ("Terrain type", TerrainType.instance, 0), new FieldStruct ("Object from ID (1)", Objects.instance, 0), new FieldStruct ("Object from ID (2)", Objects.instance, 0), new FieldStruct ("Object from ID (3)", Objects.instance, 0),
				new FieldStruct ("Object to ID (1)", Objects.instance, 0), new FieldStruct ("Object to ID (2)", Objects.instance, 0), new FieldStruct ("Object to ID (3)", Objects.instance, 0), new FieldStruct ("<Probably> Chance spawn object per tile", FieldType.INTEGER),
				new FieldStruct ("<Used by animated terrains>", FieldType.INTEGER), new FieldStruct ("<Used by animated terrains>", FieldType.INTEGER), new FieldStruct ("Color: Red", FieldType.INTEGER), new FieldStruct ("Color: Green", FieldType.INTEGER),
				new FieldStruct ("Color: Blue", FieldType.INTEGER), FieldStruct.UNUSED_INT4, new FieldStruct ("Amb Marsh 1", FieldType.FLOAT), new FieldStruct ("Amb Marsh 2", FieldType.FLOAT),
				new FieldStruct ("Is an ambient terrain? (Yes = 16777216)", FieldType.INTEGER)
		};
		newEntryValues = new Object[] {
				0, "", "<New terrain>", 0, -1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0f, 0f, 0
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
