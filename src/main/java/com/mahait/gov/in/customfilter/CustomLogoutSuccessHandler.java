package com.mahait.gov.in.customfilter;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.mahait.gov.in.entity.UserLoginHistryEntity;
import com.mahait.gov.in.service.UserLoginHistryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Autowired
	UserLoginHistryService userLoginHistryService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		//User user = (User) authentication.getPrincipal();
		
		Object principal = authentication.getPrincipal();
	    String username;
	    
	    if (principal instanceof org.springframework.security.core.userdetails.User) {
	        org.springframework.security.core.userdetails.User user = 
	            (org.springframework.security.core.userdetails.User) principal;
	        username = user.getUsername();
	    } else if (principal instanceof String) {
	        username = (String) principal;
	    } else {
	        throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
	    }

		ServerHttpRequest request1 = (ServerHttpRequest) request;
		String latestIp = request1.getRemoteAddress().toString();

		String ip = InetAddress.getLocalHost().getHostAddress();

		System.out.println("latestIp" + latestIp);
		UserLoginHistryEntity userLoginHistryEntity = new UserLoginHistryEntity();
		userLoginHistryEntity.setUsername(username);
		userLoginHistryEntity.setLoginIp(ip);
		userLoginHistryEntity.setCreatedDate(new Date());
		userLoginHistryEntity.setLoginDate(new Date());
		userLoginHistryEntity.setCreatedUserId(1);
		userLoginHistryEntity.setIsActive(1);
		userLoginHistryEntity.setStatus("Logout successful");
		userLoginHistryService.saveLoginDtls(userLoginHistryEntity);

		HttpSession session = request.getSession(false);


		if (session != null) {
			session.invalidate();
		}

		//return new RedirectView("redirect:/user/login?logout");
		redirectStrategy.sendRedirect(request, response, "/user/login?logout");

		// super.onLogoutSuccess(request, response, authentication);
	}
}