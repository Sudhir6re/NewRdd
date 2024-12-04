package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.ApplicationAdminMstEntity;

public interface WelcomeService {
	
	public List<ApplicationAdminMstEntity> findApplicationAll();
	
	public List<ApplicationAdminMstEntity> findApplicationOnebyId(int applicationCode);
	
	public String idleTerminateConnectivity();

}
