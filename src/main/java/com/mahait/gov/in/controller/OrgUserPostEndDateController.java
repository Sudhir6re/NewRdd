package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.OrgUserPostEndDateModel;
import com.mahait.gov.in.service.OrgUserPostEndDateService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrgUserPostEndDateController  extends BaseController{

	@Autowired
	OrgUserPostEndDateService orgUserPostEndDateService;
	
	
	@GetMapping("/orgUserPostEndDate")
	public String OrgUserPostEndDate(@ModelAttribute("orgUserPostEndDateModel") OrgUserPostEndDateModel orgUserPostEndDateModel,
			 Model model, Locale locale,
			HttpSession session) {
		String message = (String) model.asMap().get("message");
		model.addAttribute("orgUserPostEndDateModel", orgUserPostEndDateModel);
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("language", locale.getLanguage());
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);

		return "/views/mst-OrgUserPostEndDate";
	}
}
