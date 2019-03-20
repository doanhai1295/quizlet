package com.quizlet.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.quizlet.web.app", "com.quizlet.core.app" })
@EnableJpaRepositories("com.quizlet.core.app.repository")
@EntityScan("com.quizlet.core.app.entity")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
