package com.mahait.gov.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.ZpAdminNameMst;
import com.mahait.gov.in.entity.ZpAdminOfficeMst;

@Repository
public interface ZpAdminNameMstRepository extends JpaRepository<ZpAdminNameMst, Long> {

	 List<ZpAdminNameMst> findByAdminCodeInOrderByAdminCode(List<String> adminCodes);

	   @Query(value = "SELECT COALESCE(MAX(CAST(admin_code AS integer)), 0) FROM zp_admin_name_mst", nativeQuery = true)
	   Long findMaxAdminCode();

	   
	   

}