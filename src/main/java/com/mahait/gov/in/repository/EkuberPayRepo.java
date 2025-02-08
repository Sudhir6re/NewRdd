package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.HrPayEkuberRecordMst;
import com.mahait.gov.in.entity.HrPayEkuberRecordMstDtls;

public interface EkuberPayRepo  {

	Long getNxtSequenceNo(String treasuryCode, int noOfPayees);

	List getPaybillIdFromAuthNo(String authNo);

	List getCommonDetails(String authNo);

	List getNonGovDeductionforDDO(String authNo);

	Long getNPSAmount(String authNo);

	String getFinancialYear(String authNo);

	int checkBenificiaryCount(String authNo);

	void modifyEkuberFile(String authNo);

	List getCMPAuthCount(String authNo);

	void save(HrPayEkuberRecordMst ekuberRecord);

	void save(HrPayEkuberRecordMstDtls ekuberRecordDtls);

	Long getPaybillFlag(String authNo);

	List getOtherDedForDDO(String authNo, String tableName, Long paybillFlag, int paybillMonth, int paybillYear);

	List getDdoDtls(String level1Ddocode, String authNo);

	List getCAFODtls(String level1Ddocode);

	void updateCMPFlag(String authNo, String string, String format);



	List<Object[]> getEkuberDataForExcel(String authNo);

	List<Object[]> getToDdoCodeAndNoOfPayees(String authNo);

}
