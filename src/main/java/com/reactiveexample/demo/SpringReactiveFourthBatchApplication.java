package com.reactiveexample.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@EnableMongoAuditing

@EnableScheduling
@SpringBootApplication
//@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class })
public class SpringReactiveFourthBatchApplication {
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		/*
		 * System.setProperty("javax.xml.bind.JAXBContextFactory",
		 * "com.sun.xml.bind.v2.ContextFactory");
		 */
		SpringApplication.run(SpringReactiveFourthBatchApplication.class, args);
	}
}