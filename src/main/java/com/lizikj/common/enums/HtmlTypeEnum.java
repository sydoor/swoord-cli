package com.lizikj.common.enums;

/**
 * Created by liangxiaolin on 2017/9/26.
 */
public enum  HtmlTypeEnum {
    HTML_UTF8("text/html;charset=utf-8"),
    HTML_GBK("text/html;charset=gbk"),
    HTML_ISO("text/html;charset=ISO-8859-1");
    private String type;
    HtmlTypeEnum(String type) {
        this.type = type;
    }
    public String getType() {
            return this.type;
        }
}
