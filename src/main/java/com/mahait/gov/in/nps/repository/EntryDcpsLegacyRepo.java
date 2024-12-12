package com.mahait.gov.in.nps.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.entity.DcpsLegacyDataEntity;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

public interface EntryDcpsLegacyRepo {

	List<Object[]> findDcpsEmployeeDetails(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages);

	Long saveDcpsLegacyData(DcpsLegacyDataEntity dcpsLegacyDataEntity);

	Integer checkDataExistsForPeriod(OrgUserMst messages, String period, String sevaarthId);


}
