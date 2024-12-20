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

import com.mahait.gov.in.entity.ComponentMstEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.ComponentMstService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@RequestMapping("/mdc")
@Controller
public class ParameterMstController extends BaseController{
	

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
		
	@Autowired
	ComponentMstService componentMstService;
		
	@GetMapping("/loadParameterPage")
	public String loadParameterPage(@ModelAttribute("componentParameterMstEntity") ComponentMstEntity componentParameterMstEntity,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		
		model.addAttribute("componentParameterMstEntity", componentParameterMstEntity);
	
		model.addAttribute("language", locale.getLanguage());
		List<ComponentMstEntity> components = componentMstService.lstAllComponent();
		components.forEach(component -> 
	    System.err.println("ID: " + component.getId() + ", Component Name: " + component.getComponentName())
	);

		model.addAttribute("lstCompPrameterMst",components);
		return "/views/parameter-mst";
    }
	
	@PostMapping("/saveNewComponent")
	public String saveNewComponent(@ModelAttribute("componentParameterMstEntity") ComponentMstEntity componentParameterMstEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, Locale locale, HttpSession session) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/parameter-mst"; /*Return to HTML Page*/
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("componentParameterMstEntity", componentParameterMstEntity);
		OrgUserMst orgUserMst  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		redirectAttributes.addFlashAttribute("message","SUCCESS");
		model.addAttribute("lstPrameterMst",componentMstService.saveComponent(componentParameterMstEntity));
		model.addAttribute("language", locale.getLanguage());
		return "redirect:/mdc/loadParameterPage"; /*redirects to controller URL*/
	}
	

	@RequestMapping(value="/editParameterComponent/{id}")	// , method = RequestMethod.POST
    public String editParameterComponent(@PathVariable long id, RedirectAttributes redirectAttributes, Model model,Locale locale,HttpSession session) {
		
		model.addAttribute("componentParameterMstEntity", componentMstService.findMenuByKeyForEdit(id));
		model.addAttribute("language", locale.getLanguage());
			
		return "/views/edit-component-parameter-mst";
	}	
	
	@PostMapping("/saveEditComponentParameter")
	public String saveEditComponentParameter(@ModelAttribute("componentParameterMstEntity") @Valid ComponentMstEntity componentParameterMstEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {
		
		OrgUserMst orgUserMst  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			return "/views/edit-component-parameter-mst"; 
		} 
		String message = componentMstService.saveEditMenu(componentParameterMstEntity,orgUserMst);
		if(message.equals("UPDATED")) {
			redirectAttributes.addFlashAttribute("message","UPDATED");
		}
		return "redirect:/mdc/loadParameterPage"; /*redirects to controller URL*/
	}
	
	
	@RequestMapping(value = "/deleteComponentParameter/{id}") // , method = RequestMethod.POST
	public String deleteComponentParameter(@PathVariable long id, Model model, Locale locale, RedirectAttributes redirectAttributes,HttpSession session) {

		String message= componentMstService.findMenuByKeyForDelete(id);
		System.err.println("SSSSS"+message);
		if(message.equals("DELETED")) {
			System.err.println("SSSSS"+message);
			redirectAttributes.addFlashAttribute("message","UPDATED");
			model.addAttribute("language", locale.getLanguage());
		}
			return "redirect:/mdc/loadParameterPage";
	}


}
