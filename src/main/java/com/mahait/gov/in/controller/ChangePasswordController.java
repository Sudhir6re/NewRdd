package com.mahait.gov.in.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ChangePasswordModel;
import com.mahait.gov.in.service.ChangePasswordServceImpl;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value= {"/ddoast","/ddo","/user","/super","/mdc"})
public class ChangePasswordController  extends BaseController{

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	private UserService userService;

	@Autowired
	private ChangePasswordServceImpl changePasswordServiceImpl;

	@GetMapping("/changePassword")
	public String changePassword(@ModelAttribute("changePasswordModel") ChangePasswordModel changePasswordModel,
			Model model, Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		int levelRoleVal = messages.getMstRoleEntity().getRoleId();
		
		session.setAttribute("menuList", new ArrayList<>());
		session.setAttribute("subMenuList", new ArrayList<>());

	
		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}

		model.addAttribute("sessionMessages", levelRoleVal);
		model.addAttribute("userName", messages.getUserName());
		model.addAttribute("roleid", levelRoleVal);
		
		model.addAttribute("language", locale.getLanguage());
		
		
		switch(levelRoleVal) {
		case 1:
			model.addAttribute("action", "/mdc/updatePassword");
			break;
		case 2:
			model.addAttribute("action",  "/ddo/updatePassword");
			break;
		case 3:
			model.addAttribute("action",  "/ddoast/updatePassword");
			break;
		case 4:
			model.addAttribute("action",  "/user/updatePassword");
			break;
		case 5:
			model.addAttribute("action",  "/super/updatePassword");
			break;
		}
		
		
		
		model.addAttribute("changePasswordModel", changePasswordModel);
		return "/views/change-password";

	}

	@PostMapping("/updatePassword")
	public ModelAndView updatePassword(
			@ModelAttribute("changePasswordModel") @Valid ChangePasswordModel changePasswordModel,
			BindingResult bindingResult, Model model, Locale locale, RedirectAttributes redirectAttributes,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		int levelRoleVal = messages.getMstRoleEntity().getRoleId();
		if (bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			//return new ModelAndView("redirect:/master/changePassword");
			
			switch(levelRoleVal) {
			case 1:
				return new ModelAndView("redirect:/mdc/changePassword");
			case 2:
				return new ModelAndView("redirect:/ddo/changePassword");
			case 3:
				return new ModelAndView("redirect:/ddoast/changePassword");
			case 4:
				return new ModelAndView("redirect:/user/changePassword");
			case 5:
				return new ModelAndView("redirect:/super/changePassword");
			}
		}

		
		if (messages != null) {
			if (changePasswordModel.getNewPasswordConfirm().equals(changePasswordModel.getNewPassword())) {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String oldPass = changePasswordModel.getPassword();
				String newPass = bCryptPasswordEncoder.encode(changePasswordModel.getNewPasswordConfirm());

				if (bCryptPasswordEncoder.matches(oldPass, messages.getPassword())) {

					messages.setPassword((bCryptPasswordEncoder.encode(changePasswordModel.getNewPasswordConfirm())));
					
					messages.setUpdatedDate(new Timestamp(new Date().getTime()));
					String message = changePasswordServiceImpl.updateUser(messages);
					if (message.equals("UPDATED")) {
						redirectAttributes.addFlashAttribute("message", "UPDATED");
						session.invalidate();
						return new ModelAndView("redirect:/user/login");
					}

				} else {
					redirectAttributes.addFlashAttribute("message", "Old Password Does Not Matched !!!");
					// session.invalidate();
					//return new ModelAndView("redirect:/master/changePassword");
				}
			} else {

				redirectAttributes.addFlashAttribute("message", "ERROR");
				model.addAttribute("changePasswordModel", changePasswordModel);
				
			}
		}
		model.addAttribute("language", locale.getLanguage());
		
		
		return new ModelAndView("redirect:/user/login");
	}

}