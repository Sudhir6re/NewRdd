package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.mahait.gov.in.entity.AllowanceDeductionRuleMstEntity;
import com.mahait.gov.in.entity.DcpsContributionEntity;
import com.mahait.gov.in.entity.LoanEmployeeDtlsEntity;
import com.mahait.gov.in.entity.MstDcpsContriVoucherDtlEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.PaybillGenerationTrnDetails;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.entity.PaybillStatusEntity;


public interface PaybillGenerationTrnRepo {

	public Long savePaybillHeadMpg(PaybillGenerationTrnEntity objEntity);
	public Long saveHrPayPaybill(PaybillGenerationTrnDetails paybillGenerationTrnDetails);
	
	public Long getPaybillGenerationTrnId();
	
	public PaybillGenerationTrnEntity findForwardChangeStatementById(Long paybillGenerationTrnId);
	
	public void updateForwardChangeStatementStatus(PaybillGenerationTrnEntity paybillGenerationTrnEntity);
	
	public PaybillGenerationTrnEntity forwardPayBillToLevel2(Long paybillGenerationTrnId);
    ///public int getpaybilldata(String billGroup, String paybillMonth, String paybillYear);
	public int isPaybillExists(BigInteger billGroup, int paybillMonth, int paybillYear);
	public List<Object[]> findpaybill(BigInteger billGroup, int paybillMonth, int paybillYear,String ddo);



	public List<Object[]> getChangeStatementReport(String ddoCode);
	
	public List<Object[]> getChangeStatementReportFromPreviousMonth(String ddoCode);

	public PaybillGenerationTrnEntity consolidatedPaybill(Long paybillGenerationTrnId);
	public List<Object[]>  findDDOinfo(String userName);
	public List<Object[]> findregIdinfo(Long regid);
	public List<Object[]> findmonthinfo(BigInteger currmonth);
	public List<Object[]> findofcIdinfo(Long ofcid);
	public List<Object[]> getAbstractReport(String paybillGenerationTrnId);
	public List<Object[]> findyearinfo(BigInteger yearcurr);
	public int isBrokenPeriodEmpty(String sevaarthid,String monthid,String yearid );
	public List<Object[]> getBrokenPeriodData(String sevaarthid,String monthid,String yearid,String Username ) ;
	public  List<Object[]> getViewDetialsReport(Integer monthName);
	public int getDaPercentageByMonthYear(String startDate, int commoncodePaycommission7pc, int componentCode);
	public String getHRAPercentageByMonthYear(String startDate, int commoncodePaycommission7pc, String cityClass);
	public Integer isPaybillExistsForCurrentMonth(Long schemeBillgroupId, int paybillMonth, int paybillYear);
	public int getCheckIsBillInProcess(int monthName, int yearName, BigInteger schemeBillGroupId, int paybillType);
	public String getgradePay7PC(Long gradelevel);
	public String isEmpRetired(int monthName, int yearName, BigInteger schemeBillGroupId, int paybillType, String userName);
	
	public String isEmpRetiredBySevaarthId(String sevaarthId,Date suppAnnDate);
	public PaybillGenerationTrnEntity findPaybillById(Long paybillGenerationTrnId);
	public void updateVoucherEntry(PaybillGenerationTrnEntity objPaybillGeberationTrnEntity);
	public Integer getannualincment(String sevaarthId, String startDate);
	public Integer getamtbeforeannualincment(String sevaarthId, String startDate);
	public int getDaCentralPercentageByMonthYear(String startDate, int commoncodePaycommission7pc);
	public List<MstEmployeeEntity> checkedBgisAndGisCatNull(String schemeBillGroupId, String userName);
	public int savePaybillStatus(PaybillStatusEntity paybillStatusEntity);
	List<AllowanceDeductionRuleMstEntity> fetchComponentDtlsByCompoId(int CompoId);
	public List<AllowanceDeductionRuleMstEntity> getClaAmaountDtls(Long sevenPcLevel, Double basic, String citygroup,
			Long payCommissionCode, int allowDeducCode);
	public List<AllowanceDeductionRuleMstEntity> fetchhraDtls(int allowDeducCode, String startDate, String citygroup,
			Double basic, int i);
	public Double findGisComponentValue(String gisgroup, Date doj, String startDate, int allowDeducCode);
	public Double fetchHraDtls(Double basic, String startDate, String cityClass, int allowDeducCode);
	public Double calculatePt(Double basic, int paybillMonth);
	public Double calculateFamilyAllow(Long payCommission, Long sevenPcLevel, int allowDeducCode);
	public Double fetchtaDtls(Double basic, Long payCommission, int allowDeducCode, Long gradelevel, String cityClass,
			String physicallyHandicapped);
	Double fetchAccidentialPilocyDtls(String startDate, String citygroup, int allowDeducCode);
	public String getEmpCadre(String sevaarthId, Long empClass);
	public Long saveBulkPaybillDetail(List<PaybillGenerationTrnDetails> lstPaybillGenerationTrnDetails);
	public double findSumContribution(String sevaarthId, String paymentType, Integer monthId, Integer yearId, String string);
	public Optional<MstDcpsContriVoucherDtlEntity> findMstDcpsContriVoucherDtlEntity(
			PaybillGenerationTrnEntity paybillGenerationTrnEntity);
	public void updateMstDcpsContriVoucherDtlEntity(MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity);
	public void updateMstDcpsContriVoucherDtlEntity(DcpsContributionEntity dcpsContributionEntity);
	public LoanEmployeeDtlsEntity fetchLoanDtls(String sevaarthId, int allowDeducCode);
	
/*	public void saveFaDtlsTrn(FaLoanDtlsTrnEntity faLoanDtlsTrnEntity);
	public void saveCaDtlsTrn(CaLoanDtlsTrnEntity caLoanDtlsTrnEntity);
	public void saveVaDtlsTrn(VaLoanDtlsTrnEntity vaLoanDtlsTrnEntity);
	public void saveHbaDtlsTrn(HbaLoanDtlsTrnEntity hbaLoanDtlsTrnEntity);
	
	public Double findAllrecoveryAmt(Date d, String ppoNo);
	public Double findDaPercentage(String startDate, String endDate, int payCommisionCode);
	public Double getTotalArrearSum(int month2, int year2, String ppoNo);
	public List<PensionEmpRecovDtlsEntity> findAllRecoveryDetails(Date d, Date toDate, String ppoNo);
	public List<PensionEmpRecovDtlsEntity> findRecoveryDetails(String ppoNo, String futureString);
	
	public Double findGratuityRecoveryAmt(Date date, String ppoNo);
	public List<Object[]> getSuspensionBrokenPeriodData(String string, String valueOf, String valueOf2, String trim);
	public int isSuspensionBrokenPeriodEmpty(String sevaarthId, String valueOf, String valueOf2);*/
	
	
}
