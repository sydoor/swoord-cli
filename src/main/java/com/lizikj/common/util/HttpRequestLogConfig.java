package com.lizikj.common.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * http日志配置
 * 
 * @author liaojw 
 * @date 2017年11月17日 下午2:13:42
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpRequestLogConfig {
	boolean isIgnore() default false;//是否忽略
	boolean isEncrypt() default false;//是否加密
}
