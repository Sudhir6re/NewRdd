package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository
@SuppressWarnings("unchecked")
public class MstDesignationRepoImpl implements MstDesignationRepo {

	@PersistenceContext	
	private EntityManager entityManager;
	
	
//	protected final Log logger = LogFactory.getLog(getClass());
	
	/*@Override
	public int saveDesignationMst(MstDesignationEntity mstDesignationEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = currentSession.save(mstDesignationEntity);
		return (Integer) saveId;
	}

	@Override
	public List<Object[]> getCadreDesc(Integer fieldDepartmrntID) {
		String HQL = "SELECT t.cadreId, t.cadreDescription FROM MstCadreEntity as t WHERE t.fieldDepartmrnt='"+fieldDepartmrntID+"' ";
		return (List<Object[]>) entityManager.createQuery(HQL).getResultList();
	}*/

	@Override
	public List<MstPayCommissionEntity> findAllPayCommission() {
		String HQL = "FROM MstPayCommissionEntity as t where t.isActive ='1' ORDER BY t.id";
		return (List<MstPayCommissionEntity>) entityManager.createQuery(HQL).getResultList();
	}

	@Override
	public List<Object[]> getDesignationMstData() {

		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT a.designation_id, "+
				     
				       "a.designation_code, "+
				       "a.designation_name, "+
				       "a.designation_short_name, "+
					
				       "a.is_active,b.cadre_name,a.cadre_code "+ 
				"FROM   designation_mst a left join cadre_mst b on a.cadre_code = b.cadre_code  ";
				   
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.getResultList();
	}
	@Override
	public List<Object[]> getCadre() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select * from cadre_group_mst";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.getResultList();
	}
	
	@Override
	public int saveDesignationMst(MstDesignationEntity mstDesignationEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(mstDesignationEntity);
		return (Integer) saveId;
	}
	
	@Override
	public MstDesignationEntity findMstDesgByDesgId(Long designationId) {
		MstDesignationEntity objCad = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objCad = currentSession.get(MstDesignationEntity.class, designationId);
		return objCad;
	}
	
	
	

	/*@Override
	public List<MstCadreEntity> findCadreDescByFldDeptId(int fldDeptId) {
		String HQL = "FROM MstCadreEntity as t WHERE t.fieldDepartmrnt='"+fldDeptId+"' ";
		return (List<MstCadreEntity>) entityManager.createQuery(HQL).getResultList();
	}*/

	@Override
	public void updateDesginationStatus(MstDesignationEntity objDesg) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(objDesg);
	}

	@Override
	public List<Long> validateDesignationName(String desgname) {
		Session currentSession =  entityManager.unwrap(Session.class);

		String hql = "select count(*) as count from designation_mst where designation_name ='"+ desgname+"'";
		
		Query query = currentSession.createNativeQuery(hql).addScalar("count", Long.class);
		
		List<Long> lstresult = query.getResultList();
		return lstresult;
	}



	
}
