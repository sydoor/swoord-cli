package com.lizikj.common.exception;

import com.lizikj.common.enums.ArgumentCheckErrorEnum;
import com.lizikj.common.enums.CheckDataRuleEnum;
import com.lizikj.common.util.BeanUtil;

/**
 * Created by Michael.Huang on 2017/4/1.
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -233607534302505304L;

    /**
     * 错误码
     */
    String code;

    /**
     * 错误消息
     */
    String message;

    Throwable cause;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }


    public BaseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    public BaseException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.message = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static void checkData(Object object,CheckDataRuleEnum checkDataRuleEnum,String... args)throws BaseException{
        if(null == object){
            throw  new BaseException(ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getCode(),"检查参数为空");
        }
        if(null == args || args.length==0){
            return;
        }
        for(int i =0;i<args.length;i++){
            try{
                Object obj = BeanUtil.getPropertyValue(object.getClass(),object,args[i]);
                if(null == obj){
                    throw  new BaseException(ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getCode(),ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getMessage()+":"+args[0]);
                }
                int isOk = checkDataRuleEnum.checkValue(args[i],obj);
            }catch (Exception e){
                throw new BaseException("-1200",e.getMessage(),e);
            }
        }
    }
}
