package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.List;

import com.mahait.gov.in.entity.HrPayOfficepostMpg;
import com.mahait.gov.in.entity.HrPayOrderMst;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgPostDetailsRlt;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PostEntryModel;

public interface EntryOfPostsService {

	List<MstDesignationEntity> getActiveDesig(Long lLngFieldDept);

	List getAllBillsFromLocation(Long locId);

	List<OrgDdoMst> getDDOCodeByLoggedInlocId(long locId);

	List getAllBranchList(long l);

	List<HrPayOrderMst> getAllOrderData(long locId, String ddoCode);

	List getAllOfficesFromDDO(String ddoCode);

	List<Object[]> getSubjectList();

	List getSubOfficesFromDDONew(Long postId);

	String districtName(String ddoCode);

	List allTaluka(String districtID);

	List getSubDDOsOffc(long loggedInPostId, String talukaId, String ddoSelected);

	Long savePost(OrgPostMst orgPostMst);

	Long savePostDetails(OrgPostDetailsRlt orgPostDetailsRlt);

	Long saveHrPayOfficepostMpg(HrPayOfficepostMpg hrPayOfficepostMpg);

	List getSubLocationDDOs(BigInteger loggedInPostId);

	List getFilterDdoCode(String locationcodeArray);

	List getSubDDOsOffc(BigInteger loggedInPostId, String talukaId, String ddoSelected);

	void savePostEntry(PostEntryModel postEntryModel, long locId, BigInteger loggedInPostId, OrgUserMst messages);

	void savePostEntryDtl(PostEntryModel postEntryModel, long locId, BigInteger loggedInPostId, OrgUserMst messages);

	List<HrPayOrderMst> findGrOrderDetails(Long grOrderId);

	List getPostNameForDisplay(String valueOf);

	List searchPostListByGrOrderId(Long locId, Long orderId);

	List<HrPayOrderMst> getAllOrderDataByDate(long locId, String todaysDate, String ddoCode);

	List getExpiryData(long locId, String ddoCode);

	void renewPostEntry(PostEntryModel postEntryModel, long locId, BigInteger loggedInPostId, OrgUserMst messages);

	List findLevel1DddoByDdoCode(String ddoCode);

	HrPayOrderMst findOrderMasterById(long oldGrOrderId);

	List<Object[]> findLevelDdoCodeByDistrict(String districtId, OrgUserMst messages);

	List<MstDesignationEntity> findAllDesignation();


}
