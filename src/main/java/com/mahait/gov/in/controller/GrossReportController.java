package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.GrossReportModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.GrossReportService;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/mdc")
@Controller
public class GrossReportController  extends BaseController{
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	
	@Autowired
	GrossReportService grossReportService;

	@GetMapping("/grossReportsearch")
	public String grossReportsearch(Model model, Locale locale, HttpSession session,@ModelAttribute ("grossReportModel") GrossReportModel grossReportModel ) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("dptLst", grossReportService.lstGetDeptLst());
		
		return "/views/gross-report-search";
	}
	@RequestMapping("/grossReport")
	public String grossReport(Model model, Locale locale, HttpSession session,@ModelAttribute ("grossReportModel") GrossReportModel grossReportModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		
		
		List<GrossReportModel> grossReport = grossReportService.grossReport(grossReportModel.getYearId(),grossReportModel.getLocId());
		model.addAttribute("grossReport", grossReport);
		return "/views/reports/gross-report";
	}
}
