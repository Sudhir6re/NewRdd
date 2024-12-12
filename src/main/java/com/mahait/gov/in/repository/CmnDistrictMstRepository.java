package com.mahait.gov.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.entity.ZpAdminNameMst;


@Repository
public interface CmnDistrictMstRepository extends JpaRepository<CmnDistrictMst, Long>{
	
	 List<CmnDistrictMst> findByLangIdAndStateIdOrderByDistrictName(Integer langId, Long stateId);

}

