package com.projektNAI.TSP;

public class HexUtil {
	/**
	 * Zamienia tablice bajt�w na String ASCII
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	public static String bytesToASCII(byte[] data, int offset, int length) {
		StringBuilder bld = new StringBuilder();
		for (int i = offset; i < data.length && i < offset + length; i++) {
			if ((data[i]+256)%256 > 31 && (data[i]+256)%256 < 128) {
				bld.append((char) data[i]);
			}
		}
		return bld.toString();
	}
	/**
	 * Zamienia tablice bajt�w na String ASCII
	 * @param data
	 * @return
	 */
	public static String bytesToASCII(byte[] data) {
		return bytesToASCII(data, 0, data.length);
	}
	/**
	* Convenience method to convert a byte to a hex string.
	*
	* @param data the byte to convert
	* @return String the converted byte
	*/
	public static String byteToHex(byte data)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(toHexChar((data>>>4)&0x0F));
		buf.append(toHexChar(data&0x0F));
		return buf.toString();
	}
	/**
	* Convenience method to convert an int to a hex char.
	*
	* @param i the int to convert
	* @return char the converted char
	*/
	public static char toHexChar(int i){
		if ((0 <= i) && (i <= 9 ))
			return (char)('0' + i);
		else
			return (char)('a' + (i-10));
	} 
	/**
	* Convenience method to convert a byte array to a hex string.
	*
	* @param data the byte[] to convert
	* @return String the converted byte[]
	*/
	public static String bytesToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for ( int i = 0; i < data.length; i++ ) {
			buf.append( byteToHex(data[i]) );
			if(i > 0 && i%16 == 15) {
				buf.append("\n");
			} else if (i > 0 && i%2==1) {
				buf.append(" ");
			}
		}
		return(buf.toString());
	} 
	public static String bytesToHex(byte[] data,int offset,int length) {
		StringBuffer buf = new StringBuffer();
		for ( int i = offset; i < length+offset; i++ ) {
			buf.append( byteToHex(data[i]) );
			if(i > 0 && i%16 == 15) {
				buf.append("\n");
			} else if (i > 0 && i%2==1) {
				buf.append(" ");
			}
		}
		return(buf.toString());
	}

	public static String bytesToParseableHex(byte[] data,int offset,int length) {
		StringBuffer buf = new StringBuffer();
		for ( int i = offset; i < length+offset; i++ ) {
			buf.append( byteToHex(data[i]) );
		}
		return(buf.toString());
	}

	public static String bytesToParseableHex(int[] data,int offset,int length) {
		StringBuffer buf = new StringBuffer();
		for ( int i = offset; i < length+offset; i++ ) {
			buf.append( byteToHex((byte) data[i]) );
		}
		return(buf.toString());
	}

	/*
	 *  ******************* DEKODOWANIE BCD ******************
	 */
	/**
	 * BCD -> INT
	 * @param b - liczba zakodowana w BCD
	 * @return odkodowany int
	 */
	private static int byteBCDToIntNoEx(byte b) {
		return (int) 10*(((b+256)%256)/16) +  (((b+256)%256)%16);
	}

	private static int byteBCDtoIntV2(byte b){
		System.out.println("hex="+ b);
		int result = 0;

		int byteAsInt = b & 0xFF;

		result += (byteAsInt % 16);
		result += (10 * (byteAsInt / 16));

		System.out.println("dec="+ result);
		return result;
	}

	private static long bytesBCDToLongNoEx(byte[] b,int offset,int length) {
		long result = 0;
		for(int i=0; i<length; i++) {
			result += byteBCDToIntNoEx(b[i+offset])*(long)Math.pow(100, length - i - 1);
		}
		return result;
	}
	/**
	 * BCD -> INT
	 * @param b - liczba zakodowana w BCD
	 * @return odkodowany int lub zero je�li b nie jest bcd
	 */
	public static int byteBCDToIntOrZero(byte b) {
		if(!isBCD(b))
			return 0;
		return byteBCDToIntNoEx(b);
	}
//	public static long bytesBCDToLongOrZero(byte[] b,int offset,int length) {
//		return bytesBCDToLongOrZero(b, offset, length, null);
//	}
//
//	public static long bytesBCDToLongOrZero(byte[] b,int offset,int length, OdczytLogger logger) {
//		if(!isBCD(b, offset, length)) {
//			if (logger != null)
//				logger.log("oczekiwano BCD, offset: " + offset);
//			return 0;
//		}
//		return bytesBCDToLongNoEx(b, offset, length);
//	}
//	public static long bytesBCDToLongOrZero(byte[] b) {
//		return bytesBCDToLongOrZero(b,0,b.length);
//	}
//	public static int byteBCDToInt(byte b) throws NotBCDException {
//		if(!isBCD(b))
//			throw new NotBCDException();
//		return byteBCDToIntNoEx(b);
//	}
//	public static long bytesBCDToLong(byte[] b,int offset,int length) throws NotBCDException {
//		if(!isBCD(b, offset, length))
//			throw new NotBCDException();
//		return bytesBCDToLongNoEx(b, offset, length);
//	}
//	public static long bytesBCDToLong(byte[] b) throws NotBCDException {
//		return bytesBCDToLong(b,0,b.length);
//	}
	public static long bytesHexToLong(byte[] b) {
		return bytesHexToLong(b, 0, b.length);
	}

	public static long bytesHexToLong(byte[] b, int offset, int length) {
		long result = 0;
		for (int i = 0; i < length; i++) {
			result += byteToInt(b[offset + i]) * (long)Math.pow(256, length - i - 1);
		}
		return result;
	}

