package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.common.CashWordConverter;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.IncomeTaxReportService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;
import com.mahait.gov.in.service.RevenueStampReportService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddoast")
@Controller
public class IncomeTaxReportController  extends BaseController {
	
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	RegularReportService regularReportService;
	
	
	@Autowired
	IncomeTaxReportService incomeTaxReportService;
	
	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@Autowired
	RevenueStampReportService revenueStampReportService;
	
	@GetMapping("/incomeTax")
	public String incomeTax(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel,
			Model model, Locale locale, HttpSession session) {
		String message = (String) model.asMap().get("message");
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstBillDesc", regularReportService.lstBillDesc(messages.getDdoCode()));
		addMenuAndSubMenu(model,messages);
		
		return "/views/reports/income-tax";
	}
	@PostMapping("/incomeTaxReport")
	public String incomeTaxReport(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel,
			Model model, Locale locale, HttpSession session) {
		
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat sdf1 =new SimpleDateFormat("dd/MM/yyyy");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		addMenuAndSubMenu(model,messages);
		
		String curryear=null;
		Integer month=regularReportModel.getMonthId();
		Integer year=regularReportModel.getYearId();
		
		List<Object[]>  yearinfo = commonHomeMethodsService.findyearinfo(BigInteger.valueOf(year));
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[9].toString();
			
		}
		BigInteger monthId = BigInteger.valueOf(regularReportModel.getMonthId());
        
        
		String monname="";
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(monthId);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();
			
		}
		
		List<RegularReportModel> empDtls = incomeTaxReportService.findIncomeTaxDtls(regularReportModel.getYearId(),regularReportModel.getMonthId(),
				regularReportModel.getBillGroup(),messages.getDdoCode());
		
		double totalDeduc = empDtls.stream().mapToDouble(RegularReportModel:: getIncometax).sum();
		
		String lvl2ddoname=null;
		String lvl2ddoCode=null;
		String tanNo=null;
		List<Object[]> ddoLvl2 =revenueStampReportService.getddoLvl2byddo(messages.getDdoCode());
		for (Object[] objects : ddoLvl2) {
			lvl2ddoCode = (String) objects[0];
			lvl2ddoname = (String) objects[1];
			tanNo = (String) objects[2];
		}
		
		BigInteger trsyCode =null;
		String trsyName=null;
		List<Object[]>  treasuryDtls = regularReportService.findTrsyDtls(messages.getDdoCode());
		
		for (Object[] objects : treasuryDtls) {
			
			trsyCode = (BigInteger) objects[0];
			trsyName = (String) objects[1];
			
		}
		model.addAttribute("trsyCode",trsyCode);
		model.addAttribute("trsyName",trsyName);
		String officename =commonHomeMethodsService.getOffice(messages.getDdoCode());
		model.addAttribute("totalDeduc", totalDeduc);
		model.addAttribute("empDtls", empDtls);
		model.addAttribute("lvl2ddoCode", lvl2ddoCode);
		model.addAttribute("lvl2ddoname", lvl2ddoname);
		model.addAttribute("officename", officename);
		model.addAttribute("tanNo", tanNo);
		model.addAttribute("ddoCode", messages.getDdoCode());
		model.addAttribute("currmonyer", monname+" "+curryear);
		String word=CashWordConverter.doubleConvert(totalDeduc);
		model.addAttribute("word", word);
		
		return "/views/reports/income-tax-report";
	}
}
