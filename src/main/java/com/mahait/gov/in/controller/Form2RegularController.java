package com.mahait.gov.in.controller;
import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mahait.gov.in.common.CashWordConverter;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/ddoast")
@Controller
public class Form2RegularController  extends BaseController{
	
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	RegularReportService regularReportService;
	
	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@RequestMapping(value = "/form2Report", method = { RequestMethod.GET , RequestMethod.POST})
	public String form2Report(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel,
			Model model, Locale locale, HttpSession session) {
		
			String message = (String) model.asMap().get("message");
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstBillDesc", regularReportService.lstBillDesc(messages.getDdoCode()));
	/*	model.addAttribute("lstReportType", regularReportService.lstReportType();*/
		
		addMenuAndSubMenu(model,messages);
		return "/views/form2-report";
	}
	@RequestMapping("/form2RegularReport")
	public String form2RegularReport(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel, Model model, Locale locale,
			HttpSession session) {
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat sdf1 =new SimpleDateFormat("dd/MM/yyyy");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
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
			curryear = yearLst[4].toString();
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
		
		String totalContrinword = null;
		
		List<RegularReportModel> entryinfo = regularReportService.findDCPSRegularEmpLst(regularReportModel.getYearId(),regularReportModel.getMonthId(),regularReportModel.getBillGroup(),
				messages.getDdoCode(),regularReportModel.getAllowdedCode());
        BigInteger bigInteger = BigInteger.valueOf(regularReportModel.getMonthId());
        
        if(entryinfo!=null || entryinfo.size()>0) {
        	double dcpsReg = entryinfo.stream().mapToDouble(RegularReportModel::getDcpsReg).sum();
            double npsEmprdeduc = entryinfo.stream().mapToDouble(RegularReportModel::getNpsEmployerDedu).sum();
            double totalSum = dcpsReg + npsEmprdeduc;
            totalContrinword=CashWordConverter.doubleConvert(totalSum);
        }
        
        
        
		String monname="";
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(bigInteger);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();
			
		}
		BigInteger yearcurr = BigInteger.valueOf(regularReportModel.getYearId());
		BigInteger nextMonth = BigInteger.valueOf(regularReportModel.getMonthId()+1);
		String NextMon="";
		String NextYear="";
		BigInteger neyer = null;
		BigInteger nemon;
		
		if(bigInteger.equals(new BigInteger("12"))) {
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
		
		Long trsyCode =null;
		String trsyName=null;
		List<Object[]>  treasuryDtls = regularReportService.findTrsyDtls(messages.getDdoCode());
		
		for (Object[] objects : treasuryDtls) {
			
			trsyCode = (Long) objects[0];
			trsyName = (String) objects[1];
			
		}
		
		String monthString = new DateFormatSymbols().getMonths()[month-1];
    	model.addAttribute("systemdate", sdf.format(new Date()));
    	model.addAttribute("date", sdf1.format(new Date()));
    	model.addAttribute("fromDate",sdf1.format(fromDate));
		model.addAttribute("toDate",sdf1.format(toDate));
		model.addAttribute("entryinfo",entryinfo);
		model.addAttribute("trsyCode",trsyCode);
		model.addAttribute("trsyName",trsyName);
		String officename =commonHomeMethodsService.getOffice(messages.getDdoCode());
		/*String treasury =commonHomeMethodsService.getTreasury(messages.getUserName());
		String treasuryCode =commonHomeMethodsService.getTreasuryCode(messages.getUserName());*/
		
    	model.addAttribute("systemdate", sdf.format(new Date()));
    	model.addAttribute("date", sdf1.format(new Date()));
		model.addAttribute("officename",officename);
        model.addAttribute("createddate", sdf.format(new Date()));
        model.addAttribute("systemdate", sdf.format(new Date()));
		model.addAttribute("totalContrinword", totalContrinword);
		
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("offcName", offcName);
		model.addAttribute("billGroup", billgrpname);
		model.addAttribute("yearName",curryear);
		model.addAttribute("monthName",monname);
		model.addAttribute("currFinancialYr",currFinancialYr);
		
		model.addAttribute("ddoCode",messages.getDdoCode());
		model.addAttribute("regularReportModel", regularReportModel);
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("systemdate", sdf.format(new Date()));
    	model.addAttribute("date", sdf1.format(new Date()));
		model.addAttribute("ddoName",ddoName);
		model.addAttribute("monthString", monthString);
		model.addAttribute("allowdeducId", regularReportModel.getAllowdedCode());
		/*model.addAttribute("treasury", treasury);
		model.addAttribute("treasuryCode",treasuryCode);*/
		model.addAttribute("nextMonth",nextMonth);
		NextYear="Paybill of Month "+monname+" - " +curryear+" Paid In Month "+NextMon+"-"+NextYear;
		model.addAttribute("NextYear",NextYear);
		addMenuAndSubMenu(model,messages);
	
		return "/views/reports/form2-regular-report";
	}
	
	
	
	@GetMapping(value = "/checktheEntryForForm2Regular/{billNumber}/{monthName}/{yearName}/{allowdedCode}")
	public ResponseEntity<Integer> checktheEntryForForm2Regular(@PathVariable Long billNumber, @PathVariable Integer monthName,
			@PathVariable Integer yearName,@PathVariable Long allowdedCode,HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		List<RegularReportModel> entryinfo = regularReportService.findDCPSRegularEmpLst(yearName,monthName,billNumber,
				messages.getDdoCode(),allowdedCode);
		
		return ResponseEntity.ok(entryinfo.size());
	
	}


}
