package com.smip.ulities;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单工具类
 * Created by kepler@gmail.com on 2017/11/23.
 */
public class briefTool {
    public static boolean notNull(Object... objs){
        boolean flg = true;
        for (Object o : objs){
            if (null == o){
                flg = false;
                break;
            }
        }
        return flg;
    }
}
