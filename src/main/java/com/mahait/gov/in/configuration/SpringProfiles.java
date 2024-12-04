package com.mahait.gov.in.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SpringProfiles {

	@Profile("dev")
	@Bean
	public String devBean() {
		return "dev";
	}

	@Profile("qa")
	@Bean
	public String qaBean() {
		return "qa";
	}

	@Profile("prod")
	@Bean
	public String prodBean() {
		return "prod";
	}

	@Profile("uat")
	@Bean
	public String uatBean() {
		return "uat";
	}

	@Profile("test")
	@Bean
	public String testBean() {
		return "test";
	}

	@Profile("local")
	@Bean
	public String localBean() {
		return "local";
	}

}
