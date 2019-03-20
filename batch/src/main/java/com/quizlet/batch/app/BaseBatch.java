package com.quizlet.batch.app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BaseBatch implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Configuration
	public static class BatchApplicationConfig{
		@Bean
		@ConditionalOnProperty(value = { "batch.execute" }, havingValue = "base")
        public BaseBatch BatchRunner() {
            return new BaseBatch();
        }

	}
}