//	public static String bytesHexToBCDV2(byte[] b, int offset, int length) {
//
//		long result = 0;
//		int shiftOffset = 1;
//
//		for (int i = length-1; i > -1; i--) {
//
//			System.out.println("offset = "+(offset+i));
//			result += (byteBCDtoIntV2(b[offset + i]) * (shiftOffset));
//
//			shiftOffset *= 100;
//		}
//
//		return ""+result;
//	}

	public static long bytesHexToLongV2(byte[] b, int offset, int length) {

		long result = 0;
		int shiftOffset = 0;

		for (int i = length-1; i > -1; i--) {

			int byteAsInt = b[offset + i];

			result |= byteAsInt << shiftOffset;

			shiftOffset += 8;
		}

		return result;
	}

	public static int bytesHexToIntV2(byte[] b, int offset, int length) {

		int result = 0;
		int shiftOffset = 0;

		for (int i = length-1; i > -1; i--) {

			int byteAsInt = b[offset + i];

			result |= byteAsInt << shiftOffset;

			shiftOffset += 8;
		}

		return result;
	}

	public static byte[] longToBytesHex(long l) {
		int length = 1;
		for (int i = 1; i < 8; i++) {
			if (l/(long)Math.pow(256,i) >= 1) {
				length = i+1;
			}
		}
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			int oneByte = (int) (l / (long)Math.pow(256,length-1-i) - (long)((l / (long)Math.pow(256, length-i)))*256);
			result[i] = (byte) oneByte;
		}
		return result;
	}
	public static byte[] longToBytesHex(long l, int length) {
		byte[] hex = longToBytesHex(l);
		if (hex.length == length) {
			return hex;
		} else if (hex.length < length) {
			byte[] result = new byte[length];
			int dif = length - hex.length;
			for (int i = 0; i < result.length; i++) {
				if (i < dif) {
					result[i] = 0;
				} else {
					result[i] = hex[i - dif];
				}
			}
			return result;
		} else {
			byte[] result = new byte[length];
			for (int i = 0; i < result.length; i++) {
				result[i] = hex[i];
			}
			return result;
		}
	}
	/**
	 * przeksztalca liczbe long na zapis BCD jako tablica bajtow
	 * @param l
	 * @return
	 */
	public static byte[] longToBytesBCD(long l) {
		int length = 1;
		for (int i = 1; i < 8; i++) {
			if (l/(long)Math.pow(100,i) >= 1) {
				length = i+1;
			}
		}
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			int oneByte = (int) (l / (long)Math.pow(100,length-1-i) - (long)((l / (long)Math.pow(100, length-i)))*100);
			
			result[i] = (byte) ((int)(oneByte/10)*16 + oneByte%10);
		}
		return result;
	}
	public static byte[] longToBytesBCD(long l, int length) {
		byte[] bcd = longToBytesBCD(l);
		if (bcd.length == length) {
			return bcd;
		} else if (bcd.length < length) {
			byte[] result = new byte[length];
			int dif = length - bcd.length;
			for (int i = 0; i < result.length; i++) {
				if (i < dif) {
					result[i] = 0;
				} else {
					result[i] = bcd[i - dif];
				}
			}
			return result;
		} else {
			byte[] result = new byte[length];
			for (int i = 0; i < result.length; i++) {
				result[i] = bcd[i];
			}
			return result;
		}
	}
	public static boolean isBCD(byte b) {
		return ((b&0x0F) < 10 && ((b&0xF0)>>>4) < 10);
	}
	public static boolean isBCD(byte[] num, int offset, int length) {
		for (int i = 0; i < length; i++) {
			if(!isBCD(num[i + offset]))
				return false;
		}
		return true;
	}
	public static boolean isBCD(byte[] num) {
		return isBCD(num, 0, num.length);
	}
	public static byte decToBCD(int i) {
		if (i < 100) {
			int tens = i / 10;
			int units = i % 10;
			byte result = (byte) (tens * 16 + units);
			return result;
		}
		throw new NumberFormatException();
	}
	public static int byteToInt(byte b) {
		return (b+256)%256;
	}
	/**
	 * Sprawdza czy n-ty bit w bajcie num jest r�wny 1
	 * @param num
	 * @param n
	 * @return
	 */
	public static boolean isBitOn(byte num, int n) {
		//byte and = (byte) Math.pow(2, 7-n);
		//return (num & and) != 0;
		return ((num >>> (7-n)) & 1) == 1;
	}
	public static void main(String[] args) {
		int num = Integer.parseInt("0", 2);
		byte nm = (byte) num;
		System.out.println(nm);
		for (int i =0; i < 8; i++) {
		System.out.println(isBitOn(nm, i));
		}
	}
}
