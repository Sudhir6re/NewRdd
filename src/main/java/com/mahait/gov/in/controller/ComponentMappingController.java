package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mahait.gov.in.entity.AllowanceDeductionRuleMstEntity;
import com.mahait.gov.in.entity.ComponentParameterMstEntity;
import com.mahait.gov.in.entity.ComponetMappingEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.ComponentMappingService;
import com.mahait.gov.in.service.ComponentMstService;
import com.mahait.gov.in.service.DeptEligibilityForAllowAndDeductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/mdc")
public class ComponentMappingController extends BaseController {

	@Autowired
	ComponentMappingService componentMappingService;
	
	@Autowired
	ComponentMstService componentMstService;

	@Autowired
	DeptEligibilityForAllowAndDeductService deptEligibilityForAllowAndDeductService;
	@GetMapping("/getallComponentList")
	public ModelAndView getAllComponentList(Model model, Locale locale, HttpSession session
			,HttpServletRequest request,HttpServletResponse response,
			
			@ModelAttribute("allowanceDeductionRuleMstEntity") AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		
		model.addAttribute("lstdeptEligibilityForAllowAndDeduct",deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());
	    
	 List<ComponentParameterMstEntity>  componentParameterMstEntity;
	 componentMappingService.transferData(deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("context", request.getContextPath());
		modelAndView.addObject("language", locale.getLanguage());

		session.setAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("sessionMessages", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("userName", messages.getUserName());

		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());
																							// Component

		modelAndView.addObject("allowanceDeductionRuleMstEntity", allowanceDeductionRuleMstEntity);

		model.addAttribute("lstdeptEligibilityForAllowAndDeduct",
				deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());
		
		
	//	model.addAttribute("lstAllComponent", componentMstService.lstAllDepartment());

		addMenuAndSubMenu(modelAndView, messages);
		modelAndView.setViewName("/views/component-mapping");
		return modelAndView;
		
		

}
	 @PostMapping("/setPrivileges")
	 public ResponseEntity< List<ComponetMappingEntity>> updatePrivileges(@RequestBody Map<String, List<String>> componentParameterMap)
             {
		 List<ComponetMappingEntity> mappings = componentMappingService.setMandatoryFieldsForComponents(componentParameterMap);
	        return new ResponseEntity<>(mappings, HttpStatus.CREATED);
          }
//		 componentMappingService.updatePrivileges(compId, typeAllowance, typeOfComponet, payCommision, amount ,startDate ,endDate,percentage,minBasic, maxBasic,cityClass, gradePayorSevenPcLevelLow, grdePayorSevenPcLevelhigh,  premiumAmount, cityGroup);
}
	
	
