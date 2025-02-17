package com.mahait.gov.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;




//@EnableAutoConfiguration
//@EnableAspectJAutoProxy(proxyTargetClass=true)  
@EnableCaching  
//@ComponentScan("com.mahait.gov.in")
//@EntityScan(basePackages = { "com.mahait.gov.in.entity","com.mahait.gov.in.nps.entity" })
@SpringBootApplication
public class Rdd1Application {


	public static void main(String[] args) {
		SpringApplication.run(Rdd1Application.class, args);
	}
	
	
}
