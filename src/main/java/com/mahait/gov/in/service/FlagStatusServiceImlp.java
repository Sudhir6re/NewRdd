package com.mahait.gov.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.repository.FlagStatusRepo;

@Service
public class FlagStatusServiceImlp implements  FlagStatusService{
	@Autowired
	FlagStatusRepo FlagStatusRepo;

}
