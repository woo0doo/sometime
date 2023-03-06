package com.example.sometime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // BaseTimeEntity 때문에 넣음
public class SometimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SometimeApplication.class, args);
	}

}
