package com.mahait.gov.in.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.AllowanceDeductionRuleMstEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DDOScreenModel;
import com.mahait.gov.in.service.AllowDeducRuleMasterService;
import com.mahait.gov.in.service.DeptEligibilityForAllowAndDeductService;
import com.mahait.gov.in.service.MstDesignationService;
import com.mahait.gov.in.service.WelcomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/mdc")
@Controller
public class AllowDeducRuleMasterController extends BaseController {

	@Autowired
	MstDesignationService mstDesignationService;

	@Autowired
	AllowDeducRuleMasterService allowDeducRuleMasterService;

	@Autowired
	DeptEligibilityForAllowAndDeductService deptEligibilityForAllowAndDeductService;

	@Autowired
	WelcomeService welcomeService;

	@RequestMapping("/loadRuleMaster")
	public ModelAndView loadRuleMaster(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session,
			@ModelAttribute("allowanceDeductionRuleMstEntity") AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity) {
		String message = (String) model.asMap().get("message");

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("context", request.getContextPath());
		modelAndView.addObject("language", locale.getLanguage());

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		session.setAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("sessionMessages", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("userName", messages.getUserName());

		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();

		model.addAttribute("lstAllowanceDeductionMstEntity", allowDeducRuleMasterService.findAllRules(0)); // 0 for All
																											// Component

		model.addAttribute("lstddcPayCommission", lstddcPayCommission);
		modelAndView.addObject("message", message);
		modelAndView.addObject("allowanceDeductionRuleMstEntity", allowanceDeductionRuleMstEntity);

		model.addAttribute("lstdeptEligibilityForAllowAndDeduct",
				deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());

		addMenuAndSubMenu(modelAndView, messages);
		modelAndView.setViewName("/views/allow-deduc-rule-master");
		return modelAndView;
	}

	@RequestMapping(value = "/findRuleByComponentCode/{departmentAllowdeducCode}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AllowanceDeductionRuleMstEntity>> findRuleByComponentCode(
			@PathVariable int departmentAllowdeducCode) throws ParseException {
		List<AllowanceDeductionRuleMstEntity> lst = allowDeducRuleMasterService.findAllRules(departmentAllowdeducCode);
		return ResponseEntity.ok(lst);
	}

	@RequestMapping("/saveAllowanceDeductionRulesMaster")
	public ModelAndView saveAllowanceDeductionRulesMaster(HttpServletRequest request, Model model,
			HttpServletResponse response, Locale locale, HttpSession session,
			@ModelAttribute("allowanceDeductionRuleMstEntity") AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity,
			RedirectAttributes redirectAttributes) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		allowanceDeductionRuleMstEntity.setCreatedUserId(messages.getUserId());
		int isSave = 0;

		isSave = allowDeducRuleMasterService.saveAllowanceDeductionRulesMaster(allowanceDeductionRuleMstEntity);
		if (isSave > 0) {
			redirectAttributes.addFlashAttribute("message", "Rule Save Successfully");
		} else {
			redirectAttributes.addFlashAttribute("message", "Rule Save Successfully");
		}
		return new ModelAndView("redirect:/mdc/loadRuleMaster");
	}

	@RequestMapping("/updateAllowanceDeductionRulesMaster")
	public ModelAndView updateAllowanceDeductionRulesMaster(HttpServletRequest request, Model model,
			HttpServletResponse response, Locale locale, HttpSession session,
			@ModelAttribute("allowanceDeductionRuleMstEntity") AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity,
			RedirectAttributes redirectAttributes) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		allowanceDeductionRuleMstEntity.setCreatedUserId(messages.getUserId());
		int isSave = 0;

		isSave = allowDeducRuleMasterService.updateAllowanceDeductionRulesMaster(allowanceDeductionRuleMstEntity);
		if (isSave > 0) {
			redirectAttributes.addFlashAttribute("message", "Rule Updated Successfully");
		} else {
			redirectAttributes.addFlashAttribute("message", "Rule Updated Successfully");
		}
		return new ModelAndView("redirect:/mdc/loadRuleMaster");
	}

	@RequestMapping("/editAllowanceDeductionRulesMaster/{allowanceDeductionWiseRuleId}")
	public ModelAndView editAllowanceDeductionRulesMaster(HttpServletRequest request, Model model,
			@ModelAttribute("allowanceDeductionRuleMstEntity") AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity,
			HttpServletResponse response, Locale locale, HttpSession session,
			@PathVariable int allowanceDeductionWiseRuleId, RedirectAttributes redirectAttributes)
			throws ParseException {
		String message = (String) model.asMap().get("message");

		allowanceDeductionRuleMstEntity = allowDeducRuleMasterService.findRuleByRuleId(allowanceDeductionWiseRuleId);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("context", request.getContextPath());
		modelAndView.addObject("language", locale.getLanguage());

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		session.setAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("sessionMessages", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("userName", messages.getUserName());

		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();

		modelAndView.addObject("lstAllowanceDeductionMstEntity", allowDeducRuleMasterService.findAllRules());

		model.addAttribute("lstddcPayCommission", lstddcPayCommission);
		modelAndView.addObject("message", message);
		modelAndView.addObject("allowanceDeductionRuleMstEntity", allowanceDeductionRuleMstEntity);

		modelAndView.addObject("lstdeptEligibilityForAllowAndDeduct",
				deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());

		addMenuAndSubMenu(modelAndView, messages);

		modelAndView.setViewName("/views/edit-allow-deduc-rule-master");
		return modelAndView;
	}

	@RequestMapping("/deleteRule/{id}/{status}")
	public ModelAndView deleteRule(@PathVariable int id, @PathVariable char status, Model model, Locale locale) {
		AllowanceDeductionRuleMstEntity allowanceDeductionMstEntity = allowDeducRuleMasterService.deleteRule(id,
				status);
		if (allowanceDeductionMstEntity != null) {
			model.addAttribute("ddoScreenModel", new DDOScreenModel());
			model.addAttribute("language", locale.getLanguage());
		}
		return new ModelAndView("redirect:/mdc/loadRuleMaster");
	}

	@RequestMapping("/permanantDeleteRule/{id}")
	public ModelAndView permanantDeleteRule(@PathVariable int id,Model model, Locale locale) {
		AllowanceDeductionRuleMstEntity allowanceDeductionMstEntity = allowDeducRuleMasterService.permanentDeleteRule(id);
		if (allowanceDeductionMstEntity != null) {
			model.addAttribute("ddoScreenModel", new DDOScreenModel());
			model.addAttribute("language", locale.getLanguage());
		}
		return new ModelAndView("redirect:/mdc/loadRuleMaster");
	}
}
