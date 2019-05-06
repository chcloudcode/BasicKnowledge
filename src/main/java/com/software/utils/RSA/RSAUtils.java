package com.software.utils.RSA;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

/**
 * RSA 加密解密工具
 */
public class RSAUtils {

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, PublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
       byte[] result = cipher.doFinal(data.getBytes("utf-8"));
        return result;
    }

    /**
     *  私钥解密
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] privateDecrypt(byte[] data, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(data);
        return result;
    }

    public static void main(String[] args) throws Exception{
        Map<String,String> keyPair  =RSAEncrypt.genKeyPair();
        String privateKey = keyPair.get("privateKey");
        String publicKey =keyPair.get("publicKey");

        String data = "hello,world";

        System.out.println("--------     原始字符串16进制表示----"+Base64.getEncoder().encodeToString(data.getBytes("utf-8")));


        byte[] encodeResult = RSAUtils.encryptByPublicKey(data,MySignature.getPublicKey(publicKey));
        System.out.println("-----------加密数据后-------"+Base64.getEncoder().encodeToString(encodeResult));

        byte[] decodeResult = RSAUtils.privateDecrypt(encodeResult,MySignature.getPrivateKey(privateKey));
        System.out.println("-----------解密之后----------"+Base64.getEncoder().encodeToString(decodeResult));

        System.out.println(new String(Base64.getDecoder().decode(Base64.getEncoder().encodeToString(decodeResult))));

    }

}
