package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmployeeAllowDeducComponentAmtModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DeptEligibilityForAllowAndDeductService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/ddoast")
@Controller
public class NonComputationalDuesDeductionsController extends BaseController {
	
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	DeptEligibilityForAllowAndDeductService deptEligibilityForAllowAndDeductService;
	
	@GetMapping("nonComputationalDuesDeductions")
	public String nonComputationalDuesDeductions(Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/non-computational-dues-deductions";
	}
	
	
	
	@GetMapping("/nonGovDuesDeduction")
	public String adminOfficeMaster(@ModelAttribute("employeeAllowDeducComponentAmtModel") EmployeeAllowDeducComponentAmtModel employeeAllowDeducComponentAmtModel,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		if(message != null && message.equals("SUCCESS")) {
			if(locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("language", locale.getLanguage());
		
		model.addAttribute("lstdeptEligibilityForAllowAndDeduct", deptEligibilityForAllowAndDeductService.findDeptNonGovDeductList());
		addMenuAndSubMenu(model,messages);
		
		return "/views/NonGovernmentDuesAndDeduction";
    }
	
	
	@PostMapping(value = "/saveEmployeeNonGovDuesDeduct")
	public String saveEmployeeNonGovDuesDeduct(@ModelAttribute("employeeAllowDeducComponentAmtModel") @Valid EmployeeAllowDeducComponentAmtModel employeeAllowDeducComponentAmtModel, 
			BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {	
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
//		 logger.info("employeeAllowDeducComponentAmtModel="+employeeAllowDeducComponentAmtModel);
//		 logger.info("Inside saveEmployeeNonGovDuesDeduct");
			int afterSaveId = deptEligibilityForAllowAndDeductService.saveEmployeeNonGovDuesDeduct(employeeAllowDeducComponentAmtModel, messages);
			if(afterSaveId > 0) {
				redirectAttributes.addFlashAttribute("message","SUCCESS");
			}
			
			return "redirect:/ddoast/nonGovDuesDeduction";
		}
}
