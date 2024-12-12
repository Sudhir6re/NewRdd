package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.ComponetMappingEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ComponentMappingRepositoryImpl implements ComponentMappingRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public ComponetMappingEntity findByUser(Object componentParameterMstEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComponetMappingEntity> save(List<ComponetMappingEntity> mappings) {

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.merge(mappings);
		currentSession.flush();
		currentSession.clear();
		
		return mappings;

	}

	

}
