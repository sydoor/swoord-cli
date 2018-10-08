package com.lizikj.common.enums;

/**
 * 用户登陆来源枚举类
 * @author lijundong 
 * @date 2017年7月13日 上午11:17:18
 */
public enum UserLoginSourceEnum {
	PC((byte)0, "PC端"),	

	APP((byte)1, "APP端"),

	POS((byte)2, "POS端"),
	
	H5((byte)3, "h5端"),
	
	SC((byte)4, "小程序")
	;

	private Byte value;

	private String message;

	private UserLoginSourceEnum(Byte value, String message) {
		this.value = value;
		this.message = message;
	}

	public Byte getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 根据值获取具体的枚举 
	 * @param value
	 * @return MerchantLoginSourceEnum
	 * @author lijundong
	 * @date 2017年7月13日 上午11:18:21
	 */
	public static UserLoginSourceEnum getEnum(Byte value){
		for(UserLoginSourceEnum sourceEnum: values()){
			if(sourceEnum.getValue().equals(value))
				return sourceEnum;
		}
		return null;
	}
}
