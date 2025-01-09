package com.mahait.gov.in.nps.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.controller.BaseController;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;
import com.mahait.gov.in.nps.service.VerifyDcpsLegacyService;
import com.mahait.gov.in.service.CommonHomeMethodsService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddo")
@Controller
public class VerifyDcpsLegacyController extends BaseController {
	
	
	@Autowired
	VerifyDcpsLegacyService verifyDcpsLegacyService;
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@RequestMapping("/verifyDcpsLegacy")
	public String verifyDcpsLegacy(Model model, Locale locale, HttpSession session,@ModelAttribute("dcpsLegacyModel") DcpsLegacyModel dcpsLegacyModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

	     List<CmnLookupMst> lLstLegValidatePeriod = commonHomeMethodsService.getLookupValues("validatePeriod",CommonConstants.Languages.English);
	     model.addAttribute("lLstLegValidatePeriod", lLstLegValidatePeriod);
		
		if (dcpsLegacyModel.getAction() != null && dcpsLegacyModel.getAction().equals("search")) {
			String locationId=(String) session.getAttribute("locationId");
			
			List<DcpsLegacyModel> lstDcpsLegacyModel = verifyDcpsLegacyService.findLegacyData(dcpsLegacyModel,messages,locationId);
			dcpsLegacyModel.setLstDcpsLegacyModel(lstDcpsLegacyModel);
			
			if(lstDcpsLegacyModel.size()==0) {
				model.addAttribute("noDataFoundMsg", "No Data found for searched period ");
			}
			
		}
		model.addAttribute("dcpsLegacyModel", dcpsLegacyModel);
		return "/views/nps/verify-dcps-legacy";
	}
	
	@RequestMapping("/verifySavedData")
	public ResponseEntity<Integer> verifySavedData(@RequestBody Map<String, String> inputParam, Model model,
			HttpSession session, Locale locale) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Integer isVerified= verifyDcpsLegacyService.verifySavedData(inputParam,messages);
		return ResponseEntity.ok(isVerified);
	}
	
	@RequestMapping("/rejectSavedData")
	public ResponseEntity<Integer> rejectSavedData(@RequestBody Map<String, String> inputParam, Model model,
			HttpSession session, Locale locale) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Integer isVerified= verifyDcpsLegacyService.rejectSavedData(inputParam,messages);
		return ResponseEntity.ok(isVerified);
	}
	
	
	
	

}
