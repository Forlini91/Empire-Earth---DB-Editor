package dbmanager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteManager {
	
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
	
	public static byte[] intToBytes(int value, int numBytes){
		byte[] bytes = new byte[numBytes];
		for (byte i = 0; i < numBytes; i++){
			bytes[i] = (byte) (value % 256);
			value >>= 8;
		}
		return bytes;
	}
	

	public static float bytesToFloat(byte[] bytes){
		return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	
	public static byte[] floatToBytes(float value, int numBytes){
		return ByteBuffer.allocate(numBytes).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).array();
	}


	public static char[] bytesToChars(byte[] bytes){
		int n = bytes.length;
		char[] chars = new char[n];
		for(int i = 0; i < n; i++){
			chars[i] = (char) bytes[i];
		}
		return chars;
	}

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
	
	public static String getCharList(char[] chars){
		if (chars == null) {
			return "null";
		}
		int iMax = chars.length - 1;
		if (iMax == -1) {
			return "[]";
		}

		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0; ; i++) {
			if (chars[i] < 10){
				b.append(0);
			}
			b.append(Integer.toHexString(chars[i]).toUpperCase());
			if (i == iMax) {
				return b.append(']').toString();
			}
			b.append(' ');
		}
	}
	
}
