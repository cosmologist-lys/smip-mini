package com.smip;

import com.smip.config.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SmipMiniApplication {


	public static void main(String[] args) {
		Object[] sources = {RedisConfig.class,SmipMiniApplication.class};
		SpringApplication.run(sources, args);
	}
}
