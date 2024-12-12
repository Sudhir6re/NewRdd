package com.mahait.gov.in.nps.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

public interface VerifyDcpsLegacyRepo {

	Long findLocId(String locationId);

	List<Object[]> findLegacyData(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages, Long locId);

	int verifySavedData(String sevaarthId, String period, OrgUserMst messages);

	int rejectSavedData(String sevaarthId, String period, String remarks,OrgUserMst messages);


}
