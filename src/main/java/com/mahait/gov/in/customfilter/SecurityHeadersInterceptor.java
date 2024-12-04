package com.mahait.gov.in.customfilter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityHeadersInterceptor implements HandlerInterceptor {

	public final static String MY_COOKIE_NAME = "HTEUSER";

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		response.setHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains");
		response.setHeader("X-Content-Type-Options", "nosniff");
		response.setHeader("X-Frame-Options", "DENY");
		response.setHeader("X-XSS-Protection", "1; mode=block");
		response.setHeader("Referrer-Policy", "no-referrer");
		response.setHeader("Permissions-Policy", "FEATURE ORIGIN");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Content-Security-Policy",
				"connect-src 'self' font-src 'self'  img-src 'self'  default-src  'self' style-src 'self' *.https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css http://code.jquery.com/ui/1.8.3/themes/base/jquery-ui.css http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css   'unsafe-inline' *.https://unpkg.com/sweetalert/dist/sweetalert.min.js http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js http://code.jquery.com/ui/1.8.3/jquery-ui.js;");

		//super.postHandle(request, response, handler, modelAndView);
	}

}
