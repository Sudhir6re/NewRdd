package com.mahait.gov.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.repository.UserInfoRepo;

@Service
public class UserService {

	@Autowired
	UserInfoRepo userInfoRepo;

	public OrgUserMst getUserIdbyUserName(String username) {
		return userInfoRepo.getUserIdbyUserName(username);
	}

}
