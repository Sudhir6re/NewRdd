package com.mahait.gov.in.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.repository.UserInfoRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserInfoRepo userInfoRepo;

	public OrgUserMst getUserIdbyUserName(String username) {
		OrgUserMst user = userInfoRepo.getUserIdbyUserName(username);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		OrgUserMst user = userInfoRepo.getUserIdbyUserName(username);
		MstRoleEntity mstRoleEntity = user.getMstRoleEntity();
		GrantedAuthority authority = new SimpleGrantedAuthority((mstRoleEntity).getRoleName());
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					Arrays.asList(authority));
		}
		throw new RuntimeException("User not found");
	}

}
