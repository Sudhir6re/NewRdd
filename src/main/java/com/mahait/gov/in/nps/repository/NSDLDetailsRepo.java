package com.mahait.gov.in.nps.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.entity.NSDLBHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLDHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLSDDtlsEntity;

public interface NSDLDetailsRepo {

	List<Object[]> getNsdlEmpData(int month, int year, String ddoCode);
	List<Object[]> getNSDLEmpDtlsForGenerate(int month, int year, String ddoCode);

	List<Object[]> lstNsdlEmpDtls(String filename);

	List<Object[]> getEmpDataByFileId(String userName, String fileId);
	
	
	String getbatchIdCount(String batchIdPrefix);
	List<Object[]> getEmployeeListNsdl(Integer yrCode, Integer month, Integer treasuryyno, String ddoName);
	Long getDDoRegCount(Integer yrCode, Integer month, Integer treasuryyno);
	Integer saveDHDetail(NSDLDHDtlsEntity nSDLDHDtlsEntity);
	Integer saveSDDetail(NSDLSDDtlsEntity nSDLSDDtlsEntity);
	Integer saveBHDetail(NSDLBHDtlsEntity nSDLBHDtlsEntity);
	List<Object[]> lstNsdlDDOWiseDtls(String ddoCode);
	
	String getBatchData(String fileNumber);
	public List getDHData(String fileNumber);
	public List getSDDtls(String fileNumber, String ddoRegNo);

	public void updateFileStatus(int fileStatus, String fileno, String errorData);
	void updateBatchId(String batchId, List dcpsId);
	List<Object[]> getNsdlEmpDataLevelwise(int month, int year, String userName);
	List<Object[]> getEmployeeListNsdl1(Integer year, Integer month, String ddoCode);
	List<Object[]> searchDdoWiseContribution(int year, int month, OrgUserMst messages);
	
}
