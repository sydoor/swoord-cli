package com.lizikj.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class AmountUtils {

	/**
	 * 分转元，精确到小数点后2位
	 * @param amount
	 * @return String
	 * @author lijundong
	 * @date 2017年6月19日 下午7:31:35
	 */
	public static String fenToYuan(long amount) {
		BigDecimal value = new BigDecimal(amount);
		value = value.divide(new BigDecimal(100));
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(value);
	}

	public static long yuanToFen(double amount){
		BigDecimal value = new BigDecimal(amount+"").multiply(new BigDecimal(""+100));
		return value.setScale(2,BigDecimal.ROUND_HALF_DOWN).longValue();
	}
}
