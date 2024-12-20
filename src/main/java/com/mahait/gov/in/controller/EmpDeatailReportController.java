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
public class EmpDeatailReportController  extends BaseController{
	@GetMapping("/empDeatail")
	public String empDeatail(Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/reports/employee-details";
	}
	@GetMapping("/empDeatailReport")
	public String empDeatailReport(Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/reports/employee-details-report";
	}
}
