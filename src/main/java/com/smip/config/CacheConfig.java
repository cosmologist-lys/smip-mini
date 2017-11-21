package com.smip.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * guava配置，定义缓存最大释放时间和最大size
 * Created by kepler@gmail.com on 2017/11/21.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    private static final int GUAVA_MAXSIZE = 3000;
    private static final int GUAVA_EXPIRETIME = 60*60*2;

    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(
                CacheBuilder.newBuilder().
                        expireAfterWrite(GUAVA_EXPIRETIME, TimeUnit.SECONDS).
                        maximumSize(GUAVA_MAXSIZE));
        return cacheManager;
    }
}
