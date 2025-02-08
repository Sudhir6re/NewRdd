package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.BeamsIntegrationEntity;
import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class BDSIntegrationRepoImpl implements BDSIntegrationRepo {
	
	@PersistenceContext
	EntityManager manager;

	public List<Object[]> getBillDtls(Long consolidatePaybillTrnId) {
		Session currentSession = manager.unwrap(Session.class);
		List<Object[]> alBillDtls = new ArrayList<>();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select a.consolidate_paybill_trn_id,a.ddo_code,b.from_year as finyear1,b.to_year as finyear2,a.scheme_code,");
		strQuery.append("a.gross_amt,a.no_of_employee,a.bill_type,a.paybill_month,a.created_date,sum(a.total_deduction) as total_deduction,");
		strQuery.append("sum(a.pt) as pt,sum(a.income_tax) as incometax,sum(a.hrr) as hrr,sum((COALESCE(a.dcps_arr, 0)) + (COALESCE(a.dcps, 0))) as dcps,");
		strQuery.append("sum(a.gis) as gis,sum(a.group_acc_policy) as group_acc_policy,sum(a.pf) as pf,sum(a.revenue_stamp) as revenueStamp,sum(a.gis_zp) as gis_zp from consolidate_paybill_trn a ");
		strQuery.append("inner join  year_mst b ON b.year_id = a.paybill_year ");
		strQuery.append("inner join rlt_zp_ddo_map c on c.rept_ddo_code=a.ddo_code  ");
		strQuery.append("inner join consolidate_paybill_trn_mpg d on d.consolidate_paybill_trn_id=a.consolidate_paybill_trn_id ");
		strQuery.append("where a.consolidate_paybill_trn_id="+consolidatePaybillTrnId);
		strQuery.append(" group by a.consolidate_paybill_trn_id,a.ddo_code,b.from_year,b.to_year,a.scheme_code ");
		
		Query query = currentSession.createNativeQuery(strQuery.toString());
		return (List<Object[]>) query.getResultList();
	}

	@Override
	public PaybillGenerationTrnEntity getConsBillDtls(Long consolidatePaybillTrnId) {
		PaybillGenerationTrnEntity objCadre = null;
		Session currentSession = manager.unwrap(Session.class);
		objCadre = currentSession.get(PaybillGenerationTrnEntity.class, consolidatePaybillTrnId);
		return objCadre;
	}

	@Override
	public void updategetBillDtls(PaybillGenerationTrnEntity paybillGenerationTrnEntity) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		currentSession.merge(paybillGenerationTrnEntity);

	}

	@Override
	public void updateDeptStatus(BeamsIntegrationEntity beamsIntegrationEntity) {
		Session currentSession = manager.unwrap(Session.class);
		currentSession.merge(beamsIntegrationEntity);
	}

	
	@Override
	public List<String> getData(BeamsIntegrationEntity beamsIntegrationEntity, List<String> lst) {
		Session currentSession = manager.unwrap(Session.class);
		currentSession.merge(beamsIntegrationEntity);
		return lst;
	}

	
	
	
	
	/*
	 * List pfDetails = null;
	 * 
	 * final StringBuffer sb = new StringBuffer(); sb.append(
	 * "SELECT emp.HEAD_ACT_CODE,sum(GPF_GRP_ABC + GPF_ADV_GRP_ABC +  GPF_ABC_ARR_MR + GPF_GRP_D + GPF_ADV_GRP_D + GPF_D_ARR_MR) FROM HR_PAY_PAYBILL paybill "
	 * ); sb.append("inner join HR_EIS_EMP_MST eis on eis.EMP_ID=paybill.EMP_ID  ");
	 * sb.append("inner join Org_emp_mst emp on emp.EMP_ID=eis.EMP_MPG_ID ");
	 * sb.append(
	 * " where paybill.PAYBILL_GRP_ID in (select paybill_id from consolidated_bill_mpg where cons_bill_id in ("
	 * + consBillId + ")) and emp.HEAD_ACT_CODE is not null ");
	 * sb.append(" group by emp.HEAD_ACT_CODE");
	 * 
	 * this.logger.info("---- getPfDetails DAo---" + sb.toString()); final Query
	 * query = this.hibSession.createSQLQuery(sb.toString()); pfDetails =
	 * query.list(); return pfDetails;
	 */
	
	
	public List<Object[]> getheadpf(Long consolidatePaybillTrnId) {
		Session currentSession = manager.unwrap(Session.class);
		List<Object[]> allheadpf = new ArrayList<>();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select a.head_act_code,sum(GPF_GRP_ABC + GPF_ADV_GRP_ABC +  GPF_ABC_ARR_MR + GPF_GRP_D + GPF_ADV_GRP_D + GPF_D_ARR_MR)  from employee_mst a ");		
		strQuery.append("inner join paybill_generation_trn b ON a.ddo_code=b.ddo_code inner join paybill_generation_trn_details c ON c.paybill_generation_trn_id=b.paybill_generation_trn_id ");
		strQuery.append(" where c.paybill_generation_trn_id in(select paybill_generation_trn_id from consolidate_paybill_trn_mpg where consolidate_paybill_trn_id="+ consolidatePaybillTrnId+")");
		strQuery.append(" and a.head_act_code is not null GROUP BY a.head_act_code");
		Query query = currentSession.createNativeQuery(strQuery.toString());
		return (List<Object[]>) query.getResultList();
	}

	@Override
	public boolean isEkuber(String ddoCode) {
		boolean result = false;
		Session currentSession = manager.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM HR_PAY_EKUBER_DDO_CODE where DDO_CODE = '" + ddoCode + "' and ACTIVATE_FLAG = '1' ");
		Query query = currentSession.createNativeQuery(sb.toString());
		try {
			if (query != null && query.getResultList() != null && query.getResultList().size() == 1)
				result = true;
		} catch (NullPointerException e) {
			result = false;
		}
		return result;
	}

	@Override
	public ConsolidatePayBillTrnEntity getConsolidatedPaybillDtls(Long consolidatePaybillTrnId) {
		ConsolidatePayBillTrnEntity objCadre = null;
		Session currentSession = manager.unwrap(Session.class);
		objCadre = currentSession.get(ConsolidatePayBillTrnEntity.class, consolidatePaybillTrnId);
		return objCadre;
	
	}

	@Override
	public void updategetBillDtls(ConsolidatePayBillTrnEntity consolidatePayBillTrnEntity) {
		Session currentSession = manager.unwrap(Session.class);
		currentSession.merge(consolidatePayBillTrnEntity);
	}



}
