package com.lizikj.common.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * XML辅助工具类
 * @author lijundong 
 * @date 2017年6月16日 下午4:26:47
 */
public class XmlUtils {
	
	/**
	 * 把map转换对应的xml字符串
	 * @param parameters
	 * @return String
	 * @author lijundong
	 * @date 2017年6月16日 下午4:26:34
	 */
	public static String mapToXml(Map<String, String> params) {
		StringBuffer sb = new StringBuffer("<xml>");
		for(Map.Entry<String, String> entry: params.entrySet()){
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			v = escapeSpecialChar(v);
			sb.append("<" + k + ">" + v + "</" + k + ">" + "\r\n");
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * xml特殊字符转义
	 * @param value
	 * @return String
	 * @author lijundong
	 * @date 2017年6月16日 下午4:29:25
	 */
	private static String escapeSpecialChar(String value) {
		value = StringUtils.replace(value, "&", "&amp;");
		value = StringUtils.replace(value, "<", "&lt;");
		value = StringUtils.replace(value, ">", "&gt;");
		value = StringUtils.replace(value, "\"", "&quot;");
		value = StringUtils.replace(value, "'", "&apos;");
		return value;
	}
	
	/**
     * 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    @SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(String xml) {
    	if(StringUtils.isBlank(xml))
    		return Collections.emptyMap();
    	
    	Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            List<Element> list = rootElt.elements();// 获取根节点下所有节点
            for (Element element : list) { // 遍历节点
                map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
	
	/**
	 * 把map转换对应的xml 带data字符串
	 * @param parameters
	 * @return String
	 * @author zone
	 */
	public static String mapToDataXml(Map<String, String> params) {
		StringBuffer sb = new StringBuffer("<xml>");
		for(Map.Entry<String, String> entry: params.entrySet()){
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			v = escapeSpecialChar(v);
			sb.append("<").append(k).append("><![CDATA[").append(v).append("]]></").append(k).append(">");
		}
		sb.append("</xml>");
		return sb.toString();
	}

}
