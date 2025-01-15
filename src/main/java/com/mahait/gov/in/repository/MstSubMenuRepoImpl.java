package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.MstSubMenuEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MstSubMenuRepoImpl implements MstSubMenuRepo {

	@PersistenceContext	
	private EntityManager entityManager;

//	protected final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public int saveSubMenu(MstSubMenuEntity mstSubMenuEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(mstSubMenuEntity);
		return (Integer) saveId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MstSubMenuEntity> findSubMenuByMenuKey(int key) {
		String HQL = "FROM MstSubMenuEntity as t WHERE t.menuCode= '"+key+"' and is_active ='"+1+"' ";
		return (List<MstSubMenuEntity>) entityManager.createQuery(HQL).getResultList();
	}
	
	@Override
	public MstSubMenuEntity findSubMenuBySubMenuKeyMenuKeyAndRoleKey(int subMenuKey, int menuKey,
			int roleKey) {
		String HQL = "FROM MstSubMenuEntity as t WHERE t.subMenuId = '"+subMenuKey+"' AND t.menuCode= '"+menuKey+"' and roleId ='"+roleKey+"' ";
		return (MstSubMenuEntity) entityManager.createQuery(HQL).getSingleResult();
	}

	@Override
	public void updateSubMenu(MstSubMenuEntity mstSubMenuEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(mstSubMenuEntity);
	}

	@Override
	public MstSubMenuEntity findSubMenuBySubMenuKey(int getSubMenuId) {
		MstSubMenuEntity objSubMenuEntity = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objSubMenuEntity = currentSession.get(MstSubMenuEntity.class, getSubMenuId);
		return objSubMenuEntity;
	}

	@Override
	public MstRoleEntity findRoleWiseUrl(Integer roleId) {
		return entityManager.find(MstRoleEntity.class, roleId);
	}
}
