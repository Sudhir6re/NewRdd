package com.mahait.gov.in.controller;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.repository.UserInfoRepo;
import com.mahait.gov.in.service.ChangePasswordNewService;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.UserService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/mdc")
public class changePasswordNewController  extends BaseController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	private ChangePasswordNewService changePasswordNewService;
	
	@Autowired
	private UserInfoRepo userInfoDAO;

	
	
    @GetMapping(value = "/changePasswordNew") 
	public String changePasswordNew(@ModelAttribute("orgUserMst") OrgUserMst orgUserMst,Model model,HttpSession session,Locale locale) {
	
	List<TopicModel> menuList = new ArrayList<>();
	List<TopicModel> subMenuList = new ArrayList<>();
		
	
	OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	addMenuAndSubMenu(model,messages);
	model.addAttribute("orgUserMst", orgUserMst);
		return "/views/change-password-new";
	}
    
    
    
    @PostMapping("/updatePasswordNew")
	public String updatePasswordNew(@ModelAttribute("orgUserMst") OrgUserMst orgUserMst,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,HttpSession session) {
		 
    	
    //	UserInfo messages  = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
    	
    	OrgUserMst orgUserMst1=userInfoDAO.getUserIdbyUserName(orgUserMst.getUserName());
    	//update password code start here
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	orgUserMst1.setPassword((bCryptPasswordEncoder.encode(orgUserMst.getPassword())));
    	changePasswordNewService.updatePassword(orgUserMst1);
    	//end
    	
		return "redirect:/mdc/changePasswordNew";
	}
    
    
    
    
    
}
