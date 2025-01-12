package com.mahait.gov.in.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.InstituteType;
import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstDesnModel;
import com.mahait.gov.in.model.OrganisationDtlsModel;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.OrganisationDtlsService;
import com.mahait.gov.in.service.OrganizationInstInfoService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value= {"/ddoast","/ddo"})
public class OrganisationDtlsController   extends BaseController {

	@Autowired
	OrganisationDtlsService organisationDtlsService;

	@Autowired
	OrganizationInstInfoService organizationInstInfoService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@RequestMapping("/ddoOfficeDetails")
	public ModelAndView ddoOfficeDetails(Locale locale, HttpSession session, Model model,
			@ModelAttribute("organisationDtlsModel") @Valid OrganisationDtlsModel organisationDtlsModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		// logger.info("For Testing Logger *****");
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();

		organisationDtlsModel = organisationDtlsService.lstOfficeDetails(messages.getDdoCode());
		
		
		String adminDeptName = organisationDtlsService.getDeptNameByLocCode(organisationDtlsModel.getDeptLocCode(),
				locale.getLanguage());
		String fieldDeptName = organisationDtlsService.getDeptNameByLocCode(organisationDtlsModel.getHodLocCode(),
				locale.getLanguage());
		
		organisationDtlsModel.setAdminDeptName(adminDeptName);
		organisationDtlsModel.setFieldDeptName(fieldDeptName);
		
		
		//DCPSDDOOffice
	    List<CmnLookupMst> lstOfficeClass = commonHomeMethodsService.getLookupValues("DCPS_OFFICE_CLASS",CommonConstants.Languages.English);
	     model.addAttribute("lstOfficeClass", lstOfficeClass);
		
		/*
		 * 
		 * officeMaster = commonHomeMethodsService.lstGetOfficeMaster();
		 */
		List<MstBankEntity> bankName = new ArrayList<>();
		List<MstDesnModel> lstdesgination = new ArrayList<>();

		bankName = commonHomeMethodsService.findBankName();
		lstdesgination = commonHomeMethodsService.findDesignation(messages.getUserName());

		// orgDdoMstModel=organizationInstInfoService.findDDOInfo(ddoCode);

		List<InstituteType> lstInstituteType = organizationInstInfoService.lstInstType();

		if (organisationDtlsModel.getBankName() != null) {
			model.addAttribute("lstAllBankBranchList",
					commonHomeMethodsService.getBankBranch(organisationDtlsModel.getBankName()));
		}
		modelAndView.addObject("menuList", menuList);
		modelAndView.addObject("subMenuList", subMenuList);
		modelAndView.addObject("lstdesgination", lstdesgination);
		modelAndView.addObject("lstDistrict", commonHomeMethodsService.lstGetAllDistrict());
		// modelAndView.addObject("lstTaluka",
		// commonHomeMethodsService.lstGetAllTaluka());
		// modelAndView.addObject("lstcity", commonHomeMethodsService.lstGetAllCity());
		Map<String, Object> response1 = organisationDtlsService
				.findDataByDistrict(organisationDtlsModel.getDistrictId());
		if (response1.size() > 0) {
			modelAndView.addObject("lsttaluka", response1.get("talukaList"));
			modelAndView.addObject("lstcity", response1.get("cityList"));
		}
		
		
		
		modelAndView.setViewName("/views/org-detail-com-info");
		
		
		
		if(organisationDtlsModel.getCityClass()==null  && organisationDtlsModel.getCity()!=null) {
			organisationDtlsModel.setCityClass(organisationDtlsModel.getCity());
		}
		
		
		modelAndView.addObject("organisationDtlsModel", organisationDtlsModel);
		
		
		
		modelAndView.addObject("language", locale.getLanguage());
		model.addAttribute("bankName", bankName);
		model.addAttribute("lstInstituteType", lstInstituteType);
		model.addAttribute("language", locale.getLanguage());
		// model.addAttribute("lstStates", commonHomeMethodsService.lstGetAllState());
		
		
		addMenuAndSubMenu(modelAndView,messages);
		return modelAndView;
	}

	@PostMapping("/saveddoOfficeDetails")
	public String SaveddoOfficeDetails(
			@ModelAttribute("organisationDtlsModel") @Valid OrganisationDtlsModel organisationDtlsModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			return "/views/org-detail-com-info";
		}
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Long afterSaveId = organisationDtlsService.SaveorgInstituteInfo(organisationDtlsModel, messages);
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		return "redirect:/ddoast/ddoOfficeDetails";
	}

	@RequestMapping(value = "/findDataByDistrict/{districtId}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> findDataByDistrict(@PathVariable String districtId) {
		Map<String, Object> response1 = organisationDtlsService.findDataByDistrict(districtId);
		return ResponseEntity.ok(response1);
	}


	@RequestMapping(value = "/getCityClassByCity/{city}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCityClassByCity(@PathVariable String city) {
		return ResponseEntity.ok(commonHomeMethodsService.getCityClassByCity(city));
	}

	// @{/ddoast/editddoOfficeDetails}

	@RequestMapping("/editddoOfficeDetails")
	public ModelAndView editddoOfficeDetails(Locale locale, HttpSession session, Model model,
			@ModelAttribute("organisationDtlsModel") @Valid OrganisationDtlsModel organisationDtlsModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		// logger.info("For Testing Logger *****");
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();

		String[] ddo = messages.getUserName().split("_");

		String ddoCode = ddo[0];

		organisationDtlsModel = organisationDtlsService.lstOfficeDetails(ddoCode);

		/*
		 * 
		 * officeMaster = commonHomeMethodsService.lstGetOfficeMaster();
		 */

		modelAndView.addObject("menuList", menuList);
		modelAndView.addObject("subMenuList", subMenuList);
		modelAndView.addObject("lstDistrict", commonHomeMethodsService.lstGetAllDistrict());
		// modelAndView.addObject("lstTaluka",
		// commonHomeMethodsService.lstGetAllTaluka());
		// modelAndView.addObject("lstcity", commonHomeMethodsService.lstGetAllCity());

		modelAndView.setViewName("/views/edit-organization-details");
		modelAndView.addObject("organisationDtlsModel", organisationDtlsModel);
		modelAndView.addObject("language", locale.getLanguage());
		
		addMenuAndSubMenu(modelAndView,messages);
		// model.addAttribute("lstStates", commonHomeMethodsService.lstGetAllState());
		return modelAndView;
	}

	@PostMapping("/updateddoOfficeDetails")
	public String updateddoOfficeDetails(
			@ModelAttribute("organisationDtlsModel") OrganisationDtlsModel organisationDtlsModel, HttpSession session,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		organisationDtlsModel.setDdoCode(messages.getUserName());
		int message = organisationDtlsService.updateddoOfficeDetails(organisationDtlsModel, messages);
		if (message > 0) {
			redirectAttributes.addFlashAttribute("message", "Record Updated Successfully");

		}

		return "redirect:/ddoast/ddoOfficeDetails"; /* redirects to controller URL */
	}
}
