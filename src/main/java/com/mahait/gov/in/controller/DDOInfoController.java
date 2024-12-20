package com.mahait.gov.in.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ApproveDDOHstModel;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DDOInfoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class DDOInfoController  extends BaseController {
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	DDOInfoService ddoInfoService;
	
	
	List<NewRegDDOModel> emplist = new ArrayList<>();
	
	@GetMapping("/ApproveDDOHistory")
	public String ApproveDDOHistory(
			@ModelAttribute("approveDDOHstModel") ApproveDDOHstModel approveDDOHstModel,Model model, Locale locale,
			HttpSession session) {
		String message = (String) model.asMap().get("message");
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		/*String lStrDdoCode = ddoInfoService.getDdoCodeForDDO(messages.getCreatedByPost().getPostId());
		 String ddo = null;
		 List <Object[]> ddoHistory = null;
         String ddoType = null;
         
         if (lStrDdoCode.isEmpty())
         {
             ddoHistory = null;
             model.addAttribute("ddoHistoryList", ddoHistory);
         } 
         else
         {*/
             //ddoType = lObjDdoInfoDAO.getDDOtype(lStrDdoCode);

		model.addAttribute("lstDDOofficeforApprv", ddoInfoService.getLevel1DDOList(messages.getUserName()));
                 // get level 1 ddo list
                 ///List <Object[]>level1DDOList = ddoInfoService.getLevel1DDOList(messages.getUserName());
                 // get ddo history
                /* model.addAttribute("ddoCode", lStrDdoCode);
                 for (int i = 0; i < level1DDOList.size(); i++)
                 {
                     ddo = ddo + "," + level1DDOList.get(i).toString();
                 }
       
             ddoHistory = ddoInfoService.getDDoHistoryDetailsForApprove(ddo);
             model.addAttribute("ddoHistoryList", ddoHistory);
*/
        /// }
		model.addAttribute("language", locale.getLanguage());
		addMenuAndSubMenu(model,messages);	
	//	model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		//model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());
		return "/views/ApproveDDOHistory";
	}
	
	}

