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

import com.mahait.gov.in.entity.MstDepartmentEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ZpAdminOfficeMstModel;
import com.mahait.gov.in.service.DepartmentMasterService;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/mdc")
@Controller
public class DepartmentMasterController  extends BaseController {
	
	@Autowired
	DepartmentMasterService departmentMasterService;
	
	
	@GetMapping("/deptMaster")
	public String deptMaster( Model model, Locale locale,
			HttpSession session,@ModelAttribute("mstDepartmentEntity") MstDepartmentEntity mstDepartmentEntity) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);	
		List<MstDepartmentEntity> deptList = new ArrayList<>();
		deptList = departmentMasterService.listOfDept();
		
		
		
		model.addAttribute("deptList", deptList);
		return "/views/department-master";
	}
	@GetMapping("/addDeptMaster")
	public String addDeptMaster( Model model, Locale locale,
			HttpSession session,@ModelAttribute("mstDepartmentEntity") MstDepartmentEntity mstDepartmentEntity) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		List<ZpAdminOfficeMstModel> adminOfc = new ArrayList<>();
		
		adminOfc = departmentMasterService.lstadminOfc();
		model.addAttribute("adminOfc", adminOfc);
		
		
		return "/views/add-department-master";
	}

}
