package com.jb.CouponsProjectPart2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponsProjectPart2Application {

	// http://localhost:8080/swagger-ui.html
	public static void main(String[] args) {
		SpringApplication.run(CouponsProjectPart2Application.class, args);
		System.out.println("Application Done !");

	}

}
