package com.lizikj.common.exception;

import com.lizikj.common.enums.ArgumentCheckErrorEnum;

import java.text.MessageFormat;

/**
 * 参数错误异常
 *
 * @author Michael.Huang
 * @date 2017/6/21
 */
public class ArgumentCheckException extends BaseException {

    /**
     * 错误信息模板
     */
    private static final String MSG_TEMPLATE = "错误码:{0}, 描述:{1}";
    private static final String MSG_FULL_TEMPLATE = "错误码:{0}, 描述:{1}, 详情:{2}";

    /**
     * private
     */
    private ArgumentCheckErrorEnum errorEnum;

    public ArgumentCheckException(ArgumentCheckErrorEnum errorEnum) {
        super(MessageFormat.format(MSG_TEMPLATE
                , errorEnum.getCode(), errorEnum.getMessage()));
        this.errorEnum = errorEnum;
    }

    public ArgumentCheckException(ArgumentCheckErrorEnum errorEnum, String exMsg) {
        super(MessageFormat.format(MSG_FULL_TEMPLATE
                , errorEnum.getCode(), errorEnum.getMessage(), exMsg));
        this.errorEnum = errorEnum;
    }

    public ArgumentCheckErrorEnum getErrorEnum() {
        return errorEnum;
    }
}
