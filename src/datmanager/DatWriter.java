package datmanager;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.structures.RandomMap;

/**
 * A class which can convert the integer, float and string values to array of bytes and save them in a *.dat file.
 * 
 * @author MarcoForlini
 */
public class DatWriter implements Closeable {
	private DatFile datFile;
	private ByteBuffer writer;
	private boolean closed = false;


	/**
	 * @param datFile The datFile to write
	 * @param entries The entries to save
	 * @throws IOException If any IO exception occur
	 */
	public DatWriter (DatFile datFile, List <Entry> entries) throws IOException {
		this.datFile = datFile;

		int alloc = datFile.datStructure.entrySize * entries.size (); // the base size of an entry * the number of entries

		// If the file define the field(s) "numEntries", adds 4 bytes for each group, as every group define its number (epoch)
		if (datFile.datStructure.defineNumEntries) {
			alloc += 4 * datFile.entryGroups.size ();
		}
		if (datFile.datStructure == RandomMap.instance) {
			alloc += 4;
		}

		// If the entries in the file may contains extra fields, adds 4 bytes for each extra entry
		if (datFile.datStructure.extraField != null) {
			int indexExtra = datFile.datStructure.indexExtraFields ();
			alloc += 4 * entries.parallelStream ().mapToInt (entry -> entry.get (indexExtra)).sum ();
		}

		// If there are fields with dynamic size, add their size
		if (datFile.datStructure.dynamicSizeFields.length >= 0) {
			FieldStruct[] dynamicSizeFields = datFile.datStructure.dynamicSizeFields;
			int[] dsfIndexes = Arrays.stream (dynamicSizeFields).parallel ().mapToInt (dsf -> dsf.indexSize).toArray ();
			alloc += entries
					.parallelStream ()
					.mapToInt (entry -> Arrays
							.stream (dsfIndexes)
							.map (entry::get)
							.sum ())
					.sum ();
		}

		writer = ByteBuffer.allocate (alloc).order (ByteOrder.LITTLE_ENDIAN);
	}




	/**
	 * Write a 4 bytes integer
	 * 
	 * @param value the value to write
	 * @throws IOException If any IO exception occur
	 */
	public void writeInt (int value) throws IOException {
		writer.putInt (value);
	}

	/**
	 * Write a single byte integer
	 * 
	 * @param value the value to write
	 */
	public void writeByte (int value) {
		writer.put ((byte) value);
	}

	/**
	 * Write a 4 bytes float
	 * 
	 * @param value the value to write
	 * @throws IOException If any IO exception occur
	 */
	public void writeFloat (float value) throws IOException {
		writer.putFloat (value);
	}

	/**
	 * Write a string of length numBytes
	 * 
	 * @param string the string to write
	 * @param numBytes the length of the string (trim the string or put extra \0 chars if string length is different than this value)
	 * @throws IOException If any IO exception occur
	 */
	public void writeString (String string, int numBytes) throws IOException {
		byte[] str = Arrays.copyOf (string.getBytes (), numBytes);
		writer.put (str);
	}

	@Override
	public void close () throws IOException {
		if (!closed) {
			try (BufferedOutputStream bos = new BufferedOutputStream (new FileOutputStream (datFile))) {
				bos.write (writer.array ());
			}
			closed = true;
		}
	}
}