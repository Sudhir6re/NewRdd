package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstDepartmentEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class DepartmentMasterRepoImpl implements DepartmentMasterRepo {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<MstDepartmentEntity> listOfDept() {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select department_code,department_name_en,department_id from department_mst";
		Query query = currentSession.createNativeQuery(HQL);
	List<Object[]> lstobj= query.getResultList();;
	
	List<MstDepartmentEntity> lstMstDepartmentEntity = new ArrayList();
		for(Object obj[]:lstobj)
		{
			MstDepartmentEntity mstDepartmentEntity = new MstDepartmentEntity();
		mstDepartmentEntity.setDepartmentCode(StringHelperUtils.isNullInt(obj[0]));
		mstDepartmentEntity.setDepartmentNameEn(StringHelperUtils.isNullString(obj[1]));
		mstDepartmentEntity.setDepartmentId(StringHelperUtils.isNullInt(obj[2]));
		
		
		lstMstDepartmentEntity.add(mstDepartmentEntity);
		}
		return lstMstDepartmentEntity;
	}

	@Override
	public List<Object[]> lstadminOfc() {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select office_code,office_name from ZP_ADMIN_OFFICE_MST";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.getResultList();
	}

}
