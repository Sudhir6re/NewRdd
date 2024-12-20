/**
 * 
 */
package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.MstMenuRoleMappingEntity;
import com.mahait.gov.in.entity.MstSubMenuEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstMenuRoleMappingModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstMenuRoleMappingService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/master")
public class MstMenuRoleMappingController  extends BaseController {

	@Autowired
	MstMenuRoleMappingService mstMenuRoleMappingService;
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@GetMapping("/mstMenuRoleMapping")
	public String mstCadreMst(@ModelAttribute("mstMenuRoleMappingModel") MstMenuRoleMappingModel mstMenuRoleMappingModel,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		model.addAttribute("mstMenuRoleMappingModel", mstMenuRoleMappingModel);
		
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		
		model.addAttribute("lstMenu", commonHomeMethodsService.findAllMenu(locale.getLanguage()));
		model.addAttribute("lstRole", commonHomeMethodsService.findAllRole(locale.getLanguage()));
		model.addAttribute("lstMenuRoleMapping", commonHomeMethodsService.findAllMenuRoleMapping(locale.getLanguage()));
		
		addMenuAndSubMenu(model,messages);
		return "/views/mst-menu-role-mapping";
    }
	
	@PostMapping("/saveMenuRoleMapping")
	public String saveMenuRoleMapping(@ModelAttribute("mstMenuRoleMappingModel") @Valid MstMenuRoleMappingModel mstMenuRoleMappingModel,
									BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, Locale locale, HttpSession session) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/mst-menu-role-mapping"; /*Return to HTML Page*/
		} 
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		int afterSaveId = mstMenuRoleMappingService.saveMenuRoleMapping(mstMenuRoleMappingModel,messages);
		if(afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message","SUCCESS");
		}
		model.addAttribute("language", locale.getLanguage());
		return "redirect:/master/mstMenuRoleMapping"; /*redirects to controller URL*/
	}
	
	@RequestMapping(value="/editMenuRoleMapping/{key}")	// , method = RequestMethod.POST
    public String editMenuRoleMapping(@PathVariable int key, RedirectAttributes redirectAttributes, Model model,Locale locale,HttpSession session) {
		
		model.addAttribute("mstMenuRoleMappingEntity", mstMenuRoleMappingService.findMenuAndRoleMappingByKey(key));
		
		MstMenuRoleMappingEntity objMenuRole = mstMenuRoleMappingService.findMenuAndRoleMappingByKey(key);
		
		model.addAttribute("menu_key", objMenuRole.getMenuCode());
		model.addAttribute("role_key", objMenuRole.getRoleId());
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstMenu", commonHomeMethodsService.findAllMenu(locale.getLanguage()));
		model.addAttribute("lstRole", commonHomeMethodsService.findAllRole(locale.getLanguage()));
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);		
		return "/views/edit-menu-role-mapping";
    }
	
	@PostMapping("/editMenuRoleMappingSave")
	public String editMenuRoleMappingSave(@ModelAttribute("mstMenuRoleMappingEntity") @Valid MstMenuRoleMappingEntity mstMenuRoleMappingEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		addMenuAndSubMenu(model,messages);
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/edit-menu-role-mapping"; 
		} 
		String message = mstMenuRoleMappingService.saveEditMenuRoleMapping(mstMenuRoleMappingEntity,messages.getMstRoleEntity().getRoleId());
		if(message.equals("UPDATED")) {
			redirectAttributes.addFlashAttribute("message","UPDATED");
		} else if(message.equals("ALLREADYEXISTS")) {
			redirectAttributes.addFlashAttribute("message","ALLREADYEXISTS");
		}
		return "redirect:/master/mstMenuRoleMapping"; 
	}
	
	@RequestMapping(value = "/checkSubMenuExistsByMenuAndRoleKey/{menuKey}/{roleKey}", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public ResponseEntity<String> checkSubMenuExistsByMenuAndRoleKey(@RequestHeader HttpHeaders headers,@PathVariable int menuKey,@PathVariable int roleKey) {
		List<MstSubMenuEntity> subMenuList = mstMenuRoleMappingService.findSubMenuByMenuKeyAndRoleKey(menuKey,roleKey);
		Integer subMenuListSize = subMenuList.size();
		String resJson = subMenuListSize.toString();
		return ResponseEntity.ok(resJson);
    }
	
	@RequestMapping(value = "/deleteMenuAndRoleMapping/{menuKey}/{roleKey}") 
	public String deleteMenuAndRoleMapping(@PathVariable int menuKey,@PathVariable int roleKey, Model model, Locale locale, 
												RedirectAttributes redirectAttributes,HttpSession session) {
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		MstMenuRoleMappingEntity mstMenuRoleMappingEntity = mstMenuRoleMappingService.findMenuRoleMappingByMenuKeyAndRoleKey(menuKey,roleKey,messages.getMstRoleEntity().getRoleId());
		if (mstMenuRoleMappingEntity != null) {
			model.addAttribute("mstMenuRoleMappingModel", new MstMenuRoleMappingModel());
			model.addAttribute("language", locale.getLanguage());
		}
		
		addMenuAndSubMenu(model,messages);
		return "views/mst-menu-role-mapping";
	}
}
