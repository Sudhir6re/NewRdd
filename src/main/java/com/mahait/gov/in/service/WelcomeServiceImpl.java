package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.ApplicationAdminMstEntity;
import com.mahait.gov.in.repository.TerminateRepo;
import com.mahait.gov.in.repository.WelcomeRepo;


@Service
@Transactional
public class WelcomeServiceImpl implements WelcomeService{
	
	@Autowired
	WelcomeRepo welcomeRepo;
	
	@Autowired
	TerminateRepo terminateRepo;

	@Override
	public List<ApplicationAdminMstEntity> findApplicationAll() {
		
		return welcomeRepo.findAllAsc();
	}

	@Override
	public List<ApplicationAdminMstEntity> findApplicationOnebyId(int applicationCode) {
		
		return welcomeRepo.findAllByApplicationCode(applicationCode);
	}

	@Override
	public String idleTerminateConnectivity() {
		
		return terminateRepo.idleTerminateConnectivity();
		
	}
	
	

}
