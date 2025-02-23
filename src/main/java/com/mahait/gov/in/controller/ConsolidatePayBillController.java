/**
 * 
 */
package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.ConsolidatePayBillModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.ConsolidatePayBillService;
import com.mahait.gov.in.service.MstSchemeService;
import com.mahait.gov.in.service.PayBillViewApprDelBillService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.ViewDelConsolidatePayBillService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class ConsolidatePayBillController extends BaseController {
//	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	MstSchemeService mstSchemeService;

	@Autowired
	PayBillViewApprDelBillService payBillViewApprDelBill;

	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;

	@Autowired
	ConsolidatePayBillService consolidatePayBillService;

	@Autowired
	ViewDelConsolidatePayBillService viewDelConsolidatePayBillService;

	@GetMapping("/consolidatePayBill")
	public String consolidatePayBill(
			@ModelAttribute("consolidatePayBillModel") ConsolidatePayBillModel consolidatePayBillModel, Model model,
			Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstSchemeCode", mstSchemeService.findAllSchemeforConsolidate(messages.getDdoCode()));
		model.addAttribute("lstBillStatus", commonHomeMethodsService.lstGetAllBillStatusForConsolidatePaybill());

		consolidatePayBillModel.setLstCons(consolidatePayBillService.fetchDDOLst(messages.getDdoCode()));
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("userId", messages.getUserId());
		model.addAttribute("consolidatePayBillModel", consolidatePayBillModel);
		addMenuAndSubMenu(model, messages);
		return "/views/paybill/consolidate-paybill";
	}

	@PostMapping(value = "/searchConsolidatedPaybill")
	public String searchConsolidatedPaybill(
			@ModelAttribute("consolidatePayBillModel") ConsolidatePayBillModel consolidatePayBillModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,
			HttpSession session) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		consolidatePayBillModel
				.setLstCons(consolidatePayBillService.searchConsolidatedPaybill(consolidatePayBillModel, messages));
		model.addAttribute("consolidatePayBillModel", consolidatePayBillModel);

		return "/views/paybill/consolidate-paybill-details";
	}

	@RequestMapping(value = "/saveConsolidatePayBill")
	public String saveConsolidatePayBill(
			@ModelAttribute("consolidatePayBillModel") ConsolidatePayBillModel consolidatePayBillModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,
			HttpSession session) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		int afterSaveId = consolidatePayBillService.saveConsolidatePayBill(consolidatePayBillModel, messages);
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		} else {
			redirectAttributes.addFlashAttribute("messages", "SUCCESS");
		}

		return "/views/paybill/view-delete-consolidated-paybill";
	}

	@RequestMapping("/rejectConsolidatePaybill/{ddoCode}/{billNo}")
	public String updateApproveStatus(
			@ModelAttribute("consolidatePayBillModel") ConsolidatePayBillModel consolidatePayBillModel,
			@PathVariable String ddoCode, @PathVariable Long billNo, Model model, Locale locale, HttpSession session,
			HttpServletRequest request) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		PaybillGenerationTrnEntity status = consolidatePayBillService.rejectConsolidatePaybill(ddoCode, billNo);

		addMenuAndSubMenu(model, messages);
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		if (status != null)
			model.addAttribute("message", "Consolidate Paybill Rejected Successfully");
		return "redirect:/ddo/consolidatePayBill";
		/// }
	}

	@GetMapping("/viewApproveConsolidatedBill")
	public String viewApproveConsolidatedBill(
			@ModelAttribute("consolidatePayBillModel") ConsolidatePayBillModel consolidatePayBillModel, Model model,
			Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		consolidatePayBillModel
				.setLstCons(consolidatePayBillService.fetchDDOLstForConsolidateApproval(messages.getDdoCode()));
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("userId", messages.getUserId());
		model.addAttribute("consolidatePayBillModel", consolidatePayBillModel);
		addMenuAndSubMenu(model, messages);
		return "/views/paybill/approve-consolidate-paybill";
	}

	@PostMapping(value = "/approveConsolidateBill")
	public String approveConsolidateBill(
			@ModelAttribute("consolidatePayBillModel") ConsolidatePayBillModel consolidatePayBillModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		int afterSaveId = consolidatePayBillService.approveConsolidateBill(consolidatePayBillModel.getConsolidateId());
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		} else {
			redirectAttributes.addFlashAttribute("messages", "SUCCESS");
		}
		return "redirect:/ddo/viewApproveConsolidatedBill";
	}

	@RequestMapping("/deleteConsolidateBill/{consPaybillGenerationTrnId}")
	public ResponseEntity<String> deleteConsolidateBill(@PathVariable Long consPaybillGenerationTrnId, Model model,
			Locale locale, HttpSession session, HttpServletRequest request) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String status = consolidatePayBillService.deleteConsolidateBill(consPaybillGenerationTrnId);
		if (status != null) {
			return ResponseEntity.ok("1");
		} else {
			return ResponseEntity.ok("0");
		}

	}
}