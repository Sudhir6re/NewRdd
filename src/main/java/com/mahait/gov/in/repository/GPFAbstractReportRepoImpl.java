package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class GPFAbstractReportRepoImpl implements GPFAbstractReportRepo {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> findgpfRptDtls(Integer monthId, Integer yearId, Long billGroup) {

		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		String queryString = " select pfdescription,count(a.sevaarth_id),sum(b.D_PAY) as DP,sum(b.GPF_GRP_ABC+b.GPF_IAS+b.GPF_IAS_OTHER+b.GPF_IPS+b.GPF_IFS) as subs " + 
				" , sum(b.GPF_ABC_Arr_Mr+b.GPF_IAS_Arr_Mr+b.GPF_IFS_Arr_Mr) as payDaArrMrg,0 as gpfArr,sum(b.GPF_ADV_GRP_ABC) as refund, " + 
				" sum(b.D_PAY) + sum(b.GPF_GRP_ABC+b.GPF_IAS+b.GPF_IAS_OTHER+b.GPF_IPS+b.GPF_IFS) + sum(b.GPF_ABC_Arr_Mr+b.GPF_IAS_Arr_Mr+b.GPF_IFS_Arr_Mr) " + 
				" + sum(b.GPF_ADV_GRP_ABC) from EMPLOYEE_MST a  " + 
				" inner join PAYBILL_GENERATION_TRN_DETAILS b on a.sevaarth_id=b.sevaarth_id " + 
				" inner join PAYBILL_GENERATION_TRN c on b.paybill_generation_trn_id=c.paybill_generation_trn_id " + 
				" where a.dcps_gpf_flag = 'N' and c.paybill_month = "+monthId+" and c.paybill_year = "+yearId+"  and c.scheme_billgroup_id = '"+billGroup+"'  and c.is_active <>8 " + 
				" group by a.pfdescription ";
		Query query = session.createNativeQuery(queryString);
		System.out.println("-------" + queryString);
		return query.getResultList();
	
	}
	
	

}
