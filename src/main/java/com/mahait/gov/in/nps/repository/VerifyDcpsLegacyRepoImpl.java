package com.mahait.gov.in.nps.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class VerifyDcpsLegacyRepoImpl implements VerifyDcpsLegacyRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> findLegacyData(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages, Long locationId) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT a.employee_full_name_en,a.dcps_no,a.sevaarth_id,");
		stringBuilder.append("b.period,b.emp_contri,b.employer_contri,b.emp_int,b.employer_int,b.TOTAL,b.status,a.pran_no,b.remarks  ");
		stringBuilder.append("FROM EMPLOYEE_MST a INNER JOIN DCPS_LEGACY_DATA b on a.sevaarth_id=b.SEVARTH_ID  ");
		stringBuilder.append("where b.status='2' and b.period='" + dcpsLegacyModel.getPeriod() + "'");
		Query query = session.createNativeQuery(stringBuilder.toString());
		return query.list();
	}

	@Override
	public Long findLocId(String locationId) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT LOC.LOC_ID ").append("FROM CMN_LOCATION_MST loc ")
				.append("INNER JOIN ORG_DDO_MST org ON substr(org.DDO_CODE, 1, 4) = cast(loc.LOC_ID as text) ")
				.append("WHERE org.LOCATION_CODE = cast(:locationId as varchar)");
		Query query = session.createNativeQuery(stringBuilder.toString());
		query.setParameter("locationId", locationId); 
		Object result = query.uniqueResult();
		if (result != null) {
			return ((Number) result).longValue();
		}
		return 0l;
	}

	@Override
	public int verifySavedData(String sevaarthId, String period,OrgUserMst messages) {
		Session session = entityManager.unwrap(Session.class);
		int count = 0;
		StringBuffer sb = new StringBuffer();
		Query lQuery = null;
		sb.append(
				" update dcps_legacy_data set status='1',UPDATED_DATE=CURRENT_TIMESTAMP ,APPROVAL_DATE=CURRENT_TIMESTAMP where sevarth_id='"
						+ sevaarthId + "' and period='" + period + "' and status='2' ");
		lQuery = session.createNativeQuery(sb.toString());
		count = lQuery.executeUpdate();
		return count;
	}

	@Override
	public int rejectSavedData(String sevaarthId, String period, String remarks,OrgUserMst messages) {
		Session session = entityManager.unwrap(Session.class);
		int count = 0;
		StringBuffer sb = new StringBuffer();
		Query lQuery = null;
		sb.append("update dcps_legacy_data set status='0', UPDATED_DATE=CURRENT_TIMESTAMP,REJECTION_DATE=CURRENT_TIMESTAMP,remarks='"
				+ remarks + "' where sevarth_id='" + sevaarthId + "' and period='" + period + "' and status='2'  ");
		lQuery = session.createNativeQuery(sb.toString());
		count = lQuery.executeUpdate();
		return count;
	}

}
