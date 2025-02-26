package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.common.CashWordConverter;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.service.GISReportService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddoast")
@Controller
public class GISReportController extends BaseController {

	@Autowired
	RegularReportService regularReportService;
	@Autowired
	GISReportService gisReportService;

	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;

	@GetMapping("/gis")
	public String gis(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel, Model model,
			Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstBillDesc", regularReportService.lstBillDesc(messages.getDdoCode()));
		return "/views/reports/gis-search";
	}
	@RequestMapping("/gisReport")
	public String gisReport(@ModelAttribute("regularReportModel") RegularReportModel regularReportModel, Model model,
			Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		List<RegularReportModel> lstRptDtls = gisReportService.findRptDtls(regularReportModel.getMonthId(),
				regularReportModel.getYearId(), regularReportModel.getBillGroup(),
				regularReportModel.getGisAllowDeducCode());

		List<RegularReportModel> lstPremium = lstRptDtls.stream()
				.filter(s -> s.getGisRateType().equals("premium_amount")).collect(Collectors.toList());
		List<RegularReportModel> lstComposite = lstRptDtls.stream()
				.filter(s -> s.getGisRateType().equals("composite_amount")).collect(Collectors.toList());

		double totalCompositeSum = lstComposite.stream().mapToDouble(RegularReportModel::getGisAmount).sum(); // getGisAmount()
																												// returns
																												// a
																												// double
		double totalPremiumSum = lstPremium.stream().mapToDouble(RegularReportModel::getGisAmount).sum(); // getGisAmount()
																											// returns a
																											// double

		double totalDeduc = totalCompositeSum + totalPremiumSum;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String totalDeducInWords = CashWordConverter.doubleConvert(totalDeduc);

		String monname = "";
		String curryear = "";
		String currFinancialYr = null;
		List<Object[]> monthinfo = paybillGenerationTrnService
				.findmonthinfo(BigInteger.valueOf(regularReportModel.getMonthId()));
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();

		}

		List<Object[]> yearinfo = commonHomeMethodsService
				.findyearinfo(BigInteger.valueOf(regularReportModel.getYearId()));
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[4].toString();
			currFinancialYr = yearLst[3].toString();

		}

		String officename = commonHomeMethodsService.getOffice(messages.getDdoCode());

		Long trsyCode = null;
		String trsyName = null;

		List<Object[]> treasuryDtls = regularReportService.findTrsyDtls(messages.getDdoCode());

		for (Object[] objects : treasuryDtls) {

			trsyCode = (Long) objects[0];
			trsyName = (String) objects[1];

		}

		model.addAttribute("lstPremium", lstPremium);
		model.addAttribute("lstComposite", lstComposite);
		model.addAttribute("sysDate", sdf.format(new Date()));
		model.addAttribute("date", sdf1.format(new Date()));
		model.addAttribute("totalCompositeSum", totalCompositeSum);
		model.addAttribute("totalPremiumSum", totalPremiumSum);
		model.addAttribute("totalDeducInWords", totalDeducInWords);
		model.addAttribute("currmonyer", monname + " " + curryear);
		model.addAttribute("officename", officename + " (" + messages.getDdoCode() + " )");
		model.addAttribute("treasury", trsyName + " (" + trsyCode + " )");
		return "/views/reports/gis-report";
	}

	
}
