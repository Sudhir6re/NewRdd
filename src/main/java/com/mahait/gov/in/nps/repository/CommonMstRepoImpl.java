package com.mahait.gov.in.nps.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstCommonEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Repository
public class CommonMstRepoImpl implements CommonMstRepo {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<MstCommonEntity> findAllCommonConst() {
		TypedQuery<MstCommonEntity> query = entityManager.createQuery(
				"SELECT a FROM MstCommonEntity a",MstCommonEntity.class);
		return query.getResultList();
	}

	@Override
	public MstCommonEntity findCommonMstById(Integer commonId) {
		return entityManager.find(MstCommonEntity.class, commonId);
	}

	@Override
	public void updateCommonMst(MstCommonEntity mstCommonEntity) {
		entityManager.merge(mstCommonEntity);
	}

	@Override
	public void saveCommonMst(MstCommonEntity mstCommonEntity) {
		entityManager.merge(mstCommonEntity);
	}

}
