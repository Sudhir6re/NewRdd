package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.ConsolidatePayBillTrnMpgEntity;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class ConsolidatePayBillRepoImpl implements ConsolidatePayBillRepo {
	// protected final Log logger = LogFactory.getLog(getClass());
	@PersistenceContext
	EntityManager entityManager;

	public Long saveConsolidatePayBill(ConsolidatePayBillTrnEntity objEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(objEntity);
		return (Long) saveId;
	}

	@Override
	public Long saveConsolidatePayBillTrnMpg(ConsolidatePayBillTrnMpgEntity objEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(objEntity);
		return (Long) saveId;
	}

	@Override
	public List<Object[]> addConsComponents(String ddoCode, List<Integer> payBillGenerationTransId) {
		Session currentSession = entityManager.unwrap(Session.class);

		String HQL = "  select SUM(COALESCE(it,0)+ COALESCE(income_tax,0)) as IT,sum(dcps_arr)as DCPS_ARR,  sum(gis)as GIS,sum(pt)as PT,sum(group_acc_policy)as GROUP_ACC_POLICY,sum(hrr)as HRR, Sum(total_deduction)as TotalDeduct, SUM(COALESCE(gpf_grp_abc,0) + COALESCE(GPF_ADV_GRP_ABC,0)+COALESCE(GPF_ABC_ARR,0)+COALESCE(gpf_grp_d,0)+COALESCE(GPF_ADV_GRP_D,0)+COALESCE(GPF_D_ARR,0))as prov_fund,sum(COALESCE(dcps_da_arr,0)+COALESCE(dcps_delay,0)+COALESCE(dcps_employer,0)+COALESCE(dcps_pay_arr,0)+COALESCE(dcps_regular_recovery,0))as dcps\r\n"
				+ " from paybill_generation_trn_details  where paybill_generation_trn_id in :payBillGenerationTransId ";
		Query query = currentSession.createNativeQuery(HQL);
		query.setParameter("payBillGenerationTransId", payBillGenerationTransId);

		System.out.println("raw query" + query.getQueryString());
		return query.list();
	}

	@Override
	public List<Object[]> fetchDDOLst(String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);

		String HQL = " select distinct c.ddo_code,a.off_name from mst_dcps_ddo_office a inner join rlt_zp_ddo_map b on a.ddo_code=b.zp_ddo_code\r\n"
				+ "inner join paybill_generation_trn c on a.ddo_code=c.ddo_code\r\n" + "where rept_ddo_code =:ddoCode ";

		Query query = currentSession.createNativeQuery(HQL);
		query.setParameter("ddoCode", ddoCode);

		System.out.println("raw query" + query.getQueryString());
		return query.list();
	}

	@Override
	public List<Object[]> fetchConsolidateDetails(String ddoCode, Integer monthId, Integer yearId, String schemeCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select a.ddo_code,a.paybill_generation_trn_id,c.description,a.bill_gross_amt,a.bill_net_amount from paybill_generation_trn a "
				+ " inner join rlt_zp_ddo_map b on a.ddo_code=b.zp_ddo_code "
				+ " inner join mst_dcps_bill_group c on a.scheme_billgroup_id=c.bill_group_id"
				+ " where c.scheme_code = '" + schemeCode + "' and a.paybill_month = " + monthId
				+ " and a.paybill_year = " + yearId + " and a.is_active ='6' and a.ddo_code = '" + ddoCode + "'";

		System.out.println("fetchConsolidateDetails------" + hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.list();

	}

	@Override
	public List<PaybillGenerationTrnEntity> getPaybillDtls(Integer monthName, Integer yearName, String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		// TODO Auto-generated method stub
		String HQL = "From PaybillGenerationTrnEntity as t where t.paybillMonth=" + monthName + " and t.paybillYear="
				+ yearName + " and t.ddoCode='" + ddoCode + "' and isActive = 6";
		return (List<PaybillGenerationTrnEntity>) entityManager.createQuery(HQL).getResultList();

	}

	@Override
	public List<Object[]> fetchbilldts(Long paybillGenerationTrnId) {
		Session currentSession = entityManager.unwrap(Session.class);

		String HQL = " select  COALESCE(IT,0) as IT,sum(DCPS)as DCPS_ARR,sum(gis)as GIS,sum(pt)as PT,sum(ACC_POLICY)as GROUP_ACC_POLICY,sum(hrr)as HRR, "
				+ " Sum(TOTAL_DED)as TotalDeduct,SUM(COALESCE(gpf_grp_abc,0) + COALESCE(GPF_ADV_GRP_ABC,0) + "
				+ " COALESCE(GPF_ABC_ARR_MR,0)+COALESCE(gpf_grp_d,0)+COALESCE(GPF_ADV_GRP_D,0)+COALESCE(GPF_D_ARR_MR,0))as prov_fund,sum(GIS_ZP)as GIS_ZP "
				+ " from paybill_generation_trn_details  where paybill_generation_trn_id='" + paybillGenerationTrnId
				+ "' group by "
				+ " IT,gis,pt,ACC_POLICY,hrr,gpf_grp_abc,GPF_ADV_GRP_ABC,GPF_ABC_ARR_MR,gpf_grp_d,GPF_ADV_GRP_D,GPF_D_ARR_MR";/// ,sum(COALESCE(dcps_da_arr,0)+COALESCE(dcps_delay,0)+COALESCE(dcps_employer,0)+COALESCE(dcps_pay_arr,0)+COALESCE(dcps_regular_recovery,0))as
		/// dcps
		Query query = currentSession.createNativeQuery(HQL);

		System.out.println("raw query" + query.getQueryString());
		return query.list();
	}

	
	@Override
	public Long getConsolidateTrnId() {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT nextval('consolidate_paybill_trn_consolidate_paybill_trn_id_seq')";
		Query query = currentSession.createQuery(hql);
		return (Long) query.list().get(0);
	}

	@Override
	public Serializable saveDtlsPaybillTrn(PaybillGenerationTrnEntity paybillGenerationTrnEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(paybillGenerationTrnEntity);
		return (Long) saveId;
	}

	@Override
	public PaybillGenerationTrnEntity findPaybillDtls(String ddoCode, Long billNo) {
		try {
			String HQL = "FROM PaybillGenerationTrnEntity as  t  where t.ddoCode = '" + ddoCode
					+ "' and t.paybillGenerationTrnId='" + billNo + "'";
			return (PaybillGenerationTrnEntity) entityManager.createQuery(HQL).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void updaterejectConsolidateStatus(PaybillGenerationTrnEntity paybillGenerationTrnEntity) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(paybillGenerationTrnEntity);
	}

	@Override
	public List<Object[]> fetchDDOLstForConsolidateApproval(String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);

		String HQL = " select consolidate_paybill_trn_id,scheme_code, '-'as subSchemeCode,gross_amt,net_amt  from consolidate_paybill_trn  where ddo_code = '"
				+ ddoCode + "'" + " and is_active = 9 ";

		Query query = currentSession.createNativeQuery(HQL);

		System.out.println("raw query" + query.getQueryString());
		return query.list();
	}

	@Override
	public Serializable updateConsolidateapproveStatus(Long consolidateId) {
		// TODO Auto-generated method stub
		try {

			Session currentSession = entityManager.unwrap(Session.class);
			String hql = "update paybill_generation_trn a set is_active='11' from consolidate_paybill_trn_mpg b where a.paybill_generation_trn_id=b.paybill_generation_trn_id and b.consolidate_paybill_trn_id ='"
					+ consolidateId + "' ";

			Query query = currentSession.createNativeQuery(hql);
			query.executeUpdate();

			hql = "update consolidate_paybill_trn a set is_active='11' from consolidate_paybill_trn_mpg b where a.consolidate_paybill_trn_id=b.consolidate_paybill_trn_id and b.consolidate_paybill_trn_id ='"
					+ consolidateId + "'";
			query = currentSession.createNativeQuery(hql);
			query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	

	@Override
	public void deleteConsolidateBill(Long consPaybillGenerationTrnId) {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			String hql = "update paybill_generation_trn a set is_active='13' from consolidate_paybill_trn_mpg b where a.paybill_generation_trn_id=b.paybill_generation_trn_id and b.consolidate_paybill_trn_id ='"
					+ consPaybillGenerationTrnId + "' ";

			Query query = currentSession.createNativeQuery(hql);
			query.executeUpdate();

			hql = "update consolidate_paybill_trn a set is_active='13' from consolidate_paybill_trn_mpg b where a.consolidate_paybill_trn_id=b.consolidate_paybill_trn_id and b.consolidate_paybill_trn_id ='"
					+ consPaybillGenerationTrnId + "'";
			query = currentSession.createNativeQuery(hql);
			query.executeUpdate();
			
			
			
			/*
			 * hql =
			 * "delete from from consolidate_paybill_trn_mpg where consolidate_paybill_trn_id ="
			 * + consPaybillGenerationTrnId;
			 * 
			 * query = currentSession.createNativeQuery(hql); query.executeUpdate();
			 * 
			 * hql =
			 * "delete from consolidate_paybill_trn where consolidate_paybill_trn_id ="+
			 * consPaybillGenerationTrnId; query = currentSession.createNativeQuery(hql);
			 * query.executeUpdate();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
