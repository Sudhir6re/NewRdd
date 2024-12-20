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
import com.mahait.gov.in.model.BankStatementReportModel;
import com.mahait.gov.in.service.BankStatementReportService;
import com.mahait.gov.in.service.DisplayInnerReportService;
import com.mahait.gov.in.service.DisplayOuterReportService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@RequestMapping("/ddoast")
@Controller
public class BankStatementReportController  extends BaseController {
	
	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@Autowired
	BankStatementReportService bankStatementReportService;
	
	@Autowired
	DisplayInnerReportService displayInnerReportService;
	
	@Autowired
	RegularReportService regularReportService;
	
	@Autowired
	DisplayOuterReportService displayOuterReportService;
	
	
	@GetMapping("/bankStatement")
	public String bankStatement(@ModelAttribute("bankStatementReportModel") BankStatementReportModel bankStatementReportModel,
			Model model, Locale locale, HttpSession session,HttpServletRequest request) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstBillDesc", regularReportService.lstBillDesc(messages.getDdoCode()));
		addMenuAndSubMenu(model,messages);
		model.addAttribute("context", request.getContextPath());
		model.addAttribute("bankStatementReportModel",bankStatementReportModel);
		return "/views/reports/bankStatment";
	}
	
	@GetMapping("/bankStatementreport/{yearName}/{monthName}/{billNumber}/{ddoCode}")
	public String getbankStatementreport(@ModelAttribute("bankStatementReportModel") BankStatementReportModel bankStatementReportModel,@PathVariable int monthName,@PathVariable int yearName,@PathVariable Long billNumber, Model model, 
			Locale locale, HttpSession session,@PathVariable String ddoCode) {
//		String message = (String) model.asMap().get("message");
		// model.addAttribute("payBillViewApprDelBillModel",
		// payBillViewApprDelBillModel);
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		if(ddoCode.equals("1")) {
			ddoCode=messages.getUserName();
		}else {
			ddoCode=messages.getDdoCode();;
		}
		
		String monname = null;
		List<BankStatementReportModel>  bankstatementinfo = bankStatementReportService.findbankstatementinfo(monthName,yearName,ddoCode.toString(),String.valueOf(billNumber));
		
	    double totalNetAmount = bankstatementinfo.stream().mapToDouble(BankStatementReportModel::getNetamt).sum();
        String totalnetAmtinWords=CashWordConverter.doubleConvert(totalNetAmount);
		
		
		String curryear=null;
		BigInteger monthcurr = BigInteger.valueOf(monthName);
		BigInteger yearcurr = BigInteger.valueOf(yearName);
		
		List<Object[]> monthinfo = commonHomeMethodsService.findmonthinfo(monthcurr);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();
		}

		List<Object[]> yearinfo = commonHomeMethodsService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[9].toString();
		}
		String ddoname=null;
		Long ofcid=null;
		List<Object[]>  ddoinfo = paybillGenerationTrnService.findDDOinfo(ddoCode);

		for (Object[] objddoLst : ddoinfo) {
			// String empName;
			 ddoname = objddoLst[0].toString();
			 if(objddoLst[1]!=null)
			 ofcid = Long.parseLong(objddoLst[1].toString());
			
		}
		String billGrpname = displayInnerReportService.getbillDetails(billNumber);
		Date createdate = commonHomeMethodsService.findbillCreateDate(billNumber);
		String officename =commonHomeMethodsService.getOffice(ddoCode);
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat sdf1 =new SimpleDateFormat("dd/MM/yyyy");
		////String treasury =commonHomeMethodsService.getTreasuryCode(ddoCode);
	///	model.addAttribute("treasury",treasury);
		model.addAttribute("bankstatementinfo",bankstatementinfo);
		model.addAttribute("officename",officename);
		model.addAttribute("billGrpname",billGrpname);
		model.addAttribute("totalNetAmount",totalNetAmount);
		model.addAttribute("totalnetAmtinWords",totalnetAmtinWords);
        model.addAttribute("createddate", sdf.format(createdate));
        model.addAttribute("systemdate", sdf.format(new Date()));
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("ddoname", ddoCode);
		model.addAttribute("yearName",yearName);
		model.addAttribute("monthName",monthName);
		model.addAttribute("billNumber",billNumber);
		model.addAttribute("bankStatementReportModel", bankStatementReportModel);
		model.addAttribute("language", locale.getLanguage());
		return "/views/reports/bank-statement-report";
		
	}
	/*@GetMapping("/bankStatement")
	public String bankStatement( Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);	
		return "/views/reports/bank-statement";
	}
*/
}
