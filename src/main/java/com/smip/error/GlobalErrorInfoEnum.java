package com.smip.error;

import org.springframework.http.HttpStatus;

import javax.xml.ws.http.HTTPException;

/**
 * Created by kepler@gmail.com on 2017/11/6.
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface{
    SUCCESS(HttpStatus.OK.toString(), "成功"),
    NOT_FOUND(HttpStatus.NOT_FOUND.toString(), "未找到"),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY.toString(),"错误请求"),
    EXPECTATION_FAILED(HttpStatus.EXPECTATION_FAILED.toString(),"有效期超时"),
    LOCKED(HttpStatus.LOCKED.toString(),"资源被锁定"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.toString(),"无权限"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.toString(),"错误请求"),
    FORBIDDEN(HttpStatus.FORBIDDEN.toString(),"无权限");


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
