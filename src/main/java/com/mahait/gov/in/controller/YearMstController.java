package com.mahait.gov.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.MstYearEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.YearMstService;

import jakarta.servlet.http.HttpSession;



@RequestMapping("/developer")
@Controller
public class YearMstController extends BaseController {
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	
	@Autowired
	YearMstService yearMstService;
	
	
	@GetMapping("/loadYears")
	public String loadYears(@ModelAttribute("mstYearEntity") MstYearEntity mstYearEntity,Model model, HttpSession session) {
		model.addAttribute("mstYearEntity", mstYearEntity);
		List<MstYearEntity> lstMstYearEntity=yearMstService.findAllYears();
		model.addAttribute("lstMstYearEntity", lstMstYearEntity);
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		return "/views/year-mst";
	}
	
	
	@GetMapping("/findYearById/{yearId}")
	public String findYearById(@PathVariable Integer yearId,Model model, HttpSession session) {
		MstYearEntity mstYearEntity=yearMstService.findYearById(yearId);
		model.addAttribute("mstCommonEntity", mstYearEntity);
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		return "/views/edit-year-mst";
	}
	
	
	@PostMapping("/updateYear")
	public String updateYear(@ModelAttribute("mstYearEntity") MstYearEntity mstYearEntity) {
		yearMstService.updateYear(mstYearEntity);
		return "redirect:/developer/loadYears";
	}
	
	@PostMapping("/saveYear")
	public String saveYear(@ModelAttribute("mstYearEntity") MstYearEntity mstYearEntity) {
		yearMstService.saveYear(mstYearEntity);
		return "redirect:/developer/loadYears";
	}

}