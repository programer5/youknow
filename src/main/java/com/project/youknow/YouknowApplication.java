package com.project.youknow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YouknowApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouknowApplication.class, args);
	}

}
