package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.repository.DDOCityCategoryRepo;

@Service
@Transactional
public class DDOCityCategoryServiceImpl implements DDOCityCategoryService {
	
    @Autowired
	DDOCityCategoryRepo dDOCityCategoryRepo;
	public String getCityCategoryMappedWithDDo(String ddoCode) {
		return dDOCityCategoryRepo.getCityCategoryMappedWithDDo(ddoCode);
	}
	public List<Object[]> getEmployeeMappedWithAllowanceDeduction(BigInteger schemeBillGroupId,String ddoCode) {
		return dDOCityCategoryRepo.getEmployeeMappedWithAllowanceDeduction(schemeBillGroupId,ddoCode);
	}
	public List<Object[]> getSevaarthIdMappedWithBill(int schemeBillGroupId,int month,int year,String ddoName) {
		return dDOCityCategoryRepo.getSevaarthIdMappedWithBill(schemeBillGroupId,month,year,ddoName);
	}
	public List<Object[]> getNumberOfPaybillProcessed(int schemeBillGroupId){
		return dDOCityCategoryRepo.getNumberOfPaybillProcessed(schemeBillGroupId);	
	}
	@Override
	public List<Object[]> getSevaarthIdMappedWithBillUpdate(int monthName, int yearName,Long schemeBillGroupId) {
		return dDOCityCategoryRepo.getSevaarthIdMappedWithBillUpdate(monthName,yearName,schemeBillGroupId);
	}
	
	
	
	
	
}
