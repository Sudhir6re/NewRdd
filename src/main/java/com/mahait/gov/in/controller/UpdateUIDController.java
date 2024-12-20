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
import com.mahait.gov.in.model.UpdateUIDModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.UpdateUIDService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ddoast")
public class UpdateUIDController extends BaseController {
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	UpdateUIDService updateUIDService;
	
	List<UpdateUIDModel> emplist = new ArrayList<>();
	
	@GetMapping("/updateUID")
	public String updateUID(@ModelAttribute("updateUIDModel") UpdateUIDModel updateUIDModel, Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		String message=(String) model.asMap().get("message");
		emplist = updateUIDService.findAllEmployee(messages.getDdoCode());
		updateUIDModel.setEmplist(emplist);
		model.addAttribute("updateUIDModel", updateUIDModel);
		
		addMenuAndSubMenu(model, messages);
		model.addAttribute("message", message);
		return "/views/UpdateUID";
	}
	
	@PostMapping("/saveuidNo")
	public String saveuidNo(@ModelAttribute("updateUIDModel") @Valid UpdateUIDModel updateUIDModel,HttpSession session,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	
				int afterSaveId = updateUIDService.saveuidNo(updateUIDModel,messages);
		
				if(afterSaveId > 0) {
					redirectAttributes.addFlashAttribute("message","Record Updated Successfully");
				}
				else
				{
					redirectAttributes.addFlashAttribute("messages","Record Updated Successfully");
				}
				
				return "redirect:/ddoast/updateUID";
	}

}
