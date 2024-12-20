
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

import com.mahait.gov.in.entity.MstMenuEntity;
import com.mahait.gov.in.entity.MstMenuRoleMappingEntity;
import com.mahait.gov.in.entity.MstSubMenuEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstMenuModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstMenuRoleMappingService;
import com.mahait.gov.in.service.MstMenuService;
import com.mahait.gov.in.service.MstSubMenuService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/master")
public class MstMenuController   extends BaseController{

	@Autowired
	MstMenuService mstMenuService;

	@Autowired
	MstSubMenuService mstSubMenuService;

	@Autowired
	MstMenuRoleMappingService mstMenuRoleMappingService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@GetMapping("/mstMenu")
	public String mstCadreMst(@ModelAttribute("mstMenuModel") MstMenuModel mstMenuModel,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		model.addAttribute("mstMenuModel", mstMenuModel);
	
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstMenu", commonHomeMethodsService.findAllMenu(locale.getLanguage()));
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/mst-menu";
    }
	
	@PostMapping("/saveMenu")
	public String saveMenu(@ModelAttribute("mstMenuModel") @Valid MstMenuModel mstMenuModel,
									BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, Locale locale, HttpSession session) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/mst-menu"; /*Return to HTML Page*/
		} 
		OrgUserMst orgUserMst  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		int afterSaveId = mstMenuService.saveMenu(mstMenuModel,orgUserMst);
		if(afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message","SUCCESS");
		}
		model.addAttribute("language", locale.getLanguage());
		return "redirect:/master/mstMenu"; /*redirects to controller URL*/
	}
	
	@RequestMapping(value="/editMenu/{key}")	// , method = RequestMethod.POST
    public String editMenu(@PathVariable int key, RedirectAttributes redirectAttributes, Model model,Locale locale,HttpSession session) {
		
		model.addAttribute("mstMenuEntity", mstMenuService.findMenuByKeyForEdit(key));
		model.addAttribute("language", locale.getLanguage());
		
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		
		return "/views/edit-mst-menu";
    }
	
	@PostMapping("/saveEditMenu")
	public String saveEditMenu(@ModelAttribute("mstMenuEntity") @Valid MstMenuEntity mstMenuEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {
		
		OrgUserMst orgUserMst  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/edit-mst-menu"; 
		} 
		String message = mstMenuService.saveEditMenu(mstMenuEntity,orgUserMst);
		if(message.equals("UPDATED")) {
			redirectAttributes.addFlashAttribute("message","UPDATED");
		}
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "redirect:/master/mstMenu"; /*redirects to controller URL*/
	}
	
	@RequestMapping(value = "/checkSubMenuExists/{key}", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public ResponseEntity<String> checkSubMenuExists(@RequestHeader HttpHeaders headers,@PathVariable int key) {
		List<MstSubMenuEntity> subMenuList = mstSubMenuService.findSubMenuByMenuKey(key);
		Integer subMenuListSize = subMenuList.size();
		String resJson = subMenuListSize.toString();
		return ResponseEntity.ok(resJson);
    }
	@RequestMapping(value = "/checkMenuRoleMappingExists/{key}", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
	public ResponseEntity<String> checkMenuRoleMappingExists(@RequestHeader HttpHeaders headers,@PathVariable int key) {
		List<MstMenuRoleMappingEntity> menuAndRoleMappingList = mstMenuRoleMappingService.findMenuRoleMappingByMenuKey(key);
		Integer menuAndRoleMappingListSize = menuAndRoleMappingList.size();
		String resJson = menuAndRoleMappingListSize.toString();
		return ResponseEntity.ok(resJson);
	}
	
	@RequestMapping(value = "/deleteMenu/{key}") // , method = RequestMethod.POST
	public String deleteMenu(@PathVariable int key, Model model, Locale locale, RedirectAttributes redirectAttributes,HttpSession session) {
		
		OrgUserMst orgUserMst  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		MstMenuEntity mstMenuEntity = mstMenuService.findMenuByKeyForDelete(key,orgUserMst.getMstRoleEntity().getRoleId());
		if (mstMenuEntity != null) {
			model.addAttribute("mstMenuModel", new MstMenuModel());
			model.addAttribute("language", locale.getLanguage());
		}
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "views/mst-menu";
	}
}
