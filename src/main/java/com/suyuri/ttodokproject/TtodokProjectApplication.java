package com.suyuri.ttodokproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@ComponentScan(basePackages = "com.suyuri.ttodokproject")
public class TtodokProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TtodokProjectApplication.class, args);
	}

}
