package com.mahait.gov.in.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.UserLoginHistryService;
import com.mahait.gov.in.service.UserService;
import com.mahait.gov.in.service.WelcomeService;

import jakarta.servlet.http.HttpServletRequest;

public class WelcomeController {
	
	

@Controller
//@EnableJdbcHttpSession
@RequestMapping("/")
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

	@RequestMapping("/")
	public String login(Locale locale, HttpServletRequest request,Model model ) throws UnknownHostException {
		int id = 1;
		//ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("lstApplicationAdminTable", welcomeService.findApplicationOnebyId(id));
	//	model.addAttribute("custom-login");
		// Port
		String portNumber = environment.getProperty("server.port");
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		String hostName = InetAddress.getLocalHost().getHostName();
		InetAddress.getLoopbackAddress().getHostAddress();
		InetAddress.getLoopbackAddress().getHostName();

		return "custom-login";
	}

}
}
