package com.mahait.gov.in.controller;
import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.EmpBasicDtlsReportService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/ddoast")
@Controller
public class EmpBasicDtlsReportController  extends BaseController{
	
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	RegularReportService regularReportService;
	
	
	@Autowired
	EmpBasicDtlsReportService empBasicDtlsReportService;
	
	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@RequestMapping(value = "/empBasicDtls", method = { RequestMethod.GET , RequestMethod.POST})
	public String empBasicDtls(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel,
			Model model, Locale locale, HttpSession session) {
		
			String message = (String) model.asMap().get("message");
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstBillDesc", regularReportService.lstBillDesc(messages.getDdoCode()));
		model.addAttribute("empLst", empBasicDtlsReportService.lstDDOWiseEmp(messages.getDdoCode()));
		addMenuAndSubMenu(model,messages);
		return "/views/emp-basic-dtls-report";
	}
	
	@RequestMapping("/empBasicDtlsReport")
	public String empBasicDtlsReport(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel, Model model, Locale locale,
			HttpSession session) {
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat sdf1 =new SimpleDateFormat("dd/MM/yyyy");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		addMenuAndSubMenu(model,messages);
		String curryear=null;
		String currFinancialYr=null;
		
		List<OrgDdoMst> ddoDtls=regularReportService.getDDOName(messages.getDdoCode());
		
	
		String ddoName=null;
		String ddoCode=null;
		String offcName=null;
		for (OrgDdoMst ddoScreenEntity : ddoDtls) {
			ddoName= ddoScreenEntity.getDdoName();
			ddoCode= ddoScreenEntity.getDdoCode();
			offcName=  ddoScreenEntity.getDdoOffice();
		}
		
		Integer month=regularReportModel.getMonthId();
		Integer year=regularReportModel.getYearId();
		
		List<Object[]>  yearinfo = commonHomeMethodsService.findyearinfo(BigInteger.valueOf(year));
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[9].toString();
			currFinancialYr = yearLst[3].toString();
			
		}
		
		
		String startDate=null;
		Date fromDate=null;
		Date toDate=null;
		try {
			if (month < 10) {
				startDate = String.valueOf(curryear) + '-' + String.valueOf("0" + month) + "-01";
			} else {
				startDate = String.valueOf(curryear) + '-' + String.valueOf(month) + "-01";
			}
			 fromDate=new SimpleDateFormat("yyyy-mm-dd").parse(startDate);  
			Calendar c = Calendar.getInstance();
			c.setTime(fromDate);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			 toDate= c.getTime();
		}catch(Exception e) {
			
		}
		

		
		List<RegularReportModel> empDtls = empBasicDtlsReportService.findEmpBasicDtls(regularReportModel.getYearId(),regularReportModel.getMonthId(),
				regularReportModel.getBillGroup(),messages.getDdoCode(),regularReportModel.getSevaarthId());
        
		
		BigInteger monthId = BigInteger.valueOf(regularReportModel.getMonthId());
        
        
		String monname="";
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(monthId);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();
			
		}
		BigInteger yearcurr = BigInteger.valueOf(regularReportModel.getYearId());
		BigInteger nextMonth = BigInteger.valueOf(regularReportModel.getMonthId()+1);
		String NextMon="";
		String NextYear="";
		BigInteger neyer = null;
		BigInteger nemon;
		
		if(monthId.equals(new BigInteger("12"))) {
			nextMonth=new BigInteger("1");
			BigInteger nextyer =new BigInteger("1");
			neyer=yearcurr.add(nextyer);
			NextYear=neyer.toString();
		}else {
			nextMonth=nextMonth;
			neyer=yearcurr;
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

		Long billno = 0l;
		billno = regularReportModel.getBillGroup();
		String billgrpname="";
		List<Object[]>  billinfo = regularReportService.findbillgrp(billno);
		
		for (Object[] billst :  billinfo) {
			billgrpname = billst[2].toString();
			
		}
		
		String monthString = new DateFormatSymbols().getMonths()[month-1];
    	model.addAttribute("systemdate", sdf.format(new Date()));
    	model.addAttribute("date", sdf1.format(new Date()));
    	model.addAttribute("fromDate",sdf1.format(fromDate));
		model.addAttribute("toDate",sdf1.format(toDate));
		model.addAttribute("empDtls",empDtls);
		String officename =commonHomeMethodsService.getOffice(messages.getDdoCode());
		
    	model.addAttribute("systemdate", sdf.format(new Date()));
    	model.addAttribute("date", sdf1.format(new Date()));
		model.addAttribute("officename",officename);
        model.addAttribute("systemdate", sdf.format(new Date()));
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("billGroup", billgrpname);
		model.addAttribute("billno", billno);
		model.addAttribute("yearName",curryear);
		model.addAttribute("monthName",monname);
		
		model.addAttribute("regularReportModel", regularReportModel);
		model.addAttribute("systemdate", sdf.format(new Date()));
		model.addAttribute("ddoName",ddoName);
		model.addAttribute("monthString", monthString);
		model.addAttribute("nextMonth",nextMonth);
		addMenuAndSubMenu(model,messages);
	
		return "/views/reports/emp-basic-dtls-report-view";
	}
	

}
