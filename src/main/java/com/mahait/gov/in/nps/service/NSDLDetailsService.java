package com.mahait.gov.in.nps.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.NSDLBHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLDHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLSDDtlsEntity;
import com.mahait.gov.in.nps.model.DdoWiseNpsContriModel;
import com.mahait.gov.in.nps.model.NSDLDetailsModel;

public interface NSDLDetailsService {

	List<Object[]> getNsdlEmpData(int month, int year , String ddoCode);
	List<Object[]> getNSDLEmpDtlsForGenerate(int month, int year, String ddoCode);

	List<NSDLDetailsModel> lstNsdlEmpDtls(String filename);


	List<MstEmployeeNPSEntity> getEmpDataByFileId(String userName, String fileId);
	String getbatchIdCount(String batchIdPrefix);
	List<Object[]> getEmployeeListNsdl(Integer yrCode, Integer month, Integer treasuryyno, String ddoName);
	Long getDDoRegCount(Integer yrCode, Integer month, Integer treasuryyno);
	Integer saveDHDetail(NSDLDHDtlsEntity nSDLDHDtlsEntity);
	Integer saveSDDetail(NSDLSDDtlsEntity nSDLSDDtlsEntity);
	Integer saveBHDetail(NSDLBHDtlsEntity nSDLBHDtlsEntity);
	
	List<NSDLDetailsModel> lstNsdlDDOWiseDtls(String ddoCode);
	String getBatchData(String fileno);
	List getDHData(String fileno);
	List getSDDtls(String fileno, String ddoRegNo);
	void updateFileStatus(int fileStatus, String fileno, String errorData);
	void updateBatchId(String batchId, List finalNpdId);
	List<Object[]> getNsdlEmpDataLevelwise(int month, int year, String userName);
	List<NSDLDetailsModel> getEmployeeListNsdl1(Integer year, Integer month, String ddoCode);
	List<DdoWiseNpsContriModel> searchDdoWiseContribution(int month, int year, OrgUserMst messages);
	List getEmployeeListNsdlsuperAnnNull(Integer year, Integer month, Integer treasuryyno, String ddoCode);
	
	


	

}
