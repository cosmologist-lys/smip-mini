package com.smip.entity;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;



/**
 * 返回json
 */
public class FeedbackJson<T> {
    private ReqInfoMsg info;
    private T obj;
    private HttpStatus httpStatus;
    private MultiValueMap<String,String> headers;
    private int size;

    public FeedbackJson(ReqInfoMsg info, T t, HttpStatus httpStatus, MultiValueMap<String, String> headers,int size) {
        this.info = info;
        this.obj = t;
        this.httpStatus = httpStatus;
        this.headers = headers;
        this.size = size;
    }

    public FeedbackJson() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FeedbackJson{" +
                "info=" + info +
                ", obj=" + obj +
                ", httpStatus=" + httpStatus +
                ", headers=" + headers +
                '}';
    }

    public ReqInfoMsg getInfo() {
        return info;
    }

    public void setInfo(ReqInfoMsg info) {
        this.info = info;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public MultiValueMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(MultiValueMap<String, String> headers) {
        this.headers = headers;
    }
}
