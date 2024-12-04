package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.UserLoginHistryEntity;

public interface UserLoginHistryRepo {

	List<UserLoginHistryEntity> findlogindata();

	void saveLoginDtls(UserLoginHistryEntity userLoginHistryEntity);

}
