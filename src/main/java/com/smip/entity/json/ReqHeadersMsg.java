package com.smip.entity.json;


/**
 * 请求验证头部
 * Created by kepler@gmail.com on 2017/11/15.
 */
public class ReqHeadersMsg {
    private String username;
    private boolean valid;
    private String psw;
    private String enPsw;
    private String uri;


    public ReqHeadersMsg(String username, boolean valid, String psw, String enPsw, String uri) {
        this.username = username;
        this.valid = valid;
        this.psw = psw;
        this.enPsw = enPsw;
        this.uri = uri;
    }


    public ReqHeadersMsg(boolean valid) {
        this.valid = valid;
    }

    public ReqHeadersMsg() {

    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "ReqHeadersMsg{" +
                "username='" + username + '\'' +
                ", valid=" + valid +
                ", psw='" + psw + '\'' +
                ", enPsw='" + enPsw + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getEnPsw() {
        return enPsw;
    }

    public void setEnPsw(String enPsw) {
        this.enPsw = enPsw;
    }
}
