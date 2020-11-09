/**
 * created by ywc at 2012-3-20 下午1:35:18
 **/
package com.xyf.learnweb.common.utils;

public class ByteArrayUtil {
    public static String customDbIndex(int dbIndex) {
        if (0 < dbIndex && dbIndex < 10) {
            return "0" + dbIndex;
        } else if (dbIndex > 9 && dbIndex < 100) {
            return "" + dbIndex;
        } else {
            return "" + dbIndex;
        }
    }

    public static String customDbIndex(short dbIndex) {
        if (0 < dbIndex && dbIndex < 10) {
            return "00" + dbIndex;
        } else if (dbIndex > 9 && dbIndex < 100) {
            return "0" + dbIndex;
        } else {
            return "" + dbIndex;
        }
    }

    public static int parseDbName(byte[] bytes) {
        if (bytes == null || bytes.length != 2) {
            return -1;
        }
        int result;
        int high = (bytes[0] & 0xff) << 8;
        int low = (bytes[1] & 0xff);
        // System.out.println("high = " + high + ", low = " + low);
        result = high | low;
        return result;
    }

    public static int byteToSignedInt(byte[] bytes) {
        if (bytes == null || bytes.length != 4) {
            return -1;
        }
        int result;
        int a1 = (bytes[0] & 0xff) << 24;
        int a2 = (bytes[1] & 0xff) << 16;
        int a3 = (bytes[2] & 0xff) << 8;
        int a4 = (bytes[3] & 0xff);
        result = a1 | a2 | a3 | a4;
        return result;
    }

    public static byte[] signedIntToByte(int value) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(value >> 24);
        bytes[1] = (byte)(value >> 16);
        bytes[2] = (byte)(value >> 8);
        bytes[3] = (byte)value;
        return bytes;
    }

    public static byte[] unsignedIntToByte(long value) {
        String hexString = Long.toHexString(value);
        while (hexString.length() < 8) {
            hexString = "0" + hexString;
        }
        byte[] bytes = hexStringToBytes(hexString);
        return bytes;
    }

    public static byte[] unsignedIntToByte1(long value) {
        String hexString = Long.toHexString(value);
        while (hexString.length() < 2) {
            hexString = "0" + hexString;
        }
        byte[] bytes = hexStringToBytes(hexString);
        return bytes;
    }

    public static byte[] unsignedByteToByte(long value) {
        String hexString = Long.toHexString(value);
        while (hexString.length() < 2) {
            hexString = hexString + "0";
        }
        byte[] bytes = hexStringToBytes(hexString);
        return bytes;
    }

    public static byte[] unsignedIntToByte2(long value) {
        String hexString = Long.toHexString(value);
        while (hexString.length() < 4) {
            hexString = "0" + hexString;
        }
        byte[] bytes = hexStringToBytes(hexString);
        return bytes;
    }

    public static byte[] unsignedIntToByte6(long value) {
        String hexString = Long.toHexString(value);
        while (hexString.length() < 12) {
            hexString = "0" + hexString;
        }
        byte[] bytes = hexStringToBytes(hexString);
        return bytes;
    }

    public static long byteToUnsignedInt(byte[] bytes) {
        String hexString = bytesToHexString(bytes);
        long unsignedInteger = Long.parseLong(hexString, 16);
        return unsignedInteger;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexString.length() / 2];
        for (int i = 0; i < (hexString.length() / 2); i++) {
            int high = Integer.parseInt(hexString.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexString.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte)(high * 16 + low);
        }
        return result;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xff);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] swapEndianness(byte[] src) {
        for (int i = 0; i < (src.length / 2); i++) {
            byte dump = src[i];
            src[i] = src[src.length - i - 1];
            src[src.length - i - 1] = dump;
        }
        return src;
    }

    public static String phaseIdcarrier(String dbIndex) {
        while (dbIndex.length() < 12) {
            dbIndex = "0" + dbIndex;
        }
        return dbIndex;

    }

    public static long byteToLong(byte[] bytes) {
        String hexString = bytesToHexString(bytes);
        long unsignedInteger = Long.parseLong(hexString, 10);
        return unsignedInteger;
    }

    public static byte[] intToBytes(int value) {

        byte[] b = new byte[4];
        b[3] = (byte)(value & 0xff);
        b[2] = (byte)(value >> 8 & 0xff);
        b[1] = (byte)(value >> 16 & 0xff);
        b[0] = (byte)(value >> 24 & 0xff);
        return b;

    }

    /**
     * byte数组中取int数值
     *
     * @param src
     *            byte数组
     * @return int数值
     */
    public static int bytesToInt(byte[] b) {
        int res = 0;
        for (int i = 0; i < b.length; i++) {
            res += (b[i] & 0xff) << ((3 - i) * 8);
        }
        return res;
    }
}
