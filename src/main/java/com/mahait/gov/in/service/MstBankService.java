package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.entity.MstBankPay;


public interface MstBankService {

	 public List<MstBankEntity> lstAllBank();
	 
	 public List<Object[]> findAllBankBranchList(int bankCode);
	
	 public int saveBank(MstBankEntity mstBankEntity);
	 
	 public void deleteBankById(int bankId);
	 
	 public MstBankEntity findBankById(int bankId);

	public String editbankdetails( MstBankEntity mstBankEntity);

	public List<Long> validateBankName(String bankname);
	
	 
}
