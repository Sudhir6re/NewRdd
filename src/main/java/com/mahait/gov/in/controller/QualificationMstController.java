package com.mahait.gov.in.controller;

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
import com.mahait.gov.in.entity.QualificationEntity;
import com.mahait.gov.in.service.QualificationService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/mdc")
@Controller
public class QualificationMstController extends BaseController {
	
 @Autowired
 QualificationService qualificationService;
 
	@GetMapping("/loadQualificationPage")
	public String loadParameterPage(@ModelAttribute("qualificationEntity") QualificationEntity qualificationEntity,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		
		model.addAttribute("qualificationEntity", qualificationEntity);
	
		model.addAttribute("language", locale.getLanguage());
		List<QualificationEntity> components = qualificationService.lstAllQualification();
		components.forEach(component -> 
	   System.err.println("ID: " + component.getQId() + ", Qualification: " + component.getQualification())
	);

		model.addAttribute("lstOfQualificationMst",components);
		return "/views/qualification-mst";
 }
	
	@PostMapping("/saveNewQualification")
	public String saveNewComponent(@ModelAttribute("qualificationEntity") QualificationEntity qualificationEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, Locale locale, HttpSession session) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/qualification-mst"; /*Return to HTML Page*/
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("qualificationEntity", qualificationEntity);
		OrgUserMst orgUserMst  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		redirectAttributes.addFlashAttribute("message","SUCCESS");
		model.addAttribute("lstQualificationMst",qualificationService.saveQualification(qualificationEntity));
		model.addAttribute("language", locale.getLanguage());
		return "redirect:/mdc/loadQualificationPage"; /*redirects to controller URL*/
	}
	

	@RequestMapping(value="/editQualification/{id}")	// , method = RequestMethod.POST
 public String editParameterComponent(@PathVariable long id, RedirectAttributes redirectAttributes, Model model,Locale locale,HttpSession session) {
		
		model.addAttribute("qualificationEntity", qualificationService.findQualificationbyidForEdit(id));
		model.addAttribute("language", locale.getLanguage());
			
		return "/views/edit-qualification";
	}	
	
	@PostMapping("/saveEditQualification")
	public String saveEditComponentParameter(@ModelAttribute("qualificationEntity") @Valid QualificationEntity qualificationEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {
		
		OrgUserMst orgUserMst  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/edit-qualification"; 
		} 
		String message = qualificationService.saveEditQualification(qualificationEntity,orgUserMst);
		if(message.equals("UPDATED")) {
			redirectAttributes.addFlashAttribute("message","UPDATED");
		}
		return "redirect:/mdc/loadQualificationPage"; /*redirects to controller URL*/
	}
	
	
	@RequestMapping(value = "/deleteQualification/{id}") // , method = RequestMethod.POST
	public String deleteComponentParameter(@PathVariable long id, Model model, Locale locale, RedirectAttributes redirectAttributes,HttpSession session) {

		String message= qualificationService.findQualificationByIdForDelete(id);
		System.err.println("SSSSS"+message);
		if(message.equals("DELETED")) {
			redirectAttributes.addFlashAttribute("message","DELETED");
			model.addAttribute("language", locale.getLanguage());
		}
			return "redirect:/mdc/loadQualificationPage";
	}


}

