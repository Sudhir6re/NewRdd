package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.BulkAllowancesModel;
import com.mahait.gov.in.service.BulkAllowancesService;
import com.mahait.gov.in.service.CommonHomeMethodsService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddoast")
public class BulkAllowancesController  extends BaseController{
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	BulkAllowancesService bulkAllowancesService;
	
	@GetMapping("/bulkAllowances")
	public String BulkAllowances(@ModelAttribute("bulkAllowancesModel") BulkAllowancesModel bulkAllowancesModel,
			 Model model, Locale locale,
			HttpSession session) {
		String message = (String) model.asMap().get("message");
		model.addAttribute("bulkAllowancesModel", bulkAllowancesModel);
		model.addAttribute("language", locale.getLanguage());
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);	
		return "/views/mst-bulkAllowance";
	}

}
