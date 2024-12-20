package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.BillgroupMaintainanceModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DdoBillGroupService;
import com.mahait.gov.in.service.MstSchemeService;
import com.mahait.gov.in.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddoast")

public class BillgroupMaintainanceController  extends BaseController {

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	DdoBillGroupService ddoBillGroupService;

	@Autowired
	MstSchemeService mstSchemeService;

	// @Autowired
	// MstBillGroupService mstBillGroupService;
	//
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/billgroupMaintainance")
	public String billgroupMaintainance(
			@ModelAttribute("billgroupMaintainanceModel") BillgroupMaintainanceModel billgroupMaintainanceModel,
			Model model, Locale locale, HttpSession session) {
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Long Postid = messages.getCreatedByPost().getPostId();
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstAllScheme", mstSchemeService.findAllScheme(messages.getDdoCode()));
		model.addAttribute("lstBillName", ddoBillGroupService.lstBillName(messages.getDdoCode()));

		addMenuAndSubMenu(model,messages);	
		
		return "/views/billgroupMaintainance";
	}

	@PostMapping("/saveBillgroupMaintainance")
	public String saveBillgroupMaintainance(
			@ModelAttribute("billgroupMaintainanceModel") BillgroupMaintainanceModel billgroupMaintainanceModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = Long.parseLong((String) session.getAttribute("locationId"));
		long afterSaveId = ddoBillGroupService.saveBillGroupMaintainance(billgroupMaintainanceModel, messages, locId);
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		// model.addAttribute("lstDeptDataTable",
		// mstDepartmentService.findAllDepartment());
		return "redirect:/ddoast/billgroupMaintainance"; /* redirects to controller URL */
	}

	@RequestMapping(value = "/getSchemeCodeAgainstName/{schemeId}") // , method = RequestMethod.POST
	public @ResponseBody List<MstScheme> getSchemeCodeAgainstName(@PathVariable String schemeId, Model model,
			Locale locale) {

		List<MstScheme> mstscheme = ddoBillGroupService.getSchemeCodeAgainstName(schemeId);
		return mstscheme;
	}

	// @GetMapping(value="/getBillDtlsForAlreadySaved/{billGrpId}")
	// public @ResponseBody List<Object[]> getBillDtlsForAlreadySaved(@PathVariable
	// String billGrpId,Model model,Locale locale)
	// {
	// List<Object[]> lst =
	// ddoBillGroupService.getBillDtlsForAlreadySaved(billGrpId);
	// return lst;
	//
	//
	// }

	@RequestMapping(value = "/getBillDtlsForAlreadySaved/{billGrpId}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getddoInfo(@PathVariable String billGrpId) {
		Map<String, Object> response1 = ddoBillGroupService.getBillDtlsForAlreadySaved(billGrpId);
		return ResponseEntity.ok(response1);
	}

}
