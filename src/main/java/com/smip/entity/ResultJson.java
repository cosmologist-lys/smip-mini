package com.smip.entity;

import com.smip.error.ErrorInfoInterface;
import com.smip.error.GlobalErrorInfoEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * 统一返回JSON格式
 * 成功时返回对象+code+message
 * 失败时返回code+message+url
 */
public class ResultJson {
    private String code;
    private String message;
    private String url;
    private Object result;
    private String discribe;
    private String jsonType;

    public ResultJson(ErrorInfoInterface errorInfo,HttpServletRequest req) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
        this.url = req.getRequestURL().toString();
    }

    public ResultJson(Object result) {
        this.code = GlobalErrorInfoEnum.SUCCESS.getCode();
        this.message = GlobalErrorInfoEnum.SUCCESS.getMessage();
        this.result = result;
        this.jsonType = "OBJECT";
    }

    public ResultJson(List<Objects> results){
        this.code = GlobalErrorInfoEnum.SUCCESS.getCode();
        this.message = GlobalErrorInfoEnum.SUCCESS.getMessage();
        this.result = results;
        this.jsonType = "LIST<OBJECT>";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getJsonType() {
        return jsonType;
    }

    public void setJsonType(String jsonType) {
        this.jsonType = jsonType;
    }
}
