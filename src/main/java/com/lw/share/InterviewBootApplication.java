package com.lw.share;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"personal.tools","com.lw.share"})
public class InterviewBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewBootApplication.class, args);
	}
}
