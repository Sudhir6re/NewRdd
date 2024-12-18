package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstBankBranchEntity;

import jakarta.validation.Valid;

public interface BranchMasterService {

	List<MstBankBranchEntity> listOfBranch();

	int saveBankBranch(@Valid MstBankBranchEntity mstBankBranchEntity);

	MstBankBranchEntity findBankBranchById(Long bankBranchId);

	String updateBankBranch(@Valid MstBankBranchEntity mstBankBranchEntity);

	List<Long> validateIFSCCode(Integer bankcode, String ifscCode);

}
