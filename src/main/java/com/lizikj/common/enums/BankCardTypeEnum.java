package com.lizikj.common.enums;

/**
 * 银行卡类型枚举
 *
 * @author zhoufe    2018/05/07
 */
public enum BankCardTypeEnum {
    /**
     * 0:未知
     */
    UNKNOWM((byte) 0, "未知"),
    /**
     * 1:借记卡
     */
    DEPOSIT_CARD((byte) 1, "储蓄卡"),
    /**
     * 2:贷记卡
     */
    CREDIT_CARD((byte) 2, "信用卡"),


    ;

    public Byte code;
    public String message;

    private BankCardTypeEnum() {
    }

    BankCardTypeEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取代码
     * @params [code]
     * @return com.lizikj.common.enums.BankCardTypeEnum
     * @author zhoufe
     * @date 2018/5/7 11:12
     */
    public static BankCardTypeEnum fromCode(Byte code) {
        for (BankCardTypeEnum typeEnum : BankCardTypeEnum.values()) {
            if (! typeEnum.getCode().equals(code)) {
                continue;
            }
            return typeEnum;
        }
        return null;
    }

    public Byte getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
