/**
 * 
 */
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.entity.MstSubMenuEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstSubMenuModel;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstSubMenuService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/master")
public class MstSubMenuController  extends BaseController{

	@Autowired
	MstSubMenuService mstSubMenuService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@GetMapping("/mstSubMenu")
	public String mstCadreMst(@ModelAttribute("mstSubMenuModel") MstSubMenuModel mstSubMenuModel, Model model,
			Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
		model.addAttribute("mstSubMenuModel", mstSubMenuModel);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();

		model.addAttribute("lstMenu", commonHomeMethodsService.findAllMenu(locale.getLanguage()));
		model.addAttribute("lstRole", commonHomeMethodsService.findAllRole(locale.getLanguage()));
		model.addAttribute("lstSubMenu", commonHomeMethodsService.findAllSubMenu(locale.getLanguage()));
		model.addAttribute("language", locale.getLanguage());
		addMenuAndSubMenu(model,messages);
		return "/views/mst-sub-menu";
	}

	@PostMapping("/saveSubMenu")
	public String saveSubMenu(@ModelAttribute("mstSubMenuModel") @Valid MstSubMenuModel mstSubMenuModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,
			HttpSession session) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/mst-sub-menu"; /* Return to HTML Page */
		}
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		int afterSaveId = mstSubMenuService.saveSubMenu(mstSubMenuModel, messages);
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		addMenuAndSubMenu(model,messages);
		model.addAttribute("language", locale.getLanguage());
		return "redirect:/master/mstSubMenu"; /* redirects to controller URL */
	}

	@RequestMapping(value = "/editSubMenu/{subMenuId}") // , method = RequestMethod.POST
	public String editSubMenu(@PathVariable int subMenuId, RedirectAttributes redirectAttributes, Model model,
			Locale locale, HttpSession session) {

		model.addAttribute("mstSubMenuEntity", mstSubMenuService.findSubMenuBySubMenuKey(subMenuId));

		MstSubMenuEntity objSubMenu = mstSubMenuService.findSubMenuBySubMenuKey(subMenuId);

		model.addAttribute("menuCode", objSubMenu.getMenuCode());
		model.addAttribute("roleId", objSubMenu.getRoleId());
		model.addAttribute("sub_menu_name_english", objSubMenu.getSub_menu_name_english());
		model.addAttribute("sub_menu_name_marathi", objSubMenu.getSub_menu_name_marathi());
		model.addAttribute("controller_name", objSubMenu.getController_name());
		model.addAttribute("link_name", objSubMenu.getLink_name());
		model.addAttribute("is_active", objSubMenu.getIs_active());
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstMenu", commonHomeMethodsService.findAllMenu(locale.getLanguage()));
		model.addAttribute("lstRole", commonHomeMethodsService.findAllRole(locale.getLanguage()));
		model.addAttribute("lstCommonMst",
				commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_STATUS));

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		
		return "/views/edit-sub-menu";
	}

	@PostMapping("/saveEditSubMenu")
	public String saveEditSubMenu(@ModelAttribute("mstSubMenuEntity") @Valid MstSubMenuEntity mstSubMenuEntity,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		if (bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/edit-sub-menu";
		}
		String message = mstSubMenuService.saveEditSubMenu(mstSubMenuEntity, messages.getMstRoleEntity().getRoleId());
		if (message.equals("UPDATED")) {
			redirectAttributes.addFlashAttribute("message", "UPDATED");
		} else if (message.equals("ALLREADYEXISTS")) {
			redirectAttributes.addFlashAttribute("message", "ALLREADYEXISTS");
		}
		return "redirect:/master/mstSubMenu";
	}

	@RequestMapping(value = "/deleteSubMenu/{subMenuId}/{menuCode}/{roleId}") // , method = RequestMethod.POST
	public String deleteMenu(Model model, Locale locale, RedirectAttributes redirectAttributes,HttpSession session,@PathVariable int subMenuId,
												@PathVariable int menuCode,@PathVariable int roleId) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		MstSubMenuEntity mstSubMenuEntity = mstSubMenuService.findSubMenuBySubMenuKeyMenuKeyAndRoleKey(subMenuId,menuCode,roleId,messages.getMstRoleEntity().getRoleId());
		if (mstSubMenuEntity != null) {
			model.addAttribute("mstSubMenuModel", new MstSubMenuModel());
			model.addAttribute("language", locale.getLanguage());
		}
		addMenuAndSubMenu(model,messages);
		return "views/mst-sub-menu";
	}
}
