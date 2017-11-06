package com.smip.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ThreadPoolManager {

    public List<JSONObject> list(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(session.getServletContext());
        Map<String, ThreadPoolTaskExecutor> threadPoolTaskExecutorMap = ctx.getBeansOfType(ThreadPoolTaskExecutor.class);
        List<JSONObject> list = new ArrayList();

        threadPoolTaskExecutorMap.forEach((s, threadPoolTaskExecutor) -> {
            JSONObject json = new JSONObject();
            json.put("name", s);
            json.put("corePoolSize", threadPoolTaskExecutor.getCorePoolSize());
            json.put("maxPoolSize", threadPoolTaskExecutor.getMaxPoolSize());
            json.put("activeCount", threadPoolTaskExecutor.getActiveCount());
            json.put("keepAliveSeconds", threadPoolTaskExecutor.getKeepAliveSeconds());
            json.put("largestPoolSize", threadPoolTaskExecutor.getThreadPoolExecutor().getLargestPoolSize());
            json.put("queueSize", threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().size());
            list.add(json);
        });

        return list;
    }

    public JSONObject clean(String name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(session.getServletContext());
        ThreadPoolTaskExecutor threadPoolTaskExecutor = ctx.getBean(name, ThreadPoolTaskExecutor.class);
        threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().clear();

        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }

    public JSONObject update(String name, JSONObject data, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(session.getServletContext());
        ThreadPoolTaskExecutor threadPoolTaskExecutor = ctx.getBean(name, ThreadPoolTaskExecutor.class);
        if (data.getIntValue("maxPoolSize") > 0)
            threadPoolTaskExecutor.setMaxPoolSize(data.getIntValue("maxPoolSize"));
        if (data.getIntValue("corePoolSize") > 0)
            threadPoolTaskExecutor.setCorePoolSize(data.getIntValue("corePoolSize"));
        if (data.getIntValue("keepAliveSeconds") > 0)
            threadPoolTaskExecutor.setKeepAliveSeconds(data.getIntValue("keepAliveSeconds"));

        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }
}
