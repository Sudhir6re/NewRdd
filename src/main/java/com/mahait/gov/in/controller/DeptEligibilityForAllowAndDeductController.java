package com.mahait.gov.in.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DeptEligibilityForAllowAndDeductModel;
import com.mahait.gov.in.service.CreateAdminOfficeService;
import com.mahait.gov.in.service.DdoBillGroupService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/ddoast")
public class DeptEligibilityForAllowAndDeductController  extends BaseController{
	@Autowired
	CreateAdminOfficeService createAdminOfficeService;
	@Autowired
	DdoBillGroupService ddoBillGroupService;
	
	@GetMapping("/empEligibilityForAllowAndDeduct")
	public String employeeList(@ModelAttribute("deptEligibilityForAllowAndDeductModel") DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,
										Model model,Locale locale,HttpSession session) {
		//model.addAttribute("lstDDOCode", createAdminOfficeService.lstAllDDO());
		model.addAttribute("lstAllDepartment", createAdminOfficeService.lstAllDepartment());
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
	List<Object[]> deptEligibilityForAllowAndDeductEntity =  createAdminOfficeService.
			employeeMappingList(messages.getDdoCode());
		
//		List<TopicModel> menuList = new ArrayList<>();
//		List<TopicModel> subMenuList = new ArrayList<>();
//		
//		menuList = commonHomeMethodsService.findMenuNameByRoleID(messages.getRole_id(),locale.getLanguage());
//		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(messages.getRole_id(),locale.getLanguage());
//		
//		model.addAttribute("menuList", menuList);
//		model.addAttribute("subMenuList", subMenuList);
		
		model.addAttribute("language", locale.getLanguage());
		/*model.addAttribute("lstDDOWiseEmployee", mstEmployeeService.findAllEmployeeByddoCode(messages.getUserName()));
		*/
		/*model.addAttribute("lstDeptDataTable", mstEmployeeService.findAllEmployees());*/
		model.addAttribute("lstDeptDataTable", ddoBillGroupService.findAllEmployeesByDDOName(messages.getDdoCode()));
		
		model.addAttribute("testObj",deptEligibilityForAllowAndDeductEntity);
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		
		addMenuAndSubMenu(model,messages);	
		return "/views/mst-emp";
		
	}
	
	@GetMapping(value="/isPaybillIsInProcess/{sevaarthId}")
	public @ResponseBody List<Object[]> isPaybillIsInProcess(@PathVariable String sevaarthId,Model model,Locale locale) {
		
		List<Object[]> deptEligibilityForAllowAndDeductEntity =  ddoBillGroupService.isPaybillIsInProcess(sevaarthId);
		return deptEligibilityForAllowAndDeductEntity;
	}
	
	@GetMapping(value = "/saveEmpMpgDdoAllowDeduc/{department_allowdeduc_id}/{empId}/{sevaarthId}/{effectiveDate}", consumes = {
	"application/json" }, produces = { "application/json" })
      public @ResponseBody List<MstEmployeeEntity> mutlip123(@PathVariable Object[] department_allowdeduc_id,@PathVariable Long empId, @PathVariable String sevaarthId,@PathVariable String effectiveDate, Locale locale) {
     try {
      Date date=new SimpleDateFormat("yyyy-MM-dd").parse(effectiveDate);
          int i =0;
          ddoBillGroupService.deleteEmpMpgDdoAllowDeduc(sevaarthId);
      for (Object object : department_allowdeduc_id) {
    	  ddoBillGroupService.saveEmpMpgDdoAllowDeduc(object,empId,sevaarthId,effectiveDate);
          	i++;
          }
         }catch(Exception e) {
    	return null;
     }
       return null;
     }
	
	@GetMapping(value="/empEligibilityForAllowAndDeductCheckBoxId/{sevaarth_id}")
	public @ResponseBody List<Object[]> empEligibilityForAllowAndDeductCheckBoxId1(@PathVariable String sevaarth_id) {
		
		List<Object[]> deptEligibilityForAllowAndDeductEntity =  ddoBillGroupService.empEligibilityForAllowAndDeductCheckBoxId(sevaarth_id);
		return deptEligibilityForAllowAndDeductEntity;
	}
	@GetMapping(value="/empEligibilityForAllowAndDeduct1/{input}")
	public @ResponseBody List<Object[]> employeeListAgaintDDOCode(@PathVariable int input,Model model,Locale locale)
	{
		List<Object[]> deptEligibilityForAllowAndDeductEntity =  ddoBillGroupService.findAllMpgSchemeBillGroupbyParameter1(input);
		return deptEligibilityForAllowAndDeductEntity;
		
		
    }
}
