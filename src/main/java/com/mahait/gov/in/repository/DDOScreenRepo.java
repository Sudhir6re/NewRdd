package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgDdoMst;

public interface DDOScreenRepo {

	List<OrgDdoMst> findDDOByUsername(String ddoCode);

}
