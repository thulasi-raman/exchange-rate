package com.exrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
//@ComponentScan(basePackages={"com.exrate","com.exrate.repository"})
//@EnableJpaRepositories
public class ExRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExRateApplication.class, args);
	}
}
