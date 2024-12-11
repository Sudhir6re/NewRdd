package com.mahait.gov.in.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mahait.gov.in.service.WelcomeService;

@Aspect
@Component
public class ApplicationAspect {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	WelcomeService welcomeService;
	
	@Before(value = "execution(* com.mahait.gov.in.controller.*..*(..)) ") 
	public void beforeAdvice(JoinPoint joinPoint) {  
		welcomeService.idleTerminateConnectivity();
	}
	
	
}