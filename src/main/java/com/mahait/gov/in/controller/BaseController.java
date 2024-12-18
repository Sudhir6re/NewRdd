package com.mahait.gov.in.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseController {

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	private HttpServletRequest request; // Inject HttpServletRequest

	protected void addMenuAndSubMenu(ModelAndView modelAndView, OrgUserMst messages) {
		Integer levelRoleVal = messages.getMstRoleEntity().getRoleId();
		if (levelRoleVal != null && levelRoleVal != 0) {
			List<TopicModel> menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal, "en");
			List<TopicModel> subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal, "en");

			if (modelAndView != null) {
				modelAndView.addObject("menuList", menuList);
				modelAndView.addObject("subMenuList", subMenuList);
			}
		}
		modelAndView.addObject("levelRoleVal", levelRoleVal);
		modelAndView.addObject("today", new Date());
		modelAndView.addObject("appRootPath", getAppRootPath());
		modelAndView.addObject("request", request);
		modelAndView.addObject("session", request.getSession());
		modelAndView.addObject("servletContext", request.getServletContext());
		modelAndView.addObject("remoteUser", request.getRemoteUser());
		modelAndView.addObject("userUrl", CommonConstants.getUserWiseUrl(messages));
		if(messages!=null) {
		modelAndView.addObject("userName",messages.getUserName());
		}
	}

	protected void addMenuAndSubMenu(Model model, OrgUserMst messages) {
		Integer levelRoleVal = messages.getMstRoleEntity().getRoleId();
		if (levelRoleVal != null && levelRoleVal != 0) {
			List<TopicModel> menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal, "en");
			List<TopicModel> subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal, "en");
			model.addAttribute("menuList", menuList);
			model.addAttribute("subMenuList", subMenuList);
		}
		model.addAttribute("levelRoleVal", levelRoleVal);
		model.addAttribute("today", new Date());
		model.addAttribute("appRootPath", getAppRootPath());
		model.addAttribute("request", getAppRootPath());
		model.addAttribute("session", getAppRootPath());
		model.addAttribute("servletContext", getAppRootPath());

		model.addAttribute("request", request);
		model.addAttribute("session", request.getSession());
		model.addAttribute("servletContext", request.getServletContext());
		model.addAttribute("remoteUser", request.getRemoteUser());
		model.addAttribute("userUrl", CommonConstants.getUserWiseUrl(messages));
		if(messages!=null) {
			model.addAttribute("userName",messages.getUserName());
		}
	}

	private String getAppRootPath() {
		ServletContext servletContext = request.getServletContext();
		String appRootPath = servletContext.getContextPath();
		// Determine the scheme (http or https)
		String scheme = request.getScheme(); // "http" or "https"
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();

		// If context path is empty, build the app root path
		if (appRootPath.isEmpty()) {
			appRootPath = scheme + "://" + serverName + (serverPort == 80 || serverPort == 443 ? "" : ":" + serverPort);
		}
		return appRootPath;
	}
}
