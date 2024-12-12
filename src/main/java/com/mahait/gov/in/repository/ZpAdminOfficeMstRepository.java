package com.mahait.gov.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.ZpAdminOfficeMst;


@Repository
public interface ZpAdminOfficeMstRepository  extends JpaRepository<ZpAdminOfficeMst, Long>{


	   @Query("SELECT CASE WHEN COUNT(z) > 0 THEN true ELSE false END " +
		       "FROM ZpAdminOfficeMst z WHERE LOWER(z.officeName) = LOWER(:officeName)")
	    boolean existsByOfficeName(@Param("officeName") String officeName);

	

}
