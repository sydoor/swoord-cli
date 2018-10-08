package com.lizikj.common.enums;

/**
 * virtual枚举
 * @author zhoufe
 * @date 2018年01月08日 上午10:38:57
 */
public enum VirtualEnum {

	/**
	 * 否
	 */
	NO((byte) 0, "否"),

	/**
	 * 是
	 */
	YES((byte) 1, "是"),;

	private Byte status;

	private String message;

	private VirtualEnum() {
	}

	private VirtualEnum(Byte status, String message) {
		this.status = status;
		this.message = message;
	}

	public Byte getStatus() {
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
	public static VirtualEnum getEnum(Byte status){
		for(VirtualEnum userStatusEnum: values()){
			if(userStatusEnum.getStatus().equals(status)){
				return userStatusEnum;
			}
		}
		return null;
	}
}
