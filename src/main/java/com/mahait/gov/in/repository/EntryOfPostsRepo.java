package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.List;

import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.HrPayOfficepostMpg;
import com.mahait.gov.in.entity.HrPayOrderHeadMpg;
import com.mahait.gov.in.entity.HrPayOrderHeadPostMpg;
import com.mahait.gov.in.entity.HrPayOrderMst;
import com.mahait.gov.in.entity.HrPayPsrPostMpg;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgPostDetailsRlt;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.SubjectPostMpg;

public interface EntryOfPostsRepo {

	List<MstDesignationEntity> getActiveDesig(Long lLngFieldDept);

	List getAllBillsFromLocation(Long locId);

	List<OrgDdoMst> getDDOCodeByLoggedInlocId(long locId);

	List getAllBranchList(long l);

	List<HrPayOrderMst> getAllOrderData(long locId, String ddoCode);

	List getAllOfficesFromDDO(String ddoCode);

	List getSubjectList();

	List getSubOfficesFromDDONew(Long postId);

	String districtName(String ddoCode);

	List allTaluka(String districtID);

	List getSubDDOsOffc(long loggedInPostId, String talukaId, String ddoSelected);

	Long savePostDetails(OrgPostDetailsRlt orgPostDetailsRlt);

	OrgPostMst savePost(OrgPostMst orgPostMst);

	Long savePostDetails(HrPayOfficepostMpg hrPayOfficepostMpg);

	List getSubLocationDDOs(BigInteger loggedInPostId);

	List getFilterDdoCode(String locationcodeArray);

	List getSubDDOsOffc(BigInteger loggedInPostId, String talukaId, String ddoSelected);

	List getDDODtls(String ddoCode);

	List findHrPayOrderHeadMpg(long orderId);

	void create(HrPayOrderHeadMpg orderHeadMpg);

	OrgPostMst findPost(long longValue);

	Long submitSubject(SubjectPostMpg subjectPostMpg);

	Long save(HrPayPsrPostMpg postPsrMpg);

	Long savePostDtls(OrgPostDetailsRlt orgPostDtlRlt);

	Long save(HrPayOrderHeadPostMpg orderHeadPostmpg);

	Long save(HrPayOfficepostMpg hrOfficePostMpg);

	MstDesignationEntity finddesignationByCode(Long valueOf);

	long getNextPsrNo();

	DdoOffice findOfficeByOfficeId(Long valueOf);


	DdoOffice findOfficeByfficeId(Long valueOf);

	List<HrPayOrderMst> findGrOrderDetails(Long grOrderId);

	List getPostNameForDisplay(String valueOf);

	OrgPostMst findPostObj(Long postId);

	List searchPostListByGrOrderId(Long orderId);

	List<HrPayOrderMst> getAllOrderDataByDate(long locId, String todaysDate, String ddoCode);

	List getExpiryData(long locId, String ddoCode);

	HrPayOrderMst find(Long orderId);

	List findLevel1DddoByDdoCode(String ddoCode);

	HrPayOrderMst findOrderMasterById(long oldGrOrderId);

	List<Object[]> findLevelDdoCodeByDistrict(String districtId, OrgUserMst messages);

	List<MstDesignationEntity> findAllDesignation();

	List<CmnLocationMst> findByLocId(Long valueOf);

	List<MstDesignationEntity> getDesignationLstByDdoCode(String ddoCode);

	List<OrgDdoMst> findDdoDetailByDdoCode(String ddoCode);

}
