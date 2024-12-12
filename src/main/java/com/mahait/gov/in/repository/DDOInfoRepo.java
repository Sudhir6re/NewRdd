package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.CmnStateMst;
import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.NewRegDDOModel;

public interface DDOInfoRepo {

	String getDdoCodeForDDO(Long postId);

	List<Object[]> getLevel1DDOList(String lStrDdoCode);

	List<Object[]> getDDoHistoryDetailsForApprove(String ddo);

	List<CmnStateMst> getStateLst(long countryId);
	
	List<CmnDistrictMst> getDistrictlst(long stateId);

	List<DdoOffice> getAllOffices(String ddoCode);

	String getDistrictId(String ddoCode);

	List<CmnTalukaMst> getTalukalst();

	DdoOffice getDdoOfficeDtls(Long dcpsDdoOfficeMstId);

	Serializable SaveApproveDdoOffice(DdoOffice ddoOffice);

	List<CmnLookupMst> findDDOOffClass(Long lookupId);

	List<Object[]> getDDOOffForApproval(String ddoCode);

	List<Object[]> getAlreadySavedDataforDDO(String ddoCode);

	DdoOffice findDdoData(String ddoCode);

	void updateApproveRejectStatus(DdoOffice ddoOffice);

	List<Object[]> getLstTown();

	OrgUserMst findOrgUserMstByDdoCode(String ddoCode);

	void update(OrgUserMst orgUserMst);

}
