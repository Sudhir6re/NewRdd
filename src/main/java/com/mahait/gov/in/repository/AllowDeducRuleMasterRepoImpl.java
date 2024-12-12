package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.AllowanceDeductionRuleMstEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class AllowDeducRuleMasterRepoImpl implements AllowDeducRuleMasterRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> findAllRules(int departmentAllowdeducCode) {

		Session currentSession = entityManager.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append(
				" select a.allowance_deduction_wise_rule_id,a.amount,a.created_date,a.created_user_id,a.department_allowdeduc_code,");
		sb.append(" a.end_date,a.is_active,a.is_type,a.pay_commission_code, ");
		sb.append(
				"  a.percentage,a.start_date,a.premium_amount,a.city_class,a.max_basic,a.min_basic,a.city_group,a.grade_pay_higher,a.grade_pay_lower, ");
		sb.append("  c.DEPARTMENT_ALLOWDEDUC_NAME,b.commission_name_en,b.commission_name_mh ");
		sb.append(
				" from ALLOWANCE_DEDUCTION_WISE_RULE_MST a left join pay_commission_mst b on a.pay_commission_code=b.pay_commission_code ");
		sb.append(
				" inner join department_allowdeduc_mst c on  c.DEPARTMENT_ALLOWDEDUC_CODE=a.DEPARTMENT_ALLOWDEDUC_CODE ");

		if (departmentAllowdeducCode != 0) {
			sb.append("where  a.DEPARTMENT_ALLOWDEDUC_CODE=" + departmentAllowdeducCode);
		}

		Query query = currentSession.createNativeQuery(sb.toString());
		return query.getResultList();
	}

	@Override
	public int saveAllowanceDeductionRulesMaster(AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		return (int) currentSession.save(allowanceDeductionRuleMstEntity);
	}

	@Override
	public void updateAllowanceDeductionRulesMaster(AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(allowanceDeductionRuleMstEntity);
	}

	@Override
	public AllowanceDeductionRuleMstEntity findRuleByComponentCode(Integer allowanceDeductionWiseRuleId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "FROM AllowanceDeductionRuleMstEntity as t where t.departmentAllowdeducCode="
				+ allowanceDeductionWiseRuleId;
		List<AllowanceDeductionRuleMstEntity> lstAllowanceDeductionMstEntity = (List<AllowanceDeductionRuleMstEntity>) entityManager
				.createQuery(HQL).getResultList();
		if (lstAllowanceDeductionMstEntity.size() > 0) {
			return lstAllowanceDeductionMstEntity.get(0);
		} else {
			return null;
		}
	}

	@Override
	public AllowanceDeductionRuleMstEntity findRuleByRuleId(int allowanceDeductionWiseRuleId) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.find(AllowanceDeductionRuleMstEntity.class, allowanceDeductionWiseRuleId);
	}

	@Override
	public AllowanceDeductionRuleMstEntity deleteRule(int id, char status) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "UPDATE allowance_deducduction_wise_mst SET is_active =(CASE  WHEN  is_active ='" + status
				+ "' THEN '0' else '1' END) where allowance_deducduction_wise_id=" + id;
		Query query = currentSession.createNativeQuery(HQL);
		query.executeUpdate();
		int updatedCount = query.executeUpdate();
		// query.list();
		if (updatedCount > 0) {
			return new AllowanceDeductionRuleMstEntity();
		} else {
			return null;
		}
	}

	@Override
	public AllowanceDeductionRuleMstEntity permanentDeleteRule(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "delete from allowance_deducduction_wise_mst  where allowance_deducduction_wise_id=" + id;
		Query query = currentSession.createNativeQuery(HQL);
		query.executeUpdate();
		int updatedCount = query.executeUpdate();
		// query.list();
		if (updatedCount > 0) {
			return new AllowanceDeductionRuleMstEntity();
		} else {
			return new AllowanceDeductionRuleMstEntity();
		}
	}

}
