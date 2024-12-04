package com.mahait.gov.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstRoleEntity;

@Repository
public interface MstRoleRepo extends JpaRepository<MstRoleEntity, Integer>  {

	
	MstRoleEntity findByRoleId(int role);
	
}

