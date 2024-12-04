package com.mahait.gov.in.repository;

import com.mahait.gov.in.entity.OrgUserMst;

public interface LimitLoginRepo {

	void updateFailedAttempts(OrgUserMst user);

	OrgUserMst findUserbyUsername(String username);

}
