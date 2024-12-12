package com.mahait.gov.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mahait.gov.in.entity.CmnLocationMst;

public interface CmnLocationMstRepository extends JpaRepository<CmnLocationMst, Long>{

	
	 @Query("SELECT MAX(e.locId) FROM CmnLocationMst e")
	 Long findMaxLocId();

	CmnLocationMst findByLocId(long parseLong);

	 
	 
	 
	

}
