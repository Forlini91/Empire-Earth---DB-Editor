package datmanager;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * A class which can read a *.dat file and convert the array of bytes read to integer, float and string values.
 * @author MarcoForlini
 *
 */
public class DatReader implements AutoCloseable, Closeable {
	private boolean closed = false;
	private ByteBuffer reader;
	
	public DatReader(File file) throws IOException {
		byte[] buffer = Files.readAllBytes(file.toPath());
		reader = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);
	}

	public int getRemaining() throws IOException {
		return reader.remaining();
	}
	
	public int readInt() throws IOException, BufferUnderflowException {
		return reader.getInt();
	}

	public int readByte() throws IOException, BufferUnderflowException {
		return reader.get();
	}
	
	public float readFloat() throws IOException, BufferUnderflowException {
		return reader.getFloat();
	}
	
	public String readString(int numBytes) throws IOException, BufferUnderflowException {
		byte[] buffer = new byte[numBytes];
		reader.get(buffer);
		return new String(buffer, StandardCharsets.ISO_8859_1);
	}
	
	@Override
	public void close() throws IOException {
		if (!closed){
			closed = true;
		}
	}
}