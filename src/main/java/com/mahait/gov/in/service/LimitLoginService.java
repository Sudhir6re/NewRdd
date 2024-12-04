package com.mahait.gov.in.service;

import com.mahait.gov.in.entity.OrgUserMst;

public interface LimitLoginService {

	public static final int MAX_FAILED_ATTEMPTS = 3;

	public static final long LOCK_TIME_DURATION = 12 * 60 * 1000; // 24 hours

	public void increaseFailedAttempts(OrgUserMst user);

	public void resetFailedAttempts(String username);

	public void lock(OrgUserMst user);

	public boolean unlockWhenTimeExpired(OrgUserMst user);

	public OrgUserMst findUserbyUsername(String username);

}
