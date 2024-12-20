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

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.UpdateServiceEndDateModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.UpdateServiceEndDateService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ddoast")
public class UpdateServiceEndDateController extends BaseController {
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	UpdateServiceEndDateService updateServiceEndDateService;
	
	List<UpdateServiceEndDateModel> emplist = new ArrayList<>();
	
	@GetMapping("/updateSED")
	public String updateSED(@ModelAttribute("updateServiceEndDateModel") UpdateServiceEndDateModel updateServiceEndDateModel, Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String message=(String) model.asMap().get("message");
		
		
		//model.addAttribute("", getDesignation())
    	emplist = updateServiceEndDateService.findAllEmployee(messages.getDdoCode());
    	updateServiceEndDateModel.setEmplist(emplist);
	
		
    	addMenuAndSubMenu(model,messages);		
		model.addAttribute("updateServiceEndDateModel", updateServiceEndDateModel);
		model.addAttribute("message", message);
		
		
		
		return "/views/updateServiceEnd";
	}
	
	
	@PostMapping("/saveSED")
	public String saveSED(@ModelAttribute("updateServiceEndDateModel") @Valid UpdateServiceEndDateModel updateServiceEndDateModel,HttpSession session,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	
				int afterSaveId = updateServiceEndDateService.saveSED(updateServiceEndDateModel,messages);
		
				if(afterSaveId > 0) {
					redirectAttributes.addFlashAttribute("message","Record Updated Successfully");
				}
				else
				{
					redirectAttributes.addFlashAttribute("messages","Record Updated Successfully");
				}
				
				return "redirect:/ddoast/updateSED";
	}
	
	
	

}
