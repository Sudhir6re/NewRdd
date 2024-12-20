package com.mahait.gov.in.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstRoleModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class MstRoleController  extends BaseController {
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@GetMapping("/mstRole")
	public String adminOfficeMaster(@ModelAttribute("mstRoleModel") MstRoleModel mstRoleModel,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		model.addAttribute("mstRoleModel", mstRoleModel);
		
	
		model.addAttribute("lstRoleTable", commonHomeMethodsService.findAllRole());
		model.addAttribute("language", locale.getLanguage());
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/mst-role";
    }
	@PostMapping("/saveMstRole")
	public String saveMstRole(@ModelAttribute("MstRoleEntity") @Valid MstRoleEntity  mstRoleEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("lstRoleTable", commonHomeMethodsService.findAllRole());
			return "/views/mst-role"; /*Return to HTML Page*/
		} 
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
	//	mstRoleEntity.setCreatedUserId(messages.getUserId());
		int afterSaveId = commonHomeMethodsService.saveMstRole(mstRoleEntity);
		if(afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message","SUCCESS");
		}
		
		
		model.addAttribute("lstRoleTable", commonHomeMethodsService.findAllRole());
		return "redirect:/admin/mstRole"; /*redirects to controller URL*/
	}	
	@RequestMapping(value="/editRole/{roleId}")	// , method = RequestMethod.POST
    public String editScheme (@PathVariable int roleId, RedirectAttributes redirectAttributes, Model model,Locale locale,HttpSession session) {
		model.addAttribute("mstRoleEntity", commonHomeMethodsService.findRole(roleId));
		model.addAttribute("language", locale.getLanguage());
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		
		return "/views/edit-mst-role";
    }
	@PostMapping("/editRoleSave")
	public String editRoleSave(@ModelAttribute("mstRoleEntity") @Valid MstRoleEntity mstRoleEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/edit-mst-role"; 
		} 
		String message = commonHomeMethodsService.editRoleSave(mstRoleEntity);
		if(message.equals("UPDATED")) {
			redirectAttributes.addFlashAttribute("message","UPDATED");
		}
		//model.addAttribute("lstRoleTable", commonHomeMethodsService.findAllRole());
		return "redirect:/admin/master/mstRole"; //redirects to controller URL
	}
	
	@RequestMapping(value="/deleteRole/{roleId}")	// , method = RequestMethod.POST
	public String deleteRole(@PathVariable int roleId,Model model,Locale locale) {
		MstRoleEntity mstRoleEntity = commonHomeMethodsService.deleteRoleById(roleId);
		if(mstRoleEntity != null) {
			model.addAttribute("mstRoleModel", new MstRoleModel());
			model.addAttribute("lstRoleTable", commonHomeMethodsService.findAllRole());
			model.addAttribute("language", locale.getLanguage());
		}
		
		return "views/mst-role";
	}
}
