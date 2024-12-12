package com.mahait.gov.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstTalukaEntity;


	@Repository
	public interface MstSaveTalukaRepo extends JpaRepository<MstTalukaEntity, Integer> {


}
