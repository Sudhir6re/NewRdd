package com.mahait.gov.in.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstDistrictService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/mdc")
public class MstDistrictController extends BaseController {
//	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	MstDistrictService mstDistrictService;
	
	/*@Autowired
	MstCountryService mstCountryService;*/
	
	@GetMapping("/mstDistrict")
	public String adminOfficeMaster(@ModelAttribute("mstDistrictEntity") CmnDistrictMst mstDistrictEntity,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");

		//mstDistrictEntity.setDistrictCode(Integer.valueOf(commonHomeMethodsService.findCodeSeq("district_code","district_mst"))); 
		model.addAttribute("mstDistrictEntity", mstDistrictEntity);

		 OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		 addMenuAndSubMenu(model,messages);	
		if(message != null && message.equals("SUCCESS")) {
			if(locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("lstDistrictTable",mstDistrictService.findAllDistrict());
	
		/*model.addAttribute("lstCountryTable", mstCountryService.findAllCountry().stream()
				.filter(p -> p.getIsActive() == '1').collect(Collectors.toList()));*/
		
		
		model.addAttribute("language", locale.getLanguage());
		return "/views/mst-district";
    }
	
	@PostMapping("/saveDistrictMst")
	public String saveDistrict(@ModelAttribute("mstDistrictEntity") @Valid CmnDistrictMst mstDistrictEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,HttpSession session) {
		 OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()) {
			return "/views/mst-district";
		} 
		mstDistrictEntity.setCreatedBy(messages.getUserId());
		CmnDistrictMst MstDistrictEntity12 = mstDistrictService.saveDistrict(mstDistrictEntity);
		
		if(MstDistrictEntity12 != null) {
			redirectAttributes.addFlashAttribute("message","SUCCESS");
		}
		return "redirect:/mdc/mstDistrict"; 
	}
	
/*	@GetMapping(value="/fetchStateByCountry/{countryId}")
	public   ResponseEntity<List<MstStateModel>>  fetchStateByCountry(@PathVariable int countryId,Model model,Locale locale) {
		return new ResponseEntity<List<MstStateModel>>( mstDistrictService.fetchStateByCountry(countryId), HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping("/validateDistrictName/{districtname}")
	public @ResponseBody List<Long> validateDistrictName(@PathVariable String districtname, Model model, Locale locale) {

		List<Long> status = mstDistrictService.validateDistrictname(districtname);
		return status;
	}*/

}
