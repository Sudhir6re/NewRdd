package com.mahait.gov.in.common;

import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CommonUtils {

	public static Model initModel(String message,String errorCode ,Model model) {
		 model.addAttribute("message" , message );
		 model.addAttribute("errorCode" , errorCode );
		 return model;
	}
	
	
	

	 public static String getRequestIpAddress() {
	        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        if (requestAttributes != null) {
	            return requestAttributes.getRequest().getRemoteAddr();
	        }
	        return "N/A";
	    }
	 
}
