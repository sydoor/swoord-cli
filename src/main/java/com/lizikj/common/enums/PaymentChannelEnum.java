package com.lizikj.common.enums;
/**
 * 支付渠道枚举
 * @author liaojw	2017/06/27
 *
 */
public enum PaymentChannelEnum {
	/**
	 * 1:钱宝
	 */
	PAY_CHANNEL_QIANBAO((byte)1,"钱宝"),
	/**
	 * 2:通联
	 */
	PAY_CHANNEL_TONGLIAN((byte)2,"通联"),
	/**
	 * 3:兴业
	 */
	PAY_CHANNEL_XINGYE((byte)3,"兴业"),
	/**
	 * 4:银盛
	 */
	PAY_CHANNEL_YINSHENG((byte)4,"银盛"),
	/**
	 * 5:群迈
	 */
	PAY_CHANNEL_QUNMAI((byte)5,"群迈"),
	/**
	 * 6:李子
	 */
	PAY_CHANNEL_LIZI((byte)6,"李子"),
	/**
	 * 7：汇宜
	 */
	PAY_CHANNEL_HUIYI((byte)7, "汇宜"),
	/**
	 * 8:中汇
	 */
	PAY_CHANNEL_ZHONGHUI((byte)8, "中汇"),
	/**
	 * 9:国通(星POS)
	 */
	PAY_CHANNEL_GUOTONG((byte)9, "国通(星POS)"),
	
	/**
	 * 10:合利宝
	 */
	PAY_CHANNEL_HELIBAO((byte)10, "合利宝"),
	
	/**
	 * 11:富友
	 */
	PAY_CHANNEL_FUIOU((byte)11, "富友"),
	/**
	 * 新大陆
	 */
	PAY_CHANNEL_XINDALU((byte)12, "新大陆")
	;
	
	PaymentChannelEnum(){
		
	}
	
	PaymentChannelEnum(byte code,String message){
		this.code = code;
		this.message = message;
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

	public static PaymentChannelEnum get(byte code){
		for(PaymentChannelEnum channelEnum: PaymentChannelEnum.values()){
			if(channelEnum.getCode() == code){
				return channelEnum;
			}
		}
		return null;
	}
}
