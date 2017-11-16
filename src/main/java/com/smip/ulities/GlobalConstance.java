package com.smip.ulities;

import org.omg.CORBA.PUBLIC_MEMBER;

public class GlobalConstance {
    public static final String JSON_TYPE_OBJECT = "OBJECT";
    public static final String JSON_TYPE_LIST_OBJECT = "LIST<OBJECT>";
    public static final String JSON_TYPE_PAGE_OBJECT = "PAGE<OBJECT>";
    public static final String JSON_TYPE_BOOLEAN = "BOOLEAN";
    public static final String JSON_TYPE_INTEGER = "INTEGER";

    public static final String CONTROLLER_NAME_querySingleById = "根据ID查找";
    public static final String CONTROLLER_NAME_countAll = "查找所有该对象";
    public static final String CONTROLLER_NAME_countByObject = "根据条件检索对象条数";
    public static final String CONTROLLER_NAME_queryListByObject = "根据条件检索对象列表";
    public static final String CONTROLLER_NAME_checkExistById = "根据ID检索对象是否存在";
    public static final String CONTROLLER_NAME_checkExistByObject = "根据条件检索对象是否存在";
    public static final String CONTROLLER_NAME_saveObject = "保存单个对象";
    public static final String CONTROLLER_NAME_saveListObject = "保存多个对象";
    public static final String CONTROLLER_NAME_deleteObject = "根据条件删除单个对象";
    public static final String CONTROLLER_NAME_deleteListByObject = "根据条件删除多个对象";

    public static final int TOKEN_EXPIRES_HOUR = 3;
}
