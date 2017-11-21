package com.smip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableCaching
public class SmipMiniApplication {

	public static void main(String[] args) {
		Object[] sources = {SmipMiniApplication.class};
		SpringApplication.run(sources, args);
	}
}
