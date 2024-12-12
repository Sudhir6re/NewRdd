package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.OrgUserMst;

public interface DCPSOnlineContributionEntryRepo {

	List<MstDcpsBillGroup> findAllBillGroup(OrgUserMst messages);
	
	List<CmnLocationMst> findTreasuryByDdoCode(OrgUserMst messages);

}
