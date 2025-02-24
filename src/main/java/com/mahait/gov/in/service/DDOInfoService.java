package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.CmnStateMst;
import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ApproveDDOHstModel;
import com.mahait.gov.in.model.NewRegDDOModel;

public interface DDOInfoService {

	String getDdoCodeForDDO(Long postId);

	 List <ApproveDDOHstModel> getLevel1DDOList(String lStrDdoCode);

	 List <Object[]> getDDoHistoryDetailsForApprove(String ddo);
	
	 List<CmnStateMst> getStateLst(long countryId);
		
		List<CmnDistrictMst> getDistrictlst(long stateId);

		List<DdoOffice> getAllOffices(String ddoCode);

		String getDistrictId(String ddoCode);

		List<CmnTalukaMst> getTalukalst();

		DdoOffice getDdoOfficeDtls(Long dcpsDdoOfficeMstId);

		int SaveApproveDdoOffice(NewRegDDOModel newRegDDOModel, OrgUserMst messages);

		List<CmnLookupMst> findDDOOffClass(Long lookupId);

		List<NewRegDDOModel> getDDOOffForApproval(String ddoCode);

		List<Object[]> getAlreadySavedDataforDDO(String ddoCode);

		DdoOffice updateApproveRejectStatus(String ddoCode, int flag, String cityClass);

		List<NewRegDDOModel> getLstTown();

		Long validateAccNo(String accNo, OrgUserMst messages);

		Long validateTelephone(String telPhone, OrgUserMst messages);

		Long validateEmailAdd(String email, OrgUserMst messages);
}
