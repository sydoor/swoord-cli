package com.lizikj.common.enums;

/**
 * 消息中心-系统模块枚举
 *
 * @author liaojw
 * @date 2017年8月30日 上午9:41:51
 */
public enum MessageSysModuleEnum {
    /**
     * 系统模块-美食系统
     */
    MERCHANDISE_SYS(100, "美食系统"),
    /**
     * 系统模块-商户系统
     */
    MERCHANT_SYS(200, "商户系统"),
    /**
     * 系统模块-会员系统
     */
    MEMBER_SYS(300, "会员系统"),
    /**
     * 系统模块-门店系统
     */
    SHOP_SYS(400, "门店系统"),
    /**
     * 系统模块-用户系统
     */
    USER_SYS(500, "用户系统"),
    /**
     * 系统模块-订单系统
     */
    ORDER_SYS(600, "订单系统"),
    /**
     * 系统模块-支付系统
     */
    PAYMENT_SYS(700, "支付系统"),
    /**
     * 系统模块-报表系统
     */
    REPORTING_SYS(800, "报表系统"),
    /**
     * 系统模块-对账系统
     */
    BALANCE_SYS(900, "对账系统"),
    /**
     * 系统模块-运营系统
     */
    OPT_SYS(1000, "运营系统"),
    /**
     * 系统模块-数据操作日志系统
     */
    DATA_LOG_SYS(1100, "数据操作日志系统"),//Zone edited 2017-09-12
    /**
     * 系统模块-登录
     */
    LOGIN_MODULE(1200, "登录模块"),//Zone edited 2017-09-12
    /**
     * 系统模块-外卖
     */
    CATER_SYS(1300, "外卖模块")//liaojw 2017/11/1
    ;
    public Integer code;
    public String message;

    MessageSysModuleEnum() {
    }

    MessageSysModuleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(byte code) {
        for (MessageSysModuleEnum e : MessageSysModuleEnum.values()) {
            if (e.getCode() == code) {
                return e.getMessage();
            }
        }
        return "";
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
}
