package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpAdminOfficeMst;
import com.mahait.gov.in.model.ZpAdminOfficeMstModel;
import com.mahait.gov.in.response.MessageResponse;
import com.mahait.gov.in.service.AdminOfficeMasterService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/mdc")
@Controller
public class AdminOfficeMasterController  extends BaseController{

	@Autowired
	AdminOfficeMasterService adminOfficeMasterService;

	@GetMapping("/adminOfficeMaster")
	public String adminOfficeMaster(Model model, Locale locale, HttpSession session,
			@ModelAttribute("zpAdminOfficeMstModel") ZpAdminOfficeMstModel zpAdminOfficeMstModel) {

		String officeCode = adminOfficeMasterService.generateOfficeCode();
		zpAdminOfficeMstModel.setOfficeCode(officeCode);
		model.addAttribute("zpAdminOfficeMst", zpAdminOfficeMstModel);

		MessageResponse messageResponse = (MessageResponse) model.asMap().get("messageResponse");
		if (messageResponse != null) {
			model.addAttribute("messageResponse", messageResponse);
		}

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<ZpAdminOfficeMstModel> lstZpAdminOfficeMstModel = adminOfficeMasterService.findAllZpAdminOfficeMstModel();

		model.addAttribute("lstZpAdminOfficeMstModel", lstZpAdminOfficeMstModel);
		
		
		addMenuAndSubMenu(model,messages);

		return "/views/admin-office-master";
	}

	/*
	 * @GetMapping("/adminOffice") public String adminOffice(Model model, Locale
	 * locale, HttpSession session) { adminOfficeMasterService.sa return
	 * "/views/admin-office"; }
	 */

	@PostMapping("/createOfficeMaster")
	public String createOfficeMaster(HttpSession session,
			@ModelAttribute("zpAdminOfficeMstModel") ZpAdminOfficeMstModel zpAdminOfficeMstModel,
			RedirectAttributes redirectAttributes) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		zpAdminOfficeMstModel.setCreatedBy(messages.getUserId());
		zpAdminOfficeMstModel.setCreatedByPost(messages.getUserId());
		zpAdminOfficeMstModel.setIsActive(1);
		
		

		ZpAdminOfficeMst zpAdminOfficeMst = adminOfficeMasterService.createOffice(zpAdminOfficeMstModel);

		MessageResponse messageResponse = new MessageResponse();
		if (zpAdminOfficeMst != null) {
			messageResponse.setResponse("Office Created Successfully");
			messageResponse.setStyle("alert alert-success");
			messageResponse.setStatusCode(200);
		} else {
			messageResponse.setResponse("Something went wrong");
			messageResponse.setStyle("alert alert-error");
			messageResponse.setStatusCode(200);
		}
		redirectAttributes.addFlashAttribute("messageResponse", messageResponse);
		return "redirect:/mdc/adminOfficeMaster";
	}

	@GetMapping("/findOfficebyId/{officeId}")
	public String findOfficebyId(HttpSession session, @PathVariable Long officeId) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		ZpAdminOfficeMstModel zpAdminOfficeMstModel = adminOfficeMasterService.findOfficebyId(officeId);
		return "redirect:/mdc/adminOfficeMaster";
	}

	@GetMapping("/deleteOfficeById/{officeId}")
	public String deleteOfficeById(HttpSession session, @PathVariable Long officeId) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Integer isDeleted = adminOfficeMasterService.deleteOfficeById(officeId, messages);
		return "redirect:/mdc/adminOfficeMaster";
	}

	@RequestMapping(value = "/officeNameExists/{officeName}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> officeNameExists(@PathVariable String officeName) {
		boolean response1 = adminOfficeMasterService.officeNameExists(officeName);
		return ResponseEntity.ok(response1);
	}

}
