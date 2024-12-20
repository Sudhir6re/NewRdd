package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.common.PdfGenaratorUtil;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.DisplayInnerReportModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DisplayInnerReportService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value= {"/ddoast","/ddo"})
public class DisplayInnerReportController  extends BaseController{
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;

	@Autowired
	DisplayInnerReportService displayInnerReportService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@GetMapping("/getinnerreport/{billNumber}/{size}/{page}/{ddoCode}")
	public String getInnerReport(@PathVariable Long billNumber, @PathVariable int size, @PathVariable int page,
			Model model, Locale locale, HttpSession session, @PathVariable String ddoCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		int currentPage = page;
		int pageSize = 1;
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		
		int roleId=messages.getMstRoleEntity().getRoleId();
		
		if(roleId!=2) {
			if (ddoCode.equals("1")) {
				ddoCode = messages.getUserName();
			} else {
				ddoCode = ddoCode;
			}
		}else {
			ddoCode=ddoCode;
		}
		
		Page<DisplayInnerReportModel> innerrptPage = displayInnerReportService
				.findPaginated(PageRequest.of(currentPage - 1, pageSize), billNumber, ddoCode.toString());

		model.addAttribute("innerrptPage", innerrptPage);

		int totalPages = innerrptPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		
		
		
		PaybillGenerationTrnEntity paybillGenerationTrnEntity=displayInnerReportService.findPayBilldetailByPaybillid(billNumber);
		
		Long mon = 0l;
		Long yer = 0l;
	//	List<Object[]> createdate = commonHomeMethodsService.findDetailsBillNumber(billNumber);
		if (paybillGenerationTrnEntity!=null) {
			mon = paybillGenerationTrnEntity.getPaybillMonth().longValue();
			yer = paybillGenerationTrnEntity.getPaybillYear().longValue();
		}

		BigInteger monthcurr = BigInteger.valueOf(mon);
		BigInteger yearcurr = BigInteger.valueOf(yer);

		String monname = "";
		String curryear = "";

		String billDetails = displayInnerReportService.getbillDetails(billNumber);
		
		List<Object[]> monthinfo = commonHomeMethodsService.findmonthinfo(monthcurr);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();
		}

