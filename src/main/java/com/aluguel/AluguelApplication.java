package com.aluguel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AluguelApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluguelApplication.class, args);
	}
}
