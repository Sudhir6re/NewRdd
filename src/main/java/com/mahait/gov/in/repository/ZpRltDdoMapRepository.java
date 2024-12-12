package com.mahait.gov.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.ZpRltDdoMap;


@Repository
public interface ZpRltDdoMapRepository extends JpaRepository<ZpRltDdoMap, Long> {
	
	
	
}