		List<Object[]> yearinfo = commonHomeMethodsService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[9].toString();
		}
		
		String officeDetails = commonHomeMethodsService.getOffice(ddoCode);

		Date createdateded = paybillGenerationTrnEntity.getCreatedDate();
		
		model.addAttribute("createddate", sdf.format(createdateded));
		model.addAttribute("currmonyer", monname + " " + curryear);
		model.addAttribute("officeDetails", officeDetails);
		model.addAttribute("billDetails", billDetails);
		model.addAttribute("ddoname", ddoCode);
		model.addAttribute("billNumber", billNumber);
		model.addAttribute("systemdate", sdf.format(new Date()));
		model.addAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		
		
		addMenuAndSubMenu(model,messages);	
		return "views/reports/innerreportDesign";
	}

	@GetMapping("/getinnerreportPDF/{billNumber}/{size}/{page}")
	public String getinnerreportPDF(@PathVariable Long billNumber, @PathVariable int size, @PathVariable int page,
			Model model, Locale locale, HttpSession session, HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		int currentPage = page;
		int pageSize = 1;
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		Page<DisplayInnerReportModel> innerrptPage = displayInnerReportService.findPaginated(
				PageRequest.of(currentPage - 1, pageSize), billNumber, messages.getUserName().toString());

		model.addAttribute("innerrptPage", innerrptPage);

		int totalPages = innerrptPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		Long mon = 0l;
		Long yer = 0l;
		List<Object[]> createdate = commonHomeMethodsService.findDetailsBillNumber(billNumber);
		for (Object[] objects : createdate) {
			mon = Long.parseLong(objects[4].toString());
			yer = Long.parseLong(objects[5].toString());
		}

		BigInteger monthcurr = BigInteger.valueOf(mon);
		BigInteger yearcurr = BigInteger.valueOf(yer);

		String monname = "";
		String curryear = "";

		List<Object[]> monthinfo = commonHomeMethodsService.findmonthinfo(monthcurr);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[1].toString();
		}

		List<Object[]> yearinfo = commonHomeMethodsService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[1].toString();
		}
		String officeDetails = commonHomeMethodsService.getOffice(messages.getDdoCode());

		Date createdateded = displayInnerReportService.findbillCreateDate(billNumber);
		model.addAttribute("createddate", sdf.format(createdateded));
		model.addAttribute("currmonyer", monname + " " + curryear);
		model.addAttribute("officeDetails", officeDetails);
		model.addAttribute("ddoname", messages.getUserName());
		
		
		
		
		model.addAttribute("billNumber", billNumber);
		model.addAttribute("systemdate", sdf.format(new Date()));
		String billDetails = displayInnerReportService.getbillDetails(billNumber);
		model.addAttribute("billDetails", billDetails);
		model.addAttribute("roleId", messages.getMstRoleEntity().getRoleId());

		String ddoname = null;
		Map<String, String> data = new HashMap<String, String>();
		data.put("ddoname", ddoname);
		try {
			pdfGenaratorUtil.createPdf("views/report/innerreportDesign", model.asMap(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		addMenuAndSubMenu(model,messages);
		return "views/report/innerreportDesign";
	}

	@GetMapping("/getinnerPrintreport/{billNumber}/{size}/{page}")
	public String getInnerPrintReport(@PathVariable Long billNumber, @PathVariable int size, @PathVariable int page,
			Model model, Locale locale, HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		int currentPage = page;
		int pageSize = 1;
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		Page<DisplayInnerReportModel> innerrptPage = displayInnerReportService.findPaginated(
				PageRequest.of(currentPage - 1, pageSize), billNumber, messages.getDdoCode().toString());

		model.addAttribute("innerrptPage", innerrptPage);

		int totalPages = innerrptPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		Long mon = 0l;
		Long yer = 0l;
		List<Object[]> createdate = commonHomeMethodsService.findDetailsBillNumber(billNumber);
		for (Object[] objects : createdate) {
			mon = Long.parseLong(objects[4].toString());
			yer = Long.parseLong(objects[5].toString());
		}

		BigInteger monthcurr = BigInteger.valueOf(mon);
		BigInteger yearcurr = BigInteger.valueOf(yer);

		String monname = "";
		String curryear = "";

		List<Object[]> monthinfo = commonHomeMethodsService.findmonthinfo(monthcurr);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[1].toString();
		}

		List<Object[]> yearinfo = commonHomeMethodsService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[1].toString();
		}
		String officeDetails = commonHomeMethodsService.getOffice(messages.getDdoCode());

		Date createdateded = displayInnerReportService.findbillCreateDate(billNumber);
		String billDetails = displayInnerReportService.getbillDetails(billNumber);
		model.addAttribute("billDetails", billDetails);
		model.addAttribute("createddate", sdf.format(createdateded));
		model.addAttribute("currmonyer", monname + " " + curryear);
		model.addAttribute("officeDetails", officeDetails);
		model.addAttribute("ddoname", messages.getUserName());
		model.addAttribute("billNumber", billNumber);
		model.addAttribute("systemdate", sdf.format(new Date()));
		
		addMenuAndSubMenu(model,messages);
		return "views/report/innerreportDesignPrint";
	}

	
    //all pages 
	@GetMapping("/getinnerreportAllPages/{billNumber}/{size}/{page}/{ddoCode}")
	public String getinnerreportAllPages(@PathVariable Long billNumber, @PathVariable int size, @PathVariable int page,
			Model model, Locale locale, HttpSession session, @PathVariable String ddoCode,@ModelAttribute("displayInnerReportModel") DisplayInnerReportModel displayInnerReportModel) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		int currentPage = page;
		int pageSize = 1;
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		
		if (ddoCode.equals("1")) {
			ddoCode = messages.getDdoCode();
		} else {
			ddoCode = ddoCode;
		}
		
		
         /*Page<DisplayInnerReportModel> innerrptPage = displayInnerReportService
				.findPaginated(PageRequest.of(currentPage - 1, pageSize), billNumber, ddoCode.toString());*/

		
		List<DisplayInnerReportModel> listDisplayInnerReportModel=new ArrayList<>();
		
		
		//List<Page<DisplayInnerReportModel>> lstPage = new ArrayList<Page<DisplayInnerReportModel>>();
		int currpg = 1;
		
		int totalPages=1;
		for (int i = 0; i < totalPages; i++) {
			
			Page<DisplayInnerReportModel> innerrptPage1 = displayInnerReportService
					.findPaginated(PageRequest.of(currpg - 1, pageSize), billNumber, ddoCode.toString());
		
			totalPages=innerrptPage1.getTotalPages();
			
			for(DisplayInnerReportModel lst:innerrptPage1) {
				listDisplayInnerReportModel.add(lst);
			}
			
			currpg++;
		}

		displayInnerReportModel.setLstDisplayInnerReportModel(listDisplayInnerReportModel);
		
		
		model.addAttribute("listDisplayInnerReportModel", listDisplayInnerReportModel);
	

	//	totalPages = innerrptPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		Long mon = 0l;
		Long yer = 0l;
		List<Object[]> createdate = commonHomeMethodsService.findDetailsBillNumber(billNumber);
		for (Object[] objects : createdate) {
			mon = Long.parseLong(objects[4].toString());
			yer = Long.parseLong(objects[5].toString());
		}

		BigInteger monthcurr = BigInteger.valueOf(mon);
		BigInteger yearcurr = BigInteger.valueOf(yer);

		String monname = "";
		String curryear = "";

		List<Object[]> monthinfo = commonHomeMethodsService.findmonthinfo(monthcurr);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[1].toString();
		}

		List<Object[]> yearinfo = commonHomeMethodsService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[1].toString();
		}
		String officeDetails = commonHomeMethodsService.getOffice(ddoCode);

		Date createdateded = displayInnerReportService.findbillCreateDate(billNumber);
		String billDetails = displayInnerReportService.getbillDetails(billNumber);
		model.addAttribute("billDetails", billDetails);
		model.addAttribute("createddate", sdf.format(createdateded));
		model.addAttribute("currmonyer", monname + " " + curryear);
		model.addAttribute("officeDetails", officeDetails);
		model.addAttribute("ddoname", ddoCode);
		model.addAttribute("billNumber", billNumber);
		model.addAttribute("systemdate", sdf.format(new Date()));
		
		addMenuAndSubMenu(model,messages);
		
		model.addAttribute("displayInnerReportModel", displayInnerReportModel);
		return "views/report/inner-report-allpages";
	}

}
