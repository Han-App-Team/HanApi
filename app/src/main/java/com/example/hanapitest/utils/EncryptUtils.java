package com.example.hanapitest.utils;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class EncryptUtils {

    /**
     * 注意这里要去掉  -----BEGIN PUBLIC KEY-----  和  -----END PUBLIC KEY-----
     */
    private static String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDaXkCnsOmmMI/Gnz33t4xROpSh\n" +
            "mdR5kjAXShoVZxk0gDPyvkLTVscMicROfzfqIL6G4tGRav+jL1sN4uxBLUvTOYs+\n" +
            "+y6gLyL8iDPTV/ble1ZXKFlnpyUisoia5wUjbeymz/DA9+rHtNCOmo0yydRCSd81\n" +
            "kXzCK3mJASyV7CsjoQIDAQAB";

    /**
     * 注意这里要去掉  -----BEGIN PUBLIC KEY-----  和  -----END PUBLIC KEY-----
     */
    private static String RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANpeQKew6aYwj8af\n" +
            "Pfe3jFE6lKGZ1HmSMBdKGhVnGTSAM/K+QtNWxwyJxE5/N+ogvobi0ZFq/6MvWw3i\n" +
            "7EEtS9M5iz77LqAvIvyIM9NX9uV7VlcoWWenJSKyiJrnBSNt7KbP8MD36se00I6a\n" +
            "jTLJ1EJJ3zWRfMIreYkBLJXsKyOhAgMBAAECgYBQdfI018b/6VzQvtRCdmtJcuBZ\n" +
            "OylDMk5uBmvd/wMWiTaa9ugAnzTwtcKTeeTMbAxH/0Ng5aAULNrMHxUWXu5U09P6\n" +
            "QLKa4e/f2KKANJwDe0WML5C/te39hrUtBF77+UZIalp20zhKdHuT6olsvLAVAF4R\n" +
            "OHhqHkY3qbVcZu0XAQJBAPF9Cu/TLm/SI+kI7hjuEJrU0afurroCt4GurJ2eZT/+\n" +
            "h21RZdbEq6gvAZ5lZ37XK4QAz3otMdUNch6JPjdoh4kCQQDnfYmGqY8g3GH3eP81\n" +
            "Jtp7Zq9NI53X6fDYMiLwAuAEGEFjL4Vju/58F7rNdAD/dNLAEbE0w9qPfvtHw3ad\n" +
            "zZ1ZAkEAvDbQOmFnFQI8uNkb8dRCntp2WaO/DP0nF20T3nlgwsLXvI0viXeZrqiM\n" +
            "Y273fjWoFecnzjrBVyN2qXQgSMEwuQJAB/KNaCwmF9C5JE/pIyAEXDw4RIO2hZUo\n" +
            "iaOfA3J10m4qwIb/I19u6z+9UBRtfFe8bI+u78rRlB+d7ObPCbfncQJBAJTQ0fY+\n" +
            "LvAdd2nfuMLkpPeg5Kk6KAiyArol8wc9rnOJgAaxcIykrzW+n6Ci2NFwUoRA74Cc\n" +
            "I4zlh1dPAVKQ++M=";


    /**
     * 大写字母MD5加密
     */
    public static String MD5(String s) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] strTemp = s.getBytes();
            //使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                //System.out.println((int)b);
                //将没个数(int)b进行双字节加密
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 小写字母MD5加密
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] b = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < b.length; i++) {
                int v = b[i];
                v = v < 0 ? 0x100 + v : v;
                String cc = Integer.toHexString(v);
                if (cc.length() == 1) {
                    sb.append('0');
                }
                sb.append(cc);
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 使用公钥进行RSA加密 再B64编码
     */
    public static String rsaEncrypt(String str) {
        try {
            //第一种获取PublicKey方式
            //InputStream inputStream = MyApplication.getInstance().getAssets().open("rsa_public_key.pem");
            //PublicKey publicKey1 = getPublicKeyFromIo(inputStream);
            //第二种获取PublicKey方式
            PublicKey publicKey2 = getPublicKeyFromStr(RSA_PUBLIC_KEY);

            //公钥加密字符串
            byte[] bytesMessageRsa = encryptData(str.getBytes(), publicKey2);

            //将加密后的字节码进行base64编码,变成可见字符  设置 Base64.NO_WRAP  略去所有的换行符
            //不进行B64编码会导致  Caused by: java.lang.ArrayIndexOutOfBoundsException: too much data for RSA block
            byte[] bytesB64Encode = Base64.encode(bytesMessageRsa, Base64.NO_WRAP);

            String strB64Encode = new String(bytesB64Encode);
            return strB64Encode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 使用私钥解密rsa公钥加密过的字符串
     */
    public static String rsaDecode(String rsaStr) {
        try {
            //第一种获取PrivateKey方式
//            InputStream inputStream = MyApplication.getInstance().getAssets().open("cspadmin.pem");
//            PrivateKey privateKey1 = getPrivateKeyFromIo(inputStream);
            //第二种获取PrivateKey方式
            PrivateKey privateKey2 = getPrivateKeyFromStr(RSA_PRIVATE_KEY);

            //先Base64解码，再给RSA解密
            byte[] bytesB64Decode = Base64.decode(rsaStr, Base64.NO_WRAP);

            byte[] bytesMessageRsa = decryptByPrivateKey(bytesB64Decode, privateKey2);

            String strB64Decode = "";
            if (bytesMessageRsa != null) {
                strB64Decode = new String(bytesMessageRsa);
            }
            return strB64Decode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 使用私钥解密
     */
    public static byte[] decryptByPrivateKey(byte[] encrypted, PrivateKey privateKey) {
        try {
            //android的rsa加密方式是RSA/ECB/NoPadding，而标准jdk是RSA/ECB/PKCS1Padding，所以加密时要设置标准jdk的加密方式
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用公钥加密
     * 每次加密的字节数，不能超过密钥的长度值减去11
     */
    public static byte[] encryptData(byte[] data, PublicKey publicKey) {
        try {
            //android的rsa加密方式是RSA/ECB/NoPadding，而标准jdk是RSA/ECB/PKCS1Padding，所以加密时要设置标准jdk的加密方式
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            //编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //传入编码数据并返回编码结果
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据公钥字符串获取公钥对象
     */
    public static PublicKey getPublicKeyFromStr(String publicKeyStr) throws Exception {
        try {
            //勿使用Base64.DEFAULT  当字符串过长（一般超过76）时会自动在中间加一个换行符，字符串最后也会加一个换行符，
            //这样就导致和其他模块对接时结果不一致    使用Base64.NO_WRAP略去所有换行符
            byte[] buffer = Base64.decode(publicKeyStr, Base64.NO_WRAP);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从字符串中获取私钥对象
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     */
    public static PrivateKey getPrivateKeyFromStr(String privateKeyStr) throws Exception {
        try {
            //勿使用Base64.DEFAULT  当字符串过长（一般超过76）时会自动在中间加一个换行符，字符串最后也会加一个换行符，
            //这样就导致和其他模块对接时结果不一致    使用Base64.NO_WRAP略去所有换行符
            byte[] buffer = Base64.decode(privateKeyStr, Base64.NO_WRAP);
            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 从文件输入流中加载公钥
     */
    public static PublicKey getPublicKeyFromIo(InputStream in) throws Exception {
        try {
            return getPublicKeyFromStr(readKey(in));
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }

    /**
     * 从文件输入流中加载私钥
     */
    public static PrivateKey getPrivateKeyFromIo(InputStream in) throws Exception {
        try {
            return getPrivateKeyFromStr(readKey(in));
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    /**
     * 读取密钥信息
     */
    private static String readKey(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            if (readLine.charAt(0) == '-') {
            } else {
                sb.append(readLine);
                sb.append('\r');
            }
        }
        return sb.toString();
    }


}
