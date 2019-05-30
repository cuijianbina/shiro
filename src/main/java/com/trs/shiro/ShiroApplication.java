package com.trs.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class }) // 忽略数据库配置
@SpringBootApplication
public class ShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroApplication.class, args);
	}

}
