package com.mahait.gov.in.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class NonGovDeducMasterRepoImpl implements NonGovDeducMasterRepo {
	
	@PersistenceContext
	EntityManager entityManager;

}
