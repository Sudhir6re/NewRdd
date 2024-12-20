package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DisplayBrokenPeriodReportModel;
import com.mahait.gov.in.service.BrokenPeriodReportService;
import com.mahait.gov.in.service.CommonHomeMethodsService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/ddoast")
public class BrokenPeriodReportController extends BaseController{
	
	
	@Autowired
	BrokenPeriodReportService	brokenPeriodReportService;
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@GetMapping("/brokenPeriodReport/{billNumber}/{size}/{page}/{billno}")
	public String brokenPeriodReport(@PathVariable Long billNumber,@PathVariable int size,@PathVariable int page,@PathVariable Long billno, Model model, Locale locale, HttpSession session) {
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		  int currentPage = page;
	      int pageSize = 1;
	      OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	      addMenuAndSubMenu(model,messages);
	      Page<DisplayBrokenPeriodReportModel> innerrptPage = brokenPeriodReportService.findPaginated(PageRequest.of(currentPage - 1, pageSize),billNumber,messages.getDdoCode().toString(),billno);
	        model.addAttribute("innerrptPage", innerrptPage);
	 
	        int totalPages = innerrptPage.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
	        
	        Long mon=0l;
	        Long yer=0l;
	        List<Object[]> createdate = commonHomeMethodsService.findDetailsBillNumber(billno);
	        for (Object[] objects : createdate) {
	        	mon=Long.parseLong(objects[0].toString());
	        	yer=Long.parseLong(objects[1].toString());
			}
	        
	        BigInteger bigInteger = BigInteger.valueOf(mon);
			BigInteger yearcurr = BigInteger.valueOf(yer);
			
			String monname="";
			String curryear="";
			
			List<Object[]>  monthinfo = commonHomeMethodsService.findmonthinfo(bigInteger);
			for (Object[] monthLst : monthinfo) {
				monname = monthLst[4].toString();
			}
			
			List<Object[]>  yearinfo = commonHomeMethodsService.findyearinfo(yearcurr);
			for (Object[] yearLst : yearinfo) {
				curryear = yearLst[9].toString();
			}
	        String officeDetails = commonHomeMethodsService.getOffice(messages.getDdoCode());
	     ///   model.addAttribute("createddate", sdf.format(new Date()));
	        model.addAttribute("currmonyer", monname+" "+curryear);
	        model.addAttribute("officeDetails", officeDetails);
	        model.addAttribute("ddoname", messages.getUserName());
		model.addAttribute("billNumber", billNumber);
		///model.addAttribute("systemdate", sdf.format(new Date()));
		return "views/reports/brokenPeriodreportDesign";
	}
	
	@GetMapping(value = "/monthyearBill/{monthName}/{yearName}/{billNumber}")
	public ResponseEntity<String> getBillsForConsolidation(@PathVariable int yearName,@PathVariable int monthName,@PathVariable Long billNumber,HttpSession session, Model model) {
		try {
			String resJson = "";
			 OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		      addMenuAndSubMenu(model,messages);
			Long billsize = brokenPeriodReportService.findbillsize(monthName, yearName,billNumber,messages.getDdoCode());
			resJson=billsize.toString();
			return ResponseEntity.ok(resJson);
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

}
