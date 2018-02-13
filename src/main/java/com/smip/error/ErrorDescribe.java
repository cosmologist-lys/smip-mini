package com.smip.error;

/**
 * Created by kepler@gmail.com on 2018/2/5.
 */
public enum ErrorDescribe {
    EMPTY_TABLE_OPTION("cannot do this execution from an empty table","空表无法执行删除操作"),
    EMPTY_ID_OPTION("cannot do this execution via an empty id","该操作需要传递id参数"),
    EMPTY_OBJECT_OPTION("cannot do this execution via an empty object","该操作需要传递对象信息"),
    ONE_ROW_ONLY_SAVE("this table can only apply one row","此表只能保存至多一条内容"),
    EXCLUDE_ID_SAVE("cannot save items via an object with id","保存对象不可包含id参数"),
    TABLE_LIMITS("cannot execute this on a table with length limit","无法在固定长度的表里执行此操作"),
    EMPTY_PARAMS_OPTION("params are need indeed but empty","参数不可为空"),

    LIMITED_PERMISSION("no permission authorited","没有访问权限"),
    NOT_FOUND("404 not found","错误的URL地址");


    private String eng;
    private String chn;
    private ErrorDescribe(String eng,String chn){
        this.eng = eng;
        this.chn = chn;
    }

    public String getEng() {
        return eng;
    }

    public ErrorDescribe setEng(String eng) {
        this.eng = eng;
        return this;
    }

    public String getChn() {
        return chn;
    }

    public ErrorDescribe setChn(String chn) {
        this.chn = chn;
        return this;
    }

    public String getDescribe(){
        return this.eng+" / "+this.chn;
    }
}
