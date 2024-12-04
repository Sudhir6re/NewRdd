package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.UserLoginHistryEntity;

public interface UserLoginHistryService {

	public List<UserLoginHistryEntity> findlogindata();

	public void saveLoginDtls(UserLoginHistryEntity userLoginHistryEntity);

}
