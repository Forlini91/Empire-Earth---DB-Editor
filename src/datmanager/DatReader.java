package datmanager;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;

/**
 * A class which can read a *.dat file and convert the array of bytes read to integer, float and string values.
 * @author MarcoForlini
 *
 */
public class DatReader implements Closeable {
	private boolean closed = false;
	private ByteBuffer reader;
	
	/**
	 * Create a new DatReader and read the given file
	 * @param file	The file to read
	 * @throws IOException	If any IO exception occur
	 */
	public DatReader(File file) throws IOException {
		byte[] buffer = Files.readAllBytes(file.toPath());
		reader = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);
	}

	/**
	 * Count the amount of bytes left to read
	 * @return the amount of bytes left to read
	 * @throws IOException	If any IO exception occur
	 */
	public int getRemaining() throws IOException {
		return reader.remaining();
	}
	
	/**
	 * Read the next 4 bytes as an integer value
	 * @return the value read
	 * @throws IOException	If any IO exception occur
	 * @throws BufferUnderflowException	If buffer is empty
	 */
	public int readInt() throws IOException, BufferUnderflowException {
		return reader.getInt();
	}

	/**
	 * Read the next byte as an integer value
	 * @return the value read
	 * @throws IOException	If any IO exception occur
	 * @throws BufferUnderflowException	If buffer is empty
	 */
	public int readByte() throws IOException, BufferUnderflowException {
		return reader.get();
	}
	
	/**
	 * Read the next 4 bytes as a float value
	 * @return the value read
	 * @throws IOException	If any IO exception occur
	 * @throws BufferUnderflowException	If buffer is empty
	 */
	public float readFloat() throws IOException, BufferUnderflowException {
		return reader.getFloat();
	}
	
	/**
	 * Read the next numBytes bytes as a string value
	 * @param numBytes the length of the string
	 * @return the string read
	 * @throws IOException	If any IO exception occur
	 * @throws BufferUnderflowException	If buffer is empty
	 */
	public String readString(int numBytes) throws IOException, BufferUnderflowException {
		byte[] buffer = new byte[numBytes];
		reader.get(buffer);
		return new String(buffer);
	}
	
	@Override
	public void close() throws IOException {
		if (!closed){
			closed = true;
		}
	}
}