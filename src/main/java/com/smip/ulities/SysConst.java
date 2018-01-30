package com.smip.ulities;

import com.smip.entity.json.UserJson;
import com.smip.entity.sys.Secuser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统静态常量
 * 项目启动时加载
 */
public class SysConst {
    //系统启动时加载所有用户map
    public static Map<String, Secuser> SYS_SECUSERS_MAP = new LinkedHashMap<>();
    //系统启动时加载所有用户map 。。。
    // 1个MAP1个LIST因为查找速度不同，场景不同时各有优势，其实本身也没多大，没必要这么搞。
    public static List<Secuser> SYS_SECUSER_LIST = new ArrayList<>();

    //已登陆用户
    public static Map<String, UserJson> SYS_SECUSER_TOKEN = new LinkedHashMap<>();

}
