package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.mahait.gov.in.model.MstEmployeeModel;
import com.mahait.gov.in.service.ChangeDcpsGpfService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/mdc")
@Controller
public class ChangeDcpsGpfController extends BaseController {
	
	@Autowired
	ChangeDcpsGpfService changeDcpsGpfService;
	
	@GetMapping("/changeDcpsGpf")
	public String changeDcpsGpf(Model model, Locale locale, HttpSession session,
			@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		return "/views/change-dcps-gpf";
	}

	@RequestMapping(value = "/getDOJ/{sevaarthId}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDOJ(@PathVariable String sevaarthId) {
		
		return ResponseEntity.ok(changeDcpsGpfService.getDOJ(sevaarthId));
	}
	
	
	@PostMapping("/savechangeDcpsGpf")
	public String savechangeDcpsGpf(
			@ModelAttribute("mstEmployeeModel") @Valid MstEmployeeModel mstEmployeeModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			return "/views/change-dcps-gpf";
		}
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Long afterSaveId = changeDcpsGpfService.savechangeDcpsGpf(mstEmployeeModel, messages);
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		return "redirect:/mdc/changeDcpsGpf";
	}
	
}
