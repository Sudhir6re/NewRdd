package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PaybillGenerationReportModel;
import com.mahait.gov.in.service.PaybillGenerationReportService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/mdc")
@Controller
public class PaybillGenerationReportController extends BaseController {
	
	@Autowired 
	PaybillGenerationReportService paybillGenerationReportService;
	

	@GetMapping("/paybillGeneration")
	public String paybillGeneration(Model model, Locale locale, HttpSession session,
			@ModelAttribute("paybillGenerationReportModel") PaybillGenerationReportModel paybillGenerationReportModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		model.addAttribute("paybillGenerationReportModel", paybillGenerationReportModel);
		model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());
		return "/views/paybill-generation-report";
	}

	@PostMapping("/treasuryDDOWiseReport")
	public String treasuryDDOWiseReport(Model model, Locale locale, HttpSession session,
			@ModelAttribute("paybillGenerationReportModel") PaybillGenerationReportModel paybillGenerationReportModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		System.out.println("month=====>" + paybillGenerationReportModel.getMonth());
		System.out.println("year=====>" + paybillGenerationReportModel.getYear());
		System.out.println("region=====>" + paybillGenerationReportModel.getRegion());
		
		List<PaybillGenerationReportModel> lst = paybillGenerationReportService.findRegionWiseData(paybillGenerationReportModel,messages);

		model.addAttribute("lst", lst);
		addMenuAndSubMenu(model, messages);
		return "/views/reports/treasury-ddo-wise-report";
	}

}
