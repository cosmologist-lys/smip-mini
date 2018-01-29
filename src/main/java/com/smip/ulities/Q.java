package com.smip.ulities;

import com.smip.enums.DateFmt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 简单工具类
 * Created by kepler@gmail.com on 2017/11/23.
 */
public class Q {
    /**
     * 判断是否为空
     * @param objs
     * @return
     */
    public static boolean notNull(Object... objs){
        for (Object o : objs){
            if (null == o || 0 == o.toString().length()) return false;
        }
        return true;
    }

    /**
     * 返回string时间
     * @param fmt 想要的格式
     * @return
     */
    public static String getDateString(Date dNow, DateFmt fmt) {
        SimpleDateFormat sdf = null;
        switch (fmt){
            case YMDHMS:
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case HMS:
                sdf = new SimpleDateFormat("hh:mm:ss");
                break;
            case YMD:
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case YM:
                sdf = new SimpleDateFormat("yyyy-MM");
                break;
            case MD:
                sdf = new SimpleDateFormat("MM-dd");
                break;
            case CUST:
                sdf = new SimpleDateFormat("yyyyMMdd/HHmmssSSSSSS");
                break;
        }
        return sdf.format(dNow);
    }

    /**
     * 全角符号转半角
     *
     * @param input
     * @return 半角字符串
     */

    public static String ToDBC(String input) {

        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        String returnString = new String(c);

        return returnString;
    }

    /**
     * 空转0
     * @param obj
     * @return
     */
    public static Object null2zero(Object obj){
            return obj==null?0:obj;
    }

    /**
     * 随机产生指定长度的字符串
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 过滤<, >,\n 字符的方法。
     * @param input 需要过滤的字符
     */
    public static String filterMark(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll(" ", "&nbsp;");
        input = input.replaceAll("'", "&#39;");
        input = input.replaceAll("\"", "&quot;");
        input.replaceAll("\n", "<br>");
        return input;
    }

    public static String null2Empty(String arg){
        return null == arg?"":arg;
    }

    /**
     * 判断是否为字母
     * @param str
     */
    public static boolean isCharacter(String str) {
        byte[] buf = str.getBytes();
        for (byte b : buf) {
            if (b < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断全是是数字
     * @param str
     */
    public static boolean isNumber(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取修改后的object.classname
     */
    public static String getClassname(Object object){
        /*if (object instanceof Integer)
            return "int";
        if (object instanceof Boolean)
            return "bool";
        if (object instanceof Double)
            return "double";
        if (object instanceof Float)
            return "float";*/
        if (notNull(object)){
            String name = object.getClass().getName().toString();
            if (name.contains("smip."))
                name = name.split("smip.")[1];
            return name;
        }
        return null;
    }
}
