package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MpgSchemeBillGroupModel;
import com.mahait.gov.in.model.MstSchemeModel;
import com.mahait.gov.in.service.DdoBillGroupService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddoast")

public class DdoBillGroupAttachDetachController  extends BaseController{

	@Autowired
	DdoBillGroupService ddoBillGroupService;

	// public String loadAttachBillGroup(HttpServletRequest request, Model model,
	// HttpServletResponse response,
	// Locale locale, HttpSession session) {
	//
	// String message = (String) model.asMap().get("message");
	//
	//
	// model.addAttribute("language", locale.getLanguage());
	// return "/views/attach_dettach_employee";
	// }
	//
	@RequestMapping(value = "/attachDeattachEmployee1", method = { RequestMethod.GET })
	public String employeeAttachDettach(
			@ModelAttribute("mpgSchemeBillGroupModel") MpgSchemeBillGroupModel mpgSchemeBillGroupModel,
			@ModelAttribute("mstSchemeModel") MstSchemeModel mstSchemeModel, Model model, Locale locale,
			HttpSession session) {

		String message = (String) model.asMap().get("message");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		// List<TopicModel> menuList = new ArrayList<>();
		// List<TopicModel> subMenuList = new ArrayList<>();
		//
		// menuList =
		// commonHomeMethodsService.findMenuNameByRoleID(messages.getRole_id(),locale.getLanguage());
		// subMenuList =
		// commonHomeMethodsService.findSubMenuByRoleID(messages.getRole_id(),locale.getLanguage());
		//
		// model.addAttribute("menuList", menuList);
		// model.addAttribute("subMenuList", subMenuList);
		//
		/*
		 * model.addAttribute("lstAllBillGroup",
		 * mstBillGroupService.findAllBillGroup());
		 */
		model.addAttribute("lstAllBillGroup", ddoBillGroupService.lstBillName(messages.getDdoCode()));
		;
		/*
		 * model.addAttribute("lstMpgSchemeBillGroupDataTable",
		 * mpgSchemeBillGroupService.findAllMpgSchemeBillGroupByDDOCode(messages.
		 * getUserName()));
		 */
		model.addAttribute("language", locale.getLanguage());
		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		if (message != null && message.equals("SAVEORUPDATE")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.SAVEUPDATEATTACHDETTACH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		/*
		 * model.addAttribute("lstDeptDataTable",
		 * mstEmployeeService.findAllEmployees());
		 * model.addAttribute("lstDDOWiseEmployee",
		 * mstEmployeeService.firstgetfindAllEmployeeByddoCode(messages.getUserName()));
		 */

		// model.addAttribute("lstDeptDataTable",
		// ddoBillGroupService.findAllEmployeesByDDOName(messages.getUserName()));
		// model.addAttribute("lstDDOWiseEmployee",
		// ddoBillGroupService.firstgetfindAllEmployeeByddoCode(messages.getUserName()));

		String search = (String) model.asMap().get("SEARCH");

		List<Object[]> lstAttachedEmployee = null;

		/// String type=(String)model.asMap().get("type");

		if (search != null && search.equals("SEARCH")) {
			String scmebillgroupid = (String) model.asMap().get("schemebillgroup");
			String type = (String) model.asMap().get("type");
			mpgSchemeBillGroupModel.setSchemebillGroupId(Long.valueOf(scmebillgroupid.trim()));
			mpgSchemeBillGroupModel.setType(Long.valueOf(type.trim()));
			mpgSchemeBillGroupModel.setBillGroupId(Long.valueOf(scmebillgroupid.trim()));
			
			if(type.equals("1")) {
				lstAttachedEmployee = ddoBillGroupService.findAttachedEmployee(messages.getDdoCode(),
						mpgSchemeBillGroupModel.getSchemebillGroupId().toString());
				if (lstAttachedEmployee != null && lstAttachedEmployee.size() != 0) {
					Object[] object = lstAttachedEmployee.get(0);
					model.addAttribute("billDescription", object[5]);
					mpgSchemeBillGroupModel.setBillGroupId(Long.valueOf(object[3].toString()));
				} else {
					// model.addAttribute("billDescription","");
					MstDcpsBillGroup mpgSchemeBillGroupEntity = ddoBillGroupService
							.findAllMpgSchemeBillGroupbyParameter(Long.valueOf(scmebillgroupid));
					mpgSchemeBillGroupModel.setBillGroupId(mpgSchemeBillGroupModel.getSchemebillGroupId());
				}
				model.addAttribute("lstAttachedEmployee", lstAttachedEmployee);
				model.addAttribute("lstDettachEmployee", ddoBillGroupService.findDettachEmployee(messages.getDdoCode(),
						mpgSchemeBillGroupModel.getSchemebillGroupId().toString()));
				
			}else if(type.equals("2")) {
				model.addAttribute("lstattachpost", ddoBillGroupService.findattachpostlist(messages.getDdoCode(),
						mpgSchemeBillGroupModel.getSchemebillGroupId().toString()));
				model.addAttribute("lstdetachpost", ddoBillGroupService.finddetachpostlist(messages.getDdoCode(),
						mpgSchemeBillGroupModel.getSchemebillGroupId().toString()));
			}
			
			model.addAttribute("type", type);
			
			
			model.addAttribute("mpgSchemeBillGroupModel", mpgSchemeBillGroupModel);
			
			
			
			
		} else {
			model.addAttribute("billDescription", "");
		}

		/*
		 * model.addAttribute("lstMpgSchemeBillGroupDataTable",
		 * mpgSchemeBillGroupService.findAllMpgSchemeBillGroup());
		 */
		addMenuAndSubMenu(model,messages);	
		model.addAttribute("language", locale.getLanguage());
		return "/views/attach_dettach_employee";
	}

	@RequestMapping(value = "/saveAttachDettachEmployee", method = { RequestMethod.POST })
public String saveAttachDettachEmployee(@ModelAttribute("mpgSchemeBillGroupModel") MpgSchemeBillGroupModel mpgSchemeBillGroupModel, 
		RedirectAttributes redirectAttributes, Model model, Locale locale) {
		
		
        String result = null;
        if(mpgSchemeBillGroupModel.getStatus().equals("SAVE")) {
        	if(mpgSchemeBillGroupModel.getType()==1) {
        	//MpgSchemeBillGroupModel mpgSchemeBillGroupModel
        	result=ddoBillGroupService.saveAttachDettachEmployee(mpgSchemeBillGroupModel);
        	}
        	else if(mpgSchemeBillGroupModel.getType()==2){
        	result=ddoBillGroupService.saveAttachDettachPost(mpgSchemeBillGroupModel);
        	}
        	
        	if(result.equals("save"))
        	redirectAttributes.addFlashAttribute("message", "SAVEORUPDATE");
        	return "redirect:/ddoast/attachDeattachEmployee1";
        }else

	{
		redirectAttributes.addFlashAttribute("SEARCH", "SEARCH");
		redirectAttributes.addFlashAttribute("schemebillgroup",
				mpgSchemeBillGroupModel.getSchemebillGroupId().toString());
		redirectAttributes.addFlashAttribute("type", mpgSchemeBillGroupModel.getType().toString());
		return "redirect:/ddoast/attachDeattachEmployee1";
	}

}}