package com.lizikj.common.enums;

/**
 * 支付场景枚举 
 * @author liaojw 2017/06/27
 *
 */
public enum PaymentSceneEnum {

	/**
	 * 未知统一传这里
	 */
	DEFAULT((byte)0, "默认的"),
	/**
	 * 收银台付款码
	 */
	DESK_PAY_CODE((byte)1,"收银台付款码"),
	/**
	 * 店铺付款码
	 */
	SHOP_PAY_CODE((byte)2,"店铺收款码"),
	/**
	 * 订单小票支付二维码
	 */
	ORDER_TICKET_PAY_CODE((byte)3,"订单小票支付二维码"),
	/**
	 * pos扫用户
	 */
	POS_SCAN((byte)4,"pos扫用户"),
	/**
	 * h5h点餐在线支付
	 */
	H5_ONLINE_PAY((byte)5,"h5点餐在线支付"),
//	/**
//	 * 公众号点餐在线支付
//	 */
//	PUBLIC_NUMBER_ONLINE_PAY((byte)6,"公众号点餐在线支付"),
	/**
	 * 刷卡
	 */
	SWIPECARD_CARD((byte)7,"刷卡"),
	/**
	 * APP支付
	 */
	APP_PAY((byte)8,"app支付"),
	/**
	 * 云闪付
	 */
	PAYMENT_TYPE_CLOUD((byte)9, "云闪付"),
	/**
	 * 现金
	 */
	PAYMENT_TYPE_CASHE((byte)10, "现金"),
	/**
	 * 小程序
	 */
	PAYMENT_TYPE_SC((byte) 11, "小程序")
	;
	
	PaymentSceneEnum(){
		
	}
	
	PaymentSceneEnum(byte code,String messsage){
		this.code = code;
		this.message = messsage;
	}
	
	public byte code;
	public String message;
	
	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 根据code拿到具体的枚举值
	 * @param code
	 * @return PaymentSceneEnum
	 * @author lijundong
	 * @date 2017年6月27日 下午3:46:29
	 */
	public static PaymentSceneEnum get(byte code){
		for(PaymentSceneEnum sceneEnum: PaymentSceneEnum.values()){
			if(sceneEnum.getCode() == code){
				return sceneEnum;
			}
		}
		return null;
	}
}
