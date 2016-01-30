package datmanager;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A class which can convert the integer, float and string values to array of bytes and save them in a *.dat file.
 * @author MarcoForlini
 *
 */
public class DatWriter implements AutoCloseable, Closeable {
	private FileOutputStream writer;
	private boolean closed = false;

	public DatWriter(File file) throws IOException {
		writer = new FileOutputStream(file);
	}

	public void writeInt(int value, int numBytes) throws IOException {
		byte[] buffer = ByteManager.intToBytes(value, numBytes);
		writer.write(buffer);
	}

	public void writeFloat(float value, int numBytes) throws IOException {
		byte[] buffer = ByteManager.floatToBytes(value, numBytes);
		writer.write(buffer);
	}
	
	public void writeChars(String chars, int numBytes) throws IOException{
		byte[] buffer = ByteManager.charsToBytes(chars.toCharArray(), numBytes);
		writer.write(buffer);
	}

	@Override
	public void close() throws IOException {
		if (!closed){
			writer.flush();
			writer.close();
			closed = true;
		}
	}
}