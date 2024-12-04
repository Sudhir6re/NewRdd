package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.UserLoginHistryService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/mdc")
@Controller
public class AuditTrialController  extends BaseController{
	@Autowired
	UserLoginHistryService userLoginHistryService;
	
	@GetMapping("/auditTrial")
	public String auditTrial(Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);	
		
		model.addAttribute("lstdata", userLoginHistryService.findlogindata());
		model.addAttribute("language", locale.getLanguage());
		
		
		return "/views/audit-trial";
	}

}
