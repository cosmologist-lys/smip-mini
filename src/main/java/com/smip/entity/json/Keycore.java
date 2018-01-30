package com.smip.entity.json;

import java.util.Arrays;

/**
 * Created by kepler@gmail.com on 2018/1/29.
 * conjson 子类，主要信息部分
 */
public class Keycore {
    private String _tkn; //请求用户名
    private String _unit_code; //单位代码，留用
    private String _token; //token
    private int _auth; //加密类型
    private int _comtick; //请求次数
    private boolean _isvalid; //登陆标志
    private String[] _userlimits; //用户权限
    private double _timecost; //请求耗时

    public Keycore(String _tkn, String _token, int _auth, int _comtick, boolean _isvalid, String[] _userlimits,double timecost,String _unit_code) {
        this._tkn = _tkn;
        this._token = _token;
        this._auth = _auth;
        this._comtick = _comtick;
        this._isvalid = _isvalid;
        this._userlimits = _userlimits;
        this._timecost = timecost;
        this._unit_code = _unit_code;
    }

    public Keycore() {
    }

    @Override
    public String toString() {
        return "Keycore{" +
                "_tkn='" + _tkn + '\'' +
                ", _token='" + _token + '\'' +
                ", _auth=" + _auth +
                ", _comtick='" + _comtick + '\'' +
                ", _isvalid=" + _isvalid +
                ", _userlimits=" + Arrays.toString(_userlimits) +
                ", _timecost="+_timecost+
                '}';
    }

    public String get_tkn() {
        return _tkn;
    }

    public Keycore set_tkn(String _tkn) {
        this._tkn = _tkn;
        return this;
    }

    public String get_token() {
        return _token;
    }

    public Keycore set_token(String _token) {
        this._token = _token;
        return this;
    }

    public int get_auth() {
        return _auth;
    }

    public Keycore set_auth(int _auth) {
        this._auth = _auth;
        return this;
    }

    public int get_comtick() {
        return _comtick;
    }

    public Keycore set_comtick(int _comtick) {
        this._comtick = _comtick;
        return this;
    }

    public boolean is_isvalid() {
        return _isvalid;
    }

    public Keycore set_isvalid(boolean _isvalid) {
        this._isvalid = _isvalid;
        return this;
    }

    public String[] get_userlimits() {
        return _userlimits;
    }

    public Keycore set_userlimits(String[] _userlimits) {
        this._userlimits = _userlimits;
        return this;
    }

    public double get_timecost() {
        return _timecost;
    }

    public Keycore set_timecost(double _timecost) {
        this._timecost = _timecost;
        return this;
    }

    public String get_unit_code() {
        return _unit_code;
    }

    public Keycore set_unit_code(String _unit_code) {
        this._unit_code = _unit_code;
        return this;
    }
}
