package com.lizikj.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES加解密工具类
 * @auth zone
 * @date 2018-03-30
 */
public class AesUtils {
	/**
	 * 解密
	 * @param srcString
	 * @param encodingFormat
	 * @param aesKey
	 * @param aesIV
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String srcString, String encodingFormat, byte[] aesKey, byte[] aesIV) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(aesKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivParameter = new IvParameterSpec(aesIV);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameter);
		byte[] encrypted1 = Base64.decodeBase64(srcString);
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original, encodingFormat);
		return originalString;
	}
}
