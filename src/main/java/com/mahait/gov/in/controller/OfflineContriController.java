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
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.EmpChangeDetailsService;
import com.mahait.gov.in.service.LocationMasterService;
import com.mahait.gov.in.service.MstBankService;
import com.mahait.gov.in.service.MstDesignationService;
import com.mahait.gov.in.service.MstEmployeeService;
import com.mahait.gov.in.service.NewRegDDOService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class OfflineContriController extends BaseController {

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	NewRegDDOService newRegDDOService;

	@Autowired
	EmpChangeDetailsService empChangeDetailsService;

	@Autowired
	LocationMasterService locationMasterService;

	@Autowired
	MstEmployeeService mstEmployeeService;

	@Autowired
	MstDesignationService mstDesignationService;

	@Autowired
	MstBankService mstBankService;

	List<NewRegDDOModel> emplist = new ArrayList<>();

	@GetMapping("/loadOfflineDCPSForm")
	public String loadOfflineDCPSForm(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel, Model model,
			Locale locale, HttpSession session) {
		/*
		 * UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		 * int levelRoleVal = messages.getRole_id(); List<TopicModel> menuList = new
		 * ArrayList<>(); List<TopicModel> subMenuList = new ArrayList<>();
		 * 
		 * menuList =
		 * commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal,locale.getLanguage
		 * ()); subMenuList =
		 * commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal,locale.getLanguage(
		 * ));
		 * 
		 * model.addAttribute("menuList", menuList); model.addAttribute("subMenuList",
		 * subMenuList);
		 * 
		 * String message=(String) model.asMap().get("message");
		 * 
		 * 
		 * //model.addAttribute("", getDesignation()) emplist =
		 * empWiseCityClassService.findAllEmployee(messages.getUserName());
		 * newRegDDOModel.setEmplist(emplist);
		 * 
		 * 
		 * model.addAttribute("lstDDO",newRegDDOService.findLvl1DDOCode(messages.
		 * getUserName()));
		 * model.addAttribute("empLst",newRegDDOService.findEmpLst(messages.getUserName(
		 * )));
		 */
		/*
		 * model.addAttribute("lstDistrictLst",empWiseCityClassService.lstGetAllDistrict
		 * ());
		 * model.addAttribute("lstTaluka",empWiseCityClassService.lstGetAllTaluka());
		 * ///model.addAttribute("lstTalukaLst",empWiseCityClassService.
		 * findCityClasssLst());
		 */ model.addAttribute("newRegDDOModel", newRegDDOModel);
		/// model.addAttribute("message", message);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		return "/views/OfflineEntry";

	}

}
