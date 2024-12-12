package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpRltDdoMap;

public interface ZpDDOOfficeRepo {

	List<ZpRltDdoMap> getAllDDOOfficeDtlsDataByPostID(String username);

	List<Object[]> getDDOinfo(String zpDdoCode);

	String getOfficeName(String zpDdoCode);

	int approveChangeStatement(String zpDdoCode, int flag);

	OrgUserMst findddo(String zpDdoCode);

	void updateApproveStatus(OrgUserMst objOrgUserMst);

	ZpRltDdoMap findddoinZPRlt(String zpDdoCode);

	void updateApproveStatusinZpRlt(ZpRltDdoMap zpRltDdoMap);

	List<ZpRltDdoMap> lstApprovedOffices(String ddoCode);

	List<ZpRltDdoMap> lstRejectedOffices(String userName);
	
}
