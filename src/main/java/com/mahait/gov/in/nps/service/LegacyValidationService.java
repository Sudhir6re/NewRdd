package com.mahait.gov.in.nps.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;
import com.mahait.gov.in.response.MessageResponse;

public interface LegacyValidationService {

	List<DcpsLegacyModel> findNsdlLegacyList(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages);

	String viewAndSaveLegacyFile(OrgUserMst messages, String fileId);

	MessageResponse deleteLegacyData(OrgUserMst messages, String fileId, String bhId);

	String validateLegacyData(OrgUserMst messages, String fileId, Integer month, Integer year);


}
