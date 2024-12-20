package com.mahait.gov.in.controller;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DisplayPageWiseAbstractReportModel;
import com.mahait.gov.in.service.PageWiseAbstractReportService;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/ddoast")
@Controller
public class PageWiseAbstractReportController  extends BaseController{
	
	@Autowired
	PageWiseAbstractReportService pageWiseAbstractReportService;
	
	
	
	
	@GetMapping("/getpageWiseAbstractreport/{billNumber}/{size}/{page}/{ddoCode}")
	public String getpageWiseAbstractreport(@PathVariable Long billNumber,@PathVariable int size,@PathVariable int page
			, Model model, Locale locale, HttpSession session,@PathVariable String ddoCode) {

	
		  int currentPage = page;
	      int pageSize = 1;
	      OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	      addMenuAndSubMenu(model,messages);

			if(ddoCode.equals("1")) {
				ddoCode=messages.getUserName();
			}else {
				ddoCode=messages.getDdoCode();
			}
	      Page<DisplayPageWiseAbstractReportModel> innerrptPage = pageWiseAbstractReportService.findPaginated(PageRequest.of(currentPage - 1, pageSize),billNumber,ddoCode);
	      
	        model.addAttribute("innerrptPage", innerrptPage);
	        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	 
	        int totalPages = innerrptPage.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
	        Long mon=0l;
	        Long yer=0l;
	        List<Object[]> createdate = commonHomeMethodsService.findDetailsBillNumber(billNumber);
	        for (Object[] objects : createdate) {
	        	mon=Long.parseLong(objects[0].toString());
	        	yer=Long.parseLong(objects[1].toString());
			}
	        
	        BigInteger monthcurr = BigInteger.valueOf(mon);
			BigInteger yearcurr = BigInteger.valueOf(yer);
			String billGrpname = commonHomeMethodsService.findbillGrpname(billNumber);
			String monname="";
			String curryear="";
			
			List<Object[]>  monthinfo = commonHomeMethodsService.findmonthinfo(monthcurr);
			for (Object[] monthLst : monthinfo) {
				monname = monthLst[4].toString();
			}
			
			List<Object[]>  yearinfo = commonHomeMethodsService.findyearinfo(yearcurr);
			for (Object[] yearLst : yearinfo) {
				curryear = yearLst[9].toString();
			}
	        String officeDetails = commonHomeMethodsService.getOffice(ddoCode);
	        
	        Date createdateded = commonHomeMethodsService.findbillCreateDate(billNumber);
	        model.addAttribute("createddate", sdf.format(createdateded));
	        model.addAttribute("currmonyer", monname+" "+curryear);
	        model.addAttribute("officeDetails", officeDetails);
	        model.addAttribute("ddoname", ddoCode);
		model.addAttribute("billNumber", billNumber);
		model.addAttribute("billGrpname", billGrpname);
		model.addAttribute("systemdate", sdf.format(new Date()));
				

		
		return "views/reports/pageWiseAbstractReportDesign";
	}
	
	
	/*@GetMapping("/pageWiseAbstractReport")
	public String pageWiseAbstractReport( Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/reports/page-wise-abstract";
	}
*/
}
