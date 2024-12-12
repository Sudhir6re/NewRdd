package com.mahait.gov.in.nps.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstSchemeModel;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

public interface EntryDcpsLegacyService {

	List<DcpsLegacyModel> findDcpsEmployeeDetails(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages);

	Long saveDcpsLegacyData(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages);

	Integer checkDataExistsForPeriod(OrgUserMst messages, String period, String sevaarthId);

}
