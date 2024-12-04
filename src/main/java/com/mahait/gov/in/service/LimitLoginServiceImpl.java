package com.mahait.gov.in.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.repository.LimitLoginRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class LimitLoginServiceImpl implements LimitLoginService {

	public static final int MAX_FAILED_ATTEMPTS = 3;

	// private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24
	// hours
	private static final long LOCK_TIME_DURATION = 12 * 60 * 1000;

	@Autowired
	private LimitLoginRepo repo;

	public void increaseFailedAttempts(OrgUserMst user) {
		int newFailAttempts = user.getInvalidLoginCnt() + 1;
		if (newFailAttempts == 3) {
			user.setActivateFlag(0l);
		}
		user.setInvalidLoginCnt(newFailAttempts);
		repo.updateFailedAttempts(user);
	}

	public void resetFailedAttempts(String username) {
		OrgUserMst userInfo = repo.findUserbyUsername(username);
		userInfo.setInvalidLoginCnt(0);
		repo.updateFailedAttempts(userInfo);
	}

	public void lock(OrgUserMst user) {
		user.setActivateFlag(0l);
		user.setUnlockTime(new Timestamp(new Date().getTime()));
		user.setInvalidLoginCnt(user.getInvalidLoginCnt() + 1);
		repo.updateFailedAttempts(user);
	}

	public boolean unlockWhenTimeExpired(OrgUserMst user) {
		long lockTimeInMillis = user.getUnlockTime().getTime();
		long currentTimeInMillis = System.currentTimeMillis();
		if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
			user.setUnlockTime(null);
			user.setInvalidLoginCnt(0);
			user.setActivateFlag(1l);
			repo.updateFailedAttempts(user);
			return true;
		}
		return false;
	}

	@Override
	public OrgUserMst findUserbyUsername(String username) {
		return repo.findUserbyUsername(username);
	}

}
