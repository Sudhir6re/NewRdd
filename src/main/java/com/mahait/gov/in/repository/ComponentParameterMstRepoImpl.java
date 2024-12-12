package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.ComponentParameterMstEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class ComponentParameterMstRepoImpl implements ComponentParameterMstRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void transferData(List<ComponentParameterMstEntity> targetList) {
		Session currentSession = entityManager.unwrap(Session.class);

		for (ComponentParameterMstEntity entity : targetList) {
			currentSession.saveOrUpdate(entity);
		}
		currentSession.flush();
		currentSession.clear();
	}

	@Override
	public Optional<ComponentParameterMstEntity> findById(Long compId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT " + "FROM   component_parameter_mst a where a.id=" + compId;
		Query<ComponentParameterMstEntity> query = currentSession.createQuery(hql, ComponentParameterMstEntity.class);
		query.setParameter("compId", compId); // Set the compId parameter

		ComponentParameterMstEntity result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			return Optional.empty();
		}
		return Optional.of(result);
	}

	@Override
	public Optional<ComponentParameterMstEntity> findByComponentName(String componentName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT " + "FROM   component_parameter_mst  where CompntParameterName=" + componentName;
		Query<ComponentParameterMstEntity> query = currentSession.createQuery(hql, ComponentParameterMstEntity.class);
		query.setParameter("CompntParameterName", componentName); // Set the compId parameter

		ComponentParameterMstEntity result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			return Optional.empty();
		}
		return Optional.of(result);
	}
	}

