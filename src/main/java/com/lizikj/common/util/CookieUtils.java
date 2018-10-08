package com.lizikj.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * cookie工具类
 * @author lijundong 
 * @date 2017年7月20日 下午4:27:16
 */
public class CookieUtils {
	
	public static final int TIME = 7 * 24 * 3600;
	
	/**
	 * 根据cookie的name获取具体的cookie
	 * @param request
	 * @param name
	 * @return Cookie
	 * @author lijundong
	 * @date 2017年7月20日 下午4:30:06
	 */
	public static Cookie getCookie(HttpServletRequest request, String name){
		if(StringUtils.isBlank(name))
			return null;
		
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals(name)){
					return cookie;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据name查找cookie，并给他设置为已过期
	 * @param request
	 * @param name
	 * @return Cookie
	 * @author lijundong
	 * @date 2017年7月20日 下午4:29:54
	 */
	public static void setCookieExpire(HttpServletRequest request, HttpServletResponse response, String name){
		Cookie cookie = getCookie(request, name);
		if(null != cookie){
			cookie.setPath("/");
			if(request.getRequestURL().toString().contains("lizikj.com")){
				cookie.setDomain("lizikj.com");
			}
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
	
	/**
	 * 新增cookie，如果已存在，新覆盖旧值
	 * @param request
	 * @param response
	 * @param name
	 * @param value void
	 * @author lijundong
	 * @date 2017年7月27日 下午2:51:22
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value){
		Cookie cookie = getCookie(request, name);
		if(null == cookie)
			cookie = new Cookie(name, value);
		else{
			cookie.setValue(value);
		}
		cookie.setPath("/");
		String requestURL = request.getRequestURL().toString();
		if(requestURL.contains("lizikj.com")){
			cookie.setDomain("lizikj.com");
		}
		cookie.setMaxAge(TIME);
		response.addCookie(cookie);
	}
}
