package com.smip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableCaching
//@MapperScan("com.smip.repository")
public class SmipMiniApplication {

	public static void main(String[] args) {
		Object[] sources = {SmipMiniApplication.class};
		SpringApplication.run(sources, args);
	}
}
