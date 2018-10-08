package com.lizikj.common.util;


import org.apache.commons.lang.StringUtils;

import com.lizikj.common.number.RandomUtils;
import org.joda.time.DateTime;

/**
 * 生成交易号工具类
 *
 * @author tangfeng
 * @date 2017年6月29日
 */
public class TradeNoUtil {

    /**
     * 根据sn生成该sn的pos订单号
     * POS产生订单号：SD+时间戳+8位随机数+sn的hash值
     * @params [sn]
     * @return java.lang.String
     * @author zhoufe
     * @date 2017/9/29 11:30
     */
    public static String genPosOrderNo(String sn){
        if (StringUtils.isBlank(sn)){
            throw new RuntimeException("传入的sn号为空");
        }
        return "S" + genOrderNo() + sn.hashCode();
    }

    /**
     * 根据时间生成该sn的pos订单号
     * POS产生订单号：SD+时间戳+8位随机数+sn的hash值
     * @params [sn]
     * @return java.lang.String
     * @author zhoufe
     * @date 2017/9/29 11:30
     */
    public static String genVDOrderNo(DateTime date){
        if (null == date){
            throw new RuntimeException("传入的时间为空");
        }
        return "D" + new DateTime(date).toString(DateUtils.FULL_READ_INDENT_PATTERN) + RandomUtils.randomNum(8);
    }

    /**
     * 生成会员流水订单编号
     * @return
     */
    public static String genMemberOrderNo(){
        return "M" + genTradeNo();
    }
    
    /**
     * 生成订单编号
     *
     * @return
     */
    public static String genOrderNo() {
        return "D" + genTradeNo();
    }

    public static String genCapitalOrderNo(){
        return "C" + genTradeNo();
    }

    /**
     * 根据订单编号生成支付流水号
     *
     * @param orderTradeNo
     * @return
     */
    public static String genPayTradeNo(String orderTradeNo) {
        if (null == orderTradeNo)
            return null;
        String payTradeNo = orderTradeNo + RandomUtils.getRandomNum(3);
        payTradeNo = payTradeNo.replace("D", "P");
        return payTradeNo;
    }

    /**
     * 根据支付流水号生成退单流水号
     *
     * @param payTradeNo
     * @return
     */
    public static String genRefundTradeNo(String payTradeNo) {
        if (null == payTradeNo)
            return null;
        String refundTradeNo = payTradeNo + RandomUtils.getRandomNum(3);
        refundTradeNo = refundTradeNo.replace("P", "R");
        return refundTradeNo;
    }

    /**
     * 根据支付流水号生成退单流水号
     *
     * @param payTradeNo
     * @return
     */
    public static String genRefundTradeNoByOrderNo(String orderNo) {
        if (null == orderNo)
            return null;
        String refundTradeNo = orderNo + RandomUtils.getRandomNum(6);
        refundTradeNo = refundTradeNo.replace("D", "R");
        return refundTradeNo;
    }
    
    /**
     * 生成交易号
     *
     * @return String
     * @author tangfeng
     * @date 2017年6月29日
     */
    private static String genTradeNo() {
        return DateUtils.getCurrent(DateUtils.FULL_READ_INDENT_PATTERN) + RandomUtils.randomNum(8);
    }

    /**
     * 生成商户号 规则：时间戳+6位随机数
     *
     * @return String
     * @author liaojw
     * @date 2017年7月10日 下午8:45:57
     */
    public static String genMerchantNo() {
        return DateUtils.getCurrent(DateUtils.FULL_READ_INDENT_PATTERN) + RandomUtils.randomNum(6);
    }

    /**
     * 生成版本号
     *
     * @return String
     * @author liaojw
     * @date 2017年7月11日 上午10:29:34
     */
    public static String genVersion() {
        return "v" + DateUtils.getCurrent(DateUtils.FULL_READ_INDENT_PATTERN);
    }

    /**
     * 生成员工编号
     * @return String
     * @author liaojw
     * @date 2017年7月28日 下午3:04:20
     */
    public static String getStaffCode(){
    	return "LZ" + DateUtils.getCurrent(DateUtils.SHORT_DATE_PATTERN) + RandomUtils.randomNum(6);
    }

    public static void main(String[] args) {

        String orderTradeNo = genOrderNo();

        System.out.println("orderTradeNo:" + orderTradeNo + ",length:" + orderTradeNo.length());

        String payTradeNo = genPayTradeNo(orderTradeNo);
        System.out.println("payTradeNo:" + payTradeNo + ",length:" + payTradeNo.length());

        String refundTradeNo = genRefundTradeNo(payTradeNo);
        System.out.println("refundTradeNo:" + refundTradeNo + ",length:" + refundTradeNo.length());
    }
}
