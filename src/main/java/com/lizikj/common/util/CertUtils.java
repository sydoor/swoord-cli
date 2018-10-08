package com.lizikj.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Enumeration;

import org.apache.commons.lang.StringUtils;

/**
 * 证书辅助工具类
 * @author lijundong 
 * @date 2017年6月28日 下午5:14:41
 */
public class CertUtils {
	
	/**
	 * 根据证书路径和密码获取私钥
	 * @param certPath 证书路径
	 * @param password 证书密码
	 * @return PrivateKey
	 * @author lijundong
	 * @date 2017年6月28日 下午5:14:54
	 */
	public static PrivateKey getPrivateKey(String certPath, String password) {
		try {
			//获取证书
			KeyStore keyStore = getKeyStore(certPath, password);
			
			Enumeration<String> aliases = keyStore.aliases();
			String keyAlias = null;
			if (aliases.hasMoreElements())
				keyAlias = aliases.nextElement();
			
			//密码转为字节数组
			char[] bytes = StringUtils.isBlank(password) ? null : password.toCharArray();
			
			PrivateKey prikey = (PrivateKey) keyStore.getKey(keyAlias, bytes);
			return prikey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据证书路径和密码获取公钥
	 * @param certPath 证书路径
	 * @param password 证书密码
	 * @return PublicKey
	 * @author lijundong
	 * @date 2017年6月28日 下午5:14:54
	 */
	public static PublicKey getPublicKey(String certPath, String password) {
		try {
			//获取证书
			KeyStore keyStore = getKeyStore(certPath, password);
			
			Enumeration<String> aliases = keyStore.aliases();
			String keyAlias = null;
			if (aliases.hasMoreElements())
				keyAlias = aliases.nextElement();
			
			//获取公钥
			Certificate cert = keyStore.getCertificate(keyAlias);
			PublicKey pubkey = cert.getPublicKey();
			return pubkey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取证书工具类
	 * @param certPath
	 * @param password
	 * @return KeyStore
	 * @author lijundong
	 * @date 2017年6月28日 下午6:05:06
	 */
	private static KeyStore getKeyStore(String certPath, String password){
		KeyStore keyStore = null;
		try {
			keyStore = KeyStore.getInstance("PKCS12");
			InputStream inputStream = null;
			//先拿绝对路径的
			try {
				inputStream = new FileInputStream(new File(certPath));
			} catch (Exception e) {
				//如果为null，拿相对路径的
				if(inputStream == null)
					inputStream = CertUtils.class.getResourceAsStream(certPath);
			}
			keyStore.load(inputStream, (password == null ? "" : password).toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyStore;
	}
	
	public static void main(String[] args) {
		PrivateKey privateKey = getPrivateKey("C:\\Users\\ASUS\\Desktop\\yinsheng.pfx", "952706");
	}
}
