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

/**
 * A class which can convert the integer, float and string values to array of bytes and save them in a *.dat file.
 * @author MarcoForlini
 *
 */
public class DatWriter implements Closeable {
	private DatFile datFile;
	private ByteBuffer writer;
	private boolean closed = false;

	
	/**
	 * @param datFile		The datFile to write
	 * @throws IOException	If any IO exception occur
	 */
	public DatWriter(DatFile datFile) throws IOException {
		this.datFile = datFile;
		List<Entry> entries = datFile.getAllEntries();
		int sizeSingle = datFile.datStructure.getNumBytes();

		//Adds all "numEntries" fields in the file (0 if the field is not present, 4*numEpochs in dbtechtree.dat, 4 in the others)
		int alloc = datFile.datStructure.defineNumEntries ? 4 * datFile.entryGroups.size() : 0;
		alloc += sizeSingle * entries.size();		//Adds the base size of an entry * the number of entries
		
		/* If the file contains extra fields, adds 4 for each one */
		if (datFile.datStructure.extraField != null){
			int indexExtra = datFile.datStructure.getIndexExtraFields();
			alloc += 4 * entries.parallelStream().mapToInt(entry -> (int)entry.values.get(indexExtra)).sum();
		}
		
		alloc += Arrays
				.stream(datFile.datStructure.fieldStructs)	//iterate all FieldStructs
				.parallel()
				.filter(fs -> fs.indexSize >= 0)			//only take the ones with indexSize >= 0
				.mapToInt(fs -> entries.stream().mapToInt(entry -> (int)entry.values.get(fs.indexSize)).sum())	//take the size every entry declare in that field and sum it
				.sum();
		writer = ByteBuffer.allocate(alloc).order(ByteOrder.LITTLE_ENDIAN);
	}
	



	/**
	 * Write a 4 bytes integer
	 * @param value the value to write
	 * @throws IOException	If any IO exception occur
	 */
	public void writeInt(int value) throws IOException {
		writer.putInt(value);
	}
	
	/**
	 * Write a single byte integer
	 * @param value the value to write
	 */
	public void writeByte(int value){
		writer.put((byte) value);
	}

	/**
	 * Write a 4 bytes float
	 * @param value the value to write
	 * @throws IOException	If any IO exception occur
	 */
	public void writeFloat(float value) throws IOException {
		writer.putFloat(value);
	}
	
	/**
	 * Write a string of length numBytes
	 * @param string the string to write
	 * @param numBytes the length of the string (trim the string or put extra \0 chars if string length is different than this value)
	 * @throws IOException	If any IO exception occur
	 */
	public void writeString(String string, int numBytes) throws IOException{
		byte[] str = Arrays.copyOf(string.getBytes(), numBytes);
		writer.put(str);
	}

	@Override
	public void close() throws IOException {
		if (!closed){
			try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(datFile))){
				bos.write(writer.array());
			}
			closed = true;
		}
	}
}