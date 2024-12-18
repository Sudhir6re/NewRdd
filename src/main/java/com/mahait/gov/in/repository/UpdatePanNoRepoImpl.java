package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstEmployeeEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class UpdatePanNoRepoImpl implements UpdatePanNoRepo{

	@PersistenceContext
	EntityManager entityManager;
	

	@Override
	public List<Object[]> findAllEmployee(String userName) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select a.employee_id,a.sevaarth_id, a.employee_full_name_en,a.PAN_NO,b.designation_name from employee_mst\r\n" + 
				     "a inner join designation_mst b on b.designation_code=a.designation_code where ddo_code ='" +userName+ "'";
		System.out.println("HQL:"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}

	@Override
	public MstEmployeeEntity findEmpData(Long employeeId) {
		// TODO Auto-generated method stub
		System.out.println("----------Inside findEMpdata------------");
		
		MstEmployeeEntity objDept = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objDept = currentSession.get(MstEmployeeEntity.class, employeeId);
		return objDept;
		
	}

	/*@Override
	public Serializable saveupdatePANhistory(UpdatePanNoHistryEntity updatePanNoHistryEntity) {
		// TODO Auto-generated method stub

		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId=0;
		saveId =currentSession.save(updatePanNoHistryEntity);
		return (Integer) saveId;
	
		
	}*/

	@Override
	public Serializable saveupdateMobNo(MstEmployeeEntity mstEmployeeEntity) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId=1;
		currentSession.merge(mstEmployeeEntity);
		return (Integer) saveId;
		}

	
}