package com.mahait.gov.in.repository;

import java.util.Date;
import java.util.List;

import com.mahait.gov.in.entity.BrokenPeriodAllowDeducEntity;
import com.mahait.gov.in.entity.BrokenPeriodEntity;
import com.mahait.gov.in.model.MstEmployeeModel;

public interface BrokenPeriodRepo {
	public MstEmployeeModel getEmployeeinfo(String sevaarthid,String ddocode);
	public String getDesignationName(String strDesignationCode);
	public List<Object[]> fetchAllowDeducName(String sevaarthid, int i) ;
	public List<Object[]> fetchAllowDeducNameForCalcEmpSalary(String sevaarthid);
	public List saveBrokenPeriodPay(BrokenPeriodEntity[] lArrMstBrokenPeriodPay, List<BrokenPeriodAllowDeducEntity> lListRltBrokenPeriodAllow,
			List<BrokenPeriodAllowDeducEntity> lListRltBrokenPeriodDeduc);
	public Boolean checkBrokenPeriodPayExistsOrNot(Long lLongEmpId, Long lLongYearId, Long lLongMonthId, String ddoCode);
	public List<BrokenPeriodEntity> getAddedBrokenPeriodPaysForEmp(Long lLongEmpId, Long lLongYearId, Long lLongMonthId, String ddoCode);
	public void deleteAllBrokenPeriodPaysForPk(Long lLongBrokenPrdId);
	public void deleteAllBrokenPeriodAllowancesForBrknPrdId(Long lLongBrokenPrdId);
	public void deleteAllBrokenPeriodDeductionsForBrknPrdId(Long lLongBrokenPrdId);
	public List getAddedAllowancesForEmp(Long lLongRltBrokenPeriodId);
	public List getAddedDeductionsForEmp(Long lLongRltBrokenPeriodId);
	public List getAllowancesListForGivenEmp(String sevaarthid);
	public List getDeductionsListForGivenEmp(String sevaarthid);
	public Long getBrokenPeriodNextID();
	public String CheckBrkPrdMonthExitOrNot(String monthyear,String sevaarthId);
	public String isEmpMappedWithBillGroup(String sevaarthId);
	public int getSevaarthIdMappedWithPayBillProcessed(String sevaarthid,int month,int year,String ddoName);
	public int getSevaarthIdMappedWithPayBillInprogress(String sevaarthid,int month,int year,String ddoName);
	public List<Object[]> fetchAllowDeducNameDaArray(String sevaarthid);
	public List getAllowancesListForGivenEmpDAArray(String sevaarthid);
	public List getDeductionsListForGivenEmpDAArray(String sevaarthid);
	public Object CheckBrkPrdMonthExitOrNot(String monthyear, String sevaarthid, Date fromDate, Date toDate);
	public List saveBrokenPeriodDAArrayPay(BrokenPeriodEntity[] lArrMstBrokenPeriodPay,
			List<BrokenPeriodAllowDeducEntity> lListRltBrokenPeriodAllow,
			List<BrokenPeriodAllowDeducEntity> lListRltBrokenPeriodDeduc);
}
