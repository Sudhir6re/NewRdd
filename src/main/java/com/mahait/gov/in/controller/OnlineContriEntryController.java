package com.mahait.gov.in.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstDcpsContriVoucherDtlEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DcpContributionModel;
import com.mahait.gov.in.model.MstSchemeModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstDesignationService;
import com.mahait.gov.in.service.OnlineContributionService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpSession;

@RequestMapping(value = { "/ddoast", "/ddo" })
@Controller
public class OnlineContriEntryController extends BaseController {

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	RegularReportService regularReportService;

	@Autowired
	OnlineContributionService onlineContributionService;

	@Autowired
	MstDesignationService mstDesignationService;

	@RequestMapping("/onlineContriEntry")
	public String onlineContriEntry(@ModelAttribute("dcpContributionModel") DcpContributionModel dcpContributionModel,
			Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		String message = (String) model.asMap().get("message");
		model.addAttribute("message", message);

		dcpContributionModel.setUseType("ViewAll");

		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		
		Integer regStatus=0;
		
		List<MstDcpsBillGroup>  listMstDcpsBillGroup=onlineContributionService.findBillgroupList(messages,regStatus);
		
		
		model.addAttribute("lstBillDesc", listMstDcpsBillGroup);
		
		
		
		if(dcpContributionModel.getFinYearId()==0) {
			dcpContributionModel.setFinYearId((new Date().getYear()-99));
		}
		
		
		if(dcpContributionModel.getMonthId()==0) {
			dcpContributionModel.setMonthId(4);
		}
		

		model.addAttribute("ddoNameLst", regularReportService.getDDOName(messages.getDdoCode()));

		List<OrgDdoMst> lst = regularReportService.getDDOName(messages.getDdoCode());
		if (lst.size() > 0) {
			dcpContributionModel.setLocId(Long.valueOf(lst.get(0).getLocationCode()));
		}

		model.addAttribute("paymentTypeLst", onlineContributionService.getPaymentTypeLst());
		model.addAttribute("payCommisionLst", mstDesignationService.findAllPayCommission());

		String messageResponse = (String) model.asMap().get("message");
		if (messageResponse != null && messageResponse.equals("SUCCESS")) {
			model.addAttribute("message", "Contribution saved successfully !!!");
		}

		if (dcpContributionModel.getAction() != null && dcpContributionModel.getAction().equals("SEARCH_EMP")) {
			String startDate = null;

			int month2 = dcpContributionModel.getDelayedMonthId();
			int year2 = dcpContributionModel.getDelayedFinYearId();
		
			if (month2 < 10) {
				startDate = 20 + String.valueOf((year2 - 1)) + '-' + String.valueOf("0" + month2) + "-01";
			} else {
				startDate = 20 + String.valueOf((year2 - 1)) + '-' + String.valueOf(month2) + "-01";
			}
			
			
			dcpContributionModel.setUseType("ViewAll");

			Boolean isPaybillGenerated = onlineContributionService.checkIfBillAlreadyGenerated(
					dcpContributionModel.getBillGroupId(), dcpContributionModel.getMonthId(),
					dcpContributionModel.getFinYearId(),messages.getDdoCode());

			model.addAttribute("isPaybillGenerated", isPaybillGenerated);

			List<DcpContributionModel> dcpContributionModelLst = onlineContributionService
					.getEmpListForContribution(dcpContributionModel, messages, startDate);

			dcpContributionModel.setLstDcpContributionModel(dcpContributionModelLst);
			model.addAttribute("dcpContributionModelLst", dcpContributionModelLst);
			
			
			MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity = onlineContributionService.findMstDcpsContriVoucherDtlEntity(dcpContributionModel);
			if(mstDcpsContriVoucherDtlEntity!=null) {
				dcpContributionModel.setVoucherDate(mstDcpsContriVoucherDtlEntity.getVoucherDate());
				dcpContributionModel.setVoucherNo(mstDcpsContriVoucherDtlEntity.getVoucherNo());
			}
			
			

		}

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);

		dcpContributionModel.setAction("search");
		model.addAttribute("dcpContributionModel", dcpContributionModel);
		List<CmnLocationMst> lstTreasury = onlineContributionService.findTreasuryList(messages);
		model.addAttribute("lstTreasury", lstTreasury);

