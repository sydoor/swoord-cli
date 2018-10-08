package com.lizikj.common.util;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMacSHA1加密算法工具类
 * @author lijundong 
 * @date 2017年7月26日 上午11:38:58
 */
public class HMacSHA1Utils {

	private static final String HMAC_SHA1 = "HmacSHA1";

	private static String byte2hex(byte[] b) {
		StringBuilder buf = new StringBuilder(32);
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				buf.append("0").append(stmp);
			else
				buf.append(stmp);
		}
		return buf.toString().toLowerCase();
	}

	/**
	 * 生成签名数据
	 * @param data 待加密的数据
	 * @param key 加密使用的key
	 * @return 加密后的字符串，纯小写
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSignature(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data);
		return byte2hex(rawHmac);
	}

	/**
	 * 加密
	 * @param signSource - 待加密的数据
	 * @param appSecret - 加密使用的key
	 * @return 正常情况下返回加密后的字符串，纯小写；如果出错，返回null
	 */
	public static String getSignature(String signSource, String appSecret) {
		String result = null;
		try {
			byte[] data = signSource.getBytes("UTF-8");
			byte[] keyData = appSecret.getBytes("UTF-8");
			result = getSignature(data, keyData);
		} catch (UnsupportedEncodingException e) {

		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string = "1340252002471";
		String key = "5vOJTtA1RtOdXmuNN39VD4EM6Aam982x";
		System.out.println(getSignature(string, key));
		System.out.println("3a8c2ee21d9de49e940146a5ded0d77ea8facf50".equalsIgnoreCase(getSignature(string, key)));
	}

}