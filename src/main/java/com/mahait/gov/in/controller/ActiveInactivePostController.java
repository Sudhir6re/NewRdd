package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstMenuModel;
import com.mahait.gov.in.service.ActiveInactivePostService;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstMenuRoleMappingService;
import com.mahait.gov.in.service.MstMenuService;
import com.mahait.gov.in.service.MstSubMenuService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mdc")
public class ActiveInactivePostController extends BaseController {

	@Autowired
	MstMenuService mstMenuService;

	@Autowired
	MstSubMenuService mstSubMenuService;

	@Autowired
	MstMenuRoleMappingService mstMenuRoleMappingService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	ActiveInactivePostService activeInactivePostService;
	
	
	@GetMapping("/showddoList")
	public String showddoList(@ModelAttribute("mstMenuModel") MstMenuModel mstMenuModel, Model model, Locale locale,
			HttpSession session) {

		String message = (String) model.asMap().get("message");
		model.addAttribute("mstMenuModel", mstMenuModel);
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstMenu", commonHomeMethodsService.findAllMenu(locale.getLanguage()));
		
		
		List getddolst = activeInactivePostService.getddolst();
		//List getPostNameForDisplay = activeInactivePostService.getPostNameForDisplay();
		model.addAttribute("getddolst", getddolst);
		addMenuAndSubMenu(model, messages);
		return "/views/ddo-list";
	}
	

	@GetMapping("/showPostList/{ddoCode}")
	public String showPostList(@ModelAttribute("mstMenuModel") MstMenuModel mstMenuModel, Model model, Locale locale,@PathVariable String ddoCode,
			HttpSession session) {

		String message = (String) model.asMap().get("message");
		model.addAttribute("mstMenuModel", mstMenuModel);
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstMenu", commonHomeMethodsService.findAllMenu(locale.getLanguage()));
		
		
		List getPostNameForDisplay = activeInactivePostService.getPostNameForDisplay(ddoCode);
		model.addAttribute("getPostNameForDisplay", getPostNameForDisplay);
		addMenuAndSubMenu(model, messages);
		return "/views/active-inactive-post";
	}
	
	@RequestMapping("/updatePostStatus/{postId}/{activateFlag}")
	public ResponseEntity<String> updatePostStatus(@PathVariable Long postId,@PathVariable Long activateFlag,Model model,Locale locale,HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		activeInactivePostService.updatePostStatus(postId,activateFlag,messages);
		String  msg="";
		if(activateFlag==0) {
			msg="Post In Activated Successfully";
		}else {
			msg="Post Activated Successfully";
		}
		return ResponseEntity.ok(msg);
	}

}
