package com.mahait.gov.in.repository;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstMenuEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MstMenuRepoImpl implements MstMenuRepo {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public int saveMenu(MstMenuEntity objMenuEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(objMenuEntity);
		return (Integer) saveId;
	}

	@Override
	public MstMenuEntity findMenuByKeyForDelete(int key) {
		MstMenuEntity objMenu = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objMenu = currentSession.get(MstMenuEntity.class, key);
		return objMenu;
	}

	@Override
	public void updateMenuStatus(MstMenuEntity objMenu) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(objMenu);
	}

	@Override
	public MstMenuEntity findMenuByKeyForEdit(int key) {
		MstMenuEntity objMenu = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objMenu = currentSession.get(MstMenuEntity.class, key);
		return objMenu;
	}

}
