package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstEmployeeEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
@SuppressWarnings("unchecked")
public class EmpChangeBasicDtlsRepoImpl implements EmpChangeBasicDtlsRepo{
	
	@PersistenceContext
	EntityManager manager;
	
	
	public List<Object[]> findEmpChangeBasicDtls(String ddo) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select employee_full_name_en,sevaarth_id,case when seven_pc_basic <> 0  then seven_pc_basic else basic_pay end as basicPay,employee_id,case when updated_basic_percentage <> 0 "
				+ " then updated_basic_percentage  end as percentage,case when revised_basic <> 0  then revised_basic else (case when seven_pc_basic <> 0  then seven_pc_basic else basic_pay end ) end as revisedBasic,case when updated_basic_witheff_date is not null  then updated_basic_witheff_date  end as withEffectiveDate  from employee_mst where ddo_code = '"+ddo+"' and is_active = '1'";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}


	
	@Override
	public Serializable saveChangeBasicdtls(MstEmployeeEntity mstEmployeeEntity) {
		
		
		Session currentSession = manager.unwrap(Session.class);
		Serializable saveId=1;
		currentSession.update(mstEmployeeEntity);
		return (Integer) saveId;
	}

	@Override
	public MstEmployeeEntity findEmpData(Long employeeId) {
		System.out.println("----------Inside findEMpdata------------");
		
		MstEmployeeEntity objDept = null;
			Session currentSession = manager.unwrap(Session.class);
			objDept = currentSession.get(MstEmployeeEntity.class, employeeId);
			return objDept;
		}
		
}
