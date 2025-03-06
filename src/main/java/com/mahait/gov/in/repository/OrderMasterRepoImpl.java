package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.GROrderDocumentEntity;
import com.mahait.gov.in.entity.HrPayOrderMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class OrderMasterRepoImpl implements OrderMasterRepo {
	
	@PersistenceContext
	EntityManager entityManager;


	@Override
	public List<Object[]> findAllEmployee(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select employee_id,sevaarth_id,employee_full_name_en,district_code,taluka_code,city_class from employee_mst where ddo_code = '"+userName+"' and is_active = '1'";
		System.out.println("HQL:"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}


	@Override
	public List<Object[]> getSubGRType(long parentGrpId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT * FROM cmn_lookup_mst where PARENT_LOOKUP_ID="+parentGrpId+" order by ORDER_NO ASC";
		System.out.println("HQL:"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}


	@Override
	public String getDistrictId(String ddoCode) {

		Session currentSession = entityManager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT DIstrict FROM MST_DCPS_DDO_OFFICE where ddo_code='"+ddoCode+"' and DDO_OFFICE='Yes'");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.getResultList();
		if (list != null && list.size() > 0)
			rtnStr = list.get(0).toString();

		return rtnStr;

	}


	@Override
	public List<CmnTalukaMst> gettalukalst(String districtId) {
		String HQL = "FROM CmnTalukaMst as  t  where districtId='"+districtId+"'";
		return (List<CmnTalukaMst>) entityManager.createQuery(HQL).getResultList();

	}


	@Override
	public List<Long> findUsertype(String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);

		String hql = "select count(1) from rlt_Zp_ddo_map where rept_ddo_code='"+ddoCode+"'";
		Query query = currentSession.createNativeQuery(hql).addScalar("count", Long.class);
		List<Long> lstresult = query.getResultList();
		return lstresult;

	}


	@Override
	public Integer saveGrOrder(HrPayOrderMst payOrderMst, MultipartFile[] files) {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		Long saveId=(Long) session.save(payOrderMst);
		return saveId.intValue();
	}


	@Override
	public Long saveAdvanceDocuments(GROrderDocumentEntity grOrderDocumentEntity) {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		Long saveId=(Long) session.save(grOrderDocumentEntity);
		return saveId;
	}


	@Override
	public List<Long> getSubDDOs(Long postId) {
		Session currentSession =  entityManager.unwrap(Session.class);

	String hql = "SELECT ddo.LOCATION_CODE FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and ((rep.REPT_DDO_POST_ID='"+postId+"') or (rep.FINAL_DDO_POST_ID='"+postId+"'))";
	
	Query query = currentSession.createNativeQuery(hql).addScalar("count", Long.class);
	
	List<Long> lstresult = query.getResultList();
	return lstresult;}


	
	
	
	@Override
	public List<Object[]> getsancOrderLst(String ddo) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT hr.ORDER_NAME, hr.ORDER_DATE,CASE  WHEN ddo.DDO_OFFICE IS NULL THEN '' ELSE ddo.DDO_OFFICE END AS DDO_CODE,ddo.DDO_CODE FROM HR_PAY_ORDER_MST hr inner join RLT_ZP_DDO_MAP rep on rep.zp_ddo_code=hr.ddo_code ");
		sb.append("inner join org_ddo_mst ddo on (hr.LOCATION_CODE=ddo.LOCATION_CODE or hr.ddo_code=ddo.ddo_code) where rep.rept_ddo_code='"+ddo+"'");
	
		String hql = " SELECT hr.ORDER_NAME, hr.ORDER_DATE FROM HR_PAY_ORDER_MST hr inner join RLT_ZP_DDO_MAP rep on rep.zp_ddo_code=hr.ddo_code "
				+ "where rep.rept_ddo_code='"+ddo+"'";//inner join org_ddo_mst ddo on hr.LOCATION_CODE=ddo.LOCATION_CODE
		System.out.println("HQL:"+hql);
		Query query = currentSession.createNativeQuery(sb.toString());
		return query.getResultList();
	}
	

	@Override
	public List<Object[]> getddoOff(String locationcodeArray) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = " SELECT DDO_CODE,DDO_OFFICE FROM org_ddo_mst  where LOCATION_CODE in ('"+locationcodeArray+"') order by DDO_CODE asc";
		System.out.println("HQL:"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}


	@Override
	public List<Object[]> getInstitutionLst(String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = " SELECT b.zp_DDO_CODE,a.DDO_OFFICE FROM ORG_DDO_MST a inner join RLT_ZP_DDO_MAP b on a.ddo_code =b.zp_DDO_CODE\r\n" + 
				"		where b.rept_ddo_code= '"+ddoCode+"' order by b.zp_DDO_CODE ";
		System.out.println("HQL:"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}


	@Override
	public Long saveMstGrOrder(HrPayOrderMst payOrderMst) {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		Long saveId=(Long) session.save(payOrderMst);
		return saveId;
	}


	

}
