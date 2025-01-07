package com.mahait.gov.in.nps.controller;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.controller.BaseController;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;
import com.mahait.gov.in.nps.service.LegacyValidationService;
import com.mahait.gov.in.nps.service.NSDLDetailsService;
import com.mahait.gov.in.response.MessageResponse;
import com.mahait.gov.in.service.CommonHomeMethodsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddo")
@Controller
public class LegacyValidationController extends BaseController {

	@Autowired
	LegacyValidationService legacyValidationService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	NSDLDetailsService nsdlDetailsService;

	@GetMapping("/legacyValidation")
	public String legacyValidation(Model model, Locale locale, HttpSession session,
			@ModelAttribute("dcpsLegacyModel") DcpsLegacyModel dcpsLegacyModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		Calendar cal = Calendar.getInstance();
		Integer currmonth = new Integer(cal.get(2) + 1);
		Integer curryear = new Integer(cal.get(1));

		dcpsLegacyModel.setMonth(currmonth);
		dcpsLegacyModel.setYear(curryear);

		List<DcpsLegacyModel> lstDcpsLegacyModel = legacyValidationService.findNsdlLegacyList(dcpsLegacyModel,
				messages);
		dcpsLegacyModel.setLstDcpsLegacyModel(lstDcpsLegacyModel);


		model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());
		return "/views/nps/legacy-validation";
	}

	@PostMapping("/searchLegacyDataByYearAndMonth")
	public ResponseEntity<List<DcpsLegacyModel>> searchLegacyDataByYearAndMonth(Model model, Locale locale,
			HttpSession session, @RequestBody DcpsLegacyModel dcpsLegacyModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<DcpsLegacyModel> lstDcpsLegacyModel = legacyValidationService.findNsdlLegacyList(dcpsLegacyModel,
				messages);
		return ResponseEntity.ok(lstDcpsLegacyModel);
	}
	
	@GetMapping("/viewAndSaveLegacyFile/{fileId}")
	public void viewAndSaveLegacyFile(@PathVariable String fileId,Locale locale,HttpSession session,
	        HttpServletRequest request,HttpServletResponse response) {
	    OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	    PrintWriter outputfile = null;
	    try {
	        String txtFileContent = legacyValidationService.viewAndSaveLegacyFile(messages, fileId);
	        String fileName = fileId + ".txt";
	        response.setContentType("text/plain;charset=UTF-8");
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	        response.setCharacterEncoding("UTF-8");
	        // Write content to the output stream
	        outputfile = response.getWriter();
	        outputfile.write(txtFileContent);
	        outputfile.flush();
	    } catch (Exception e) {
	        System.out.println("Error while generating legacy file: "+e);
	    } finally {
	        if (outputfile != null) {
	            outputfile.close();
	        }
	    }
	}


	@GetMapping("/deleteLegacyData/{fileId}/{bhId}")
	public ResponseEntity<MessageResponse> deleteLegacyData(Model model, Locale locale,
			HttpSession session,@PathVariable String fileId,@PathVariable String bhId) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		MessageResponse messageResponse = legacyValidationService.deleteLegacyData(messages,fileId,bhId);
		return ResponseEntity.ok(messageResponse);
	}
	
	@GetMapping("/validateLegacyData/{fileId}/{month}/{year}")
	public String validateLegacyData(Model model, Locale locale,
			HttpSession session,@PathVariable String fileId,@PathVariable Integer month,@PathVariable Integer year,HttpServletRequest request,HttpServletResponse response) {
	    OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	    PrintWriter outputfile = null;
	    try {
	        String txtFileContent = legacyValidationService.validateLegacyData(messages, fileId,month,year);
	        String fileExtwithContent[]=txtFileContent.split("-");
	        String fileName = fileExtwithContent[0] + "." + fileExtwithContent[1];
	        response.setContentType("text/plain;charset=UTF-8");
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	        response.setCharacterEncoding("UTF-8");
	        outputfile = response.getWriter();
	        outputfile.write(txtFileContent);
	        outputfile.flush();
	    } catch (Exception e) {
	        System.out.println("Error while generating legacy file: "+e);
	    } finally {
	        if (outputfile != null) {
	            outputfile.close();
	        }
	    }
		return fileId;
	}
	

}
