package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.common.CashWordConverter;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.AquittanceRollReportModel;
import com.mahait.gov.in.service.AquittanceRollReportService;
import com.mahait.gov.in.service.DisplayOuterReportService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddoast")
@Controller
public class AquittanceRollReportController  extends BaseController {
	
	@Autowired
	
	AquittanceRollReportService aquittanceRollReportService;
	@Autowired
	
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@Autowired
	RegularReportService regularReportService;
	
	@Autowired
	DisplayOuterReportService displayOuterReportService;
	
	@GetMapping("/aquittanceRoll")
	public String aquittanceRoll(@ModelAttribute("aquittanceRollReportModel") AquittanceRollReportModel aquittanceRollReportModel,
			Model model, Locale locale, HttpSession session,HttpServletRequest request) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstBillDesc", regularReportService.lstBillDesc(messages.getDdoCode()));
		addMenuAndSubMenu(model,messages);
		model.addAttribute("context", request.getContextPath());
		model.addAttribute("aquittanceRollReportModel",aquittanceRollReportModel);
		return "/views/reports/aquittanceRoll";
	}
	
	
	@GetMapping("/aquittancereport/{yearName}/{monthName}/{billNumber}/{ddoCode}")
	public String getAquittancereport(@ModelAttribute("aquittanceRollReportModel") AquittanceRollReportModel aquittanceRollReportModel,@PathVariable int monthName,@PathVariable int yearName, 
			@PathVariable Long billNumber,Model model, Locale locale, HttpSession session,@PathVariable String ddoCode) {
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		if(ddoCode.equals("1")) {
			ddoCode=messages.getUserName();
		}else {
			ddoCode=messages.getDdoCode();
		}
		String monname = null;
		Double totalnetamt = 0d;
		 Double word1 = 0d;
		List<AquittanceRollReportModel>  aquittanceinfo = aquittanceRollReportService.findAquittanceReportDtls(monthName,yearName,ddoCode,billNumber);
		
		totalnetamt = aquittanceinfo.stream().mapToDouble(AquittanceRollReportModel ::getNetamt).sum();

		
		String curryear=null;
		BigInteger currMonth = BigInteger.valueOf(monthName);
		BigInteger yearcurr = BigInteger.valueOf(yearName);
		
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(currMonth);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();
			
		}
		
		List<Object[]>  yearinfo = paybillGenerationTrnService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			// String empName;
			curryear = yearLst[9].toString();
			
		}
		String orgname=null;		
		String ddoname=null;
		Long ofcid=null;
		List<Object[]>  ddoinfo = paybillGenerationTrnService.findDDOinfo(ddoCode);

		for (Object[] objddoLst : ddoinfo) {
			// String empName;
			 ddoname = objddoLst[0].toString();
			 if(objddoLst[1]!=null)
			 ofcid = Long.parseLong(objddoLst[1].toString());
			
		}
		String billGrpname = commonHomeMethodsService.findbillGrpname(billNumber);
		Date createdate = commonHomeMethodsService.findbillCreateDate(billNumber);
		String officename =commonHomeMethodsService.getOffice(ddoCode);
		
		Long trsyCode =null;
		String trsyName=null;
		List<Object[]>  treasuryDtls = regularReportService.findTrsyDtls(messages.getDdoCode());
		
		for (Object[] objects : treasuryDtls) {
			
			trsyCode = (Long) objects[0];
			trsyName = (String) objects[1];
			
		}
		
		
		/*String treasury =commonHomeMethodsService.getTreasuryCode(ddoCode);
		model.addAttribute("treasury",treasury);*/
		model.addAttribute("trsyCode",trsyCode);
		model.addAttribute("trsyName",trsyName);
		model.addAttribute("aquittanceinfo", aquittanceinfo);
		model.addAttribute("officename",officename);
		model.addAttribute("totalnetamt",totalnetamt);
		model.addAttribute("billGrpname",billGrpname);
		
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat sdf1 =new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("createddate", sdf1.format(createdate));
        model.addAttribute("systemdate", sdf.format(new Date()));
		
		String word=CashWordConverter.doubleConvert(totalnetamt);
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("orgname", orgname);
		model.addAttribute("ddoname", messages.getDdoCode());
		model.addAttribute("word", word);
		model.addAttribute("aquittanceRollReportModel", aquittanceRollReportModel);
		
		model.addAttribute("language", locale.getLanguage());
		return "/views/reports/aquittance-roll-report";
		
	}
	
	
	
	
	
	
}
