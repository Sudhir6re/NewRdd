package com.mahait.gov.in.repository;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.AllowanceDeductionMstEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
@SuppressWarnings("unchecked")
public class AllowanceDeductionWiseMstRepoImpl implements AllowanceDeductionWiseMstRepo{
	

	@PersistenceContext	
	EntityManager entityManager;

	@Override
	public int saveAllowanceDeductionWiseMaster(AllowanceDeductionMstEntity allowanceDeductionMstEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		return (int) currentSession.save(allowanceDeductionMstEntity);
	}

	@Override
	public List<Object[]> fetchLstDeptEligibilityForAllowAndDeductEntityByType(
			int isType) {
		String HQL = "SELECT t.departmentAllowdeducCode, t.departmentAllowdeducName FROM DeptEligibilityForAllowAndDeductEntity as t WHERE t.isType="+isType;
		return (List<Object[]>) entityManager.createQuery(HQL).getResultList();
	}

	@Override
	public List<Object[]> getAllallowanceDeductionWiseMst() {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select a.*,c.DEPARTMENT_ALLOWDEDUC_NAME,b.commission_name_en,b.commission_name_mh from ALLOWANCE_DEDUCDUCTION_WISE_MST a inner join pay_commission_mst b on a.pay_commission_code=b.pay_commission_code inner join department_allowdeduc_mst c on c.DEPARTMENT_ALLOWDEDUC_CODE=a.DEPARTMENT_ALLOWDEDUC_CODE";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<Object[]> isAllowanceDeductionWiseMasterDataPresent(
		AllowanceDeductionMstEntity allowanceDeductionMstEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		//String HQL ="select * from allowance_deducduction_wise_mst  where   is_type ="+allowanceDeductionMstEntity.getIsType()+" and  pay_commission_code="+allowanceDeductionMstEntity.getPayCommissionCode()+" and    department_allowdeduc_code ="+allowanceDeductionMstEntity.getDepartmentAllowdeducCode()+" and  start_date <='"+sdf.format(allowanceDeductionMstEntity.getStartDate())+"' and  end_date>='"+sdf.format(allowanceDeductionMstEntity.getEndDate())+"' ";
		String HQL ="select * from allowance_deducduction_wise_mst  where   is_type ="+allowanceDeductionMstEntity.getIsType()+" and  pay_commission_code="+allowanceDeductionMstEntity.getPayCommissionCode()+" and    department_allowdeduc_code ="+allowanceDeductionMstEntity.getDepartmentAllowdeducCode();
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public AllowanceDeductionMstEntity findAllowanceDeductionWiseMasterById(int id) {
		// TODO Auto-generated method stub
		AllowanceDeductionMstEntity allowanceDeductionMstEntity=entityManager.find(AllowanceDeductionMstEntity.class, id);
		return allowanceDeductionMstEntity;
	}

	@Override
	public AllowanceDeductionMstEntity deleteAllowanceDeductionWiseMasterById(int id,char status) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String HQL ="UPDATE allowance_deducduction_wise_mst SET is_active =(CASE  WHEN  is_active ='"+status+"' THEN '0' else '1' END) where allowance_deducduction_wise_id="+id;
		Query query = currentSession.createNativeQuery(HQL);
		query.executeUpdate();
		int updatedCount=query.executeUpdate();
		//query.list();
		if(updatedCount>0) {
			return new AllowanceDeductionMstEntity();
		}else {
			return null;
		}
		
	}

	@Override
	public int checkComponentAlreadyPresent(AllowanceDeductionMstEntity allowanceDeductionMstEntity) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String  HQL = "FROM AllowanceDeductionMstEntity as t where t.isType="+allowanceDeductionMstEntity.getIsType()+" and t.departmentAllowdeducCode="+allowanceDeductionMstEntity.getDepartmentAllowdeducCode()+"  and  t.payCommissionCode="+allowanceDeductionMstEntity.getPayCommissionCode()+" ORDER BY t.allowanceDeductionWiseId DESC ";
		List<AllowanceDeductionMstEntity> lstAllowanceDeductionMstEntity= (List<AllowanceDeductionMstEntity>) entityManager.createQuery(HQL).getResultList();
		Integer id = lstAllowanceDeductionMstEntity.stream().map(m -> m.getAllowanceDeductionWiseId()).findFirst().orElse(0);
		return id;
	}

	@Override
	public void updateAllowanceDeductionMstEntity(AllowanceDeductionMstEntity allowanceDeductionMstEntity1) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(allowanceDeductionMstEntity1);
	}

	@Override
	public List<Long> validateAllowDeduct(String allowdeductName, Integer isType) {
	    Session currentSession = entityManager.unwrap(Session.class);

	    String sql = "SELECT COUNT(*) FROM department_allowdeduc_mst " +
	                 "WHERE department_allowdeduc_name = :allowdeductName AND is_type = :isType";
	    Query query = currentSession.createNativeQuery(sql);
	    query.setParameter("allowdeductName", allowdeductName);
	    query.setParameter("isType", isType);
	    List<Long> lstresult = query.getResultList();
	    return lstresult;
	}
}
