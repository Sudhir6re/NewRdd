package com.mahait.gov.in.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.AnnualIncrementModel;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.EmployeeIncrementService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ddo")
public class EmployeeIncrementController  extends BaseController{
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	EmployeeIncrementService employeeIncrementService;
	
	
	List<NewRegDDOModel> emplist = new ArrayList<>();
	
	@GetMapping("/getIncrementDataForReptDDO")
	public String getIncrementDataForReptDDO(
			@ModelAttribute("annualIncrementModel") AnnualIncrementModel annualIncrementModel, Model model, Locale locale,
			HttpSession session) {
//		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Date date = new Date();
		int year = date.getYear();
		int currentYear = year + 1900;
		String currYear= String.valueOf(currentYear);
		model.addAttribute("incrementOrderempLst", employeeIncrementService.getIncrementDataForReptDDO(messages.getUserName(),currYear));
		model.addAttribute("language", locale.getLanguage());
		///model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());
		
		addMenuAndSubMenu(model,messages);
		
		return "/views/approvalAnnual-increment";
	}
	
	@GetMapping("/MTR21/{orderNo}")
	public String MTR21(@ModelAttribute("annualIncrementModel") AnnualIncrementModel annualIncrementModel, Model model,
			Locale locale, HttpSession session, @PathVariable String orderNo) {
		///UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		int levelRoleVal = 2;
		/*List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();

		menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal, locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal, locale.getLanguage());*/

		model.addAttribute("systemdate", sdf.format(new Date()));
		///model.addAttribute("officeName", employeeIncrementService.officeName(messages.getUserName()));
		
		model.addAttribute("lstEmp", employeeIncrementService.lstEmpforMTR21(orderNo,levelRoleVal,messages.getUserName()));//,messages.getUpdatedByPost().getLocationCode()
		///model.addAttribute("menuList", menuList);
		///model.addAttribute("subMenuList", subMenuList);
		
		addMenuAndSubMenu(model,messages);
		return "/views/report/MTR21Report";
	}
	
	@PostMapping("/approveAnnualIncrement")
	public String approveAnnualIncrement(
			@ModelAttribute("annualIncrementModel") @Valid AnnualIncrementModel annualIncrementModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		ModelAndView modelAndView = new ModelAndView();
		int saveId = employeeIncrementService.approveAnnualIncrement(annualIncrementModel, messages);

		if (saveId != 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		return "redirect:/ddo/approvalAnnualIncrement"; /// redirects to controller URL
	}

	
	}

