package com.mahait.gov.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahait.gov.in.entity.CmnLookupMst;

public interface CmnLookupMstRepository  extends JpaRepository<CmnLookupMst, Long>{

	CmnLookupMst findByLookupId(long l);
	

}