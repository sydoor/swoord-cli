package com.lizikj.common.enums;

import com.lizikj.common.exception.BaseException;
import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuyuntao
 * Date: 17-7-2
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
public enum CheckDataRuleEnum {
    NOT_BLANK{
        @Override
        public String getDescription() {
            return "参数不能为空";
        }

        @Override
        public int getCode() {
            return -9999;
        }

        @Override
        public int checkValue(String paramName,Object paramValue)throws BaseException {
            if(null == paramValue || "".equals(paramValue)){
                throw  new BaseException(getCode()+"",getDescription());
            }
            return 1;
        }
    },
    THAN_ZERO{
        @Override
        public String getDescription() {
            return "参数值必须大于0";
        }

        @Override
        public int getCode() {
            return -8888;
        }

        @Override
        public int checkValue(String paramName,Object paramValue) throws BaseException {
            if(null == paramValue || "".equals(paramValue)){
                throw new BaseException(getCode()+"",paramName+"参数为空:");
            }
            if(Double.parseDouble(paramValue+"") <=0){
                throw new BaseException(getCode()+"",paramName+""+getDescription());
            }
            return 1;
        }
    }

;



    public abstract String getDescription();
    public abstract int getCode();
    public abstract int checkValue(String paramName,Object paramValue)throws BaseException;
}
