package datstructure.structures;

import java.io.IOException;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;


/**
 * Represents the file dbgraphics.dat
 *
 * @author MarcoForlini
 */
public class Graphics extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Graphics instance = new Graphics();

	/**
	 * Creates a new {@link Graphics}
	 */
	private Graphics() {
		super("Graphics", "dbgraphics.dat", true, 0, 1, 1, 66, 67, 68, -1, 4, 125, 175);
	}

	@Override
	public void customInit() throws IOException {
		// fieldStructs = new FieldStruct[] {
		// FieldStruct.STRING_SIZE, new FieldStruct ("Name 1", 0), FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 2),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 4), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 6),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 8), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 10),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 12), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 14),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 16), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 18),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 20), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 22),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 24), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 26),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 28), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 30),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 32), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 34),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 36), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 38),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 40), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 42),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 44), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 46),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 48), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 50),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 52), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 54),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 56), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 58),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Model path", 60), FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 62),
		// FieldStruct.STRING_SIZE, new FieldStruct ("Texture path", 64), FieldStruct.NAME, FieldStruct.SEQ_NUMBER,
		// FieldStruct.ID, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, ID_SOUND, ID_SOUND, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
		// ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, ID_SOUND, ID_SOUND, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4,
		// ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, ID_SOUND, ID_SOUND, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
		// ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, ID_SOUND, ID_SOUND, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
		// ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4, ID_SOUND, ID_SOUND, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNUSED_INT4,
		// ID_SOUND, ID_SOUND, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, ID_SOUND, ID_SOUND, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_BOOL4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNUSED_INT4, new FieldStruct ("<Some entries have link to themselves", Graphics.instance, 0), FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, // FF FF FF FF
		// FieldStruct.UNKNOWN_INT4, FieldStruct.UNKNOWN_FLOAT, FieldStruct.UNUSED_INT4, FieldStruct.UNKNOWN_INT4,
		// FieldStruct.UNKNOWN_INT4
		// };
		newEntryValues = new Object[] {
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", 0, "", 0, "", 0, "",
				0, "", "<New graphic>", 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				-1, -1, 0, 0, 0, 0, 0, 0,
				0, 0, -1, -1, 0, 0, 0, 0,
				0, 0, 0, 0, -1, -1, 0, 0,
				0, 0, 0, 0, 0, 0, -1, -1,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, -1, -1, 0, 0,
				0, 0, 0, 0, 0, 0, -1, -1,
				0, 0, 0, 0, 0, 0, 0, 0,
				-1, -1, 0, 0, 0, 0, 0, 0,
				0, 0, -1, -1, 0, 0, 0, 0,
				0, 0, 0, 0, -1, -1, 0, 0,
				0, 0, 0, 0, 0, 0, -1, -1,
				0, 0, 0, 0, 0, 0, 0, 0,
				-1, -1, 0, 0, 0, 0, 0, 0,
				0, 0, -1, -1, 0, 0, 0, 0,
				0, -1, 0, 0, -1, -1, 0, 1f,
				0, 0f, 0, 0, 0
		};
	}

	@Override
	public int indexExtraFields() {
		return -1;
	}

	@Override
	public boolean hasCustomEntryName() {
		return false;
	}

	@Override
	public String getCustomEntryName(int index, List<Object> values) {
		return null;
	}

	@Override
	public String getEntryDescription(Entry entry) {
		return null;
	}

}
