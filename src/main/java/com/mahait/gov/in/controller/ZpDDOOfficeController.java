package com.mahait.gov.in.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpRltDdoMap;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.ZpDDOOfficeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class ZpDDOOfficeController   extends BaseController{
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	ZpDDOOfficeService zpDDOOfficeService;
	
	
	List<NewRegDDOModel> emplist = new ArrayList<>();
	
	@GetMapping("/approveDdoOfficeDataList")
	public String approveDdoOfficeDataList(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel,
			 Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		String message=(String) model.asMap().get("message");
	
		List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeService
				.getAllDDOOfficeDtlsDataByPostID(messages.getUserName());
		
		
	///	model.addAttribute("message", message);
	///	
		model.addAttribute("zpDDOOfficelst", zpDDOOfficelst);
		
		addMenuAndSubMenu(model,messages);
	
		return "/views/approveDDOOfficeView";
		
	}
	
	@GetMapping("/approveRejectDtls/{zpDdoCode}")
	public String approveRejectDtls(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel, Model model,
			Locale locale, HttpSession session, @PathVariable String zpDdoCode) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		
		newRegDDOModel = zpDDOOfficeService.getDDOinfo(zpDdoCode);
	    String officeName = zpDDOOfficeService.getOfficeName(zpDdoCode);
		model.addAttribute("newRegDDOModel", newRegDDOModel);
		model.addAttribute("zpDdoCode", zpDdoCode);
		model.addAttribute("officeName", officeName);
		addMenuAndSubMenu(model,messages);
	
		
		
		return "/views/approveRejectDDOOfficeView";
	}
	
	@GetMapping("/updateApproveStatus/{zpDdoCode}/{flag}")
	public String updateApproveStatus(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel,@PathVariable String zpDdoCode,
			@PathVariable int flag,Model model,Locale locale,HttpSession session,HttpServletRequest request, Object paybillHeadMpgRepo) {
		
			OrgUserMst orgUserMst  =  zpDDOOfficeService.approveddoDtls(zpDdoCode,flag);
			OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
			addMenuAndSubMenu(model,messages);
		
			
			
		if(orgUserMst!=null)
			model.addAttribute("message","Approved Successfully");
			return "/views/approveDDOOfficeView";
		///}
	}
	
	@PostMapping("/getReport")
	public String getReport(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel,
			 Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		String message=(String) model.asMap().get("message");
		addMenuAndSubMenu(model,messages);
	
		
		return "/views/DDOOfficeStatusView";
		
	}
	
	@GetMapping("/getApprovedOfficesLst")
	public String getApprovedOfficesLst(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel, Model model,
			Locale locale, HttpSession session) {
		///UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
	
		model.addAttribute("lstApprovedOffices", zpDDOOfficeService.lstApprovedOffices(messages.getUserName()));//,messages.getUpdatedByPost().getLocationCode()
		return "/views/approvedDDOOffices";
	}
	@GetMapping("/getRejectedOfficesLst")
	public String getRejectedOfficesLst(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel, Model model,
			Locale locale, HttpSession session) {
		///UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
	
		
		model.addAttribute("lstRejectedOffices", zpDDOOfficeService.lstRejectedOffices(messages.getUserName()));//,messages.getUpdatedByPost().getLocationCode()
		return "/views/rejectedDDOOffices";
	}
}