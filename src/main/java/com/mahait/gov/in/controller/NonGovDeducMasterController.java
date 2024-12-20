package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.NonGovDeducMasterModel;
import com.mahait.gov.in.service.NonGovDeducMasterService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddoast")
public class NonGovDeducMasterController  extends BaseController {
	
	
	@Autowired
	NonGovDeducMasterService nonGovDeducMasterService;
	
	@GetMapping("/getNonGovDeduction")
	public String getNonGovDeduction(@ModelAttribute("nonGovDeducMasterModel") NonGovDeducMasterModel nonGovDeducMasterModel,
			 Model model, Locale locale,
			HttpSession session) {
		String message = (String) model.asMap().get("message");
		model.addAttribute("nonGovDeducMasterModel", nonGovDeducMasterModel);
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("language", locale.getLanguage());
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/mst-NonGovDeduction";
	}

}
