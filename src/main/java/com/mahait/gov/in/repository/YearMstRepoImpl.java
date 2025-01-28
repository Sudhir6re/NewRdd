package com.mahait.gov.in.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstYearEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class YearMstRepoImpl  implements YearMstRepo{
	
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<MstYearEntity> findAllYears() {
		TypedQuery<MstYearEntity> query = entityManager.createQuery(
				"SELECT a FROM MstYearEntity",MstYearEntity.class);
		return query.getResultList();
	}

	@Override
	public MstYearEntity findYearById(Integer commonId) {
		return entityManager.find(MstYearEntity.class, commonId);
	}

	@Override
	public void updateYear(MstYearEntity mstCommonEntity) {
		entityManager.merge(mstCommonEntity);
	}

	@Override
	public void saveYear(MstYearEntity mstCommonEntity) {
		entityManager.merge(mstCommonEntity);
	}


}
