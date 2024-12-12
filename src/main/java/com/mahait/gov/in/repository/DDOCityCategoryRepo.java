package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.List;

public interface DDOCityCategoryRepo {
	public String getCityCategoryMappedWithDDo(String ddoCode);
	public List<Object[]>getEmployeeMappedWithAllowanceDeduction(BigInteger schemeBillGroupId,String ddoCode);
	public List<Object[]>getSevaarthIdMappedWithBill(int schemeBillGroupId,int month,int year,String ddoName);
	public List<Object[]> getNumberOfPaybillProcessed(int schemeBillGroupId);
	public List<Object[]> getSevaarthIdMappedWithBillUpdate(int monthName, int yearName,Long schemeBillGroupId);
}
