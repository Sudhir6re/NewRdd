package com.mahait.gov.in.nps.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.controller.BaseController;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.nps.model.NSDLDetailsModel;
import com.mahait.gov.in.nps.service.NsdlContriService;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class NsdlContriController  extends BaseController{

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	NsdlContriService nsdlContriService;

	@Autowired
	RegularReportService regularReportService;

	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;

	@RequestMapping(value = "/nsdlContri", method = { RequestMethod.GET, RequestMethod.POST })
	public String nsdlContri(@ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel, Model model,
			Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		addMenuAndSubMenu(model, messages);

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.SAVEDRAFT, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		if (message != null && message.equals("FRWDDDO")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.FRWDDDO, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("nsdlDetailsModel", nsdlDetailsModel);

		return "/views/nps/nsdlContri";
	}

	@RequestMapping("/nsdlContriReport")
	public String nsdlContriReport(@ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel, Model model,
			Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();
		String curryear = null;

	/*	List<DDOScreenEntity> ddoDtls = regularReportService.getDDOName(messages.getUserName());
		String ddoName = null;
		String ddoCode = null;
		String offcName = null;
		for (DDOScreenEntity ddoScreenEntity : ddoDtls) {
			ddoName = ddoScreenEntity.getDdoName();
			ddoCode = ddoScreenEntity.getDdoCode();
			offcName = ddoScreenEntity.getOfficeName();
		}*/

		Integer month = nsdlDetailsModel.getMonth();
		Integer year = nsdlDetailsModel.getYear();
		BigInteger bigInteger = BigInteger.valueOf(nsdlDetailsModel.getMonth());

		List<Object[]> yearinfo = commonHomeMethodsService.findyearinfo(BigInteger.valueOf(year));
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[1].toString();
		}

		String monname = "";
		List<Object[]> monthinfo = paybillGenerationTrnService.findmonthinfo(bigInteger);
		for (Object[] monthLst : monthinfo) {
			// String empName;
			monname = monthLst[1].toString();

		}

		BigInteger yearcurr = BigInteger.valueOf(nsdlDetailsModel.getYear());
		BigInteger nextMonth = BigInteger.valueOf(nsdlDetailsModel.getMonth() + 1);
		String NextMon = "";
		String NextYear = "";
		BigInteger neyer = null;
		BigInteger nemon;

		if (bigInteger.equals(new BigInteger("12"))) {
			nextMonth = new BigInteger("1");
			BigInteger nextyer = new BigInteger("1");
			neyer = yearcurr.add(nextyer);
			NextYear = neyer.toString();
		} else {
			nextMonth = nextMonth;
			neyer = yearcurr;
		}

		List<Object[]> nextmonthinfo = paybillGenerationTrnService.findmonthinfo(nextMonth);
		for (Object[] monthLst : nextmonthinfo) {
			// String empName;
			NextMon = monthLst[1].toString();

		}

		List<Object[]> nextyearinfo = paybillGenerationTrnService.findyearinfo(neyer);
		for (Object[] yearLst : nextyearinfo) {
			// String empName;
			NextYear = yearLst[1].toString();

		}

		String orgname = null;
		String ofcname = null;
		String ddoname = null;
		Long regid = null;
		Long ofcid = null;
		List<Object[]> ddoinfo = paybillGenerationTrnService.findDDOinfo(messages.getUserName());

		for (Object[] objddoLst : ddoinfo) {
			// String empName;
			regid = Long.parseLong(objddoLst[0].toString());
			ddoname = objddoLst[1].toString();
			if (objddoLst[2] != null)
				ofcid = Long.parseLong(objddoLst[2].toString());

		}

		addMenuAndSubMenu(model, messages);

		List<NSDLDetailsModel> lstNSDLDetailsModel = nsdlContriService.getNsdlContriDetails(nsdlDetailsModel.getMonth(),
				nsdlDetailsModel.getYear(), messages.getMstRoleEntity().getRoleId(), messages.getUserName());

		model.addAttribute("nsdlDetailsModel", nsdlDetailsModel);
		model.addAttribute("lstNSDLDetailsModel", lstNSDLDetailsModel);
		model.addAttribute("menuList", menuList);
		NextYear = "Paybill of Month " + monname + " - " + curryear + " Paid In Month " + NextMon + "-" + NextYear;
		model.addAttribute("NextYear", NextYear);
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		model.addAttribute("subMenuList", subMenuList);

		return "/views/nps/nsdlContriReport";
	}
}
