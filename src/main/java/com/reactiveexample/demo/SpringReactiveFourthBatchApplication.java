package com.reactiveexample.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@EnableMongoAuditing
@SpringBootApplication
public class SpringReactiveFourthBatchApplication {
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveFourthBatchApplication.class, args);
	}
}