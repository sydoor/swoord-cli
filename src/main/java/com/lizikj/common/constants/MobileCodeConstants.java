package com.lizikj.common.constants;

public class MobileCodeConstants {

	/**存放商户用户手机验证码的缓存key*/
	public static final String MERCHANT_MOBILE_CODE_CACHE_KEY = "MERCHANT_MOBILE_CODE_CACHE_KEY:";
	
	/**存放撩美味用户手机验证码的缓存key*/
	public static final String TENDER_MOBILE_CODE_CACHE_KEY = "TENDER_MOBILE_CODE_CACHE_KEY:";
	
	/**存放客户端用户手机验证码的缓存key*/
	public static final String CLIENT_MOBILE_CODE_CACHE_KEY = "CLIENT_MOBILE_CODE_CACHE_KEY:";
	
	public static String getMerchantMobileCodeCacheKey(String mobile){
		return MERCHANT_MOBILE_CODE_CACHE_KEY + mobile;
	}
	
	public static String getTenderMobileCodeCacheKey(String mobile){
		return TENDER_MOBILE_CODE_CACHE_KEY + mobile;
	}
	
	public static String getClientMobileCodeCacheKey(String mobile){
		return CLIENT_MOBILE_CODE_CACHE_KEY + mobile;
	}
}
