package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.util.ArrayList;
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
import com.mahait.gov.in.entity.MstBankBranchEntity;
import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.BranchMasterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/mdc")
@Controller
public class BranchMasterController extends BaseController {
	
	@Autowired
	BranchMasterService branchMasterService;
	
	@GetMapping("/branchMaster")
	public String branchMaster(@ModelAttribute("mstBankBranchEntity") MstBankBranchEntity mstBankBranchEntity,
			Model model,Locale locale,HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);	
		List<MstBankBranchEntity> branchName = new ArrayList<>();
		branchName = branchMasterService.listOfBranch();
		
		model.addAttribute("branchName",branchName);
		return "/views/branch-master";
	}

	@GetMapping("/addBranch")
	public String addBranch(@ModelAttribute("mstBankBranchEntity") MstBankBranchEntity mstBankBranchEntity,
			Model model,Locale locale,HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);	
		List<MstBankEntity> bankName = new ArrayList<>();
		bankName = commonHomeMethodsService.findBankName();
		model.addAttribute("bankName", bankName);
		return "/views/add-branch";
	}
	
	@PostMapping("/saveBankBranch")
	public String saveBankBranch(@ModelAttribute("mstBankBranchEntity") @Valid MstBankBranchEntity mstBankBranchEntity,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,HttpSession session) {
		 BigInteger bigIntegerStr=new BigInteger(commonHomeMethodsService.findCodeSeq("BANK_BRANCH_CODE","bank_branch_mst"));
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		mstBankBranchEntity.setBankBranchCode(bigIntegerStr.longValue());
		//mstBankBranchEntity.setIsActive('1');
		mstBankBranchEntity.setCreatedUserId(messages.getUserId());
		int isSaved= branchMasterService.saveBankBranch(mstBankBranchEntity);
		if (isSaved >0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		return "redirect:/mdc/branchMaster";
	}
	
	@RequestMapping(value="/editBankBranch/{bankBranchId}")	// , method = RequestMethod.POST
    public String editBankBranch(@PathVariable Long bankBranchId,RedirectAttributes redirectAttributes, Model model,Locale locale,HttpSession session,@ModelAttribute("MstBankBranchEntity") MstBankBranchEntity mstBankBranchEntity) {
		String message = (String)model.asMap().get("message");
		
		 mstBankBranchEntity=branchMasterService.findBankBranchById(bankBranchId);
		
	
		model.addAttribute("mstBankBranchEntity", mstBankBranchEntity);
		
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		 
		if(message != null && message.equals("SUCCESS")) {
			if(locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		
		List<MstBankEntity> bankName = new ArrayList<>();
		bankName = commonHomeMethodsService.findBankName();
		model.addAttribute("bankName", bankName);
		model.addAttribute("language", locale.getLanguage());
		return "/views/edit-bank-branch";
		
	}
	
	@PostMapping("/updateBankBranch")
	public String updateBankBranch(@ModelAttribute("mstBankBranchEntity") @Valid MstBankBranchEntity mstBankBranchEntity,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,HttpSession session) {
		BigInteger bigIntegerStr=new BigInteger(commonHomeMethodsService.findCodeSeq("BANK_BRANCH_CODE","bank_branch_mst"));
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		mstBankBranchEntity.setBankBranchCode(bigIntegerStr.longValue());
		//mstBankBranchEntity.setIsActive('1');
		mstBankBranchEntity.setCreatedUserId(messages.getUserId());
       String message = branchMasterService.updateBankBranch(mstBankBranchEntity);
       System.out.println("saveId  " +message);
       if(message.equals("UPDATED")) {
			redirectAttributes.addFlashAttribute("message","Record Updated Successfully");	
	}
		return "redirect:/mdc/branchMaster";
	}
	
	
	@RequestMapping("/validateIFSCCode/{bankcode}/{ifscCode}") //validateBankBranchName/"+bankbranchname+"/"+bankcode
	public @ResponseBody List<Long> validateIFSCCode(@PathVariable Integer bankcode,@PathVariable String ifscCode, Model model, Locale locale) {
		
		List<Long> status = branchMasterService.validateIFSCCode(bankcode,ifscCode);
		return status;
	}
	

}
