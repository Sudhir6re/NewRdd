package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.ComponentMstEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class ComponentMstRepositoryImpl implements ComponentMstRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Optional<ComponentMstEntity> findByParameterName(String parameterName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT " + "FROM   component_mst  where componentName=" + parameterName;
		Query<ComponentMstEntity> query = currentSession.createQuery(hql, ComponentMstEntity.class);
		query.setParameter("componentName", parameterName); // Set the compId parameter

		ComponentMstEntity result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			return Optional.empty();
		}
		return Optional.of(result);
	}

	@Override
	public List<ComponentMstEntity> saveAll(List<ComponentMstEntity> componentList) {
		Session currentSession = entityManager.unwrap(Session.class);

		for (ComponentMstEntity entity : componentList) {
			currentSession.saveOrUpdate(entity);
		}
		currentSession.flush();
		currentSession.clear();
		return componentList;
	}

	public List<ComponentMstEntity> lstAllComponent() {
		String HQL = "From ComponentMstEntity";
		return entityManager.createQuery(HQL).getResultList();
	}

	@Override
	public ComponentMstEntity save(ComponentMstEntity componentParameterMstEntity) {
	
	    // Unwrapping the session from the EntityManager
	    Session currentSession = entityManager.unwrap(Session.class);
	    
	    // Save or update the entity
	    currentSession.saveOrUpdate(componentParameterMstEntity);
	    
	    // Flush and clear the session to ensure the data is committed and session is clean
	    currentSession.flush();
	    currentSession.clear();
	    
	    return componentParameterMstEntity; 
	}


	@Override
	public void updateComponentParameter(ComponentMstEntity objMenu) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(objMenu);
		
	}

	@Override
	public ComponentMstEntity findComponentParameterByIdForEdit(Long Id) {
		ComponentMstEntity objMenu = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objMenu = currentSession.get(ComponentMstEntity.class, Id);
		return objMenu;
	}

	@Override
	public ComponentMstEntity findMenuByidForDelete(long id) {
		ComponentMstEntity objMenu = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objMenu = currentSession.get(ComponentMstEntity.class, id);
		return objMenu;
	}

	@Override
	public void deleteComponent(ComponentMstEntity objMenu) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(objMenu);
		
	}

	
}

