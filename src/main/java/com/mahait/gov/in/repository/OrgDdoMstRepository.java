package com.mahait.gov.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgPostDetailsRlt;


@Repository
public interface OrgDdoMstRepository  extends JpaRepository<OrgDdoMst, Long>{

	List<OrgDdoMst> findAllByDdoCode(String strRepoDDOCode);
	
	 @Query("SELECT o FROM OrgDdoMst o WHERE o.ddoCode LIKE %:ddoCode%")
	 List<OrgDdoMst> findByDdoCodeLike(@Param("ddoCode") String ddoCode);

	List<OrgDdoMst> findByLocationCode(String string);

}