		return "/views/online-contri-entry";
	}

	
	@RequestMapping("/viewForwrdedOnlineContriEntry")
	public String viewForwrdedOnlineContriEntry(@ModelAttribute("dcpContributionModel") DcpContributionModel dcpContributionModel,
			Model model, Locale locale, HttpSession session) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		
		

		String message = (String) model.asMap().get("message");
		model.addAttribute("message", message);

		dcpContributionModel.setUseType("ViewAll");

		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		
		if(messages.getMstRoleEntity().getRoleId()==2) {
			Integer regStatus=0;
			List<MstDcpsBillGroup>  ListMstDcpsBillGroup=onlineContributionService.findBillgroupList(messages,regStatus);
			
			model.addAttribute("lstBillDesc", ListMstDcpsBillGroup);
		}
		
		
		model.addAttribute("ddoNameLst", onlineContributionService.getAllForwardedDdo(messages));
		List<OrgDdoMst> lst = regularReportService.getDDOName(messages.getDdoCode());
		if (lst.size() > 0) {
			dcpContributionModel.setLocId(Long.valueOf(lst.get(0).getLocationCode()));
		}
		model.addAttribute("paymentTypeLst", onlineContributionService.getPaymentTypeLst());
		model.addAttribute("payCommisionLst", mstDesignationService.findAllPayCommission());

		String messageResponse = (String) model.asMap().get("message");
		if (messageResponse != null && messageResponse.equals("SUCCESS")) {
			model.addAttribute("message", "Contribution saved successfully !!!");
		}

		if (dcpContributionModel.getAction() != null && dcpContributionModel.getAction().equals("SEARCH_EMP")) {
			String startDate = null;
			int month2=0;
			int year2=0;
            if(messages.getMstRoleEntity().getRoleId()==2) {
            	 month2 = dcpContributionModel.getMonthId();
    			 year2 = dcpContributionModel.getFinYearId();
            }else {
            	 month2 = dcpContributionModel.getDelayedMonthId();
    			 year2 = dcpContributionModel.getDelayedFinYearId();
            }
			
            if (month2 < 10) {
				startDate = 20 + String.valueOf(year2 - 1) + '-' + String.valueOf("0" + month2) + "-01";
			} else {
				startDate = 20 + String.valueOf(year2 - 1) + '-' + String.valueOf(month2) + "-01";
			}
			
			

			dcpContributionModel.setUseType("ViewForwarded");
			Boolean isPaybillGenerated = onlineContributionService.checkIfBillAlreadyGenerated(
					dcpContributionModel.getBillGroupId(), dcpContributionModel.getMonthId(),
					dcpContributionModel.getFinYearId(),dcpContributionModel.getDdoCode());

			model.addAttribute("isPaybillGenerated", isPaybillGenerated);

			List<DcpContributionModel> dcpContributionModelLst = onlineContributionService
					.getEmpListForContribution(dcpContributionModel, messages, startDate);

			dcpContributionModel.setLstDcpContributionModel(dcpContributionModelLst);
			model.addAttribute("dcpContributionModelLst", dcpContributionModelLst);
			
			MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity = onlineContributionService.findMstDcpsContriVoucherDtlEntity(dcpContributionModel);
			if(mstDcpsContriVoucherDtlEntity!=null) {
				dcpContributionModel.setVoucherDate(mstDcpsContriVoucherDtlEntity.getVoucherDate());
				dcpContributionModel.setVoucherNo(mstDcpsContriVoucherDtlEntity.getVoucherNo());
			}

		}

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);

		dcpContributionModel.setAction("search");
		model.addAttribute("dcpContributionModel", dcpContributionModel);
		List<CmnLocationMst> lstTreasury = onlineContributionService.findTreasuryList(messages);
		model.addAttribute("lstTreasury", lstTreasury);
		
		System.out.println("hhhh"+model.asMap().get("levelRoleVal").toString());

		return "/views/view-forwarded-online-contri-entry";
	}
	
	
	@RequestMapping("/viewApprovedOnlineContribution")
	public String viewApprovedOnlineContribution(@ModelAttribute("dcpContributionModel") DcpContributionModel dcpContributionModel,
			Model model, Locale locale, HttpSession session) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		
		

		String message = (String) model.asMap().get("message");
		model.addAttribute("message", message);

		dcpContributionModel.setUseType("ViewApproved");

		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		
		if(messages.getMstRoleEntity().getRoleId()==2) {
			Integer regStatus=1;
			List<MstDcpsBillGroup>  ListMstDcpsBillGroup=onlineContributionService.findBillgroupList(messages,regStatus);
			
			model.addAttribute("lstBillDesc", ListMstDcpsBillGroup);
		}
		
		
		model.addAttribute("ddoNameLst", onlineContributionService.getAllForwardedDdo(messages));
		List<OrgDdoMst> lst = regularReportService.getDDOName(messages.getDdoCode());
		if (lst.size() > 0) {
			dcpContributionModel.setLocId(Long.valueOf(lst.get(0).getLocationCode()));
		}
		model.addAttribute("paymentTypeLst", onlineContributionService.getPaymentTypeLst());
		model.addAttribute("payCommisionLst", mstDesignationService.findAllPayCommission());

		String messageResponse = (String) model.asMap().get("message");
		if (messageResponse != null && messageResponse.equals("SUCCESS")) {
			model.addAttribute("message", "Contribution saved successfully !!!");
		}

		if (dcpContributionModel.getAction() != null && dcpContributionModel.getAction().equals("SEARCH_EMP")) {
			String startDate = null;
			int month2=0;
			int year2=0;
            if(messages.getMstRoleEntity().getRoleId()==2) {
            	 month2 = dcpContributionModel.getMonthId();
    			 year2 = dcpContributionModel.getFinYearId();
            }else {
            	 month2 = dcpContributionModel.getDelayedMonthId();
    			 year2 = dcpContributionModel.getDelayedFinYearId();
            }
			
            if (month2 < 10) {
				startDate = 20 + String.valueOf(year2 - 1) + '-' + String.valueOf("0" + month2) + "-01";
			} else {
				startDate = 20 + String.valueOf(year2 - 1) + '-' + String.valueOf(month2) + "-01";
			}
			
			

			dcpContributionModel.setUseType("ViewApproved");
			Boolean isPaybillGenerated = onlineContributionService.checkIfBillAlreadyGenerated(
					dcpContributionModel.getBillGroupId(), dcpContributionModel.getMonthId(),
					dcpContributionModel.getFinYearId(),dcpContributionModel.getDdoCode());

			model.addAttribute("isPaybillGenerated", isPaybillGenerated);

			List<DcpContributionModel> dcpContributionModelLst = onlineContributionService
					.getEmpListForContribution(dcpContributionModel, messages, startDate);

			dcpContributionModel.setLstDcpContributionModel(dcpContributionModelLst);
			model.addAttribute("dcpContributionModelLst", dcpContributionModelLst);
			
			MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity = onlineContributionService.findMstDcpsContriVoucherDtlEntity(dcpContributionModel);
			if(mstDcpsContriVoucherDtlEntity!=null) {
				dcpContributionModel.setVoucherDate(mstDcpsContriVoucherDtlEntity.getVoucherDate());
				dcpContributionModel.setVoucherNo(mstDcpsContriVoucherDtlEntity.getVoucherNo());
			}

		}

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);

		dcpContributionModel.setAction("search");
		model.addAttribute("dcpContributionModel", dcpContributionModel);
		List<CmnLocationMst> lstTreasury = onlineContributionService.findTreasuryList(messages);
		model.addAttribute("lstTreasury", lstTreasury);
		
		System.out.println("hhhh"+model.asMap().get("levelRoleVal").toString());

		return "/views/view-approved-online-contri-entry";
	}
	
	
	@GetMapping("/viewRejectedContri")
	public String viewRejectedContri(Model model, Locale locale, HttpSession session,@ModelAttribute("dcpContributionModel") DcpContributionModel dcpContributionModel) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		String message = (String) model.asMap().get("message");
		model.addAttribute("message", message);

		dcpContributionModel.setUseType("ViewRejected");

		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		
		Integer regStatus=0;
		
		List<MstDcpsBillGroup>  listMstDcpsBillGroup=onlineContributionService.findBillgroupList(messages,regStatus);
		
		
		model.addAttribute("lstBillDesc", listMstDcpsBillGroup);
		
		
		
		if(dcpContributionModel.getFinYearId()==0) {
			dcpContributionModel.setFinYearId((new Date().getYear()-99));
		}
		
		
		if(dcpContributionModel.getMonthId()==0) {
			dcpContributionModel.setMonthId(4);
		}
		

		model.addAttribute("ddoNameLst", regularReportService.getDDOName(messages.getDdoCode()));

		List<OrgDdoMst> lst = regularReportService.getDDOName(messages.getDdoCode());
		if (lst.size() > 0) {
			dcpContributionModel.setLocId(Long.valueOf(lst.get(0).getLocationCode()));
		}

		model.addAttribute("paymentTypeLst", onlineContributionService.getPaymentTypeLst());
		model.addAttribute("payCommisionLst", mstDesignationService.findAllPayCommission());

		String messageResponse = (String) model.asMap().get("message");
		if (messageResponse != null && messageResponse.equals("SUCCESS")) {
			model.addAttribute("message", "Contribution saved successfully !!!");
		}

		if (dcpContributionModel.getAction() != null && dcpContributionModel.getAction().equals("SEARCH_EMP")) {
			String startDate = null;

			int month2 = dcpContributionModel.getDelayedMonthId();
			int year2 = dcpContributionModel.getDelayedFinYearId();
		
			if (month2 < 10) {
				startDate = 20 + String.valueOf((year2 - 1)) + '-' + String.valueOf("0" + month2) + "-01";
			} else {
				startDate = 20 + String.valueOf((year2 - 1)) + '-' + String.valueOf(month2) + "-01";
			}
			
			
			dcpContributionModel.setUseType("ViewRejected");

			Boolean isPaybillGenerated = onlineContributionService.checkIfBillAlreadyGenerated(
					dcpContributionModel.getBillGroupId(), dcpContributionModel.getMonthId(),
					dcpContributionModel.getFinYearId(),messages.getDdoCode());

			model.addAttribute("isPaybillGenerated", isPaybillGenerated);

			List<DcpContributionModel> dcpContributionModelLst = onlineContributionService
					.getEmpListForContribution(dcpContributionModel, messages, startDate);

			dcpContributionModel.setLstDcpContributionModel(dcpContributionModelLst);
			model.addAttribute("dcpContributionModelLst", dcpContributionModelLst);
			
			
			MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity = onlineContributionService.findMstDcpsContriVoucherDtlEntity(dcpContributionModel);
			if(mstDcpsContriVoucherDtlEntity!=null) {
				dcpContributionModel.setVoucherDate(mstDcpsContriVoucherDtlEntity.getVoucherDate());
				dcpContributionModel.setVoucherNo(mstDcpsContriVoucherDtlEntity.getVoucherNo());
			}
			
			

		}

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);

		dcpContributionModel.setAction("search");
		model.addAttribute("dcpContributionModel", dcpContributionModel);
		List<CmnLocationMst> lstTreasury = onlineContributionService.findTreasuryList(messages);
		model.addAttribute("lstTreasury", lstTreasury);
		return "/views/view-rejected-contri";
	}
	

	@RequestMapping("/saveOnlineContriEntry")
	public String saveOnlineContriEntry(
			@ModelAttribute("dcpContributionModel") DcpContributionModel dcpContributionModel, Model model,
			RedirectAttributes redirectAttributes, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		dcpContributionModel.setCreatedPostId(messages.getUserId());
		Long saveId = onlineContributionService.saveOrUpdate(dcpContributionModel, messages);

		redirectAttributes.addFlashAttribute("message", "Contribution Saved Successfully !!!");

		model.addAttribute("message", "SUCCESS");
		return "redirect:/ddoast/onlineContriEntry";
	}

	@RequestMapping("/approveOnlineContriEntry")
	public String approveOnlineContriEntry(
			@ModelAttribute("dcpContributionModel") DcpContributionModel dcpContributionModel, Model model,
			RedirectAttributes redirectAttributes, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		dcpContributionModel.setCreatedPostId(messages.getUserId());
		Long saveId = onlineContributionService.approveOnlineContriEntry(dcpContributionModel, messages);

		redirectAttributes.addFlashAttribute("message", "Contribution Saved Successfully !!!");

		model.addAttribute("message", "SUCCESS");
		return "redirect:/ddoast/onlineContriEntry";
	}

	
	
	@RequestMapping("/getSchemeCodeByBillGroupId/{billGroupId}")
	public @ResponseBody List<MstSchemeModel> getSchemeCodeByBillGroupId(@PathVariable String billGroupId, Model model,
			Locale locale) {
		List<MstSchemeModel> status = onlineContributionService.getSchemeCodeByBillGroupId(billGroupId);
		return status;
	}

	@PostMapping("/calculateDcpsArrear")
	public ResponseEntity<DcpContributionModel> calculateDcpsArrear(@RequestBody Map<String, String> formData) {
		DcpContributionModel lstDcpContributionModel = onlineContributionService.calculateDcpsArrear(formData);
		return ResponseEntity.ok(lstDcpContributionModel);
	}

	@PostMapping("/findDcpsContribution")
	public ResponseEntity<Double> findSumContribution(@PathVariable String sevaarthId, String paymentType,
			Integer monthId, Integer yearId) {
		Double sum = onlineContributionService.findSumContribution(sevaarthId, paymentType, monthId, yearId,"");
		return ResponseEntity.ok(sum);
	}
	
	@PostMapping("/addDcpsVoucherDtl")
	public ResponseEntity<Integer> addDcpsVoucherDtl(@RequestBody Map<String, String> formData,HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Integer status= onlineContributionService.addDcpsVoucherDtl(formData,messages);
		return ResponseEntity.ok(status);
	}
	
	@PostMapping("/rejectContribution")
	public ResponseEntity<Integer> rejectContribution(@RequestBody Map<String, String> formData,HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Integer status= onlineContributionService.rejectContribution(formData,messages);
		return ResponseEntity.ok(status);
	}
	
	

}
