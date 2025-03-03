package com.mahait.gov.in.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.InstituteType;
import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstDesnModel;
import com.mahait.gov.in.model.OrgDdoMstModel;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.OrganizationInstInfoService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ddoast")
public class OrganizationInstInfoController extends BaseController {
	// protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	OrganizationInstInfoService organizationInstInfoService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@GetMapping("/orgInstituteInfo")
	/* @GetMapping(value="/orgInstituteInfo/{remoteUser}") */
	public String adminOfficeMaster(@ModelAttribute("orgDdoMstModel") OrgDdoMstModel orgDdoMstModel, Model model,
			Locale locale, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String message = (String) model.asMap().get("message");
		modelAndView.addObject("organizationDdoMstModel", new OrgDdoMstModel());

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		String ddoCode = messages.getDdoCode();

		// List<DDOScreenEntity>
		// lstDDO=dDOScreenService.findDDOByUsername(messages.getUserName());

		List<MstBankEntity> bankName = new ArrayList<>();
		List<MstDesnModel> lstdesgination = new ArrayList<>();

		bankName = commonHomeMethodsService.findBankName();
		lstdesgination = commonHomeMethodsService.findDesignation(messages.getUserName());

		orgDdoMstModel = organizationInstInfoService.findDDOInfo(ddoCode);

		List<InstituteType> lstInstituteType = organizationInstInfoService.lstInstType();

		if (orgDdoMstModel.getBankName() != null) {
			model.addAttribute("lstAllBankBranchList",
					commonHomeMethodsService.getBankBranch(orgDdoMstModel.getBankName()));
		}
		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("lstdesgination", lstdesgination);

		LocalDate localDate = LocalDate.now();
		java.util.Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		model.addAttribute("today", today);
		
		model.addAttribute("bankName", bankName);
		model.addAttribute("lstInstituteType", lstInstituteType);
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("orgDdoMstModel", orgDdoMstModel);

		addMenuAndSubMenu(model, messages);
		return "views/org-detail-com-info";
		// return "views/hte-organization-institution-info";
	}

	@PostMapping("/saveorgInstituteInfo")
	public String SaveorgInstituteInfo(@ModelAttribute("orgDdoMstModel") @Valid OrgDdoMstModel orgDdoMstModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		orgDdoMstModel.setDdoCode(messages.getDdoCode());
		Long afterSaveId = organizationInstInfoService.SaveorgInstituteInfo(orgDdoMstModel);
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		return "redirect:/ddoast/orgInstituteInfo";
	}

	@GetMapping("/editorgInstituteInfo")
	/* @GetMapping(value="/orgInstituteInfo/{remoteUser}") */
	public String editorgInstituteInfo(@ModelAttribute("orgDdoMstModel") OrgDdoMstModel orgDdoMstModel, Model model,
			Locale locale, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String message = (String) model.asMap().get("message");
		modelAndView.addObject("organizationDdoMstModel", new OrgDdoMstModel());

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		List<InstituteType> lstInstituteType = organizationInstInfoService.lstInstType();
		String ddoCode = messages.getDdoCode();

		// List<DDOScreenEntity>
		// lstDDO=dDOScreenService.findDDOByUsername(messages.getUserName());

		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();
		List<MstBankEntity> bankName = new ArrayList<>();
		List<MstDesnModel> lstdesgination = new ArrayList<>();

		bankName = commonHomeMethodsService.findBankName();
		lstdesgination = commonHomeMethodsService.findDesignation(messages.getUserName());

		orgDdoMstModel = organizationInstInfoService.findDDOInfo(ddoCode);

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}

		model.addAttribute("lstdesgination", lstdesgination);

		model.addAttribute("subMenuList", subMenuList);
		model.addAttribute("bankName", bankName);
		model.addAttribute("menuList", menuList);
		model.addAttribute("lstInstituteType", lstInstituteType);
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("orgDdoMstModel", orgDdoMstModel);

		addMenuAndSubMenu(model, messages);

		return "views/edit-hte-organization-institution-info";
	}

	@PostMapping("/updateorgInstituteInfo")
	public String updateorgInstituteInfo(@ModelAttribute("orgDdoMstModel") OrgDdoMstModel orgDdoMstModel,
			HttpSession session, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,
			Locale locale) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		orgDdoMstModel.setDdoCode(messages.getDdoCode());
		int message = organizationInstInfoService.updateorgInstituteInfo(orgDdoMstModel);
		if (message > 0) {
			redirectAttributes.addFlashAttribute("message", "Record Updated Successfully");

		}

		return "redirect:/ddoast/orgInstituteInfo"; /* redirects to controller URL */
	}

	@GetMapping(value = "/mstBank/{bankCode}")
	public @ResponseBody List<Object[]> multiply(@PathVariable int bankCode, Model model, Locale locale) {
		List<Object[]> mstBankBranch = commonHomeMethodsService.findAllBankBranchList(bankCode);
		// List<Object[]> mstEmployeeEntity= mstBankBranch;
		return mstBankBranch;
	}

	@RequestMapping(value = "/getIfscCodeByBranchId/{branchId}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getIfscCodeByBranchId(@PathVariable int branchId) {
		return ResponseEntity.ok(commonHomeMethodsService.getIfscCodeByBranchId(branchId));
	}

}
