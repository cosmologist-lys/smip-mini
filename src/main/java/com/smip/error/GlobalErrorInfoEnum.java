package com.smip.error;

/**
 * Created by kepler@gmail.com on 2017/11/6.
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface{
    SUCCESS("0", "成功"),
    NOT_FOUND("-1", "未找到"),
    FAILED("-1","失败"),
    URL_ERROR("-404","请求地址错误"),
    PARAM_ERROR("-2","参数错误");


    private String code;

    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
