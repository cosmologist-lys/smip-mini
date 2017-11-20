package com.smip.entity.json;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

import java.util.Objects;


/**
 * 返回json
 */
public class FeedbackJson<T> {
    private ReqInfoMsg info;
    private Object targets;
    private HttpStatus httpStatus;
    private MultiValueMap<String,String> headers;
    private int size;

    public FeedbackJson(ReqInfoMsg info, Object ts, HttpStatus httpStatus, MultiValueMap<String, String> headers,int size) {
        this.info = info;
        this.targets = ts;
        this.httpStatus = httpStatus;
        this.headers = headers;
        this.size = size;
    }

    public FeedbackJson(ReqInfoMsg info, Object targets, HttpStatus httpStatus, MultiValueMap<String, String> headers) {
        this.info = info;
        this.targets = targets;
        this.httpStatus = httpStatus;
        this.headers = headers;
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
                ", obj=" + targets +
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

    public Object getTargets() {
        return targets;
    }

    public void setTargets(Object targets) {
        this.targets = targets;
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
