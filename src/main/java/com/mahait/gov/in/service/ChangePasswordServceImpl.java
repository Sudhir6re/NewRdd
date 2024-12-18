package com.mahait.gov.in.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.repository.ChangePasswordRepo;


@Service
@Transactional
public class ChangePasswordServceImpl implements ChangePasswordServce {
	
	@Autowired
	ChangePasswordRepo changePasswordRepo;
	
	@Override
	public String updateUser(OrgUserMst orgUserMst) {
		changePasswordRepo.updateUser(orgUserMst);
		return "UPDATED";
	}

}
