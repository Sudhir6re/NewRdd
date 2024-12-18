package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.model.RegularReportModel;

public interface RegularReportService {

	List<OrgDdoMst> getDDOName(String userName);

	List<RegularReportModel> findDCPSRegularEmpLst(Integer yearId, Integer monthId, Long billgrpid, String string, Long allowdeducId);

	List<Object[]> findbillgrp(Long billno);

	List<Object[]> findpaybill(Long billNumber, int monthName, int yearName, String ddo);

	String getbillGroup(int billnumber);

	List<Object[]> checktheEntryForForm2Regular(int billNumber, int monthName, int yearName, String userName);

	List<MstDcpsBillGroup> lstBillDesc(String ddoCode);

	List<Object[]> findTrsyDtls(String ddoCode);

	
	

}
