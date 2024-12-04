package com.mahait.gov.in.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mahait.gov.in.service.CommonHomeMethodsService;

import jakarta.servlet.http.HttpSession;

//@Component
public class UserSessionObject {

	@Autowired
	private CommonHomeMethodsService commonHomeMethodsService;

	public  void setSession(Long userId, HttpSession session) {
		HashMap<String, Object> baseLoginMap = new HashMap<String, Object>();
		
		Map<String, Object> objectArgs = new HashMap<>();

		
	}

}
