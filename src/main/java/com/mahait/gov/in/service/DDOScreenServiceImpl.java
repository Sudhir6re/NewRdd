package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.repository.DDOScreenRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DDOScreenServiceImpl implements DDOScreenService{

	@Autowired
	DDOScreenRepo ddoScreenRepo;

	@Override
	public List<OrgDdoMst> findDDOByUsername(String ddoCode) {
		// TODO Auto-generated method stub
		return ddoScreenRepo.findDDOByUsername(ddoCode);
	}}
	
	
	
