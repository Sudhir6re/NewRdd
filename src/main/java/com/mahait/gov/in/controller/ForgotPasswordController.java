package com.mahait.gov.in.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.ForgotPasswordService;
import com.mahait.gov.in.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@PropertySource(value = { "classpath:application.properties" })
public class ForgotPasswordController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@Autowired
	private UserService userService;

	@GetMapping("/forgotPassword")
	public String showForgotPasswordForm(@ModelAttribute("userInfo") OrgUserMst orgUserMst, Model model,
			Locale locale) {
		model.addAttribute("orgUserMst", orgUserMst);
		return "views/forgotPassword/forgot_password_form";
	}

	@PostMapping("/sendToken")
	public ResponseEntity<String> sendToken(HttpServletRequest request, @Param(value = "email") String email) {
		OrgUserMst orgUserMst1 = forgotPasswordService.initiatePasswordReset(email, request);
	
		  Map<String, String> response = new HashMap<String, String>();
		if (orgUserMst1 != null) {
			return ResponseEntity.ok("1");
		} else {
			return ResponseEntity.ok("0");
		}
	}

	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		OrgUserMst orgUserMst = forgotPasswordService.checkTokenIsValid(token);
		model.addAttribute("token", token);

		if (orgUserMst == null) {
			model.addAttribute("message", "Invalid Token");
			return "views/forgotPassword/message";
		}
		return "views/forgotPassword/reset_password_form";
	}

	@PostMapping("/resetPassword")
	public String resetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");
		String cnfrmPassword = request.getParameter("cnfrmPassword");

		if (!password.equals(cnfrmPassword)) {
			model.addAttribute("message", "Password Confirm password mismatched !!!");
			return "views/forgotPassword/message";
		}

		OrgUserMst orgUserMst = forgotPasswordService.checkTokenIsValid(token);
		model.addAttribute("title", "Reset your password");

		if (orgUserMst == null) {
			model.addAttribute("message", "Invalid Token");
			return "views/forgotPassword/message";
		} else {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			orgUserMst.setPassword(bCryptPasswordEncoder.encode(password));
			forgotPasswordService.updatePassword(orgUserMst);

			model.addAttribute("message", "You have successfully changed your password.");
		}

		return "redirect:/user/login";
	}

}
