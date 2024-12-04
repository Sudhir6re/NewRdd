package com.mahait.gov.in.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.UserLoginHistryEntity;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.UserLoginHistryService;
import com.mahait.gov.in.service.UserService;
import com.mahait.gov.in.service.WelcomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


//@RequestMapping(value = { "/ddoast", "/ddo","/mdc","/super","/user","/level3","/level4" })
//@Controller
public class LogoutController {


	@Autowired
	UserLoginHistryService userLoginHistryService;
	
	@RequestMapping("/logOut")
	public String logOut(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		
			UserLoginHistryEntity userLoginHistry = new UserLoginHistryEntity();
			userLoginHistry.setUsername(messages.getUserName());
			userLoginHistry.setLoginIp(ip);
			userLoginHistry.setCreatedDate(new Date());
			userLoginHistry.setLoginDate(new Date());
			userLoginHistry.setCreatedUserId(1);
			userLoginHistry.setIsActive(3);
			userLoginHistry.setStatus("Logout Successful");
			userLoginHistryService.saveLoginDtls(userLoginHistry);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if (session != null) {
			session.invalidate();
		}

		/*
		 * switch(messages.getMstRoleEntity().getRoleId()) { case 1: return
		 * "redirect:/mdc/login?logout"; case 2: return "redirect:/ddo/login?logout";
		 * case 3: return "redirect:/ddoast/login?logout"; case 4: return
		 * "redirect:/user/login?logout"; case 5: return "redirect:/super/login?logout";
		 * case 6: return "redirect:/mdp/login?logout"; case 7: return
		 * "redirect:/level3/login?logout"; case 8: return
		 * "redirect:/level4/login?logout"; }
		 */
		return "redirect:/user/login?logout";
	}
    
    
}
