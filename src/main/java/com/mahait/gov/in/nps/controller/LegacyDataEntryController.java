package com.mahait.gov.in.nps.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.controller.BaseController;
import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/ddo")
@Controller
public class LegacyDataEntryController extends BaseController {
	

	@GetMapping("/dcpsLegacyEntry")
	public String dcpsLegacyEntry( Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/entry-dcps-legacy";
	}
	



}
