package datmanager;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import datstructure.DatContent;
import datstructure.Entry;
import datstructure.FieldStruct;

/**
 * A class which can convert the integer, float and string values to array of bytes and save them in a *.dat file.
 * @author MarcoForlini
 *
 */
public class DatWriter implements AutoCloseable, Closeable {
	private DatFile datFile;
	private ByteBuffer writer;
	private boolean closed = false;
	
	public DatWriter(DatFile datFile, DatContent datContent) throws IOException {
		this.datFile = datFile;
		int sizeSingle = datFile.datStructure.getNumBytes();
		int alloc = datContent.datStructure.defineNumEntries() ? 4 * datContent.entryGroups.size() : 0;
		List<Entry> entries = datContent.getAllEntries();
		alloc += sizeSingle * entries.size();

		int indexExtra = datContent.datStructure.getIndexCountExtra();
		List<Integer> dynamicStrings = new ArrayList<>();
		for (FieldStruct fieldStruct : datContent.datStructure.getFieldStructs()){
			if (fieldStruct.indexStringLength >= 0){
				dynamicStrings.add(fieldStruct.indexStringLength);
			}
		}
		
		if (dynamicStrings.size() > 0){
			if (indexExtra >= 0){
				for (Entry entry : entries){
					alloc += 4 * (int) entry.values.get(indexExtra);
					for (Integer indexStringLength : dynamicStrings){
						alloc += (int) entry.values.get(indexStringLength);
					}
				}
			} else {
				for (Entry entry : entries) {
					for (Integer indexStringLength : dynamicStrings){
						alloc += (int) entry.values.get(indexStringLength);
					}
				}
			}
		} else if (indexExtra >= 0){
			for (Entry entry : entries){
				alloc += 4 * (int) entry.values.get(indexExtra);
			}
		}
		writer = ByteBuffer.allocate(alloc).order(ByteOrder.LITTLE_ENDIAN);
	}
	
	
	
	public void writeInt(int value) throws IOException {
		writer.putInt(value);
	}

	public void writeByte(int value){
		writer.put((byte) value);
	}
	
	public void writeFloat(float value) throws IOException {
		writer.putFloat(value);
	}

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