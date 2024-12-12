package com.mahait.gov.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.CmnLanguageMst;


@Repository
public interface CmnLanguageMstRepository extends JpaRepository<CmnLanguageMst, Long>{

	CmnLanguageMst findByLangId(long l);
	
}
