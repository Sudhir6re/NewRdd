package com.mahait.gov.in.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.mahait.gov.in.entity.AllowanceDeductionMstEntity;
import com.mahait.gov.in.entity.HRAAllowanceMstEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DDOScreenModel;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.HRAAllowanceMstService;
import com.mahait.gov.in.service.HRAAllowanceMstServiceImpl;
import com.mahait.gov.in.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/master")
public class HRAAllowanceMstController   extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	HRAAllowanceMstService hraAllowanceMstService;
	
	@Autowired
	HRAAllowanceMstServiceImpl hraAllowanceMstServiceImpl;
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@GetMapping("/HRAAllowance")
	public String HRAAllowance(Model model,@ModelAttribute("hraAllowanceMstEntity") HRAAllowanceMstEntity hraAllowanceMstEntity , @ModelAttribute("allowanceDeductionMstEntity") AllowanceDeductionMstEntity allowanceDeductionMstEntity, HttpSession session,Locale locale) {
		model.addAttribute("hraAllowanceMstEntity", hraAllowanceMstEntity);
		model.addAttribute("lstddcPayCommission", hraAllowanceMstService.getlstddcPayCommission());
		model.addAttribute("lstAllowanceDeductionMst", hraAllowanceMstService.getlstAllowanceDeductionMst());
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();
		String message = (String)model.asMap().get("message");
//		UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
//		int levelRoleVal = messages.getRole_id();
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		session.setAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		// logger.info(""+messages.getFullName());
		int levelRoleVal = messages.getMstRoleEntity().getRoleId();

		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());
		
		menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal,locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal,locale.getLanguage());
		
		model.addAttribute("menuList", menuList);
		model.addAttribute("subMenuList", subMenuList);
		addMenuAndSubMenu(model,messages);
		return "/views/HRAAllowanceMst";
		
	}
	
	
	@RequestMapping("/saveHRAAllowanceMaster")
	public ModelAndView saveAllowanceDeductionWiseMaster(HttpServletRequest request, Model model,
			HttpServletResponse response, Locale locale, HttpSession session,
			@ModelAttribute("hraAllowanceMstEntity")  HRAAllowanceMstEntity hraAllowanceMstEntity,RedirectAttributes redirectAttributes) throws ParseException {
		ModelAndView modelAndView=new ModelAndView();
		
		
		request.getSession().setAttribute("MY_SESSION_MESSAGES",
				userService.getUserIdbyUserName(request.getRemoteUser()));
//		UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		session.setAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("sessionMessages", messages.getMstRoleEntity().getRoleId());
		// logger.info(""+messages.getFullName());
		modelAndView.addObject("userName", messages.getUserName());
		
		hraAllowanceMstEntity.setCreatedUserId(messages.getMstRoleEntity().getRoleId());
		int checkIsDataAlreadyPresent=hraAllowanceMstService.checkComponentAlreadyPresent(hraAllowanceMstEntity);
		if(checkIsDataAlreadyPresent!=0) {
			HRAAllowanceMstEntity hRAAllowanceMstEntity=hraAllowanceMstService.findAllowanceDeductionWiseMasterById(checkIsDataAlreadyPresent);
			hRAAllowanceMstEntity.setEndDate(subtractDate(hraAllowanceMstEntity.getStartDate(),1));
			hraAllowanceMstService.updateRRAAllowanceMstEntity(hRAAllowanceMstEntity);
		}
		hraAllowanceMstEntity.setEndDate(null);
		int isSave=hraAllowanceMstService.SaveHRAAllowanceMaster(hraAllowanceMstEntity);
		if(isSave>0) {
			redirectAttributes.addFlashAttribute("message", "1");
		}else {
			redirectAttributes.addFlashAttribute("message", "0");
		}
		return new ModelAndView("redirect:/master/HRAAllowance");
	}
	public  Date subtractDate(Date date,int subtractDay) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -subtractDay);
        date = c.getTime();
        return date;
    }
	
	@RequestMapping(value="/EditHRAAllowanceMaster/{allowanceHRAId}")	// , method = RequestMethod.POST
    public String approveOffice (Model model,@ModelAttribute("hraAllowanceMstEntity") HRAAllowanceMstEntity hraAllowanceMstEntity,@PathVariable int allowanceHRAId,
    		RedirectAttributes redirectAttributes,Locale locale,HttpSession session) {
		model.addAttribute("hraAllowanceMstEntity", hraAllowanceMstService.findEntrybyId(allowanceHRAId));
		model.addAttribute("lstAllowanceDeductionMst", hraAllowanceMstService.getlstAllowanceDeductionMst());
		model.addAttribute("language", locale.getLanguage());
//		UserInfo messages  = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());
		
		model.addAttribute("lstddcPayCommission", hraAllowanceMstService.getlstddcPayCommission());
		
		addMenuAndSubMenu(model,messages);
		return "/views/HRAAllowanceMstEdit";
    }
	
	@PostMapping("/EditHRAAllowanceMasterSave")
	public String EditHRAAllowanceMasterSave(@ModelAttribute("hraAllowanceMstEntity") @Valid HRAAllowanceMstEntity hraAllowanceMstEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/HRAAllowanceMstEdit"; 
		} 
		String message = hraAllowanceMstService.hRAAllowanceMstUpdate(hraAllowanceMstEntity);
		if(message.equals("UPDATED")) {
			redirectAttributes.addFlashAttribute("message","Record Updated Successfully");
//			logger.info("msg "+message);
	}
	
		return "redirect:/master/HRAAllowance"; 
	}
	@RequestMapping(value = "/deleteAllowanceHRA/{allowanceHRAId}") // , method = RequestMethod.POST
	public String deleteAllowanceHRA(@PathVariable int allowanceHRAId, Model model, Locale locale) {
		HRAAllowanceMstEntity hraAllowanceMstEntity = hraAllowanceMstService.findAllowanceHRAByIdForReject(allowanceHRAId);
		if (hraAllowanceMstEntity != null) {
			model.addAttribute("ddoScreenModel", new DDOScreenModel());
			model.addAttribute("language", locale.getLanguage());
		}
		return "redirect:/master/HRAAllowance";
	}

	
}
