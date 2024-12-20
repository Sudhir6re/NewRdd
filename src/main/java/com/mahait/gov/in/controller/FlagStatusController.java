package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.FlagStatusModel;
import com.mahait.gov.in.service.FlagStatusService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/mdc")
@Controller
public class FlagStatusController extends BaseController {
	@Autowired
	FlagStatusService flagStatusService;

	@GetMapping("/statusFlag")
	public String statusFlag(Model model, Locale locale, HttpSession session,
			@ModelAttribute("flagStatusModel") FlagStatusModel flagStatusModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		
		System.out.println("billDesc=====>" + flagStatusModel.getBillDescription());
		model.addAttribute("flagStatusModel", flagStatusModel);
		return "/views/flag-status";
	}

}
