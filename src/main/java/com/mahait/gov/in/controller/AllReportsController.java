package com.mahait.gov.in.controller;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/ddoast")
@Controller
public class AllReportsController  extends BaseController {
	@GetMapping("/allReports")
	public String allReports( Model model, Locale locale,
			HttpSession session) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);		
		return "/views/all-reports";
	}

}