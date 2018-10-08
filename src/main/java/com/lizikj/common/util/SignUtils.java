package com.lizikj.common.util;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * 签名辅助工具类
 * 
 * @author lijundong
 * @date 2017年6月16日 下午3:07:19
 */
public class SignUtils {
	public static final String SN = "agfdfsRR$%%FGS#T^REWTBFGFDD()%$RF<>JGHNVGDER^%$$TYHF#KFGHKFXWU_)(*&^%QADFGHKHYTFVM,uGFFytr";
	
	/**
	 * 拼接签名源
	 */
	public static String signSource(Map<String, ? extends Object> params) {
		if(null == params || params.isEmpty())
			return null;
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		map.putAll(params);
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Object> entry: map.entrySet()){
			if(entry.getValue() != null && StringUtils.isNotBlank(entry.getValue().toString())){
				//字符串直接拼接
				if(entry.getValue() instanceof String){
					sb.append(entry.getKey() + "=" + entry.getValue() +"&");
				}
				//对象类型，转为json字符串
				else{
					sb.append(entry.getKey() + "=" + JsonUtils.toJSONString(entry.getValue()) +"&");
				}
			}
		}
		//去掉尾部&
		return sb.substring(0, sb.length() - 1);
	}
	
	/**
	 * 生成密码
	 * @param mobile
	 * @return String
	 * @author liaojw
	 * @date 2017年7月6日 下午7:40:29
	 */
	public static String generatePassword(String mobile){
		if (mobile.length()>=6) {
			mobile = mobile.substring((mobile.length()-6), mobile.length());
		}
		return MD5Utils.pwdMd5(mobile, SN);
	}
	
	/**
	 * 密码明文转密文
	 * @param password
	 * @return String
	 * @author lijundong
	 * @date 2017年8月15日 下午4:22:49
	 */
	public static String md5Password(String password){
		return MD5Utils.md5(password, SN);
	}

	/**
	 * 通过证书文件获取签名
	 * @param toSign 待签名字符串
	 * @param pfx_pwd 证书密码
	 * @param pfxPath 证书路径
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 */
	public static String getSign(String toSign, String pfx_pwd, String pfxPath) throws NoSuchAlgorithmException, SignatureException, UnsupportedEncodingException, InvalidKeyException {
		PrivateKey privateKey = null;

		String path = pfxPath;

		privateKey = getPvkformPfx(path, pfx_pwd);
		java.security.Signature signet = java.security.Signature.getInstance("SHA1WithRSA");
		signet.initSign(privateKey);
		signet.update(toSign.getBytes("utf-8"));
		byte[] signed = signet.sign();
		String sign = new String(Base64.encodeBase64(signed), "utf-8");
		return sign;
	}

	/**
	 * 通过密码和证书文件获取私钥
	 * @param strPfx 证书文件路径
	 * @param strPassword 证书密码
	 * @return
	 */
	private static PrivateKey getPvkformPfx(String strPfx, String strPassword) {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(strPfx);
			char[] nPassword = null;
			if ((strPassword == null) || strPassword.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = strPassword.toCharArray();
			}
			ks.load(fis, nPassword);
			fis.close();
			Enumeration enumas = ks.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements())// we are readin just one certificate.
			{
				keyAlias = (String) enumas.nextElement();
			}
			// Now once we know the alias, we could get the keys.
			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			Certificate cert = ks.getCertificate(keyAlias);
			PublicKey pubkey = cert.getPublicKey();
			return prikey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(generatePassword("13711123456"));
	}
}
