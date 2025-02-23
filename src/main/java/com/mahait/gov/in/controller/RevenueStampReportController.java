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
import com.mahait.gov.in.model.RevenueStampReportModel;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;
import com.mahait.gov.in.service.RevenueStampReportService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddoast")
@Controller
public class RevenueStampReportController  extends BaseController {
	
	
	@Autowired
	RevenueStampReportService revenueStampReportService;
	
	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@Autowired
	RegularReportService regularReportService;
	
	@GetMapping("/revenueStampreport/{yearName}/{monthName}/{billNumber}/{ddoCode}")
	public String getrevenueStamp(@ModelAttribute("revenueStampReportModel") RevenueStampReportModel revenueStampReportModel,@PathVariable int monthName,@PathVariable int yearName,@PathVariable Long billNumber,
			Model model, Locale locale, HttpSession session,@PathVariable String ddoCode) {
		String message = (String) model.asMap().get("message");
		 OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	      addMenuAndSubMenu(model,messages);

		if(ddoCode.equals("1")) {
			ddoCode=messages.getDdoCode();
		}else {
			ddoCode=messages.getDdoCode();
		}
		String monname = null;
		List<RevenueStampReportModel>  revenueStampReport = revenueStampReportService.revenueStampReportfind(monthName,yearName,ddoCode.toString(),billNumber);
		
		double totalDeduc = revenueStampReport.stream().mapToDouble(RevenueStampReportModel :: getRevenueStamp).sum();
		
		String curryear=null;
		BigInteger bigInteger = BigInteger.valueOf(monthName);
		BigInteger yearcurr = BigInteger.valueOf(yearName);
		
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(bigInteger);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();
		}
		List<Object[]>  yearinfo = paybillGenerationTrnService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[9].toString();
		}
		String ddoname=null;
		String lvl2ddoname=null;
		String lvl2ddoCode=null;
		String tanNo=null;
		Long regid=null;
		Long ofcid=null;
		List<Object[]>  ddoinfo = paybillGenerationTrnService.findDDOinfo(messages.getUserName());

		for (Object[] objddoLst : ddoinfo) {
			 ddoname = objddoLst[0].toString();
			 if(objddoLst[1]!=null)
			 ofcid = Long.parseLong(objddoLst[1].toString());
		}
		
		Date createdate = commonHomeMethodsService.findbillCreateDate(billNumber);
		String officename =commonHomeMethodsService.getOffice(ddoCode);
		List<Object[]> ddoLvl2 =revenueStampReportService.getddoLvl2byddo(ddoCode);
		for (Object[] objects : ddoLvl2) {
			lvl2ddoCode = (String) objects[0];
			lvl2ddoname = (String) objects[1];
			tanNo = (String) objects[2];
		}
		/*String treasury =commonHomeMethodsService.getTreasuryCode(ddoCode);
		model.addAttribute("treasury",treasury);*/
		
		
		Long trsyCode =null;
		String trsyName=null;
		List<Object[]>  treasuryDtls = regularReportService.findTrsyDtls(messages.getDdoCode());
		
		for (Object[] objects : treasuryDtls) {
			
			trsyCode = (Long) objects[0];
			trsyName = (String) objects[1];
			
		}
		
		model.addAttribute("trsyCode",trsyCode);
		model.addAttribute("trsyName",trsyName);
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat sdf1 =new SimpleDateFormat("dd-MM-yyyy");
		model.addAttribute("officename",officename);
		model.addAttribute("lvl2ddoCode",lvl2ddoCode);
		model.addAttribute("lvl2ddoname",lvl2ddoname);
		model.addAttribute("tanNo",tanNo);
		model.addAttribute("revenueStampReport",revenueStampReport);
		model.addAttribute("totalDeduc",totalDeduc);
        model.addAttribute("createddate", sdf1.format(createdate));
        model.addAttribute("systemdate", sdf.format(new Date()));
		String word=CashWordConverter.doubleConvert(totalDeduc);
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("officename", officename);
		model.addAttribute("ddoCode", ddoCode);
		model.addAttribute("word", word);
		model.addAttribute("revenueStampReportModel", revenueStampReportModel);
		model.addAttribute("language", locale.getLanguage());
		return "/views/reports/revenue-stamp-report";
		
	}
	
}
