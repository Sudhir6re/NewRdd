package com.mahait.gov.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgEmpMst;
import com.mahait.gov.in.entity.OrgGradeMst;

@Repository
public interface OrgGradeMstRepository  extends JpaRepository<OrgGradeMst, Long>{

	OrgGradeMst findByGradeId(long l);

}