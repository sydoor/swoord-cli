package com.lizikj.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * emoji工具类
 * @author zone
 * @date 2017年11月17日
 */
public class EmojiUtil {

	/**
	 * 去除emoji表情字符
	 * @param source
	 * @return
	 */
	public static String removeEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\ud83e\udc00-\ud83e\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("");
			}
		}
		return source;
	}
	
	/**
	 * 字符串转16进制
	 * @param source
	 * @return
	 */
	public static String getUnicode(String source) {
		String returnUniCode = null;
		String uniCodeTemp = null;
		for (int i = 0; i < source.length(); i++) {
			uniCodeTemp = "\\u" + Integer.toHexString((int) source.charAt(i));
			returnUniCode = returnUniCode == null ? uniCodeTemp : returnUniCode + uniCodeTemp;
		}
		System.out.print(source + " 's unicode = " + returnUniCode);
		return returnUniCode;
	}
}
