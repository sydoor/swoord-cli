package com.lizikj.common.constants;

/**
 * 用户缓存常量类
 * @author lijundong 
 * @date 2017年7月13日 下午8:47:02
 */
public class UserCacheConstants {
	
	/**
	 * 登录用户缓存key
	 */
	public static final String LIZI_LOGIN_USER = "cache:login:user";
	
	/**
	 * 根据用户类型和用户id，封装缓存key
	 * @param userId
	 * @param userType
	 * @return String
	 * @author lijundong
	 * @date 2017年7月27日 下午2:39:02
	 */
	public static String getUserCacheKey(long userId, byte userType){
		return LIZI_LOGIN_USER + ":" + userId + ":" + userType;
	}
	
}
