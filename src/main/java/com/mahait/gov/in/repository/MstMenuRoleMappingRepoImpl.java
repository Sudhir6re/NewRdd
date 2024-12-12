package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstMenuRoleMappingEntity;
import com.mahait.gov.in.entity.MstSubMenuEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MstMenuRoleMappingRepoImpl implements MstMenuRoleMappingRepo {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public int saveMenuRoleMapping(MstMenuRoleMappingEntity mstMenuRoleMappingEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(mstMenuRoleMappingEntity);
		return (Integer) saveId;
	}

	@Override
	public List<MstMenuRoleMappingEntity> findMenuRoleMappingByMenuKey(int key) {
		String HQL = "FROM MstMenuRoleMappingEntity as t WHERE t.menuCode= '"+key+"' and t.is_active='"+1+"' ";
		return (List<MstMenuRoleMappingEntity>) entityManager.createQuery(HQL).getResultList();
	}

	@Override
	public List<MstSubMenuEntity> findSubMenuByMenuKeyAndRoleKey(int key, int roleKey) {
		String HQL = "FROM MstSubMenuEntity as t WHERE t.menuCode= '"+key+"' and t.roleId ='"+roleKey+"' and t.is_active='"+1+"'  ";
		return (List<MstSubMenuEntity>) entityManager.createQuery(HQL).getResultList();
	}
	@Override
	public MstMenuRoleMappingEntity findMenuRoleMappingByMenuKeyAndRoleKey(int key, int roleKey) {
		String HQL = "FROM MstMenuRoleMappingEntity as t WHERE t.menuCode= '"+key+"' and roleId ='"+roleKey+"' ";
		return (MstMenuRoleMappingEntity) entityManager.createQuery(HQL).getSingleResult();
	}

	@Override
	public void updateMenuRoleMappingStatus(MstMenuRoleMappingEntity objMenu) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(objMenu);
	}

	@Override
	public MstMenuRoleMappingEntity findMenuAndRoleMappingByKey(int key) {
		MstMenuRoleMappingEntity objMenuEntity = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objMenuEntity = currentSession.get(MstMenuRoleMappingEntity.class, key);
		return objMenuEntity;
	}

	@Override
	public List<MstMenuRoleMappingEntity> findMenuRoleMappingByMenuKeyAndRoleKeyList(Integer menu_key, Integer role_key) {
		String HQL = "FROM MstMenuRoleMappingEntity as t WHERE t.menuCode= '"+menu_key+"' and roleId ='"+role_key+"' ";
		return (List<MstMenuRoleMappingEntity>) entityManager.createQuery(HQL).getResultList();
	}
}
