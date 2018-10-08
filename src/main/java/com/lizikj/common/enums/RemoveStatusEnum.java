package com.lizikj.common.enums;

/**
 * 删除状态枚举
 *
 * @author Michael.Huang
 * @date 2017/7/6 11:14
 */
public enum RemoveStatusEnum {

    UN_REMOVED((byte) 0, "未删除"),
    REMOVED((byte) 1, "已删除");

    /**
     * 状态
     */
    private byte status;

    /**
     * 描述
     */
    private String description;

    private RemoveStatusEnum(byte status, String description) {
        this.status = status;
        this.description = description;
    }

    /**
     * 获取枚举类型
     *
     * @param status
     * @return
     */
    public static RemoveStatusEnum getEnum(byte status) {
        for (RemoveStatusEnum e :
                values()) {
            if (e.getStatus() == status) {
                return e;
            }
        }
        return null;
    }


    public byte getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
