package com.mahait.gov.in.service;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.servlet.http.HttpServletRequest;

public interface ForgotPasswordService {

	OrgUserMst initiatePasswordReset(String emailId, HttpServletRequest request);

	OrgUserMst checkTokenIsValid(String token);

	void updatePassword(OrgUserMst orgUserMst);

}
