package com.mahait.gov.in.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DeptEligibilityForAllowAndDeductModel;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.CreateAdminOfficeService;
import com.mahait.gov.in.service.DeptEligibilityForAllowAndDeductService;
import com.mahait.gov.in.service.MstEmployeeService;
import com.mahait.gov.in.service.OrderMasterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value= {"/mdc","/ddo"})
public class DeptEligibilityForAllowAndDeductAdminController  extends BaseController{

	@Autowired
	DeptEligibilityForAllowAndDeductService deptEligibilityForAllowAndDeductService;
	
	@Autowired
	CreateAdminOfficeService createAdminOfficeService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	MstEmployeeService mstEmployeeService;
	
	@Autowired
	OrderMasterService orderMasterService;
	
	
	@GetMapping("/mstAllowAndDeduct")
	public String msgAllowAndDeduct(@ModelAttribute("mstAllowAndDeductModel") DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		model.addAttribute("mstBankModel", deptEligibilityForAllowAndDeductModel);
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();
		
		/*menuList = commonHomeMethodsService.findMenuNameByRoleID(messages.getRole_id(),locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(messages.getRole_id(),locale.getLanguage());
		
		model.addAttribute("menuList", menuList);
		model.addAttribute("subMenuList", subMenuList);
		
		if(message != null && message.equals("SUCCESS")) {
			if(locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}*/
		model.addAttribute("lstDeptDataTable1", deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());
		System.out.println("listdata"+deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());
		
		model.addAttribute("lstAllDepartment", createAdminOfficeService.lstAllDepartment());
		model.addAttribute("language", locale.getLanguage());
		
		addMenuAndSubMenu(model,messages);	
		deptEligibilityForAllowAndDeductModel.setDepartmentAllowdeducCode(Integer.valueOf(commonHomeMethodsService.findCodeSeq("department_allowdeduc_code","department_allowdeduc_mst"))); 
		model.addAttribute("deptEligibilityForAllowAndDeductModel",deptEligibilityForAllowAndDeductModel); 
		return "/views/mst-allowance-deduction";
    }
	
	@PostMapping("/saveAllowDeductionMst")
	public String saveAllowDeductionMst(@ModelAttribute("mstAllowAndDeductModel") @Valid DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,HttpSession session) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		int afterSaveId = deptEligibilityForAllowAndDeductService.saveAllowDeductionMst(deptEligibilityForAllowAndDeductModel,messages);
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}

		return "redirect:/mdc/mstAllowAndDeduct";
	}
	
	@GetMapping("/deptEligibilityForAllowAndDeductAdmin")
	public String deptEligibilityForAllowAndDeductAdmin(@ModelAttribute("deptEligibilityForAllowAndDeductModel") DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		model.addAttribute("mstBankModel", deptEligibilityForAllowAndDeductModel);
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		model.addAttribute("lstDeptDataTable1", deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());
		model.addAttribute("lstInstitute", orderMasterService.getInstitutionLst(messages.getUserName()));
		//model.addAttribute("lstDDOCode", createAdminOfficeService.lstAllDDOLevel2(messages.getUserName()));
	///	model.addAttribute("lstAllDepartment", createAdminOfficeService.lstAllDepartment1());
		model.addAttribute("language", locale.getLanguage());
	///	model.addAttribute("lstDeptDataTable", mstEmployeeService.findAllEmployees());
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		addMenuAndSubMenu(model,messages);	
		return "/views/mst-dept-eligibility-for-allow-deduct-admin";
    }
	
	@GetMapping(value="/findallowDeductLevel1/{ddoCode2}")
	public @ResponseBody List<Object[]> findallowDeductLevel2(@PathVariable String ddoCode2,Model model,Locale locale) {
		
		List<Object[]> deptEligibilityForAllowAndDeductEntity =  deptEligibilityForAllowAndDeductService.findallowDeductLevel2(ddoCode2);
		List<Object[]>  mstEmployeeEntity=  deptEligibilityForAllowAndDeductEntity;
		
		return mstEmployeeEntity;
    }
	
	@GetMapping(value = "/saveDeptEligibilityAllowDeducAdmin/{selecteditems}/{action}/{serialid}/{effectiveDate}/{selecteditems1}", consumes = { "application/json" }, produces = {
	"application/json" })
	public @ResponseBody List<DeptEligibilityForAllowAndDeductEntity> mutlip(@PathVariable Object[] selecteditems,@PathVariable Object[] serialid,@PathVariable String effectiveDate, @PathVariable int action,@PathVariable String selecteditems1, 
			Locale locale) {
		
		Date date=null;
		try {
			date=new SimpleDateFormat("yyyy-MM-dd").parse(effectiveDate);
		}catch(Exception e)
		{
			
		}
		
		
		int i=0;
			deptEligibilityForAllowAndDeductService.deleteMpgDdoAllowDeduc(action,selecteditems1);
		
				for (Object components : selecteditems) {
					deptEligibilityForAllowAndDeductService.saveMpgDdoAllowDeduc(components,action,serialid,effectiveDate,selecteditems1);
				}
		return null;
	}
	
}
