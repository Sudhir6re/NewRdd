package com.mahait.gov.in.customfilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


	@Autowired
	private AuthenticationManager authenticationManager;

	public static final String SPRING_SECURITY_FORM_DOMAIN_KEY = "appCode";

	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		CustomAuthenticationToken authRequest = getAuthRequest(request);
		setDetails(request, authRequest);
		return authenticationManager.authenticate(authRequest);
	}

	private CustomAuthenticationToken getAuthRequest(HttpServletRequest request) {
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		HttpSession session = request.getSession();

		String appCode = obtainDomain(request);
		int appCode1 = Integer.parseInt(obtainDomain(request));

		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
		if (appCode == null) {
			appCode1 = 0;
		}

		return new CustomAuthenticationToken(username, password, appCode1);
	}

	private String obtainDomain(HttpServletRequest request) {
		return request.getParameter(SPRING_SECURITY_FORM_DOMAIN_KEY);
	}
}
