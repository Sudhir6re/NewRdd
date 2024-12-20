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
import com.mahait.gov.in.model.UpdatePanNoModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.UpdatePanNoService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ddoast")
public class UpdatePanNoController extends BaseController {
 
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	UpdatePanNoService updatePanNoService;
	
	List<UpdatePanNoModel> emplist = new ArrayList<>();
	
	
	
	@GetMapping("/updatePanNo")
	public String updatePanNo(@ModelAttribute("updatePanNoModel") UpdatePanNoModel updatePanNoModel, Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String message=(String) model.asMap().get("message");
		
		
		//model.addAttribute("", getDesignation())
    	emplist = updatePanNoService.findAllEmployee(messages.getDdoCode());
		updatePanNoModel.setEmplist(emplist);
	
		addMenuAndSubMenu(model,messages);
				
		model.addAttribute("updatePanNoModel", updatePanNoModel);
		model.addAttribute("message", message);
		
		
		
		return "/views/updatePanNo";
	}
	
	@PostMapping("/savePanNo")
	public String saveupdateMobNo(@ModelAttribute("updatePanNoModel") @Valid UpdatePanNoModel updatePanNoModel,HttpSession session,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	
				int afterSaveId = updatePanNoService.saveupdateMobNo(updatePanNoModel,messages);
		
				if(afterSaveId > 0) {
					redirectAttributes.addFlashAttribute("message","Record Updated Successfully");
				}
				else
				{
					redirectAttributes.addFlashAttribute("messages","Record Updated Successfully");
				}
				
				return "redirect:/ddoast/updatePanNo";
	}

}
