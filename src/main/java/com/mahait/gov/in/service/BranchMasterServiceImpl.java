package com.mahait.gov.in.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.MstBankBranchEntity;
import com.mahait.gov.in.repository.BranchMasterRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class BranchMasterServiceImpl implements BranchMasterService {
	
	@Autowired
	BranchMasterRepo branchMasterRepo;

	@Override
	public List<MstBankBranchEntity> listOfBranch() {
		// TODO Auto-generated method stub
		return branchMasterRepo.listOfBranch();
	}

	@Override
	public int saveBankBranch(@Valid MstBankBranchEntity mstBankBranchEntity) {
		mstBankBranchEntity.setIsActive('1');
		mstBankBranchEntity.setCreatedUserId(1l);
		mstBankBranchEntity.setBankCode(mstBankBranchEntity.getBankid());
		mstBankBranchEntity.setCreatedDate(new Date());
		int isSaved =  branchMasterRepo.saveBankBranch(mstBankBranchEntity);
		return isSaved;
	}

	@Override
	public MstBankBranchEntity findBankBranchById(Long bankBranchId) {
		// TODO Auto-generated method stub
		return branchMasterRepo.findBankBranchById(bankBranchId);
	}

	@Override
	public String updateBankBranch(@Valid MstBankBranchEntity mstBankBranchEntity) {
		MstBankBranchEntity brachobject =branchMasterRepo.findBankBranchById(mstBankBranchEntity.getBankBranchId());
		if(brachobject != null) {
			brachobject.setBankBranchCode(mstBankBranchEntity.getBankBranchCode());
			brachobject.setBankBranchShortName(mstBankBranchEntity.getBankBranchShortName());
			brachobject.setBankBranchName(mstBankBranchEntity.getBankBranchName());
			brachobject.setBankCode(mstBankBranchEntity.getBankid());
			brachobject.setIfscCode(mstBankBranchEntity.getIfscCode());
			brachobject.setMicrCode(mstBankBranchEntity.getMicrCode());
			brachobject.setIsActive(mstBankBranchEntity.getIsActive());	// UPDATED
		int id=0;
		int i=0;
		Serializable  saveChangeBasicdtls= branchMasterRepo.updateBankBranch(brachobject);
		id = (int) saveChangeBasicdtls;
		i++;
		}
		return "UPDATED";
	}

	@Override
	public List<Long> validateIFSCCode(Integer bankcode, String ifscCode) {
		// TODO Auto-generated method stub
		return branchMasterRepo.validateIFSCCode(bankcode,ifscCode);
	}

}
