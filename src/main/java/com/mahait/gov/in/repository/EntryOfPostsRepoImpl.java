package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
import com.mahait.gov.in.entity.SubjectPostMpg;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class EntryOfPostsRepoImpl implements EntryOfPostsRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<MstDesignationEntity> getActiveDesig(Long lLngFieldDept) {
		Session session = getSession();
		//String HQL_QUERY = "from MstDesignationEntity ";
		
		String HQL_QUERY = "select mst from MstDcpsDesignation dcpsDesig, MstDesignationEntity mst  "
				+ "where mst.desginationId=dcpsDesig.orgDesignation and mst.isActive='1' and  dcpsDesig.fieldDeptId =  "
				+ lLngFieldDept;
		Query query = session.createQuery(HQL_QUERY);
		List resultList = query.getResultList();
		return resultList;
	}

	public Session getSession() {
		Session session = entityManager.unwrap(Session.class);
		return session;
	}

	@Override
	public List getAllBillsFromLocation(Long locId) {
		Session session = getSession();
		String HQL_QUERY = " from MstDcpsBillGroup where locId= " + locId
				+ " and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y')";
		Query query = session.createQuery(HQL_QUERY);
		List resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<OrgDdoMst> getDDOCodeByLoggedInlocId(long locId) {
		List<OrgDdoMst> ddoList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" from OrgDdoMst as ddo where locationCode='" + locId + "' ");
		Query sqlQuery = hibSession.createQuery(query.toString());
		ddoList = sqlQuery.getResultList();
		return ddoList;
	}

	@Override
	public List getAllBranchList(long langId) {
		Session session = getSession();
		String hql=" from CmnBranchMst";
		Query sqlQuery = session.createQuery(hql);
		return sqlQuery.getResultList();
	}

	@Override
	public List getAllOfficesFromDDO(String ddoCode) {
		Session session = getSession();
		String HQL_QUERY = " from DdoOffice where dcpsDdoCode='" + ddoCode + "'";
		Query query = session.createQuery(HQL_QUERY);
		List resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> getSubjectList() {
		List subList = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM FRM_SUBJECT_MST");
		Query query = session.createNativeQuery(sb.toString());
		subList = query.getResultList();
		return subList;
	}

	@Override
	public List getSubOfficesFromDDONew(Long postId) {
		List SubOffices = null;
		Session session = getSession();
		String strQuery = "SELECT FIELD_ID,SUB_FIELD_NAME FROM SUB_FIELD_DEPT";
		Query query = session.createNativeQuery(strQuery);
		SubOffices = query.getResultList();
		return SubOffices;
	}

	@Override
	public String districtName(String ddoCode) {
		String districtId = "";
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT distinct DIstrict FROM MST_DCPS_DDO_OFFICE where ddo_code='" + ddoCode
				+ "' and lower(ddo_office)='yes'");
		Query query = session.createNativeQuery(sb.toString());
		districtId = (String) query.getSingleResult();
		return districtId;
	}

	@Override
	public List allTaluka(String districtID) {
		List talukaList = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT TALUKA_ID,TALUKA_NAME FROM CMN_TALUKA_MST where DISTRICT_ID=" + districtID);
		Query query = session.createNativeQuery(sb.toString());
		talukaList = query.getResultList();
		return talukaList;
	}

	@Override
	public List getSubDDOsOffc(long postId, String talukaId, String ddoSelected) {
		List ddoDtls = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"SELECT ddo.DDO_CODE ,ddo.DDO_office FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo, mst_dcps_ddo_office office where lower(office.ddo_office)= 'yes' and ddo.DDO_OFFICE !='null' and office.status_flag=1 and ddo.DDO_CODE = rep.ZP_DDO_CODE and office.ddo_code=ddo.ddo_code and rep.status =1 and ((rep.REPT_DDO_POST_ID='"
						+ postId + "') or (rep.FINAL_DDO_POST_ID='" + postId + "'))");
		if ((talukaId != null) && (talukaId != "") && (Long.parseLong(talukaId) != -1)) {
			sb.append(" and office.taluka=" + talukaId);
		}
		if ((ddoSelected != null) && (ddoSelected != "")) {
			sb.append(" and (office.ddo_code like '%" + ddoSelected + "%' or ddo.ddo_office like '%" + ddoSelected
					+ "%')");
		}
		Query query = session.createNativeQuery(sb.toString());
		ddoDtls = query.getResultList();
		return ddoDtls;

	}

	@Override
	public Long savePostDetails(OrgPostDetailsRlt orgPostDetailsRlt) {
		Session session = getSession();
		return (Long) session.save(orgPostDetailsRlt);
	}

	@Override
	public Long savePost(OrgPostMst orgPostMst) {
		Session session = getSession();
		return (Long) session.save(orgPostMst);
	}

	@Override
	public Long savePostDetails(HrPayOfficepostMpg hrPayOfficepostMpg) {
		Session session = getSession();
		return (Long) session.save(hrPayOfficepostMpg);
	}

	@Override
	public List getSubLocationDDOs(BigInteger loggedInPostId) {
		List ddoDtls = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"SELECT ddo.LOCATION_CODE FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and ((rep.REPT_DDO_POST_ID='"
						+ loggedInPostId + "') or (rep.FINAL_DDO_POST_ID='" + loggedInPostId + "'))");
		Query query = session.createNativeQuery(sb.toString());
		ddoDtls = query.getResultList();
		return ddoDtls;
	}

	@Override
	public List getFilterDdoCode(String locationcodeArray) {
		List orderMstList = null;
		Session hibSession = getSession();
		String strQuery = " SELECT DDO_CODE,DDO_OFFICE FROM org_ddo_mst where LOCATION_CODE in (" + locationcodeArray
				+ ") order by DDO_CODE asc";
		Query query = hibSession.createNativeQuery(strQuery);
		orderMstList = query.getResultList();
		return orderMstList;
	}

	@Override
	public List getSubDDOsOffc(BigInteger loggedInPostId, String talukaId, String ddoSelected) {
		List ddoDtls = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"SELECT ddo.DDO_CODE ,ddo.DDO_office FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo, mst_dcps_ddo_office office where lower(office.ddo_office)= 'yes' and ddo.DDO_OFFICE !='null' and office.status_flag=1 and ddo.DDO_CODE = rep.ZP_DDO_CODE and office.ddo_code=ddo.ddo_code and rep.status =1 and ((rep.REPT_DDO_POST_ID='"
						+ loggedInPostId + "') or (rep.FINAL_DDO_POST_ID='" + loggedInPostId + "'))");
		if ((talukaId != null) && (talukaId != "") && (Long.parseLong(talukaId) != -1)) {
			sb.append(" and office.taluka=" + talukaId);
		}
		if ((ddoSelected != null) && (ddoSelected != "")) {
			sb.append(" and (office.ddo_code like '%" + ddoSelected + "%' or ddo.ddo_office like '%" + ddoSelected
					+ "%')");
		}
		Query query = session.createNativeQuery(sb.toString());
		ddoDtls = query.getResultList();
		return ddoDtls;

	}

	@Override
	public List getDDODtls(String ddoCode) {
		List ddoDtls = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT LOCATION_CODE,HOD_LOC_CODE,post_ID FROM ORG_DDO_MST where DDO_CODE='" + ddoCode + "'");
		Query query = session.createNativeQuery(sb.toString());
		ddoDtls = query.getResultList();
		return ddoDtls;

	}

	@Override
	public List findHrPayOrderHeadMpg(long orderId) {
		Session session = getSession();
		String HQL_QUERY = " from HrPayOrderHeadMpg where orderId='" + orderId + "'";
		Query query = session.createQuery(HQL_QUERY);
		List resultList = query.getResultList();
		return resultList;

	}

	@Override
	public void create(HrPayOrderHeadMpg orderHeadMpg) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.save(orderHeadMpg);
	}

	@Override
	public OrgPostMst findPost(long longValue) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		return session.find(OrgPostMst.class, longValue);
	}

	@Override
	public Long submitSubject(SubjectPostMpg subjectPostMpg) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		return (Long) session.save(subjectPostMpg);

	}

	@Override
	public Long save(HrPayPsrPostMpg postPsrMpg) {
		Session session = entityManager.unwrap(Session.class);
		return (Long) session.save(postPsrMpg);
	}

	@Override
	public Long savePostDtls(OrgPostDetailsRlt orgPostDtlRlt) {
		Session session = entityManager.unwrap(Session.class);
		return (Long) session.save(orgPostDtlRlt);

	}

	@Override
	public Long save(HrPayOrderHeadPostMpg orderHeadPostmpg) {
		Session session = entityManager.unwrap(Session.class);
		return (Long) session.save(orderHeadPostmpg);
	}

	@Override
	public Long save(HrPayOfficepostMpg hrOfficePostMpg) {
		Session session = entityManager.unwrap(Session.class);
		return (Long) session.save(hrOfficePostMpg);

	}

	@Override
	public MstDesignationEntity finddesignationByCode(Long valueOf) {
		Session session = entityManager.unwrap(Session.class);
		return session.find(MstDesignationEntity.class, valueOf);
	}

	@Override
	public long getNextPsrNo() {
		long nextPsr = 0;
		Session hibSession = getSession();
		String nextPsrlstr = "";
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("Select max(psrId) from HrPayPsrPostMpg psr ");
		List psrList = hibSession.createQuery(strQuery.toString()).getResultList();
		if (psrList.get(0) == null) {
			nextPsr = 1l;
		} else {
			nextPsr = Long.parseLong(psrList.get(0).toString()) + 1;
		}
		return nextPsr;
	}

	@Override
	public DdoOffice findOfficeByfficeId(Long valueOf) {
		Session session = entityManager.unwrap(Session.class);
		return session.find(DdoOffice.class, valueOf);
	}

	@Override
	public DdoOffice findOfficeByOfficeId(Long valueOf) {
		Session session = entityManager.unwrap(Session.class);
		return session.find(DdoOffice.class, valueOf);
	}

	@Override
	public List<HrPayOrderMst> getAllOrderData(long locId, String ddoCode) {
		List<HrPayOrderMst> orderMstList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayOrderMst orderMst where orderMst.locationCode = :locId or ddoCode=:ddoCode order by orderMst.orderName";
		Query query = hibSession.createQuery(strQuery);
		query.setParameter("locId", Long.toString(locId));
		query.setParameter("ddoCode", ddoCode);
		orderMstList = query.getResultList();
		return orderMstList;
	}

	@Override
	public List<HrPayOrderMst> findGrOrderDetails(Long orderId) {
		List<HrPayOrderMst> orderMstList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayOrderMst orderMst where orderMst.orderId = :orderId";
		Query query = hibSession.createQuery(strQuery);
		query.setParameter("orderId", orderId);
		orderMstList = query.getResultList();
		return orderMstList;
	}

	
	
		
	@Override
	public List getPostNameForDisplay(String loginDddo, String lPostName, String PsrNo, String BillNo, String Dsgn,
			String ddoSelected) {
		List postNameList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.post_name, a.post_id,f.employee_full_name_en,c.designation_name,e.PSR_NO, ");
		sb.append(" i.DESCRIPTION,b.post_type_lookup_id,d.lookup_name,g.ddo_code  from org_post_details_rlt a  ");
		sb.append(" inner join org_post_mst b on a.post_id=b.post_id ");
		sb.append(" inner join designation_mst c on a.dsgn_id = c.designation_id ");
		sb.append(" inner join cmn_lookup_mst d on d.lookup_id=b.post_type_lookup_id  ");
		sb.append(" inner join HR_PAY_POST_PSR_MPG e on e.POST_ID=b.post_id  ");
		sb.append(" left join employee_mst f on f.post_detail_id=a.post_id  ");
		sb.append(" inner join org_ddo_mst g on a.loc_id = cast(g.location_code as bigint)  ");
		sb.append(" inner join cmn_lookup_mst  h on h.lookup_id=b.post_type_lookup_id   ");
		sb.append(" left join mst_dcps_bill_group  i on i.BILL_GROUP_ID=e.bill_no   ");
		
		sb.append("  where a.loc_id in (SELECT cast(location_code as bigint) FROM org_ddo_mst  a inner join rlt_zp_ddo_map b on a.ddo_code=b.zp_ddo_code" + 
				"  where rept_ddo_code='"+loginDddo+"')");
		
		if ((ddoSelected != null) && (ddoSelected != "")) {
			sb.append(
					" and a.loc_id =(select cast(loc.location_code as bigint) from org_ddo_mst loc where loc.ddo_code='"
							+ ddoSelected + "')");
		} 
		
		if (BillNo != null && !BillNo.equals(""))
			sb.append("  and i.BILL_GROUP_ID  = " + BillNo);
		
	    if (Dsgn != null && !(Dsgn.trim()).equals(""))
			sb.append("  and  upper(c.DESIGNATION_NAME) like  upper('%" + Dsgn + "%')  ");
		else
			sb.append("  and  upper(a.post_name) like  upper('%" + lPostName + "%') ");
		
		sb.append("   order by a.CREATED_DATE desc  ");

		System.out.println(sb.toString());

		Query query = hibSession.createNativeQuery(sb.toString());
		postNameList = query.getResultList();
		return postNameList;
	}

	@Override
	public OrgPostMst findPostObj(Long postId) {
		Session hibSession = getSession();
		OrgPostMst save = hibSession.get(OrgPostMst.class, postId);
		return save;
	}

	@Override
	public List searchPostListByGrOrderId(Long orderId) {
		List postList = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"select postRlt from OrgPostMst postMst, OrgPostDetailsRlt postRlt where");
		sb.append(" postMst.postTypeLookupId = 10001198130 and postMst.postId = postRlt.orgPostMst.postId and postMst.orderId =");  //and endDate < CURRENT_DATE

		sb.append(orderId);
		Query query = session.createQuery(sb.toString());
		postList = query.getResultList();
		return postList;
	}

	@Override
	public List<HrPayOrderMst> getAllOrderDataByDate(long locId, String todaysDate, String ddoCode) {
		List orderMstList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayOrderMst orderMst where (orderMst.locationCode in ('" + locId
				+ "') or  orderMst.ddoCode='" + ddoCode + "') order by orderMst.orderName";
		Query query = hibSession.createQuery(strQuery);
		orderMstList = query.getResultList();
		return orderMstList;
	}

	/*@Override
	public List getExpiryData(long locId, String ddoCode) {
		List orderMstList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayOrderMst orderMst where (orderMst.locationCode in (" + locId
				+ ") or orderMst.ddoCode='" + ddoCode + "') and endDate < CURRENT_DATE  order by orderMst.orderName";
		Query query = hibSession.createQuery(strQuery);
		orderMstList = query.getResultList();
		return orderMstList;
	}*/
	
	@Override
	public List getExpiryData(long locId, String ddoCode) {
	    List orderMstList = null;
	    Session hibSession = getSession();
	    String strQuery = "from HrPayOrderMst orderMst where (orderMst.locationCode = :locId or orderMst.ddoCode = :ddoCode) order by orderMst.orderName"; // and endDate < CURRENT_DATE
	    Query query = hibSession.createQuery(strQuery);
	    query.setParameter("locId", String.valueOf(locId));
	    query.setParameter("ddoCode", ddoCode);
	    orderMstList = query.getResultList();
	    return orderMstList;
	}


	@Override
	public HrPayOrderMst find(Long orderId) {
		Session hibSession = getSession();
		HrPayOrderMst hrPayOrderMst=hibSession.find(HrPayOrderMst.class,orderId);
		return hrPayOrderMst;
	}

	@Override
	public List findLevel1DddoByDdoCode(String ddoCode) {
		List orderMstList = null;
		Session hibSession = getSession();
		String strQuery = " SELECT DDO_CODE,DDO_OFFICE FROM org_ddo_mst  a inner join rlt_zp_ddo_map b on a.ddo_code=b.zp_ddo_code" + 
				" where rept_ddo_code='"+ddoCode+"' order by a.DDO_CODE asc";
		Query query = hibSession.createNativeQuery(strQuery);
		orderMstList = query.getResultList();
		return orderMstList;
	}

	@Override
	public HrPayOrderMst findOrderMasterById(long oldGrOrderId) {
		Session hibSession = getSession();
		HrPayOrderMst hrPayOrderMst=hibSession.find(HrPayOrderMst.class,oldGrOrderId);
		return hrPayOrderMst;
	}

}
