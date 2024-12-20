package com.mahait.gov.in.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ChangeBasicDtlsModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.EmpChangeBasicdtlsService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/ddoast")
public class EmpChangeBasicDtlsController extends BaseController {
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	EmpChangeBasicdtlsService empchangeBasicDtls;
	
	List<ChangeBasicDtlsModel> changeBasicDtlsModelsList = new ArrayList<>();
	
	@GetMapping("/getBasicDetails")
	public String getBasicDetails(  @ModelAttribute("changeBasicDtlsModel") ChangeBasicDtlsModel changeBasicDtlsModel,
			Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);

		String message = (String) model.asMap().get("message");

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.SAVEDRAFT, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("language", locale.getLanguage());
		
		
		changeBasicDtlsModelsList = empchangeBasicDtls.findEmpChangeBasicDtls(messages.getDdoCode());
		
		changeBasicDtlsModel.setChangeBasicDtlsModelsList(changeBasicDtlsModelsList);
		
		
		
		model.addAttribute("changeBasicDtlsModel", changeBasicDtlsModel);
		///
		

		// model=brokenPeriodService.brokenPeriod(mstEmployeeModel, model, sevaarthid);

		return "/views/changebasicdtls";
	}


	
	@PostMapping(value = "/saveChangeBasicdtls")
	public String saveChangeBasicdtls(@ModelAttribute("changeBasicDtlsModel") ChangeBasicDtlsModel changeBasicDtlsModel, 
			BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {
			
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		//empLeaveModel.setCreatedUserId(messages.getUser_id());
			
				int afterSaveId = empchangeBasicDtls.saveChangeBasicdtls(changeBasicDtlsModel,messages);
		
				if(afterSaveId > 0) {
					redirectAttributes.addFlashAttribute("message","SUCCESS");
				}
				else
				{
					redirectAttributes.addFlashAttribute("messages","SUCCESS");
				}
				
				return "redirect:/ddoast/getBasicDetails";
			}
	
	
	
	
}
