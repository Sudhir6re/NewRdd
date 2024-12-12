package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;

public interface ForgotPasswordRepo {

	List<Object[]> findUserByEmail(String emailId);

	void updateTokenDetails(OrgUserMst orgUserMst);

	OrgUserMst findUserObjectByUserName(String username);

	OrgUserMst checkTokenIsValid(String token);

	void updatePassword(OrgUserMst orgUserMst);

}
