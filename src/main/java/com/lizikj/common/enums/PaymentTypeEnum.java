package com.lizikj.common.enums;

/**
 * 支付方式枚举
 *
 * @author liaojw    2017/6/27
 */
public enum PaymentTypeEnum {
    /**
     * 1:支付宝
     */
    PAYMENT_TYPE_ALIPAY((byte) 1, "支付宝"),
    /**
     * 2:微信
     */
    PAYMENT_TYPE_WECHAT((byte) 2, "微信"),
    /**
     * 3:银联钱包
     */
    PAYMENT_TYPE_YINLIANQIANBAO((byte) 3, "银联钱包"),
    /**
     * 4:银行卡支付 (刷卡已改名‘银行卡支付’)
     */
    PAYMENT_TYPE_SWIPECARD((byte) 4, "银行卡支付"),
    /**
     * 5:现金
     */
    PAYMENT_TYPE_CASH((byte) 5, "现金"),

//    /**
//     * 6:云闪付
//     */
//    PAYMENT_TYPE_CLOUD((byte) 6, "云闪付"),

//	/**
//	 * 7:付款码
//	 */
//	PAYMENT_TYPE_QRCODE((byte)7,"付款码"),
    /**
     * 8:会员
     */
    PAYMENT_TYPE_MEMBER((byte) 8, "会员余额"),
    /**
     * 9:优惠券
     */
    PAYMENT_TYPE_COUPON((byte) 9, "店铺卡券"),

    /**
     * 10:美团券
     */
    PAYMENT_TYPE_MEI_TUAN_COUPON((byte) 10, "美团代金券"),
//    /**
//     * 11:APP支付
//     */
//    PAYMENT_TYPE_APP_PAY((byte)11, "APP支付"),
    /**
     * 12：预付券
     */
    PAYMENT_TYPE_REPLY_COUPON((byte) 12, "预付券"),

    /**
     * 13:会员股东
     */
    PAYMENT_TYPE_PARTNER_MEMBER((byte) 13, "会员股东"),

    ;

    public byte code;
    public String message;

    PaymentTypeEnum() {

    }

    PaymentTypeEnum(byte code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code拿到具体的枚举值
     *
     * @param code
     * @return PaymentTypeEnum
     * @author lijundong
     * @date 2017年6月27日 下午3:46:29
     */
    public static PaymentTypeEnum get(byte code) {
        for (PaymentTypeEnum typeEnum : PaymentTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum;
            }
        }
        return null;
    }


    /**
     * 在线支付和现金支付是互斥支付，不能同时一起支付
     *
     * @param paymentType
     * @return
     */
    public static Boolean isMutex(PaymentTypeEnum paymentType) {
        if (PAYMENT_TYPE_ALIPAY.equals(paymentType) ||
                PAYMENT_TYPE_WECHAT.equals(paymentType) ||
                PAYMENT_TYPE_YINLIANQIANBAO.equals(paymentType) ||
                PAYMENT_TYPE_SWIPECARD.equals(paymentType) ||
                PAYMENT_TYPE_CASH.equals(paymentType)
                ) {
            return true;
        }

        return false;
    }

    /**
     * 是否是在线支付
     *
     * @param paymentType
     * @return
     */
    public static Boolean isOnlinePay(PaymentTypeEnum paymentType) {
        if (PAYMENT_TYPE_ALIPAY.equals(paymentType) ||
                PAYMENT_TYPE_WECHAT.equals(paymentType) ||
                PAYMENT_TYPE_YINLIANQIANBAO.equals(paymentType) ||
                PAYMENT_TYPE_SWIPECARD.equals(paymentType)) {
            return true;
        }
        return false;
    }

    /**
     * 是否支持混合支付，如会员+现金，会员+在线支付即混合支付
     *
     * @param paymentType
     * @return
     */
    public static Boolean isSupportMixedPay(PaymentTypeEnum paymentType) {


        if (PAYMENT_TYPE_PARTNER_MEMBER.equals(paymentType) ||
                PAYMENT_TYPE_MEMBER.equals(paymentType) ||
                PAYMENT_TYPE_MEI_TUAN_COUPON.equals(paymentType) ||
                PAYMENT_TYPE_REPLY_COUPON.equals(paymentType)||
				PAYMENT_TYPE_COUPON.equals(paymentType)
                ) {
            return true;
        }
        return false;
    }

    /**
     * 是否是互斥支付
     *
     * @return
     */
    public Boolean isMutex() {
        return isMutex(this);
    }

    /**
     * 是否是在线支付
     *
     * @return
     */
    public Boolean isOnlinePay() {
        return isOnlinePay(this);
    }

    /**
     * 是否支持混合支付
     *
     * @return
     */
    public Boolean isSupportMixedPay() {
        return isSupportMixedPay(this);
    }

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
}
