package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstBankService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/mdc")
public class MstBankController extends BaseController {
	@Autowired
	MstBankService mstBankService;
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@GetMapping("/mstBank")
	public String adminOfficeMaster(@ModelAttribute("MstBankEntity") MstBankEntity mstBankEntity,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		mstBankEntity.setBankCode((Integer.parseInt(commonHomeMethodsService.findCodeSeq("bank_code","bank_mst"))));
		
		model.addAttribute("mstBankEntity", mstBankEntity);
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		
		if(message != null && message.equals("SUCCESS")) {
			if(locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());
		model.addAttribute("language", locale.getLanguage());
		return "/views/mst-bank";
    }
	
	@GetMapping(value="/mstBank/{bankCode}")
	public @ResponseBody List<Object[]> multiply(@PathVariable int bankCode,Model model,Locale locale) {
		List<Object[]> mstBankBranch =  mstBankService.findAllBankBranchList(bankCode);
		List<Object[]>  mstEmployeeEntity=  mstBankBranch;
		return mstEmployeeEntity;
    }
	
	
	@PostMapping("/saveBank")
	public String saveAllowDeductionMst(@ModelAttribute("MstBankEntity") @Valid MstBankEntity mstBankEntity,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,HttpSession session) {
		int afterSaveId = mstBankService.saveBank(mstBankEntity);
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		
		String message = (String)model.asMap().get("message");
		mstBankEntity.setBankCode((Integer.parseInt(commonHomeMethodsService.findCodeSeq("bank_code","bank_mst"))));
		mstBankEntity.setBankShortName("");
		mstBankEntity.setBankName("");
		model.addAttribute("mstBankEntity", mstBankEntity);
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		
		
		if(message != null && message.equals("SUCCESS")) {
			if(locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());
		model.addAttribute("language", locale.getLanguage());
		return "/views/mst-bank";
	}
	
	@RequestMapping(value="/deleteBank/{bankId}")	// , method = RequestMethod.POST
	public String deleteCadre(@PathVariable int bankId,Model model,Locale locale) {
	 mstBankService.deleteBankById(bankId);
		return "redirect:/mstBank";
	}
	

	@RequestMapping(value="/editBank/{bankId}")	// , method = RequestMethod.POST
    public String editBank(@PathVariable int bankId,RedirectAttributes redirectAttributes, Model model,Locale locale,HttpSession session,@ModelAttribute("MstBankEntity") MstBankEntity mstBankEntity) {
		String message = (String)model.asMap().get("message");
		mstBankEntity=mstBankService.findBankById(bankId);
		
		model.addAttribute("mstBankEntity", mstBankEntity);
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		
		if(message != null && message.equals("SUCCESS")) {
			if(locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());
		model.addAttribute("language", locale.getLanguage());
		return "/views/edit-bank";
		
	}

	@PostMapping("/editbankdetails")
	public String editbankdetails(@ModelAttribute("mstBankEntity") @Valid MstBankEntity mstBankEntity,
									BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model,Locale locale,HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		if(bindingResult.hasErrors()) {
			model.addAttribute("language", locale.getLanguage());
			/*return "/views/edit-mst-department";*/ 
			return "redirect:/master/editBank/{bankId}";
		} 
		String message = mstBankService.editbankdetails(mstBankEntity);
		if(message.equals("UPDATED")) {
				redirectAttributes.addFlashAttribute("message","Record Updated Successfully");
			
		}
		
		
		return "redirect:/mdc/mstBank"; /*redirects to controller URL*/
	}
	@RequestMapping("/validateBankName/{bankname}") //"validateBankName/"+bankname
	public @ResponseBody List<Long> validateBankName(@PathVariable String bankname, Model model, Locale locale) {

		List<Long> status = mstBankService.validateBankName(bankname);
		return status;
	}
}