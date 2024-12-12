package com.mahait.gov.in.nps.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

public interface LegacyValidationRepo {

	List<Object[]> findNsdlLegacyList(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages, Long locId);

	String getDtoRegNo(String fileNumber);

	String getBatchData(String fileNumber);

	List<Object[]> getDHData(String fileNumber);

	List<String> getSDDtls(String fileNumber, String ddoRegNo);

	String getDdoCode(String batchId, String fileNo);

	String getFileStatus(String ddoCode, String fileNo, String batchId);

	List<Object[]> getLegacyDataDetailsHis(String fileNo, String ddoCode, String batchId);

	void deleteFileDetails(String fileNo, String ddoCode, String batchId);

	String getDdoCode(String fileno);

	void updateFileStatus(int fileStatus, String fileno, String errorData);

}
