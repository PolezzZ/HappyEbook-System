package com.polezz.ebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.polezz.ebook.mapper")
public class HappyEBookSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyEBookSystemApplication.class, args);
	}
}
