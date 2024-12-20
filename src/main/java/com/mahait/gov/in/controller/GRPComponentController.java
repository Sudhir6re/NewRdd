package com.mahait.gov.in.controller;

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

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmployeeAllowDeducComponentAmtModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DeptEligibilityForAllowAndDeductService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ddoast")
public class GRPComponentController   extends BaseController{
//	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	DeptEligibilityForAllowAndDeductService deptEligibilityForAllowAndDeductService;
	
	
	@GetMapping("/nonComputationalDuesAndDeductions")
	public String adminOfficeMaster(@ModelAttribute("employeeAllowDeducComponentAmtModel") EmployeeAllowDeducComponentAmtModel employeeAllowDeducComponentAmtModel,
										Model model,Locale locale,HttpSession session) {
		String message = (String)model.asMap().get("message");
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		//List<TopicModel> menuList = new ArrayList<>();
		//List<TopicModel> subMenuList = new ArrayList<>();
		/*menuList = commonHomeMethodsService.findMenuNameByRoleID(messages.getRole_id(),locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(messages.getRole_id(),locale.getLanguage());
		model.addAttribute("menuList", menuList);
		model.addAttribute("subMenuList", subMenuList);
		*/
		if(message != null && message.equals("SUCCESS")) {
			if(locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstdeptEligibilityForAllowAndDeduct", deptEligibilityForAllowAndDeductService.findDeptAllowAndDeductList());
		
		addMenuAndSubMenu(model,messages);
		return "/views/NonComputationalDuesAndDeduction";
    }
	
	
	@GetMapping(value = "/getEmployeeAgainstId/{allowDeducComponentId}/{ddoCode}/{sevaarthId}") // , method = RequestMethod.POST
	public @ResponseBody List<Object[]> ddoCodeBySubDeptId1(@PathVariable int allowDeducComponentId,@PathVariable String ddoCode,@PathVariable String sevaarthId) 
	{
		
		List<Object[]> allDDOCodeBySubDept = deptEligibilityForAllowAndDeductService.getEmployeeAgainstId(allowDeducComponentId,ddoCode,sevaarthId);
		return allDDOCodeBySubDept;
	}
	
	@PostMapping(value = "/saveEmployeeAllowDeducComponentAmt")
	public String saveEmployeeAllowDeducComponentAmt(@ModelAttribute("employeeAllowDeducComponentAmtModel") @Valid EmployeeAllowDeducComponentAmtModel employeeAllowDeducComponentAmtModel, 
			BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {
			
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		employeeAllowDeducComponentAmtModel.setCreatedUserId(messages.getUserId());
				int afterSaveId = deptEligibilityForAllowAndDeductService.saveEmployeeNonGovDuesDeduct(employeeAllowDeducComponentAmtModel,messages);
		
				if(afterSaveId > 0) {
					redirectAttributes.addFlashAttribute("message","SUCCESS");
				}
				
				return "redirect:/ddoast/nonComputationalDuesAndDeductions";
			}
	
}
