/** create by gwei at 2017-10-28 上午9:46:51 */
package com.xyf.learnweb.common.utils;

import java.math.BigInteger;

public class SM2Const {
	public static final BigInteger p = new BigInteger(
			"FFFFFFFE FFFFFFFF FFFFFFFF FFFFFFFF FFFFFFFF 00000000 FFFFFFFF FFFFFFFF".replaceAll(" ", ""), 16);
	public static final BigInteger a = new BigInteger(
			"FFFFFFFE FFFFFFFF FFFFFFFF FFFFFFFF FFFFFFFF 00000000 FFFFFFFF FFFFFFFC".replaceAll(" ", ""), 16);
	public static final BigInteger b = new BigInteger(
			"28E9FA9E 9D9F5E34 4D5A9E4B CF6509A7 F39789F5 15AB8F92 DDBCBD41 4D940E93".replaceAll(" ", ""), 16);
	public static final BigInteger n = new BigInteger(
			"FFFFFFFE FFFFFFFF FFFFFFFF FFFFFFFF 7203DF6B 21C6052B 53BBF409 39D54123".replaceAll(" ", ""), 16);
	public static final BigInteger Gx = new BigInteger(
			"32C4AE2C 1F198119 5F990446 6A39C994 8FE30BBF F2660BE1 715A4589 334C74C7".replaceAll(" ", ""), 16);
	public static final BigInteger Gy = new BigInteger(
			"BC3736A2 F4F6779C 59BDCEE3 6B692153 D0A9877C C62A4740 02DF32E5 2139F0A0".replaceAll(" ", ""), 16);
}
