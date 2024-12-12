package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.HRAAllowanceMstEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository

public class HRAAllowanceMstRepoImpl implements HRAAllowanceMstRepo {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public int SaveHRAAllowanceMaster(HRAAllowanceMstEntity hraAllowanceMstEntity) {
		// TODO Auto-generated method stub
		
		Session session=entityManager.unwrap(Session.class);
		session.save(hraAllowanceMstEntity);
		return (int) session.save(hraAllowanceMstEntity);
	}

	@Override
	public List<MstPayCommissionEntity> getlstddcPayCommission() {
		// TODO Auto-generated method stub
		String HQL = "FROM MstPayCommissionEntity as t ORDER BY t.id";
		return (List<MstPayCommissionEntity>) entityManager.createQuery(HQL).getResultList();
		
	}

	@Override
	public List<Object[]> getlstAllowanceDeductionMst() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select a.ALLOWANCE_HRA_ID,a.START_DATE,a.END_DATE,a.city_class_X,a.city_class_Y,a.city_class_Z,b.commission_name_en,a.is_active,b.commission_name_mh from ALLOWANCE_HRA_MST a inner join pay_commission_mst b on a.pay_commission_code=b.pay_commission_code ";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public int checkComponentAlreadyPresent(HRAAllowanceMstEntity hraAllowanceMstEntity) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String  HQL = "FROM HRAAllowanceMstEntity as t where  t.payCommissionCode="+hraAllowanceMstEntity.getPayCommissionCode()+" ORDER BY t.allowanceHRAId DESC ";
		List<HRAAllowanceMstEntity> lstAllowanceDeductionMstEntity= (List<HRAAllowanceMstEntity>) entityManager.createQuery(HQL).getResultList();
		Integer id = lstAllowanceDeductionMstEntity.stream().map(m -> m.getAllowanceHRAId()).findFirst().orElse(0);
		return id;
	}

	@Override
	public HRAAllowanceMstEntity findAllowanceDeductionWiseMasterById(int checkIsDataAlreadyPresent) {

		// TODO Auto-generated method stub
		HRAAllowanceMstEntity allowanceDeductionMstEntity=entityManager.find(HRAAllowanceMstEntity.class, checkIsDataAlreadyPresent);
		return allowanceDeductionMstEntity;
	
	}

	@Override
	public void updateAllowanceDeductionMstEntity(HRAAllowanceMstEntity hRAAllowanceMstEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(hRAAllowanceMstEntity);
		
	}

	@Override
	public HRAAllowanceMstEntity findEntrybyId(int allowanceHRAId) {
		// TODO Auto-generated method stub
		HRAAllowanceMstEntity objforedit = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objforedit = currentSession.get(HRAAllowanceMstEntity.class, allowanceHRAId);
		
		return objforedit;
		
	}

	@Override
	public HRAAllowanceMstEntity findbyhraid(Integer allowanceHRAId) {
		HRAAllowanceMstEntity objforedit = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objforedit = currentSession.get(HRAAllowanceMstEntity.class, allowanceHRAId);
		
		return objforedit;
	}

	@Override
	public void updateHRAStatus(HRAAllowanceMstEntity hraAllowanceMstEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(hraAllowanceMstEntity);
		
	}

	@Override
	public HRAAllowanceMstEntity findAllowanceHRAByIdForReject(int allowanceHRAId) {
		HRAAllowanceMstEntity objCadre = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objCadre = currentSession.get(HRAAllowanceMstEntity.class, allowanceHRAId);
		return objCadre;
	}

	@Override
	public void updateAllowanceHRAStatus(HRAAllowanceMstEntity objCadre) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(objCadre);
		
	}

}
