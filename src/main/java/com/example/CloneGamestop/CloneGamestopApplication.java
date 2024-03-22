package com.example.CloneGamestop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.CloneGamestop")
public class CloneGamestopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloneGamestopApplication.class, args);
	}
}
