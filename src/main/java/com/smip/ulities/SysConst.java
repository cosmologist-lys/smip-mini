package com.smip.ulities;

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
    public static Map<String, Secuser> SYS_SECUSERS_MAP = new LinkedHashMap<>();
    public static List<Secuser> SYS_SECUSER_LIST = new ArrayList<>();

    public static Map<String, Secuser> SYS_SECUSER_TOKEN = new LinkedHashMap<>();
}
