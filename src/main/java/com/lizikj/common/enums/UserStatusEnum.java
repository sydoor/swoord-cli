package com.lizikj.common.enums;

/**
 * 用户状态枚举
 * @author lijundong
 * @date 2017年8月24日 上午10:38:57
 */
public enum UserStatusEnum {

	/**
	 * 启用
	 */
	ENABLE((byte) 1, "启用"),

	/**
	 * 禁用
	 */
	DISABLE((byte) 0, "禁用"),;

	private byte status;

	private String message;

	private UserStatusEnum() {
	}

	private UserStatusEnum(byte status, String message) {
		this.status = status;
		this.message = message;
	}

	public byte getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 根据状态值获取用户具体的枚举
	 * @param status
	 * @return UserStatusEnum
	 * @author lijundong
	 * @date 2017年8月24日 上午11:27:09
	 */
	public static UserStatusEnum getEnum(byte status){
		for(UserStatusEnum userStatusEnum: values()){
			if(userStatusEnum.getStatus() == status){
				return userStatusEnum;
			}
		}
		return null;
	}
}
