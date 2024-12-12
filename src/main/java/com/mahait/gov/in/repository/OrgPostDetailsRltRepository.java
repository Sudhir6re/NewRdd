package com.mahait.gov.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgPostDetailsRlt;
import com.mahait.gov.in.entity.OrgPostMst;

@Repository
public interface OrgPostDetailsRltRepository  extends JpaRepository<OrgPostDetailsRlt, Long>{

	List<OrgPostDetailsRlt> findByOrgPostMst(OrgPostMst createdByPost);

}