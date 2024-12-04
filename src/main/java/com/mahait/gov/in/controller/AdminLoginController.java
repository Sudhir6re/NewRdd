package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.UserService;
import com.mahait.gov.in.service.WelcomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
//@EnableJdbcHttpSession
@RequestMapping("/mdc")
public class AdminLoginController   extends BaseController{

	// private final Logger logger=LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	WelcomeService welcomeService;
	
	
	
	//@Autowired
//	UserSessionObject  userSessionObject;

	@RequestMapping("/home")
	public ModelAndView getAllUserTopics(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("language", locale.getLanguage());
		request.getSession().setAttribute("MY_SESSION_MESSAGES",userService.getUserIdbyUserName(request.getRemoteUser()));
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> retriveUserdetails = commonHomeMethodsService.retriveUserdetails(messages.getUserId());
		if (retriveUserdetails.size() > 0) {
			for (Object[] obj : retriveUserdetails) {
				session.setAttribute("ddoCode", obj[0]);
				session.setAttribute("locationId", obj[1]);
				session.setAttribute("loggedInPost", obj[2]);
			}
		}
		
		
		addMenuAndSubMenu(modelAndView,messages);
	//	userSessionObject.setSession(messages.getUserId(),session);
		
		modelAndView.addObject("sessionMessages", messages.getUserId());
		modelAndView.addObject("userName", messages.getUserName());
		Integer levelRoleVal = messages.getMstRoleEntity().getRoleId();
/*
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();

		menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal, locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal, locale.getLanguage());

		model.addAttribute("menuList", menuList);
		model.addAttribute("subMenuList", subMenuList);*/

		model.addAttribute("levelRoleVal", levelRoleVal);

		modelAndView.setViewName("topics");

		return modelAndView;
	}
}