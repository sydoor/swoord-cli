package com.lizikj.common.enums;


/**
 * 消息中心-业务类型枚举
 * @author liaojw 
 * @date 2017年8月30日 上午9:40:43
 */
public enum MessageBizTypeEnum {
	//-------------------------------------------
	//  美食系统
	//-------------------------------------------
	/**
	 * 美食下架消息
	 */
	GOODS_OFF_SHELF(MessageSysModuleEnum.MERCHANDISE_SYS,  101, "美食售罄消息"),

	//-------------------------------------------
	//  会员系统
	//-------------------------------------------
	MEMBER_DISCOUNT_SETTING_CHANGE(MessageSysModuleEnum.MEMBER_SYS,301,"商户会员优惠方式变更"),

	//-------------------------------------------
	//  店铺系统
	//-------------------------------------------

	/**
	 * H5呼叫服务员
	 */
	CALL_WAITER(MessageSysModuleEnum.SHOP_SYS,  401, "H5呼叫服务员"),




	//-------------------------------------------
	//  订单系统
	//-------------------------------------------
	/**
	 * H5自助结账
	 */
	H5_SELF_CHECKOUT(MessageSysModuleEnum.ORDER_SYS, 601, "H5自助结账"),
	/**
	 * 店铺收款码收款信息
	 */
	GATHERING_CODE(MessageSysModuleEnum.ORDER_SYS,  602, "店铺收款码收款信息"),
	/**
	 * H5下单提醒
	 */
	H5_ADD_ORDER(MessageSysModuleEnum.ORDER_SYS,  603, "H5下单提醒"),
	/**
	 * 订单状态变更
	 */
	ORDER_STATUS_CHANGE(MessageSysModuleEnum.ORDER_SYS,  604, "订单状态变更"),
	/**
	 * H5 加菜
	 */
	H5_ADD_MERCHANDISE(MessageSysModuleEnum.ORDER_SYS,  605, "H5加菜"),

	SHOP_ORDER_UNDER_RECEIVE(MessageSysModuleEnum.ORDER_SYS,606,"店铺有待接单的订单"),


	//-------------------------------------------
	//  运营系统
	//-------------------------------------------

	/**
	 * 业务类型-pos版本更新推送通知
	 */
	POS_VERSION_UPDATE_NOTIFY(MessageSysModuleEnum.OPT_SYS,  1001, "pos版本更新通知"),


	//-------------------------------------------
	//  数据操作日志系统
	//-------------------------------------------

	/**
	 * 数据同步消息
	 */
	MSG_SYNCHRONY_NOTIFY(MessageSysModuleEnum.DATA_LOG_SYS,  1101, "数据同步消息"),


	//-------------------------------------------
	//  登录系统
	//-------------------------------------------
	/**
	 * pos登陆失效通知
	 */
	POS_LOGIN_FAILED_NOTIFY(MessageSysModuleEnum.LOGIN_MODULE,  1201, "pos登陆失效通知 "),
	/**
	 * 异常登录消息
	 */
	LOGIN_ABNORMAL(MessageSysModuleEnum.LOGIN_MODULE, 1202, "异常登录消息"),


	//-------------------------------------------
	//  外卖系统
	//-------------------------------------------
	CATER_ORDER_STATUS_MODIFY(MessageSysModuleEnum.CATER_SYS, 1301, "外卖订单状态变更"),
	CATER_UNBIND(MessageSysModuleEnum.CATER_SYS, 1302, "外卖平台解绑"),
	;
	
	private MessageSysModuleEnum messageSysModule;
	private Integer code;
	private String message;
	
	MessageBizTypeEnum() {}
	MessageBizTypeEnum(MessageSysModuleEnum messageSysModule, Integer code, String message) {
		this.messageSysModule = messageSysModule;
		this.code = code;
		this.message = message;
	}
	
	public MessageSysModuleEnum getMessageSysModule() {
		return messageSysModule;
	}
	public void setMessageSysModule(MessageSysModuleEnum messageSysModule) {
		this.messageSysModule = messageSysModule;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public static String getMessage(Byte code) {
		for (MessageBizTypeEnum e : MessageBizTypeEnum.values()) {
			if (e.getCode().equals(code)) {
				return e.getMessage();
			}
		}
		return "";
	}

	public static MessageBizTypeEnum fromCode(Integer code){
		if(code == null){
			return null;
		}

		for(MessageBizTypeEnum bizTypeEnum:values()){
			if(bizTypeEnum.getCode().equals(code)){
				return bizTypeEnum;
			}
		}

		return null;
	}
}
