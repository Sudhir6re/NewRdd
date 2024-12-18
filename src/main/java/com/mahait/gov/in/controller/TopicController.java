package com.mahait.gov.in.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
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

@RestController
//@EnableJdbcHttpSession
@RequestMapping("/user")
public class TopicController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	WelcomeService welcomeService;

	@Autowired
	private Environment environment;

	@Autowired
	UserLoginHistryService userLoginHistryService;

	@RequestMapping("/login")
	public ModelAndView login(Locale locale, HttpServletRequest request) throws UnknownHostException {
		int id = 1;
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("lstApplicationAdminTable", welcomeService.findApplicationOnebyId(id));
		modelAndView.setViewName("custom-login");

		// Port
		String portNumber = environment.getProperty("server.port");
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		String hostName = InetAddress.getLocalHost().getHostName();
		InetAddress.getLoopbackAddress().getHostAddress();
		InetAddress.getLoopbackAddress().getHostName();

		return modelAndView;
	}

	
	@CacheEvict(value = {"menus", "submenus"}, allEntries = true)
	@RequestMapping("/logOut")
	public RedirectView logOut(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
		RedirectView redirectView = new RedirectView("/user/login?logout");
		redirectView.setContextRelative(true); // Ensures the context path is preserved
		return redirectView;
	}

	@RequestMapping("/error")
	public ModelAndView error(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		modelAndView.addObject("errorMsg", errorMessage);
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		modelAndView.addObject("levelRoleVal", messages.getMstRoleEntity().getRoleId());
		String sessionExpMessage = "Session Expired, Please click below link to login again !!!";
		modelAndView.addObject("sessionExpMessageMsg", sessionExpMessage);

		String url = "";

		switch (messages.getMstRoleEntity().getRoleId()) {
		case 1:
			url = "/mdc/home";
			break;
		case 2:
			url = "/ddo/home";
			break;
		case 3:
			url = "/ddoast/home";
			break;
		case 4:
			url = "/user/home";
			break;
		case 5:
			url = "/super/home";
			break;
		}

		modelAndView.addObject("url", url);
		modelAndView.setViewName("error");
		return modelAndView;

	}

	@RequestMapping("/underConstruction")
	public ModelAndView underConstruction(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String underConstMessage = "This Page Is Under Constuction !!!";
		modelAndView.addObject("underConstructionMsg", underConstMessage);
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		modelAndView.addObject("levelRoleVal", messages.getMstRoleEntity().getRoleId());

		String url = "";

		switch (messages.getMstRoleEntity().getRoleId()) {
		case 1:
			url = "/mdc/home";
			break;
		case 2:
			url = "/ddo/home";
			break;
		case 3:
			url = "/ddoast/home";
			break;
		case 4:
			url = "/user/home";
			break;
		case 5:
			url = "/super/home";
			break;
		}

		modelAndView.addObject("url", url);

		modelAndView.setViewName("under-construction");
		return modelAndView;
	}

	@RequestMapping("/invalidsession")
	public ModelAndView invalidsession(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String sessionExpMessage = "Session Expired, Please click below link to login again !!!";
		modelAndView.addObject("sessionExpMessageMsg", sessionExpMessage);
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		modelAndView.addObject("levelRoleVal", messages.getMstRoleEntity().getRoleId());

		String url = "";

		switch (messages.getMstRoleEntity().getRoleId()) {
		case 1:
			url = "/mdc/home";
			break;
		case 2:
			url = "/ddo/home";
			break;
		case 3:
			url = "/ddoast/home";
			break;
		case 4:
			url = "/user/home";
			break;
		case 5:
			url = "/super/home";
			break;
		}

		modelAndView.addObject("url", url);

		modelAndView.setViewName("invalid-session");
		return modelAndView;
	}
}