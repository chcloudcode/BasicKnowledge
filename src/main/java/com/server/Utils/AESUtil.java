package com.server.Utils;

import lombok.val;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtil {

    /**
     *  AES 加密
     * @param content  被加密内容
     * @param password  加密 密码 (明文）
     * @return
     */
    public static byte[] encrypt(String content,String password){
        try {
            //获取AES专用密钥
            SecretKeySpec key = getSecretKeySpec(password);
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  AES 解密
     * @param content  AES加密过得内容
     * @param password  加密 密码（明文）
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            //获取AES专用密钥
            SecretKeySpec key = getSecretKeySpec(password);
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result; // 明文
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SecretKeySpec getSecretKeySpec(String password) throws NoSuchAlgorithmException {
        // 创建AES的Key生产者
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(password.getBytes()));

        // 根据用户密码，生成一个密钥
        SecretKey secretKey = kgen.generateKey();
        // 获取基本编码格式的密钥
        byte[] enCodeFormat = secretKey.getEncoded();
        // 转换为AES专用密钥
        return new SecretKeySpec(enCodeFormat, "AES");
    }

    public static void main(String[] args) throws Exception {
        String content = "i'am eva";
        String password = "123456";
        System.out.println("需要加密的内容：" + content);
        byte[] encrypt = encrypt(content, password);
        System.out.println("加密后的2进制密文：" + new String(encrypt));
        String hexStr = ParseUtil.parseByteToHexStr(encrypt);
        System.out.println("加密后的16进制密文:" + hexStr);
        byte[] byte2 = ParseUtil.parseHexStrToByte(hexStr);
//        System.out.println("加密后的2进制密文：" + new String(byte2));
        byte[] decrypt = decrypt(byte2, password);
        System.out.println("解密后的内容：" + new String(decrypt,"utf-8"));
    }


}
