package com.lizikj.common.enums;

/**
 * 用户类型枚举类
 * 
 * @author lijundong
 * @date 2017年7月12日 下午3:44:25
 */
public enum UserTypeEnum {
	AGENT_USER((byte) 1, "agent", "代理商系统用户"),

	MERCHANT_USER((byte) 2, "mch", "商户系统用户"),

	OPT_USER((byte) 3, "opt", "运营系统用户"),
	
	CLIENT_USER((byte) 4, "client", "h5用户"),
	
	TENDER_USER((byte) 5, "tender", "撩美味用户"),
	
	SMALL_CLIENT_USER((byte)6, "smallClient", "小程序"),
	;

	private byte type;

	private String code;

	private String message;

	private UserTypeEnum(byte type, String code, String message) {
		this.type = type;
		this.code = code;
		this.message = message;
	}

	public byte getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	/***
	 * 根据值查找对应的枚举
	 * @param type
	 * @return UserTypeEnum
	 * @author lijundong
	 * @date 2017年7月12日 下午3:44:13
	 */
	public static UserTypeEnum getEnum(Byte type) {
		if(null == type)
			return null;
		
		for (UserTypeEnum typeEnum : values()) {
			if (typeEnum.getType() == type.byteValue())
				return typeEnum;
		}
		return null;
	}
	
	/***
	 * 根据code查找对应的枚举
	 * @param type
	 * @return UserTypeEnum
	 * @author lijundong
	 * @date 2017年7月12日 下午3:44:13
	 */
	public static UserTypeEnum getEnum(String code) {
		if(null == code)
			return null;
		
		for (UserTypeEnum typeEnum : values()) {
			if (typeEnum.getCode().equals(code))
				return typeEnum;
		}
		return null;
	} 
}
