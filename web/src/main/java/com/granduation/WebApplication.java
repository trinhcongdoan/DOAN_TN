package com.granduation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/* (exclude = SecurityAutoConfiguration.class) */
@SpringBootApplication
public class WebApplication implements CommandLineRunner{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(System.getProperty("user.dir"));
	}

}
