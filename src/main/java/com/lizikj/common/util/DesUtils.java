package com.lizikj.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class DesUtils {

	public static final String ALGORITHM = "DESede";

	public static byte[] des3Encryption(byte[] key, byte[] data) {
		SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
		Cipher c1 = null;
		byte[] buffer = null;
		try {
			c1 = Cipher.getInstance(ALGORITHM);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			buffer = c1.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public static String des3EncryptionString(byte[] key, byte[] data) {
		return StringUtils.newStringUtf8(Base64.encodeBase64(des3Encryption(key, data)));
	}
	
	public static String des3EncryptionString(String key, String data) {
		String encryption = null;
		try {
			byte[] encodeBase64 = Base64.encodeBase64(des3Encryption(key.getBytes(), data.getBytes()));
			encryption = StringUtils.newStringUtf8(encodeBase64);
		} catch (Exception e) {
		}
		return encryption;
	}
	
	public static byte[] des3Decryption(byte[] key, byte[] data) {
		SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
		Cipher c1 = null;
		byte[] buffer = null;
		try {
			c1 = Cipher.getInstance(ALGORITHM);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			buffer = c1.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
	public static String des3DecryptionString(byte[] key, byte[] data) {
		return StringUtils.newStringUtf8(Base64.encodeBase64(des3Decryption(key, data)));
	}
	
	public static String des3DecryptionString(String key, String data) {
		String decryption = null;
		try {
			byte[] des3Decryption = des3Decryption(key.getBytes(), Base64.decodeBase64(data));
			decryption = StringUtils.newStringUtf8(des3Decryption);
		} catch (Exception e) {
		}
		return decryption;
	}
}