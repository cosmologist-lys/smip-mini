package com.smip.entity.json;

import com.smip.enums.DateFmt;
import com.smip.ulities.Q;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * Created by kepler@gmail.com on 2018/1/29.
 * conjson 子类。response回去的信息
 */
public class Respmodule {
    private HttpStatus httpStatus;
    private Object object;
    private String describe;
    private String reptime;
    private String resptype;

    public Respmodule(HttpStatus httpStatus, Object object, String describe, Date reptime , String resptype) {
        this.httpStatus = httpStatus;
        this.object = object;
        this.describe = describe;
        this.reptime = Q.getDateString(reptime, DateFmt.CUST);
        this.resptype = resptype;
    }

    public Respmodule() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Respmodule setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public Respmodule setObject(Object object) {
        this.object = object;
        return this;
    }

    public String getDescribe() {
        return describe;
    }

    public Respmodule setDescribe(String describe) {
        this.describe = describe;
        return this;
    }

    public String getReptime() {
        return reptime;
    }

    public Respmodule setReptime(Date reptime) {
        this.reptime = Q.getDateString(reptime, DateFmt.CUST);
        return this;
    }

    public String getResptype() {
        return resptype;
    }

    public Respmodule setResptype(String resptype) {
        this.resptype = resptype;
        return this;
    }
}
