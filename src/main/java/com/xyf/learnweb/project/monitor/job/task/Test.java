package com.xyf.learnweb.project.monitor.job.task;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;


public class Test {
    public static void main(String[] args) throws Exception {
        // 文件路径具体到文件名字
        File fileOne = new File("D:\\pubkey\\APP_PUBLICKEY\\full_20200210.bin");
        byte[] bytesArray = new byte[(int)fileOne.length()];
        System.out.println("length:" + fileOne.length());
        FileInputStream fis = new FileInputStream(fileOne);
        // 读取文件到byte[]
        fis.read(bytesArray);
        fis.close();
        // 长度
        byte[] lengthByte = new byte[4];
        System.arraycopy(bytesArray, 0, lengthByte, 0, 4);
        System.out.println("length:" + bytesToInt(lengthByte));
        // 公钥字节长度
        int pubkeyLength = bytesArray.length - 1568;
        byte[] pubkeyByteList = new byte[pubkeyLength];
        System.arraycopy(bytesArray, 4, pubkeyByteList, 0, pubkeyLength);

        int number = pubkeyLength / 128;
        for (int i = 0; i < number; i++) {
            //值
            byte[] idByte = new byte[64];
            System.arraycopy(pubkeyByteList, i * 128 , idByte, 0, idByte.length);
            System.out.println(new String(idByte));
            //公钥值
            byte[] pubkeyByte = new byte[64];
            System.arraycopy(pubkeyByteList, i * 128+ 64, pubkeyByte, 0, pubkeyByte.length);
            System.out.println("pubkey:" + bytesToHexString(pubkeyByte));
 
        }
        // 证书值
        byte[] certByte = new byte[1500];
        System.arraycopy(bytesArray, pubkeyLength+lengthByte.length, certByte, 0, certByte.length);
        System.out.println("cert:" + new String(certByte));
        // 签名值
        byte[] signByte = new byte[64];
        System.arraycopy(bytesArray, pubkeyLength + lengthByte.length + certByte.length, signByte, 0, signByte.length);
        System.out.println("sign:"+encodeBytes(signByte));
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
    public static String encodeBytes(byte[] source) {
        return new String(Base64.encodeBase64(source));
    }

}
