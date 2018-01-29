package com.smip.entity.json;

import com.smip.enums.DateFmt;
import com.smip.ulities.Q;

import java.util.Date;

/**
 * Created by kepler@gmail.com on 2018/1/29.
 * conjson 子类。从request来的信息
 */
public class Reqmodule {
    private String uri;  //请求uri
    private String reqtime; //请求时间
    private String route; //请求来源的vue.route

    public Reqmodule(String uri, String reqtime, String route) {
        this.uri = uri;
        //this.reqtime = Q.getDateString(reqtime, DateFmt.CUST);
        this.reqtime = reqtime;
        this.route = route;
    }

    public Reqmodule(String uri, Date reqtime, String route) {
        this.uri = uri;
        this.reqtime = Q.getDateString(reqtime, DateFmt.CUST);
        this.route = route;
    }

    public Reqmodule() {
    }

    @Override
    public String toString() {
        return "Reqmodule{" +
                "uri='" + uri + '\'' +
                ", reqtime='" + reqtime + '\'' +
                ", route='" + route + '\'' +
                '}';
    }

    public String getUri() {
        return uri;
    }

    public Reqmodule setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getReqtime() {
        return reqtime;
    }

    public Reqmodule setReqtime(String reqtime) {
        //this.reqtime = Q.getDateString(reqtime, DateFmt.CUST);
        this.reqtime = reqtime;
        return this;
    }

    public Reqmodule setReqtime(Date reqtime){
        this.reqtime = Q.getDateString(reqtime, DateFmt.CUST);
        return this;
    }

    public String getRoute() {
        return route;
    }

    public Reqmodule setRoute(String route) {
        this.route = route;
        return this;
    }
}
