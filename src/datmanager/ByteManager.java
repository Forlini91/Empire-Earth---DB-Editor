package datmanager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


/**
 * This class contains many static methods to convert bytes read from the files to integer, float or string values, and vice versa.
 * @author MarcoForlini
 *
 */
public class ByteManager {

	/**
	 * Convert the given bytes to an integer value
	 * @param the array of bytes to convert
	 * @return the integer value
	 */
	public static int bytesToInt(byte[] bytes){
		int value = 0;
		for (int i = bytes.length-1; i >= 0; i--){
			value += (bytes[i] & 0xFF);
			if (i > 0){
				value <<= 8;
			}
		}
		return value;
	}

	/**
	 * Convert the given integer value to an array of bytes with the given size
	 * @param the integer value to convert
	 * @param numBytes the number of bytes
	 * @return the array of bytes
	 */
	public static byte[] intToBytes(int value, int numBytes){
		byte[] bytes = new byte[numBytes];
		for (byte i = 0; i < numBytes; i++){
			bytes[i] = (byte) (value % 256);
			value >>= 8;
		}
		return bytes;
	}

	/**
	 * Convert the given bytes to a float value
	 * @param the array of bytes to convert
	 * @return the float value
	 */
	public static float bytesToFloat(byte[] bytes){
		return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}

	/**
	 * Convert the given float value to an array of bytes with the given size
	 * @param the float value to convert
	 * @param numBytes the number of bytes
	 * @return the array of bytes
	 */
	public static byte[] floatToBytes(float value, int numBytes){
		return ByteBuffer.allocate(numBytes).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).array();
	}
	
	/**
	 * Convert the given bytes to an array of chars
	 * @param the array of bytes to convert
	 * @return the array of chars
	 */
	public static char[] bytesToChars(byte[] bytes){
		int n = bytes.length;
		char[] chars = new char[n];
		for(int i = 0; i < n; i++){
			chars[i] = (char) bytes[i];
		}
		return chars;
	}
	
	/**
	 * Convert the given array of chars to an array of bytes with the given size
	 * @param the array of chars to convert
	 * @param numBytes the number of bytes
	 * @return the array of bytes
	 */
	public static byte[] charsToBytes(char[] chars, int numBytes){
		int n = chars.length;
		byte[] bytes = new byte[numBytes];
		if (n > numBytes){
			n = numBytes;
		}
		for (int i = 0; i < n; i++){
			bytes[i] = (byte) chars[i];
		}
		for (int i = n; i < numBytes; i++){
			bytes[i] = 0;
		}
		return bytes;
	}

}
