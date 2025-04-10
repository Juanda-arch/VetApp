package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.example.demo.domain",
		"com.example.demo.infrastructure.mapper",
		"com.example.demo.infrastructure.entity",
		"com.example.demo.infrastructure.repository",
		"com.example.demo.infrastructure.controller",
		"com.example.demo.web"
})
public class VetAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetAppApplication.class, args);
	}

}
