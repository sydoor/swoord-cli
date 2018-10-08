package com.lizikj.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal工具类
 * @author lijundong 
 * @date 2017年9月11日 下午5:56:26
 */
public class BigDecimalUtil {

	/**
	 * 进行加法运算
	 * @param b1
	 * @param b2
	 * @return BigDecimal
	 * @author lijundong
	 * @date 2017年9月11日 下午5:56:20
	 */
	public static double add(double d1, double d2) {
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
		return bigDecimal1.add(bigDecimal2).doubleValue();
	}

	/**
	 * 进行减法运算
	 * @param d1
	 * @param d2
	 * @return BigDecimal
	 * @author lijundong
	 * @date 2017年9月11日 下午5:56:06
	 */
	public static double sub(double d1, double d2) {
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
		return bigDecimal1.subtract(bigDecimal2).doubleValue();
	}

	/**
	 * 进行乘法运算
	 * @param d1
	 * @param d2
	 * @return BigDecimal
	 * @author lijundong
	 * @date 2017年9月11日 下午5:56:01
	 */
	public static double mul(double d1, double d2) {
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
		return bigDecimal1.multiply(bigDecimal2).doubleValue();
	}

	/**
	 * 进行除法运算
	 * @param d1
	 * @param d2
	 * @return BigDecimal
	 * @author lijundong
	 * @date 2017年9月11日 下午5:55:47
	 */
	public static double div(double d1, double d2) {
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
		return bigDecimal1.divide(bigDecimal2).doubleValue();
	}
	
	/**
	 * 进行除法运算
	 * @param d1
	 * @param d2
	 * @param len 几位小数点
	 * @param roundingMode 舍入模式
	 * @return BigDecimal
	 * @author lijundong
	 * @date 2017年9月11日 下午5:55:47
	 */
	public static double div(double d1, double d2, int len, RoundingMode roundingMode) {
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
		return bigDecimal1.divide(bigDecimal2, len, roundingMode).doubleValue();
	}
	
	/**
	 * 比较大小
	 * @param d1
	 * @param d2
	 * @return int
	 * @author lijundong
	 * @date 2017年9月11日 下午5:55:54
	 */
	public static int compare(double d1, double d2) {
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2));
		return bigDecimal1.compareTo(bigDecimal2);
	}

	/**
	 * 进行四舍五入操作
	 * @param d1
	 * @param len 保留几位小数
	 * @return BigDecimal
	 * @author lijundong
	 * @date 2017年9月11日 下午5:55:23
	 */
	public static double rounding(double d1, int len) {
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
		return bigDecimal1.setScale(len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 进行向下取整操作
	 * @param d1
	 * @param len 保留几位小数
	 * @return BigDecimal
	 * @author lijundong
	 * @date 2017年9月11日 下午5:55:23
	 */
	public static double roundDown(double d1, int len) {
		BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
		return bigDecimal1.setScale(len, BigDecimal.ROUND_DOWN).doubleValue();
	}
}