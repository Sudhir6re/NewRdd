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
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.common.CashWordConverter;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.service.GPFAbstractReportService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddoast")
@Controller
public class FormXIIReportController  extends BaseController{
	
	@Autowired
	RegularReportService regularReportService;
	
	@Autowired
	GPFAbstractReportService gpfAbstractReportService;

	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@GetMapping("/formXII")
	public String formXII(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel,
			Model model, Locale locale, HttpSession session,HttpServletRequest request) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstBillDesc", regularReportService.lstBillDesc(messages.getDdoCode()));
		model.addAttribute("regularReportModel", regularReportModel);
		
		return "/views/reports/form-XII-search";
	}
	@RequestMapping("/formXIIReport")
	public String formXIIReport(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel,
			Model model, Locale locale, HttpSession session,HttpServletRequest request) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		
		
		List<RegularReportModel> gpfRptDtls = gpfAbstractReportService.findgpfRptDtls(regularReportModel.getMonthId(),regularReportModel.getYearId(),regularReportModel.getBillGroup());
		
		double totalSubscription = gpfRptDtls.stream().mapToDouble(RegularReportModel::getSubsAmt).sum();
		double totalRefund = gpfRptDtls.stream().mapToDouble(RegularReportModel::getRefundAmt).sum();
		double totalDaArr = gpfRptDtls.stream().mapToDouble(RegularReportModel::getDaArr).sum();
		
		double totalAmount = totalSubscription + totalRefund + totalDaArr;
		
		
		
		
		String monname = "";
		String curryear = "";
		String currFinancialYr = null;
		List<Object[]> monthinfo = paybillGenerationTrnService
				.findmonthinfo(BigInteger.valueOf(regularReportModel.getMonthId()));
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();

		}

		BigInteger yearCurr = BigInteger.valueOf(regularReportModel.getYearId());
		List<Object[]> yearinfo = commonHomeMethodsService
				.findyearinfo(BigInteger.valueOf(regularReportModel.getYearId()));
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[4].toString();
			currFinancialYr = yearLst[3].toString();

		}
		
		BigInteger nextMonth = BigInteger.valueOf(regularReportModel.getMonthId()+1);
		String NextMon="";
		String NextYear="";
		BigInteger neyer = null;
		BigInteger nemon;
		
		if(regularReportModel.getMonthId().equals(new BigInteger("12"))) {
			nextMonth=new BigInteger("1");
			BigInteger nextyer =new BigInteger("1");
			neyer=BigInteger.valueOf(regularReportModel.getYearId()).add(nextyer);
			NextYear=neyer.toString();
		}else {
			nextMonth=nextMonth;
			neyer=BigInteger.valueOf(regularReportModel.getYearId());
		}
		
		List<Object[]>  nextmonthinfo = paybillGenerationTrnService.findmonthinfo(nextMonth);
		for (Object[] monthLst : nextmonthinfo) {
			// String empName;
			NextMon = monthLst[4].toString();
			
		}
		
		List<Object[]>  nextyearinfo = paybillGenerationTrnService.findyearinfo(neyer);
		for (Object[] yearLst : nextyearinfo) {
			NextYear = yearLst[9].toString();
			
		}

		String officename = commonHomeMethodsService.getOffice(messages.getDdoCode());

		Long trsyCode = null;
		String trsyName = null;

		List<Object[]> treasuryDtls = regularReportService.findTrsyDtls(messages.getDdoCode());

		for (Object[] objects : treasuryDtls) {

			trsyCode = (Long) objects[0];
			trsyName = (String) objects[1];

		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		
		model.addAttribute("totalSubscription",totalSubscription);
		model.addAttribute("totalRefund",totalRefund);
		model.addAttribute("totalDaArr",totalDaArr);
		model.addAttribute("totalAmount",totalAmount);
	
		model.addAttribute("currmonyer", monname + " " + curryear);
		model.addAttribute("nextmonyer", NextMon + " " + NextYear);
		model.addAttribute("officename", officename + " (" + messages.getDdoCode() + " )");
		model.addAttribute("treasury", trsyName + " (" + trsyCode + " )");
		model.addAttribute("sysDate", sdf.format(new Date()));
		model.addAttribute("date", sdf1.format(new Date()));
		String word=CashWordConverter.doubleConvert(totalAmount);
		model.addAttribute("word",word);
		
		
		return "/views/reports/form-XII-report";
	}
}
