package com.mahait.gov.in.controller;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/ddo")
@Controller
public class PrintForm1DdoController  extends BaseController{
	@GetMapping("/printForm1")
	public String printForm1( Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/print-form1-ddo";
	}

}
