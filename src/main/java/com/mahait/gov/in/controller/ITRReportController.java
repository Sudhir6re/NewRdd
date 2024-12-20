package com.mahait.gov.in.controller;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddoast")
@Controller
public class ITRReportController {
	
	@GetMapping("/itr")
	public String itr(Model model, Locale locale, HttpSession session) {
		return "/views/itr-report";
	}
}