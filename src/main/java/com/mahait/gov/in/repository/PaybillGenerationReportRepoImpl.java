package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PaybillGenerationReportModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository
public class PaybillGenerationReportRepoImpl implements PaybillGenerationReportRepo {
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	

	@Override
	public List<Object[]> findRegionWiseData(PaybillGenerationReportModel paybillGenerationReportModel,
			OrgUserMst messages) {
		Session session=entityManager.unwrap(Session.class);
		String sqlQuery=" select e.loc_name,count(zp_ddo_code),count(b.is_active) as total,count(b.is_active) as generated,\r\n" + 
				" count(b.is_active) as notGenearated from \r\n" + 
				"  paybill_generation_trn_details a \r\n" + 
				" inner join paybill_generation_trn b on \r\n" + 
				" a.paybill_generation_trn_id=b.paybill_generation_trn_id\r\n" + 
				" inner join org_ddo_mst c on c.ddo_code=b.ddo_code\r\n" + 
				" inner join rlt_zp_ddo_map f on f.zp_ddo_code=c.ddo_code \r\n" + 
				" inner join cmn_location_mst e on e.loc_id=cast(c.location_code as bigint)\r\n" + 
				" group by f.zp_ddo_code,e.loc_name\r\n" + 
				" where b.is_active=14  and b.paybill_month="+paybillGenerationReportModel.getMonth()+" and paybill_year="+paybillGenerationReportModel.getYear();
		
		Query query=session.createNativeQuery(sqlQuery);
		return query.getResultList();
	}
}