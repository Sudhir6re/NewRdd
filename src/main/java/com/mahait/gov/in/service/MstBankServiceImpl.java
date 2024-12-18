package com.mahait.gov.in.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.repository.MstBankRepo;


@Service
@Transactional
public class MstBankServiceImpl implements MstBankService{
	

	@Autowired
	MstBankRepo mstBankRepo;
	
	@Override
	public List<MstBankEntity> lstAllBank() {
		return mstBankRepo.lstAllBank();
	}

	@Override
	public List<Object[]> findAllBankBranchList(int bankCode) {
		List<Object[]> mstBankBranchList = mstBankRepo.findAllBankBranchList(bankCode);
		return mstBankBranchList;
	}
	
	@Override
	public int saveBank(MstBankEntity mstBankEntity) {
		MstBankEntity MstBankEntity1=new MstBankEntity();
		MstBankEntity1.setIsActive('1');
		MstBankEntity1.setBankName(mstBankEntity.getBankName());
		MstBankEntity1.setBankCode(mstBankEntity.getBankCode());
		MstBankEntity1.setCreatedUserId(1);
		MstBankEntity1.setBankShortName(mstBankEntity.getBankShortName());
		MstBankEntity1.setCreatedDate(new Date());
		return mstBankRepo.saveBank(MstBankEntity1);	
	}
	public MstBankEntity findBankById(int bankId)
	{
		return mstBankRepo.findBankById(bankId);
	}
	public void deleteBankById(int bankId)
	{
		MstBankEntity MstBankEntity1=mstBankRepo.findBankById(bankId);
		mstBankRepo.deleteBankById(MstBankEntity1);
	}
	
	@Override
	public String editbankdetails(MstBankEntity mstBankEntity) {
		MstBankEntity objbank = mstBankRepo.findBankById(mstBankEntity.getBankId());
		if(objbank != null) {
//			objbank.setBankCode(mstBankEntity.getBankCode());
			objbank.setBankName(mstBankEntity.getBankName());
			objbank.setBankShortName(mstBankEntity.getBankShortName());
			objbank.setBankId(mstBankEntity.getBankId());
			
			objbank.setIsActive('1');	// UPDATED
			
			objbank.setUpdatedDate(new Date());
			mstBankRepo.updatebankStatus(objbank);
		}
		return "UPDATED";
	}

	@Override
	public List<Long> validateBankName(String bankname) {
		// TODO Auto-generated method stub
		return mstBankRepo.validateBankName(bankname);
	}
}
