package com.mahait.gov.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.repository.DDOOfficeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DDOOfficeServiceImpl implements DDOOfficeService{

	
	@Autowired
	DDOOfficeRepo ddoOfficeRepo;
	
	

}
