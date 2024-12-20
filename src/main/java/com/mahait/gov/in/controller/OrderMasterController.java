package com.mahait.gov.in.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstGrOrderModel;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.OrderMasterService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class OrderMasterController  extends BaseController {
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	OrderMasterService orderMasterService;
	
	
	List<NewRegDDOModel> emplist = new ArrayList<>();
	
	@GetMapping("/getOrderData")
	public String getOrderData(@ModelAttribute("mstGrOrderModel") MstGrOrderModel mstGrOrderModel,
			 Model model, Locale locale,
			HttpSession session) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		/*List<Object[]>grSubTypeList = orderMasterService.getSubGRType(10001198149L);
		
		List<Long> countOfDDOCode=orderMasterService.findUsertype(ddoCode);
		String userType=null;
   		if(!countOfDDOCode.equals(0))
   		{
   			userType="reportingDDO";
   		}
   		else 
   		 {
   			userType="finalDDO";
   		}
		model.addAttribute("lstDeptDataTable", orderMasterService.findAllDepartment());
		model.addAttribute("lstAllDistrict", orderMasterService.findAllDistrict());
		model.addAttribute("lstDesignation", orderMasterService.getDesignationMstData(locale.getLanguage()));
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstMstGrOrder", orderMasterService.lstMstGrOrder());
		model.addAttribute("fetchGRDataList", orderMasterService.fetchGRDataList());
		model.addAttribute("talukaLst", talukaLst);
		
		model.addAttribute("grSubTypeList", grSubTypeList);*/
		String ddoCode = messages.getUserName();
		String Type =ddoCode.substring(0,2);
		Long TypeOfSchool=Long.valueOf(Type);
		String typeOfOffice=null;
		if(TypeOfSchool !=2)
   		{
   			typeOfOffice="otherThanZp";
   		}
   		else 
   		 {
   			typeOfOffice="ZP";
   		}
		///String districtId=orderMasterService.getDistrictId(ddoCode);
		/*List<CmnTalukaMst>talukaLst = orderMasterService.gettalukalst(districtId);
		List<Long>locationList=orderMasterService.getSubDDOs(messages.getCreatedByPost().getPostId());
		 String locationcodeArray="";
		 if(locationList!=null && locationList.size()>0)
	    		for(int i=0;i< locationList.size();i++){
	    			if(i==0)
	    				locationcodeArray+=locationList.get(i).toString();
	    			else
	    				locationcodeArray+=","+locationList.get(i).toString();
	    		}
		///model.addAttribute("lstMstGrOrder", mstGrOrderService.lstMstGrOrder());
		model.addAttribute("talukaLst", talukaLst);*/
		model.addAttribute("lstSancOrder", orderMasterService.getsancOrderLst(ddoCode));
		model.addAttribute("lstInstitute", orderMasterService.getInstitutionLst(ddoCode));
		///model.addAttribute("lstDDOOff", orderMasterService.getddoOff(locationcodeArray));
	///	model.addAttribute("fetchGRDataList", mstGrOrderService.fetchGRDataList());
		
		addMenuAndSubMenu(model,messages);
		return "/views/OrderMasterView";
		
		
	}
	/*@RequestMapping(value = "/addNewEntry", method = { RequestMethod.GET, RequestMethod.POST })
	public String addNewEntry(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel,
			Model model, Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		String ddoCode = messages.getUserName();
		String districtId=orderMasterService.getDistrictId(ddoCode);
		List<CmnTalukaMst>talukaLst = orderMasterService.gettalukalst(districtId);
		model.addAttribute("talukaLst", talukaLst);
		// model.addAttribute("brokenPeriodModel", brokenPeriodModel);
		model.addAttribute("language", locale.getLanguage());
		return "/views/OrderMasterAddNewEntry";
			
		}*/
	@PostMapping("/AddOrderData")
	public String AddOrderData(@ModelAttribute("mstGrOrderModel") MstGrOrderModel mstGrOrderModel,
			RedirectAttributes redirectAttributes, Model model, Locale locale,HttpSession session,@RequestParam("documentPath") MultipartFile[] files) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES"); 
		model.addAttribute("mstGrOrderModel", mstGrOrderModel);
		Long afterSaveId = orderMasterService.saveMstGrOrder(mstGrOrderModel,files,messages);
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		return "redirect:/ddo/getOrderData";
	}
	
	}

