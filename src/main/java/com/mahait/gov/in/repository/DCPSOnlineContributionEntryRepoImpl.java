package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class DCPSOnlineContributionEntryRepoImpl implements DCPSOnlineContributionEntryRepo {

	@PersistenceContext
	EntityManager manager;

	@Override
	public List<MstDcpsBillGroup> findAllBillGroup(OrgUserMst messages) {
		String HQL = "FROM MstDcpsBillGroup as t where dcpsDdoCode ='" + messages.getDdoCode()+"' ORDER BY t.dcpsDdoBillGroupId DESC "; 
		return (List<MstDcpsBillGroup>) manager.createQuery(HQL).getResultList();
	}

	@Override
	public List<CmnLocationMst> findTreasuryByDdoCode(OrgUserMst messages) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT LM.locationCode, LM.locName FROM RltDdoOrg RO, CmnLocationMst LM ");
		sb.append("WHERE RO.ddoCode = :ddoCode AND	LM.locationCode = RO.locationCode");
        Session session=manager.unwrap(Session.class);
		List<CmnLocationMst> lstCmnLocationMst = null;
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("ddoCode", messages.getDdoCode());
	    lstCmnLocationMst = query.getResultList();
	    return lstCmnLocationMst;
	}

}
