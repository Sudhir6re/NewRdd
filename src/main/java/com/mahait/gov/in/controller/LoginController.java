package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ChangePasswordModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.UserService;
import com.mahait.gov.in.service.WelcomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
//@EnableJdbcHttpSession
public class LoginController extends BaseController{

	// private final Logger logger=LoggerFactory.getLogger(getClass());
	// private static final Logger logger = (Logger)
	// LogManager.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	WelcomeService welcomeService;
	
	
	//@Autowired
	//UserSessionObject  userSessionObject;

	/* Operator Home Page */
	@RequestMapping("/ddoast/home")
	public ModelAndView getAllUserTopics(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		/* modelAndView.addObject("usertopics", topicService.getAllTopics()); */
		modelAndView.addObject("language", locale.getLanguage());

		/* Setting User Session in an application */
		/*
		 * logger.info(">>>>>ApppCode : " +request.getSession().getAttribute("appCode")
		 * );
		 */
		request.getSession().setAttribute("MY_SESSION_MESSAGES",
				userService.getUserIdbyUserName(request.getRemoteUser()));
		/*
		 * request.getSession().setAttribute("MY_SESSION_MESSAGES",
		 * userDetailsServiceImpl.getUserIdbyUserName(request.getRemoteUser(),
		 * applicationAdminMstEntity.getApplicationCode()));
		 */
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		session.setAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		List<Object[]> retriveUserdetails = commonHomeMethodsService.retriveUserdetails(messages.getUserId());
		if (retriveUserdetails.size() > 0) {
			for (Object[] obj : retriveUserdetails) {
				session.setAttribute("ddoCode", obj[0]);
				session.setAttribute("locationId", obj[1]);
				session.setAttribute("loggedInPost", obj[2]);
				
				messages.setLocId(Long.valueOf(obj[1].toString()));
				messages.setPostId(Long.valueOf(obj[2].toString()));
				
				request.getSession().setAttribute("MY_SESSION_MESSAGES",messages);
				
			}
		}
		
		addMenuAndSubMenu(modelAndView,messages);
		
		
		modelAndView.addObject("sessionMessages", messages.getMstRoleEntity().getRoleId());
		// logger.info(""+messages.getFullName());
		modelAndView.addObject("userName", messages.getUserName());
		int levelRoleVal = messages.getMstRoleEntity().getRoleId();

		//userSessionObject.setSession(messages.getUserId(),session);
		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());
/*
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();

		menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal, locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal, locale.getLanguage());

		modelAndView.addObject("menuList", menuList);
		modelAndView.addObject("subMenuList", subMenuList);
*/
		modelAndView.addObject("levelRoleVal", levelRoleVal);

		if (messages.getUpdatedDate() == null) {
			modelAndView.addObject("changePasswordModel", new ChangePasswordModel());
			modelAndView.setViewName("redirect:/ddoast/changePassword");
		} else {

			modelAndView.setViewName("topics");
		}

		return modelAndView;

	}

	/* Moderator Home Page */

	@RequestMapping("/ddo/home")
	public ModelAndView getModeratorLogin(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		/* modelAndView.addObject("usertopics", topicService.getAllTopics()); */
		modelAndView.addObject("language", locale.getLanguage());

		/* Setting User Session in an application */
		/*
		 * logger.info(">>>>>ApppCode : " +request.getSession().getAttribute("appCode")
		 * );
		 */
		request.getSession().setAttribute("MY_SESSION_MESSAGES",
				userService.getUserIdbyUserName(request.getRemoteUser()));

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		List<Object[]> retriveUserdetails = commonHomeMethodsService.retriveUserdetails(messages.getUserId());
		if (retriveUserdetails.size() > 0) {
			for (Object[] obj : retriveUserdetails) {
				session.setAttribute("ddoCode", obj[0]);
				session.setAttribute("locationId", obj[1]);
				session.setAttribute("loggedInPost", obj[2]);
				
				messages.setLocId(Long.valueOf(obj[1].toString()));
				messages.setPostId(Long.valueOf(obj[2].toString()));
				
				request.getSession().setAttribute("MY_SESSION_MESSAGES",messages);
			}
		}
		
		addMenuAndSubMenu(modelAndView,messages);
		
		//userSessionObject.setSession(messages.getUserId(),session);
		if (messages.getUpdatedDate() == null) {
			modelAndView.setViewName("redirect:/ddo/changePassword");
		} else {
			modelAndView.setViewName("topics");
		}

		return modelAndView;

	}
	

	@RequestMapping("/user/home")
	public ModelAndView userHomePage(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("language", locale.getLanguage());
		request.getSession().setAttribute("MY_SESSION_MESSAGES",
				userService.getUserIdbyUserName(request.getRemoteUser()));
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("sessionMessages", messages.getUserId());
		// logger.info(""+messages.getFullName());
		modelAndView.addObject("userName", messages.getUserName());
		int levelRoleVal = messages.getMstRoleEntity().getRoleId();
		if (levelRoleVal==1) {
			return new ModelAndView("redirect:/mdc/home");
		} else if (levelRoleVal==3) {
			return new ModelAndView("redirect:/ddoast/home");
		} else if (levelRoleVal==2) {
			return new ModelAndView("redirect:/ddo/home");
		}else if (levelRoleVal==4) {

			addMenuAndSubMenu(modelAndView, messages);
			modelAndView.setViewName("topics");
			//return new ModelAndView("redirect:/user/home");
		}else if (levelRoleVal==5) {
			return new ModelAndView("redirect:/super/home");
		} else {

			addMenuAndSubMenu(modelAndView, messages);

			modelAndView.setViewName("homepageLevel");
		}
		return modelAndView;

	}

	

	/* Super Admin Home Page */
	@RequestMapping("/super/home")
	public ModelAndView getSuperHomePage(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("language", locale.getLanguage());
		request.getSession().setAttribute("MY_SESSION_MESSAGES",
				userService.getUserIdbyUserName(request.getRemoteUser()));
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(modelAndView,messages);
		modelAndView.setViewName("topics");
		return modelAndView;
	}


}