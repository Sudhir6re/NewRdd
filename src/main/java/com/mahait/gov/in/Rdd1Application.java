package com.mahait.gov.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;




//@EnableAutoConfiguration
//@EnableAspectJAutoProxy(proxyTargetClass=true)  
@EnableCaching  
//@ComponentScan("com.mahait.gov.in")
//@EntityScan(basePackages = { "com.mahait.gov.in.entity","com.mahait.gov.in.nps.entity" })
@SpringBootApplication
public class Rdd1Application {

	@Autowired
	private static Environment environment;

	
	public static void main(String[] args) {
		SpringApplication.run(Rdd1Application.class, args);
		printActiveProfiles();
	}
	
	
	public static void printActiveProfiles() {
		// Get the active profiles
		String[] activeProfiles = environment.getActiveProfiles();
		for (String profile : activeProfiles) {
			System.out.println("Active profile: " + profile);
		}
	}

}
