/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author yiqian
 */


import java.math.BigInteger;

public class StringUtil {

	/**
	 * 
	 * convert a binary string to a decimal integer number
	 * 
	 * @param binary
	 *            binary String
	 * @return decimal number
	 * 
	 */
	public static int binaryToDecimal(String binary) {
		return new BigInteger(binary, 2).intValue();
	}

	public static int signedBinaryToDecimal(String binary, boolean signed) {
		if (signed) {
			String sub = binary.substring(1, binary.length());
			sub = sub.replace('1', '2');
			sub = sub.replace('0', '1');
			sub = sub.replace('2', '0');
			int i = new BigInteger(sub, 2).intValue();
			return -i - 1;
		} else {
			return binaryToDecimal(binary);
		}
	}

	/**
	 * 
	 * convert a decimal number to a binary String with a length of @param
	 * bitLength. Additional zeros may be added at the left side of the string
	 * in order to fit this length.
	 * 
	 * @param decimal
	 *            decimal number
	 * @param bitLength
	 *            the bits of the binary
	 * @return binary String
	 * 
	 * 
	 */
	public static String decimalToBinary(int decimal, int bitLength) {
		if (decimal >= 0) {
			return String.format("%" + bitLength + "s", Integer.toBinaryString(decimal)).replace(" ", "0");
		} else {
			String seq = Integer.toBinaryString(decimal);
			return seq.substring(seq.length() - 16, seq.length());
		}

	}

}