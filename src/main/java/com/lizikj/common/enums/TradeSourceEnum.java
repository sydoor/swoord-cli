package com.lizikj.common.enums;

/**
 * Created by adept on 2017/7/18.
 */
public enum TradeSourceEnum {
    ADD_ORDER(1,"下单"),
    REFUND_ORDER(2,"退款"),
    RECHARGE(7,"充值"),
    OTHER_SOURCE(0,"其他来源"),

    ;
    private int code;
    private String description;

    private TradeSourceEnum(int code, String description){
        this.code = code;
        this.description = description;
    }

    public static TradeSourceEnum getEnumByCode(int code){//通过code值获取对应支付来源的枚举

        TradeSourceEnum[] tradeSourceEnums = TradeSourceEnum.values();
        for(TradeSourceEnum tradeSourceEnum:tradeSourceEnums){
            if(tradeSourceEnum.getCode() != code){
                continue;
            }
            return tradeSourceEnum;
        }

        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
