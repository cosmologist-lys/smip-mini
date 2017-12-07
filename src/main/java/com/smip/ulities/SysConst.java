package com.smip.ulities;

import com.smip.entity.sys.Secuser;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 系统静态常量
 * 项目启动时加载
 */
public class SysConst {
    public static Map<String, Secuser> SYS_SECUSERS = new LinkedHashMap<>();
}
