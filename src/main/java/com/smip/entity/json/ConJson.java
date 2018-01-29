package com.smip.entity.json;

/**
 * Created by kepler@gmail.com on 2018/1/29.
 * 返回json集合
 */
public class ConJson<T> {
    private Keycore keycore;
    private Reqmodule requst;
    private Respmodule response;

    public ConJson(Keycore keycore, Reqmodule requst, Respmodule response) {
        this.keycore = keycore;
        this.requst = requst;
        this.response = response;
    }

    public ConJson() {
    }

    public ConJson(Keycore keycore, Reqmodule requst) {
        this.keycore = keycore;
        this.requst = requst;
    }

    public ConJson(boolean valid){
        this.keycore = new Keycore().set_isvalid(valid);
    }

    @Override
    public String toString() {
        return "ConJson{" +
                "keycore=" + keycore +
                ", requst=" + requst +
                ", response=" + response +
                '}';
    }

    public Keycore getKeycore() {
        return keycore;
    }

    public ConJson setKeycore(Keycore keycore) {
        this.keycore = keycore;
        return this;
    }

    public Reqmodule getRequst() {
        return requst;
    }

    public ConJson setRequst(Reqmodule requst) {
        this.requst = requst;
        return this;
    }

    public Respmodule getResponse() {
        return response;
    }

    public ConJson setResponse(Respmodule response) {
        this.response = response;
        return this;
    }
}
