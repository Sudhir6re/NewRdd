package com.mahait.gov.in.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DeptEligibilityForAllowAndDeductModel;
import com.mahait.gov.in.model.MstEmployeeModel;
import com.mahait.gov.in.service.AllowDeduBulkEmpService;
import com.mahait.gov.in.service.AllowanceDeductionWiseMstService;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.CreateAdminOfficeService;
import com.mahait.gov.in.service.DeptEligibilityForAllowAndDeductService;
import com.mahait.gov.in.service.MstEmployeeService;
import com.mahait.gov.in.service.MstSchemeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddoast")
public class AllowDeduBulkEmpController extends BaseController  {
	
	@Autowired
	DeptEligibilityForAllowAndDeductService deptEligibilityForAllowAndDeductService;
	
	@Autowired
	CreateAdminOfficeService createAdminOfficeService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	MstEmployeeService mstEmployeeService;
	
	/*@Autowired
	MpgSchemeBillGroupService mpgSchemeBillGroupService;
	*/
	@Autowired
	MstSchemeService mstSchemeService;
	
	
	
	@Autowired
	AllowanceDeductionWiseMstService allowanceDeductionWiseMstService;
	
	@Autowired
	AllowDeduBulkEmpService allowDeduBulkEmpService;
	
	
	@GetMapping("/loadAllowDeduBulkEmpPage")
 	public String loadAllowDeduBulkEmpPage(HttpServletRequest request,Locale locale,HttpSession session,Model model,@ModelAttribute("deptEligibilityForAllowAndDeductModel") DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel){
 		
		model.addAttribute("lstAllDepartment", createAdminOfficeService.lstAllDepartment());
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		
		/*
	    List<Object[]> deptEligibilityForAllowAndDeductEntity =  createAdminOfficeService.employeeMappingList(messages.getRole_id(),messages.getUserName());
	    model.addAttribute("testObj",deptEligibilityForAllowAndDeductEntity);*/
	    
		model.addAttribute("language", locale.getLanguage());
	    model.addAttribute("lstDeptDataTable", allowDeduBulkEmpService.findAllEmployeesByDDOName(messages.getDdoCode()));
		model.addAttribute("lstdeptEligibilityForAllowAndDeduct", deptEligibilityForAllowAndDeductService.findDeptNonGovDeductList());
		model.addAttribute("context", request.getContextPath());

		model.addAttribute("lstSchemeBillGroup", mstSchemeService.findAllMpgSchemeBillGroupByDDOCode(messages.getDdoCode(), messages.getMstRoleEntity().getRoleId()));
		
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		return "views/allow-dedu-bulk-emp";
	}
	
	@GetMapping(value = "/getListEmpBySchemBillGroup/{schemeBillGrpId}/{departmentAllowdeducCode}")
	public ResponseEntity<List<MstEmployeeModel>> getListEmpBySchemBillGroup(@PathVariable Long schemeBillGrpId,@PathVariable Integer departmentAllowdeducCode
			,HttpSession session) {
		OrgUserMst orgUserMst = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<MstEmployeeModel> lstMstEmployeeModel = allowDeduBulkEmpService.getListEmpBySchemBillGroup(orgUserMst.getDdoCode(), schemeBillGrpId,departmentAllowdeducCode);
		return ResponseEntity.ok(lstMstEmployeeModel);
	}
	
	@RequestMapping(value = "/allowancDeductionByType/{isType}", consumes = {
	"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<List<DeptEligibilityForAllowAndDeductEntity>> allowancDeductionByType(
	 @PathVariable int isType) {
     return ResponseEntity
		.ok(allowanceDeductionWiseMstService.fetchLstDeptEligibilityForAllowAndDeductEntityByType(isType));
   }
	
	@RequestMapping("/saveAllowDeduBulkForEmp")
	public ModelAndView saveAllowDeduBulkForEmp(HttpServletRequest request, Model model,
			HttpServletResponse response, Locale locale, HttpSession session,
			@ModelAttribute("deptEligibilityForAllowAndDeductModel") DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,RedirectAttributes redirectAttributes) throws ParseException {
		OrgUserMst orgUserMst = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		int isSave=0;
		for(DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel1:deptEligibilityForAllowAndDeductModel.getLstDeptEligibilityForAllowAndDeductModel()) {
			 allowDeduBulkEmpService.checkComponentAlreadyPresent(deptEligibilityForAllowAndDeductModel1,orgUserMst.getDdoCode());
			 if(deptEligibilityForAllowAndDeductModel1.isCheckBox()==true) {
					isSave=allowDeduBulkEmpService.updateAllowDeductBulkEmplComp(deptEligibilityForAllowAndDeductModel1,orgUserMst.getDdoCode());
				}
		}
		if(isSave>0) {
			redirectAttributes.addFlashAttribute("message", "Data Saved successfully !!!");
		}else {
			redirectAttributes.addFlashAttribute("message", "Data Saved successfully !!!");
		}
		return new ModelAndView("redirect:/ddoast/loadAllowDeduBulkEmpPage");
	}
	
	@GetMapping(value = "/PaybillInBulkProcess/{billNumber}")
	public ResponseEntity<String> PaybillInBulkProcess(@PathVariable String billNumber,HttpSession session) {
		OrgUserMst orgUserMst = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> paybillgen = allowDeduBulkEmpService.findpaybill(billNumber,orgUserMst.getDdoCode());
		Integer existingData = paybillgen.size();
		String resJson = existingData.toString();
		return ResponseEntity.ok(resJson);
	}
}

