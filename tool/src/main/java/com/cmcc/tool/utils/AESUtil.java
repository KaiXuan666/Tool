package com.cmcc.tool.utils;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES Coder<br/>
 * secret key length:	128bit, default:	128 bit<br/>
 * mode:	ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128<br/>
 * padding:	Nopadding/PKCS5Padding/ISO10126Padding/
 * @author Aub
 *
 */
public class AESUtil {


    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 初始化密钥
     *
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initSecretKey() {
        //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        //初始化此密钥生成器，使其具有确定的密钥大小
        //AES 要求密钥长度为 128
        kg.init(128);
        //生成一个密钥
        SecretKey  secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 转换密钥
     *
     * @param key	二进制密钥
     * @return 密钥
     */
    private static Key toKey(byte[] key){
        //生成密钥
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data	待加密数据
     * @param key	密钥
     * @return byte[]	加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,Key key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data	待加密数据
     * @param key	二进制密钥
     * @return byte[]	加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }


    /**
     * 加密
     *
     * @param data	待加密数据
     * @param key	二进制密钥
     * @param cipherAlgorithm	加密算法/工作模式/填充方式
     * @return byte[]	加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    /**
     * 加密
     *
     * @param data	待加密数据
     * @param key	密钥
     * @param cipherAlgorithm	加密算法/工作模式/填充方式
     * @return byte[]	加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }



    /**
     * 解密
     *
     * @param data	待解密数据
     * @param key	二进制密钥
     * @return byte[]	解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     *
     * @param data	待解密数据
     * @param key	密钥
     * @return byte[]	解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,Key key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     *
     * @param data	待解密数据
     * @param key	二进制密钥
     * @param cipherAlgorithm	加密算法/工作模式/填充方式
     * @return byte[]	解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    /**
     * 解密
     *
     * @param data	待解密数据
     * @param key	密钥
     * @param cipherAlgorithm	加密算法/工作模式/填充方式
     * @return byte[]	解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }

    public static  String  encrypt(byte[] data, String key) throws Exception {
        byte [] bKey = key.getBytes();
        return printHexString(encrypt(data, bKey, DEFAULT_CIPHER_ALGORITHM));
    }
    
    
    public static String printHexString( byte[] b) {
      String a = "";
        for (int i = 0; i < b.length; i++) { 
          String hex = Integer.toHexString(b[i] & 0xFF); 
          if (hex.length() == 1) { 
            hex = '0' + hex; 
          }
         
          a = a+hex;
        }
        return a;
      }
    
    public static byte[] hexStringToBytes(String hexString) {
      if (hexString == null || hexString.equals("")) {
          return null;
      }
      hexString = hexString.toUpperCase();
      int length = hexString.length() / 2;
      char[] hexChars = hexString.toCharArray();
      byte[] d = new byte[length];
      for (int i = 0; i < length; i++) {
          int pos = i * 2;
          d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
          
      }
      return d;
  }
  private static byte charToByte(char c) {
      return (byte) "0123456789ABCDEF".indexOf(c);
  }

    
    
    public static String  decrypt(String data, String key) throws Exception {
        byte [] bKey = key.getBytes();
        byte []dBytes = decrypt(hexStringToBytes(data), bKey, DEFAULT_CIPHER_ALGORITHM);
        return new String(dBytes);
    }



//    public static void main(String[] args) throws Exception {
////        byte[] key = initSecretKey();
//        String key_str = "adshjkhajshfjksd";
//
//        byte [] key = key_str.getBytes();
////        System.out.println("key："+showByteArray(key));
//
//        Key k = toKey(key);
//
//        String data ="AES数据";
//        System.out.println("加密前数据: string:"+data);
//        System.out.println();
//        byte[] encryptData = encrypt(data.getBytes(), k);
//        System.out.println("加密后数据: hexStr:"+ printHexString(encryptData));
//        System.out.println();
//        byte[] decryptData = decrypt(encryptData, k);
//        System.out.println("解密后数据: string:"+new String(decryptData));

//    }
}