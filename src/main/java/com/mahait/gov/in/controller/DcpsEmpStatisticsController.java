package com.mahait.gov.in.controller;

import org.apache.xmlbeans.impl.store.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/level3")
@Controller
public class DcpsEmpStatisticsController extends BaseController {
	@GetMapping("/dcpsEmpStatistics")
	public String dcpsEmpStatistics(Model model, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		return "/views/reports/dcps-emp-stat";
	}
}
