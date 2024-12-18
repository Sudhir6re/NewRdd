package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.OrgUserMst;

public interface DCPSOnlineContributionEntryService {

	List<MstDcpsBillGroup> findAllBillGroup(OrgUserMst messages);

	List<CmnLocationMst> findTreasuryByDdoCode(OrgUserMst messages);

}
