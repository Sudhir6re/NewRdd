package com.mahait.gov.in.nps.service;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

public interface VerifyDcpsLegacyService {

	List<DcpsLegacyModel> findLegacyData(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages, String locationId);

	Integer verifySavedData(Map<String, String> inputParam, OrgUserMst messages);

	Integer rejectSavedData(Map<String, String> inputParam, OrgUserMst messages);

}
