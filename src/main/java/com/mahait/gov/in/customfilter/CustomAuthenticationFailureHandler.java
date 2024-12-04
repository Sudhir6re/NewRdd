package com.mahait.gov.in.customfilter;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.UserLoginHistryEntity;
import com.mahait.gov.in.service.LimitLoginService;
import com.mahait.gov.in.service.UserLoginHistryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Autowired
	UserLoginHistryService userLoginHistryService;

	@Autowired
	LimitLoginService limitLoginService;

	public static final int MAX_FAILED_ATTEMPTS = 3;

	public static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public CustomAuthenticationFailureHandler() {
		super();
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String ip = InetAddress.getLocalHost().getHostAddress();

		UserLoginHistryEntity userLoginHistryEntity = new UserLoginHistryEntity();

		String username = request.getParameter("username");

		userLoginHistryEntity.setUsername(username);
		userLoginHistryEntity.setLoginIp(ip);
		userLoginHistryEntity.setCreatedDate(new Date());
		userLoginHistryEntity.setLoginDate(new Date());
		// UserLoginHistryEntity.setUpdatedDate(new Date());
		// UserLoginHistryEntity.setUpdatedUserId(1);
		userLoginHistryEntity.setCreatedUserId(1);
		userLoginHistryEntity.setIsActive(2);
		userLoginHistryEntity.setStatus("Login failure");
		userLoginHistryService.saveLoginDtls(userLoginHistryEntity);

		userLoginHistryService.saveLoginDtls(userLoginHistryEntity);

		/*
		 * OrgUserMst user = limitLoginService.findUserbyUsername(username); if (user !=
		 * null) { if (user.getActivateFlag() == 1l) { if (user.getInvalidLoginCnt() <
		 * LimitLoginService.MAX_FAILED_ATTEMPTS - 1) {
		 * limitLoginService.increaseFailedAttempts(user); } else { //
		 * user.setFailedAttempt(user.getFailedAttempt()+1);
		 * limitLoginService.lock(user); exception = new
		 * LockedException("Your account has been locked due to 3 failed attempts." +
		 * " It will be unlocked after 24 hours.");
		 * 
		 * if (exception instanceof LockedException) { request.setAttribute("locked",
		 * true); redirectStrategy.sendRedirect(request, response,
		 * "/user/login?locked=true"); } } } else if (user.getActivateFlag() == 0l) { if
		 * (limitLoginService.unlockWhenTimeExpired(user)) { exception = new
		 * LockedException("Your account has been unlocked. Please try to login again."
		 * ); } } }
		 * 
		 * if (exception instanceof LockedException) { request.setAttribute("locked",
		 * true); redirectStrategy.sendRedirect(request, response,
		 * "/user/login?locked=true"); }else{ redirectStrategy.sendRedirect(request,
		 * response, "/user/login?error=true"); }
		 */
		
		redirectStrategy.sendRedirect(request, response, "/user/login?error=true");
		// return new SimpleUrlAuthenticationFailureHandler("/user/login?error=true") ;
	}

}
