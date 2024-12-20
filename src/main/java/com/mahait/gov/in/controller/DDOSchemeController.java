package com.mahait.gov.in.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.model.OrgDdoMst;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DDOSchemeService;
import com.mahait.gov.in.service.OrderMasterService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class DDOSchemeController  extends BaseController{
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	DDOSchemeService ddoSchemeService;
	
	@Autowired
	OrderMasterService orderMasterService;
	
	
	List<NewRegDDOModel> emplist = new ArrayList<>();
	
	@GetMapping("/loadDdoSchemesAndBillGroups")
	public String loadDdoSchemesAndBillGroups(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel,
			 Model model, Locale locale,
			HttpSession session) {
		
		
		String message=(String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		List<OrgDdoMst> ddoListForScheme = ddoSchemeService.getDDOCodeByLoggedInlocId(1L);
		OrgDdoMst ddoMst  = null;
	   	
		if(ddoListForScheme!=null && ddoListForScheme.size()>0) {
		 ddoMst = ddoListForScheme.get(0);
   		}
		String ddoCode = null;
   		if(ddoMst!=null)
   		{
   			ddoCode = ddoMst.getDdoCode();
   		}
   		String type=null;
   		if(ddoCode!=null) {
   			type =ddoCode.substring(0,2);
   			Long TypeOfSchool=Long.valueOf(type);
   			String typeOfOffice=null;
   			if(TypeOfSchool !=2)
   	   		{
   	   			typeOfOffice="otherThanZp";
   	   		}
   	   		else 
   	   		 {
   	   			typeOfOffice="ZP";
   	   		}
   		}
   			
		List<NewRegDDOModel> DcpsDdoSchemeList = null;
		DcpsDdoSchemeList = ddoSchemeService.getAllSchemesForDDO(messages.getUserName());
		String districtID=ddoSchemeService.districtName(messages.getUserName());
		List<CmnTalukaMst> talukaList=ddoSchemeService.allTaluka(districtID);
   		String talukaId=talukaList.get(0).getTalukaId().toString();
    	   String ddoSelected=null;
    	  /// List ddoList=lObjDcpsCommonDAO.getSubDDOs(SessionHelper.getPostId(inputMap),talukaId,ddoSelected);
    	   
    		Integer totalRecords = DcpsDdoSchemeList.size();
    		/*List role=ddoSchemeService.getpostRole(messages.getCreatedByPost());
			Iterator IT = role.iterator();
			Integer o= null;
			String isLvl2= "no";
			while(IT.hasNext()){
				o= (Integer)IT.next();
				if(o.toString().equals("700017") || o.toString().equals("700002"))
					isLvl2="yes";
				
			}
			*/
			model.addAttribute("talukaList", talukaList);
	   		model.addAttribute("talukaId", talukaId);
	   		model.addAttribute("ddoSelected", ddoSelected);
	   		//model.addAttribute("displayAddNewEntry", displayAddNewEntry);
			
		//	model.addAttribute("DDOlist", ddoList);
			model.addAttribute("schemelist", DcpsDdoSchemeList);
			model.addAttribute("totalRecords", totalRecords);
			model.addAttribute("lstInstitute", orderMasterService.getInstitutionLst(messages.getUserName()));
			//model.addAttribute("isLvl2", isLvl2);
			
		model.addAttribute("newRegDDOModel", newRegDDOModel);
		model.addAttribute("message", message);
		addMenuAndSubMenu(model,messages);	
		
		return "/views/DDOScheme";
		
		
	}
	@PostMapping("/addSchemesAndBillGroupsToDdo")
	public String addSchemesAndBillGroupsToDdo(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Long saveId=ddoSchemeService.addSchemesAndBillGroupsToDdo(newRegDDOModel,messages);
		
		if(saveId != 0) {
			redirectAttributes.addFlashAttribute("message","Scheme Added successfully!!");
		}
		return "redirect:/ddo/loadDdoSchemesAndBillGroups"; /// redirects to controller URL
	}

	@GetMapping(value = "/CheckSubSchemeExist/{schemeCode}/{subschemeCode}")
	public ResponseEntity<String> PaybillValidation(@PathVariable String schemeCode,@PathVariable String subschemeCode, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> orderNo = ddoSchemeService.CheckSubSchemeExist(schemeCode,subschemeCode);
		Integer existingData = orderNo.size();
		String resJson = existingData.toString();
		return ResponseEntity.ok(resJson);
	}
	@RequestMapping("/displaySchemeNameForCode/{schemeCode}")
	public @ResponseBody List<Object[]> getBankBranch(@PathVariable String schemeCode, Model model, Locale locale) {

		List<Object[]> mstScheme=ddoSchemeService.displaySchemeNameForCode(schemeCode);
		return mstScheme;
	}

}