package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstDesignationModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstDesignationService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/mdc")
public class MstDesignationController  extends BaseController {

	@Autowired
	MstDesignationService mstDesignationService;

	/*@Autowired
	MstSubDepartmentService mstSubDepartmentService;*/

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@GetMapping("/mstDesignation")
	public String designationMaster(@ModelAttribute("mstDesignationModel") MstDesignationModel mstDesignationModel,
			Model model, Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
	//	mstDesignationModel.setDesignationCode(Integer.valueOf(commonHomeMethodsService.findCodeSeq("designation_code","designation_mst"))); 
		model.addAttribute("mstDesignationModel", mstDesignationModel);

		/* Optional<String> checkNull = Optional.ofNullable(commonHomeMethodsService.findCodeSeq("designation_code","designation_mst"));  
	        if(checkNull.isPresent()){  // check for value is present or not  
	        	mstDesignationModel.setDesignationCode(commonHomeMethodsService.findCodeSeq("designation_code","designation_mst"));
	        }else  
	        	mstDesignationModel.setDesignationCode(1); 
	        
	        */
	        model.addAttribute("cadreGroupsLst", commonHomeMethodsService
					.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.CADRE_GROUP));   
	       
	        OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
			addMenuAndSubMenu(model,messages);	

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		if (message != null && message.equals("UPDATED")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.UPDATED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.UPDATED_ENGLSH, STATUS.SUCCESS, model);
			}
		}
		//List<MstSubDepartmentEntity> lstFieldDepartment = mstSubDepartmentService.findAllSubDepartment();
		//model.addAttribute("lstFieldDepartment", lstFieldDepartment);

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);
       
		model.addAttribute("lstDesignation", mstDesignationService.getDesignationMstData(locale.getLanguage()));
		model.addAttribute("lstCadre", mstDesignationService.getCadre());
		model.addAttribute("language", locale.getLanguage());
		return "/views/mst-designation";
	}

	@GetMapping(value = "/getCadreDesc", headers = "Accept=application/json", produces = "application/json;charset=UTF-8") // ,
																															// method
																															// =
																															// RequestMethod.POST
	/*
	 * public ResponseEntity<String> getCadreDesc(@RequestHeader HttpHeaders
	 * headers, @RequestParam("fieldDepartmrntID") Integer fieldDepartmrntID,
	 * HttpSession session, UriComponentsBuilder ucBuilder,Locale locale) {
	 */
	public ResponseEntity<String> getCadreDesc(@RequestHeader HttpHeaders headers, HttpSession session,
			UriComponentsBuilder ucBuilder, Locale locale) {
		try {
			String resJson = "";
			/*
			 * resJson =
			 * mstDesignationService.getCadreDesc(fieldDepartmrntID,locale.getLanguage());
			 */
			return ResponseEntity.ok(resJson);
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@PostMapping("/saveDesignationMst")
	public String saveDesignationMst(@ModelAttribute("mstCadreModel") @Valid MstDesignationModel mstDesignationModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,HttpSession session) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/mst-designation";  
		}
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		Long afterSaveId = mstDesignationService.saveDesignationMst(mstDesignationModel,messages.getUserId());
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		model.addAttribute("language", locale.getLanguage());
		return "redirect:/mdc/mstDesignation";  
	}

	@RequestMapping(value = "/editDesg/{designationId}") // , method = RequestMethod.POST
	public String editDesg(@ModelAttribute("mstDesignationModel") MstDesignationModel mstDesignationModel,@PathVariable Long designationId, RedirectAttributes redirectAttributes, Model model,
			Locale locale, HttpSession session) {

		MstDesignationEntity objDesgEntity = mstDesignationService.findMstDesgByDesgId(designationId);

	    model.addAttribute("cadreGroupsLst", commonHomeMethodsService
				.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.CADRE_GROUP));   
		model.addAttribute("desgination_code", objDesgEntity.getDesginationCode());
		model.addAttribute("desgination", objDesgEntity.getDesgination());
		model.addAttribute("designationShortName", objDesgEntity.getDesignationShortName());
		

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		 model.addAttribute("lstddcPayCommission", lstddcPayCommission); 
		model.addAttribute("mstDesgEntity", mstDesignationService.findMstDesgByDesgId(designationId));

		model.addAttribute("language", locale.getLanguage());


		model.addAttribute("lstCadre", mstDesignationService.getCadre());

		return "/views/edit-mst-designation";
	}
	
	@PostMapping("/editDesgSave")
	public String editDesgSave(@ModelAttribute("mstDesgEntity") @Valid MstDesignationEntity mstDesgEntity,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,HttpSession session) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/edit-mst-designation";
		}
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		mstDesgEntity.setCreatedUserId(messages.getUserId());
		String message = mstDesignationService.editDesgSave(mstDesgEntity,messages.getUserId());
		if (message.equals("UPDATED")) {
			redirectAttributes.addFlashAttribute("message", "UPDATED");
		}
		return "redirect:/mdc/mstDesignation";
	}

	@RequestMapping(value = "/deleteDesg/{designationId}") // , method = RequestMethod.POST
	public String deleteDesg(@PathVariable long designationId, Model model, Locale locale,HttpSession session) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		MstDesignationEntity mstCadreEntity = mstDesignationService.findMstDesgByIdForReject(designationId,messages.getUserId());
		if (mstCadreEntity != null) {
			model.addAttribute("mstDesignationModel", new MstDesignationModel());
			model.addAttribute("lstDesignation", mstDesignationService.getDesignationMstData(locale.getLanguage()));
			model.addAttribute("language", locale.getLanguage());
		}
		return "views/mst-designation";
	}
	
	@RequestMapping("/validateDesignationName/{desgname}")
	public @ResponseBody List<Long> validateDesignationName(@PathVariable String desgname, Model model, Locale locale) {

		List<Long> status = mstDesignationService.validateDesignationName(desgname);
		return status;
	}

}
