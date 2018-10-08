package com.lizikj.common.template;

import com.lizikj.common.enums.MessageBizTypeEnum;
import com.lizikj.common.enums.MessageSysModuleEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息推送内容模板
 *
 * @author liaojw
 * @date 2017年8月30日 上午9:39:16
 */
public class MessageContentTemplate {
    public final static String TYPE_KEY = "type";
    public final static String SUBTYPE_KEY = "subType";
    public final static String SCREAM_KEY = "scream";
    public final static String CONTEXT_KEY = "context";
    /**
     * 系统模块类型
     */
    private Integer type;
    /**
     * 业务类型
     */
    private Integer subType;
    /**
     * 机器是否响铃
     */
//    private Boolean ring; //Zone edited 2017-09-12
    /**
     * 语音提示内容（如果无不提醒）
     */
    private String scream;//Zone edited 2017-09-12

    /**
     * 消息要处理的内容，json格式 存放相关业务处理相关内容
     */
    private String context;
    /**
     * 消息显示内容（不能为空）
     */
    private String content;

    public MessageContentTemplate() {
    }

    public MessageContentTemplate(MessageSysModuleEnum sysModuleType, MessageBizTypeEnum bizType, String content, String scream, String context) {
        this.type = sysModuleType.getCode();
        this.subType = bizType.getCode();
        this.content = content;
        this.scream = scream;
        this.context = context;
    }

    public MessageContentTemplate(MessageSysModuleEnum sysModuleType, MessageBizTypeEnum bizType, String content) {
        this.type = sysModuleType.getCode();
        this.subType = bizType.getCode();
        this.content = content;
    }

    public MessageContentTemplate(MessageSysModuleEnum sysModuleType, MessageBizTypeEnum bizType,String content, String context) {
        this.type = sysModuleType.getCode();
        this.subType = bizType.getCode();
        this.content = content;
        this.context = context;
    }

    public Map<String,String> buildMsgExtra(){
        Map<String,String> extra = new HashMap<>();
        extra.put(TYPE_KEY,String.valueOf(this.type));
        extra.put(SUBTYPE_KEY,String.valueOf(this.subType));
        if (this.scream != null) {
            extra.put(SCREAM_KEY,String.valueOf(this.scream));
        }
        if (this.context != null) {
            extra.put(CONTEXT_KEY,String.valueOf(this.context));
        }

        return extra;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScream() {
        return scream;
    }

    public void setScream(String scream) {
        this.scream = scream;
    }
}
