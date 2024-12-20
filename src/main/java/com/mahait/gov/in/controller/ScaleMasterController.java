package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/mdc")
@Controller
public class ScaleMasterController  extends BaseController {
	@GetMapping("/scaleMaster")
	public String scaleMaster(Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/scale-master";
	}

	@GetMapping("/addScale")
	public String addScale(Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/add-scale";
	}

}
