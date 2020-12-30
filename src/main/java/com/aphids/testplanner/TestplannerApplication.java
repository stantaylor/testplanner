package com.aphids.testplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TestplannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestplannerApplication.class, args);
	}

}
