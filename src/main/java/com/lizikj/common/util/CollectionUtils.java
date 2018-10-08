package com.lizikj.common.util;

import java.util.List;
import java.util.Map;

/**
 * 集合根据类
 * @author zhoufe
 * @date 2017/8/4 17:25
 */
public class CollectionUtils {

    private CollectionUtils(){}

    /**
     * 判断list是否不为空
     * @author zhoufe 2017年6月19日 下午3:10:53
     * @param list
     * @return
     */
    public static <T> boolean isListNotBlank(List<T> list) {
        return list != null && !list.isEmpty();
    }

    /**
     * 判断list是否不空
     * @author zhoufe 2017年6月19日 下午3:10:53
     * @param list
     * @return
     */
    public static <T> boolean isListBlank(List<T> list) {
        return !isListNotBlank(list);
    }
    
    /**
     * 判断map是否不为空
     * @author zone
     * @date 2017年10月20日15:06:31
     * @param map
     * @return
     */
	public static <T> boolean isMapNotBlank(Map map) {
		return map != null && !map.isEmpty();
	}
    
    /**
     * 判断map是否不空
     * @author zone
     * @date 2017年10月20日15:06:31
     * @param map
     * @return
     */
    public static <T> boolean isMapBlank(Map map) {
    	return !isMapNotBlank(map);
    }

}
