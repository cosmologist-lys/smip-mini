package com.smip.entity.json;


/**
 * 请求验证msg,feedbackjson子类
 * Created by kepler@gmail.com on 2017/11/15.
 */
public class ReqInfoMsg {
    private String tokenUsername;//用户名
    private String tokenPsw;//密码
    private String requri;//请求地址
    private String describe;
    private String reqtime;//请求时间
    private String type;


    public ReqInfoMsg(String describe, String reqtime, String type,ReqHeadersMsg header) {
        this.describe = describe;
        this.reqtime = reqtime;
        this.type = type;
        this.tokenUsername = header.getUsername();
        this.tokenPsw = header.get_token();
        this.requri = header.getUri();
    }

    public ReqInfoMsg(String tokenUsername, String tokenPsw, String requri, String describe, String reqtime, String type) {
        this.tokenUsername = tokenUsername;
        this.tokenPsw = tokenPsw;
        this.requri = requri;
        this.describe = describe;
        this.reqtime = reqtime;
        this.type = type;
    }

    public ReqInfoMsg() {
    }

    public String getTokenUsername() {
        return tokenUsername;
    }

    public void setTokenUsername(String tokenUsername) {
        this.tokenUsername = tokenUsername;
    }

    public String getTokenPsw() {
        return tokenPsw;
    }

    public void setTokenPsw(String tokenPsw) {
        this.tokenPsw = tokenPsw;
    }

    public String getRequri() {
        return requri;
    }

    public void setRequri(String requri) {
        this.requri = requri;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getReqtime() {
        return reqtime;
    }

    public void setReqtime(String reqtime) {
        this.reqtime = reqtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
