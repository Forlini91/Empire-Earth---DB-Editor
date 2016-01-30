package datmanager;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A class which can read a *.dat file and convert the array of bytes read to integer, float and string values.
 * @author MarcoForlini
 *
 */
public class DatReader implements AutoCloseable, Closeable {
	private FileInputStream reader;
	private boolean closed = false;
	
	public DatReader(File file) throws IOException {
		reader = new FileInputStream (file);
	}

	public int getRemaining() throws IOException{
		return reader.available();
	}
	
	public int readInt(int numBytes) throws IOException {
		byte[] buffer = new byte[numBytes];
		reader.read(buffer);
		return ByteManager.bytesToInt(buffer);
	}
	
	public float readFloat(int numBytes) throws IOException {
		byte[] buffer = new byte[numBytes];
		reader.read(buffer);
		return ByteManager.bytesToFloat(buffer);
	}
	
	public String readChars(int numBytes) throws IOException {
		byte[] buffer = new byte[numBytes];
		reader.read(buffer);
		return String.valueOf(ByteManager.bytesToChars(buffer));
	}
	
	@Override
	public void close() throws IOException {
		if (!closed){
			reader.close();
			closed = true;
		}
	}
}