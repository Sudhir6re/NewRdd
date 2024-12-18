package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgDdoMst;

public interface DDOScreenService {

	List<OrgDdoMst> findDDOByUsername(String userName);

}
