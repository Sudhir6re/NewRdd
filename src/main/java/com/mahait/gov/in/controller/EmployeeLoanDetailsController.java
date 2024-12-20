package com.mahait.gov.in.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmpLoanModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.EmployeeLoanDetailsService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddoast")
@Controller


public class EmployeeLoanDetailsController  extends BaseController{
	
@Autowired
CommonHomeMethodsService commonHomeMethodsService;

@Autowired
EmployeeLoanDetailsService employeeLoanDetailsService;
	
	@GetMapping("employeeLoanDetails")
	public String employeeLoanDetails(@ModelAttribute("empLoanModel") EmpLoanModel empLoanModel,Model model, Locale locale, HttpSession session,RedirectAttributes redirectAttributes) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		
		addMenuAndSubMenu(model,messages);
		// model.addAttribute("brokenPeriodModel", brokenPeriodModel);
	
		
//		model.addAttribute("lstCommonMstLoanAndAdvance",
//				commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.GPFLOAN_ADVANCE));
				
		
	 	
		List<EmpLoanModel> lstEmp=new ArrayList<>();
		lstEmp=employeeLoanDetailsService.findAllEmpLoanDtls(messages.getDdoCode());
		
		model.addAttribute("lstEmp", lstEmp);
		
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("standardDate", new Date());
		
		return "/views/emp-loan-details";
	}
	/*@GetMapping("addLoan")
	public String addLoan(@ModelAttribute("empLoanModel") EmpLoanModel empLoanModel,Model model, Locale locale, HttpSession session){
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		// model.addAttribute("brokenPeriodModel", brokenPeriodModel);
		
		List<EmpLoanModel> lstEmpLoanModel=new ArrayList<>();
		
		
		lstEmpLoanModel=employeeLoanDetailsService.findAllEmpLoanDtls(messages.getUserName());
		
		model.addAttribute("lstEmpLoanModel", lstEmpLoanModel);
		
		model.addAttribute("language", locale.getLanguage());
		
		model.addAttribute("standardDate", new Date());
		
		List<EmpLoanModel> lstEmp=new ArrayList<>();
		
		
		lstEmp=employeeLoanDetailsService.findAllEmpLoanDtls(messages.getDdoCode());
		
		model.addAttribute("lstEmp", lstEmp);
		
		
		model.addAttribute("standardDate", new Date());
		////return "/views/addEmployeeLoanDetails";
		return "/views/emp-loan-details";
	}*/
	@RequestMapping("addLoan")
	public String addLoan(@ModelAttribute("empLoanModel") EmpLoanModel empLoanModel,Model model, Locale locale, HttpSession session,RedirectAttributes redirectAttributes) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		
		
		model.addAttribute("lstCommonMstLoanAndAdvance",employeeLoanDetailsService.findLoanNames());
		model.addAttribute("empLoanModel", empLoanModel);
		model.addAttribute("status","1");
		return "/views/add-loan";
	}
	
	
	@PostMapping("/getEmpInfoBySevaarthId/{sevaarthId}")
	public ResponseEntity<List<EmpLoanModel>> getEmpInfoBySevaarthId(@PathVariable String sevaarthId,Model model, Locale locale, HttpSession session,RedirectAttributes redirectAttributes){
		//String message = (String) model.asMap().get("message");
		//UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<EmpLoanModel> empModel=employeeLoanDetailsService.getEmpInfoBySevaarthId(sevaarthId,messages.getDdoCode());
		return ResponseEntity.ok(empModel);
	}
	@PostMapping("/saveEmployeeLoanDtls")
	public String saveLoanEmpDetails(@ModelAttribute("empLoanModel") EmpLoanModel empLoanModel,Model model, Locale locale, HttpSession session,RedirectAttributes redirectAttributes){
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Long afterSaveId = employeeLoanDetailsService.saveEmployeeLoanDtls(empLoanModel);
		if (afterSaveId > 0) { 
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		return "redirect:/ddoast/employeeLoanDetails";
	}
	
	@GetMapping("/findSavedEmpLoanDtls/{sevaarthId}")
	public String findSavedEmpLoanDtls(@ModelAttribute("empLoanModel") EmpLoanModel empLoanModel, Model model,
			Locale locale, HttpSession session, @PathVariable String sevaarthId) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		model.addAttribute("systemdate", sdf.format(new Date()));
		
		empLoanModel = employeeLoanDetailsService.findSavedEmpLoanDtls(sevaarthId);
		model.addAttribute("lstCommonMstLoanAndAdvance",employeeLoanDetailsService.findLoanNames());
		model.addAttribute("status","2");
		model.addAttribute("empLoanModel", empLoanModel);
		
		addMenuAndSubMenu(model,messages);	
		return "/views/add-loan";
	
	}
}
