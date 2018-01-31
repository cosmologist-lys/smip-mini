package com.smip.entity.json;

import com.smip.entity.sys.Secuser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kepler@gmail.com on 2018/1/30.
 * token为KEY，存放map里的user_json对象
 * 用来通信时候记录所用
 */
public class UserJson {
    private Secuser secuser;
    private String _token; //token
    private int _auth; //加密类型
    private int _comtick; //请求次数
    private boolean _isvalid; //登陆标志
    private String[] _userlimits; //用户权限
    private ArrayList uris;  //请求uri
    private String lastReqtime; //请求时间
    private String _unit_code;//单位代码，留用

    public UserJson() {
    }

    public UserJson(ConJson conJson, Secuser secuser) {
        this._token = conJson.getKeycore().get_token();
        this._auth = conJson.getKeycore().get_auth();
        this._isvalid = conJson.getKeycore().is_isvalid();
        this._comtick++;
        this._userlimits = conJson.getKeycore().get_userlimits();
        this.lastReqtime = conJson.getRequst().getReqtime();
        //this.uris.add(conJson.getRequst().getUri());
        if (secuser != null) {
            this.secuser = secuser;
        }
    }

    public UserJson(String _token, Secuser secuser) {
        this.secuser = secuser;
        this._token = _token;
    }

    public UserJson(Secuser secuser, String _token, int _auth, int _comtick, boolean _isvalid, String[] _userlimits, ArrayList uris, String lastReqtime, String _unit_code) {
        this.secuser = secuser;
        this._token = _token;
        this._auth = _auth;
        this._comtick = _comtick;
        this._isvalid = _isvalid;
        this._userlimits = _userlimits;
        this.uris = uris;
        this.lastReqtime = lastReqtime;
        this._unit_code = _unit_code;
    }

    @Override
    public String toString() {
        return "UserJson{" +
                "secuser=" + secuser +
                ", _token='" + _token + '\'' +
                ", _auth=" + _auth +
                ", _comtick=" + _comtick +
                ", _isvalid=" + _isvalid +
                ", _userlimits=" + Arrays.toString(_userlimits) +
                ", uris=" + uris +
                ", lastReqtime='" + lastReqtime + '\'' +
                ", _unit_code='" + _unit_code + '\'' +
                '}';
    }

    public Secuser getSecuser() {
        return secuser;
    }

    public UserJson setSecuser(Secuser secuser) {
        this.secuser = secuser;
        return this;
    }

    public String get_token() {
        return _token;
    }

    public UserJson set_token(String _token) {
        this._token = _token;
        return this;
    }

    public int get_auth() {
        return _auth;
    }

    public UserJson set_auth(int _auth) {
        this._auth = _auth;
        return this;
    }

    public int get_comtick() {
        return _comtick;
    }

    public UserJson set_comtick(int _comtick) {
        this._comtick = _comtick;
        return this;
    }

    public boolean is_isvalid() {
        return _isvalid;
    }

    public UserJson set_isvalid(boolean _isvalid) {
        this._isvalid = _isvalid;
        return this;
    }

    public String[] get_userlimits() {
        return _userlimits;
    }

    public UserJson set_userlimits(String[] _userlimits) {
        this._userlimits = _userlimits;
        return this;
    }

    public ArrayList getUris() {
        return uris;
    }

    public UserJson setUris(ArrayList uris) {
        this.uris = uris;
        return this;
    }

    public String getLastReqtime() {
        return lastReqtime;
    }

    public UserJson setLastReqtime(String lastReqtime) {
        this.lastReqtime = lastReqtime;
        return this;
    }

    public String get_unit_code() {
        return _unit_code;
    }

    public void set_unit_code(String _unit_code) {
        this._unit_code = _unit_code;
    }
}
