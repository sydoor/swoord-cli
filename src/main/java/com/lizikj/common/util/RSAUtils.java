package com.lizikj.common.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;


/**
 * RSA签名工具类
 * @author lijundong 
 * @date 2017年6月28日 下午5:08:09
 */
public class RSAUtils {

	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 根据字符串和私钥进行加密
	 * @param content
	 * @param privateKey
	 * @return String
	 * @author lijundong
	 * @date 2017年6月28日 下午5:26:29
	 */
	public static String encode(String content, String privateKey) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			byte[] sign = signature.sign();

			return Base64.encodeBase64String(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 此方法与上一方法结果不一致，两个都试一下
	 * @param content
	 * @param privateKey
	 * @param charset
	 * @return
	 */
	public static String encode2(String content, String privateKey, String charset) {
		try {
			// 解密由base64编码的私钥
			byte[] bytesKey = Base64.decodeBase64(privateKey);
			// 构造PKCS8EncodedKeySpec对象
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(bytesKey);
			// KEY_ALGORITHM 指定的加密算法
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			// 取私钥匙对象
			PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// 用私钥对信息生成数字签名
			java.security.Signature signature = java.security.Signature.getInstance("MD5WithRSA");
			signature.initSign(priKey);
			signature.update(content.getBytes(charset));
			return Base64.encodeBase64String(signature.sign());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据字符串、证书、证书密码进行加密
	 * @param content
	 * @param certPath
	 * @param password
	 * @return String
	 * @author lijundong
	 * @date 2017年6月28日 下午5:26:50
	 */
	public static String encode(String content, String certPath, String password) {
		try {
			//根据证书和密码获取私钥
			PrivateKey privateKey = CertUtils.getPrivateKey(certPath, password);
			
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			
			signature.initSign(privateKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));
			
			byte[] sign = signature.sign();
			
			return Base64.encodeBase64String(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * RSA验签名检查(根据公钥)
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @return boolean
	 * @author lijundong
	 * @date 2017年7月4日 下午5:08:37
	 */
	public static boolean verify(String content, String sign, String publicKey) {
		try {
			X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PublicKey pubKey = keyFactory.generatePublic(encodedKeySpec);

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));
			return signature.verify(Base64.decodeBase64(sign));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * RSA验签名检查(根据证书和证书密码)
	 * @param content
	 * @param sign
	 * @param certPath
	 * @param password
	 * @return boolean
	 * @author lijundong
	 * @date 2017年7月4日 下午5:21:34
	 */
	public static boolean verify(String content, String sign, String certPath, String password) {
		try {
			PublicKey pubKey = CertUtils.getPublicKey(certPath, password);

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));
			return signature.verify(Base64.decodeBase64(sign));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * 私钥解密
	 * 
	 * @param encryptedData 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		PrivateKey key = getPrivateKey(privateKey);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		Base64 base64 = new Base64();
		byte[] b1 = base64.decode(encryptedData);
		byte[] b = cipher.doFinal(b1);
		return b;
	}

	/**
	 * 公钥加密
	 * 
	 * @param data 源数据
	 * @param publicKey 公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 私钥签名
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] signPrivate(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		PrivateKey priKey = keyf.generatePrivate(priPKCS8);

		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

		signature.initSign(priKey);
		signature.update(data);

		byte[] signed = signature.sign();

		return signed;
	}

	/**
	 * 公钥验签
	 * @param data 待签名数据
	 * @param publicKey 公钥
	 * @param sign 签名值
	 * @return
	 * @throws Exception
	 */
	public static boolean verifyPublic(byte[] data, String publicKey, String sign) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));

		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

		signature.initVerify(pubKey);
		signature.update(data);

		boolean bverify = signature.verify(Base64.decodeBase64(sign));
		return bverify;
	}

	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}   
    
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
}
