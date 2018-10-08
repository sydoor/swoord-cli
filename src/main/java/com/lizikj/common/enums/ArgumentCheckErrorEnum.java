package com.lizikj.common.enums;

/**
 * 参数错误枚举
 *
 * @author Michael.Huang
 * @date 2017/6/21
 */
public enum ArgumentCheckErrorEnum {

    MISSING_REQUIRED_ARGUMENT("103", "缺少必要的参数"),
    INVALIDE_ARGUMENT("104", "无效的参数内容");


    private String code;
    private String message;

    private ArgumentCheckErrorEnum() {
    }

    private ArgumentCheckErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
}
