package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.MstCommonEntity;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.CommonMstService;

@RequestMapping("/developer")
@Controller
public class CommonMstController {
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	CommonMstService commonMstService;
	
	
	@GetMapping("/loadCommonConstList")
	public String loadCommonConstList(@ModelAttribute("mstCommonEntity") MstCommonEntity mstCommonEntity,Model model) {
		BigInteger commonId=new BigInteger(commonHomeMethodsService.findCodeSeq("common_id","common_mst"));
		mstCommonEntity.setCommonId(commonId.intValue());
		model.addAttribute("mstCommonEntity", mstCommonEntity);
		List<MstCommonEntity> lstMstCommonEntity=commonMstService.findAllCommonConst();
		model.addAttribute("lstMstCommonEntity", lstMstCommonEntity);
		return "/views/common-mst";
	}
	
	
	@GetMapping("/editCommonConst/{commonId}")
	public String editCommonConstList(@PathVariable Integer commonId,Model model) {
		MstCommonEntity mstCommonEntity=commonMstService.findCommonMstById(commonId);
		model.addAttribute("mstCommonEntity", mstCommonEntity);
		return "/views/edit-common-mst";
	}
	
	
	@PostMapping("/updateCommonMst")
	public String saveCommonConst(@ModelAttribute("mstCommonEntity") MstCommonEntity mstCommonEntity) {
		commonMstService.updateCommonMst(mstCommonEntity);
		return "redirect:/developer/loadCommonConstList";
	}
	
	@PostMapping("/saveCommonMst")
	public String saveCommonMst(@ModelAttribute("mstCommonEntity") MstCommonEntity mstCommonEntity) {
		BigInteger commonId=new BigInteger(commonHomeMethodsService.findCodeSeq("common_id","common_mst"));
		mstCommonEntity.setCommonId(commonId.intValue());
		commonMstService.saveCommonMst(mstCommonEntity);
		return "redirect:/developer/loadCommonConstList";
	}

}
