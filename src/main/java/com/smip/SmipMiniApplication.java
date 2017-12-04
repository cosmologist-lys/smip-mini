package com.smip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableTransactionManagement
@SpringBootApplication
@EnableCaching
@EnableScheduling
@CrossOrigin(origins = { "http://localhost:3300" }, methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST,
		RequestMethod.DELETE })
//@MapperScan("com.smip.repository") mybatis.dao层文件扫描
public class SmipMiniApplication {

	public static void main(String[] args) {
		Object[] sources = {SmipMiniApplication.class};
		SpringApplication.run(sources, args);
	}
}
