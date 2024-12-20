package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DcpContributionModel;
import com.mahait.gov.in.service.DCPSOnlineContributionEntryService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/master")
@Controller
public class DCPSOnlineContributionEntryController extends BaseController {
	
	@Autowired
	DCPSOnlineContributionEntryService dCPSOnlineContributionEntryService;
	
	
	@GetMapping("/onlineContributionEntry")
	public String onlineContributionEntry(Model model, Locale locale,
			@ModelAttribute("dcpContributionModel") DcpContributionModel dcpContributionModel, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		List<MstDcpsBillGroup> lstMstDcpsBillGroup=dCPSOnlineContributionEntryService.findAllBillGroup(messages);
		List<CmnLocationMst> lstTreasury=dCPSOnlineContributionEntryService.findTreasuryByDdoCode(messages);
		
		model.addAttribute("dcpContributionModel", dcpContributionModel);
		model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstMstDcpsBillGroup", lstMstDcpsBillGroup);
		model.addAttribute("lstTreasury", lstTreasury);
		
		addMenuAndSubMenu(model, messages);
		
		return "/views/DCPS-Online-Contribution-Entry";
	}

}
