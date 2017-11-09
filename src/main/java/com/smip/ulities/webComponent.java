package com.smip.ulities;

/**
 * Created by kepler@gmail.com on 2017/11/8.
 */
public class webComponent {
    /**
     * 得到方法名
     * @return
     */
    public static String getMethodName(){
//        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//        StackTraceElement e = stacktrace[2];
//        String methodName = e.getMethodName();
//        return methodName;
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    /**
     * 根据方法名得到方法中文描述(对baseController公用方法),返回json用
     * @param methodname
     * @return
     */
    public static String getMethodDiscribe(String methodname){
        if (methodname == null) return null;
        String chinese = "";
        switch (methodname.toUpperCase()){
            case "QUERYSINGLEBYID":
                chinese = GlobalConstance.CONTROLLER_NAME_querySingleById;
                break;
            case "COUNTALL":
                chinese = GlobalConstance.CONTROLLER_NAME_countAll;
                break;
            case "COUNTBYOBJECT":
                chinese = GlobalConstance.CONTROLLER_NAME_countByObject;
                break;
            case "QUERYLISTBYOBJECT":
                chinese = GlobalConstance.CONTROLLER_NAME_queryListByObject;
                break;
            case "CHECKEXISTBYID":
                chinese = GlobalConstance.CONTROLLER_NAME_checkExistById;
                break;
            case "CHECKEXISTBYOBJECT":
                chinese = GlobalConstance.CONTROLLER_NAME_checkExistByObject;
                break;
            case "SAVELISTOBJECT":
                chinese = GlobalConstance.CONTROLLER_NAME_saveListObject;
                break;
            case "SAVEOBJECT":
                chinese = GlobalConstance.CONTROLLER_NAME_saveObject;
                break;
            case "DELETEOBJECT":
                chinese = GlobalConstance.CONTROLLER_NAME_deleteObject;
                break;
            case "DELETELISTBYOBJECT":
                chinese = GlobalConstance.CONTROLLER_NAME_deleteListByObject;
                break;
        }
        return chinese;
    }
}
