package com.software.utils.RSA;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

/**
 * 签名工具类 chenh
 */
public class MySignature {

    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";


    /**
     * 根据提供的16进制字符串获取私钥PrivateKey
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 根据提供的16进制字符串获取公钥PublicKey
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA私钥签名
     * @param content
     * @param algorithm
     * @param privateKey
     * @return
     */
    public static String sign(byte[] content, String algorithm, PrivateKey privateKey){
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privateKey);
            signature.update(content);
            byte[] result = signature.sign();
            return Base64.getEncoder().encodeToString(result);
        }catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e){
            System.out.println("sign error"+e);
            return null;
        }
    }

    /**
     * RSA验签
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 分配给开发商公钥
     * @param encode    字符集编码
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign, String publicKey, String encode) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.getDecoder().decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(encode));
            boolean bverify = signature.verify(Base64.getDecoder().decode(sign));
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取请求流水号
     * @return
     */
    public static String getSerialNumber(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) throws Exception{
        Map<String,String> keyPair  =RSAEncrypt.genKeyPair();
        String privateKey = keyPair.get("privateKey");
        String publicKey =keyPair.get("publicKey");

        String content = "hello,world";
        System.out.println("-------------开始签名------------");
        String result = MySignature.sign(content.getBytes("utf-8"),SIGN_ALGORITHMS,MySignature.getPrivateKey(privateKey));
        System.out.println("-------------签名后信息："+result);

        System.out.println("-------------开始验签------------");
        System.out.println("-------------验签结果--------"+MySignature.doCheck(content,result,publicKey,"utf-8"));
    }

}
